package se.alipsa.uso.tasks

import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.apache.maven.resolver.internal.ant.types.Dependency
import org.apache.maven.resolver.internal.ant.types.Pom
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import org.apache.tools.ant.types.DataType
import se.alipsa.uso.model.MavenProject
import se.alipsa.uso.model.Model
import se.alipsa.uso.model.RepositoryPolicy
import se.alipsa.uso.types.DependencyManagement

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

/**
 * Creates a Maven pom file with the specified dependencies and dependency management.
 * The pom file is created in the specified target directory. The pom() task is also called
 * to register the pom file in the project.
 * Usage:
 * <pre><code>
 *  createPom(pomTarget: pomFile, dependenciesRef: 'compile',
 *    dependencyManagementRef: 'dm' name: 'publish-example',
 *    description: "A simple example") {
 *
 *    licenses {
 *      license (name:"MIT", url:"https://opensource.org/license/mit")
 *    }
 *    repositories {
 *      repository(id:'jitpack.io', url: 'https://jitpack.io')
 *    }
 *    developers {
 *      developer (
 *        name: 'Per Nyfelt',
 *        email: 'per.nyfelt@alipsa.se',
 *        organization: 'Alipsa HB',
 *        organizationUrl: 'http://www.alipsa.se'
 *      )
 *    }
 *    scm (
 *      connection: 'scm:git:https://github.com/x/example.git',
 *      developerConnection: 'scm:git:https://github.com/x/example.git',
 *      url: 'https://github.com/Alipsa/x/tree/main'
 *    )
 *  }
 * </code></pre>
 * The only required attributes are pomTarget. groupId, artifactId and version should have been set in the build script
 * already but can be overridden here. The pom file will be created in the specified target directory.
 * @since 0.0.1
 */
class CreatePom extends Task {

  private String dependenciesRef
  private String dependencyManagementRef
  private File pomFile
  private String groupId
  private String artifactId
  private String version
  private String name
  private String description
  private Licenses licenses
  private Repositories repositories
  private Developers developers
  private Scm scm

  void setPomTarget(String pomTarget) {
    pomFile = new File(pomTarget)
  }
  void setPomTarget(File pomTarget) {
    pomFile = pomTarget
  }

  void setDependencyManagementRef(String dependencyManagementRef) {
    this.dependencyManagementRef = dependencyManagementRef
  }

  void setDependenciesRef(String dependenciesRef) {
    this.dependenciesRef = dependenciesRef
  }

  void setGroupId(String groupId) {
    this.groupId = groupId
  }

  void setArtifactId(String artifactId) {
    this.artifactId = artifactId
  }

  void setVersion(String version) {
    this.version = version
  }

  void setName(String name) {
    this.name = name
  }

  String getName() {
    return name
  }

  void setDescription(String description) {
    this.description = description
  }

  String getGroupId() {
    if (groupId == null || groupId == 'null') {
      return project.getProperty('groupId')
    }
    return groupId
  }

  String getArtifactId() {
    if (artifactId == null || artifactId == 'null') {
      return project.getProperty('artifactId')
    }
    return artifactId
  }

  String getVersion() {
    if (version == null || version == 'null') {
      return project.getProperty('version')
    }
    return version
  }

  String getDescription() {
    return description ?: super.getDescription()
  }

  void addLicenses(Licenses licenses) {
    this.licenses = licenses
  }

  void addRepositories(Repositories repositories) {
    this.repositories = repositories
  }

  void addDevelopers(Developers developers) {
    this.developers = developers
  }

  void addScm(Scm scm) {
    this.scm = scm
  }


