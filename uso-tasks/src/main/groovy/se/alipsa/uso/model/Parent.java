
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The <code>&lt;parent&gt;</code> element contains information required to locate the parent project from which
 *         this project will inherit from.
 *         <strong>Note:</strong> The children of this element are not interpolated and must be given as literal values.
 * 
 * <p>Java class for Parent complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Parent">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="relativePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parent", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class Parent {

    /**
     * The group id of the parent project to inherit from.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String groupId;
    /**
     * The artifact id of the parent project to inherit from.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String artifactId;
    /**
     * The version of the parent project to inherit.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String version;
    /**
     * The relative path of the parent <code>pom.xml</code> file within the check out.
     *             If not specified, it defaults to <code>../pom.xml</code>.
     *             Maven looks for the parent POM first in this location on
     *             the filesystem, then the local repository, and lastly in the remote repo.
     *             <code>relativePath</code> allows you to select a different location,
     *             for example when your structure is flat, or deeper without an intermediate parent POM.
     *             However, the group ID, artifact ID and version are still required,
     *             and must match the file in the location given or it will revert to the repository for the POM.
     *             This feature is only for enhancing the development in a local checkout of that project.
     *             Set the value to an empty string in case you want to disable the feature and always resolve
     *             the parent POM from the repositories.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "../pom.xml")
    protected String relativePath;

    /**
     * The group id of the parent project to inherit from.
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

    /**
     * The artifact id of the parent project to inherit from.
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
     * The version of the parent project to inherit.
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

    /**
     * The relative path of the parent <code>pom.xml</code> file within the check out.
     *             If not specified, it defaults to <code>../pom.xml</code>.
     *             Maven looks for the parent POM first in this location on
     *             the filesystem, then the local repository, and lastly in the remote repo.
     *             <code>relativePath</code> allows you to select a different location,
     *             for example when your structure is flat, or deeper without an intermediate parent POM.
     *             However, the group ID, artifact ID and version are still required,
     *             and must match the file in the location given or it will revert to the repository for the POM.
     *             This feature is only for enhancing the development in a local checkout of that project.
     *             Set the value to an empty string in case you want to disable the feature and always resolve
     *             the parent POM from the repositories.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * Sets the value of the relativePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRelativePath()
     */
    public void setRelativePath(String value) {
        this.relativePath = value;
    }

}
