# The bom example

When you want to use a bom file to manage dependencies, you can use the `dependencyManagement` section. The bom file is used to manage the versions of the dependencies in the project. 

For this to work you need to create a pom file and call the pom task to register it. The resolve task will then use the versions defined in the pom file to add versions when resolving dependencies.
### Example:
First you define the use of the bom file(s) to use in the `dependencyManagement` section:
```groovy
dependencyManagement(id: 'dm') {
  dependencies {
    dependency(groupId: 'org.junit', artifactId: 'junit-bom', version:'5.12.2', type: 'pom', scope: 'import')
  }
}
```

Then, in the `dependencies` section you can define dependencies without specifying the versions:
```groovy
dependencies(id: 'testDeps') {
  dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test')
  dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', scope: 'test')
}
```

Then, before you call the resolve task, you create the pom file:
```groovy
target('init') {
  echo "Initializing project..."
  pomFile = new File(buildDir, "libs/${artifactId}-${version}.pom")
  createPom(pomTarget: pomFile, dependenciesRef: 'testDeps', dependencyManagementRef: 'dm',
      name: 'publish-example', description: "A simple example of a publishable library")
  pom(file: pomFile) // This makes maven ant tasks aware of the pom file
  resolve { // resolve will use the pom file we just declared
    path(refId: 'compilePath', classpath: 'compile')
    path(refId: 'testPath', classpath: 'test')
  }
}
```

The createPom task makes this simple, but nothing stops you from creating the pom file manually. The only thing you need to do is to make sure that the pom file is created and registered with the pom task before the resolve task is called. 
```groovy
target('init') {
  echo "Initializing project..."
  pomFile = new File("build/libs/${artifactId}-${version}.pom")
  // Fetch the dependencies from the dependencies section
  def dependencies = new org.apache.maven.resolver.internal.ant.types.Dependencies()
  dependencies.setProject(project)
  def ref = new org.apache.tools.ant.types.Reference(project, 'testDeps')
  dependencies.setRefid(ref)
  // Create the XML for each dependency defined
  StringBuilder deps = new StringBuilder()
  se.alipsa.uso.tasks.CreatePom.appendDependencies(dependencies, deps)
  pomFile.text = """\
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>${groupId}</groupId>
  <artifactId>${artifactId}</artifactId>
  <version>${version}</version>
  <name>publish-example</namw>
  <description>A simple example of a publishable library</description>
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.junit</groupId>
        <artifactId>junit-bom</artifactId>
        <version>5.12.2</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <dependencies>
    ${deps.toString()}
  </dependencies>
</project>
  """
  pom(file: pomFile) // This makes maven ant tasks aware of the pom file
  resolve { // resolve will use the pom file we just declared
    path(refId: 'compilePath', classpath: 'compile')
    path(refId: 'testPath', classpath: 'test')
  }
}
```