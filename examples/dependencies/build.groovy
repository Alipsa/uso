project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'simple-example'
  version = '0.0.1'
  defaultTarget = 'init'

  buildDir = new File("build")

  dependencies(id: 'compile') {
    //dependency(groupId: 'se.alipsa.matrix', artifactId:'matrix-bom', version:'2.2.0', type: 'pom', scope: 'import')
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-core', version: '3.2.0')
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-csv', version: '2.1.0')

    //dependency(groupId: 'org.junit', artifactId: 'junit-bom', version:'5.12.2', type: 'pom', scope: 'import')
    dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', version:'5.12.2', scope: 'test')
    dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', version:'1.12.2', scope: 'test')
    //dependency(coords: 'org.apache.ant:ant-junitlauncher:1.10.15', scope: 'test')
  }

  target('init') {
    echo "Initializing project..."
    resolve(dependenciesref: 'compile') {
      path(refId: 'compilePath', classpath: 'compile')
      path(refId: 'testPath', classpath: 'test')
    }
    mkdir dir: buildDir
    mainBuildDir = new File(buildDir, "main")
    mkdir dir: mainBuildDir
    testBuildDir = new File(buildDir, "test")
    mkdir dir: testBuildDir
    path(id: 'testClassPath') {
      path refid: 'testPath'
      pathelement location: mainBuildDir
    }
  }

  target(name: 'clean') {
    delete dir: buildDir, quiet: true
  }

  target(name: 'compile', depends: 'init') {
    echo "Compiling main groovy classes"
    int level = setOutputLevel(1)
    groovyc(
        srcdir: 'src/main/groovy',
        destdir: mainBuildDir,
        classpathref: 'compilePath',
        targetBytecode: "21"
    )
    setOutputLevel(level)
  }

  target(name: 'test', depends: 'compile') {
    echo "Compiling test groovy classes"

    int level = setOutputLevel(1)
    groovyc(
        srcdir: 'src/test/groovy',
        destdir: testBuildDir,
        classpathref: 'testClassPath',
        targetBytecode: "21"
    )
    setOutputLevel(level)

    def outputDir = new File(buildDir, "testoutput")
    mkdir dir: outputDir

    junitlauncher(printSummary: true, haltOnFailure: true) {
      classpath {
        pathelement location: mainBuildDir
        pathelement location: testBuildDir
        path refid: "testPath"
      }
      testclasses(outputdir: outputDir) {
        fileset dir: testBuildDir
        listener type: "legacy-xml", sendSysOut: true, sendSysErr: true
        //listener type: "legacy-plain", sendSysOut: true, sendSysErr: true
      }
    }
    testReportDir = new File(outputDir, "testreport")
    mkdir dir: testReportDir
    junitreport(todir: outputDir) {
      fileset dir: outputDir
      report format: 'frames', todir: testReportDir
    }
    //def testResults = new File(outputDir, "TEST-MatrixExampleTest.txt")
    //if (testResults.exists()) println testResults.text
  }

  target(name: 'jar', depends: 'compile') {
    echo "Creating jar file"
    jarFile = new File(buildDir, "libs/${artifactId}-${version}.jar")
    jar(destfile: jarFile) {
      fileset dir: mainBuildDir
    }
  }

  /*target(name: 'pom') {
    echo "Creating pom file"
    pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
    pomFile.text = """
    <project xmlns="http://maven.apache.org/POM/4.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
          <modelVersion>4.0.0</modelVersion>
      <groupId>$groupId</groupId>
      <artifactId>$artifactId</artifactId>
      <version>$version</version>
      <packaging>jar</packaging>
      <name>Dependencies example</name>
      <description>Show how dependencies works in uso</description>
      ${dependencies.compile.toMavenXml()}
    </project>
    """.stripIndent()
  }

  target(name: 'deployLocal', depends: 'jar, pom') {
    echo "Deploying to local maven repository"
    def m2Home = new File(System.getProperty("user.home"), ".m2/repository")
    def groupDir = groupId.replace('.', '/')
    def localRepo = new File(m2Home, "repository/$groupDir/$artifactId/$version")
    if (!localRepo.exists()) {
      localRepo.mkdirs()
    }
    copy(file: jarFile, todir: localRepo)
    copy(file: pomFile, todir: localRepo)
  }*/
}



