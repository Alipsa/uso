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
  String lastInvokedTaskName = null

  ClosureTask() {
    super()
  }

  ClosureTask(Target owner, Closure action) {
    this()
    this.owner = owner
    this.project = owner.project
    this.action = action
  }

  @Override
  void execute() {
    try {
      def interceptor = new TaskInterceptor(this)
      action.delegate = interceptor
      action.resolveStrategy = Closure.DELEGATE_FIRST
      action.call()
    } catch (Exception e) {
      if (e.message.contains("No such property:")) {
        // Try to get the actual line from the build script
        def stackTrace = e.getStackTrace()
        def scriptElement = stackTrace.find {
          it.fileName?.endsWith("build.groovy") ||
              it.className.contains("Script")
        }

        if (scriptElement) {
          throw new BuildException(
              "Error in ${lastInvokedTaskName ?: 'unknown'} task at line ${scriptElement.lineNumber}: ${e.message}",
              e
          )
        }
      }

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

  @Override
  String getTaskName() {
    return lastInvokedTaskName ?: super.getTaskName()
  }

  class TaskInterceptor {
    ClosureTask task
    Target target

    TaskInterceptor(ClosureTask task) {
      this.task = task
      this.target = task.owningTarget
    }

    def methodMissing(String name, args) {
      // Record the task name for better error reporting
      task.lastInvokedTaskName = name

      // Record the line number where this method was called
      def stackTrace = new Exception().getStackTrace()

      // Look specifically for the build script in the stack trace
      def callerElement = stackTrace.find {
        it.fileName?.endsWith(task.project.name) ||
            it.fileName?.endsWith("build.groovy") ||
            it.className.contains("Script")
      }

      if (callerElement) {
        task.taskLineNumbers[name] = callerElement.lineNumber
        // Debug output to verify we're getting the right line
        //println "Debug: Task ${name} called at line ${callerElement.lineNumber} in ${callerElement.fileName}"
      } else {
        // If we can't find a direct reference, search more broadly
        callerElement = stackTrace.find {
          !it.className.contains("AntTargetBuilder") &&
              !it.className.contains("ClosureTask") &&
              !it.className.contains("java.lang.reflect") &&
              !it.className.contains("groovy.lang")
        }

        if (callerElement) {
          task.taskLineNumbers[name] = callerElement.lineNumber
          //println "Debug: Task ${name} called at line ${callerElement.lineNumber} in ${callerElement.fileName} (fallback)"
        }
      }

      // Forward the call to the project directly since target might be null
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

  Map<String, Integer> getTaskLineNumbers() {
    return taskLineNumbers
  }
}