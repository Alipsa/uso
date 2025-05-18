
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * This element describes all of the mailing lists associated with a project. The
 *         auto-generated site references this information.
 * 
 * <p>Java class for MailingList complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="MailingList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="subscribe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="unsubscribe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="post" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="archive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="otherArchives" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="otherArchive" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "MailingList", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class MailingList {

    /**
     * The name of the mailing list.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * The email address or link that can be used to subscribe to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String subscribe;
    /**
     * The email address or link that can be used to unsubscribe to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String unsubscribe;
    /**
     * The email address or link that can be used to post to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String post;
    /**
     * The link to a URL where you can browse the mailing list archive.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String archive;
    /**
     * The link to alternate URLs where you can browse the list archive.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected MailingList.OtherArchives otherArchives;

    /**
     * The name of the mailing list.
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
     * The email address or link that can be used to subscribe to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscribe() {
        return subscribe;
    }

    /**
     * Sets the value of the subscribe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSubscribe()
     */
    public void setSubscribe(String value) {
        this.subscribe = value;
    }

    /**
     * The email address or link that can be used to unsubscribe to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnsubscribe() {
        return unsubscribe;
    }

    /**
     * Sets the value of the unsubscribe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUnsubscribe()
     */
    public void setUnsubscribe(String value) {
        this.unsubscribe = value;
    }

    /**
     * The email address or link that can be used to post to
     *             the mailing list.  If this is an email address, a
     *             <code>mailto:</code> link will automatically be created
     *             when the documentation is created.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPost() {
        return post;
    }

    /**
     * Sets the value of the post property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPost()
     */
    public void setPost(String value) {
        this.post = value;
    }

    /**
     * The link to a URL where you can browse the mailing list archive.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchive() {
        return archive;
    }

    /**
     * Sets the value of the archive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getArchive()
     */
    public void setArchive(String value) {
        this.archive = value;
    }

    /**
     * The link to alternate URLs where you can browse the list archive.
     * 
     * @return
     *     possible object is
     *     {@link MailingList.OtherArchives }
     *     
     */
    public MailingList.OtherArchives getOtherArchives() {
        return otherArchives;
    }

    /**
     * Sets the value of the otherArchives property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailingList.OtherArchives }
     *     
     * @see #getOtherArchives()
     */
    public void setOtherArchives(MailingList.OtherArchives value) {
        this.otherArchives = value;
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
     *         <element name="otherArchive" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "otherArchive"
    })
    public static class OtherArchives {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> otherArchive;

        /**
         * Gets the value of the otherArchive property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherArchive property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getOtherArchive().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * </p>
         * 
         * 
         * @return
         *     The value of the otherArchive property.
         */
        public List<String> getOtherArchive() {
            if (otherArchive == null) {
                otherArchive = new ArrayList<>();
            }
            return this.otherArchive;
        }

    }

}
