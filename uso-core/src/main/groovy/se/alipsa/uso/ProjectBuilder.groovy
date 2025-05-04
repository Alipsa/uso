package se.alipsa.uso

import groovy.ant.AntBuilder

/**
 * An extension of AntBuilder that allows for the creation of Ant targets using Groovy closures.
 * This class is used to build and execute Ant targets in a Groovy-based build system.
 * A target will only be executed once, even if it is called (through dependant tasks) multiple times.
 * The targets created are not real Ant targets but an inner class of this Class.
 */
class ProjectBuilder extends AntBuilder {
  String groupId
  String artifactId
  String version
  String name
  String defaultTarget
  String baseDir

  Map<String, Target> targets = [:]
  List<String> executedTargets = []

  ProjectBuilder() {
    super()
    this.baseDir = System.getProperty("user.dir")
    taskdef(name: 'groovyc', classname: 'org.codehaus.groovy.ant.Groovyc')
  }

  ProjectBuilder target(Map params, Closure closure) {
    String name = params.name
    String depends = params.depends
    targets[name] = new Target(name: name, depends: depends, closure: closure)
    this
  }

  ProjectBuilder target(String name, Closure closure) {
    targets[name] = new Target(name: name, closure: closure)
    this
  }

  ProjectBuilder target(String name, String depends, Closure closure) {
    targets[name] = new Target(name: name, depends: depends, closure: closure)
    this
  }

  def execute(String targetName) {
    try {
      def target = targets[targetName]
      if (target.depends != null) {
        execute(target.depends)
      }
      if (!executedTargets.contains(targetName)) {
        println "\n$targetName:"
        target.execute()
        executedTargets << targetName
      }
    } catch (Exception e) {
      String errorMessage = e.message
          .replace(" class: se.alipsa.muskrat.ProjectBuilder", " script $name")
          .replace("se.alipsa.muskrat.ProjectBuilder", name)
      System.err.println "Error executing target '$targetName': ${errorMessage}"
      System.exit(1)
    }
  }

  static class Target {
    String name
    String depends
    Closure closure

    def execute() {
      closure.call()
    }
  }

  String getName() {
    return name
  }

  void setName(String name) {
    this.name = name
  }

  String getDefaultTarget() {
    return defaultTarget
  }

  void setDefaultTarget(String defaultTarget) {
    this.defaultTarget = defaultTarget
  }

  String getBaseDir() {
    return baseDir
  }

  void setBaseDir(String baseDir) {
    this.baseDir = baseDir
  }

  Map<String, Target> getTargets() {
    return targets
  }

  String getGroupId() {
    return groupId
  }

  void setGroupId(String groupId) {
    this.groupId = groupId
  }

  String getArtifactId() {
    return artifactId
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
  }

  String getVersion() {
    return version
  }

  void setVersion(String version) {
    this.version = version
  }
}
