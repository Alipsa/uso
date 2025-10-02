package se.alipsa.uso.tasks

import groovy.transform.CompileStatic
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
@CompileStatic
class Layout extends Task {
  File mainSrcDir
  File testSrcDir
  File mainResourcesDir
  File testResourcesDir
  File buildDir
  File mainGeneratedDir
  File testGeneratedDir
  File mainClassesDir
  File testClassesDir
  File testResultDir
  File testReportDir
  File mainDocDir
  File testDocDir
  File distDir
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
    return dir
  }

  def mavenLayout() {
    //project.log("Creating maven layout, dir is ${getDir()}")
    mainSrcDir = new File(dir, "src/main/$language")
    testSrcDir = new File(dir, "src/test/$language")
    mainResourcesDir = new File(dir, "src/main/resources")
    testResourcesDir = new File(dir, "src/test/resources")
    buildDir = new File(dir, "target")
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
    mainSrcDir = new File(dir, "src/main/${language}")
    testSrcDir = new File(dir, "src/test/${language}")
    mainResourcesDir = new File(dir, "src/main/resources")
    testResourcesDir = new File(dir, "src/test/resources")
    buildDir = new File(dir, "build")
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
    mainSrcDir = new File(dir, "src/code")
    testSrcDir = new File(dir, "test/code")
    mainResourcesDir = new File(dir, "src/resources")
    testResourcesDir = new File(dir, "test/resources")
    buildDir = new File(dir, "out")
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

  void execute() {
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

  def createDirAndSetProperty(String name, File targetDir) {
    //project.log("Layout: Creating directory $targetDir for property $name")
    targetDir.mkdirs()
    project.setProperty(name, targetDir.canonicalPath)
    project.log("property: $name = $targetDir", logLevel)
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
    project.log("Setting dir to $dir", logLevel)
    this.dir = dir
  }

  void setLogLevel(int logLevel) {
    this.logLevel = logLevel
  }

  void setTestResultDir(File testResultDir) {
    this.testResultDir= testResultDir
  }

  void setTestReportDir(File testReportDir) {
    this.testReportDir = testReportDir
  }
}
