
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
 * The <code>&lt;plugin&gt;</code> element contains informations required for a plugin.
 * 
 * <p>Java class for Plugin complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Plugin">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="extensions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="executions" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="execution" type="{http://maven.apache.org/POM/4.0.0}PluginExecution" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
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
 *         <element name="goals" minOccurs="0">
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
 *         <element name="inherited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="configuration" minOccurs="0">
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
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plugin", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Plugin {

    /**
     * The group ID of the plugin in the repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "org.apache.maven.plugins")
    protected String groupId;
    /**
     * The artifact ID of the plugin in the repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The version (or valid range of versions) of the plugin to be used.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;
    /**
     * Whether to load Maven extensions (such as packaging and type handlers) from
     *             this plugin. For performance reasons, this should only be enabled when necessary. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String extensions;
    /**
     * Multiple specifications of a set of goals to execute during the build
     *             lifecycle, each having (possibly) a different configuration.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Plugin.Executions executions;
    /**
     * Additional dependencies that this project needs to introduce to the plugin's
     *             classloader.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Plugin.Dependencies dependencies;
    /**
     * <b>Deprecated</b>. Unused by Maven.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Plugin.Goals goals;
    /**
     * Whether any configuration should be propagated to child POMs. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String inherited;
    /**
     * <p>The configuration as DOM object.</p>
     *             <p>By default, every element content is trimmed, but starting with Maven 3.1.0, you can add
     *             <code>xml:space="preserve"</code> to elements you want to preserve whitespace.</p>
     *             <p>You can control how child POMs inherit configuration from parent POMs by adding <code>combine.children</code>
     *             or <code>combine.self</code> attributes to the children of the configuration element:</p>
     *             <ul>
     *             <li><code>combine.children</code>: available values are <code>merge</code> (default) and <code>append</code>,</li>
     *             <li><code>combine.self</code>: available values are <code>merge</code> (default) and <code>override</code>.</li>
     *             </ul>
     *             <p>See <a href="https://maven.apache.org/pom.html#Plugins">POM Reference documentation</a> and
     *             <a href="https://codehaus-plexus.github.io/plexus-utils/apidocs/org/codehaus/plexus/util/xml/Xpp3DomUtils.html">Xpp3DomUtils</a>
     *             for more information.</p>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Plugin.Configuration configuration;

    /**
     * The group ID of the plugin in the repository.
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
     * The artifact ID of the plugin in the repository.
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
     * The version (or valid range of versions) of the plugin to be used.
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
     * Whether to load Maven extensions (such as packaging and type handlers) from
     *             this plugin. For performance reasons, this should only be enabled when necessary. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getExtensions()
     */
    public void setExtensions(String value) {
        this.extensions = value;
    }

    /**
     * Multiple specifications of a set of goals to execute during the build
     *             lifecycle, each having (possibly) a different configuration.
     * 
     * @return
     *     possible object is
     *     {@link Plugin.Executions }
     *     
     */
    public Plugin.Executions getExecutions() {
        return executions;
    }

    /**
     * Sets the value of the executions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugin.Executions }
     *     
     * @see #getExecutions()
     */
    public void setExecutions(Plugin.Executions value) {
        this.executions = value;
    }

    /**
     * Additional dependencies that this project needs to introduce to the plugin's
     *             classloader.
     * 
     * @return
     *     possible object is
     *     {@link Plugin.Dependencies }
     *     
     */
    public Plugin.Dependencies getDependencies() {
        return dependencies;
    }

    /**
     * Sets the value of the dependencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugin.Dependencies }
     *     
     * @see #getDependencies()
     */
    public void setDependencies(Plugin.Dependencies value) {
        this.dependencies = value;
    }

    /**
     * <b>Deprecated</b>. Unused by Maven.
     * 
     * @return
     *     possible object is
     *     {@link Plugin.Goals }
     *     
     */
    public Plugin.Goals getGoals() {
        return goals;
    }

    /**
     * Sets the value of the goals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugin.Goals }
     *     
     * @see #getGoals()
     */
    public void setGoals(Plugin.Goals value) {
        this.goals = value;
    }

    /**
     * Whether any configuration should be propagated to child POMs. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInherited() {
        return inherited;
    }

    /**
     * Sets the value of the inherited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getInherited()
     */
    public void setInherited(String value) {
        this.inherited = value;
    }

    /**
     * <p>The configuration as DOM object.</p>
     *             <p>By default, every element content is trimmed, but starting with Maven 3.1.0, you can add
     *             <code>xml:space="preserve"</code> to elements you want to preserve whitespace.</p>
     *             <p>You can control how child POMs inherit configuration from parent POMs by adding <code>combine.children</code>
     *             or <code>combine.self</code> attributes to the children of the configuration element:</p>
     *             <ul>
     *             <li><code>combine.children</code>: available values are <code>merge</code> (default) and <code>append</code>,</li>
     *             <li><code>combine.self</code>: available values are <code>merge</code> (default) and <code>override</code>.</li>
     *             </ul>
     *             <p>See <a href="https://maven.apache.org/pom.html#Plugins">POM Reference documentation</a> and
     *             <a href="https://codehaus-plexus.github.io/plexus-utils/apidocs/org/codehaus/plexus/util/xml/Xpp3DomUtils.html">Xpp3DomUtils</a>
     *             for more information.</p>
     * 
     * @return
     *     possible object is
     *     {@link Plugin.Configuration }
     *     
     */
    public Plugin.Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugin.Configuration }
     *     
     * @see #getConfiguration()
     */
    public void setConfiguration(Plugin.Configuration value) {
        this.configuration = value;
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
    public static class Configuration {

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
     *         <element name="execution" type="{http://maven.apache.org/POM/4.0.0}PluginExecution" maxOccurs="unbounded" minOccurs="0"/>
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
        "execution"
    })
    public static class Executions {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<PluginExecution> execution;

        /**
         * Gets the value of the execution property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the execution property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getExecution().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PluginExecution }
         * </p>
         * 
         * 
         * @return
         *     The value of the execution property.
         */
        public List<PluginExecution> getExecution() {
            if (execution == null) {
                execution = new ArrayList<>();
            }
            return this.execution;
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
    public static class Goals {

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

}
