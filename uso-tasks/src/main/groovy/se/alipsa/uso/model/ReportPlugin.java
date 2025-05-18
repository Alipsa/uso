
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
 * The <code>&lt;plugin&gt;</code> element contains informations required for a report plugin.
 * 
 * <p>Java class for ReportPlugin complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ReportPlugin">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="reportSets" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="reportSet" type="{http://maven.apache.org/POM/4.0.0}ReportSet" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ReportPlugin", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class ReportPlugin {

    /**
     * The group ID of the reporting plugin in the repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "org.apache.maven.plugins")
    protected String groupId;
    /**
     * The artifact ID of the reporting plugin in the repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The version of the reporting plugin to be used.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;
    /**
     * Multiple specifications of a set of reports, each having (possibly) different
     *             configuration. This is the reporting parallel to an <code>execution</code> in the build.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected ReportPlugin.ReportSets reportSets;
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
    protected ReportPlugin.Configuration configuration;

    /**
     * The group ID of the reporting plugin in the repository.
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
     * The artifact ID of the reporting plugin in the repository.
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
     * The version of the reporting plugin to be used.
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
     * Multiple specifications of a set of reports, each having (possibly) different
     *             configuration. This is the reporting parallel to an <code>execution</code> in the build.
     * 
     * @return
     *     possible object is
     *     {@link ReportPlugin.ReportSets }
     *     
     */
    public ReportPlugin.ReportSets getReportSets() {
        return reportSets;
    }

    /**
     * Sets the value of the reportSets property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportPlugin.ReportSets }
     *     
     * @see #getReportSets()
     */
    public void setReportSets(ReportPlugin.ReportSets value) {
        this.reportSets = value;
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
     *     {@link ReportPlugin.Configuration }
     *     
     */
    public ReportPlugin.Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportPlugin.Configuration }
     *     
     * @see #getConfiguration()
     */
    public void setConfiguration(ReportPlugin.Configuration value) {
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
     *         <element name="reportSet" type="{http://maven.apache.org/POM/4.0.0}ReportSet" maxOccurs="unbounded" minOccurs="0"/>
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
        "reportSet"
    })
    public static class ReportSets {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<ReportSet> reportSet;

        /**
         * Gets the value of the reportSet property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reportSet property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getReportSet().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReportSet }
         * </p>
         * 
         * 
         * @return
         *     The value of the reportSet property.
         */
        public List<ReportSet> getReportSet() {
            if (reportSet == null) {
                reportSet = new ArrayList<>();
            }
            return this.reportSet;
        }

    }

}
