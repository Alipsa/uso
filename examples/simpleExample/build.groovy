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



