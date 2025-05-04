# Uso, an ANT based build system

The Uso ProjectBuilder is an extension of [AntBuilder](https://docs.groovy-lang.org/latest/html/api/groovy/ant/AntBuilder.html), adding declarative targets while still keeping the simplicity of AntBuilder to define and execute tasks. The result is a build system that works the same as Ant, but using Groovy as the build scripting language.

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
        srcdir: 'src/main/groovy',
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

The idea is very simple: 
1. Your build script resides within a project closure.
2. You define targets with a name and optional target dependencies.
3. The target har a closure that is executed when the target is called.
4. The target closure typically contains ant tasks but you are free to use any Groovy code you like.

The build script is executed in the context of a ProjectBuilder instance running in the jsr233 [GroovyScriptEngineImpl](https://docs.groovy-lang.org/latest/html/api/org/codehaus/groovy/jsr223/GroovyScriptEngineImpl.html), which means that:
- The ProjectBuilder is injected into the build script with the name project. It extends the [AntBuilder](https://docs.groovy-lang.org/latest/html/api/groovy/ant/AntBuilder.html) so by doing `project.with {}` you can use all AntBuilder methods as well as the additional target methods in Project builder without having to reference the project object.
- variables that are declared without a type are global. You can use ant properties if you prefer but it is not necessary.
- variables that are declared with a type or with def are local

## Installation
To install uso, you need to have Groovy installed. You can use [sdkman](https://sdkman.io/) to install it. Once you have Groovy installed, you can install uso in the project you want to use it in using the following command:

```bash
curl -H 'Cache-Control: no-cache, no-store' \
-s "https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/installUso.sh" | bash
```
There are two versions of uso scripts:
- uso: this version only executes a target once per build
- usas: this version uses the default way of how ant is executing targets i.e. for each target supplied to the command line, it will execute all the dependent targets for each one.

Let's say we have 3 targets:
- init
- clean depends on init
- compile depends on init

If you run the following commands:
- `uso clean compile` will execute init -> clean -> compile. (maven style)
- `usas clean compile` will execute init -> clean -> init -> compile. (ant style)


### What is the meaning of Uso?
Uso is a Spanish word meaning "use" (first person; usas is the second person singular form). It also refers to Usomyrma mirabilis, an extinct species of ant in the formicid subfamily Dolichoderinae, that is known from two Middle Eocene fossils which were found in Scandinavian amber (The project founder is from Scandinavia).
The name was chosen because it is short and easy to remember, and it has a nice ring to it. The project is intended to be a simple and easy-to-use Groovy based build system for jvm languages, so the name fits well.

