project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'simple-example'
  version = '0.0.1'
  defaultTarget = 'init'

  buildDir = new File("build")

  // TODO: this requires an enhancement of of maven-resolver-ant-tasks to work
  dependencyManagement(id: 'dm') {
    dependencies {
      dependency(groupId: 'se.alipsa.matrix', artifactId:'matrix-bom', version:'2.2.0', type: 'pom', scope: 'import')
      dependency(groupId: 'org.junit', artifactId: 'junit-bom', version:'5.12.2', type: 'pom', scope: 'import')
    }
  }

  dependencies(id: 'compile', references: 'dm') {
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-core')
    dependency(groupId:'se.alipsa.matrix', artifactId:'matrix-csv')

    dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test')
    dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', scope: 'test')
  }

  target('init') {
    echo "Initializing project..."
    // TODO: resolve needs to be enhanced to work with dependencyManagement
    //   or take a pom file reference (requiring createPom() to run before resolve)
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
    artifact(file:jarFile, type:"jar", id:"jar")
  }

  target(name: 'pom') {
    pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
    echo "Creating pom file ${pomFile.canonicalPath}"
    // TODO: createPom needs to be enhanced to work with dependencyManagement
    createPom(pomTarget: pomFile, dependenciesRef: 'compile',
        name: 'publish-example', description: "A simple example of a publishable library")
  }

  target(name: 'deployLocal', depends: 'jar, pom') {
    echo "Deploying to local maven repository"
    artifacts(id: 'localArtifacts') {
      artifact refid: 'jar'
    }
    install(artifactsref:"localArtifacts") {
      pom file: pomFile
    }
  }
}



