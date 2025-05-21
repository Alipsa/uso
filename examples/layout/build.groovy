project.with {
  groupId = 'se.alipsa.uso.layout'
  artifactId = 'layout-example'
  version = '0.0.1'

  target('init') {
    // Set all magic properties for the layout
    layout(type: 'maven', language: 'groovy')
    // print all properties so its is more clear whats going on
    echoproperties()
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



