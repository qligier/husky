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
import org.husky.appc.AppcUrns;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.util.*;


/**
 * <p>Java class for AttributeValueType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AttributeValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ExpressionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DataType" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeValueType", propOrder = {
        "content"
})
@XmlSeeAlso({
        AttributeAssignmentType.class
})
public class AttributeValueType {

    @XmlMixed
    @XmlAnyElement(lax = true)
    protected List<Object> content;
    @XmlAttribute(name = "DataType", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String dataType;
    @XmlAnyAttribute
    private final Map<QName, String> otherAttributes = new HashMap<>();

    public AttributeValueType() {
    }

    public AttributeValueType(@NonNull final String value) {
        this.dataType = AppcUrns.XS_STRING;
        this.content = new ArrayList<>(List.of(Objects.requireNonNull(value)));
    }

    public AttributeValueType(@NonNull final String value,
                              @NonNull final String dataType) {
        this.dataType = Objects.requireNonNull(dataType);
        this.content = new ArrayList<>(List.of(Objects.requireNonNull(value)));
    }

    public AttributeValueType(@NonNull final II instanceIdentifier) {
        this.dataType = AppcUrns.II;
        final var element = new ObjectFactory().createInstanceIdentifier(Objects.requireNonNull(instanceIdentifier));
        this.content = new ArrayList<>(List.of(element));
    }

    public AttributeValueType(@NonNull final CV codedValue) {
        this.dataType = AppcUrns.CV;
        final var element = new ObjectFactory().createCodedValue(Objects.requireNonNull(codedValue));
        this.content = new ArrayList<>(List.of(element));
    }

    public AttributeValueType(@NonNull final LocalDate date) {
        this.dataType = AppcUrns.XS_DATE;
        this.content = new ArrayList<>(List.of(Objects.requireNonNull(String.format(
                "%1$tY-%1$tm-%1$td", date))));
    }

    /**
     * Gets the value of the content property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the content property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Element } {@link String } {@link Object }
     */
    @NonNull
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return this.content;
    }

    @NonNull
    public Optional<String> getStringContent() {
        return this.getContent().stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .findAny();
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
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and the value is the string value of the attribute.
     * <p>
     * the map returned by this method is live, and you can add new attribute by updating the map directly. Because of
     * this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public boolean isIi() {
        return AppcUrns.II.equals(this.dataType);
    }

    @NonNull
    public Optional<II> getSingleIi() {
        return !isIi() ? Optional.empty() : this.getContent().stream()
                .filter(JAXBElement.class::isInstance)
                .map(JAXBElement.class::cast)
                .map(JAXBElement::getValue)
                .filter(II.class::isInstance)
                .map(II.class::cast)
                .findAny();
    }
}
