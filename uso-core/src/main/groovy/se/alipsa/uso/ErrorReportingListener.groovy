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

      if (location) {
        println " !! Location: ${location}"
      }

      // Try to extract target information
      if (event.task.owningTarget) {
        println " !! In target: ${event.task.owningTarget.name}"
      }

      // For Ant task failures, try to find the task in the build script
      if (event.task instanceof org.apache.tools.ant.Task) {
        try {
          File buildFile = new File(buildScript)
          if (buildFile.exists()) {
            List<String> lines = buildFile.readLines()
            // Look for lines containing the task name and the error message keywords
            String errorMsg = event.exception.message
            String searchKey = taskName.toLowerCase()

            // Extract key words from the error message
            List<String> keywords = errorMsg.split(" ")
                .findAll { it.length() > 3 }
                .collect { it.replaceAll(/[^a-zA-Z0-9]/, "") }
                .findAll { it.length() > 3 }

            // Find potential lines that might contain the task call
            List<Integer> potentialLines = []
            for (int i = 0; i < lines.size(); i++) {
              String line = lines[i].toLowerCase()
              if (line.contains(searchKey)) {
                // Check if any keywords from the error are in this line
                if (keywords.any { line.contains(it.toLowerCase()) }) {
                  potentialLines.add(i)
                } else {
                  // Also add lines that just contain the task name
                  potentialLines.add(i)
                }
              }
            }

            if (potentialLines) {
              println "\nPotential error locations in ${buildScript}:"
              potentialLines.each { lineNum ->
                println " >> Line ${lineNum + 1}: ${lines[lineNum]}"
              }
              println ""
            }
          }
        } catch (Exception ignored) {
          // If we can't read the file or process it, just skip this enhancement
        }
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
