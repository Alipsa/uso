package se.alipsa.uso

import org.apache.tools.ant.Target

/**
 * An extension of AntBuilder that allows for the creation of Ant targets using Groovy closures.
 * This class is used to build and execute Ant targets in a Groovy-based build system.
 * All targets in the dependency chain will be executed for each target specified.
 * The targets created are real Ant targets.
 */
class AntTargetBuilder extends ProjectBuilder {

  AntTargetBuilder() {
    super()
  }

  AntTargetBuilder(String groupId, String artifactId, String version) {
    super()
    this.groupId = groupId
    this.artifactId = artifactId
    this.version = version
  }

  void target(Map<String, String> params, Closure closure) {
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

  void target(String name, Closure closure) {
    target(name: name, closure)
  }

  void target(String name, String depends, Closure closure) {
    target(name: name, depends: depends, closure)
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
          .replace(" class: se.alipsa.uso.AntTargetBuilder", " script $project.name")
          .replace("se.alipsa.uso.AntTargetBuilder", project.name)
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
}
