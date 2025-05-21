// Running this in AntBuilder so we do all tasks at top level as AntBuilder does not support targets
project.with {
  groupId = 'se.alipsa.uso.layout'
  artifactId = 'layout-example'
  version = '0.0.1'

  // we cannot create methods inside the 'with', but closures are a good substitute
  checkProperty = { String propName, String pathPart ->
    fail unless: propName, message: "$propName property is not set"
    assert antProject.getProperty(propName).contains(pathPart)
    File propDir = new File(antProject.getProperty(propName))
    if (!propDir.exists()) {
      fail message: "$propName directory does not exist: ${propDir}"
    }
  }

  taskdef(name: "layout", classname: "se.alipsa.uso.tasks.Layout")
  layout(dir: 'mvnGroovy', type: 'maven', language: 'groovy')
  checkProperty('mainSrcDir', 'mvnGroovy/src/main/groovy')
  checkProperty('testSrcDir', 'mvnGroovy/src/test/groovy')
  checkProperty('mainResourcesDir', 'mvnGroovy/src/main/resources')
  checkProperty('testResourcesDir', 'mvnGroovy/src/test/resources')
  checkProperty('buildDir', 'mvnGroovy/target')
  checkProperty('mainGeneratedDir', 'mvnGroovy/target/generated-sources')
  checkProperty('testGeneratedDir', 'mvnGroovy/target/generated-test-sources')
  checkProperty('mainClassesDir', 'mvnGroovy/target/classes')
  checkProperty('testClassesDir', 'mvnGroovy/target/test-classes')
  checkProperty('mainDocDir', 'mvnGroovy/target/groovydoc')
  checkProperty('testDocDir', 'mvnGroovy/target/test-groovydoc')
  checkProperty('distDir', 'mvnGroovy/target')
  echo "all properties and directories created successfully, cleaning up..."
  delete(dir: 'mvnGroovy')

}



