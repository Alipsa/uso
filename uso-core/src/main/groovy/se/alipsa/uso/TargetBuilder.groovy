package se.alipsa.uso

import groovy.ant.AntBuilder
import org.apache.tools.ant.Target

/**
 * An extension of AntBuilder that allows for the creation of Ant targets using Groovy closures.
 * This class is used to build and execute Ant targets in a Groovy-based build system.
 * All targets in the dependency chain will be executed for each target specified.
 * The targets created are real Ant targets.
 */
class TargetBuilder extends AntBuilder {
  String groupId
  String artifactId
  String version
  String defaultTarget
  String baseDir

  TargetBuilder() {
    super()
    this.baseDir = System.getProperty("user.dir")
    taskdef(name: 'groovyc', classname: 'org.codehaus.groovy.ant.Groovyc')
  }

  Target target(Map<String, String> params, Closure closure) {
    Target antTarget = new Target()
    params.each {
      if (antTarget.hasProperty(it.key)) {
        antTarget[it.key] = it.value
      } else {
        project.log("Unknown property '${it.key}' for target '${params.name}'", 1)
      }
    }
    antTarget.project = project
    antTarget.addTask(new ClosureTask(antTarget, closure))
    project.addTarget(antTarget)
    antTarget
  }

  Target target(String name, Closure closure) {
    target(name: name, closure)
  }

  Target target(String name, String depends, Closure closure) {
    target(name: name, depends: depends, closure)
  }

  def execute() {
    if (defaultTarget == null) {
      throw new IllegalArgumentException("No target specified and no default target set.")
    }
    execute([defaultTarget])
  }

  def execute(String targetName) {
    execute([targetName])
  }

  def execute(List<String> targets) {
    try {
      if (targets.size() == 0) {
        if (defaultTarget == null) {
          throw new IllegalArgumentException("No target specified and no default target set.")
        }
        project.executeTarget(defaultTarget)
      }
      if (targets.size() == 1) {
        project.executeTarget(targets[0])
      } else {
        project.executeTargets(targets as Vector)
      }
    } catch (Exception e) {
      String errorMessage = e.message
          .replace(" class: se.alipsa.muskrat.TargetBuilder", " script $project.name")
          .replace("se.alipsa.muskrat.TargetBuilder", project.name)
      System.err.println "Error executing target(s) '$targets': ${errorMessage}"
      System.exit(1)
    }
  }

  Map<String, Target> getTargets() {
    Map<String, Target> targetMap = [:]
    project.getTargets().each { k, v ->
      targetMap.put(String.valueOf(k),  v as Target)
    }
    targetMap
  }

  String getDefaultTarget() {
    return defaultTarget
  }

  void setDefaultTarget(String defaultTarget) {
    this.defaultTarget = defaultTarget
    project.setDefault(defaultTarget)
  }

  String getBaseDir() {
    return baseDir
  }

  void setBaseDir(String baseDir) {
    this.baseDir = baseDir
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
    project.name = artifactId
  }

  String getVersion() {
    return version
  }

  void setVersion(String version) {
    this.version = version
  }
}
