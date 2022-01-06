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
import java.util.Objects;


/**
 * <p>Java class for TargetType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TargetType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Subjects" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Resources" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Actions" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Environments" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TargetType", propOrder = {
        "subjects",
        "resources",
        "actions",
        "environments"
})
public class TargetType {

    @XmlElement(name = "Subjects")
    protected SubjectsType subjects;
    @XmlElement(name = "Resources")
    protected ResourcesType resources;
    @XmlElement(name = "Actions")
    protected ActionsType actions;
    @XmlElement(name = "Environments")
    protected EnvironmentsType environments;

    public TargetType() {
    }

    public TargetType(@NonNull final SubjectsType subjects) {
        this.subjects = Objects.requireNonNull(subjects);
    }

    public TargetType(@NonNull final ResourcesType resources) {
        this.resources = Objects.requireNonNull(resources);
    }

    /**
     * Gets the value of the subjects property.
     *
     * @return possible object is {@link SubjectsType }
     */
    public SubjectsType getSubjects() {
        return subjects;
    }

    /**
     * Sets the value of the subjects property.
     *
     * @param value allowed object is {@link SubjectsType }
     */
    public void setSubjects(SubjectsType value) {
        this.subjects = value;
    }

    /**
     * Gets the value of the resources property.
     *
     * @return possible object is {@link ResourcesType }
     */
    public ResourcesType getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     *
     * @param value allowed object is {@link ResourcesType }
     */
    public void setResources(ResourcesType value) {
        this.resources = value;
    }

    /**
     * Gets the value of the actions property.
     *
     * @return possible object is {@link ActionsType }
     */
    public ActionsType getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     *
     * @param value allowed object is {@link ActionsType }
     */
    public void setActions(ActionsType value) {
        this.actions = value;
    }

    /**
     * Gets the value of the environments property.
     *
     * @return possible object is {@link EnvironmentsType }
     */
    public EnvironmentsType getEnvironments() {
        return environments;
    }

    /**
     * Sets the value of the environments property.
     *
     * @param value allowed object is {@link EnvironmentsType }
     */
    public void setEnvironments(EnvironmentsType value) {
        this.environments = value;
    }

}
