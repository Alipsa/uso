package se.alipsa.uso

/**
 * An extension of AntBuilder that allows for the creation of Ant targets using Groovy closures.
 * This class is used to build and execute Ant targets in a Groovy-based build system.
 * A target will only be executed once, even if it is called (through dependant tasks) multiple times.
 * The targets created are not real Ant targets but an inner class of this Class.
 */
class TargetOnceProjectBuilder extends ProjectBuilder {


  Map<String, Target> targets = [:]
  List<String> executedTargets = []

  void target(Map<String, String> params, Closure closure) {
    String name = params.name
    String depends = params.depends
    targets[name] = new Target(name: name, depends: depends, closure: closure)
  }

  void target(String name, Closure closure) {
    targets[name] = new Target(name: name, closure: closure)
  }

  void target(String name, String depends, Closure closure) {
    targets[name] = new Target(name: name, depends: depends, closure: closure)
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
          .replace(" class: se.alipsa.uso.TargetOnceProjectBuilder", " script $name")
          .replace("se.alipsa.uso.TargetOnceProjectBuilder", name)
      System.err.println "Error executing target '$targetName': ${errorMessage}"
      System.exit(1)
    }
  }

  def execute(List<String> targetsToExecute) {
    targetsToExecute.each { targetName ->
      execute(targetName)
    }
  }

  Map<String, Target> getTargets() {
    return targets
  }

  static class Target {
    String name
    String depends
    Closure closure

    def execute() {
      closure.call()
    }
  }
}
