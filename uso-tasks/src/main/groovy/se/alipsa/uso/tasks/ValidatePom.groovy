package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
import org.apache.maven.resolver.internal.ant.types.Dependency
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

/**
 * Validates a Maven pom file.
 * Usage:
 * <pre><code>
 *  validatePom(pomFile: "${pomFile})
 * </code></pre>
 *
 * The only required attributes are pomTarget.
 * @since 0.0.1
 */
@CompileStatic
class ValidatePom extends Task {

  private File pomFile

  void setPomFile(String pomTarget) {
    pomFile = new File(project.replaceProperties(pomTarget))
  }
  void setPomFile(File pomTarget) {
    pomFile = pomTarget
  }

  @Override
  void execute() {
    log("validate pom file ${pomFile.canonicalPath}", Project.MSG_VERBOSE)
    if(!pomFile.getParentFile().exists()) {
      pomFile.getParentFile().mkdirs()
    }
    validatePomContent(pomFile.text, schemaLocation)
  }

  static Map<String, String> toMap(Dependency dep) {
    Map<String, String> params = [:]
    if (dep.groupId) {
      params.put('groupId', dep.groupId)
    }
    if (dep.artifactId) {
      params.put('artifactId', dep.artifactId)
    }
    if (dep.version) {
      params.put('version', dep.version)
    }
    if (dep.classifier) {
      params.put('classifier', dep.classifier)
    }
    if (dep.type) {
      params.put('type', dep.type)
    }
    if (dep.scope) {
      params.put('scope', dep.scope)
    }
    return params
  }

  static void validatePomContent(String content, String schemaLocation) {
    try {
      def xsdLocation = new URI(schemaLocation).toURL()

      SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI)
          .newSchema(xsdLocation)
          .newValidator()
          .validate(new StreamSource(new StringReader(content)))
    } catch (Exception e) {
      throw new BuildException("Failed to validate pom: ${e.message}", e)
    }
  }

  static String getSchemaLocation() {
    'https://maven.apache.org/xsd/maven-4.0.0.xsd'
  }
}
