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
 * <p>Java class for FunctionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FunctionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType"&gt;
 *       &lt;attribute name="FunctionId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FunctionType")
public class FunctionType
        extends ExpressionType {

    @XmlAttribute(name = "FunctionId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String functionId;

    /**
     * Gets the value of the functionId property.
     *
     * @return possible object is {@link String }
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * Sets the value of the functionId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFunctionId(String value) {
        this.functionId = value;
    }

}
