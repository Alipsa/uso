package se.alipsa.uso

import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Target
import org.apache.tools.ant.Task

/**
 * A task that executes a Groovy closure. This class is used to create Ant tasks that can be
 * executed as part of an Ant build process.
 * The closure is executed with the task as its delegate, allowing for the use of Ant properties
 * and methods within the closure.
 */
class ClosureTask extends Task {
  Target owner
  Closure action
  Map<String, Integer> taskLineNumbers = [:]

  ClosureTask() {
    super()
  }

  ClosureTask(Target owner, Closure action) {
    this()
    this.owner = owner
    this.project = owner.project
    this.action = action
    // Try to capture line numbers of method calls within the closure
    try {
      // Get the source code of the closure
      def stackTrace = new Exception().getStackTrace()
      def callerElement = stackTrace.find { it.className.contains('AntTargetBuilder') }
      if (callerElement) {
        println "Debug: Closure defined at ${callerElement.fileName}:${callerElement.lineNumber}"
      }
    } catch (Exception ignored) {
      // Ignore if we can't get this information
    }
  }

  @Override
  void execute() {
    try {
      def interceptor = new TaskInterceptor(this)
      action.delegate = interceptor
      action.resolveStrategy = Closure.DELEGATE_FIRST
      action.call()
    } catch (Exception e) {
      throw new BuildException(e.message, e)
    }
  }

  void setOwningTarget(Target target) {
    this.owner = target
    setProject(owner.project)
  }

  void setAction(Closure action) {
    this.action = action
  }

  class TaskInterceptor {
    ClosureTask task
    Target target

    TaskInterceptor(ClosureTask task) {
      this.task = task
      this.target = task.owningTarget
    }

    // Handle method calls that aren't explicitly defined
    def methodMissing(String name, args) {
      // Record the line number where this method was called
      def stackTrace = new Exception().getStackTrace()
      def callerElement = stackTrace.find {
        it.className.contains('Script') || it.fileName?.endsWith('.groovy')
      }

      if (callerElement) {
        task.taskLineNumbers[name] = callerElement.lineNumber
        println "Debug: Task ${name} called at line ${callerElement.lineNumber}"
      }

      // Forward the call to the project directly since target might be null
      // This is safer than checking target.metaClass
      if (task.project != null) {
        return task.project.invokeMethod(name, args)
      } else {
        throw new BuildException("Cannot execute task: project is null")
      }
    }

    // Forward property access to the project
    def propertyMissing(String name) {
      if (task.project != null && task.project.hasProperty(name)) {
        return task.project[name]
      }
      throw new MissingPropertyException(name, this.class)
    }

    // Forward property setting to the project
    def propertyMissing(String name, value) {
      if (task.project != null && task.project.hasProperty(name)) {
        task.project[name] = value
      } else {
        throw new MissingPropertyException(name, this.class)
      }
    }
  }
}