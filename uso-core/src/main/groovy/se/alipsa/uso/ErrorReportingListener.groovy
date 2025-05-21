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
      println "  !! Task ${event.task.taskName ?: ''} failed: ${event.exception.message.replace('se.alipsa.uso.ClosureTask.', '')}"
      def userFrame = event.exception.stackTrace.find {
        it.fileName?.endsWith(".groovy") && it.lineNumber > 0 //.contains(buildScript)
      }
      if (userFrame) {
        println "  -> At: ${userFrame.fileName}, line ${userFrame.lineNumber}"
      }
    }
  }

  @Override
  void messageLogged(BuildEvent event) {
    // Optionally filter on event.priority
    //println "    [${event.priority}] ${event.message}"
  }

  static void printExceptionDetails(Throwable t) {
    def trace = t.stackTrace.find { it.fileName?.contains(buildScript) }
    if (trace) {
      println "  -> Error in ${trace.fileName}, line ${trace.lineNumber}"
    } else {
      println "  -> Error: ${t.class.name}: ${t.message}"
    }
  }
}
