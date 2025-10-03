package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task

/**
 * This task creates some standard layout for a project and defined properties
 * pointing to each directory.
 * <p>
 *   Parameters:
 *   <dl>
 *     <dt>type</dt>
 *     <dd>the type of layout to use (maven, gradle, simple). Default is maven</dd>
 *     <dt>language</dt>
 *     <dd>the language to use, e.g. groovy, java, scala. Default is groovy</dd>
 *     <dt>dir</dt>
 *     <dd>the base directory for the layout. Default is basedir</dd>
 *     <dt>loglevel</dt>
 *     <dd>the log level to use. Default is 2 (Project.MSG_INFO). If you set it to 3 (Project.MSG_VERBOSE) the property
 *     names and their values will be printed (unless you have changed the messageOutPutLevel)</dd>
 *   </dl>
 * </p>
 * <p>
 *   The properties created are:
 *   <dl>
 *     <dt>mainSrcDir</dt>
 *     <dd>the directory for main source code</dd>
 *     <dt>testSrcDir</dt>
 *     <dd>the directory for test source code</dd>
 *     <dt>mainResourcesDir</dt>
 *     <dd>the directory for main resources</dd>
 *     <dt>testResourcesDir</dt>
 *     <dd>the directory for test resources</dd>
 *     <dt>buildDir</dt>
 *     <dd>the directory for build output</dd>
 *     <dt>mainGeneratedDir</dt>
 *     <dd>the directory for main generated sources</dd>
 *     <dt>testGeneratedDir</dt>
 *     <dd>the directory for test generated sources</dd>
 *     <dt>mainClassesDir</dt>
 *     <dd>the directory for main classes</dd>
 *     <dt>testClassesDir</dt>
 *     <dd>the directory for test classes</dd>
 *     <dt>testOutputDir</dt>
 *     <dd>the directory for test output (e.g. surefire reports)</dd>
 *     <dt>testReportDir</dt>
 *     <dd>the directory for test reports</dd>
 *     <dt>mainDocDir</dt>
 *     <dd>the directory for main documentation</dd>
 *     <dt>testDocDir</dt>
 *     <dd>the directory for test documentation</dd>
 *     <dt>distDir</dt>
 *     <dd>the directory for distribution</dd>
 *   </dl>
 *
 */
@CompileStatic
class Layout extends Task {
  private File mainSrcDir
  private File testSrcDir
  private File mainResourcesDir
  private File testResourcesDir
  private File buildDir
  private File mainGeneratedDir
  private File testGeneratedDir
  private File mainClassesDir
  private File testClassesDir
  private File testResultDir
  private File testReportDir
  private File mainDocDir
  private File testDocDir
  private File distDir
  private String type = 'maven'
  private String language = 'groovy'
  private File dir
  private int loglevel = Project.MSG_VERBOSE

  Layout() {
    super()
  }

  String getBasedir() {
    return project.getBaseDir()?.canonicalPath ?: project.getProperty('basedir') ?: '.'
  }

  def mavenLayout() {
    //project.log("Creating maven layout, dir is ${getDir()}")
    mainSrcDir = new File(getDir(), "src/main/$language")
    testSrcDir = new File(getDir(), "src/test/$language")
    mainResourcesDir = new File(getDir(), "src/main/resources")
    testResourcesDir = new File(getDir(), "src/test/resources")
    buildDir = new File(getDir(), "target")
    mainGeneratedDir = new File(buildDir, "generated-sources")
    testGeneratedDir = new File(buildDir, "generated-test-sources")
    mainClassesDir = new File(buildDir, "classes")
    testClassesDir = new File(buildDir, "test-classes")
    testResultDir = new File(buildDir, "surefire-reports")
    testReportDir = new File(buildDir, "site")
    mainDocDir = new File(buildDir, "${language}doc")
    testDocDir = new File(buildDir, "test-${language}doc")
    distDir = buildDir
  }

  def gradleLayout() {
    mainSrcDir = new File(getDir(), "src/main/${language}")
    testSrcDir = new File(getDir(), "src/test/${language}")
    mainResourcesDir = new File(getDir(), "src/main/resources")
    testResourcesDir = new File(getDir(), "src/test/resources")
    buildDir = new File(getDir(), "build")
    mainGeneratedDir = new File(buildDir, "generated/sources/main/${language}")
    testGeneratedDir = new File(buildDir, "generated/sources/test/${language}")
    mainClassesDir = new File(buildDir, "classes/${language}/main")
    testClassesDir = new File(buildDir, "classes/${language}/test")
    testResultDir = new File(buildDir, "test-results/test")
    testReportDir = new File(buildDir, "test-results/report")
    mainDocDir = new File(buildDir, "/docs/${language}doc")
    testDocDir = new File(buildDir, "docs/test-${language}doc")
    distDir = new File(buildDir, "libs")
  }

  def simpleLayout() {
    mainSrcDir = new File(getDir(), "src/code")
    testSrcDir = new File(getDir(), "test/code")
    mainResourcesDir = new File(getDir(), "src/resources")
    testResourcesDir = new File(getDir(), "test/resources")
    buildDir = new File(getDir(), "out")
    mainGeneratedDir = new File(buildDir, "generated-sources")
    testGeneratedDir = new File(buildDir, "generated-test-sources")
    mainClassesDir = new File(buildDir, "classes")
    testClassesDir = new File(buildDir, "test-classes")
    testResultDir = new File(buildDir, "test-results")
    testReportDir = new File(buildDir, "test-reports")
    mainDocDir = new File(buildDir, "${language}doc")
    testDocDir = new File(buildDir, "test-${language}doc")
    distDir = buildDir
  }

