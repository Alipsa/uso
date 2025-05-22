project.with {
  groupId = 'se.alipsa.uso'
  artifactId = 'simple-example'
  version = '0.0.1'
  defaultTarget = 'init'

  target('init') {
    property('buildDir', "build") // define an ant property (value must be a String or GString)
    mkdir dir: '${buildDir}'
    // Straight Groovy code, we must resolve the property directly
    // property('buildDir') and $('buildDir') are equivalent
    mainBuildDir = new File($('buildDir'), "main")
    mkdir dir: mainBuildDir
  }

  target(name: 'clean', depends: 'init') {
    // In an Ant task, we can use the property using the ${propertyName} syntax (surrounded by single quotes)
    delete dir: '${buildDir}'
    // if we use double quotes (GString), the property will be resolved by groovy directly,
    // since mainBuildDir is a global groovy variable this will work:
    // mkdir dir: "${mainBuildDir}"
    // if we wanted to use is as an ant property, we would have to do
    // property('mainBuildDir', mainBuildDir.canonicalPath) first
    // and then refer to it as '${mainBuildDir}'
    // or resolve it directly with $() i.e. mkdir dir: $('mainBuildDir')
    property('mainBuildDir', mainBuildDir.canonicalPath)
    mkdir dir: '${mainBuildDir}'
  }

  target(name: 'compile', depends: 'init') {
    def groovyHome = System.getenv("GROOVY_HOME")
    def groovyClasspath = new File(groovyHome, "lib").listFiles().findAll { it.name.endsWith(".jar") }
    echo "Compiling main groovy classes" 123
    groovyc(
        srcdir: 'src/test/groovy',
        destdir: mainBuildDir,
        classpath: groovyClasspath,
        targetBytecode: "21"
    )
  }
}



