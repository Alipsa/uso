import groovy.ant.AntBuilder
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import org.xmlunit.builder.Input
import org.xmlunit.diff.DefaultNodeMatcher
import org.xmlunit.diff.ElementSelectors
import org.xmlunit.matchers.CompareMatcher

class CreatePomTest {

  /**
   * This test creates a minimal pom file using the CreatePom task.
   * It uses the AntBuilder to create the pom file and then compares it to an expected pom file.
   * The expected pom file is created using the MavenProject class.
   */
  @Test
  void testCreateDependencyPom() {

    File pomFile = null
    AntBuilder ant = new AntBuilder()
    ant.with {

      path(id: 'libpath') {
        pathelement location: "out/main"
      }

      typedef(name: "dependencyManagement", classname: "org.apache.maven.resolver.internal.ant.types.DependencyManagement")
      taskdef(name: 'createPom', classname: 'org.apache.maven.resolver.internal.ant.tasks.CreatePom', classpathref: 'libpath')
      typedef(name: "dependency", classname: "org.apache.maven.resolver.internal.ant.types.Dependency")
      typedef(name: "dependencies", classname: "org.apache.maven.resolver.internal.ant.types.Dependencies")
      antProject.setProperty('grp', 'se.alipsa.uso.tasks') // Test that groups can be overridden
      antProject.setProperty('groupId', 'se.alipsa.uso')
      antProject.setProperty('artifactId', 'CreateDependencyPomTest')
      antProject.setProperty('version', '1.0.0')
      dependencyManagement(id: 'dm') {
        dependencies {
          dependency(groupId: 'org.junit', artifactId: 'junit-bom', version: '5.12.2', type: 'pom', scope: 'import')
        }
      }
      dependencies(id: 'compile') {
        dependency(groupId: 'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test')
        dependency(groupId: 'org.junit.platform', artifactId: 'junit-platform-launcher', scope: 'test')
      }
      def outDir = new File("out/testClasses")
      if (!outDir.exists()) {
        outDir.mkdirs()
      }
      pomFile = new File(outDir, "${antProject.getProperty('artifactId')}-${antProject.getProperty('version')}.pom")
      createPom(pomTarget: pomFile, dependenciesRef: 'compile', dependencyManagementRef: 'dm', groupId: '${grp}')
    }
    def content = pomFile.text
    def expected = '''\
      |<?xml version="1.0" encoding="UTF-8"?>
      |<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      |    <modelVersion>4.0.0</modelVersion>
      |    <groupId>se.alipsa.uso.tasks</groupId>
      |    <artifactId>CreateDependencyPomTest</artifactId>
      |    <version>1.0.0</version>
      |    <dependencyManagement>
      |        <dependencies>
      |            <dependency>
      |                <groupId>org.junit</groupId>
      |                <artifactId>junit-bom</artifactId>
      |                <version>5.12.2</version>
      |                <type>pom</type>
      |                <scope>import</scope>
      |            </dependency>
      |        </dependencies>
      |    </dependencyManagement>
      |    <dependencies>
      |        <dependency>
      |            <groupId>org.junit.jupiter</groupId>
      |            <artifactId>junit-jupiter-engine</artifactId>
      |            <scope>test</scope>
      |        </dependency>
      |        <dependency>
      |            <groupId>org.junit.platform</groupId>
      |            <artifactId>junit-platform-launcher</artifactId>
      |            <scope>test</scope>
      |        </dependency>
      |    </dependencies>
      |</project>
      '''.trim().stripMargin()
    MatcherAssert.assertThat(
        Input.from(content).build(),
        CompareMatcher.isSimilarTo(Input.from(expected).build())
            .ignoreWhitespace()
    )
  }

