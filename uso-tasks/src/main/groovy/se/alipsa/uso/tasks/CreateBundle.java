package se.alipsa.uso.tasks;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.LogLevel;

public class CreateBundle extends Task {

  static final List<String> CHECKSUM_ALGOS = Arrays.asList("MD5", "SHA-1", "SHA-256");

  /**
   * Create a mega bundle zip with the artifacts for all projects in this build..
   * This method requires that the "verify" phase has been executed and gpg signing has been configured.
   *
   * @param bundleFile the zip file to create
   * @throws IOException if creating the zip file failed
   * @throws NoSuchAlgorithmException if md5, sha-1 or sha-256 algorithms are not available in
   * the environment.
   */
  public void createZipBundle(File bundleFile, List<MavenProject> allProjectsUsingPlugin)
      throws IOException, NoSuchAlgorithmException, MojoExecutionException {
    bundleFile.getParentFile().mkdirs();
    bundleFile.createNewFile();

    Map<String, List<File>> artifactFiles = new HashMap<>();
    for (MavenProject project : allProjectsUsingPlugin) {
      String groupId = project.getGroupId();
      String artifactId = project.getArtifactId();
      String version = project.getVersion();
      String groupPath = groupId.replace('.', '/');
      String mavenPathPrefix = String.join("/", groupPath, artifactId, version) + "/";
      artifactFiles.computeIfAbsent(mavenPathPrefix, k -> new ArrayList<>());
      File artifactFile = project.getArtifact().getFile();
      // Will be null for e.g., an aggregator project
      if (artifactFile != null && artifactFile.exists()) {
        artifactFiles.get(mavenPathPrefix).add(artifactFile);
        File signFile = new File(artifactFile.getAbsolutePath() + ".asc");
        if (!signFile.exists()) {
          throw new MojoExecutionException(artifactFile + " is not signed, " + signFile + " is missing");
        }
        artifactFiles.get(mavenPathPrefix).add(signFile);
      }
      // pom is not in getAttachedArtifacts so add it explicitly
      File pomFile = new File(project.getBuild().getDirectory(), String.join("-", artifactId, version) + ".pom");
      if (pomFile.exists()) {
        // Since it is the "raw" pom file that is published, not the effective pom, we must check the file,
        // not the project. Also since the pom file is the signed one, we cannot change it to the effective pom.
        // validateForPublishing(pomFile);

        artifactFiles.get(mavenPathPrefix).add(pomFile);
        File signFile = new File(pomFile.getAbsolutePath() + ".asc");
        if (!signFile.exists()) {
          throw new BuildException(
              "POM file " + pomFile + " is not signed, " + signFile + " is missing");
        }
        artifactFiles.get(mavenPathPrefix).add(signFile);

      } else {
        log("POM file " + pomFile + " does not exist (verify phase not reached)!", LogLevel.ERR.getLevel());
        throw new BuildException("POM file " + pomFile + " does not exist!");
      }

      for (Artifact artifact : project.getAttachedArtifacts()) {
        File file = artifact.getFile();
        if (file.exists()) {
          artifactFiles.get(mavenPathPrefix).add(artifact.getFile());
        } else {
          log("Artifact " + artifact.getId() + " does not exist!", LogLevel.ERR.getLevel());
        }
        File signFile = new File(file.getAbsolutePath() + ".asc");
        if (!signFile.exists()) {
          throw new BuildException("File " + file + " is not signed, " + signFile + " is missing");
        }
        artifactFiles.get(mavenPathPrefix).add(signFile);
      }
    }

    try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(bundleFile.toPath()))) {
      for (Map.Entry<String, List<File>> entry : artifactFiles.entrySet()) {
        String mavenPathPrefix = entry.getKey();
        for (File file : entry.getValue()) {
          addToZip(file, mavenPathPrefix, zipOut);
          if (file.getName().endsWith(".asc")) {
            continue; // asc files has no checksums
          }
          // Ensure the artifact is signed before continuing to create and add checksums
          File signFile = new File(file.getAbsolutePath() + ".asc");
          if (!signFile.exists()) {
            throw new BuildException(
                "The artifact " + file + " was not signed! " + signFile + " does not exists");
          }
          generateChecksumsAndAddToZip(file, mavenPathPrefix, zipOut);
        }
      }
    }
  }

  /**
   * Create a bundle zip with the artifacts for the current project only.
   * This method requires that the "verify" phase has been executed and gpg signing has been configured.
   * e.g. mvn verify deploy:bundle
   *
   * @param bundleFile the zip file to create
   * @throws IOException if creating the zip file failed
   * @throws NoSuchAlgorithmException if md5, sha-1 or sha-256 algorithms are not available in
   * the environment.
   */
  public void createZipBundle(File bundleFile) throws IOException, NoSuchAlgorithmException, BuildException {
    createZipBundle(bundleFile, Collections.singletonList(project));
  }

  /**
   * Validates that the following elements are present (required for publishing to central):
   * <ul>
   *  <li>Project description</li>
   *  <li>License information</li>
   *  <li>SCM URL</li>
   *  <li>Developers information</li>
   * </ul>
   * Not used now. TODO: must verify with central if a child project can omit this.
   */
  private void validateForPublishing(File pomFile) throws BuildException {
    Model model = readPomFile(pomFile);
    List<String> errs = new ArrayList<>();
    if (model.getDescription() == null || model.getDescription().trim().isEmpty()) {
      errs.add("description is missing");
    }
    if (model.getLicenses() == null || model.getLicenses().isEmpty()) {
      errs.add("license is missing");
    }
    if (model.getScm() == null) {
      errs.add("scm is missing");
    } else if (model.getScm().getUrl() == null) {
      errs.add("scm url is missing");
    }
    if (model.getDevelopers() == null || model.getDevelopers().isEmpty()) {
      errs.add("developers is missing");
    }

    if (!errs.isEmpty()) {
      throw new BuildException(pomFile + " is not valid for publishing: " + String.join(", ", errs));
    }
  }

  private void addToZip(File file, String prefix, ZipOutputStream zipOut) throws IOException {
    log("Create bundle, addToZip  - " + file.getAbsolutePath(), LogLevel.DEBUG.getLevel());
    zipOut.putNextEntry(new ZipEntry(prefix + file.getName()));
    Files.copy(file.toPath(), zipOut);
    zipOut.closeEntry();
  }

  private void generateChecksumsAndAddToZip(File sourceFile, String prefix, ZipOutputStream zipOut)
      throws NoSuchAlgorithmException, IOException {
    for (File checksumFile : generateChecksums(sourceFile, CHECKSUM_ALGOS)) {
      addToZip(checksumFile, prefix, zipOut);
    }
  }

  public List<File> generateChecksums(File file, List<String> algos) throws IOException {
    Map<String, Object> results = ChecksumUtils.calc(file, algos);
    List<File> checkSumFiles = new ArrayList<>();
    for (Map.Entry<String, Object> entry : results.entrySet()) {
      String extension = entry.getKey().toLowerCase().replace("-", "");
      File checksumFile = new File(file.getAbsolutePath() + "." + extension);
      if (!checksumFile.exists()) {
        Files.write(checksumFile.toPath(), entry.getValue().toString().getBytes(
            StandardCharsets.UTF_8));
      }
      checkSumFiles.add(checksumFile);
    }
    return checkSumFiles;
  }

  Model readPomFile(File pomFile) throws BuildException {
    try {
      MavenXpp3Reader reader = new MavenXpp3Reader();
      try (Reader fileReader = new FileReader(pomFile)) {
        return reader.read(fileReader);
      }
    } catch (XmlPullParserException | IOException e) {
      throw new BuildException("Failed to parse POM file.", e);
    }
  }
}
