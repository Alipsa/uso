
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;scm&gt;</code> element contains informations required to the SCM
 *         (Source Control Management) of the project.
 * 
 * <p>Java class for Scm complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Scm">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="connection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="developerConnection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *       <attribute name="child.scm.connection.inherit.append.path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="child.scm.developerConnection.inherit.append.path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="child.scm.url.inherit.append.path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Scm", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Scm {

    /**
     * The source control management system URL
     *             that describes the repository and how to connect to the
     *             repository. For more information, see the
     *             <a href="https://maven.apache.org/scm/scm-url-format.html">URL format</a>
     *             and <a href="https://maven.apache.org/scm/scms-overview.html">list of supported SCMs</a>.
     *             This connection is read-only.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.connection.inherit.append.path="false"</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String connection;
    /**
     * Just like <code>connection</code>, but for developers, i.e. this scm connection
     *             will not be read only.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.developerConnection.inherit.append.path="false"</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String developerConnection;
    /**
     * The tag of current code. By default, it's set to HEAD during development.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "HEAD")
    protected String tag;
    /**
     * The URL to the project's browsable SCM repository, such as ViewVC or Fisheye.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.url.inherit.append.path="false"</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * When children inherit from scm connection, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     */
    @XmlAttribute(name = "child.scm.connection.inherit.append.path")
    protected String childScmConnectionInheritAppendPath;
    /**
     * When children inherit from scm developer connection, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     */
    @XmlAttribute(name = "child.scm.developerConnection.inherit.append.path")
    protected String childScmDeveloperConnectionInheritAppendPath;
    /**
     * When children inherit from scm url, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     */
    @XmlAttribute(name = "child.scm.url.inherit.append.path")
    protected String childScmUrlInheritAppendPath;

    /**
     * The source control management system URL
     *             that describes the repository and how to connect to the
     *             repository. For more information, see the
     *             <a href="https://maven.apache.org/scm/scm-url-format.html">URL format</a>
     *             and <a href="https://maven.apache.org/scm/scms-overview.html">list of supported SCMs</a>.
     *             This connection is read-only.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.connection.inherit.append.path="false"</code>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnection() {
        return connection;
    }

    /**
     * Sets the value of the connection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getConnection()
     */
    public void setConnection(String value) {
        this.connection = value;
    }

    /**
     * Just like <code>connection</code>, but for developers, i.e. this scm connection
     *             will not be read only.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.developerConnection.inherit.append.path="false"</code>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeveloperConnection() {
        return developerConnection;
    }

    /**
     * Sets the value of the developerConnection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDeveloperConnection()
     */
    public void setDeveloperConnection(String value) {
        this.developerConnection = value;
    }

    /**
     * The tag of current code. By default, it's set to HEAD during development.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTag()
     */
    public void setTag(String value) {
        this.tag = value;
    }

    /**
     * The URL to the project's browsable SCM repository, such as ViewVC or Fisheye.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             scm's <code>child.scm.url.inherit.append.path="false"</code>
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
     * When children inherit from scm connection, append path or not? Note: While the type
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
    public String getChildScmConnectionInheritAppendPath() {
        return childScmConnectionInheritAppendPath;
    }

    /**
     * Sets the value of the childScmConnectionInheritAppendPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChildScmConnectionInheritAppendPath()
     */
    public void setChildScmConnectionInheritAppendPath(String value) {
        this.childScmConnectionInheritAppendPath = value;
    }

    /**
     * When children inherit from scm developer connection, append path or not? Note: While the type
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
    public String getChildScmDeveloperConnectionInheritAppendPath() {
        return childScmDeveloperConnectionInheritAppendPath;
    }

    /**
     * Sets the value of the childScmDeveloperConnectionInheritAppendPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChildScmDeveloperConnectionInheritAppendPath()
     */
    public void setChildScmDeveloperConnectionInheritAppendPath(String value) {
        this.childScmDeveloperConnectionInheritAppendPath = value;
    }

    /**
     * When children inherit from scm url, append path or not? Note: While the type
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
    public String getChildScmUrlInheritAppendPath() {
        return childScmUrlInheritAppendPath;
    }

    /**
     * Sets the value of the childScmUrlInheritAppendPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChildScmUrlInheritAppendPath()
     */
    public void setChildScmUrlInheritAppendPath(String value) {
        this.childScmUrlInheritAppendPath = value;
    }

}
