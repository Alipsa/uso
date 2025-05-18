
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Download policy.
 * 
 * <p>Java class for RepositoryPolicy complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="RepositoryPolicy">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="updatePolicy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="checksumPolicy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RepositoryPolicy", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class RepositoryPolicy {

    /**
     * Whether to use this repository for downloading this type of artifact. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String enabled;
    /**
     * The frequency for downloading updates - can be
     *             <code>always,</code>
     *             <code>daily</code>
     *             (default),
     *             <code>interval:XXX</code>
     *             (in minutes) or
     *             <code>never</code>
     *             (only if it doesn't exist locally).
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String updatePolicy;
    /**
     * What to do when verification of an artifact checksum fails. Valid values are
     *             <code>ignore</code>
     *             ,
     *             <code>fail</code>
     *             or
     *             <code>warn</code>
     *             (the default).
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String checksumPolicy;

    /**
     * Whether to use this repository for downloading this type of artifact. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getEnabled()
     */
    public void setEnabled(String value) {
        this.enabled = value;
    }

    /**
     * The frequency for downloading updates - can be
     *             <code>always,</code>
     *             <code>daily</code>
     *             (default),
     *             <code>interval:XXX</code>
     *             (in minutes) or
     *             <code>never</code>
     *             (only if it doesn't exist locally).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatePolicy() {
        return updatePolicy;
    }

    /**
     * Sets the value of the updatePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUpdatePolicy()
     */
    public void setUpdatePolicy(String value) {
        this.updatePolicy = value;
    }

    /**
     * What to do when verification of an artifact checksum fails. Valid values are
     *             <code>ignore</code>
     *             ,
     *             <code>fail</code>
     *             or
     *             <code>warn</code>
     *             (the default).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChecksumPolicy() {
        return checksumPolicy;
    }

    /**
     * Sets the value of the checksumPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getChecksumPolicy()
     */
    public void setChecksumPolicy(String value) {
        this.checksumPolicy = value;
    }

}
