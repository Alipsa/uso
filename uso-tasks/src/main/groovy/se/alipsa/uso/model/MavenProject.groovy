package se.alipsa.uso.model

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.Marshaller

class MavenProject {
  private Model model = new Model()

  MavenProject() {
    model.modelVersion = '4.0.0'
    model.packaging = 'jar'
  }

  void addLicense(String name, String url) {
    def licenses = model.getLicenses()
    if (licenses == null) {
      licenses = new Model.Licenses()
      model.setLicenses(licenses)
    }
    def license = new License()
    license.name = name
    license.url = url
    licenses.license << license
  }

  void addToDependencyManagement(Map dependencyProps) {
    def dependencyManagement = model.getDependencyManagement()
    if (dependencyManagement == null) {
      dependencyManagement = new DependencyManagement()
      model.setDependencyManagement(dependencyManagement)
    }
    def dependencies = dependencyManagement.getDependencies()
    if (dependencies == null) {
      dependencies = new DependencyManagement.Dependencies()
      dependencyManagement.setDependencies(dependencies)
    }
    def dependency = new Dependency(dependencyProps)
    dependencies.dependency << dependency
  }


  void toPom(Writer writer) {
    JAXBContext context = JAXBContext.newInstance(Model.class)
    Marshaller mar= context.createMarshaller()
    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)
    mar.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://maven.apache.org/POM/4.0.0 ${getSchemaLocation()}".toString())
    mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE)
    mar.marshal(model, writer)
  }

  void setGroupId(String groupId) {
    model.setGroupId(groupId)
  }
  void setArtifactId(String artifactId) {
    model.setArtifactId(artifactId)
  }
  void setVersion(String version) {
    model.setVersion(version)
  }
  void setName(String name) {
    model.setName(name)
  }
  void setDescription(String description) {
    model.setDescription(description)
  }

  void addDependency(Map<String, String> dependencyProps) {
    def dependencies = model.getDependencies()
    if (dependencies == null) {
      dependencies = new Model.Dependencies()
      model.setDependencies(dependencies)
    }
    def dependency = new Dependency(dependencyProps)
    dependencies.dependency << dependency

  }

  Model getModel() {
    return model
  }

  static String getSchemaLocation() {
    'https://maven.apache.org/xsd/maven-4.0.0.xsd'
  }
}
