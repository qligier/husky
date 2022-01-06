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


/**
 * <p>Java class for AttributeSelectorType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AttributeSelectorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType"&gt;
 *       &lt;attribute name="RequestContextPath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="DataType" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="MustBePresent" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeSelectorType")
public class AttributeSelectorType
        extends ExpressionType {

    @XmlAttribute(name = "RequestContextPath", required = true)
    protected String requestContextPath;
    @XmlAttribute(name = "DataType", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String dataType;
    @XmlAttribute(name = "MustBePresent")
    protected Boolean mustBePresent;

    /**
     * Gets the value of the requestContextPath property.
     *
     * @return possible object is {@link String }
     */
    public String getRequestContextPath() {
        return requestContextPath;
    }

    /**
     * Sets the value of the requestContextPath property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRequestContextPath(String value) {
        this.requestContextPath = value;
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
