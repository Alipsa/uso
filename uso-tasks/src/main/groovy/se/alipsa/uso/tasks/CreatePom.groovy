package se.alipsa.uso.tasks

import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.apache.maven.resolver.internal.ant.types.Dependency
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import org.w3c.dom.Document

import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class CreatePom extends Task {

  private String dependenciesRef
  private File pomFile
  private String groupId
  private String artifactId
  private String version
  private String name
  private String description

  void setPomTarget(String pomTarget) {
    pomFile = new File(pomTarget)
  }
  void setPomTarget(File pomTarget) {
    pomFile = pomTarget
  }

  void setDependenciesRef(String dependenciesRef) {
    this.dependenciesRef = dependenciesRef
  }

  void setGroupId(String groupId) {
    this.groupId = groupId
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
  }

  void setVersion(String version) {
    this.version = version
  }

  void setName(String name) {
    this.name = name
  }

  void setDescription(String description) {
    this.description = description
  }

  String getGroupId() {
    if (groupId == null || groupId == 'null') {
      return project.getProperty('groupId')
    }
    return groupId
  }

  String getArtifactId() {
    if (artifactId == null || artifactId == 'null') {
      return project.getProperty('artifactId')
    }
    return artifactId
  }

  String getVersion() {
    if (version == null || version == 'null') {
      return project.getProperty('version')
    }
    return version
  }

  String getDescription() {
    return description ?: super.getDescription()
  }


  @Override
  void execute() {
    log("createPom: groupId: ${getGroupId()} artifactId: ${getArtifactId()} version: ${getVersion()}")
    def dependencies = new Dependencies()
    dependencies.setProject(project)
    def ref = new org.apache.tools.ant.types.Reference(project, dependenciesRef)
    dependencies.setRefid(ref)
    StringBuilder deps = new StringBuilder()
    dependencies.getDependencyContainers().each { container ->
      if (container instanceof Dependency) {
        def dep = (Dependency) container
        //echo "Dependency: $dep.groupId:$dep.artifactId:$dep.version:$dep.type:${dep.classifier ?: ''}:$dep.scope"
        deps.append("""
        <dependency>
          <groupId>${dep.groupId}</groupId>
          <artifactId>${dep.artifactId}</artifactId>
          <version>${dep.version}</version>""")
        if (dep.classifier) {
          deps.append("\n          <classifier>${dep.classifier}</classifier>")
        }
        if (dep.type) {
          deps.append("\n          <type>${dep.type}</type>")
        }
        if (dep.scope) {
          deps.append("\n          <scope>${dep.scope}</scope>")
        }
        deps.append("\n        </dependency>")
      }
    }

    String schemaLocation = 'https://maven.apache.org/xsd/maven-4.0.0.xsd'
    pomFile.text = """
    <project xmlns="http://maven.apache.org/POM/4.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 $schemaLocation">
      <modelVersion>4.0.0</modelVersion>
      <groupId>${getGroupId()}</groupId>
      <artifactId>${getArtifactId()}</artifactId>
      <version>${getVersion()}</version>
      <packaging>jar</packaging>
      ${name ? "<name>$name</name>" : ''}
      ${description ? "<description>$description</description>" : ''}
      <dependencies>$deps
      </dependencies>
    </project>
    """.stripIndent()

    try {
      def doc = new XmlSlurper().parse(pomFile)

      def xsdLocation = new URI(schemaLocation).toURL()

      SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI)
          .newSchema(xsdLocation)
          .newValidator()
          .validate(new StreamSource(new StringReader( XmlUtil.serialize(doc))))
    } catch (Exception e) {
      throw new BuildException("Failed to create a valid pom file: ${e.message}", e)
    }
    log("Created pom file ${pomFile.canonicalPath}")
  }
}
