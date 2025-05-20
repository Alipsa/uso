project.with {
  //groupId = 'se.alipsa.uso.layout'
  //artifactId = 'layout-example'
  //version = '0.0.1'
  setOutputLevel(3)

  target('mavenGroovy') {
    layout(type: 'maven', language: 'groovy')
  }

  target('mavenJava') {
    layout(type: 'maven', language: 'java')
  }

  target('gradleGroovy') {
    layout(type: 'gradle', language: 'groovy')
  }

  target('gradleJava') {
    layout(type: 'gradle', language: 'java')
  }

  target('simpleGroovy') {
    layout(type: 'simple', language: 'groovy')
  }

  target('simpleJava') {
    layout(type: 'simple', language: 'java')
  }

}



