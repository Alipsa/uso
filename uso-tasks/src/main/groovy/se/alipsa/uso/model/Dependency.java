
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;dependency&gt;</code> element contains information about a dependency
 *         of the project.
 * 
 * <p>Java class for Dependency complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Dependency">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="classifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="systemPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="exclusions" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="exclusion" type="{http://maven.apache.org/POM/4.0.0}Exclusion" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="optional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dependency", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Dependency {

    /**
     * The project group that produced the dependency, e.g.
     *             <code>org.apache.maven</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String groupId;
    /**
     * The unique id for an artifact produced by the project group, e.g.
     *             <code>maven-artifact</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The version of the dependency, e.g. <code>3.2.1</code>. In Maven 2, this can also be
     *             specified as a range of versions.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;
    /**
     * The type of dependency, that will be mapped to a file extension, an optional classifier, and a few other attributes.
     *             Some examples are <code>jar</code>, <code>war</code>, <code>ejb-client</code>
     *             and <code>test-jar</code>: see <a href="../maven-core/artifact-handlers.html">default
     *             artifact handlers</a> for a list. New types can be defined by extensions, so this is not a complete list.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "jar")
    protected String type;
    /**
     * The classifier of the dependency. It is appended to
     *             the filename after the version. This allows:
     *             <ul>
     *             <li>referring to attached artifact, for example <code>sources</code> and <code>javadoc</code>:
     *             see <a href="../maven-core/artifact-handlers.html">default artifact handlers</a> for a list,</li>
     *             <li>distinguishing two artifacts
     *             that belong to the same POM but were built differently.
     *             For example, <code>jdk14</code> and <code>jdk15</code>.</li>
     *             </ul>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String classifier;
    /**
     * The scope of the dependency - <code>compile</code>, <code>runtime</code>,
     *             <code>test</code>, <code>system</code>, and <code>provided</code>. Used to
     *             calculate the various classpaths used for compilation, testing, and so on.
     *             It also assists in determining which artifacts to include in a distribution of
     *             this project. For more information, see
     *             <a href="https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html">the
     *             dependency mechanism</a>. The default scope is <code>compile</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String scope;
    /**
     * FOR SYSTEM SCOPE ONLY. Note that use of this property is <b>discouraged</b>
     *             and may be replaced in later versions. This specifies the path on the filesystem
     *             for this dependency.
     *             Requires an absolute path for the value, not relative.
     *             Use a property that gives the machine specific absolute path,
     *             e.g. <code>${java.home}</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String systemPath;
    /**
     * Lists a set of artifacts that should be excluded from this dependency's
     *             artifact list when it comes to calculating transitive dependencies.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Dependency.Exclusions exclusions;
    /**
     * Indicates the dependency is optional for use of this library. While the
     *             version of the dependency will be taken into account for dependency calculation if the
     *             library is used elsewhere, it will not be passed on transitively. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String optional;

    /**
     * The project group that produced the dependency, e.g.
     *             <code>org.apache.maven</code>.
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
     * The unique id for an artifact produced by the project group, e.g.
     *             <code>maven-artifact</code>.
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
     * The version of the dependency, e.g. <code>3.2.1</code>. In Maven 2, this can also be
     *             specified as a range of versions.
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
     * The type of dependency, that will be mapped to a file extension, an optional classifier, and a few other attributes.
     *             Some examples are <code>jar</code>, <code>war</code>, <code>ejb-client</code>
     *             and <code>test-jar</code>: see <a href="../maven-core/artifact-handlers.html">default
     *             artifact handlers</a> for a list. New types can be defined by extensions, so this is not a complete list.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getType()
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * The classifier of the dependency. It is appended to
     *             the filename after the version. This allows:
     *             <ul>
     *             <li>referring to attached artifact, for example <code>sources</code> and <code>javadoc</code>:
     *             see <a href="../maven-core/artifact-handlers.html">default artifact handlers</a> for a list,</li>
     *             <li>distinguishing two artifacts
     *             that belong to the same POM but were built differently.
     *             For example, <code>jdk14</code> and <code>jdk15</code>.</li>
     *             </ul>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassifier() {
        return classifier;
    }

    /**
     * Sets the value of the classifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getClassifier()
     */
    public void setClassifier(String value) {
        this.classifier = value;
    }

    /**
     * The scope of the dependency - <code>compile</code>, <code>runtime</code>,
     *             <code>test</code>, <code>system</code>, and <code>provided</code>. Used to
     *             calculate the various classpaths used for compilation, testing, and so on.
     *             It also assists in determining which artifacts to include in a distribution of
     *             this project. For more information, see
     *             <a href="https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html">the
     *             dependency mechanism</a>. The default scope is <code>compile</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getScope()
     */
    public void setScope(String value) {
        this.scope = value;
    }

    /**
     * FOR SYSTEM SCOPE ONLY. Note that use of this property is <b>discouraged</b>
     *             and may be replaced in later versions. This specifies the path on the filesystem
     *             for this dependency.
     *             Requires an absolute path for the value, not relative.
     *             Use a property that gives the machine specific absolute path,
     *             e.g. <code>${java.home}</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemPath() {
        return systemPath;
    }

    /**
     * Sets the value of the systemPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSystemPath()
     */
    public void setSystemPath(String value) {
        this.systemPath = value;
    }

    /**
     * Lists a set of artifacts that should be excluded from this dependency's
     *             artifact list when it comes to calculating transitive dependencies.
     * 
     * @return
     *     possible object is
     *     {@link Dependency.Exclusions }
     *     
     */
    public Dependency.Exclusions getExclusions() {
        return exclusions;
    }

    /**
     * Sets the value of the exclusions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dependency.Exclusions }
     *     
     * @see #getExclusions()
     */
    public void setExclusions(Dependency.Exclusions value) {
        this.exclusions = value;
    }

    /**
     * Indicates the dependency is optional for use of this library. While the
     *             version of the dependency will be taken into account for dependency calculation if the
     *             library is used elsewhere, it will not be passed on transitively. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptional() {
        return optional;
    }

    /**
     * Sets the value of the optional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getOptional()
     */
    public void setOptional(String value) {
        this.optional = value;
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
     *         <element name="exclusion" type="{http://maven.apache.org/POM/4.0.0}Exclusion" maxOccurs="unbounded" minOccurs="0"/>
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
        "exclusion"
    })
    public static class Exclusions {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Exclusion> exclusion;

        /**
         * Gets the value of the exclusion property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exclusion property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getExclusion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Exclusion }
         * </p>
         * 
         * 
         * @return
         *     The value of the exclusion property.
         */
        public List<Exclusion> getExclusion() {
            if (exclusion == null) {
                exclusion = new ArrayList<>();
            }
            return this.exclusion;
        }

    }

}
