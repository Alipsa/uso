package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader
import org.apache.maven.resolver.internal.ant.tasks.AbstractDistTask
import org.apache.maven.resolver.internal.ant.types.Artifact
import org.apache.maven.resolver.internal.ant.types.Artifacts
import org.apache.maven.resolver.internal.ant.types.Pom
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.types.LogLevel
import org.codehaus.plexus.util.xml.pull.XmlPullParserException

import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.security.DigestOutputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * The artifacts and the pom file needs to be signed before creating the bundle.
 * This task will not sign them, only check that the .asc files are present.
 * Checksums (md5, sha1, sha256) will however, be created if not already present.
 * Usage:
 * <pre><code>
 *   artifact(file: '${jarFile}', type:'jar', id:'jar')
 *   artifact(file: '${srcJarFile}', type: 'jar', classifier: 'sources', id: 'sourceJar')
 *   artifact(file: '${groovydocJarFile}', type: 'jar', classifier: 'javadocs', id: 'javadocJar')
 *   artifacts(id: 'zipArtifacts') {
 *     artifact refid: 'jar'
 *     artifact refid: 'sourceJar'
 *     artifact refid: 'javadocJar'
 *   }
 *   createBundle(bundleFile: 'out/central-bundle.zip', artifactsref:'zipArtifacts')
 * </code></pre>
 * This task does the following:
 * <ol>
 *  <li>Create the zip file</li>
 *  <li>for each jar in artifacts</li>
 *    <li> Add it to zip under groupId/artifactId/version</li>
 *    <li> Create checksums (md5, sha1, sha256) add all to zip under groupId/artifactId/version</li>
 *  <li>Add the pom file to zip under groupId/artifactId/version</li>
 *  <li>Create checksums (md5, sha1, sha256) add all to zip under groupId/artifactId/version</li>
 * </ol>
 */
@CompileStatic
class CreateBundle extends AbstractDistTask {

  static final List<String> CHECKSUM_ALGOS = Arrays.asList("MD5", "SHA-1", "SHA-256")

  File bundleFile

  File getBundleFile() {
    return bundleFile
  }

  void setBundleFile(File bundleFile) {
    this.bundleFile = bundleFile
  }

  void execute() {
    Pom pom = getPom()
    Artifacts artifacts = getArtifacts()
    createZipBundle(bundleFile, pom, artifacts)
  }

