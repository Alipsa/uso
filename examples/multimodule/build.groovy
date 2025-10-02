project.with {
  defaultTarget = 'buildAll'
  name = 'multimodule-aggregator'
  artifactId = '${name}'
  version = '1.0.0-SNAPSHOT'

  property(file: 'multimodule.properties')
  echo 'Multimodule base, groupId: ${groupId}'

  target('buildAll') {
    // The task uso is the equivalent to the Ant task <ant dir="common" antfile="build.xml" target="publishLocal"/>
    uso(file: 'common/build.groovy', target: 'publishLocal')
    uso(file: 'subA/build.groovy', target: 'publishLocal', unless: 'groupId')
    uso(file: 'subB/build.groovy', target: 'publishLocal')

    assert new File("common/target/common-1.0.0-SNAPSHOT.jar").exists()
    assert new File("subA/target/subA-1.0.0-SNAPSHOT.jar").exists()
    assert new File("subB/target/subB-1.0.0-SNAPSHOT.jar").exists()
  }

  target('clean') {
    // The task uso is the equivalent to the Ant task <ant dir="common" antfile="build.xml" target="publishLocal"/>
    uso(file: 'common/build.groovy', target: 'clean')
    uso(file: 'subA/build.groovy', target: 'clean')
    uso(file: 'subB/build.groovy', target: 'clean')

    assert !new File("common/target/common-1.0.0-SNAPSHOT.jar").exists()
    assert !new File("subA/target/subA-1.0.0-SNAPSHOT.jar").exists()
    assert !new File("subB/target/subB-1.0.0-SNAPSHOT.jar").exists()
  }
}