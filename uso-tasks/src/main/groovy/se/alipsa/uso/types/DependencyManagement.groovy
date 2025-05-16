package se.alipsa.uso.types

import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.apache.tools.ant.Project
import org.apache.tools.ant.types.DataType

class DependencyManagement extends DataType {
  Dependencies dependencies

  static DependencyManagement get(Project project, String dependencyManagementRef) {
    if (dependencyManagementRef == null) {
      throw new IllegalArgumentException("dependencyManagementRef must not be null")
    }
    if (project == null) {
      throw new IllegalArgumentException("Project must not be null")
    }
    DependencyManagement dependencyManagement = new DependencyManagement()
    dependencyManagement.setProject(project)
    def ref = new org.apache.tools.ant.types.Reference(project, dependencyManagementRef)
    dependencyManagement.setRefid(ref)
    return dependencyManagement.getRef()
  }
  Dependencies getDependencies() {
    return dependencies
  }

  void addDependencies(Dependencies dependencies) {
    if (this.dependencies != null) {
      throw new IllegalStateException("You must not specify multiple <dependencies> elements");
    }
    println "Adding dependencies to DependencyManagement: ${dependencies}"
    this.dependencies = dependencies
  }

  DependencyManagement getRef() {
    return getCheckedRef(DependencyManagement.class);
  }
}
