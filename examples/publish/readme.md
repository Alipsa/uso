# The Publish Example

The publish example shows a simple Groovy project with one source file and two junit 5 tests.

To build and publish the project to the local maven repository, run the following command:

```bash
./uso publishLocal
```

To publish the project to the remote maven repository (a nexus server running locally in this case), run the following command:

```bash
./startNexus.sh
sleep 5
./uso publishRemote
```

### The key concepts to pay attention to in the build script are:

### project coordinates
You must define the following project coordinates:
```groovy
groupId = 'se.alipsa.uso'
artifactId = 'publish-example'
version = '0.0.1'
```
or alternatively, if you prefer:
```groovy
setGroupId('se.alipsa.uso')
setArtifactId('publish-example')
setVersion('0.0.1')
```

### Dependencies
The dependencies are defined in the `dependencies` closure. The dependencies are resolved using the maven resolver ant tasks and the resolved dependencies are added to the classpath of the project.

```groovy
dependencies(id: 'compile') {
  dependency(coords: 'se.alipsa.matrix:matrix-core:3.2.0')
  dependency(coords: 'se.alipsa.matrix:matrix-csv:2.1.0')

  dependency(coords: 'org.junit.jupiter:junit-jupiter-engine:5.12.2:test')
  dependency(coords: 'org.junit.platform:junit-platform-launcher:1.12.2:test')
}
```
The id of the dependencies are used in subsequent tasks.

### createPom
The `createPom` task creates a pom.xml file for the project. The pom.xml file is used to publish the project to the maven repository. 
```groovy
target(name: 'pom') {
  pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
  echo "Creating pom file ${pomFile.canonicalPath}"
  createPom(pomTarget: pomFile, dependenciesRef: 'compile',
      name: 'publish-example', description: "A simple example of a publishable library")
}
```
The pomTarget parameter can be either a File or a String. The pom file is created in the build/libs directory. The dependenciesRef parameter is the id of the dependencies defined in the dependencies closure. The name and description parameters are used to create the pom.xml file. GroupId, artifactId and version in the pom are set in the project coordinates section so does not need to be specified here.

### publish locally (a.k.a. install)
The install task publishes the project to the local maven repository. The local maven repository is usually located in ~/.m2/repository. The install task uses the pom.xml file created in the createPom task.

```groovy
target(name: 'jar', depends: 'compile') {
  jarFile = new File(buildDir, "libs/${artifactId}-${version}.jar")
  jar(destfile: jarFile) {
    fileset dir: mainBuildDir
  }
  artifact(file:jarFile, type:"jar", id:"jar")
}

target(name: 'publishLocal', depends: 'jar, pom') {
    artifacts(id: 'localArtifacts') {
      artifact refid: 'jar'
    }
    install(artifactsref:"localArtifacts") {
      pom file: pomFile
    }
  }
```
You must also define the artifacts to publish in the artifacts task. We could have defined it in the directly in the 
publishLocal task but since we also want to refer to it when publishing remotely, we define it in the jar task and only 
reference it in the publishLocal task.

### publish remotely (a.k.a. deploy)
The deploy task publishes the project to the remote maven repository. The remote maven repository is usually a nexus 
or artifactory server. The deploy task uses the pom.xml file created in the createPom task.

```groovy
target(name: 'publishRemote', depends: 'jar, sourceJar, groovydocJar, pom') {
  artifacts(id: 'remoteArtifacts') {
    artifact refid: 'jar'
    artifact refid: 'sourceJar'
    artifact refid: 'javadocJar'
  }
  deploy(artifactsref: "remoteArtifacts") {
    pom file: pomFile
    remoteRepo(id: 'ossrh', url: 'https://oss.sonatype.org/service/local/staging/deploy/maven2/')
  }
}