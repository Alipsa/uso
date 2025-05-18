
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * This is the file specification used to activate the profile. The <code>missing</code> value
 *         is the location of a file that needs to exist, and if it doesn't, the profile will be
 *         activated. On the other hand, <code>exists</code> will test for the existence of the file and if it is
 *         there, the profile will be activated.<br>
 *         Variable interpolation for these file specifications is limited to <code>${basedir}</code>,
 *         System properties and request properties.
 * 
 * <p>Java class for ActivationFile complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ActivationFile">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="missing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="exists" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivationFile", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class ActivationFile {

    /**
     * The name of the file that must be missing to activate the
     *           profile.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String missing;
    /**
     * The name of the file that must exist to activate the profile.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String exists;

    /**
     * The name of the file that must be missing to activate the
     *           profile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissing() {
        return missing;
    }

    /**
     * Sets the value of the missing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMissing()
     */
    public void setMissing(String value) {
        this.missing = value;
    }

    /**
     * The name of the file that must exist to activate the profile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExists() {
        return exists;
    }

    /**
     * Sets the value of the exists property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getExists()
     */
    public void setExists(String value) {
        this.exists = value;
    }

}
