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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConditionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ConditionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Expression"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionType", propOrder = {
        "expression"
})
public class ConditionType {

    @XmlElementRef(name = "Expression", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class)
    protected JAXBElement<?> expression;

    /**
     * Gets the value of the expression property.
     *
     * @return possible object is {@link JAXBElement }{@code <}{@link AttributeValueType }{@code >} {@link JAXBElement
     * }{@code <}{@link AttributeSelectorType }{@code >} {@link JAXBElement }{@code <}{@link
     * SubjectAttributeDesignatorType }{@code >} {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >} {@link JAXBElement }{@code <}{@link
     * ApplyType }{@code >} {@link JAXBElement }{@code <}{@link FunctionType }{@code >} {@link JAXBElement }{@code
     * <}{@link ExpressionType }{@code >} {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >} {@link
     * JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     */
    public JAXBElement<?> getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     *
     * @param value allowed object is {@link JAXBElement }{@code <}{@link AttributeValueType }{@code >} {@link
     *              JAXBElement }{@code <}{@link AttributeSelectorType }{@code >} {@link JAXBElement }{@code <}{@link
     *              SubjectAttributeDesignatorType }{@code >} {@link JAXBElement }{@code <}{@link
     *              AttributeDesignatorType }{@code >} {@link JAXBElement }{@code <}{@link AttributeDesignatorType
     *              }{@code >} {@link JAXBElement }{@code <}{@link ApplyType }{@code >} {@link JAXBElement }{@code
     *              <}{@link FunctionType }{@code >} {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}
     *              {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >} {@link JAXBElement }{@code
     *              <}{@link AttributeDesignatorType }{@code >}
     */
    public void setExpression(JAXBElement<?> value) {
        this.expression = value;
    }

}
