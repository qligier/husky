/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2020.07.09 um 01:07:39 PM CEST
//

package org.husky.emed.cda.generated.hl7cdar2;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für POCD_MT000040.Entry complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="POCD_MT000040.Entry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         &lt;element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="act" type="{urn:hl7-org:v3}POCD_MT000040.Act"/>
 *           &lt;element name="encounter" type="{urn:hl7-org:v3}POCD_MT000040.Encounter"/>
 *           &lt;element name="observation" type="{urn:hl7-org:v3}POCD_MT000040.Observation"/>
 *           &lt;element name="observationMedia" type="{urn:hl7-org:v3}POCD_MT000040.ObservationMedia"/>
 *           &lt;element name="organizer" type="{urn:hl7-org:v3}POCD_MT000040.Organizer"/>
 *           &lt;element name="procedure" type="{urn:hl7-org:v3}POCD_MT000040.Procedure"/>
 *           &lt;element name="regionOfInterest" type="{urn:hl7-org:v3}POCD_MT000040.RegionOfInterest"/>
 *           &lt;element name="substanceAdministration" type="{urn:hl7-org:v3}POCD_MT000040.SubstanceAdministration"/>
 *           &lt;element name="supply" type="{urn:hl7-org:v3}POCD_MT000040.Supply"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="typeCode" type="{urn:hl7-org:v3}x_ActRelationshipEntry" default="COMP" />
 *       &lt;attribute name="contextConductionInd" type="{urn:hl7-org:v3}bl" fixed="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Entry", propOrder = { "realmCode", "typeId", "templateId", "act",
        "encounter", "observation", "observationMedia", "organizer", "procedure",
        "regionOfInterest", "substanceAdministration", "supply" })
public class POCDMT000040Entry {

