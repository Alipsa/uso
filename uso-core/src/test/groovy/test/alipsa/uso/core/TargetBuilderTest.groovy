package test.alipsa.uso.core

import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import se.alipsa.uso.core.AntTargetBuilder

class TargetBuilderTest {

  @Test
  void testTargetBuilder() {
    // Arrange
    def targetBuilder = new AntTargetBuilder()
    targetBuilder.artifactId = "TargetBuilderTest"
    def targetName = "testTarget"

    // Act
    targetBuilder.target(targetName){ targetBuilder.echo "Hello from closure!" }

    // Assert
    assert targetBuilder.targets.containsKey(targetName)
    targetBuilder.execute([targetName])
  }

  @Test
  void testTargetsAreRegisteredProperly() {
    // Arrange
    def targetBuilder = new AntTargetBuilder()
    targetBuilder.artifactId = "TargetBuilderTest"
    def targetName1 = "testTarget1"
    def targetName2 = "testTarget2"

    // Act
    targetBuilder.target(targetName1){ targetBuilder.echo "Hello from closure 1!" }
    targetBuilder.target(targetName2){ targetBuilder.echo "Hello from closure 2!" }

    // Assert
    assert targetBuilder.targets.containsKey(targetName1)
    assert targetBuilder.targets.containsKey(targetName2)
  }

  @Test
  void testDependsTask() {
    // Arrange
    def targetBuilder = new AntTargetBuilder()
    targetBuilder.artifactId = "TargetBuilderTest"
    def initTarget = "init"
    def targetName1 = "testTarget1"
    def targetName2 = "testTarget2"

    // Act
    targetBuilder.target('hello') { targetBuilder.echo "Hello from hello!" }
    targetBuilder.target('init') { targetBuilder.echo "Hello from init!" }
    targetBuilder.target(name: targetName1, depends: 'init') { targetBuilder.echo "Hello from closure 1!" }
    targetBuilder.target(targetName2, "testTarget1, hello") { targetBuilder.echo "Hello from closure 2!" }

    // Assert
    targetBuilder.targets.each { targetName, target ->
      println "Target: $targetName depends ${Collections.list(target.dependencies)}"
    }
    List<String> t2Depends = Collections.list(targetBuilder.targets[targetName2].dependencies)
    println "t2Depends: $t2Depends"
    assert t2Depends.contains(targetName1)
    targetBuilder.execute([targetName1, targetName2])
  }

  @Test
  void testDependencies() {
    def targetBuilder = new AntTargetBuilder("se.alipsa.uso", "TargetBuilderTest", "1.0")
    targetBuilder.dependencies(id: 'compile') {
      dependency(coords: 'se.alipsa.matrix:matrix-core:3.2.0')
      dependency(coords: 'se.alipsa.matrix:matrix-csv:2.1.0')
      dependency(coords: 'org.apache.maven.resolver:maven-resolver-ant-tasks:1.5.2')
    }
    def dependencies = new Dependencies()
    dependencies.setProject(targetBuilder.antProject)
    def ref = new org.apache.tools.ant.types.Reference(targetBuilder.antProject, 'compile')
    dependencies.setRefid(ref)
    def compileDeps = dependencies.getDependencyContainers()
    assert compileDeps.size() == 3
    assert compileDeps[0].groupId == 'se.alipsa.matrix'
    assert compileDeps[1].artifactId == 'matrix-csv'
  }

  @Test
  void testQuiet() {
    PrintStream console = System.out
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); PrintStream ps = new PrintStream(baos)) {
      System.setOut(ps)
      def p = new AntTargetBuilder("se.alipsa.uso", "testQuiet", "1.0")
      p.target('testQuiet') {
        p.echo(message: "Hello from testQuiet!")
        int level = p.setOutputLevel(0)
        p.echo(message: "This should not be printed")
        p.setOutputLevel(level)
        p.echo(message: "Hello again from testQuiet!")
      }
      p.execute('testQuiet')
      System.setOut(console)
      String output = new String(baos.toByteArray(), "UTF-8")
      Assertions.assertEquals("""
      testQuiet:
           [echo] Hello from testQuiet!
           [echo] Hello again from testQuiet!
      """.stripIndent(), output)
    }
  }
}