import groovy.ant.AntBuilder
import org.junit.jupiter.api.Test

class ModularGroovydocTest {

  @Test
  void testModularGroovyDoc() {
    AntBuilder ant = new AntBuilder()
    ant.with {
      taskdef(name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc", classpath: System.getProperty('java.class.path'))
      groovydoc (
          destdir: 'out/regulardoc',
          sourcepath: 'src/main/groovy',
          packagenames: '*'
      ) {
        link(packages: 'java.', href: 'https://docs.oracle.com/en/java/javase/21/docs/api/')
      }
      taskdef(name: "modularGroovydoc", classname: "se.alipsa.uso.tasks.ModularGroovydoc", classpath: System.getProperty('java.class.path'))
      modularGroovydoc (
          destdir: 'out/modulardoc',
          sourcepath: 'src/main/groovy',
          packagenames: '*'
      ) {
        link(packages: 'java.', href: 'https://docs.oracle.com/en/java/javase/21/docs/api/', module: 'java.base')
      }

    }
  }
}
