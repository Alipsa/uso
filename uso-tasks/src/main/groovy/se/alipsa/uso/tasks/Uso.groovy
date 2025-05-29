package se.alipsa.uso.tasks

import org.apache.tools.ant.Task

/**
 * The Uso target is used to run a build script in another project,
 * similar to the Ant task `<ant dir="..." antfile="..." target="..."/>`.
 */
class Uso extends Task {

  File dir
  String target
  String unless

  void setDir(File dir) {
    this.dir = dir
  }

  void setTarget(String target) {
    this.target = target
  }

  void setUnless(String unless) {
    this.unless = unless
  }

  void execute() {
    if (project.getProperty(unless)) {
      return
    }
    File file = new File(dir, 'uso')
    if (!file.exists()) {
      throw new IllegalArgumentException("File does not exist: ${file.canonicalPath}")
    }
    /*
    def runCommand = { strList ->
      assert ( strList instanceof String ||
          ( strList instanceof List && strList.each{ it instanceof String } )
      )
      def proc = strList.execute()
      proc.in.eachLine { line -> println line }
      proc.out.close()
      proc.waitFor()

      print "[INFO] ( "
      if(strList instanceof List) {
        strList.each { print "${it} " }
      } else {
        print strList
      }
      println " )"

      if (proc.exitValue()) {
        println "gave the following error: "
        println "[ERROR] ${proc.getErrorStream()}"
      }
      assert !proc.exitValue()
    }
    def cmd = "${dir.canonicalPath}/uso $target".toString()
    log("Running command: ${cmd}", project.MSG_INFO)
    runCommand(cmd)
    */
    GroovyScriptEngine scriptEngine = new GroovyScriptEngine(dir.toURI().toURL())
    Binding binding = new Binding()
    binding.setVariable("args", [target] as String[])
    log("Running script: ${file.canonicalPath} with target: ${target}", project.MSG_INFO)
    scriptEngine.run('uso', binding)
  }
}
