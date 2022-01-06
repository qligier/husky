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

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for RuleType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RuleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Description" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Target" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Condition" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="RuleId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Effect" use="required" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}EffectType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RuleType", propOrder = {
        "description",
        "target",
        "condition"
})
public class RuleType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Target")
    protected TargetType target;
    @XmlElement(name = "Condition")
    protected ConditionType condition;
    @XmlAttribute(name = "RuleId", required = true)
    protected String ruleId;
    @XmlAttribute(name = "Effect", required = true)
    protected EffectType effect;

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
     * Gets the value of the condition property.
     *
     * @return possible object is {@link ConditionType }
     */
    public ConditionType getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     *
     * @param value allowed object is {@link ConditionType }
     */
    public void setCondition(ConditionType value) {
        this.condition = value;
    }

    /**
     * Gets the value of the ruleId property.
     *
     * @return possible object is {@link String }
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * Sets the value of the ruleId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRuleId(String value) {
        this.ruleId = value;
    }

    /**
     * Gets the value of the effect property.
     *
     * @return possible object is {@link EffectType }
     */
    public EffectType getEffect() {
        return effect;
    }

    /**
     * Sets the value of the effect property.
     *
     * @param value allowed object is {@link EffectType }
     */
    public void setEffect(EffectType value) {
        this.effect = value;
    }

}
