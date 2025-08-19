project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'publish-example'
  version = '0.0.1'
  defaultTarget = 'init'

  sourceDir = new File("src/main/groovy")
  buildDir = new File("build")

  dependencies(id: 'compile') {
    dependency(coords: 'se.alipsa.matrix:matrix-core:3.2.0')
    dependency(coords: 'se.alipsa.matrix:matrix-csv:2.1.0')

    dependency(coords: 'org.junit.jupiter:junit-jupiter-engine:5.12.2:test')
    dependency(coords: 'org.junit.platform:junit-platform-launcher:1.12.2:test')
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
        srcdir: sourceDir,
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
    artifact(file:jarFile, type:"jar", id:"jar")
  }

  target(name: 'sourceJar') {
    echo "Creating sources jar file"
    srcJarFile = new File(buildDir, "libs/${artifactId}-${version}-sources.jar")
    jar(destfile: srcJarFile) {
      fileset dir: sourceDir
    }
    artifact(file: srcJarFile, type: "jar", classifier: 'sources', id: "sourceJar")
  }

  target(name: 'groovydoc', depends: 'init') {
    echo "Creating groovydoc"
    groovydocDir = new File(buildDir, "groovydoc")
    mkdir dir: groovydocDir
    taskdef(name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc", classpathref: 'compilePath')
    groovydoc(destdir: groovydocDir, sourcepath: sourceDir) {
      link(packages:"java.,org.xml.,javax.,org.xml.", href: "https://docs.oracle.com/en/java/javase/21/docs/api/")
      link(packages:"groovy.,org.codehaus.groovy.", href:"http://docs.groovy-lang.org/latest/html/api/")
      link(packages:"org.apache.tools.ant.", href:"http://docs.groovy-lang.org/docs/ant/api/")
      link(packages:"org.junit.,junit.framework.", href:"https://junit.org/junit5/docs/current/api/")
    }
  }

  target(name: 'groovydocJar', depends: 'groovydoc') {
    echo "Creating groovydoc jar file"
    groovydocJarFile = new File(buildDir, "libs/${artifactId}-${version}-javadoc.jar")
    jar(destfile: groovydocJarFile) {
      fileset dir: groovydocDir
    }
    artifact(file: groovydocJarFile, type: "jar", classifier: 'javadocs', id: "javadocJar")
  }

  target(name: 'pom') {
    def pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
    echo "Creating and registering the pom file ${pomFile.canonicalPath}"
    createPom(pomTarget: pomFile, dependenciesRef: 'compile',
        name: 'publish-example', description: "A simple example of a publishable library") {
      licenses {
        license {
          name("MIT")
          url("https://opensource.org/license/mit")
        }
      }
      repositories {
        repository {
          id('jitpack.io')
          url('https://jitpack.io')
        }
      }

      developers {
        developer {
          name('Per Nyfelt')
          email('per.nyfelt@alipsa.se')
          organization('Alipsa HB')
          organizationUrl('http://www.alipsa.se')
        }
      }
      scm {
        connection('scm:git:https://github.com/Alipsa/publish-example.git')
        developerConnection('scm:git:https://github.com/Alipsa/publish-example.git')
        url('https://github.com/Alipsa/publish-example/tree/main')
      }
    }
  }

  target(name: 'publishLocal', depends: 'jar, pom') {
    echo "Publishing to local maven repository"
    artifacts(id: 'localArtifacts') {
      artifact refid: 'jar'
    }
    install(artifactsref:"localArtifacts")
  }

  target(name: 'publishRemote', depends: 'jar, sourceJar, groovydocJar, pom') {
    echo "Deploying to remote maven repository"
    artifacts(id: 'remoteArtifacts') {
      artifact refid: 'jar'
      artifact refid: 'sourceJar'
      artifact refid: 'javadocJar'
    }
    deploy(artifactsref: "remoteArtifacts") {
      remoteRepo(id: 'localNexus', url: 'http://localhost:8081/repository/repo/')
    }
  }
}



