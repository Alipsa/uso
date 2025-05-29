project.with {
  groupId = 'se.alipsa.uso.examples.multimodule'

  property('buildDir', "build")
  property('classVersion', "21")
  echo 'Multimodule base, groupId: ${groupId}'

  target('buildAll') {
    // The task runProject is the equivalent to the Ant task <ant dir="common" antfile="build.xml" target="publishLocal"/>
    uso(dir: 'common', target: 'publishLocal')
    uso(dir: 'subA', target: 'publishLocal')
    uso(dir: 'subB', target: 'publishLocal')
  }
}