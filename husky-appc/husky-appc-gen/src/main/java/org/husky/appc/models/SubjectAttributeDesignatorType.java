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
 * <p>Java class for SubjectAttributeDesignatorType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SubjectAttributeDesignatorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}AttributeDesignatorType"&gt;
 *       &lt;attribute name="SubjectCategory" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectAttributeDesignatorType")
public class SubjectAttributeDesignatorType
        extends AttributeDesignatorType {

    @XmlAttribute(name = "SubjectCategory")
    @XmlSchemaType(name = "anyURI")
    protected String subjectCategory;

    public SubjectAttributeDesignatorType() {
        super();
    }

    public SubjectAttributeDesignatorType(@NonNull final String attributeId,
                                          @NonNull final String dataType) {
        super(attributeId, dataType);
    }

    /**
     * Gets the value of the subjectCategory property.
     *
     * @return possible object is {@link String }
     */
    @NonNull
    public String getSubjectCategory() {
        if (subjectCategory == null) {
            return "urn:oasis:names:tc:xacml:1.0:subject-category:access-subject";
        } else {
            return subjectCategory;
        }
    }

    /**
     * Sets the value of the subjectCategory property.
     *
     * @param value allowed object is {@link String }
     */
    public void setSubjectCategory(String value) {
        this.subjectCategory = value;
    }

}