  @Override
  void execute() {
    log("createPom: groupId: ${getGroupId()} artifactId: ${getArtifactId()} version: ${getVersion()}", Project.MSG_VERBOSE)
    log("licenses: ${licenses}, repositories: ${repositories}, developers: ${developers}, scm: ${scm}", Project.MSG_VERBOSE)
    if(!pomFile.getParentFile().exists()) {
      pomFile.getParentFile().mkdirs()
    }
    MavenProject pom = new MavenProject()
    pom.setGroupId(getGroupId())
    pom.setArtifactId(getArtifactId())
    pom.setVersion(getVersion())
    pom.setName(getName())
    pom.setDescription(getDescription())

    if (dependencyManagementRef != null) {
      def dependencyManagement = DependencyManagement.get(project, dependencyManagementRef)
      appendManagedDependencies(dependencyManagement.getDependencies(), pom)
    }
    def dependencies = new Dependencies()
    dependencies.setProject(project)
    def ref = new org.apache.tools.ant.types.Reference(project, dependenciesRef)
    dependencies.setRefid(ref)

    appendDependencies(dependencies, pom)

    if (licenses != null ) {
      licenses.getLicenses().each { license ->
        pom.addLicense(license.toMap())
      }
    }
    if (developers != null) {
      if (pom.model.getDevelopers() == null) {
        pom.model.setDevelopers(new Model.Developers())
      }
      developers.getDevelopers().each { developer ->
        def dev = new se.alipsa.uso.model.Developer()
        dev.setName(developer.name)
        dev.setEmail(developer.email)
        dev.setOrganization(developer.organization)
        dev.setOrganizationUrl(developer.organizationUrl)
        pom.model.getDevelopers().developer.add(dev)
      }
    }

    if (scm != null) {
      def scmModel = new se.alipsa.uso.model.Scm()
      scmModel.setConnection(scm.connection)
      scmModel.setDeveloperConnection(scm.developerConnection)
      scmModel.setUrl(scm.url)
      pom.model.setScm(scmModel)
    }

    if (repositories != null) {
      if (pom.model.getRepositories() == null) {
        pom.model.setRepositories(new Model.Repositories())
      }
      repositories.getRepositories().each { repository ->
        def repo = new se.alipsa.uso.model.Repository()
        repo.setId(repository.id)
        if (repository.name) repo.setName(repository.name)
        repo.setUrl(repository.url)
        if (repository.layout) repo.setLayout(repository.layout)
        if (repository.releases) {
          def policy = new RepositoryPolicy()
          policy.setEnabled(repository.releases)
          repo.setReleases(policy)
        }
        if (repository.snapshots) {
          def policy = new RepositoryPolicy()
          policy.setEnabled(repository.snapshots)
          repo.setSnapshots(policy)
        }
        pom.model.getRepositories().repository.add(repo)
      }
    }

    try (FileWriter fw = new FileWriter(pomFile)) {
      pom.toPom(fw)
    } catch (IOException e) {
      throw new BuildException("Failed to create pom file: ${e.message}", e)
    }
    log("Created pom file ${pomFile.canonicalPath}", Project.MSG_VERBOSE)
    Map<String, Class<?>> taskDefs = getProject().getTaskDefinitions()
    if (!taskDefs.containsKey('pom')) {
      try {
        project.addTaskDefinition('pom', Pom)
      } catch (ClassNotFoundException e) {
        log("Failed to load Pom class: ${e.message}, you need to call the pom task in your build script instead!")
      }
    }
    Pom pomType = getProject().createTask('pom') as Pom
    pomType.setProject(project)
    pomType.setFile(pomFile)
    pomType.execute()
    log("Created and registered the pom file.", Project.MSG_INFO)
  }

  static void appendDependencies(Dependencies dependencies, MavenProject pom) {
    dependencies.getDependencyContainers().each { container ->
      if (container instanceof Dependency) {
        def dep = (Dependency) container
        //echo "Dependency: $dep.groupId:$dep.artifactId:$dep.version:$dep.type:${dep.classifier ?: ''}:$dep.scope"
        pom.addDependency(toMap(dep))
      }
    }
  }

  static void appendManagedDependencies(Dependencies dependencies, MavenProject mavenProject) {
    dependencies.getDependencyContainers().each {
      if (it instanceof Dependency) {
        def dep = (Dependency) it
        //echo "Dependency: $dep.groupId:$dep.artifactId:$dep.version:$dep.type:${dep.classifier ?: ''}:$dep.scope"
        mavenProject.addToDependencyManagement(toMap(dep))
      }
    }
  }

