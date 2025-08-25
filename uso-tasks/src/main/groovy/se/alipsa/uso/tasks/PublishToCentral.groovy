package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Task

import java.nio.file.Files
import java.util.regex.Matcher
import java.util.regex.Pattern

@CompileStatic
class PublishToCentral extends Task {

  static final String CENTRAL_PORTAL_URL = "https://central.sonatype.com/api/v1"

  private String username
  private String password
  private String publishUrl
  private File bundleFile
  private boolean autoDeploy = true
  private int pollDelayMs = 10000

  @Override
  void execute() {
    uploadAndCheck(bundleFile, autoDeploy)
  }

  void setVariables(String username, String password, String publishUrl) {
    this.username = username
    this.password = password
    this.publishUrl = (publishUrl != null && !publishUrl.trim().isEmpty()) ? publishUrl : CENTRAL_PORTAL_URL
  }

  String upload(File bundle, Boolean autoDeploy) throws IOException {
    String boundary = "----MavenCentralBoundary" + System.currentTimeMillis()
    String deployUrl = publishUrl + "/publisher/upload?name=" + bundle.getName()
    if (autoDeploy) {
      deployUrl += "&publishingType=AUTOMATIC"
    } else {
      deployUrl += "&publishingType=USER_MANAGED"
    }
    URL url = new URI(deployUrl).toURL()
    HttpURLConnection conn = (HttpURLConnection) url.openConnection()
    conn.setDoOutput(true)
    conn.setInstanceFollowRedirects(true)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Authorization", authHeader())
    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)

    try (OutputStream out = conn.getOutputStream();
         PrintWriter writer = new PrintWriter(new OutputStreamWriter(out))) {

      writer.append("--").append(boundary).append("\r\n")
      writer.append("Content-Disposition: form-data; name=\"bundle\"; filename=\"")
          .append(bundle.getName())
          .append("\"\r\n")
      writer.append("Content-Type: application/zip\r\n\r\n").flush()

      Files.copy(bundle.toPath(), out)
      out.flush();

      writer.append("\r\n--").append(boundary).append("--\r\n").flush()
    }

    int status = conn.getResponseCode()
    if (status >= 400) {
      log(deployUrl + " returned HTTP error code : " + status)
      try (InputStream is = conn.getErrorStream()) {
        String body = readFully(is)
        log("Response body: " + body)
      } catch (IOException e) {
        log("Failed to read response body" + e)
      }
      throw new IOException("Failed to upload: HTTP " + status)
    }

    try (InputStream is = conn.getInputStream()) {
      return readFully(is);
    }
  }

  /**
   * Query central for the deployment status of the deployment identified with the deploymentId
   * that was sent when the bundle was uploaded.
   * Example response:
   * <pre>{@code
   * {
   *   "deploymentId": "28570f16-da32-4c14-bd2e-c1acc0782365",
   *   "deploymentName": "central-bundle.zip",
   *   "deploymentState": "PUBLISHED",
   *   "purls": [
   *     "pkg:maven/com.sonatype.central.example/example_java_project@0.0.7"
   *   ]
   * }
   * }</pre>
   * @param deploymentId the identifier from the upload step
   * @return the deploymentState part of the response
   * @throws IOException if the connection could not be established
   */
  String getStatus(String deploymentId) throws IOException {
    URL url = new URI(publishUrl + "/publisher/status?id=" + deploymentId).toURL()
    HttpURLConnection conn = (HttpURLConnection) url.openConnection()
    conn.setInstanceFollowRedirects(true)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Authorization", authHeader())
    int status = conn.getResponseCode()
    if (status >= 400) {
      log("$url returned HTTP error code : $status")
      try (InputStream is = conn.getErrorStream()) {
        if (is != null) {
          String body = readFully(is)
          log("Response body: $body")
        }
      } catch (IOException e) {
        log("Failed to read response body: $e")
      }
      throw new IOException("Failed to get status: HTTP $status")
    }
    try (InputStream is = conn.getInputStream()) {
      if (is == null) {
        return "no response body";
      }
      String responseBody = readFully(is);
      Pattern pattern = Pattern.compile("\"deploymentState\"\\s*:\\s*\"([^\"]+)\"")
      Matcher matcher = pattern.matcher(responseBody)
      if (matcher.find()) {
        return matcher.group(1)
      } else {
        return "deploymentState not found in $responseBody"
      }
    }
  }

  private String authHeader() {
    return "Bearer " + Base64.getEncoder().encodeToString((getUsername() + ":" + getPassword()).getBytes());
  }

  private static String readFully(InputStream is) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream()
    byte[] chunk = new byte[8192]
    int bytesRead
    while ((bytesRead = is.read(chunk)) != -1) {
      buffer.write(chunk, 0, bytesRead)
    }
    return buffer.toString("UTF-8").trim()
  }

  void uploadAndCheck(File zipBundle, boolean autoDeploy) throws BuildException {
    String deploymentId
    try {
      deploymentId = upload(zipBundle, autoDeploy)
      if (deploymentId == null) {
        throw new BuildException("Failed to upload bundle, no deployment id found")
      }
    } catch (IOException e) {
      throw new BuildException("Failed to upload bundle", e)
    }

    log("Deployment ID: " + deploymentId)
    log("Waiting ${pollDelayMs/1000} seconds before checking status...")
    try {
      Thread.sleep(pollDelayMs)
      String status = getStatus(deploymentId)

      int retries = 10
      while (!Arrays.asList("VALIDATED", "PUBLISHING", "PUBLISHED", "FAILED")
          .contains(status)
          && retries-- > 0) {
        log("Deploy status is " + status)
        Thread.sleep(5000)
        status = getStatus(deploymentId)
      }
      switch (status) {
        case "VALIDATED":
          log("Validated: the project is ready for publishing!")
          log("See https://central.sonatype.com/publishing/deployments for more info")
          break
        case "PUBLISHING":
          log("Published: Project is publishing on Central!")
          break
        case "PUBLISHED":
          log("Published successfully!");
          break
        default:
          throw new BuildException("Release failed with status: " + status)
      }
    } catch (InterruptedException | IOException e) {
      throw new BuildException("Failed to check status", e)
    }
  }

  String getUsername() {
    return username
  }

  String getPassword() {
    return password
  }

  String getPublishUrl() {
    return publishUrl
  }

  void setUsername(String username) {
    this.username = username
  }

  void setPassword(String password) {
    this.password = password
  }

  void setPublishUrl(String publishUrl) {
    this.publishUrl = publishUrl
  }

  File getBundleFile() {
    return bundleFile
  }

  void setBundleFile(File bundleFile) {
    this.bundleFile = bundleFile
  }

  boolean getAutoDeploy() {
    return autoDeploy
  }

  void setAutoDeploy(boolean autoDeploy) {
    this.autoDeploy = autoDeploy
  }

  int getPollDelayMs() {
    return pollDelayMs
  }

  void setPollDelayMs(int pollDelayMs) {
    this.pollDelayMs = pollDelayMs
  }
}
