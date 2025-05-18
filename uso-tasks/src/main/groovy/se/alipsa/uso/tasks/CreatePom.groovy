package se.alipsa.uso.tasks

import groovy.xml.XmlNodePrinter
import groovy.xml.XmlParser
import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.apache.maven.resolver.internal.ant.types.Dependency
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import se.alipsa.uso.model.MavenProject
import se.alipsa.uso.types.DependencyManagement

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

/**
 * Creates a Maven pom file with the specified dependencies and dependency management.
 * The pom file is created in the specified target directory. This is useful for dependecy resolving in ant but
 * if you want to publish then artifact with the pom, you should consider using the CreatePom task instead since that
 * will enable you to add license, developer meta-data etc.
 *
 * @since 0.0.1
 */
class CreatePom extends Task {

  private String dependenciesRef
  private String dependencyManagementRef
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

  void setDependencyManagementRef(String dependencyManagementRef) {
    this.dependencyManagementRef = dependencyManagementRef
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

  String getName() {
    return name
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
    log("createPom: groupId: ${getGroupId()} artifactId: ${getArtifactId()} version: ${getVersion()}", Project.MSG_VERBOSE)

    if(!pomFile.getParentFile().exists()) {
      pomFile.getParentFile().mkdirs()
    }
    MavenProject pom = new MavenProject()
    pom.setGroupId(getGroupId())
    pom.setArtifactId(getArtifactId())
    pom.setVersion(getVersion())
    pom.setName(getName())
    pom.setDescription(getDescription())

    if (dependencyManagementRef != null) {
      def dependencyManagement = DependencyManagement.get(project, dependencyManagementRef)
      appendManagedDependencies(dependencyManagement.getDependencies(), pom)
    }
    def dependencies = new Dependencies()
    dependencies.setProject(project)
    def ref = new org.apache.tools.ant.types.Reference(project, dependenciesRef)
    dependencies.setRefid(ref)

    appendDependencies(dependencies, pom)

    try (FileWriter fw = new FileWriter(pomFile)) {
      pom.toPom(fw)
    } catch (IOException e) {
      throw new BuildException("Failed to create pom file: ${e.message}", e)
    }
    log("Created pom file ${pomFile.canonicalPath}", Project.MSG_VERBOSE)
  }

  static void appendDependencies(Dependencies dependencies, MavenProject pom) {
    dependencies.getDependencyContainers().each { container ->
      if (container instanceof Dependency) {
        def dep = (Dependency) container
        //echo "Dependency: $dep.groupId:$dep.artifactId:$dep.version:$dep.type:${dep.classifier ?: ''}:$dep.scope"
        pom.addDependency(toMap(dep))
      }
    }
  }

  static void appendManagedDependencies(Dependencies dependencies, MavenProject mavenProject) {
    dependencies.getDependencyContainers().each {
      if (it instanceof Dependency) {
        def dep = (Dependency) it
        //echo "Dependency: $dep.groupId:$dep.artifactId:$dep.version:$dep.type:${dep.classifier ?: ''}:$dep.scope"
        mavenProject.addToDependencyManagement(toMap(dep))
      }
    }
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
}
