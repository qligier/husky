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
 * <p>Java class for EnvironmentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EnvironmentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}EnvironmentMatch" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvironmentType", propOrder = {
        "environmentMatch"
})
public class EnvironmentType {

    @XmlElement(name = "EnvironmentMatch", required = true)
    protected List<EnvironmentMatchType> environmentMatch;

    public EnvironmentType() {
    }

    public EnvironmentType(@NonNull final EnvironmentMatchType environmentMatch) {
        this.environmentMatch = new ArrayList<>(List.of(Objects.requireNonNull(environmentMatch)));
    }

    public EnvironmentType(@NonNull final List<EnvironmentMatchType> environmentMatches) {
        this.environmentMatch = Objects.requireNonNull(environmentMatches);
    }

    /**
     * Gets the value of the environmentMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the environmentMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvironmentMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link EnvironmentMatchType }
     */
    @NonNull
    public List<EnvironmentMatchType> getEnvironmentMatch() {
        if (environmentMatch == null) {
            environmentMatch = new ArrayList<EnvironmentMatchType>();
        }
        return this.environmentMatch;
    }

}
