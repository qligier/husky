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
package org.ehealth_connector.cda.ch.emed.v096;

import org.ehealth_connector.common.hl7cdar2.ObjectFactory;

/**
 * Original ART-DECOR template id: 1.3.6.1.4.1.19376.1.9.1.3.10 Template
 * description: Reference to a Medication Treatment Plan Entry
 *
 * Element description: Reference to Medication Treatment Plan Item General
 * Specification
 */
public class IhemtpreferenceEntryContentModule
		extends org.ehealth_connector.common.hl7cdar2.POCDMT000040SubstanceAdministration {

	/**
	 * Creates fixed contents for CDA Element hl7Code
	 *
	 * @param code
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.CD createHl7CodeFixedValue(String code,
			String codeSystem, String codeSystemName, String displayName) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CD retVal = factory.createCD();
		retVal.setCode(code);
		retVal.setCodeSystem(codeSystem);
		retVal.setCodeSystemName(codeSystemName);
		retVal.setDisplayName(displayName);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7EntryRelationship
	 *
	 * @param typeCode
	 *            the desired fixed value for this argument.
	 * @param inversionInd
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship createHl7EntryRelationshipFixedValue(
			String typeCode, String inversionInd) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship retVal = factory
				.createPOCDMT000040EntryRelationship();
		retVal.setTypeCode(org.ehealth_connector.common.hl7cdar2.XActRelationshipEntryRelationship
				.fromValue(typeCode));
		retVal.setInversionInd(Boolean.parseBoolean(inversionInd));
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7Reference
	 *
	 * @param typeCode
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference createHl7ReferenceFixedValue(
			String typeCode) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference retVal = factory
				.createPOCDMT000040Reference();
		retVal.setTypeCode(org.ehealth_connector.common.hl7cdar2.XActRelationshipExternalReference
				.fromValue(typeCode));
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7TemplateId
	 *
	 * @param root
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.II createHl7TemplateIdFixedValue(
			String root) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.II retVal = factory.createII();
		retVal.setRoot(root);
		return retVal;
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship,
	 * filled by: "REFR", null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship getPredefinedEntryRelationshipRefrNull() {
		return createHl7EntryRelationshipFixedValue("REFR", null);
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference, filled by:
	 * "XCRPT"
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference getPredefinedReferenceXcrpt() {
		return createHl7ReferenceFixedValue("XCRPT");
	}

	public IhemtpreferenceEntryContentModule() {
		super.getClassCode().add("SBADM");
		super.setMoodCode(
				org.ehealth_connector.common.hl7cdar2.XDocumentSubstanceMood.fromValue("INT"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.9.1.3.10"));
		super.setCode(createHl7CodeFixedValue("MTPItem", "1.3.6.1.4.1.19376.1.9.2.2", null, null));
	}

	/**
	 * Gets the hl7Author Author of the referenced item
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.POCDMT000040Author> getHl7Author() {
		return author;
	}

	/**
	 * Gets the hl7Code Reference to Medication Treatment Plan Item code
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return code;
	}

	/**
	 * Gets the hl7Consumable
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Consumable getHl7Consumable() {
		return consumable;
	}

	/**
	 * Gets the hl7EntryRelationship Embedding of referenced item
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship> getHl7EntryRelationship() {
		return entryRelationship;
	}

	/**
	 * Gets the hl7Id Reference to Medication Treatment Plan Item ID
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7Id() {
		return id;
	}

	/**
	 * Gets the hl7Reference ID of parent container of referenced item
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference> getHl7Reference() {
		return reference;
	}

	/**
	 * Gets the hl7TemplateId Reference to Medication Treatment Plan Item
	 * General Specification Template ID
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7TemplateId() {
		return templateId;
	}

	/**
	 * Sets the hl7Author Author of the referenced item
	 */
	public void setHl7Author(org.ehealth_connector.common.hl7cdar2.POCDMT000040Author value) {
		getAuthor().clear();
		getAuthor().add(value);
	}

	/**
	 * Sets the hl7Code Reference to Medication Treatment Plan Item code
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		this.code = value;
	}

	/**
	 * Sets the hl7Consumable
	 */
	public void setHl7Consumable(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040Consumable value) {
		this.consumable = value;
	}

	/**
	 * Sets the hl7EntryRelationship Embedding of referenced item
	 */
	public void setHl7EntryRelationship(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship value) {
		getEntryRelationship().clear();
		getEntryRelationship().add(value);
	}

	/**
	 * Sets the hl7Id Reference to Medication Treatment Plan Item ID
	 */
	public void setHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		getId().clear();
		getId().add(value);
	}

	/**
	 * Sets the hl7Reference ID of parent container of referenced item
	 */
	public void setHl7Reference(org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference value) {
		getReference().clear();
		getReference().add(value);
	}

	/**
	 * Sets the hl7TemplateId Reference to Medication Treatment Plan Item
	 * General Specification Template ID
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		getTemplateId().clear();
		getTemplateId().add(value);
	}
}