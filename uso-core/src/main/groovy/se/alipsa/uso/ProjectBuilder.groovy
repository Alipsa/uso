package se.alipsa.uso
import groovy.ant.AntBuilder
import org.apache.tools.ant.DefaultLogger

abstract class ProjectBuilder extends AntBuilder {
  String groupId
  String artifactId
  String version
  String defaultTarget


  ProjectBuilder() {
    super()
    //basedir = System.getProperty("user.dir")
    taskdef(name: 'groovyc', classname: 'org.codehaus.groovy.ant.Groovyc')
    //taskdef(uri:"antlib:org.apache.maven.resolver.ant", resource:"org/apache/maven/resolver/ant/antlib.xml")

    typedef(name:"authentication", classname:"org.apache.maven.resolver.internal.ant.types.Authentication")
    typedef(name:"proxy", classname:"org.apache.maven.resolver.internal.ant.types.Proxy")
    typedef(name:"mirror", classname:"org.apache.maven.resolver.internal.ant.types.Mirror")
    typedef(name:"localrepo", classname:"org.apache.maven.resolver.internal.ant.types.LocalRepository")
    typedef(name:"remoterepo", classname:"org.apache.maven.resolver.internal.ant.types.RemoteRepository")
    typedef(name:"remoterepos", classname:"org.apache.maven.resolver.internal.ant.types.RemoteRepositories")
    typedef(name:"dependency", classname:"org.apache.maven.resolver.internal.ant.types.Dependency")
    typedef(name:"dependencies", classname:"org.apache.maven.resolver.internal.ant.types.Dependencies")
    typedef(name:"artifact", classname:"org.apache.maven.resolver.internal.ant.types.Artifact")
    typedef(name:"artifacts", classname:"org.apache.maven.resolver.internal.ant.types.Artifacts")
    typedef(name:"settings", classname:"org.apache.maven.resolver.internal.ant.types.Settings")
    taskdef(name:"resolve", classname:"org.apache.maven.resolver.internal.ant.tasks.Resolve")
    taskdef(name:"install", classname:"org.apache.maven.resolver.internal.ant.tasks.Install")
    taskdef(name:"deploy", classname:"org.apache.maven.resolver.internal.ant.tasks.Deploy")
    taskdef(name:"pom", classname:"org.apache.maven.resolver.internal.ant.types.Pom")

    typedef(name:"dependencyManagement", classname:"se.alipsa.uso.types.DependencyManagement")
    taskdef(name: 'createPom', classname: 'se.alipsa.uso.tasks.CreatePom')
    taskdef(name: 'layout', classname: 'se.alipsa.uso.tasks.Layout')
  }

  abstract void target(Map<String, String> params, Closure closure)
  abstract void target(String name, Closure closure)
  abstract void target(String name, String depends, Closure closure)

  def execute() {
    if (defaultTarget == null) {
      throw new IllegalArgumentException("No target specified and no default target set.")
    }
    execute(defaultTarget)
  }
  def execute(String targetName) {
    execute([targetName])
  }
  abstract def execute(List<String> targets)

  abstract Map<String, ?> getTargets()

  String getName() {
    return "$groupId:$artifactId:$version"
  }

  String getDefaultTarget() {
    return defaultTarget
  }

  void setDefaultTarget(String defaultTarget) {
    this.defaultTarget = defaultTarget
    project.setDefault(defaultTarget)
  }

  String getGroupId() {
    return groupId
  }

  void setGroupId(String groupId) {
    this.groupId = groupId
    property('groupId', groupId)
  }

  /**
   * Sets a property with the given name and value.
   *
   * @param name  the name of the property to set
   * @param value the value of the property
   */
  void property(String name, String value) {
    antProject.setProperty(name, value)
  }

  /**
   * Returns the value of a property with the given name.
   * If the property is not set, it returns null.
   *
   * @param name the name of the property to retrieve
   * @return the value of the property, or null if not set
   */
  String '$'(String name) {
    return property(name)
  }

  /**
   * Returns the value of a property with the given name.
   * If the property is not set, it returns null.
   *
   * @param name the name of the property to retrieve
   * @return the value of the property, or null if not set
   */
  String property(String name) {
    return antProject.getProperty(name)
  }

  /**
   * The artifactId of the project determines the name of the artifact when published.
   *
   * @return The artifactId of the project.
   */
  String getArtifactId() {
    return artifactId
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
    property('artifactId', artifactId)
    project.name = artifactId
  }

  String getVersion() {
    return version
  }

  void setVersion(String version) {
    this.version = version
    property('version', version)
  }

  /**
   * The baseDir of the project determines the base directory for the project.
   * It is used to resolve relative paths in the project.
   * Note: In Ant the baseDir is a File whereas the basedir is a String but both refer to the same
   * property.
   */
  void setBaseDir(File baseDir) {
    project.setBaseDir(baseDir)
    property('basedir', baseDir.canonicalPath)
  }

  /**
   * The basedir of the project determines the base directory for the project.
   * It is used to resolve relative paths in the project.
   * Note: In Ant the baseDir is a File whereas the basedir is a String but both refer to the same
   * property.
   */
  void setBasedir(String basedir) {
    project.setBasedir(basedir)
    property('basedir', basedir)
  }

  String getBasedir() {
    return project.getBaseDir()?.canonicalPath
  }

  File getBaseDir() {
    return project.getBaseDir()
  }

  /**
   * Sets the output level for the project. Levels are:
   * <pre>
   * 0 - off (errors only)
   * 1 - warnings and above i.e. show echo but supress build messages
   * 2 - info i.e. show build messages (the default)
   * 3 - verbose i.e. show all messages
   * 4 - debug i.e. show all messages including debug messages
   * </pre>
   *
   * @param level the output level to set
   * @return the previous output level
   */
  int setOutputLevel(Integer level) {
    DefaultLogger listener = project.getBuildListeners().find{
     it instanceof DefaultLogger
    } as DefaultLogger
    def oldLevel = listener.getMessageOutputLevel()
    listener.setMessageOutputLevel(level)
    return oldLevel
  }

  int getOutputLevel() {
    DefaultLogger listener = project.getBuildListeners().find{
     it instanceof DefaultLogger
    } as DefaultLogger
    return listener.getMessageOutputLevel()
  }
}