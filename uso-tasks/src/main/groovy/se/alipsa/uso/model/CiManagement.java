
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;CiManagement&gt;</code> element contains informations required to the
 *         continuous integration system of the project.
 * 
 * <p>Java class for CiManagement complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="CiManagement">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="system" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="notifiers" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="notifier" type="{http://maven.apache.org/POM/4.0.0}Notifier" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CiManagement", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class CiManagement {

    /**
     * The name of the continuous integration system, e.g. <code>continuum</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String system;
    /**
     * URL for the continuous integration system used by the project if it has a web
     *             interface.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * Configuration for notifying developers/users when a build is unsuccessful,
     *             including user information and notification mode.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected CiManagement.Notifiers notifiers;

    /**
     * The name of the continuous integration system, e.g. <code>continuum</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystem() {
        return system;
    }

    /**
     * Sets the value of the system property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSystem()
     */
    public void setSystem(String value) {
        this.system = value;
    }

    /**
     * URL for the continuous integration system used by the project if it has a web
     *             interface.
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
     * Configuration for notifying developers/users when a build is unsuccessful,
     *             including user information and notification mode.
     * 
     * @return
     *     possible object is
     *     {@link CiManagement.Notifiers }
     *     
     */
    public CiManagement.Notifiers getNotifiers() {
        return notifiers;
    }

    /**
     * Sets the value of the notifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link CiManagement.Notifiers }
     *     
     * @see #getNotifiers()
     */
    public void setNotifiers(CiManagement.Notifiers value) {
        this.notifiers = value;
    }


    /**
     * <p>Java class for anonymous complex type</p>.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="notifier" type="{http://maven.apache.org/POM/4.0.0}Notifier" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "notifier"
    })
    public static class Notifiers {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<Notifier> notifier;

        /**
         * Gets the value of the notifier property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the notifier property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getNotifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Notifier }
         * </p>
         * 
         * 
         * @return
         *     The value of the notifier property.
         */
        public List<Notifier> getNotifier() {
            if (notifier == null) {
                notifier = new ArrayList<>();
            }
            return this.notifier;
        }

    }

}