  /**
   * Create a zip bundle with the artifacts for the project in this build..
   * This method requires that the "verify" phase has been executed and gpg signing has been configured.
   *
   * @param bundleFile the zip file to create
   * @throws IOException if creating the zip file failed
   * @throws NoSuchAlgorithmException if md5, sha-1 or sha-256 algorithms are not available in
   * the environment.
   */
  void createZipBundle(File bundleFile, Pom pom, Artifacts artifacts)
      throws IOException, NoSuchAlgorithmException, BuildException {
    bundleFile.getParentFile().mkdirs()
    bundleFile.createNewFile()

    // it seems that sometimes the groupId is not set in the pom but coords work, so parse it from coords instead
    String groupId = pom.getGroupId() ?: pom.coords.split(':')[0]
    String artifactId = pom.getArtifactId() ?: pom.coords.split(':')[1]
    String version = pom.getVersion() ?: pom.coords.split(':')[2]
    String groupPath = groupId.replace('.', '/')
    String pathPrefix = String.join("/", groupPath, artifactId, version) + "/"
    try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(bundleFile.toPath()))) {
      artifacts.artifacts.each { Artifact artifact ->
        addToZip(artifact.file, pathPrefix, zipOut)
        generateChecksumsAndAddToZip(artifact.file, pathPrefix, zipOut)
        File signFile = new File(artifact.file.getAbsolutePath() + ".asc")
        if (!signFile.exists()) {
          throw new BuildException("File " + artifact.file + " is not signed, " + signFile + " is missing")
        }
        addToZip(signFile, pathPrefix, zipOut)
      }


      // pom is not in artifacts so add it explicitly
      File pomFile = pom.getFile()
      if (pomFile.exists()) {
        // Since it is the "raw" pom file that is published, not the effective pom, we must check the file,
        // not the project. Also since the pom file is the signed one, we cannot change it to the effective pom.
        validateForPublishing(pomFile)
        File bundledPomFile
        if (pomFile.name == "${artifactId}-${version}.pom") {
          bundledPomFile = pomFile
        } else {
          bundledPomFile = new File(pomFile.getParentFile(), "${artifactId}-${version}.pom")
          Files.copy(pomFile.toPath(), bundledPomFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
        addToZip(bundledPomFile, pathPrefix, zipOut)
        generateChecksumsAndAddToZip(bundledPomFile, pathPrefix, zipOut)
        File signFile = new File(pomFile.getAbsolutePath() + ".asc")
        if (!signFile.exists()) {
          throw new BuildException(
              "POM file " + pomFile + " is not signed, " + signFile + " is missing")
        }
        File bundledPomSignFile = new File(bundledPomFile.getAbsolutePath() + ".asc")
        if (!bundledPomSignFile.exists()) {
          Files.copy(signFile.toPath(), bundledPomSignFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
        addToZip(bundledPomSignFile, pathPrefix, zipOut)
      }
    }
  }

  /**
   * Validates that the following elements are present (required for publishing to central):
   * <ul>
   *  <li>Project description</li>
   *  <li>License information</li>
   *  <li>SCM URL</li>
   *  <li>Developers information</li>
   * </ul>
   */
  private static void validateForPublishing(File pomFile) throws BuildException {
    Model model = readPomFile(pomFile)
    List<String> errs = new ArrayList<>()
    if (model.getDescription() == null || model.getDescription().trim().isEmpty()) {
      errs.add("description is missing")
    }
    if (model.getLicenses() == null || model.getLicenses().isEmpty()) {
      errs.add("license is missing")
    }
    if (model.getScm() == null) {
      errs.add("scm is missing")
    } else if (model.getScm().getUrl() == null) {
      errs.add("scm url is missing")
    }
    if (model.getDevelopers() == null || model.getDevelopers().isEmpty()) {
      errs.add("developers is missing")
    }

    if (!errs.isEmpty()) {
      throw new BuildException("$pomFile is not valid for publishing: ${String.join(", ", errs)}")
    }
  }

  private void addToZip(File file, String prefix, ZipOutputStream zipOut) throws IOException {
    log("Create bundle, addToZip  - " + file.getAbsolutePath(), LogLevel.DEBUG.getLevel())
    zipOut.putNextEntry(new ZipEntry(prefix + file.getName()))
    Files.copy(file.toPath(), zipOut)
    zipOut.closeEntry()
  }

  private void generateChecksumsAndAddToZip(File sourceFile, String prefix, ZipOutputStream zipOut)
      throws NoSuchAlgorithmException, IOException {
    for (File checksumFile : generateChecksums(sourceFile, CHECKSUM_ALGOS)) {
      addToZip(checksumFile, prefix, zipOut)
    }
  }

  List<File> generateChecksums(File file, List<String> algos) throws IOException {
    List<File> checkSumFiles = []
    algos.each {
      checkSumFiles << generateChecksum(file, it)
    }
    return checkSumFiles
  }

  File generateChecksum(File file, String algo) {
    String extension = algo.toLowerCase().replace('-', '')
    File checksumFile = new File("${file.absolutePath}.${extension}")
    if (checksumFile.exists()) {
      log("${checksumFile.name} already exists", LogLevel.DEBUG.level)
      return checksumFile
    }
    def digest = MessageDigest.getInstance(algo)
    file.withInputStream { is ->
      new DigestOutputStream(OutputStream.nullOutputStream(), digest)
          .withCloseable { dos -> is.transferTo(dos) }
    }
    def hash = digest.digest().encodeHex().toString()
    checksumFile.text = hash
    log("Generated ${algo} for ${file.name} → ${checksumFile.name}", LogLevel.DEBUG.level)
    checksumFile
  }

  static Model readPomFile(File pomFile) throws BuildException {
    try {
      MavenXpp3Reader reader = new MavenXpp3Reader();
      try (Reader fileReader = new FileReader(pomFile)) {
        return reader.read(fileReader);
      }
    } catch (XmlPullParserException | IOException e) {
      throw new BuildException("Failed to parse POM file.", e)
    }
  }
}
