# Uso, an ANT based build system

The Uso ProjectBuilder is an extension of [AntBuilder](https://docs.groovy-lang.org/latest/html/api/groovy/ant/AntBuilder.html), adding declarative targets while still keeping the simplicity of AntBuilder to define and execute tasks. The result is a build system that works the same as Ant, but using Groovy as the build scripting language. The Maven Ant tasks are also included and enabled by default making it possible to define and resolve, dependencies and to publish artifacts to a maven repository.

It requires groovy to be installed and on the path (e.g. through sdkman)

The idea is very simple:
1. Your build script resides within a project closure.
2. You define targets with a name and optional target dependencies.
3. The target has a closure that is executed when the target is called.
4. The target closure typically contains ant tasks, but you are free to use any Groovy code you like.

An example build file could look like this:

```groovy
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
    pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
    echo "Creating pom file ${pomFile.canonicalPath}"
    createPom(pomTarget: pomFile, dependenciesRef: 'compile',
        name: 'publish-example', description: "A simple example of a publishable library")
  }

  target(name: 'publishLocal', depends: 'jar, pom') {
    echo "Deploying to local maven repository"
    artifacts(id: 'localArtifacts') {
      artifact refid: 'jar'
    }
    install(artifactsref:"localArtifacts") {
      pom file: pomFile
    }
  }

  target(name: 'deployRemote', depends: 'jar, sourceJar, groovydocJar, pom') {
    echo "Deploying to remote maven repository"
    artifacts(id: 'remoteArtifacts') {
      artifact refid: 'jar'
      artifact refid: 'sourceJar'
      artifact refid: 'javadocJar'
    }
    deploy(artifactsref: "remoteArtifacts") {
      pom file: pomFile
      remoteRepo(id: 'ossrh', url: 'https://oss.sonatype.org/content/repositories/snapshots/')
    }
  }
}
```

This is executed using the uso command, e.g. to run the clean and compile targets:

```bash
uso clean compile
```

The build script is executed in the context of a ProjectBuilder instance running in the [GroovyShell](https://docs.groovy-lang.org/docs/latest/html/documentation/#integ-groovyshell), which means that:
- The ProjectBuilder is injected into the build script with the name project. It extends the [AntBuilder](https://docs.groovy-lang.org/latest/html/api/groovy/ant/AntBuilder.html) so by doing `project.with {}` you can use all AntBuilder methods as well as the additional target methods in Project builder without having to reference the project object.
- variables that are declared without a type are global. You can use ant properties if you prefer, but it is not necessary.
- variables that are declared with a type or with def are local
- Maven ant tasks and the groovyc task are already defined so no need to do taskdef for them.

## Installation
To install uso, you need to have Groovy installed. You can use [sdkman](https://sdkman.io/) to install it. Once you have Groovy installed, you can install uso in the project you want to use it in using the following command:

```bash
curl -H 'Cache-Control: no-cache, no-store' \
-s "https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/installUso.sh" | bash
```
The uso script uses the default way of how ant is executing targets i.e. for each target supplied to the command line, it will execute all the dependent targets for each one.

Let's say we have 3 targets:
- init
- clean depends on init
- compile depends on init

If you run the following commands:
- `uso clean compile` will execute init -> clean -> init -> compile. (ant style)

### Documentation
- The [user manual](docs/uso_user_manual.md) is a good start.
- The [examples](examples) directory contains more examples of how to use uso.
- The [tasks manual](docs/manual/00-Introduction_Groovy_Ant_Manual.md) is basically a translation of the ant manual into the AntBuilder DSL syntax.

### What is the meaning of Uso?
Uso is a Spanish word meaning "use" (first person). It also refers to Usomyrma mirabilis, an extinct species of ant in the formicid subfamily Dolichoderinae, that is known from two Middle Eocene fossils which were found in Scandinavian amber (The project founder is from Scandinavia).
The name was chosen because it is short and easy to remember, and it has a nice ring to it. The project is intended to be a simple and easy-to-use Groovy based build system for jvm languages, so the name fits well.

