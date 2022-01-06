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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ObligationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ObligationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}AttributeAssignment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ObligationId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="FulfillOn" use="required" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}EffectType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObligationType", propOrder = {
        "attributeAssignment"
})
public class ObligationType {

    @XmlElement(name = "AttributeAssignment")
    protected List<AttributeAssignmentType> attributeAssignment;
    @XmlAttribute(name = "ObligationId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String obligationId;
    @XmlAttribute(name = "FulfillOn", required = true)
    protected EffectType fulfillOn;

    /**
     * Gets the value of the attributeAssignment property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the attributeAssignment property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeAssignment().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link AttributeAssignmentType }
     */
    public List<AttributeAssignmentType> getAttributeAssignment() {
        if (attributeAssignment == null) {
            attributeAssignment = new ArrayList<AttributeAssignmentType>();
        }
        return this.attributeAssignment;
    }

    /**
     * Gets the value of the obligationId property.
     *
     * @return possible object is {@link String }
     */
    public String getObligationId() {
        return obligationId;
    }

    /**
     * Sets the value of the obligationId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setObligationId(String value) {
        this.obligationId = value;
    }

    /**
     * Gets the value of the fulfillOn property.
     *
     * @return possible object is {@link EffectType }
     */
    public EffectType getFulfillOn() {
        return fulfillOn;
    }

    /**
     * Sets the value of the fulfillOn property.
     *
     * @param value allowed object is {@link EffectType }
     */
    public void setFulfillOn(EffectType value) {
        this.fulfillOn = value;
    }

}
