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
 * <p>Java class for PolicyCombinerParametersType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PolicyCombinerParametersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}CombinerParametersType"&gt;
 *       &lt;attribute name="PolicyIdRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyCombinerParametersType")
public class PolicyCombinerParametersType
        extends CombinerParametersType {

    @XmlAttribute(name = "PolicyIdRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String policyIdRef;

    /**
     * Gets the value of the policyIdRef property.
     *
     * @return possible object is {@link String }
     */
    public String getPolicyIdRef() {
        return policyIdRef;
    }

    /**
     * Sets the value of the policyIdRef property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPolicyIdRef(String value) {
        this.policyIdRef = value;
    }

}
