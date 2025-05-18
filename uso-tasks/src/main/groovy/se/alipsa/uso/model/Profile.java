
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * Modifications to the build process which is activated based on environmental
 *         parameters or command line arguments.
 * 
 * <p>Java class for Profile complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Profile">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="activation" type="{http://maven.apache.org/POM/4.0.0}Activation" minOccurs="0"/>
 *         <element name="build" type="{http://maven.apache.org/POM/4.0.0}BuildBase" minOccurs="0"/>
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
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Profile", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Profile {

    /**
     * The identifier of this build profile. This is used for command line
     *             activation, and identifies profiles to be merged.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "default")
    protected String id;
    /**
     * The conditional logic which will automatically trigger the inclusion of this
     *             profile.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Activation activation;
    /**
     * Information required to build the project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected BuildBase build;
    /**
     * The modules (sometimes called subprojects) to build as a part of this
     *             project. Each module listed is a relative path to the directory containing the module.
     *             To be consistent with the way default urls are calculated from parent, it is recommended
     *             to have module names match artifact ids.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Profile.Modules modules;
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
    protected Profile.Properties properties;
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
    protected Profile.Dependencies dependencies;
    /**
     * The lists of the remote repositories for discovering dependencies and
     *             extensions.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Profile.Repositories repositories;
    /**
     * The lists of the remote repositories for discovering plugins for builds and
     *             reports.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Profile.PluginRepositories pluginRepositories;
    /**
     * <b>Deprecated</b>. Now ignored by Maven.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Profile.Reports reports;
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
     * The identifier of this build profile. This is used for command line
     *             activation, and identifies profiles to be merged.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * The conditional logic which will automatically trigger the inclusion of this
     *             profile.
     * 
     * @return
     *     possible object is
     *     {@link Activation }
     *     
     */
    public Activation getActivation() {
        return activation;
    }

    /**
     * Sets the value of the activation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Activation }
     *     
     * @see #getActivation()
     */
    public void setActivation(Activation value) {
        this.activation = value;
    }

    /**
     * Information required to build the project.
     * 
     * @return
     *     possible object is
     *     {@link BuildBase }
     *     
     */
    public BuildBase getBuild() {
        return build;
    }

    /**
     * Sets the value of the build property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildBase }
     *     
     * @see #getBuild()
     */
    public void setBuild(BuildBase value) {
        this.build = value;
    }

    /**
     * The modules (sometimes called subprojects) to build as a part of this
     *             project. Each module listed is a relative path to the directory containing the module.
     *             To be consistent with the way default urls are calculated from parent, it is recommended
     *             to have module names match artifact ids.
     * 
     * @return
     *     possible object is
     *     {@link Profile.Modules }
     *     
     */
    public Profile.Modules getModules() {
        return modules;
    }

    /**
     * Sets the value of the modules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.Modules }
     *     
     * @see #getModules()
     */
    public void setModules(Profile.Modules value) {
        this.modules = value;
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
     *     {@link Profile.Properties }
     *     
     */
    public Profile.Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.Properties }
     *     
     * @see #getProperties()
     */
    public void setProperties(Profile.Properties value) {
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
     *     {@link Profile.Dependencies }
     *     
     */
    public Profile.Dependencies getDependencies() {
        return dependencies;
    }

    /**
     * Sets the value of the dependencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.Dependencies }
     *     
     * @see #getDependencies()
     */
    public void setDependencies(Profile.Dependencies value) {
        this.dependencies = value;
    }

    /**
     * The lists of the remote repositories for discovering dependencies and
     *             extensions.
     * 
     * @return
     *     possible object is
     *     {@link Profile.Repositories }
     *     
     */
    public Profile.Repositories getRepositories() {
        return repositories;
    }

    /**
     * Sets the value of the repositories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.Repositories }
     *     
     * @see #getRepositories()
     */
    public void setRepositories(Profile.Repositories value) {
        this.repositories = value;
    }

    /**
     * The lists of the remote repositories for discovering plugins for builds and
     *             reports.
     * 
     * @return
     *     possible object is
     *     {@link Profile.PluginRepositories }
     *     
     */
    public Profile.PluginRepositories getPluginRepositories() {
        return pluginRepositories;
    }

    /**
     * Sets the value of the pluginRepositories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.PluginRepositories }
     *     
     * @see #getPluginRepositories()
     */
    public void setPluginRepositories(Profile.PluginRepositories value) {
        this.pluginRepositories = value;
    }

    /**
     * <b>Deprecated</b>. Now ignored by Maven.
     * 
     * @return
     *     possible object is
     *     {@link Profile.Reports }
     *     
     */
    public Profile.Reports getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.Reports }
     *     
     * @see #getReports()
     */
    public void setReports(Profile.Reports value) {
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
