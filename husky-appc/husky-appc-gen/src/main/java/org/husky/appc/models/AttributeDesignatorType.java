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
 * <p>Java class for AttributeDesignatorType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AttributeDesignatorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType"&gt;
 *       &lt;attribute name="AttributeId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="DataType" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="Issuer" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MustBePresent" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeDesignatorType")
@XmlSeeAlso({
        SubjectAttributeDesignatorType.class
})
public class AttributeDesignatorType
        extends ExpressionType {

    @XmlAttribute(name = "AttributeId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String attributeId;
    @XmlAttribute(name = "DataType", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String dataType;
    @XmlAttribute(name = "Issuer")
    protected String issuer;
    @XmlAttribute(name = "MustBePresent")
    protected Boolean mustBePresent;

    public AttributeDesignatorType() {
    }

    public AttributeDesignatorType(@NonNull final String attributeId,
                                   @NonNull final String dataType) {
        this.attributeId = Objects.requireNonNull(attributeId);
        this.dataType = Objects.requireNonNull(dataType);
    }

    /**
     * Gets the value of the attributeId property.
     *
     * @return possible object is {@link String }
     */
    public String getAttributeId() {
        return attributeId;
    }

    /**
     * Sets the value of the attributeId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setAttributeId(String value) {
        this.attributeId = value;
    }

    /**
     * Gets the value of the dataType property.
     *
     * @return possible object is {@link String }
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the issuer property.
     *
     * @return possible object is {@link String }
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     *
     * @param value allowed object is {@link String }
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the mustBePresent property.
     *
     * @return possible object is {@link Boolean }
     */
    @NonNull
    public Boolean getMustBePresent() {
        if (mustBePresent == null) {
            return false;
        } else {
            return mustBePresent;
        }
    }

    /**
     * Sets the value of the mustBePresent property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setMustBePresent(Boolean value) {
        this.mustBePresent = value;
    }

}
