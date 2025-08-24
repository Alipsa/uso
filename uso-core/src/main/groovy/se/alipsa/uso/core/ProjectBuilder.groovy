package se.alipsa.uso.core
import groovy.ant.AntBuilder
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.DefaultLogger
import org.apache.tools.ant.Target
import org.codehaus.groovy.control.CompilationFailedException

import java.nio.file.Path

class ProjectBuilder extends AntBuilder {
  String groupId
  String artifactId
  String version
  String defaultTarget


  ProjectBuilder(String groupId, String artifactId, String version) {
    this()
    this.groupId = groupId
    this.artifactId = artifactId
    this.version = version
  }

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
    typedef(name:"dependencyManagement", classname:"org.apache.maven.resolver.internal.ant.types.DependencyManagement")
    typedef(name:"pom", classname:"org.apache.maven.resolver.internal.ant.types.Pom")
    taskdef(name:"resolve", classname:"org.apache.maven.resolver.internal.ant.tasks.Resolve")
    taskdef(name:"install", classname:"org.apache.maven.resolver.internal.ant.tasks.Install")
    taskdef(name:"deploy", classname:"org.apache.maven.resolver.internal.ant.tasks.Deploy")
    taskdef(name: 'createPom', classname: 'org.apache.maven.resolver.internal.ant.tasks.CreatePom')

    taskdef(name: 'validatePom', classname: 'se.alipsa.uso.tasks.ValidatePom')
    taskdef(name: 'layout', classname: 'se.alipsa.uso.tasks.Layout')
    taskdef(name: 'createBundle', classname: 'se.alipsa.uso.tasks.CreateBundle')
    taskdef(name: 'publishToCentral', classname: 'se.alipsa.uso.tasks.PublishToCentral')
  }

  void target(Map<String, String> params, Closure closure) {
    Target antTarget = new Target()
    params.each {
      if (antTarget.hasProperty(it.key)) {
        antTarget[it.key] = it.value
      } else {
        project.log("Unknown property '${it.key}' for target '${params.name}'", 1)
      }
    }
    antTarget.project = project
    antTarget.addTask(new ClosureTask(antTarget, closure))
    project.addTarget(antTarget)
    antTarget
  }

  void target(String name, Closure closure) {
    target(name: name, closure)
  }

  void target(String name, String depends, Closure closure) {
    target(name: name, depends: depends, closure)
  }

  Map<String, Target> getTargets() {
    Map<String, Target> targetMap = [:]
    project.getTargets().each { k, v ->
      targetMap.put(String.valueOf(k),  v as Target)
    }
    targetMap
  }

  def execute(List<String> targets) {
    //try {
    if (targets.size() == 0) {
      if (defaultTarget == null) {
        throw new IllegalArgumentException("No target specified and no default target set.")
      }
      project.executeTarget(defaultTarget)
    }
    if (targets.size() == 1) {
      project.executeTarget(targets[0])
    } else {
      project.executeTargets(targets as Vector)
    }
  }

  def execute() {
    if (defaultTarget == null) {
      throw new IllegalArgumentException("No target specified and no default target set.")
    }
    execute(defaultTarget)
  }
  def execute(String targetName) {
    execute([targetName])
  }

  String getName() {
    return antProject.name ?: getCoords()
  }

  void setName(String name) {
    antProject.setName(name)
  }

  String getCoords() {
    "$groupId:$artifactId:$version"
  }

  String getDefaultTarget() {
    return defaultTarget ?: antProject.getDefaultTarget()
  }

  void setDefaultTarget(String defaultTarget) {
    this.defaultTarget = defaultTarget
    antProject.setDefault(defaultTarget)
  }

  String getGroupId() {
    return groupId ?: property('groupId')
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
    return artifactId ?: property("artifactId")
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
    property('artifactId', artifactId)
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
    antProject.setBaseDir(baseDir)
    property('basedir', baseDir.canonicalPath)
  }

  /**
   * The basedir of the project determines the base directory for the project.
   * It is used to resolve relative paths in the project.
   * Note: In Ant the baseDir is a File whereas the basedir is a String but both refer to the same
   * property.
   */
  void setBasedir(String basedir) {
    antProject.setBasedir(basedir)
    property('basedir', basedir)
  }

  String getBasedir() {
    return antProject.getBaseDir()?.canonicalPath
  }

  File getBaseDir() {
    return antProject.getBaseDir()
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
    DefaultLogger listener = antProject.getBuildListeners().find{
     it instanceof DefaultLogger
    } as DefaultLogger
    return listener.getMessageOutputLevel()
  }

  void uso(Map params) {
    if (params.file == null) {
      throw new IllegalArgumentException("File parameter is required")
    }
    File file
    if (!(params.file instanceof File)) {
      file = new File(String.valueOf(params.file))
    } else {
      file = params.file as File
    }
    uso(file, params.target as String, params.unless as String)
  }

  void uso(String file, String target = null, String unless = null) {
    uso(new File(file), target, unless)
  }

  /**
   * This is more or less equivalent to the ant task i.e. it enables an uso build to execute another build.
   * This is useful for e.g. multimodule projects.
   *
   * @param file the project build file to run, e.g. subprojectA/build.groovy
   * @param unless a property that if set will prevent the project from running.
   * @param target the target to run or a comma separated list of targets if multiple targets are to be run.
   */
  void uso(File file, String target = null, String unless = null) {
    Path first = antProject.baseDir?.canonicalFile?.toPath()
    Path second = file.canonicalFile?.toPath()

    if (first == null || second == null) {
      throw new BuildException("Base project path is $first, target project file is $second, neither of them can be null")
    }
    String msg = "* uso ${antProject.baseDir.name}/${first.relativize(second)} $target *".toString()
    println('')
    println('*'.repeat(msg.length()))
    println(msg)
    println('*'.repeat(msg.length()))
    if (property(unless)) {
      println("the 'unless' property $unless is '${property(unless)}' skipping further execution.")
      return
    }
    if (file == null || !file.exists()) {
      throw new BuildException("File does not exist: ${file?.canonicalPath}")
    }
    def binding = new Binding()
    ProjectBuilder projectBuilder = new ProjectBuilder()
    binding.setVariable("project", projectBuilder)
    projectBuilder.antProject.addBuildListener(new ErrorReportingListener(file.name))

    GroovyShell shell = new GroovyShell(binding)
    def codeSource = new GroovyCodeSource(file.text, file.name, file.absoluteFile.getParent())
    def compiledScript
    File baseDir = file.getParentFile()
    binding.getVariable('project').antProject.setBaseDir(baseDir)
    try {
      compiledScript = shell.parse(codeSource)
      compiledScript.run()
    } catch (CompilationFailedException e) {
      println "Syntax error in build script ${file.canonicalPath}:"
      println "  Message: ${e.message}"
      System.exit(1)
    } catch (IOException e) {
      println "Error reading build script: ${e.toString()}"
      System.exit(1)
    } catch (Exception e) {
      println "Error evaluating build script: ${e.toString()}"
      System.exit(1)
    }
    if (target == null) {
      // do nothing
    } else if (!target.contains(',')) {
      binding.getVariable('project').execute(target)
    } else {
      binding.getVariable('project').execute(target.split(',').collect { it.trim() })
    }
  }
}