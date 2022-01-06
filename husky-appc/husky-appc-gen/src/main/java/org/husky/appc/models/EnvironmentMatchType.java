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
import java.util.Objects;


/**
 * <p>Java class for EnvironmentMatchType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EnvironmentMatchType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}AttributeValue"/&gt;
 *         &lt;choice>
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}EnvironmentAttributeDesignator"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}AttributeSelector"/&gt;
 *         &lt;/choice>
 *       &lt;/sequence&gt;
 *       &lt;attribute name="MatchId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvironmentMatchType", propOrder = {
        "attributeValue",
        "environmentAttributeDesignator",
        "attributeSelector"
})
public class EnvironmentMatchType {

    @XmlElement(name = "AttributeValue", required = true)
    protected AttributeValueType attributeValue;
    @XmlElement(name = "EnvironmentAttributeDesignator")
    protected AttributeDesignatorType environmentAttributeDesignator;
    @XmlElement(name = "AttributeSelector")
    protected AttributeSelectorType attributeSelector;
    @XmlAttribute(name = "MatchId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String matchId;

    public EnvironmentMatchType() {
    }

    public EnvironmentMatchType(@NonNull final AttributeValueType attributeValue,
                                @NonNull final AttributeDesignatorType environmentAttributeDesignator,
                                @NonNull final String matchId) {
        this.attributeValue = Objects.requireNonNull(attributeValue);
        this.environmentAttributeDesignator = Objects.requireNonNull(environmentAttributeDesignator);
        this.matchId = Objects.requireNonNull(matchId);
    }

    /**
     * Gets the value of the attributeValue property.
     *
     * @return possible object is {@link AttributeValueType }
     */
    public AttributeValueType getAttributeValue() {
        return attributeValue;
    }

    /**
     * Sets the value of the attributeValue property.
     *
     * @param value allowed object is {@link AttributeValueType }
     */
    public void setAttributeValue(AttributeValueType value) {
        this.attributeValue = value;
    }

    /**
     * Gets the value of the environmentAttributeDesignator property.
     *
     * @return possible object is {@link AttributeDesignatorType }
     */
    public AttributeDesignatorType getEnvironmentAttributeDesignator() {
        return environmentAttributeDesignator;
    }

    /**
     * Sets the value of the environmentAttributeDesignator property.
     *
     * @param value allowed object is {@link AttributeDesignatorType }
     */
    public void setEnvironmentAttributeDesignator(AttributeDesignatorType value) {
        this.environmentAttributeDesignator = value;
    }

    /**
     * Gets the value of the attributeSelector property.
     *
     * @return possible object is {@link AttributeSelectorType }
     */
    public AttributeSelectorType getAttributeSelector() {
        return attributeSelector;
    }

    /**
     * Sets the value of the attributeSelector property.
     *
     * @param value allowed object is {@link AttributeSelectorType }
     */
    public void setAttributeSelector(AttributeSelectorType value) {
        this.attributeSelector = value;
    }

    /**
     * Gets the value of the matchId property.
     *
     * @return possible object is {@link String }
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Sets the value of the matchId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setMatchId(String value) {
        this.matchId = value;
    }

}