    protected List<CS> realmCode;
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    protected POCDMT000040Act act;
    protected POCDMT000040Encounter encounter;
    protected POCDMT000040Observation observation;
    protected POCDMT000040ObservationMedia observationMedia;
    protected POCDMT000040Organizer organizer;
    protected POCDMT000040Procedure procedure;
    protected POCDMT000040RegionOfInterest regionOfInterest;
    protected POCDMT000040SubstanceAdministration substanceAdministration;
    protected POCDMT000040Supply supply;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "typeCode")
    protected XActRelationshipEntry typeCode;
    @XmlAttribute(name = "contextConductionInd")
    protected Boolean contextConductionInd;

    /**
     * Ruft den Wert der act-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Act }
     *
     */
    public POCDMT000040Act getAct() {
        return act;
    }

    /**
     * Ruft den Wert der encounter-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Encounter }
     *
     */
    public POCDMT000040Encounter getEncounter() {
        return encounter;
    }

    /**
     * Gets the value of the nullFlavor property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the nullFlavor property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     */
    @NonNull
    public List<String> getNullFlavor() {
        if (nullFlavor == null) {
            nullFlavor = new ArrayList<String>();
        }
        return this.nullFlavor;
    }

    /**
     * Ruft den Wert der observation-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Observation }
     *
     */
    public POCDMT000040Observation getObservation() {
        return observation;
    }

    /**
     * Ruft den Wert der observationMedia-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040ObservationMedia }
     *
     */
    public POCDMT000040ObservationMedia getObservationMedia() {
        return observationMedia;
    }

    /**
     * Ruft den Wert der organizer-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Organizer }
     *
     */
    public POCDMT000040Organizer getOrganizer() {
        return organizer;
    }

    /**
     * Ruft den Wert der procedure-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Procedure }
     *
     */
    public POCDMT000040Procedure getProcedure() {
        return procedure;
    }

    /**
     * Gets the value of the realmCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the realmCode property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link CS }
     *
     *
     */
    @NonNull
    public List<CS> getRealmCode() {
        if (realmCode == null) {
            realmCode = new ArrayList<CS>();
        }
        return this.realmCode;
    }

    /**
     * Ruft den Wert der regionOfInterest-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040RegionOfInterest }
     *
     */
    public POCDMT000040RegionOfInterest getRegionOfInterest() {
        return regionOfInterest;
    }

    /**
     * Ruft den Wert der substanceAdministration-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040SubstanceAdministration }
     *
     */
    public POCDMT000040SubstanceAdministration getSubstanceAdministration() {
        return substanceAdministration;
    }

    /**
     * Ruft den Wert der supply-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040Supply }
     *
     */
    public POCDMT000040Supply getSupply() {
        return supply;
    }

    /**
     * Gets the value of the templateId property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the templateId property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link II }
     *
     *
     */
    @NonNull
    public List<II> getTemplateId() {
        if (templateId == null) {
            templateId = new ArrayList<II>();
        }
        return this.templateId;
    }

    /**
     * Ruft den Wert der typeCode-Eigenschaft ab.
     *
     * @return possible object is {@link XActRelationshipEntry }
     *
     */
    @NonNull
    public XActRelationshipEntry getTypeCode() {
        if (typeCode == null) {
            return XActRelationshipEntry.COMP;
        } else {
            return typeCode;
        }
    }

    /**
     * Ruft den Wert der typeId-Eigenschaft ab.
     *
     * @return possible object is {@link POCDMT000040InfrastructureRootTypeId }
     *
     */
    public POCDMT000040InfrastructureRootTypeId getTypeId() {
        return typeId;
    }

    /**
     * Ruft den Wert der contextConductionInd-Eigenschaft ab.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public boolean isContextConductionInd() {
        if (contextConductionInd == null) {
            return true;
        } else {
            return contextConductionInd;
        }
    }

    /**
     * Legt den Wert der act-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Act }
     *
     */
    public void setAct(POCDMT000040Act value) {
        this.act = value;
    }

    /**
     * Legt den Wert der contextConductionInd-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link Boolean }
     *
     */
    public void setContextConductionInd(Boolean value) {
        this.contextConductionInd = value;
    }

    /**
     * Legt den Wert der encounter-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Encounter }
     *
     */
    public void setEncounter(POCDMT000040Encounter value) {
        this.encounter = value;
    }

    /**
     * Legt den Wert der observation-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Observation }
     *
     */
    public void setObservation(POCDMT000040Observation value) {
        this.observation = value;
    }

    /**
     * Legt den Wert der observationMedia-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040ObservationMedia }
     *
     */
    public void setObservationMedia(POCDMT000040ObservationMedia value) {
        this.observationMedia = value;
    }

    /**
     * Legt den Wert der organizer-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Organizer }
     *
     */
    public void setOrganizer(POCDMT000040Organizer value) {
        this.organizer = value;
    }

    /**
     * Legt den Wert der procedure-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Procedure }
     *
     */
    public void setProcedure(POCDMT000040Procedure value) {
        this.procedure = value;
    }

    /**
     * Legt den Wert der regionOfInterest-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040RegionOfInterest }
     *
     */
    public void setRegionOfInterest(POCDMT000040RegionOfInterest value) {
        this.regionOfInterest = value;
    }

    /**
     * Legt den Wert der substanceAdministration-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040SubstanceAdministration }
     *
     */
    public void setSubstanceAdministration(POCDMT000040SubstanceAdministration value) {
        this.substanceAdministration = value;
    }

    /**
     * Legt den Wert der supply-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040Supply }
     *
     */
    public void setSupply(POCDMT000040Supply value) {
        this.supply = value;
    }

    /**
     * Legt den Wert der typeCode-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link XActRelationshipEntry }
     *
     */
    public void setTypeCode(XActRelationshipEntry value) {
        this.typeCode = value;
    }

    /**
     * Legt den Wert der typeId-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link POCDMT000040InfrastructureRootTypeId
     *            }
     *
     */
    public void setTypeId(POCDMT000040InfrastructureRootTypeId value) {
        this.typeId = value;
    }

}