project.with {
  groupId = 'se.alipsa.uso.layout'
  artifactId = 'layout-example'
  version = '0.0.1'

  target('init') {
    // Set all magic properties for the layout
    layout(type: 'maven', language: 'groovy', logLevel: org.apache.tools.ant.Project.MSG_VERBOSE)
  }

  target(name: 'clean', depends: 'init') {
    delete dir: '${buildDir}'
    mkdir dir: '${mainClassesDir}'
  }

  target(name: 'compile', depends: 'init') {
    echo "Compiling main groovy classes"
    groovyc(
        srcdir: '${mainSrcDir}',
        destdir: '${mainClassesDir}'
    )
  }

}



