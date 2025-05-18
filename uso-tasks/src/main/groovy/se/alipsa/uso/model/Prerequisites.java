
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Describes the prerequisites a project can have.
 * 
 * <p>Java class for Prerequisites complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Prerequisites">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="maven" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prerequisites", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Prerequisites {

    /**
     * For a plugin project (packaging is <code>maven-plugin</code>), the minimum version of
     *             Maven required to use the resulting plugin.<br>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "2.0")
    protected String maven;

    /**
     * For a plugin project (packaging is <code>maven-plugin</code>), the minimum version of
     *             Maven required to use the resulting plugin.<br>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaven() {
        return maven;
    }

    /**
     * Sets the value of the maven property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMaven()
     */
    public void setMaven(String value) {
        this.maven = value;
    }

}
