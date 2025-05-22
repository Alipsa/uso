package se.alipsa.uso

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener

class ErrorReportingListener implements BuildListener {
  private Set<String> reportedErrors = new HashSet<>()
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
      String errorMsg = event.exception.message

      // Create a unique key for this error to avoid duplicates
      String errorKey = "${taskName}:${errorMsg}"

      // Skip if we've already reported this error
      if (reportedErrors.contains(errorKey)) {
        return
      }
      reportedErrors.add(errorKey)

      // Only show this initial error message if we can't provide better context
      boolean willShowContext = buildScript.exists()


      if (!willShowContext) {
        println " !! Task ${taskName} failed: ${errorMsg.replace('se.alipsa.uso.ClosureTask.', '')}"
      } else {
        // Just show a header without the full error message
        println " !! Task ${taskName} failed:"
      }

      // For property errors, try to find the property name in the build script
      if (errorMsg.contains("No such property:")) {
        String propertyName = errorMsg.replaceAll(".*No such property: ([^ ]+).*", "\$1")
        try {

          if (buildScript.exists()) {
            List<String> lines = buildScript.readLines()

            // Look for the property name in the build script
            for (int i = 0; i < lines.size(); i++) {
              if (lines[i].contains(propertyName)) {
                println "\nProblem identified in ${buildScript.name}:"

                // Show context around the line with the property
                int start = Math.max(0, i - 2)
                int end = Math.min(lines.size() - 1, i + 2)

                for (int j = start; j <= end; j++) {
                  String prefix = (j == i) ? " >> " : "    "
                  println "${prefix}Line ${j + 1}: ${lines[j]}"
                }

                // Don't continue with the regular error reporting
                return
              }
            }
          }
        } catch (Exception e) {
          // Fall back to regular error reporting
          println " !! Exception while analyzing build file: ${e.message}"
        }
      }

      // Get the task line number from the ClosureTask if available
      Integer lineNumber = null
      if (event.task instanceof ClosureTask) {
        lineNumber = event.task.taskLineNumbers.get(taskName)
        if (lineNumber != null) {
          println " !! Error at line ${lineNumber} in ${buildScript instanceof File ? buildScript.name : buildScript}"

          // Show source context
          try {

            if (buildScript.exists()) {
              List<String> lines = buildScript.readLines()
              int start = Math.max(0, lineNumber - 3)
              int end = Math.min(lines.size() - 1, lineNumber + 2)

              println "\nSource context:"
              for (int i = start; i <= end; i++) {
                String prefix = (i == lineNumber - 1) ? " >> " : "    "
                println "${prefix}Line ${i + 1}: ${lines[i]}"
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

            // Find the most relevant line - prioritize lines that contain both the task name and error keywords
            List<Integer> relevantLines = []

            // Look for task invocation patterns
            for (int i = 0; i < lines.size(); i++) {
              String line = lines[i].trim()

              // Look for patterns like "taskname(" which indicates a task invocation
              if (line.matches("(?i)\\s*${taskName}\\s*\\(.*")) {
                relevantLines.add(i)

                // Now look for the attribute mentioned in the error
                // For example, if error mentions "srcdir", look for that in subsequent lines
                String attributePattern = errorMsg.find(/\w+dir|\w+file|\w+path/) ?: "srcdir"

                // Check the next few lines for the attribute
                for (int j = i + 1; j < Math.min(i + 10, lines.size()); j++) {
                  String nextLine = lines[j].trim()
                  if (nextLine.contains(attributePattern)) {
                    // This is likely the actual problematic line
                    relevantLines.add(0, j) // Add at the beginning to prioritize
                    break
                  }
                  // Stop if we reach the end of the task definition
                  if (nextLine.contains(")") && !nextLine.contains("(")) {
                    break
                  }
                }
              }
            }

            if (relevantLines) {
              println "\nProblem identified in ${buildScript.name}:"

              // Show the most relevant line first (should be the one with the attribute)
              int mostRelevantLine = relevantLines.get(0)

              // Show context around the most relevant line in proper order
              int start = Math.max(0, mostRelevantLine - 2)
              int end = Math.min(lines.size() - 1, mostRelevantLine + 2)

              // Display all lines in order, but highlight the problematic one
              for (int i = start; i <= end; i++) {
                String prefix = (i == mostRelevantLine) ? " >> " : "    "
                println "${prefix}Line ${i + 1}: ${lines[i]}"
              }

              // Only show error details if they add information beyond what's already shown
              if (!errorMsg.contains("does not exist") || !errorMsg.startsWith("srcdir")) {
                println "\nError details: ${errorMsg}"
              }
            }
          }
        } catch (Exception e) {
          println " !! Exception while analyzing build file: ${e.message}"
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