  static Map<String, String> toMap(Dependency dep) {
    Map<String, String> params = [:]
    if (dep.groupId) {
      params.put('groupId', dep.groupId)
    }
    if (dep.artifactId) {
      params.put('artifactId', dep.artifactId)
    }
    if (dep.version) {
      params.put('version', dep.version)
    }
    if (dep.classifier) {
      params.put('classifier', dep.classifier)
    }
    if (dep.type) {
      params.put('type', dep.type)
    }
    if (dep.scope) {
      params.put('scope', dep.scope)
    }
    return params
  }

  static void validatePomContent(String content, String schemaLocation) {
    try {
      def xsdLocation = new URI(schemaLocation).toURL()

      SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI)
          .newSchema(xsdLocation)
          .newValidator()
          .validate(new StreamSource(new StringReader(content)))
    } catch (Exception e) {
      throw new BuildException("Failed to validate pom: ${e.message}", e)
    }
  }

  static class Licenses extends DataType {

    private List<License> licenses = []

    List<License> getLicenses() {
      return licenses
    }

    void addLicense(License license) {
      if (license == null) {
        throw new IllegalArgumentException("License cannot be null")
      }
      licenses << license
    }
  }

  static class License extends DataType {
    String name
    String url
    String comments
    String distribution

    License() {
      super()
    }

    void addName(Name name) {
      this.name = name.name
    }

    void addUrl(Url url) {
      this.url = url.url
    }

    void addComments(Comments comments) {
      this.comments = comments.comments
    }

    void addDistribution(Distribution distribution) {
      this.distribution = distribution.distribution
    }

    Map toMap() {
      Map params = [:]
      if (name) {
        params.put('name', name)
      }
      if (url) {
        params.put('url', url)
      }
      if (comments) {
        params.put('comments', comments)
      }
      if (distribution) {
        params.put('distribution', distribution)
      }
      params
    }

    static class Name {
      String name
      Name() {}
      void addText(String name) {
        this.name = name
      }
    }

    static class Url {
      String url
      Url() {}
      void addText(String url) {
        this.url = url
      }
    }

    static class Comments {
      String comments
      Comments() {}
      void addText(String comments) {
        this.comments = comments
      }
    }

    static class Distribution {
      String distribution
      Distribution() {}
      void addText(String distribution) {
        this.distribution = distribution
      }
    }
  }

  static class Developers extends DataType {
    private List<Developer> developers = []

    List<Developer> getDevelopers() {
      return developers
    }

    void addDeveloper(Developer developer) {
      if (developer == null) {
        throw new IllegalArgumentException("Developer cannot be null")
      }
      developers << developer
    }
  }

  static class Developer extends DataType {
    String name
    String email
    String organization
    String organizationUrl

    Developer() {
      super()
    }

    void setName(String name) {
      this.name = name
    }

    void setEmail(String email) {
      this.email = email
    }

    void setOrganization(String organization) {
      this.organization = organization
    }

    void setOrganizationUrl(String organizationUrl) {
      this.organizationUrl = organizationUrl
    }

    @Override
    String toString() {
      return "Developer(name: $name, email: $email, organization: $organization, organizationUrl: $organizationUrl)"
    }
  }

  static class Repositories extends DataType {

    private List<Repository> repositories = []

    List<Repository> getRepositories() {
      return repositories
    }

    void addRepository(Repository repository) {
      if (repository == null) {
        throw new IllegalArgumentException("Repository cannot be null")
      }
      repositories << repository
    }
  }

  static class Repository extends DataType {
    String id
    String name
    String url
    String layout
    String releases // actually a boolean
    String snapshots // actually a boolean

    void setId(String id) {
      this.id = id
    }

    void setName(String name) {
      this.name = name
    }

    void setUrl(String url) {
      this.url = url
    }

    void setLayout(String layout) {
      this.layout = layout
    }

    void setReleases(String releases) {
      this.releases = releases
    }

    void setSnapshots(String snapshots) {
      this.snapshots = snapshots
    }
  }

  static class Scm extends DataType {
    String connection
    String developerConnection
    String url

    void setConnection(String connection) {
      this.connection = connection
    }

    void setDeveloperConnection(String developerConnection) {
      this.developerConnection = developerConnection
    }

    void setUrl(String url) {
      this.url = url
    }
  }
}
