
package se.alipsa.uso.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * The <code>&lt;execution&gt;</code> element contains informations required for the
 *         execution of a plugin.
 * 
 * <p>Java class for PluginExecution complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="PluginExecution">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <all>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="phase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="goals" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="goal" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="inherited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="configuration" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "PluginExecution", namespace = "http://maven.apache.org/POM/4.0.0", propOrder = {

})
public class PluginExecution {

    /**
     * The identifier of this execution for labelling the goals during the build,
     *             and for matching executions to merge during inheritance and profile injection.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "default")
    protected String id;
    /**
     * The build lifecycle phase to bind the goals in this execution to. If omitted,
     *             the goals will be bound to the default phase specified by the plugin.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String phase;
    /**
     * The goals to execute with the given configuration.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected PluginExecution.Goals goals;
    /**
     * Whether any configuration should be propagated to child POMs. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected String inherited;
    /**
     * <p>The configuration as DOM object.</p>
     *             <p>By default, every element content is trimmed, but starting with Maven 3.1.0, you can add
     *             <code>xml:space="preserve"</code> to elements you want to preserve whitespace.</p>
     *             <p>You can control how child POMs inherit configuration from parent POMs by adding <code>combine.children</code>
     *             or <code>combine.self</code> attributes to the children of the configuration element:</p>
     *             <ul>
     *             <li><code>combine.children</code>: available values are <code>merge</code> (default) and <code>append</code>,</li>
     *             <li><code>combine.self</code>: available values are <code>merge</code> (default) and <code>override</code>.</li>
     *             </ul>
     *             <p>See <a href="https://maven.apache.org/pom.html#Plugins">POM Reference documentation</a> and
     *             <a href="https://codehaus-plexus.github.io/plexus-utils/apidocs/org/codehaus/plexus/util/xml/Xpp3DomUtils.html">Xpp3DomUtils</a>
     *             for more information.</p>
     * 
     */
    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
    protected PluginExecution.Configuration configuration;

    /**
     * The identifier of this execution for labelling the goals during the build,
     *             and for matching executions to merge during inheritance and profile injection.
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
     * The build lifecycle phase to bind the goals in this execution to. If omitted,
     *             the goals will be bound to the default phase specified by the plugin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPhase()
     */
    public void setPhase(String value) {
        this.phase = value;
    }

    /**
     * The goals to execute with the given configuration.
     * 
     * @return
     *     possible object is
     *     {@link PluginExecution.Goals }
     *     
     */
    public PluginExecution.Goals getGoals() {
        return goals;
    }

    /**
     * Sets the value of the goals property.
     * 
     * @param value
     *     allowed object is
     *     {@link PluginExecution.Goals }
     *     
     * @see #getGoals()
     */
    public void setGoals(PluginExecution.Goals value) {
        this.goals = value;
    }

    /**
     * Whether any configuration should be propagated to child POMs. Note: While the type
     *             of this field is <code>String</code> for technical reasons, the semantic type is actually
     *             <code>Boolean</code>. Default value is <code>true</code>.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInherited() {
        return inherited;
    }

    /**
     * Sets the value of the inherited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getInherited()
     */
    public void setInherited(String value) {
        this.inherited = value;
    }

    /**
     * <p>The configuration as DOM object.</p>
     *             <p>By default, every element content is trimmed, but starting with Maven 3.1.0, you can add
     *             <code>xml:space="preserve"</code> to elements you want to preserve whitespace.</p>
     *             <p>You can control how child POMs inherit configuration from parent POMs by adding <code>combine.children</code>
     *             or <code>combine.self</code> attributes to the children of the configuration element:</p>
     *             <ul>
     *             <li><code>combine.children</code>: available values are <code>merge</code> (default) and <code>append</code>,</li>
     *             <li><code>combine.self</code>: available values are <code>merge</code> (default) and <code>override</code>.</li>
     *             </ul>
     *             <p>See <a href="https://maven.apache.org/pom.html#Plugins">POM Reference documentation</a> and
     *             <a href="https://codehaus-plexus.github.io/plexus-utils/apidocs/org/codehaus/plexus/util/xml/Xpp3DomUtils.html">Xpp3DomUtils</a>
     *             for more information.</p>
     * 
     * @return
     *     possible object is
     *     {@link PluginExecution.Configuration }
     *     
     */
    public PluginExecution.Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link PluginExecution.Configuration }
     *     
     * @see #getConfiguration()
     */
    public void setConfiguration(PluginExecution.Configuration value) {
        this.configuration = value;
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
     *         <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
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
        "any"
    })
    public static class Configuration {

        @XmlAnyElement
        protected List<Element> any;

        /**
         * Gets the value of the any property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getAny().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Element }
         * </p>
         * 
         * 
         * @return
         *     The value of the any property.
         */
        public List<Element> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return this.any;
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
     *         <element name="goal" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "goal"
    })
    public static class Goals {

        @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0")
        protected List<String> goal;

        /**
         * Gets the value of the goal property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the goal property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getGoal().add(newItem);
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
         *     The value of the goal property.
         */
        public List<String> getGoal() {
            if (goal == null) {
                goal = new ArrayList<>();
            }
            return this.goal;
        }

    }

}
