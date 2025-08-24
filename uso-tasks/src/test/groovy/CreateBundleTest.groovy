import groovy.ant.AntBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.util.zip.ZipFile
import java.util.zip.ZipInputStream

class CreateBundleTest {

  @Test
  void testCreateBundle() {
    AntBuilder ant = new AntBuilder()
    File pomFile = new File("out/test/pom.xml")
    pomFile.parentFile.mkdirs()
    File signedPomFile = new File(pomFile.parentFile, pomFile.name + ".asc")
    signedPomFile.text = "Fake sign info"
    File jarFile = new File("out/test/create-bundle-test-1.0.0.jar")
    jarFile.parentFile.mkdirs()
    jarFile.text = "This is a test jar file"
    File signedJarFile = new File(jarFile.parentFile, jarFile.name + ".asc")
    signedJarFile.text = "Fake sign info"

    ant.with {
      path(id: 'libpath') {
        pathelement location: "out/main"
      }

      //typedef(name: "pom", classname: "org.apache.maven.resolver.internal.ant.types.Pom")
      typedef(name: "artifact", classname: "org.apache.maven.resolver.internal.ant.types.Artifact")
      typedef(name: "artifacts", classname: "org.apache.maven.resolver.internal.ant.types.Artifacts")
      taskdef(name: 'createPom', classname: 'org.apache.maven.resolver.internal.ant.tasks.CreatePom', classpathref: 'libpath')
      taskdef(name: 'createBundle', classname: 'se.alipsa.uso.tasks.CreateBundle', classpathref: 'libpath')

      antProject.setProperty('groupId', 'test.alipsa.uso')
      antProject.setProperty('artifactId', 'create-bundle-test')
      antProject.setProperty('version', '1.0.0')
      createPom(pomTarget:pomFile, name:'create-bundle-test', description:"A test project for creating a bundle") {
        licenses {
          license {
            name("MIT")
            url("https://opensource.org/license/mit")
          }
        }

        developers {
          developer {
            name('Per Nyfelt')
          }
        }
        scm {
          url('https://github.com/pnyfelt/example3/tree/master' )
          connection('scm:git:https://github.com/pnyfelt/example3.git')
          developerConnection('scm:git:https://github.com/pnyfelt/example3.git')
        }
      }
      artifact(file:jarFile, type:"jar", id:"jar")
      artifacts(id: 'localArtifacts') {
        artifact refid: 'jar'
      }

      createBundle(bundleFile: "out/central-bundle.zip", artifactsref:"localArtifacts")
    }
    List<String> actualEntries
    try (ZipFile zip = new ZipFile(new File("out/central-bundle.zip"))) {
      actualEntries = zip.entries().collect { it.name }
    }
    List<String> expectedEntries = [
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.jar",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.jar.asc",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.jar.md5",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.jar.sha1",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.jar.sha256",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.pom",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.pom.asc",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.pom.md5",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.pom.sha1",
        "test/alipsa/uso/create-bundle-test/1.0.0/create-bundle-test-1.0.0.pom.sha256"
    ]
    expectedEntries.each {
      Assertions.assertTrue(actualEntries.contains(it), "Expected entry $it not found in zip, \nactual entries are \n${String.join('\n', actualEntries)}" )
    }
    assert actualEntries.size() == expectedEntries.size()
  }
}
