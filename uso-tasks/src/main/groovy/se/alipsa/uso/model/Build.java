
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;build&gt;</code> element contains informations required to build the project.
 *         Default values are defined in Super POM.
 * 
 * <p>Java class for Build complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Build">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="sourceDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="scriptSourceDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="testSourceDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="outputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="testOutputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="extensions" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="extension" type="{http://maven.apache.org/POM/4.0.0}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="defaultGoal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="resources" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="resource" type="{http://maven.apache.org/POM/4.0.0}Resource" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="testResources" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="testResource" type="{http://maven.apache.org/POM/4.0.0}Resource" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="directory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="finalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="filters" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="filter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="pluginManagement" type="{http://maven.apache.org/POM/4.0.0}PluginManagement" minOccurs="0"/>
 *         <element name="plugins" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="plugin" type="{http://maven.apache.org/POM/4.0.0}Plugin" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Build", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Build {

    /**
     * This element specifies a directory containing the source of the project. The
     *             generated build system will compile the sources from this directory when the project is
     *             built. The path given is relative to the project descriptor.
     *             The default value is <code>src/main/java</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String sourceDirectory;
    /**
     * This element specifies a directory containing the script sources of the
     *             project. This directory is meant to be different from the sourceDirectory, in that its
     *             contents will be copied to the output directory in most cases (since scripts are
     *             interpreted rather than compiled).
     *             The default value is <code>src/main/scripts</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String scriptSourceDirectory;
    /**
     * This element specifies a directory containing the unit test source of the
     *             project. The generated build system will compile these directories when the project is
     *             being tested. The path given is relative to the project descriptor.
     *             The default value is <code>src/test/java</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String testSourceDirectory;
    /**
     * The directory where compiled application classes are placed.
     *             The default value is <code>target/classes</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String outputDirectory;
    /**
     * The directory where compiled test classes are placed.
     *             The default value is <code>target/test-classes</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String testOutputDirectory;
    /**
     * A set of build extensions to use from this project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build.Extensions extensions;
    /**
     * The default goal (or phase in Maven 2) to execute when none is specified for
     *             the project. Note that in case of a multi-module build, only the default goal of the top-level
     *             project is relevant, i.e. the default goals of child modules are ignored. Since Maven 3,
     *             multiple goals/phases can be separated by whitespace.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String defaultGoal;
    /**
     * This element describes all of the classpath resources such as properties
     *             files associated with a project. These resources are often included in the final
     *             package.
     *             The default value is <code>src/main/resources</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build.Resources resources;
    /**
     * This element describes all of the classpath resources such as properties
     *             files associated with a project's unit tests.
     *             The default value is <code>src/test/resources</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build.TestResources testResources;
    /**
     * The directory where all files generated by the build are placed.
     *             The default value is <code>target</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String directory;
    /**
     * The filename (excluding the extension, and with no path information) that
     *             the produced artifact will be called.
     *             The default value is <code>${artifactId}-${version}</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String finalName;
    /**
     * The list of filter properties files that are used when filtering is enabled.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build.Filters filters;
    /**
     * Default plugin information to be made available for reference by projects
     *             derived from this one. This plugin configuration will not be resolved or bound to the
     *             lifecycle unless referenced. Any local configuration for a given plugin will override
     *             the plugin's entire definition here.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected PluginManagement pluginManagement;
    /**
     * The list of plugins to use.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Build.Plugins plugins;

    /**
     * This element specifies a directory containing the source of the project. The
     *             generated build system will compile the sources from this directory when the project is
     *             built. The path given is relative to the project descriptor.
     *             The default value is <code>src/main/java</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    /**
     * Sets the value of the sourceDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSourceDirectory()
     */
    public void setSourceDirectory(String value) {
        this.sourceDirectory = value;
    }

    /**
     * This element specifies a directory containing the script sources of the
     *             project. This directory is meant to be different from the sourceDirectory, in that its
     *             contents will be copied to the output directory in most cases (since scripts are
     *             interpreted rather than compiled).
     *             The default value is <code>src/main/scripts</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptSourceDirectory() {
        return scriptSourceDirectory;
    }

    /**
     * Sets the value of the scriptSourceDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getScriptSourceDirectory()
     */
    public void setScriptSourceDirectory(String value) {
        this.scriptSourceDirectory = value;
    }

    /**
     * This element specifies a directory containing the unit test source of the
     *             project. The generated build system will compile these directories when the project is
     *             being tested. The path given is relative to the project descriptor.
     *             The default value is <code>src/test/java</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestSourceDirectory() {
        return testSourceDirectory;
    }

    /**
     * Sets the value of the testSourceDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTestSourceDirectory()
     */
    public void setTestSourceDirectory(String value) {
        this.testSourceDirectory = value;
    }

    /**
     * The directory where compiled application classes are placed.
     *             The default value is <code>target/classes</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Sets the value of the outputDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getOutputDirectory()
     */
    public void setOutputDirectory(String value) {
        this.outputDirectory = value;
    }

    /**
     * The directory where compiled test classes are placed.
     *             The default value is <code>target/test-classes</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestOutputDirectory() {
        return testOutputDirectory;
    }

    /**
     * Sets the value of the testOutputDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTestOutputDirectory()
     */
    public void setTestOutputDirectory(String value) {
        this.testOutputDirectory = value;
    }

    /**
     * A set of build extensions to use from this project.
     * 
     * @return
     *     possible object is
     *     {@link Build.Extensions }
     *     
     */
    public Build.Extensions getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build.Extensions }
     *     
     * @see #getExtensions()
     */
    public void setExtensions(Build.Extensions value) {
        this.extensions = value;
    }

    /**
     * The default goal (or phase in Maven 2) to execute when none is specified for
     *             the project. Note that in case of a multi-module build, only the default goal of the top-level
     *             project is relevant, i.e. the default goals of child modules are ignored. Since Maven 3,
     *             multiple goals/phases can be separated by whitespace.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultGoal() {
        return defaultGoal;
    }

    /**
     * Sets the value of the defaultGoal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDefaultGoal()
     */
    public void setDefaultGoal(String value) {
        this.defaultGoal = value;
    }

    /**
     * This element describes all of the classpath resources such as properties
     *             files associated with a project. These resources are often included in the final
     *             package.
     *             The default value is <code>src/main/resources</code>.
     * 
     * @return
     *     possible object is
     *     {@link Build.Resources }
     *     
     */
    public Build.Resources getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build.Resources }
     *     
     * @see #getResources()
     */
    public void setResources(Build.Resources value) {
        this.resources = value;
    }

    /**
     * This element describes all of the classpath resources such as properties
     *             files associated with a project's unit tests.
     *             The default value is <code>src/test/resources</code>.
     * 
     * @return
     *     possible object is
     *     {@link Build.TestResources }
     *     
     */
    public Build.TestResources getTestResources() {
        return testResources;
    }

    /**
     * Sets the value of the testResources property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build.TestResources }
     *     
     * @see #getTestResources()
     */
    public void setTestResources(Build.TestResources value) {
        this.testResources = value;
    }

    /**
     * The directory where all files generated by the build are placed.
     *             The default value is <code>target</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDirectory()
     */
    public void setDirectory(String value) {
        this.directory = value;
    }

    /**
     * The filename (excluding the extension, and with no path information) that
     *             the produced artifact will be called.
     *             The default value is <code>${artifactId}-${version}</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalName() {
        return finalName;
    }

    /**
     * Sets the value of the finalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFinalName()
     */
    public void setFinalName(String value) {
        this.finalName = value;
    }

    /**
     * The list of filter properties files that are used when filtering is enabled.
     * 
     * @return
     *     possible object is
     *     {@link Build.Filters }
     *     
     */
    public Build.Filters getFilters() {
        return filters;
    }

    /**
     * Sets the value of the filters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build.Filters }
     *     
     * @see #getFilters()
     */
    public void setFilters(Build.Filters value) {
        this.filters = value;
    }

    /**
     * Default plugin information to be made available for reference by projects
     *             derived from this one. This plugin configuration will not be resolved or bound to the
     *             lifecycle unless referenced. Any local configuration for a given plugin will override
     *             the plugin's entire definition here.
     * 
     * @return
     *     possible object is
     *     {@link PluginManagement }
     *     
     */
    public PluginManagement getPluginManagement() {
        return pluginManagement;
    }

    /**
     * Sets the value of the pluginManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link PluginManagement }
     *     
     * @see #getPluginManagement()
     */
    public void setPluginManagement(PluginManagement value) {
        this.pluginManagement = value;
    }

    /**
     * The list of plugins to use.
     * 
     * @return
     *     possible object is
     *     {@link Build.Plugins }
     *     
     */
    public Build.Plugins getPlugins() {
        return plugins;
    }

    /**
     * Sets the value of the plugins property.
     * 
     * @param value
     *     allowed object is
     *     {@link Build.Plugins }
     *     
     * @see #getPlugins()
     */
    public void setPlugins(Build.Plugins value) {
        this.plugins = value;
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
     *         <element name="extension" type="{http://maven.apache.org/POM/4.0.0}Extension" maxOccurs="unbounded" minOccurs="0"/>
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
        "extension"
    })
    public static class Extensions {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Extension> extension;

        /**
         * Gets the value of the extension property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the extension property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getExtension().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Extension }
         * </p>
         * 
         * 
         * @return
         *     The value of the extension property.
         */
        public List<Extension> getExtension() {
            if (extension == null) {
                extension = new ArrayList<>();
            }
            return this.extension;
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
     *         <element name="filter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "filter"
    })
    public static class Filters {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> filter;

        /**
         * Gets the value of the filter property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the filter property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getFilter().add(newItem);
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
         *     The value of the filter property.
         */
        public List<String> getFilter() {
            if (filter == null) {
                filter = new ArrayList<>();
            }
            return this.filter;
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
     *         <element name="plugin" type="{http://maven.apache.org/POM/4.0.0}Plugin" maxOccurs="unbounded" minOccurs="0"/>
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
        "plugin"
    })
    public static class Plugins {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Plugin> plugin;

        /**
         * Gets the value of the plugin property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the plugin property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getPlugin().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Plugin }
         * </p>
         * 
         * 
         * @return
         *     The value of the plugin property.
         */
        public List<Plugin> getPlugin() {
            if (plugin == null) {
                plugin = new ArrayList<>();
            }
            return this.plugin;
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
     *         <element name="resource" type="{http://maven.apache.org/POM/4.0.0}Resource" maxOccurs="unbounded" minOccurs="0"/>
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
        "resource"
    })
    public static class Resources {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Resource> resource;

        /**
         * Gets the value of the resource property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resource property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getResource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Resource }
         * </p>
         * 
         * 
         * @return
         *     The value of the resource property.
         */
        public List<Resource> getResource() {
            if (resource == null) {
                resource = new ArrayList<>();
            }
            return this.resource;
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
     *         <element name="testResource" type="{http://maven.apache.org/POM/4.0.0}Resource" maxOccurs="unbounded" minOccurs="0"/>
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
        "testResource"
    })
    public static class TestResources {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Resource> testResource;

        /**
         * Gets the value of the testResource property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the testResource property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getTestResource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Resource }
         * </p>
         * 
         * 
         * @return
         *     The value of the testResource property.
         */
        public List<Resource> getTestResource() {
            if (testResource == null) {
                testResource = new ArrayList<>();
            }
            return this.testResource;
        }

    }

}
