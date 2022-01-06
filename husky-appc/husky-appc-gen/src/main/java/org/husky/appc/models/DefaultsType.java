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
 * <p>Java class for DefaultsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DefaultsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice>
 *           &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}XPathVersion"/&gt;
 *         &lt;/choice>
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefaultsType", propOrder = {
        "xPathVersion"
})
public class DefaultsType {

    @XmlElement(name = "XPathVersion")
    @XmlSchemaType(name = "anyURI")
    protected String xPathVersion;

    /**
     * Gets the value of the xPathVersion property.
     *
     * @return possible object is {@link String }
     */
    public String getXPathVersion() {
        return xPathVersion;
    }

    /**
     * Sets the value of the xPathVersion property.
     *
     * @param value allowed object is {@link String }
     */
    public void setXPathVersion(String value) {
        this.xPathVersion = value;
    }

}
