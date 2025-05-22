package se.alipsa.uso

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener

class ErrorReportingListener implements BuildListener {

  String buildScript

  ErrorReportingListener(String buildScript) {
    this.buildScript = buildScript
  }

  @Override
  void buildStarted(BuildEvent event) {}

  @Override
  void buildFinished(BuildEvent event) {
    if (event.exception) {
      println "\nBUILD FAILED: ${event.exception.message}"
      printExceptionDetails(event.exception)
    }
  }

  @Override
  void targetStarted(BuildEvent event) {
    //println "\n>> Executing target: ${event.target.name}"
  }

  @Override
  void targetFinished(BuildEvent event) {
    if (event.exception) {
      println "!! Target ${event.target.name} failed!"
    }
  }

  @Override
  void taskStarted(BuildEvent event) {
    //println "  - Task: ${event.task.taskName}"
  }

  @Override
  void taskFinished(BuildEvent event) {
    if (event.exception) {
      String taskName = event.task.taskName ?: 'unknown'
      String location = event.task.location?.toString() ?: ''

      println " !! Task ${taskName} failed: ${event.exception.message.replace('se.alipsa.uso.ClosureTask.', '')}"

      if (location) {
        println " !! Location: ${location}"
      }

      // Try to extract target information
      if (event.task.owningTarget) {
        println " !! In target: ${event.task.owningTarget.name}"
      }
    }
  }

  @Override
  void messageLogged(BuildEvent event) {
    // Optionally filter on event.priority
    //println "    [${event.priority}] ${event.message}"
  }

  void printExceptionDetails(Throwable t) {
    def traces = t.stackTrace.findAll { it.fileName?.contains(buildScript) }
    if (traces) {
      println " -> Error in ${buildScript}:"
      traces.each { trace ->
        println "    at line ${trace.lineNumber}, in ${trace.methodName}()"
        showSourceContext(buildScript, trace.lineNumber)
      }
      Throwable cause = t.cause
      while (cause != null) {
        println " -> Caused by: ${cause.class.name}: ${cause.message}"
        def causeTraces = cause.stackTrace.findAll { it.fileName?.contains(buildScript) }
        if (causeTraces) {
          causeTraces.each { trace ->
            println "    at line ${trace.lineNumber}, in ${trace.methodName}()"
            showSourceContext(buildScript, trace.lineNumber)
          }
        }
        cause = cause.cause
      }
    } else {
      println " -> Error: ${t.class.name}: ${t.message}"
      if (t.cause) {
        println " -> Caused by: ${t.cause.class.name}: ${t.cause.message}"
      }
    }
  }

  void showSourceContext(String fileName, int lineNumber) {
    try {
      File file = new File(fileName)
      if (file.exists()) {
        List<String> lines = file.readLines()
        int start = Math.max(0, lineNumber - 3)
        int end = Math.min(lines.size() - 1, lineNumber + 1)

        println "\nSource context:"
        for (int i = start; i <= end; i++) {
          String prefix = (i == lineNumber - 1) ? " >> " : "    "
          println "${prefix}${i + 1}: ${lines[i]}"
        }
        println ""
      }
    } catch (Exception ignored) {
      // If we can't read the file, just skip the context display
    }
  }
}
