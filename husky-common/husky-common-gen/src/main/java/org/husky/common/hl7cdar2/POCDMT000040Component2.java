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

package org.husky.common.hl7cdar2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für POCD_MT000040.Component2 complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="POCD_MT000040.Component2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         &lt;element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="nonXMLBody" type="{urn:hl7-org:v3}POCD_MT000040.NonXMLBody"/>
 *           &lt;element name="structuredBody" type="{urn:hl7-org:v3}POCD_MT000040.StructuredBody"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="typeCode" type="{urn:hl7-org:v3}ActRelationshipHasComponent" fixed="COMP" />
 *       &lt;attribute name="contextConductionInd" type="{urn:hl7-org:v3}bl" fixed="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Component2", propOrder = { "realmCode", "typeId", "templateId",
		"nonXMLBody", "structuredBody" })
public class POCDMT000040Component2 {

	protected List<CS> realmCode;
	protected POCDMT000040InfrastructureRootTypeId typeId;
	protected List<II> templateId;
	protected POCDMT000040NonXMLBody nonXMLBody;
	protected POCDMT000040StructuredBody structuredBody;
	@XmlAttribute(name = "nullFlavor")
	protected List<String> nullFlavor;
	@XmlAttribute(name = "typeCode")
	protected ActRelationshipHasComponent typeCode;
	@XmlAttribute(name = "contextConductionInd")
	protected Boolean contextConductionInd;

	/**
	 * Ruft den Wert der nonXMLBody-Eigenschaft ab.
	 *
	 * @return possible object is {@link POCDMT000040NonXMLBody }
	 *
	 */
	public POCDMT000040NonXMLBody getNonXMLBody() {
		return nonXMLBody;
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
	public List<String> getNullFlavor() {
		if (nullFlavor == null) {
			nullFlavor = new ArrayList<String>();
		}
		return this.nullFlavor;
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
	public List<CS> getRealmCode() {
		if (realmCode == null) {
			realmCode = new ArrayList<CS>();
		}
		return this.realmCode;
	}

	/**
	 * Ruft den Wert der structuredBody-Eigenschaft ab.
	 *
	 * @return possible object is {@link POCDMT000040StructuredBody }
	 *
	 */
	public POCDMT000040StructuredBody getStructuredBody() {
		return structuredBody;
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
	public List<II> getTemplateId() {
		if (templateId == null) {
			templateId = new ArrayList<II>();
		}
		return this.templateId;
	}

	/**
	 * Ruft den Wert der typeCode-Eigenschaft ab.
	 *
	 * @return possible object is {@link ActRelationshipHasComponent }
	 *
	 */
	public ActRelationshipHasComponent getTypeCode() {
		if (typeCode == null) {
			return ActRelationshipHasComponent.COMP;
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
	 * Legt den Wert der nonXMLBody-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link POCDMT000040NonXMLBody }
	 *
	 */
	public void setNonXMLBody(POCDMT000040NonXMLBody value) {
		this.nonXMLBody = value;
	}

	/**
	 * Legt den Wert der structuredBody-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link POCDMT000040StructuredBody }
	 *
	 */
	public void setStructuredBody(POCDMT000040StructuredBody value) {
		this.structuredBody = value;
	}

	/**
	 * Legt den Wert der typeCode-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link ActRelationshipHasComponent }
	 *
	 */
	public void setTypeCode(ActRelationshipHasComponent value) {
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