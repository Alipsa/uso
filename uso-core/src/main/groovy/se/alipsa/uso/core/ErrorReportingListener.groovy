package se.alipsa.uso.core

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener

class ErrorReportingListener implements BuildListener {

  String buildScript
  Set errorMsgs = new HashSet()

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
    if (event.exception && !errorMsgs.contains(event.exception.message)) {
      println "  !! Task ${event.task.taskName ?: ''} failed: ${event.exception.message.replace('se.alipsa.uso.ClosureTask.', '')}"
      errorMsgs << event.exception.message
    }
  }

  @Override
  void messageLogged(BuildEvent event) {
    // Optionally filter on event.priority
    //println "    [${event.priority}] ${event.message}"
  }

  void printExceptionDetails(Throwable t) {
    def trace = t.stackTrace.find { it.fileName?.contains(buildScript) }
    if (trace) {
      println "  -> Error in ${trace.fileName}, line ${trace.lineNumber}"
    } else {
      println "  -> Error: ${t.class.name}: ${t.message}"
    }
  }
}
