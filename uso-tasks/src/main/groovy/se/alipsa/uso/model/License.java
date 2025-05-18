
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Describes the licenses for this project. This is used to generate the license
 *         page of the project's web site, as well as being taken into consideration in other reporting
 *         and validation. The licenses listed for the project are that of the project itself, and not
 *         of dependencies.
 * 
 * <p>Java class for License complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="License">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="distribution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "License", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class License {

    /**
     * The full legal name of the license.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * The official url for the license text.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * The primary method by which this project may be distributed.
     *             <dl>
     *               <dt>repo</dt>
     *               <dd>may be downloaded from the Maven repository</dd>
     *               <dt>manual</dt>
     *               <dd>user must manually download and install the dependency.</dd>
     *             </dl>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String distribution;
    /**
     * Addendum information pertaining to this license.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String comments;

    /**
     * The full legal name of the license.
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
     * The official url for the license text.
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
     * The primary method by which this project may be distributed.
     *             <dl>
     *               <dt>repo</dt>
     *               <dd>may be downloaded from the Maven repository</dd>
     *               <dt>manual</dt>
     *               <dd>user must manually download and install the dependency.</dd>
     *             </dl>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistribution() {
        return distribution;
    }

    /**
     * Sets the value of the distribution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDistribution()
     */
    public void setDistribution(String value) {
        this.distribution = value;
    }

    /**
     * Addendum information pertaining to this license.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getComments()
     */
    public void setComments(String value) {
        this.comments = value;
    }

}
