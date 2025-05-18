
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * This is an activator which will detect an operating system's attributes in order
 *         to activate its profile.
 * 
 * <p>Java class for ActivationOS complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ActivationOS">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="family" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="arch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ActivationOS", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class ActivationOS {

    /**
     * The name of the operating system to be used to activate the profile. This must be an exact match
     *             of the <code>${os.name}</code> Java property, such as <code>Windows XP</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * The general family of the OS to be used to activate the profile, such as
     *             <code>windows</code> or <code>unix</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String family;
    /**
     * The architecture of the operating system to be used to activate the
     *           profile.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String arch;
    /**
     * The version of the operating system to be used to activate the
     *           profile.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;

    /**
     * The name of the operating system to be used to activate the profile. This must be an exact match
     *             of the <code>${os.name}</code> Java property, such as <code>Windows XP</code>.
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
     * The general family of the OS to be used to activate the profile, such as
     *             <code>windows</code> or <code>unix</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamily() {
        return family;
    }

    /**
     * Sets the value of the family property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFamily()
     */
    public void setFamily(String value) {
        this.family = value;
    }

    /**
     * The architecture of the operating system to be used to activate the
     *           profile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArch() {
        return arch;
    }

    /**
     * Sets the value of the arch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getArch()
     */
    public void setArch(String value) {
        this.arch = value;
    }

    /**
     * The version of the operating system to be used to activate the
     *           profile.
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
