import org.junit.jupiter.api.Test
import se.alipsa.uso.model.MavenProject
import se.alipsa.uso.tasks.CreatePom

class PomModelTest {

  @Test
  void testCreatePom() {
    def project = new MavenProject()
    project.setGroupId('se.alipsa.uso')
    project.setArtifactId('uso-core')
    project.setVersion('0.0.1')
    project.setName('uso-core')
    project.setDescription('A core library for uso')
    project.addLicense(name: 'Apache License, Version 2.0', url: 'http://www.apache.org/licenses/LICENSE-2.0')

    project.addToDependencyManagement(groupId: 'se.alipsa.matrix', artifactId: 'matrix-bom', version: '2.2.0', type: 'pom', scope: 'import')
    project.addDependency(groupId:'se.alipsa.matrix', artifactId:'matrix-core')
    project.addDependency(groupId:'se.alipsa.matrix', artifactId:'matrix-csv')
    StringWriter sw = new StringWriter()
    project.toPom(sw)
    String pom = sw.toString()
    assert pom.contains('<groupId>se.alipsa.uso</groupId>')
    assert pom.contains('<artifactId>uso-core</artifactId>')
    assert pom.contains('<artifactId>matrix-bom</artifactId>')
    assert pom.contains('<dependencyManagement>')
    assert pom.contains('</dependencyManagement>')
    CreatePom.validatePomContent(pom, project.getSchemaLocation())

  }
}
