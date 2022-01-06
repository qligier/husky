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
 * <p>Java class for ResourceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResourceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}ResourceMatch" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceType", propOrder = {
        "resourceMatch"
})
public class ResourceType {

    @XmlElement(name = "ResourceMatch", required = true)
    protected List<ResourceMatchType> resourceMatch;

    public ResourceType() {
    }

    public ResourceType(@NonNull final List<ResourceMatchType> resourceMatches) {
        this.resourceMatch = Objects.requireNonNull(resourceMatches);
    }

    public ResourceType(@NonNull final ResourceMatchType resourceMatch) {
        this.resourceMatch = new ArrayList<>(List.of(Objects.requireNonNull(resourceMatch)));
    }

    /**
     * Gets the value of the resourceMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the resourceMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link ResourceMatchType }
     */
    @NonNull
    public List<ResourceMatchType> getResourceMatch() {
        if (resourceMatch == null) {
            resourceMatch = new ArrayList<>();
        }
        return this.resourceMatch;
    }

}
