
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * This element describes all of the classpath resources associated with a project
 *         or unit tests.
 * 
 * <p>Java class for Resource complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Resource">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="targetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="filtering" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="directory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="includes" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="excludes" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Resource", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Resource {

    /**
     * Describe the resource target path. The path is relative to the target/classes
     *             directory (i.e. <code>${project.build.outputDirectory}</code>).
     *             For example, if you want that resource to appear in a specific package
     *             (<code>org.apache.maven.messages</code>), you must specify this
     *             element with this value: <code>org/apache/maven/messages</code>.
     *             This is not required if you simply put the resources in that directory
     *             structure at the source, however.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String targetPath;
    /**
     * Whether resources are filtered to replace tokens with parameterised values or not.
     *             The values are taken from the <code>properties</code> element and from the
     *             properties in the files listed in the <code>filters</code> element. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String filtering;
    /**
     * Describe the directory where the resources are stored. The path is relative
     *             to the POM.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String directory;
    /**
     * A list of patterns to include, e.g. <code>**&#47;*.xml</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Resource.Includes includes;
    /**
     * A list of patterns to exclude, e.g. <code>**&#47;*.xml</code>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Resource.Excludes excludes;

    /**
     * Describe the resource target path. The path is relative to the target/classes
     *             directory (i.e. <code>${project.build.outputDirectory}</code>).
     *             For example, if you want that resource to appear in a specific package
     *             (<code>org.apache.maven.messages</code>), you must specify this
     *             element with this value: <code>org/apache/maven/messages</code>.
     *             This is not required if you simply put the resources in that directory
     *             structure at the source, however.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPath() {
        return targetPath;
    }

    /**
     * Sets the value of the targetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTargetPath()
     */
    public void setTargetPath(String value) {
        this.targetPath = value;
    }

    /**
     * Whether resources are filtered to replace tokens with parameterised values or not.
     *             The values are taken from the <code>properties</code> element and from the
     *             properties in the files listed in the <code>filters</code> element. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>false</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltering() {
        return filtering;
    }

    /**
     * Sets the value of the filtering property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFiltering()
     */
    public void setFiltering(String value) {
        this.filtering = value;
    }

    /**
     * Describe the directory where the resources are stored. The path is relative
     *             to the POM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDirectory()
     */
    public void setDirectory(String value) {
        this.directory = value;
    }

    /**
     * A list of patterns to include, e.g. <code>**&#47;*.xml</code>.
     * 
     * @return
     *     possible object is
     *     {@link Resource.Includes }
     *     
     */
    public Resource.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resource.Includes }
     *     
     * @see #getIncludes()
     */
    public void setIncludes(Resource.Includes value) {
        this.includes = value;
    }

    /**
     * A list of patterns to exclude, e.g. <code>**&#47;*.xml</code>
     * 
     * @return
     *     possible object is
     *     {@link Resource.Excludes }
     *     
     */
    public Resource.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resource.Excludes }
     *     
     * @see #getExcludes()
     */
    public void setExcludes(Resource.Excludes value) {
        this.excludes = value;
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
     *         <element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "exclude"
    })
    public static class Excludes {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> exclude;

        /**
         * Gets the value of the exclude property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exclude property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getExclude().add(newItem);
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
         *     The value of the exclude property.
         */
        public List<String> getExclude() {
            if (exclude == null) {
                exclude = new ArrayList<>();
            }
            return this.exclude;
        }

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
     *         <element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "include"
    })
    public static class Includes {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> include;

        /**
         * Gets the value of the include property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the include property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getInclude().add(newItem);
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
         *     The value of the include property.
         */
        public List<String> getInclude() {
            if (include == null) {
                include = new ArrayList<>();
            }
            return this.include;
        }

    }

}
