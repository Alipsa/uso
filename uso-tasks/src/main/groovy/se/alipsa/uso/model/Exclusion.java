
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;exclusion&gt;</code> element contains informations required to exclude
 *         an artifact to the project.
 * 
 * <p>Java class for Exclusion complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Exclusion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exclusion", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Exclusion {

    /**
     * The artifact ID of the project to exclude.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The group ID of the project to exclude.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String groupId;

    /**
     * The artifact ID of the project to exclude.
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
     * The group ID of the project to exclude.
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

}
