package test.alipsa.uso

import org.junit.jupiter.api.Test
import se.alipsa.uso.AntTargetBuilder

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
    targetBuilder.dependencies('compile') {
      dependency('se.alipsa.matrix:matrix-bom:2.2.0', type: 'pom')
      dependency('se.alipsa.matrix:matrix-core')
      dependency('se.alipsa.matrix:matrix-csv')
      dependency('org.apache.maven.resolver:maven-resolver-ant-tasks:1.5.2')
    }
    def compileDeps = targetBuilder.declaredDependencies.compile.dependencies
    assert compileDeps.size() == 4
    assert compileDeps[0].dependencyId == 'se.alipsa.matrix:matrix-bom:2.2.0'
    assert compileDeps[0].type == 'pom'
    assert compileDeps[1].dependencyId == 'se.alipsa.matrix:matrix-core'
    assert compileDeps[1].type == null
  }

  @Test
  void testQuiet() {
    def file = new File("testQuiet.txt")
    if (file.exists()) {
      file.delete()
    }
    def p = new AntTargetBuilder("se.alipsa.uso", "testQuiet", "1.0")
    p.target('testQuiet') {
      p.echo(file:file, message:"Hello from testQuiet!")
      int level = p.setOutputLevel(0)
        p.echo(file:file, message:"This should not be printed", append: true)
      p.setOutputLevel(level)
      p.echo(file:file, message:"Hello again from testQuiet!", append: true)
    }
    p.execute('testQuiet')
    assert file.exists()
    assert file.text == "Hello from testQuiet!Hello again from testQuiet!"
    //println "File content: ${file.text}"
  }
}