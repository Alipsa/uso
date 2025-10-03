project.with {
  defaultTarget = 'buildAll'
  name = 'multimodule-aggregator'
  artifactId = '${name}'
  version = '1.0.0-SNAPSHOT'

  property(file: 'multimodule.properties')
  echo 'Multimodule base, groupId: ${groupId}, buildDir = ${buildDir}'
  property('commonJarPath', "common/target/multimodule-common-1.0.0.jar")
  property('subBJarPath', 'subB/target/multimodule-subB-1.0.0.jar')

  target('buildAll') {
    // The task uso is the equivalent to the Ant task <ant dir="common" antfile="build.xml" target="publishLocal"/>
    uso(file: 'common/build.groovy', target: 'publishLocal')
    uso(file: 'subA/build.groovy', target: 'publishLocal', unless: 'groupId')
    uso(file: 'subB/build.groovy', target: 'publishLocal')

    assert new File(antProject.getProperty('commonJarPath')).exists()
    //assert new File("subA/target/multimodule-subA-1.0.0.jar").exists() // skipped above
    assert new File(antProject.getProperty('subBJarPath')).exists()
  }

  target('clean') {
    // The task uso is the equivalent to the Ant task <ant dir="common" antfile="build.xml" target="publishLocal"/>
    uso(file: 'common/build.groovy', target: 'clean')
    uso(file: 'subA/build.groovy', target: 'clean')
    uso(file: 'subB/build.groovy', target: 'clean')

    assert !new File(antProject.getProperty('commonJarPath')).exists()
    assert !new File("subA/target/subA-1.0.0.jar").exists()
    assert !new File(antProject.getProperty('subBJarPath')).exists()
  }
}