  void execute() throws BuildException {
    printSettings()
    if (type == 'maven') {
      mavenLayout()
    } else if (type == 'gradle') {
      gradleLayout()
    } else if (type == 'simple') {
      simpleLayout()
    } else {
      throw new IllegalArgumentException("Unknown layout type: $type")
    }
    boolean result = getDir().mkdirs()
    if (!getDir().exists()) {
      throw new BuildException("Failed to create directory ${getDir()}")
    } else if (result) {
      log("Created directory ${getDir()}")
    } else {
      log("Directory ${getDir()} already exists")
    }
    createDirAndSetProperty('mainSrcDir', mainSrcDir)
    createDirAndSetProperty('testSrcDir', testSrcDir)
    createDirAndSetProperty('mainResourcesDir', mainResourcesDir)
    createDirAndSetProperty('testResourcesDir', testResourcesDir)
    createDirAndSetProperty('buildDir', buildDir)
    createDirAndSetProperty('mainGeneratedDir', mainGeneratedDir)
    createDirAndSetProperty('testGeneratedDir', testGeneratedDir)
    createDirAndSetProperty('mainClassesDir', mainClassesDir)
    createDirAndSetProperty('testClassesDir', testClassesDir)
    createDirAndSetProperty('testResultDir', testResultDir)
    createDirAndSetProperty('testReportDir', testReportDir)
    createDirAndSetProperty('mainDocDir', mainDocDir)
    createDirAndSetProperty('testDocDir', testDocDir)
    createDirAndSetProperty('distDir', distDir)
  }

  def createDirAndSetProperty(String name, File targetDir) {
    //project.log("Layout: Creating directory $targetDir for property $name")
    targetDir.mkdirs()
    project.setProperty(name, targetDir.canonicalPath)
    project.log("property: $name = $targetDir", loglevel)
  }

  void setMainSrcDir(File mainSrcDir) {
    this.mainSrcDir = mainSrcDir
  }

  void setTestSrcDir(File testSrcDir) {
    this.testSrcDir = testSrcDir
  }

  void setMainResourcesDir(File mainResourcesDir) {
    this.mainResourcesDir = mainResourcesDir
  }

  void setTestResourcesDir(File testResourcesDir) {
    this.testResourcesDir = testResourcesDir
  }

  void setTestResultDir(File testResultDir) {
    this.testResultDir= testResultDir
  }

  void setTestReportDir(File testReportDir) {
    this.testReportDir = testReportDir
  }

  void setBuildDir(File buildDir) {
    this.buildDir = buildDir
  }

  void setMainGeneratedDir(File mainGeneratedDir) {
    this.mainGeneratedDir = mainGeneratedDir
  }

  void setTestGeneratedDir(File testGeneratedDir) {
    this.testGeneratedDir = testGeneratedDir
  }

  void setMainClassesDir(File mainClassesDir) {
    this.mainClassesDir = mainClassesDir
  }

  void setTestClassesDir(File testClassesDir) {
    this.testClassesDir = testClassesDir
  }

  void setMainDocDir(File mainDocDir) {
    this.mainDocDir = mainDocDir
  }

  void setTestDocDir(File testDocDir) {
    this.testDocDir = testDocDir
  }

  void setDistDir(File distDir) {
    this.distDir = distDir
  }

  void setType(String type) {
    this.type = type
  }

  void setLanguage(String language) {
    this.language = language
  }

  void setDir(File dir) {
    if (dir == null) return
    log("Setting dir to $dir", loglevel)
    if (project != null)
      project.setProperty('layoutdir', dir.canonicalPath)
    this.dir = dir
  }

  File getDir() {
    def ld = project.getProperty('layoutdir')
    if (ld != null) {
      if (ld.startsWith('/')) {
        return new File(ld)
      } else {
        return new File(getBasedir(), ld)
      }
    }
    return dir ?: new File(getBasedir())
  }

  void setLoglevel(int logLevel) {
    String level = switch (logLevel) {
      case Project.MSG_ERR -> 'ERROR'
      case Project.MSG_WARN -> 'WARN'
      case Project.MSG_INFO -> 'INFO'
      case Project.MSG_VERBOSE -> 'VERBOSE'
      case Project.MSG_DEBUG -> 'DEBUG'
      default -> 'UNKNOWN'
    }
    log("Setting log level to $level ($logLevel)", Project.MSG_WARN)
    this.loglevel = logLevel
  }

  void setLoglevel(String logLevel) {
    int level = switch (logLevel.toUpperCase()) {
      case '0', 'ERROR' -> Project.MSG_ERR
      case '1', 'WARN' -> Project.MSG_WARN
      case '2', 'INFO' -> Project.MSG_INFO
      case '3', 'VERBOSE' -> Project.MSG_VERBOSE
      case '4', 'DEBUG' -> Project.MSG_DEBUG
      default -> Project.MSG_INFO
    }
    log("Setting log level to $level", Project.MSG_WARN)
    this.loglevel = level
  }

  void printSettings() {
    println "Type: $type"
    println "Language: $language"
    println "Base dir:    ${getBasedir()}"
    println "dir var:     $dir"
    println "Layout dir:  ${getDir()} "
    println "layout prop: ${project.getProperty('layoutdir')}"
    println "Log level: $loglevel"
  }
}
