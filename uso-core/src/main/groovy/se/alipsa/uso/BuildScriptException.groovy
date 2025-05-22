package se.alipsa.uso

class BuildScriptException extends RuntimeException {
  String scriptName
  int lineNumber
  String lineContent

  BuildScriptException(String message, String scriptName, int lineNumber, String lineContent, Throwable cause) {
    super(message, cause)
    this.scriptName = scriptName
    this.lineNumber = lineNumber
    this.lineContent = lineContent
  }

  @Override
  String toString() {
    return "Error in ${scriptName} at line ${lineNumber}: ${message}\n>> ${lineContent}"
  }
}
