import groovy.ant.AntBuilder
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

class LayoutTest {

  @Test
  void testLayout() {
    AntBuilder ant = new AntBuilder()
    ant.with {
      taskdef(name: "layout", classname: "se.alipsa.uso.tasks.Layout")
      layout(id: 'layout', dir: 'out/mvnGroovy', type: 'maven', language:'groovy')
    }
    def mainSrcDir = new File('out/mvnGroovy/src/main/groovy')
    assertTrue mainSrcDir.exists() && mainSrcDir.isDirectory()
    assertTrue(ant.antProject.getProperty('testResourcesDir').contains('out/mvnGroovy/src/test/resources'),
        'Failed to find a property for testResourcesDir')
  }

  @Test
  void ExternalBuildScriptTest() {
    AntBuilder ant = new AntBuilder()
    File buildScript = new File('src/test/resources/build.groovy')
    GroovyScriptEngineImpl gse = new GroovyScriptEngineImpl()
    gse.put('project', ant)
    gse.eval(buildScript.text)
  }
}