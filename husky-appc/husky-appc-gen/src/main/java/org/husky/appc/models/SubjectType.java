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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * <p>Java class for SubjectType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SubjectType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}SubjectMatch" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectType", propOrder = {
        "subjectMatch"
})
public class SubjectType {

    @XmlElement(name = "SubjectMatch", required = true)
    protected List<SubjectMatchType> subjectMatch;

    public SubjectType() {
    }

    public SubjectType(@NonNull final List<SubjectMatchType> subjectMatch) {
        this.subjectMatch = Objects.requireNonNull(subjectMatch);
    }

    /**
     * Gets the value of the subjectMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the subjectMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link SubjectMatchType }
     */
    @NonNull
    public List<SubjectMatchType> getSubjectMatch() {
        if (subjectMatch == null) {
            subjectMatch = new ArrayList<>();
        }
        return this.subjectMatch;
    }

}
