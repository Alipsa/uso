project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'simple-example'
  version = '0.0.1'
  defaultTarget = 'init'

  buildDir = new File("build")

  dependencies(id: 'compile') {
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-core', version: '3.2.0')
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-csv', version: '2.1.0')

    dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', version:'5.12.2', scope: 'test')
    dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', version:'1.12.2', scope: 'test')
  }

  target('init') {
    echo "Initializing project..."
    // if we dont create the pom file and then call pom(), we can reference the dependencies instead
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
  }

  target(name: 'jar', depends: 'compile') {
    echo "Creating jar file"
    jarFile = new File(buildDir, "libs/${artifactId}-${version}.jar")
    jar(destfile: jarFile) {
      fileset dir: mainBuildDir
    }
  }
}



