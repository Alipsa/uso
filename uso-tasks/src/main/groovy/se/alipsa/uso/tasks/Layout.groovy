package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import org.apache.tools.ant.types.LogLevel

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
 *     <dt>logLevel</dt>
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
//@CompileStatic
class Layout extends Task {
  String mainSrcDir
  String testSrcDir
  String mainResourcesDir
  String testResourcesDir
  String buildDir
  String mainGeneratedDir
  String testGeneratedDir
  String mainClassesDir
  String testClassesDir
  String testResultDir
  String testReportDir
  String mainDocDir
  String testDocDir
  String distDir
  String type = 'maven'
  String language = 'groovy'
  File dir
  int logLevel = Project.MSG_VERBOSE

  Layout() {
    super()
  }

  String getBasedir() {
    return project.getBaseDir()?.canonicalPath ?: project.getProperty('basedir') ?: '.'
  }

  File getDir() {
    return dir ?: new File(getBasedir())
  }

  def mavenLayout() {
    mainSrcDir = "${getDir()}/src/main/$language"
    testSrcDir = "${getDir()}/src/test/$language"
    mainResourcesDir = "${getDir()}/src/main/resources"
    testResourcesDir = "${getDir()}/src/test/resources"
    buildDir = "${getDir()}/target"
    mainGeneratedDir = "${buildDir}/generated-sources"
    testGeneratedDir = "${buildDir}/generated-test-sources"
    mainClassesDir = "${buildDir}/classes"
    testClassesDir = "${buildDir}/test-classes"
    testResultDir = "${buildDir}/surefire-reports"
    testReportDir = "${buildDir}/site"
    mainDocDir = "${buildDir}/${language}doc"
    testDocDir = "${buildDir}/test-${language}doc"
    distDir = "${buildDir}"
  }

  def gradleLayout() {
    mainSrcDir = "${getDir()}/src/main/${language}"
    testSrcDir = "${getDir()}/src/test/${language}"
    mainResourcesDir = "${getDir()}/src/main/resources"
    testResourcesDir = "${getDir()}/src/test/resources"
    buildDir = "${getDir()}/build"
    mainGeneratedDir = "${buildDir}/generated/sources/main/${language}"
    testGeneratedDir = "${buildDir}/generated/sources/test/${language}"
    mainClassesDir = "${buildDir}/classes/${language}/main"
    testClassesDir = "${buildDir}/classes/${language}/test"
    testResultDir = "${buildDir}/test-results/test"
    testReportDir = "${buildDir}/test-results/report"
    mainDocDir = "${buildDir}/docs/${language}doc"
    testDocDir = "${buildDir}/docs/test-${language}doc"
    distDir = "${buildDir}/libs"
  }

  def simpleLayout() {
    mainSrcDir = "${getDir()}/src/code"
    testSrcDir = "${getDir()}/test/code"
    mainResourcesDir = "${getDir()}/src/resources"
    testResourcesDir = "${getDir()}/test/resources"
    buildDir = "${getDir()}/out"
    mainGeneratedDir = "${buildDir}/generated-sources"
    testGeneratedDir = "${buildDir}/generated-test-sources"
    mainClassesDir = "${buildDir}/classes"
    testClassesDir = "${buildDir}/test-classes"
    testResultDir = "${buildDir}/test-results"
    testReportDir = "${buildDir}/test-reports"
    mainDocDir = "${buildDir}/${language}doc"
    testDocDir = "${buildDir}/test-${language}doc"
    distDir = "${buildDir}"
  }

  void execute() {
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

  def createDirAndSetProperty(String name, String value) {
    File targetDir = new File(value)
    if (!targetDir.exists()) {
      println "Creating directory: ${targetDir.getAbsolutePath()}"
      targetDir.mkdirs()
    }
    project.setProperty(name, value)
    log("property: $name = $value", logLevel)
  }

  void setMainSrcDir(String mainSrcDir) {
    this.mainSrcDir = mainSrcDir
  }

  void setTestSrcDir(String testSrcDir) {
    this.testSrcDir = testSrcDir
  }

  void setMainResourcesDir(String mainResourcesDir) {
    this.mainResourcesDir = mainResourcesDir
  }

  void setTestResourcesDir(String testResourcesDir) {
    this.testResourcesDir = testResourcesDir
  }

  void setBuildDir(String buildDir) {
    this.buildDir = buildDir
  }

  void setMainGeneratedDir(String mainGeneratedDir) {
    this.mainGeneratedDir = mainGeneratedDir
  }

  void setTestGeneratedDir(String testGeneratedDir) {
    this.testGeneratedDir = testGeneratedDir
  }

  void setMainClassesDir(String mainClassesDir) {
    this.mainClassesDir = mainClassesDir
  }

  void setTestClassesDir(String testClassesDir) {
    this.testClassesDir = testClassesDir
  }

  void setMainDocDir(String mainDocDir) {
    this.mainDocDir = mainDocDir
  }

  void setTestDocDir(String testDocDir) {
    this.testDocDir = testDocDir
  }

  void setDistDir(String distDir) {
    this.distDir = distDir
  }

  void setType(String type) {
    this.type = type
  }

  void setLanguage(String language) {
    this.language = language
  }

  void setDir(File dir) {
    this.dir = dir
  }

  void setDir(String dir) {
    this.dir = new File(dir)
  }

  void setLoglevel(int logLevel) {
    setLogLevel(logLevel)
  }

  void setLogLevel(int logLevel) {
    String level = switch (logLevel) {
      case Project.MSG_ERR -> 'ERROR'
      case Project.MSG_WARN -> 'WARN'
      case Project.MSG_INFO -> 'INFO'
      case Project.MSG_VERBOSE -> 'VERBOSE'
      case Project.MSG_DEBUG -> 'DEBUG'
      default -> 'UNKNOWN'
    }
    log("Setting log level to $level ($logLevel)", Project.MSG_WARN)
    this.logLevel = logLevel
  }

  void setLoglevel(String logLevel) {
    setLogLevel(logLevel)
  }

  void setLogLevel(String logLevel) {
    int level = switch (logLevel.toUpperCase()) {
      case '0', 'ERROR' -> Project.MSG_ERR
      case '1', 'WARN' -> Project.MSG_WARN
      case '2', 'INFO' -> Project.MSG_INFO
      case '3', 'VERBOSE' -> Project.MSG_VERBOSE
      case '4', 'DEBUG' -> Project.MSG_DEBUG
      default -> Project.MSG_INFO
    }
    log("Setting log level to $level", Project.MSG_WARN)
    this.logLevel = level
  }

  void setTestResultDir(String testResultDir) {
    this.testResultDir= testResultDir
  }

  void setTestReportDir(String testReportDir) {
    this.testReportDir = testReportDir
  }

  void printSettings() {
    println "Type: $type"
    println "Language: $language"
    println "Base dir: ${getBasedir()}"
    println "Layout dir: $dir"
    println "Log level: $logLevel"
  }
}
