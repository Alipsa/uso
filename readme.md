# Uso, an ANT based build system

The Uso ProjectBuilder is an extension of AntBuilder, adding declarative targets while still keeping the simplicity of AntBuilder to define and execute tasks. The result is a build system that works the same as Ant, but using Groovy as the build scripting language.

It requires groovy to be installed and on the path (e.g. through sdkman)

An example build file could look like this:

```groovy
project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'simple-example'
  version = '0.0.1'
  defaultTarget = 'init'

  target('init') {
    buildDir = new File("build")
    if (!buildDir.exists()) {
      buildDir.mkdir()
    }
    mainBuildDir = new File(buildDir, "main")
    if (!mainBuildDir.exists()) {
      mainBuildDir.mkdir()
    }
  }

  target(name: 'clean', depends: 'init') {
    delete dir: buildDir
    mkdir dir: mainBuildDir
  }

  target(name: 'compile', depends: 'init') {
    def groovyHome = System.getenv("GROOVY_HOME")
    def groovyClasspath = new File(groovyHome, "lib").listFiles().findAll { it.name.endsWith(".jar") }
    echo "Compiling main groovy classes"
    groovyc(
        srcdir: 'src/test/groovy',
        destdir: mainBuildDir,
        classpath: groovyClasspath,
        targetBytecode: "21"
    )
  }
}
```

This is executed using the uso command, e.g. to run the clean and compile targets:

```bash
uso clean compile
```


The build script is executed in the context of a ProjectBuilder instance running in the jsr233 GroovyScriptEngineImpl, which means that:
- The ProjectBuilder is injected into the build script with the name project. It extends the AntBuilder so by doing `project.with {}` you can use all AntBuilder methods as well as the additional target methods in Project builder without having to reference the project object.
- variables that are declared without a type are global
- variables that are declared with a type or with def are local

