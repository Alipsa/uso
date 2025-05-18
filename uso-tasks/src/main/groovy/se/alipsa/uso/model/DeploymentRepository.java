
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Repository contains the information needed for deploying to the remote
 *         repository.
 * 
 * <p>Java class for DeploymentRepository complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="DeploymentRepository">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="uniqueVersion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="releases" type="{http://maven.apache.org/POM/4.0.0}RepositoryPolicy" minOccurs="0"/>
 *         <element name="snapshots" type="{http://maven.apache.org/POM/4.0.0}RepositoryPolicy" minOccurs="0"/>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="layout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeploymentRepository", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class DeploymentRepository {

    /**
     * Whether to assign snapshots a unique version comprised of the timestamp and
     *             build number, or to use the same version each time
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "true")
    protected Boolean uniqueVersion;
    /**
     * How to handle downloading of releases from this repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected RepositoryPolicy releases;
    /**
     * How to handle downloading of snapshots from this repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected RepositoryPolicy snapshots;
    /**
     * A unique identifier for a repository. This is used to match the repository
     *             to configuration in the <code>settings.xml</code> file, for example. Furthermore, the identifier is
     *             used during POM inheritance and profile injection to detect repositories that should be merged.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String id;
    /**
     * Human readable name of the repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String name;
    /**
     * The url of the repository, in the form <code>protocol://hostname/path</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String url;
    /**
     * The type of layout this repository uses for locating and storing artifacts -
     *             can be <code>legacy</code> or <code>default</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "default")
    protected String layout;

    /**
     * Whether to assign snapshots a unique version comprised of the timestamp and
     *             build number, or to use the same version each time
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUniqueVersion() {
        return uniqueVersion;
    }

    /**
     * Sets the value of the uniqueVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isUniqueVersion()
     */
    public void setUniqueVersion(Boolean value) {
        this.uniqueVersion = value;
    }

    /**
     * How to handle downloading of releases from this repository.
     * 
     * @return
     *     possible object is
     *     {@link RepositoryPolicy }
     *     
     */
    public RepositoryPolicy getReleases() {
        return releases;
    }

    /**
     * Sets the value of the releases property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepositoryPolicy }
     *     
     * @see #getReleases()
     */
    public void setReleases(RepositoryPolicy value) {
        this.releases = value;
    }

    /**
     * How to handle downloading of snapshots from this repository.
     * 
     * @return
     *     possible object is
     *     {@link RepositoryPolicy }
     *     
     */
    public RepositoryPolicy getSnapshots() {
        return snapshots;
    }

    /**
     * Sets the value of the snapshots property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepositoryPolicy }
     *     
     * @see #getSnapshots()
     */
    public void setSnapshots(RepositoryPolicy value) {
        this.snapshots = value;
    }

    /**
     * A unique identifier for a repository. This is used to match the repository
     *             to configuration in the <code>settings.xml</code> file, for example. Furthermore, the identifier is
     *             used during POM inheritance and profile injection to detect repositories that should be merged.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Human readable name of the repository.
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
     * The url of the repository, in the form <code>protocol://hostname/path</code>.
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
     * The type of layout this repository uses for locating and storing artifacts -
     *             can be <code>legacy</code> or <code>default</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayout() {
        return layout;
    }

    /**
     * Sets the value of the layout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLayout()
     */
    public void setLayout(String value) {
        this.layout = value;
    }

}
