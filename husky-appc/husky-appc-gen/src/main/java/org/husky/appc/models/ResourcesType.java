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
 * <p>Java class for ResourcesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResourcesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Resource" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourcesType", propOrder = {
        "resource"
})
public class ResourcesType {

    @XmlElement(name = "Resource", required = true)
    protected List<ResourceType> resource;

    public ResourcesType() {
    }

    public ResourcesType(@NonNull final List<ResourceType> resources) {
        this.resource = Objects.requireNonNull(resources);
    }

    public ResourcesType(@NonNull final ResourceType resource) {
        this.resource = new ArrayList<>(List.of(Objects.requireNonNull(resource)));
    }

    /**
     * Gets the value of the resource property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the resource property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResource().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link ResourceType }
     */
    @NonNull
    public List<ResourceType> getResource() {
        if (resource == null) {
            resource = new ArrayList<>();
        }
        return this.resource;
    }

}
