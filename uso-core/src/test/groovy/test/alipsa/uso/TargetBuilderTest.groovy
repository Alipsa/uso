package test.alipsa.uso

import org.junit.jupiter.api.Test
import se.alipsa.uso.TargetBuilder

class TargetBuilderTest {

  @Test
  void testTargetBuilder() {
    // Arrange
    def targetBuilder = new TargetBuilder()
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
    def targetBuilder = new TargetBuilder()
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
  void testDepends() {
    // Arrange
    def targetBuilder = new TargetBuilder()
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
}