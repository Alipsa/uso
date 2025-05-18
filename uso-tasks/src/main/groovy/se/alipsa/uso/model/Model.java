
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.*;
import org.w3c.dom.Element;


/**
 * The <code>&lt;project&gt;</code> element is the root of the descriptor.
 *         The following table lists all of the possible child elements.
 * 
 * <p>Java class for Model complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Model">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="modelVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="parent" type="{http://maven.apache.org/POM/4.0.0}Parent" minOccurs="0"/>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="packaging" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="inceptionYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="organization" type="{http://maven.apache.org/POM/4.0.0}Organization" minOccurs="0"/>
 *         <element name="licenses" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="license" type="{http://maven.apache.org/POM/4.0.0}License" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="developers" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="developer" type="{http://maven.apache.org/POM/4.0.0}Developer" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="contributors" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="contributor" type="{http://maven.apache.org/POM/4.0.0}Contributor" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="mailingLists" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="mailingList" type="{http://maven.apache.org/POM/4.0.0}MailingList" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="prerequisites" type="{http://maven.apache.org/POM/4.0.0}Prerequisites" minOccurs="0"/>
 *         <element name="modules" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="module" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="scm" type="{http://maven.apache.org/POM/4.0.0}Scm" minOccurs="0"/>
 *         <element name="issueManagement" type="{http://maven.apache.org/POM/4.0.0}IssueManagement" minOccurs="0"/>
 *         <element name="ciManagement" type="{http://maven.apache.org/POM/4.0.0}CiManagement" minOccurs="0"/>
 *         <element name="distributionManagement" type="{http://maven.apache.org/POM/4.0.0}DistributionManagement" minOccurs="0"/>
 *         <element name="properties" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="dependencyManagement" type="{http://maven.apache.org/POM/4.0.0}DependencyManagement" minOccurs="0"/>
 *         <element name="dependencies" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="dependency" type="{http://maven.apache.org/POM/4.0.0}Dependency" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="repositories" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="repository" type="{http://maven.apache.org/POM/4.0.0}Repository" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="pluginRepositories" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="pluginRepository" type="{http://maven.apache.org/POM/4.0.0}Repository" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="build" type="{http://maven.apache.org/POM/4.0.0}Build" minOccurs="0"/>
 *         <element name="reports" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="reporting" type="{http://maven.apache.org/POM/4.0.0}Reporting" minOccurs="0"/>
 *         <element name="profiles" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="profile" type="{http://maven.apache.org/POM/4.0.0}Profile" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </all>
 *       <attribute name="child.project.url.inherit.append.path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlRootElement(name = "project", namespace = "http://maven.apache.org/POM/4.0.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Model", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Model {

    /**
     * Declares to which version of project descriptor this POM conforms.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String modelVersion;
    /**
     * The location of the parent project, if one exists. Values from the parent
     *             project will be the default for this project if they are left unspecified. The location
     *             is given as a group ID, artifact ID and version.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Parent parent;
    /**
     * A universally unique identifier for a project. It is normal to
     *             use a fully-qualified package name to distinguish it from other
     *             projects with a similar name (eg. <code>org.apache.maven</code>).
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String groupId;
    /**
     * The identifier for this artifact that is unique within the group given by the
     *             group ID. An artifact is something that is either produced or used by a project.
     *             Examples of artifacts produced by Maven for a project include: JARs, source and binary
     *             distributions, and WARs.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The current version of the artifact produced by this project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;
    /**
     * The type of artifact this project produces, for example <code>jar</code>
     *               <code>war</code>
     *               <code>ear</code>
     *               <code>pom</code>.
     *             Plugins can create their own packaging, and
     *             therefore their own packaging types,
     *             so this list does not contain all possible types.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "jar")
    protected String packaging;
    /**
     * The full name of the project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * A detailed description of the project, used by Maven whenever it needs to
     *             describe the project, such as on the web site. While this element can be specified as
     *             CDATA to enable the use of HTML tags within the description, it is discouraged to allow
     *             plain text representation. If you need to modify the index page of the generated web
     *             site, you are able to specify your own instead of adjusting this text.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String description;
    /**
     * The URL to the project's homepage.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             project's <code>child.project.url.inherit.append.path="false"</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * The year of the project's inception, specified with 4 digits. This value is
     *             used when generating copyright notices as well as being informational.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String inceptionYear;
    /**
     * This element describes various attributes of the organization to which the
     *             project belongs. These attributes are utilized when documentation is created (for
     *             copyright notices and links).
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Organization organization;
    /**
     * This element describes all of the licenses for this project.
     *             Each license is described by a <code>license</code> element, which
     *             is then described by additional elements.
     *             Projects should only list the license(s) that applies to the project
     *             and not the licenses that apply to dependencies.
     *             If multiple licenses are listed, it is assumed that the user can select
     *             any of them, not that they must accept all.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Licenses licenses;
    /**
     * Describes the committers of a project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Developers developers;
    /**
     * Describes the contributors to a project that are not yet committers.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Contributors contributors;
    /**
     * Contains information about a project's mailing lists.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.MailingLists mailingLists;
    /**
     * Describes the prerequisites in the build environment for this project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Prerequisites prerequisites;
    /**
     * The modules (sometimes called subprojects) to build as a part of this
     *             project. Each module listed is a relative path to the directory containing the module.
     *             To be consistent with the way default urls are calculated from parent, it is recommended
     *             to have module names match artifact ids.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Modules modules;
    /**
     * Specification for the SCM used by the project, such as CVS, Subversion, etc.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Scm scm;
    /**
     * The project's issue management system information.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected IssueManagement issueManagement;
    /**
     * The project's continuous integration information.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected CiManagement ciManagement;
    /**
     * Distribution information for a project that enables deployment of the site
     *             and artifacts to remote web servers and repositories respectively.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected DistributionManagement distributionManagement;
    /**
     * Properties that can be used throughout the POM as a substitution, and
     *             are used as filters in resources if enabled.
     *             The format is <code>&lt;name&gt;value&lt;/name&gt;</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Properties properties;
    /**
     * Default dependency information for projects that inherit from this one. The
     *             dependencies in this section are not immediately resolved. Instead, when a POM derived
     *             from this one declares a dependency described by a matching groupId and artifactId, the
     *             version and other values from this section are used for that dependency if they were not
     *             already specified.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected DependencyManagement dependencyManagement;
    /**
     * This element describes all of the dependencies associated with a
     *             project.
     *             These dependencies are used to construct a classpath for your
     *             project during the build process. They are automatically downloaded from the
     *             repositories defined in this project.
     *             See <a href="https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html">the
     *             dependency mechanism</a> for more information.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Dependencies dependencies;
    /**
     * The lists of the remote repositories for discovering dependencies and
     *             extensions.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Repositories repositories;
    /**
     * The lists of the remote repositories for discovering plugins for builds and
     *             reports.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.PluginRepositories pluginRepositories;
    /**
     * Information required to build the project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build build;
    /**
     * <b>Deprecated</b>. Now ignored by Maven.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Reports reports;
    /**
     * This element includes the specification of report plugins to use
     *             to generate the reports on the Maven-generated site.
     *             These reports will be run when a user executes <code>mvn site</code>.
     *             All of the reports will be included in the navigation bar for browsing.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Reporting reporting;
    /**
     * A listing of project-local build profiles which will modify the build process
     *             when activated.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Model.Profiles profiles;
    /**
     * When children inherit from project's url, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     */
    @XmlAttribute(name = "child.project.url.inherit.append.path")
    protected String childProjectUrlInheritAppendPath;

    /**
     * Declares to which version of project descriptor this POM conforms.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelVersion() {
        return modelVersion;
    }

    /**
     * Sets the value of the modelVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getModelVersion()
     */
    public void setModelVersion(String value) {
        this.modelVersion = value;
    }

    /**
     * The location of the parent project, if one exists. Values from the parent
     *             project will be the default for this project if they are left unspecified. The location
     *             is given as a group ID, artifact ID and version.
     * 
     * @return
     *     possible object is
     *     {@link Parent }
     *     
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parent }
     *     
     * @see #getParent()
     */
    public void setParent(Parent value) {
        this.parent = value;
    }

    /**
     * A universally unique identifier for a project. It is normal to
     *             use a fully-qualified package name to distinguish it from other
     *             projects with a similar name (eg. <code>org.apache.maven</code>).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGroupId()
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

    /**
     * The identifier for this artifact that is unique within the group given by the
     *             group ID. An artifact is something that is either produced or used by a project.
     *             Examples of artifacts produced by Maven for a project include: JARs, source and binary
     *             distributions, and WARs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the value of the artifactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getArtifactId()
     */
    public void setArtifactId(String value) {
        this.artifactId = value;
    }

    /**
     * The current version of the artifact produced by this project.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVersion()
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * The type of artifact this project produces, for example <code>jar</code>
     *               <code>war</code>
     *               <code>ear</code>
     *               <code>pom</code>.
     *             Plugins can create their own packaging, and
     *             therefore their own packaging types,
     *             so this list does not contain all possible types.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * Sets the value of the packaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPackaging()
     */
    public void setPackaging(String value) {
        this.packaging = value;
    }

    /**
     * The full name of the project.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getName()
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * A detailed description of the project, used by Maven whenever it needs to
     *             describe the project, such as on the web site. While this element can be specified as
     *             CDATA to enable the use of HTML tags within the description, it is discouraged to allow
     *             plain text representation. If you need to modify the index page of the generated web
     *             site, you are able to specify your own instead of adjusting this text.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDescription()
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * The URL to the project's homepage.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             project's <code>child.project.url.inherit.append.path="false"</code>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUrl()
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * The year of the project's inception, specified with 4 digits. This value is
     *             used when generating copyright notices as well as being informational.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInceptionYear() {
        return inceptionYear;
    }

    /**
     * Sets the value of the inceptionYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getInceptionYear()
     */
    public void setInceptionYear(String value) {
        this.inceptionYear = value;
    }

    /**
     * This element describes various attributes of the organization to which the
     *             project belongs. These attributes are utilized when documentation is created (for
     *             copyright notices and links).
     * 
     * @return
     *     possible object is
     *     {@link Organization }
     *     
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organization }
     *     
     * @see #getOrganization()
     */
    public void setOrganization(Organization value) {
        this.organization = value;
    }

    /**
     * This element describes all of the licenses for this project.
     *             Each license is described by a <code>license</code> element, which
     *             is then described by additional elements.
     *             Projects should only list the license(s) that applies to the project
     *             and not the licenses that apply to dependencies.
     *             If multiple licenses are listed, it is assumed that the user can select
     *             any of them, not that they must accept all.
     * 
     * @return
     *     possible object is
     *     {@link Model.Licenses }
     *     
     */
    public Model.Licenses getLicenses() {
        return licenses;
    }

    /**
     * Sets the value of the licenses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Licenses }
     *     
     * @see #getLicenses()
     */
    public void setLicenses(Model.Licenses value) {
        this.licenses = value;
    }

    /**
     * Describes the committers of a project.
     * 
     * @return
     *     possible object is
     *     {@link Model.Developers }
     *     
     */
    public Model.Developers getDevelopers() {
        return developers;
    }

    /**
     * Sets the value of the developers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Developers }
     *     
     * @see #getDevelopers()
     */
    public void setDevelopers(Model.Developers value) {
        this.developers = value;
    }

    /**
     * Describes the contributors to a project that are not yet committers.
     * 
     * @return
     *     possible object is
     *     {@link Model.Contributors }
     *     
     */
    public Model.Contributors getContributors() {
        return contributors;
    }

    /**
     * Sets the value of the contributors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Contributors }
     *     
     * @see #getContributors()
     */
    public void setContributors(Model.Contributors value) {
        this.contributors = value;
    }

    /**
     * Contains information about a project's mailing lists.
     * 
     * @return
     *     possible object is
     *     {@link Model.MailingLists }
     *     
     */
    public Model.MailingLists getMailingLists() {
        return mailingLists;
    }

    /**
     * Sets the value of the mailingLists property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.MailingLists }
     *     
     * @see #getMailingLists()
     */
    public void setMailingLists(Model.MailingLists value) {
        this.mailingLists = value;
    }

    /**
     * Describes the prerequisites in the build environment for this project.
     * 
     * @return
     *     possible object is
     *     {@link Prerequisites }
     *     
     */
    public Prerequisites getPrerequisites() {
        return prerequisites;
    }

    /**
     * Sets the value of the prerequisites property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prerequisites }
     *     
     * @see #getPrerequisites()
     */
    public void setPrerequisites(Prerequisites value) {
        this.prerequisites = value;
    }

    /**
     * The modules (sometimes called subprojects) to build as a part of this
     *             project. Each module listed is a relative path to the directory containing the module.
     *             To be consistent with the way default urls are calculated from parent, it is recommended
     *             to have module names match artifact ids.
     * 
     * @return
     *     possible object is
     *     {@link Model.Modules }
     *     
     */
    public Model.Modules getModules() {
        return modules;
    }

    /**
     * Sets the value of the modules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Modules }
     *     
     * @see #getModules()
     */
    public void setModules(Model.Modules value) {
        this.modules = value;
    }

    /**
     * Specification for the SCM used by the project, such as CVS, Subversion, etc.
     * 
     * @return
     *     possible object is
     *     {@link Scm }
     *     
     */
    public Scm getScm() {
        return scm;
    }

    /**
     * Sets the value of the scm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scm }
     *     
     * @see #getScm()
     */
    public void setScm(Scm value) {
        this.scm = value;
    }

    /**
     * The project's issue management system information.
     * 
     * @return
     *     possible object is
     *     {@link IssueManagement }
     *     
     */
    public IssueManagement getIssueManagement() {
        return issueManagement;
    }

    /**
     * Sets the value of the issueManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssueManagement }
     *     
     * @see #getIssueManagement()
     */
    public void setIssueManagement(IssueManagement value) {
        this.issueManagement = value;
    }

    /**
     * The project's continuous integration information.
     * 
     * @return
     *     possible object is
     *     {@link CiManagement }
     *     
     */
    public CiManagement getCiManagement() {
        return ciManagement;
    }

    /**
     * Sets the value of the ciManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link CiManagement }
     *     
     * @see #getCiManagement()
     */
    public void setCiManagement(CiManagement value) {
        this.ciManagement = value;
    }

    /**
     * Distribution information for a project that enables deployment of the site
     *             and artifacts to remote web servers and repositories respectively.
     * 
     * @return
     *     possible object is
     *     {@link DistributionManagement }
     *     
     */
    public DistributionManagement getDistributionManagement() {
        return distributionManagement;
    }

    /**
     * Sets the value of the distributionManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionManagement }
     *     
     * @see #getDistributionManagement()
     */
    public void setDistributionManagement(DistributionManagement value) {
        this.distributionManagement = value;
    }

    /**
     * Properties that can be used throughout the POM as a substitution, and
     *             are used as filters in resources if enabled.
     *             The format is <code>&lt;name&gt;value&lt;/name&gt;</code>.
     * 
     * @return
     *     possible object is
     *     {@link Model.Properties }
     *     
     */
    public Model.Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Properties }
     *     
     * @see #getProperties()
     */
    public void setProperties(Model.Properties value) {
        this.properties = value;
    }

    /**
     * Default dependency information for projects that inherit from this one. The
     *             dependencies in this section are not immediately resolved. Instead, when a POM derived
     *             from this one declares a dependency described by a matching groupId and artifactId, the
     *             version and other values from this section are used for that dependency if they were not
     *             already specified.
     * 
     * @return
     *     possible object is
     *     {@link DependencyManagement }
     *     
     */
    public DependencyManagement getDependencyManagement() {
        return dependencyManagement;
    }

    /**
     * Sets the value of the dependencyManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link DependencyManagement }
     *     
     * @see #getDependencyManagement()
     */
    public void setDependencyManagement(DependencyManagement value) {
        this.dependencyManagement = value;
    }

    /**
     * This element describes all of the dependencies associated with a
     *             project.
     *             These dependencies are used to construct a classpath for your
     *             project during the build process. They are automatically downloaded from the
     *             repositories defined in this project.
     *             See <a href="https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html">the
     *             dependency mechanism</a> for more information.
     * 
     * @return
     *     possible object is
     *     {@link Model.Dependencies }
     *     
     */
    public Model.Dependencies getDependencies() {
        return dependencies;
    }

    /**
     * Sets the value of the dependencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Dependencies }
     *     
     * @see #getDependencies()
     */
    public void setDependencies(Model.Dependencies value) {
        this.dependencies = value;
    }

    /**
     * The lists of the remote repositories for discovering dependencies and
     *             extensions.
     * 
     * @return
     *     possible object is
     *     {@link Model.Repositories }
     *     
     */
    public Model.Repositories getRepositories() {
        return repositories;
    }

    /**
     * Sets the value of the repositories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Repositories }
     *     
     * @see #getRepositories()
     */
    public void setRepositories(Model.Repositories value) {
        this.repositories = value;
    }

    /**
     * The lists of the remote repositories for discovering plugins for builds and
     *             reports.
     * 
     * @return
     *     possible object is
     *     {@link Model.PluginRepositories }
     *     
     */
    public Model.PluginRepositories getPluginRepositories() {
        return pluginRepositories;
    }

    /**
     * Sets the value of the pluginRepositories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.PluginRepositories }
     *     
     * @see #getPluginRepositories()
     */
    public void setPluginRepositories(Model.PluginRepositories value) {
        this.pluginRepositories = value;
    }

    /**
     * Information required to build the project.
     * 
     * @return
     *     possible object is
     *     {@link Build }
     *     
     */
    public Build getBuild() {
        return build;
    }

    /**
     * Sets the value of the build property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build }
     *     
     * @see #getBuild()
     */
    public void setBuild(Build value) {
        this.build = value;
    }

    /**
     * <b>Deprecated</b>. Now ignored by Maven.
     * 
     * @return
     *     possible object is
     *     {@link Model.Reports }
     *     
     */
    public Model.Reports getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Reports }
     *     
     * @see #getReports()
     */
    public void setReports(Model.Reports value) {
        this.reports = value;
    }

    /**
     * This element includes the specification of report plugins to use
     *             to generate the reports on the Maven-generated site.
     *             These reports will be run when a user executes <code>mvn site</code>.
     *             All of the reports will be included in the navigation bar for browsing.
     * 
     * @return
     *     possible object is
     *     {@link Reporting }
     *     
     */
    public Reporting getReporting() {
        return reporting;
    }

    /**
     * Sets the value of the reporting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reporting }
     *     
     * @see #getReporting()
     */
    public void setReporting(Reporting value) {
        this.reporting = value;
    }

    /**
     * A listing of project-local build profiles which will modify the build process
     *             when activated.
     * 
     * @return
     *     possible object is
     *     {@link Model.Profiles }
     *     
     */
    public Model.Profiles getProfiles() {
        return profiles;
    }

    /**
     * Sets the value of the profiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model.Profiles }
     *     
     * @see #getProfiles()
     */
    public void setProfiles(Model.Profiles value) {
        this.profiles = value;
    }

    /**
     * When children inherit from project's url, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildProjectUrlInheritAppendPath() {
        return childProjectUrlInheritAppendPath;
    }

    /**
     * Sets the value of the childProjectUrlInheritAppendPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChildProjectUrlInheritAppendPath()
     */
    public void setChildProjectUrlInheritAppendPath(String value) {
        this.childProjectUrlInheritAppendPath = value;
    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="contributor" type="{http://maven.apache.org/POM/4.0.0}Contributor" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "contributor"
    })
    public static class Contributors {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Contributor> contributor;

        /**
         * Gets the value of the contributor property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contributor property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getContributor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Contributor }
         * </p>
         * 
         * 
         * @return
         *     The value of the contributor property.
         */
        public List<Contributor> getContributor() {
            if (contributor == null) {
                contributor = new ArrayList<>();
            }
            return this.contributor;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="dependency" type="{http://maven.apache.org/POM/4.0.0}Dependency" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dependency"
    })
    public static class Dependencies {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Dependency> dependency;

        /**
         * Gets the value of the dependency property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dependency property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getDependency().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dependency }
         * </p>
         * 
         * 
         * @return
         *     The value of the dependency property.
         */
        public List<Dependency> getDependency() {
            if (dependency == null) {
                dependency = new ArrayList<>();
            }
            return this.dependency;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="developer" type="{http://maven.apache.org/POM/4.0.0}Developer" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "developer"
    })
    public static class Developers {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Developer> developer;

        /**
         * Gets the value of the developer property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the developer property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getDeveloper().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Developer }
         * </p>
         * 
         * 
         * @return
         *     The value of the developer property.
         */
        public List<Developer> getDeveloper() {
            if (developer == null) {
                developer = new ArrayList<>();
            }
            return this.developer;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="license" type="{http://maven.apache.org/POM/4.0.0}License" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "license"
    })
    public static class Licenses {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<License> license;

        /**
         * Gets the value of the license property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the license property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getLicense().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link License }
         * </p>
         * 
         * 
         * @return
         *     The value of the license property.
         */
        public List<License> getLicense() {
            if (license == null) {
                license = new ArrayList<>();
            }
            return this.license;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="mailingList" type="{http://maven.apache.org/POM/4.0.0}MailingList" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mailingList"
    })
    public static class MailingLists {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<MailingList> mailingList;

        /**
         * Gets the value of the mailingList property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mailingList property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getMailingList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MailingList }
         * </p>
         * 
         * 
         * @return
         *     The value of the mailingList property.
         */
        public List<MailingList> getMailingList() {
            if (mailingList == null) {
                mailingList = new ArrayList<>();
            }
            return this.mailingList;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="module" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "module"
    })
    public static class Modules {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> module;

        /**
         * Gets the value of the module property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the module property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getModule().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * </p>
         * 
         * 
         * @return
         *     The value of the module property.
         */
        public List<String> getModule() {
            if (module == null) {
                module = new ArrayList<>();
            }
            return this.module;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="pluginRepository" type="{http://maven.apache.org/POM/4.0.0}Repository" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pluginRepository"
    })
    public static class PluginRepositories {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Repository> pluginRepository;

        /**
         * Gets the value of the pluginRepository property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pluginRepository property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getPluginRepository().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Repository }
         * </p>
         * 
         * 
         * @return
         *     The value of the pluginRepository property.
         */
        public List<Repository> getPluginRepository() {
            if (pluginRepository == null) {
                pluginRepository = new ArrayList<>();
            }
            return this.pluginRepository;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="profile" type="{http://maven.apache.org/POM/4.0.0}Profile" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "profile"
    })
    public static class Profiles {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Profile> profile;

        /**
         * Gets the value of the profile property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the profile property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getProfile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Profile }
         * </p>
         * 
         * 
         * @return
         *     The value of the profile property.
         */
        public List<Profile> getProfile() {
            if (profile == null) {
                profile = new ArrayList<>();
            }
            return this.profile;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class Properties {

        @XmlAnyElement
        protected List<Element> any;

        /**
         * Gets the value of the any property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getAny().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Element }
         * </p>
         * 
         * 
         * @return
         *     The value of the any property.
         */
        public List<Element> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return this.any;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class Reports {

        @XmlAnyElement
        protected List<Element> any;

        /**
         * Gets the value of the any property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getAny().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Element }
         * </p>
         * 
         * 
         * @return
         *     The value of the any property.
         */
        public List<Element> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return this.any;
        }

    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="repository" type="{http://maven.apache.org/POM/4.0.0}Repository" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "repository"
    })
    public static class Repositories {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Repository> repository;

        /**
         * Gets the value of the repository property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the repository property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getRepository().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Repository }
         * </p>
         * 
         * 
         * @return
         *     The value of the repository property.
         */
        public List<Repository> getRepository() {
            if (repository == null) {
                repository = new ArrayList<>();
            }
            return this.repository;
        }

    }

}
