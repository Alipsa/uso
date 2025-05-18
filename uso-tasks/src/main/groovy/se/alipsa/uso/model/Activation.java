
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The conditions within the build runtime environment which will trigger the
 *         automatic inclusion of the build profile. Multiple conditions can be defined, which must
 *         be all satisfied to activate the profile.
 * 
 * <p>Java class for Activation complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Activation">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="activeByDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="jdk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="os" type="{http://maven.apache.org/POM/4.0.0}ActivationOS" minOccurs="0"/>
 *         <element name="property" type="{http://maven.apache.org/POM/4.0.0}ActivationProperty" minOccurs="0"/>
 *         <element name="file" type="{http://maven.apache.org/POM/4.0.0}ActivationFile" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Activation", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Activation {

    /**
     * If set to true, this profile will be active unless another profile in this
     *             pom is activated using the command line -P option or by one of that profile's
     *             activators.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "false")
    protected Boolean activeByDefault;
    /**
     * Specifies that this profile will be activated when a matching JDK is detected.
     *             For example, <code>1.4</code> only activates on JDKs versioned 1.4,
     *             while <code>!1.4</code> matches any JDK that is not version 1.4. Ranges are supported too:
     *             <code>[1.5,)</code> activates when the JDK is 1.5 minimum.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String jdk;
    /**
     * Specifies that this profile will be activated when matching operating system
     *             attributes are detected.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected ActivationOS os;
    /**
     * Specifies that this profile will be activated when this system property is
     *             specified.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected ActivationProperty property;
    /**
     * Specifies that this profile will be activated based on existence of a file.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected ActivationFile file;

    /**
     * If set to true, this profile will be active unless another profile in this
     *             pom is activated using the command line -P option or by one of that profile's
     *             activators.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActiveByDefault() {
        return activeByDefault;
    }

    /**
     * Sets the value of the activeByDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isActiveByDefault()
     */
    public void setActiveByDefault(Boolean value) {
        this.activeByDefault = value;
    }

    /**
     * Specifies that this profile will be activated when a matching JDK is detected.
     *             For example, <code>1.4</code> only activates on JDKs versioned 1.4,
     *             while <code>!1.4</code> matches any JDK that is not version 1.4. Ranges are supported too:
     *             <code>[1.5,)</code> activates when the JDK is 1.5 minimum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJdk() {
        return jdk;
    }

    /**
     * Sets the value of the jdk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getJdk()
     */
    public void setJdk(String value) {
        this.jdk = value;
    }

    /**
     * Specifies that this profile will be activated when matching operating system
     *             attributes are detected.
     * 
     * @return
     *     possible object is
     *     {@link ActivationOS }
     *     
     */
    public ActivationOS getOs() {
        return os;
    }

    /**
     * Sets the value of the os property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationOS }
     *     
     * @see #getOs()
     */
    public void setOs(ActivationOS value) {
        this.os = value;
    }

    /**
     * Specifies that this profile will be activated when this system property is
     *             specified.
     * 
     * @return
     *     possible object is
     *     {@link ActivationProperty }
     *     
     */
    public ActivationProperty getProperty() {
        return property;
    }

    /**
     * Sets the value of the property property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationProperty }
     *     
     * @see #getProperty()
     */
    public void setProperty(ActivationProperty value) {
        this.property = value;
    }

    /**
     * Specifies that this profile will be activated based on existence of a file.
     * 
     * @return
     *     possible object is
     *     {@link ActivationFile }
     *     
     */
    public ActivationFile getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationFile }
     *     
     * @see #getFile()
     */
    public void setFile(ActivationFile value) {
        this.file = value;
    }

}