  /**
   * This test creates a pom file suitable for publishing using the CreatePom task.
   */
  @Test
  void testCreatePom() {
    File pomFile = null
    AntBuilder ant = new AntBuilder()
    ant.with {

      path(id: 'libpath') {
        pathelement location: "out/main"
      }

      typedef(name: "dependencyManagement", classname: "org.apache.maven.resolver.internal.ant.types.DependencyManagement")
      taskdef(name: 'createPom', classname: 'org.apache.maven.resolver.internal.ant.tasks.CreatePom', classpathref: 'libpath')
      typedef(name: "dependency", classname: "org.apache.maven.resolver.internal.ant.types.Dependency")
      typedef(name: "dependencies", classname: "org.apache.maven.resolver.internal.ant.types.Dependencies")

      antProject.setProperty('author', 'Per Nyfelt') // Test that properties are resolved in the additional pom content
      antProject.setProperty('groupId', 'se.alipsa.uso')
      antProject.setProperty('artifactId', 'create-pom-test')
      antProject.setProperty('version', '1.0.0')
      antProject.name = 'CreatePomTest'
      dependencyManagement(id: 'dm') {
        dependencies {
          dependency(groupId: 'org.junit', artifactId: 'junit-bom', version: '5.12.2', type: 'pom', scope: 'import')
        }
      }
      dependencies(id: 'compile') {
        dependency(groupId: 'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test', type: 'jar')
        dependency(groupId: 'org.junit.platform', artifactId: 'junit-platform-launcher', scope: 'test')
      }
      def outDir = new File("out/testClasses")
      if (!outDir.exists()) {
        outDir.mkdirs()
      }
      pomFile = new File(outDir, "${antProject.getProperty('artifactId')}-${antProject.getProperty('version')}.pom")
      createPom(pomTarget: pomFile, dependenciesRef: 'compile', dependencyManagementRef: 'dm',
          name: '${ant.project.name}', description: "A simple example of a publishable library") {
        licenses {
          license {
            name "The Apache Software License, Version 2.0"
            url "http://www.apache.org/licenses/LICENSE-2.0.txt"
            distribution "repo"
          }
          license {
            name "MIT"
            url "https://opensource.org/license/mit"
          }
        }
        repositories {
          repository {
            id 'jitpack.io'
            url 'https://jitpack.io'
          }
        }
        developers {
          developer {
            name '${author}'
            email 'per.nyfelt@alipsa.se'
            organization 'Alipsa HB'
            organizationUrl 'http://www.alipsa.se'
          }
        }
        scm {
          connection 'scm:git:https://github.com/Alipsa/journo.git'
          developerConnection 'scm:git:https://github.com/Alipsa/journo.git'
          url 'https://github.com/Alipsa/journo/tree/main'
        }
      }
    }
    def content = pomFile.text
    def expected = ('''\
      |<?xml version="1.0" encoding="UTF-8"?>
      |<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0">
      |  <modelVersion>4.0.0</modelVersion>
      |  <groupId>se.alipsa.uso</groupId>
      |  <artifactId>create-pom-test</artifactId>
      |  <version>1.0.0</version>
      |  <name>CreatePomTest</name>
      |  <description>A simple example of a publishable library</description>
      |  <licenses>
      |    <license>
      |      <name>The Apache Software License, Version 2.0</name>
      |      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      |      <distribution>repo</distribution>
      |    </license>
      |    <license>
      |      <name>MIT</name>
      |      <url>https://opensource.org/license/mit</url>
      |    </license>
      |  </licenses>
      |  <developers>
      |    <developer>
      |      <name>Per Nyfelt</name>
      |      <email>per.nyfelt@alipsa.se</email>
      |      <organization>Alipsa HB</organization>
      |      <organizationUrl>http://www.alipsa.se</organizationUrl>
      |    </developer>
      |  </developers>
      |  <scm>
      |    <connection>scm:git:https://github.com/Alipsa/journo.git</connection>
      |    <developerConnection>scm:git:https://github.com/Alipsa/journo.git</developerConnection>
      |    <url>https://github.com/Alipsa/journo/tree/main</url>
      |  </scm>
      |  <dependencyManagement>
      |    <dependencies>
      |      <dependency>
      |        <groupId>org.junit</groupId>
      |        <artifactId>junit-bom</artifactId>
      |        <version>5.12.2</version>
      |        <type>pom</type>
      |        <scope>import</scope>
      |      </dependency>
      |    </dependencies>
      |  </dependencyManagement>
      |  <dependencies>
      |    <dependency>
      |      <groupId>org.junit.jupiter</groupId>
      |      <artifactId>junit-jupiter-engine</artifactId>
      |      <scope>test</scope>
      |    </dependency>
      |    <dependency>
      |      <groupId>org.junit.platform</groupId>
      |      <artifactId>junit-platform-launcher</artifactId>
      |      <scope>test</scope>
      |    </dependency>
      |  </dependencies>
      |  <repositories>
      |    <repository>
      |      <id>jitpack.io</id>
      |      <url>https://jitpack.io</url>
      |    </repository>
      |  </repositories>
      |</project>
      '''.trim().stripMargin())

    MatcherAssert.assertThat(
        Input.from(content).build(),
        CompareMatcher.isSimilarTo(Input.from(expected).build())
            .ignoreWhitespace()
            .withNodeFilter { node ->
              node.nodeType != org.w3c.dom.Node.COMMENT_NODE
            }
            .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
    )
  }
}