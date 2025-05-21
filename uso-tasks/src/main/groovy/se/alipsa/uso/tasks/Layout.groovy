package se.alipsa.uso.tasks

import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import org.apache.tools.ant.taskdefs.Mkdir

/**
 * This task creates some standard layout for a project and defined properties
 * pointing to each directory.
 * <p>
 * The layout type can be one of:
 * <ul>
 *  <li>maven</li>
 *  <li>gradle</li>
 *  <li>simple</li>
 * </ul>
 * </p>
 * The following properties are defined:
 * TODO: see
 * https://ant.apache.org/manual/properties.html
 * https://web.archive.org/web/20150520200505/https://docs.codehaus.org/display/MAVENUSER/MavenPropertiesGuide
 * https://docs.gradle.org/current/dsl/org.gradle.api.Project.html?_ga=2.142209076.745539784.1747744750-1766038757.1739351589&_gl=1*13dw785*_gcl_au*OTQwNzQ5NTAyLjE3NDU4MzMxMjk.*_ga*MTc2NjAzODc1Ny4xNzM5MzUxNTg5*_ga_7W7NC6YNPT*czE3NDc3NDQ3NTAkbzQ2JGcwJHQxNzQ3NzQ0NzUwJGo2MCRsMCRoMCRkb1BoUHdpbVgwTlBpN0pDOHhORzhiazUyQnpYWGRLeHlyQQ..#org.gradle.api.Project:buildDir
 *
 */
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
  String mainDocDir
  String testDocDir
  String distDir
  String type = 'maven'
  String language = 'groovy'
  File dir

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
    mainDocDir = "${buildDir}/${language}doc"
    testDocDir = "${buildDir}/test-${language}doc"
    distDir = "${buildDir}"
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
    createDirAndSetProperty('mainDocDir', mainDocDir)
    createDirAndSetProperty('testDocDir', testDocDir)
    createDirAndSetProperty('distDir', distDir)
  }

  def createDirAndSetProperty(String name, String value) {
    File targetDir = new File(value)
    if (!targetDir.exists()) {
      targetDir.mkdirs()
    }
    project.setProperty(name, value)
    project.log("property: $name = $value", Project.MSG_VERBOSE)
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
}
