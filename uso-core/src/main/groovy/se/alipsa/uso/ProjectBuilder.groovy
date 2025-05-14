package se.alipsa.uso
import groovy.ant.AntBuilder
import org.apache.tools.ant.DefaultLogger

abstract class ProjectBuilder extends AntBuilder {
  String groupId
  String artifactId
  String version
  String defaultTarget
  String baseDir

  ProjectBuilder() {
    super()
    this.baseDir = System.getProperty("user.dir")
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

  String getBaseDir() {
    return baseDir
  }

  void setBaseDir(String baseDir) {
    this.baseDir = baseDir
  }

  String getGroupId() {
    return groupId
  }

  void setGroupId(String groupId) {
    this.groupId = groupId
    antProject.setProperty('groupId', groupId)
  }

  String getArtifactId() {
    return artifactId
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
    antProject.setProperty('artifactId', artifactId)
    project.name = artifactId
  }

  String getVersion() {
    return version
  }

  void setVersion(String version) {
    this.version = version
    antProject.setProperty('version', version)
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