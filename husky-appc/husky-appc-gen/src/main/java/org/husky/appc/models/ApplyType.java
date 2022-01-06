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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ApplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ApplyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Expression" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="FunctionId" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplyType", propOrder = {
        "expression"
})
public class ApplyType
        extends ExpressionType {

    @XmlElementRef(name = "Expression", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class, required = false)
    protected List<JAXBElement<?>> expression;
    @XmlAttribute(name = "FunctionId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String functionId;

    /**
     * Gets the value of the expression property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the expression property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpression().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link JAXBElement }{@code <}{@link AttributeValueType
     * }{@code >} {@link JAXBElement }{@code <}{@link AttributeSelectorType }{@code >} {@link JAXBElement }{@code
     * <}{@link SubjectAttributeDesignatorType }{@code >} {@link JAXBElement }{@code <}{@link AttributeDesignatorType
     * }{@code >} {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >} {@link JAXBElement }{@code
     * <}{@link ApplyType }{@code >} {@link JAXBElement }{@code <}{@link FunctionType }{@code >} {@link JAXBElement
     * }{@code <}{@link ExpressionType }{@code >} {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     */
    @NonNull
    public List<JAXBElement<?>> getExpression() {
        if (expression == null) {
            expression = new ArrayList<>();
        }
        return this.expression;
    }

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
