import groovy.ant.AntBuilder
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.xmlunit.builder.Input
import org.xmlunit.input.WhitespaceStrippedSource
import org.xmlunit.matchers.CompareMatcher

class CreatePomTest {

  /**
   * This test creates a minimal pom file using the CreatePom task.
   * It uses the AntBuilder to create the pom file and then compares it to an expected pom file.
   * The expected pom file is created using the MavenProject class.
   */
  @Test
  void testCreateDependencyPom() {

    AntBuilder ant = new AntBuilder()
    ant.with {

      path(id: 'libpath') {
        pathelement location: "out/main"
      }

      typedef(name:"dependencyManagement", classname:"se.alipsa.uso.types.DependencyManagement")
      taskdef(name: 'createPom', classname: 'se.alipsa.uso.tasks.CreatePom', classpathref: 'libpath')
      typedef(name:"dependency", classname:"org.apache.maven.resolver.internal.ant.types.Dependency")
      typedef(name:"dependencies", classname:"org.apache.maven.resolver.internal.ant.types.Dependencies")
      antProject.setProperty('groupId', 'se.alipsa.uso')
      antProject.setProperty('artifactId', 'CreateDependencyPomTest')
      antProject.setProperty('version', '1.0.0')
      dependencyManagement(id: 'dm') {
        dependencies {
          dependency(groupId: 'org.junit', artifactId: 'junit-bom', version:'5.12.2', type: 'pom', scope: 'import')
        }
      }
      dependencies(id: 'compile') {
        dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test')
        dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', scope: 'test')
      }
      def outDir = new File("out/testClasses")
      if (!outDir.exists()) {
        outDir.mkdirs()
      }
      def pomFile = new File(outDir, "${antProject.getProperty('artifactId')}-${antProject.getProperty('version')}.pom")
      createPom(pomTarget: pomFile, dependenciesRef: 'compile', dependencyManagementRef: 'dm')
      def content = pomFile.text
      Assertions.assertEquals('''\
        |<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        |    <modelVersion>4.0.0</modelVersion>
        |    <groupId>se.alipsa.uso</groupId>
        |    <artifactId>CreateDependencyPomTest</artifactId>
        |    <version>1.0.0</version>
        |    <packaging>jar</packaging>
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
        |            <type>jar</type>
        |            <scope>test</scope>
        |        </dependency>
        |        <dependency>
        |            <groupId>org.junit.platform</groupId>
        |            <artifactId>junit-platform-launcher</artifactId>
        |            <type>jar</type>
        |            <scope>test</scope>
        |        </dependency>
        |    </dependencies>
        |</project>
        '''.trim().stripMargin(), content.trim())
    }
  }

  @Test
  void testCreatePom() {

    AntBuilder ant = new AntBuilder()
    ant.with {

      path(id: 'libpath') {
        pathelement location: "out/main"
      }

      typedef(name:"dependencyManagement", classname:"se.alipsa.uso.types.DependencyManagement")
      taskdef(name: 'createPom', classname: 'se.alipsa.uso.tasks.CreatePom', classpathref: 'libpath')
      typedef(name:"dependency", classname:"org.apache.maven.resolver.internal.ant.types.Dependency")
      typedef(name:"dependencies", classname:"org.apache.maven.resolver.internal.ant.types.Dependencies")

      antProject.setProperty('groupId', 'se.alipsa.uso')
      antProject.setProperty('artifactId', 'CreatePomTest')
      antProject.setProperty('version', '1.0.0')
      dependencyManagement(id: 'dm') {
        dependencies {
          dependency(groupId: 'org.junit', artifactId: 'junit-bom', version:'5.12.2', type: 'pom', scope: 'import')
        }
      }
      dependencies(id: 'compile') {
        dependency(groupId:'org.junit.jupiter', artifactId: 'junit-jupiter-engine', scope: 'test')
        dependency(groupId: 'org.junit.platform', artifactId:'junit-platform-launcher', scope: 'test')
      }
      def outDir = new File("out/testClasses")
      if (!outDir.exists()) {
        outDir.mkdirs()
      }
      def pomFile = new File(outDir, "${antProject.getProperty('artifactId')}-${antProject.getProperty('version')}.pom")
      createPom(pomTarget: pomFile, dependenciesRef: 'compile', dependencyManagementRef: 'dm',
          name: 'publish-example', description: "A simple example of a publishable library"){
        licenses {
          license {
            name("The Apache Software License, Version 2.0")
            url("http://www.apache.org/licenses/LICENSE-2.0.txt")
            distribution("repo")
          }
          license {
            name("MIT")
            url("https://opensource.org/license/mit")
          }
        }
        repositories {
          repository(id:'jitpack.io', url: 'https://jitpack.io')
        }
        developers {
          developer (
              name: 'Per Nyfelt',
              email: 'per.nyfelt@alipsa.se',
              organization: 'Alipsa HB',
              organizationUrl: 'http://www.alipsa.se'
          )
        }
        scm (
            connection: 'scm:git:https://github.com/Alipsa/journo.git',
            developerConnection: 'scm:git:https://github.com/Alipsa/journo.git',
            url: 'https://github.com/Alipsa/journo/tree/main'
        )
      }
      def content = pomFile.text
      def expected = ('''\
        |<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0">
        |  <modelVersion>4.0.0</modelVersion>
        |  <groupId>se.alipsa.uso</groupId>
        |  <artifactId>CreatePomTest</artifactId>
        |  <version>1.0.0</version>
        |  <packaging>jar</packaging>
        |  <name>publish-example</name>
        |  <description>A simple example of a publishable library</description>
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
        |      <type>jar</type>
        |      <scope>test</scope>
        |    </dependency>
        |    <dependency>
        |      <groupId>org.junit.platform</groupId>
        |      <artifactId>junit-platform-launcher</artifactId>
        |      <type>jar</type>
        |      <scope>test</scope>
        |    </dependency>
        |  </dependencies>
        |  <licenses>
        |  <license>
        |      <name>The Apache Software License, Version 2.0</name>
        |      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        |      <distribution>repo</distribution>
        |  </license>
          <license>
            name("MIT")
            url("https://opensource.org/license/mit")
          </license>
        </licenses>
        repositories {
          repository(id:'jitpack.io', url: 'https://jitpack.io')
        }
        developers {
          developer (
              name: 'Per Nyfelt',
              email: 'per.nyfelt@alipsa.se',
              organization: 'Alipsa HB',
              organizationUrl: 'http://www.alipsa.se\'
          )
        }
        scm (
            connection: 'scm:git:https://github.com/Alipsa/journo.git',
            developerConnection: 'scm:git:https://github.com/Alipsa/journo.git',
            url: 'https://github.com/Alipsa/journo/tree/main\'
        )
        |</project>
        '''.trim().stripMargin())

      MatcherAssert.assertThat(
          new WhitespaceStrippedSource(Input.from(content).build()),
          CompareMatcher.isIdenticalTo(new WhitespaceStrippedSource(Input.from(expected).build()))
      )
    }
  }
}