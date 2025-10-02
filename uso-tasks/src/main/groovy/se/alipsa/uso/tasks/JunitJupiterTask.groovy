package se.alipsa.uso.tasks

import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Task
import org.apache.tools.ant.taskdefs.Java
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference
import org.junit.platform.console.ConsoleLauncher

/**
 * <h3>Usage example</h3>
 * <code><pre>
 * def ant = new AntBuilder()
 * ant.taskdef(
 *    name: 'junitJupiter',
 *    classname: 'se.alipsa.uso.tasks.JunitJupiterTask',
 *    classpath: 'build/classes:lib/junit-platform-console-standalone-1.11.3.jar'
 *    )
 *
 * // Nested classpath usage
 * ant.junitConsoleLauncher(testclassesdir: "build/test-classes", fork: true, failonerror: true) {
 *    classpath {
 *      pathelement(location: "build/classes")
 *      pathelement(location: "build/test-classes")
 *      fileset(dir: "lib") {
 *        include(name: "&ast;&ast;/&ast;.jar")
 *      }
 *    }
 * }
 *
 * // Or reference usage
 * ant.path(id: "test.classpath") {
 *  pathelement(location: "build/classes")
 *  pathelement(location: "build/test-classes")
 *  fileset(dir: "lib") {
 *    include(name: "&ast;&ast;/&ast;.jar")
 *  }
 * }
 * ant.junitConsoleLauncher(classpathref: "test.classpath", testclassesdir: "build/test-classes")
 * </pre></code>
 */
class JunitJupiterTask extends Task {

  private Path classpath
  private String testclassesdir
  private boolean fork = true
  private boolean failonerror = true
  private boolean failFast = false

  // ----- classpath setters -----
  void setClasspath(Path cp) {
    if (this.classpath == null) {
      this.classpath = cp
    } else {
      this.classpath.append(cp)
    }
  }

  Path createClasspath() {
    if (this.classpath == null) {
      this.classpath = new Path(project)
    }
    return this.classpath.createPath()
  }

  void setClasspathRef(Reference ref) {
    if (this.classpath == null) {
      this.classpath = new Path(project)
    }
    this.classpath.setRefid(ref)
  }

  // ----- other attributes -----
  void setTestclassesdir(String dir) {
    this.testclassesdir = dir
  }

  void setFork(boolean fork) {
    this.fork = fork
  }

  void setFailFast(boolean failFast) {
    this.failFast = failFast
  }

  void setFailonerror(boolean failonerror) {
    this.failonerror = failonerror
  }

  @Override
  void execute() throws BuildException {
    if (classpath == null) {
      throw new BuildException("You must supply a classpath (nested or refid).")
    }
    if (testclassesdir == null) {
      throw new BuildException("You must supply testclassesdir.")
    }

    if (!fork) {
      def args = [
          "--scan-classpath", testclassesdir
          //, "--reports-dir=build/test-results"
      ]
      if (failFast) {
        args << "--fail-fast"
      }

      log("Running JUnit 5 ConsoleLauncher with args: ${String.join(' ', args)}")
      int exitCode = ConsoleLauncher.execute(System.out, System.err, args as String[]).exitCode

      if (exitCode != 0 && failonerror) {
        throw new BuildException("JUnit tests failed with exit code $exitCode")
      }
    } else {
      log("Forking JVM to run JUnit 5 ConsoleLauncher")
      def java = project.createTask("java") as Java
      java(classname:"org.junit.platform.console.ConsoleLauncher",
          fork: true, failonerror: true,
          classpathRef: classpath.getRefid()) {

        arg value: "--scan-class-path"
        arg value: testclassesdir
      }
      java.execute()
    }
  }
}
