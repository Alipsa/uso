
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Contains the information needed for deploying websites.
 * 
 * <p>Java class for Site complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Site">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *       <attribute name="child.site.url.inherit.append.path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Site", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Site {

    /**
     * A unique identifier for a deployment location. This is used to match the
     *             site to configuration in the <code>settings.xml</code> file, for example.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String id;
    /**
     * Human readable name of the deployment location.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * The url of the location where website is deployed, in the form <code>protocol://hostname/path</code>.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             site's <code>child.site.url.inherit.append.path="false"</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * When children inherit from distribution management site url, append path or not? Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>
     *             <br><b>Default value is</b>: <code>true</code>
     *             <br><b>Since</b>: Maven 3.6.1
     * 
     */
    @XmlAttribute(name = "child.site.url.inherit.append.path")
    protected String childSiteUrlInheritAppendPath;

    /**
     * A unique identifier for a deployment location. This is used to match the
     *             site to configuration in the <code>settings.xml</code> file, for example.
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
     * Human readable name of the deployment location.
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
     * The url of the location where website is deployed, in the form <code>protocol://hostname/path</code>.
     *             <br><b>Default value is</b>: parent value [+ path adjustment] + (artifactId or project.directory property), or just parent value if
     *             site's <code>child.site.url.inherit.append.path="false"</code>
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
     * When children inherit from distribution management site url, append path or not? Note: While the type
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
    public String getChildSiteUrlInheritAppendPath() {
        return childSiteUrlInheritAppendPath;
    }

    /**
     * Sets the value of the childSiteUrlInheritAppendPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChildSiteUrlInheritAppendPath()
     */
    public void setChildSiteUrlInheritAppendPath(String value) {
        this.childSiteUrlInheritAppendPath = value;
    }

}
