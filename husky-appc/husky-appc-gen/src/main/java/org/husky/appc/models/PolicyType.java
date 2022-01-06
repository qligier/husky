/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.models;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PolicyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PolicyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Description" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}PolicyDefaults" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}CombinerParameters" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Target"/&gt;
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}CombinerParameters" minOccurs="0"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}RuleCombinerParameters" minOccurs="0"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VariableDefinition"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Rule"/&gt;
 *         &lt;/choice>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Obligations" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="PolicyId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="Version" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VersionType" default="1.0" /&gt;
 *       &lt;attribute name="RuleCombiningAlgId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyType", propOrder = {
        "description",
        "policyDefaults",
        "combinerParameters",
        "target",
        "combinerParametersOrRuleCombinerParametersOrVariableDefinition",
        "obligations"
})
public class PolicyType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "PolicyDefaults")
    protected DefaultsType policyDefaults;
    @XmlElement(name = "CombinerParameters")
    protected CombinerParametersType combinerParameters;
    @XmlElement(name = "Target", required = true)
    protected TargetType target;
    @XmlElements({
            @XmlElement(name = "CombinerParameters", type = CombinerParametersType.class),
            @XmlElement(name = "RuleCombinerParameters", type = RuleCombinerParametersType.class),
            @XmlElement(name = "VariableDefinition", type = VariableDefinitionType.class),
            @XmlElement(name = "Rule", type = RuleType.class)
    })
    protected List<Object> combinerParametersOrRuleCombinerParametersOrVariableDefinition;
    @XmlElement(name = "Obligations")
    protected ObligationsType obligations;
    @XmlAttribute(name = "PolicyId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String policyId;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "RuleCombiningAlgId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String ruleCombiningAlgId;

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the policyDefaults property.
     *
     * @return possible object is {@link DefaultsType }
     */
    public DefaultsType getPolicyDefaults() {
        return policyDefaults;
    }

    /**
     * Sets the value of the policyDefaults property.
     *
     * @param value allowed object is {@link DefaultsType }
     */
    public void setPolicyDefaults(DefaultsType value) {
        this.policyDefaults = value;
    }

    /**
     * Gets the value of the combinerParameters property.
     *
     * @return possible object is {@link CombinerParametersType }
     */
    public CombinerParametersType getCombinerParameters() {
        return combinerParameters;
    }

    /**
     * Sets the value of the combinerParameters property.
     *
     * @param value allowed object is {@link CombinerParametersType }
     */
    public void setCombinerParameters(CombinerParametersType value) {
        this.combinerParameters = value;
    }

    /**
     * Gets the value of the target property.
     *
     * @return possible object is {@link TargetType }
     */
    public TargetType getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     *
     * @param value allowed object is {@link TargetType }
     */
    public void setTarget(TargetType value) {
        this.target = value;
    }

    /**
     * Gets the value of the combinerParametersOrRuleCombinerParametersOrVariableDefinition property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the combinerParametersOrRuleCombinerParametersOrVariableDefinition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCombinerParametersOrRuleCombinerParametersOrVariableDefinition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link CombinerParametersType } {@link
     * RuleCombinerParametersType } {@link VariableDefinitionType } {@link RuleType }
     */
    @NonNull
    public List<Object> getCombinerParametersOrRuleCombinerParametersOrVariableDefinition() {
        if (combinerParametersOrRuleCombinerParametersOrVariableDefinition == null) {
            combinerParametersOrRuleCombinerParametersOrVariableDefinition = new ArrayList<>();
        }
        return this.combinerParametersOrRuleCombinerParametersOrVariableDefinition;
    }

    /**
     * Gets the value of the obligations property.
     *
     * @return possible object is {@link ObligationsType }
     */
    public ObligationsType getObligations() {
        return obligations;
    }

    /**
     * Sets the value of the obligations property.
     *
     * @param value allowed object is {@link ObligationsType }
     */
    public void setObligations(ObligationsType value) {
        this.obligations = value;
    }

    /**
     * Gets the value of the policyId property.
     *
     * @return possible object is {@link String }
     */
    public String getPolicyId() {
        return policyId;
    }

    /**
     * Sets the value of the policyId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPolicyId(String value) {
        this.policyId = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link String }
     */
    @NonNull
    public String getVersion() {
        if (version == null) {
            return "1.0";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the ruleCombiningAlgId property.
     *
     * @return possible object is {@link String }
     */
    public String getRuleCombiningAlgId() {
        return ruleCombiningAlgId;
    }

    /**
     * Sets the value of the ruleCombiningAlgId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRuleCombiningAlgId(String value) {
        this.ruleCombiningAlgId = value;
    }

}
