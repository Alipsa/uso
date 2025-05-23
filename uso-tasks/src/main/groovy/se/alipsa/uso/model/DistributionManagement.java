
package se.alipsa.uso.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * This elements describes all that pertains to distribution for a project. It is
 *         primarily used for deployment of artifacts and the site produced by the build.
 * 
 * <p>Java class for DistributionManagement complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="DistributionManagement">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="repository" type="{http://maven.apache.org/POM/4.0.0}DeploymentRepository" minOccurs="0"/>
 *         <element name="snapshotRepository" type="{http://maven.apache.org/POM/4.0.0}DeploymentRepository" minOccurs="0"/>
 *         <element name="site" type="{http://maven.apache.org/POM/4.0.0}Site" minOccurs="0"/>
 *         <element name="downloadUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="relocation" type="{http://maven.apache.org/POM/4.0.0}Relocation" minOccurs="0"/>
 *         <element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionManagement", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class DistributionManagement {

    /**
     * Information needed to deploy the artifacts generated by the project to a
     *             remote repository.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected DeploymentRepository repository;
    /**
     * Where to deploy snapshots of artifacts to. If not given, it defaults to the
     *             <code>repository</code> element.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected DeploymentRepository snapshotRepository;
    /**
     * Information needed for deploying the web site of the project.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Site site;
    /**
     * The URL of the project's download page. If not given users will be
     *             referred to the homepage given by <code>url</code>.
     *             This is given to assist in locating artifacts that are not in the repository due to
     *             licensing restrictions.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String downloadUrl;
    /**
     * Relocation information of the artifact if it has been moved to a new group ID
     *             and/or artifact ID.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected Relocation relocation;
    /**
     * Gives the status of this artifact in the remote repository.
     *             This must not be set in your local project, as it is updated by
     *             tools placing it in the reposiory. Valid values are: <code>none</code> (default),
     *             <code>converted</code> (repository manager converted this from an Maven 1 POM),
     *             <code>partner</code>
     *             (directly synced from a partner Maven 2 repository), <code>deployed</code> (was deployed from a Maven 2
     *             instance), <code>verified</code> (has been hand verified as correct and final).
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String status;

    /**
     * Information needed to deploy the artifacts generated by the project to a
     *             remote repository.
     * 
     * @return
     *     possible object is
     *     {@link DeploymentRepository }
     *     
     */
    public DeploymentRepository getRepository() {
        return repository;
    }

    /**
     * Sets the value of the repository property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeploymentRepository }
     *     
     * @see #getRepository()
     */
    public void setRepository(DeploymentRepository value) {
        this.repository = value;
    }

    /**
     * Where to deploy snapshots of artifacts to. If not given, it defaults to the
     *             <code>repository</code> element.
     * 
     * @return
     *     possible object is
     *     {@link DeploymentRepository }
     *     
     */
    public DeploymentRepository getSnapshotRepository() {
        return snapshotRepository;
    }

    /**
     * Sets the value of the snapshotRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeploymentRepository }
     *     
     * @see #getSnapshotRepository()
     */
    public void setSnapshotRepository(DeploymentRepository value) {
        this.snapshotRepository = value;
    }

    /**
     * Information needed for deploying the web site of the project.
     * 
     * @return
     *     possible object is
     *     {@link Site }
     *     
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site }
     *     
     * @see #getSite()
     */
    public void setSite(Site value) {
        this.site = value;
    }

    /**
     * The URL of the project's download page. If not given users will be
     *             referred to the homepage given by <code>url</code>.
     *             This is given to assist in locating artifacts that are not in the repository due to
     *             licensing restrictions.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Sets the value of the downloadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDownloadUrl()
     */
    public void setDownloadUrl(String value) {
        this.downloadUrl = value;
    }

    /**
     * Relocation information of the artifact if it has been moved to a new group ID
     *             and/or artifact ID.
     * 
     * @return
     *     possible object is
     *     {@link Relocation }
     *     
     */
    public Relocation getRelocation() {
        return relocation;
    }

    /**
     * Sets the value of the relocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Relocation }
     *     
     * @see #getRelocation()
     */
    public void setRelocation(Relocation value) {
        this.relocation = value;
    }

    /**
     * Gives the status of this artifact in the remote repository.
     *             This must not be set in your local project, as it is updated by
     *             tools placing it in the reposiory. Valid values are: <code>none</code> (default),
     *             <code>converted</code> (repository manager converted this from an Maven 1 POM),
     *             <code>partner</code>
     *             (directly synced from a partner Maven 2 repository), <code>deployed</code> (was deployed from a Maven 2
     *             instance), <code>verified</code> (has been hand verified as correct and final).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getStatus()
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
