import groovy.ant.AntBuilder
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
import org.junit.jupiter.api.Test
import se.alipsa.uso.tasks.Layout

import static org.junit.jupiter.api.Assertions.*

class LayoutTest {

  @Test
  void testLayoutDirectly() {
    AntBuilder ant = new AntBuilder()
    File outDir = new File('out/mvnJava')
    Layout layout = new Layout(dir: outDir.getAbsolutePath(), type: 'maven', language:'java', loglevel: 'WARN')
    layout.project = ant.antProject
    layout.execute()
    assertTrue(outDir.exists() && outDir.isDirectory(), "${outDir.getAbsolutePath()} directory not created")
    def mainSrcDir = new File(outDir,'src/main/java')
    assertTrue mainSrcDir.exists() && mainSrcDir.isDirectory()
    assertTrue(ant.antProject.getProperty('testResourcesDir').contains('out/mvnJava/src/test/resources'),
        'Failed to find a property for testResourcesDir')
  }

  @Test
  void testLayout() {
    AntBuilder ant = new AntBuilder()
    ant.with {
      taskdef(name: "layout", classname: "se.alipsa.uso.tasks.Layout")
      layout(id: 'layout', dir: 'out/mvnGroovy', type: 'maven', language:'groovy', loglevel: '3')
    }
    assertTrue(new File('out/mvnGroovy').exists() && new File('out/mvnGroovy').isDirectory(), "mvnGroovy directory not created")
    def mainSrcDir = new File('out/mvnGroovy/src/main/groovy')
    assertTrue mainSrcDir.exists() && mainSrcDir.isDirectory()
    assertTrue(ant.antProject.getProperty('testResourcesDir').contains('out/mvnGroovy/src/test/resources'),
        'Failed to find a property for testResourcesDir')
  }

  @Test
  void externalBuildScriptTest() {
    AntBuilder ant = new AntBuilder()
    File buildScript = new File(getClass().getResource('/build.groovy').toURI())
    assertTrue(buildScript.exists())
    GroovyScriptEngineImpl gse = new GroovyScriptEngineImpl()
    gse.put('project', ant)
    gse.eval(buildScript.text)
  }
}