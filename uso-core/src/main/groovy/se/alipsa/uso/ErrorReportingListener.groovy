package se.alipsa.uso

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener

class ErrorReportingListener implements BuildListener {

  File buildScript

  ErrorReportingListener(File buildScript) {
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
      println " !! Debug: Task class = ${event.task.getClass().getName()}"

      if (location) {
        println " !! Location: ${location}"
      }

      // Try to extract target information
      if (event.task.owningTarget) {
        println " !! In target: ${event.task.owningTarget.name}"
      } else {
        println " !! Debug: No owning target found"
      }

      // For Ant task failures, try to find the task in the build script
      println " !! Debug: buildScript = ${buildScript}"

      try {
        println " !! Debug: Build file exists: ${buildScript.exists()}"

        if (buildScript.exists()) {
          List<String> lines = buildScript.readLines()
          println " !! Debug: Read ${lines.size()} lines from build file"

          // Look for lines containing the task name
          String searchKey = taskName.toLowerCase()
          println " !! Debug: Searching for task name: ${searchKey}"

          // Extract key words from the error message
          String errorMsg = event.exception.message
          List<String> keywords = errorMsg.split(" ")
              .findAll { it.length() > 3 }
              .collect { it.replaceAll(/[^a-zA-Z0-9]/, "") }
              .findAll { it.length() > 3 }

          println " !! Debug: Keywords from error: ${keywords}"

          // Find potential lines that might contain the task call
          for (int i = 0; i < lines.size(); i++) {
            String line = lines[i].toLowerCase()
            if (line.contains(searchKey)) {
              println " !! Debug: Found task name at line ${i+1}: ${lines[i]}"

              // Check if any keywords from the error are in this line
              if (keywords.any { line.contains(it.toLowerCase()) }) {
                println " !! Debug: Line also contains error keywords"
                println " >> Line ${i + 1}: ${lines[i]}"
              }
            }
          }
        }
      } catch (Exception e) {
        println " !! Debug: Exception while analyzing build file: ${e.message}"
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
