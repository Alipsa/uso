package se.alipsa.uso

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener
import org.apache.tools.ant.UnknownElement

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

      println " !! Task ${taskName} failed: ${event.exception.message.replace('se.alipsa.uso.ClosureTask.', '')}"

      // Get the task line number from the ClosureTask if available
      Integer lineNumber = null
      if (event.task instanceof ClosureTask) {
        lineNumber = event.task.taskLineNumbers.get(taskName)
        if (lineNumber != null) {
          println " !! Error at line ${lineNumber} in ${buildScript}"

          // Show source context
          try {
            if (buildScript.exists()) {
              List<String> lines = buildScript.readLines()
              int start = Math.max(0, lineNumber - 3)
              int end = Math.min(lines.size() - 1, lineNumber + 2)

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

      // If we couldn't get the line number directly, try to find it in the build file
      if (lineNumber == null) {
        try {
          if (buildScript.exists()) {
            List<String> lines = buildScript.readLines()

            // Extract key words from the error message
            String errorMsg = event.exception.message
            List<String> keywords = errorMsg.split(" ")
                .findAll { it.length() > 3 }
                .collect { it.replaceAll(/[^a-zA-Z0-9]/, "") }
                .findAll { it.length() > 3 }

            // Find the most relevant line - prioritize lines that contain both the task name and error keywords
            List<Integer> relevantLines = []

            // First, check if we have a record of where this task was called
            if (event.task instanceof UnknownElement && event.task.getTaskName() == taskName) {
              // This is the actual task that failed, look for its definition
              for (int i = 0; i < lines.size(); i++) {
                String line = lines[i].toLowerCase()
                if (line.contains(taskName.toLowerCase())) {
                  // Check if any keywords from the error are in this line or nearby lines
                  boolean hasKeywords = keywords.any { line.contains(it.toLowerCase()) }

                  // Check nearby lines for srcdir or other relevant attributes
                  boolean hasNearbyKeywords = false
                  for (int j = i; j < Math.min(i + 5, lines.size()); j++) {
                    String nearbyLine = lines[j].toLowerCase()
                    if (keywords.any { nearbyLine.contains(it.toLowerCase()) }) {
                      hasNearbyKeywords = true
                      break
                    }
                  }
                  if (hasKeywords || hasNearbyKeywords) {
                    relevantLines.add(i)
                  }
                }
              }
            }

            if (relevantLines) {
              println "\nPotential error locations:"
              for (Integer i : relevantLines) {
                println " >> Line ${i + 1}: ${lines[i]}"

                // Show a few lines after for context (especially for multi-line task definitions)
                for (int j = i + 1; j < Math.min(i + 5, lines.size()); j++) {
                  if (lines[j].trim().startsWith(")")) break; // Stop at closing parenthesis
                  println "    Line ${j + 1}: ${lines[j]}"
                }
              }
              println ""
            }
          }
        } catch (Exception e) {
          println " !! Debug: Exception while analyzing build file: ${e.message}"
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
