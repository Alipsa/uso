
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Describes a build extension to utilise.
 * 
 * <p>Java class for Extension complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Extension">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extension", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Extension {

    /**
     * The group ID of the extension's artifact.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String groupId;
    /**
     * The artifact ID of the extension.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The version of the extension.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;

    /**
     * The group ID of the extension's artifact.
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
     * The artifact ID of the extension.
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
     * The version of the extension.
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

}
