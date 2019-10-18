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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.44 Template
 * description: A Pharmaceutical Advice Item belongs to one Pharmaceutical
 * Advice and represents the validation outcome or management command regarding
 * the referenced Medication Treatment Plan-, Prescription- or Dispense Item
 * (e.g., change, cancel, etc.).
 */
public class PharmaceuticalAdviceItemEntryContentModule
		extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Observation {

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
	 * Creates fixed contents for CDA Element hl7StatusCode
	 *
	 * @param code
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.CS createHl7StatusCodeFixedValue(
			String code, String codeSystem, String codeSystemName, String displayName) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CS retVal = factory.createCS();
		retVal.setCode(code);
		retVal.setCodeSystem(codeSystem);
		retVal.setCodeSystemName(codeSystemName);
		retVal.setDisplayName(displayName);
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
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "CANCEL", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeCancel136141193761921NullNull() {
		return createHl7CodeFixedValue("CANCEL", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "CHANGE", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeChange136141193761921NullNull() {
		return createHl7CodeFixedValue("CHANGE", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "COMMENT", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeComment136141193761921NullNull() {
		return createHl7CodeFixedValue("COMMENT", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "OK", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeOk136141193761921NullNull() {
		return createHl7CodeFixedValue("OK", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "REFUSE", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeRefuse136141193761921NullNull() {
		return createHl7CodeFixedValue("REFUSE", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CD, filled by:
	 * "SUSPEND", "1.3.6.1.4.1.19376.1.9.2.1", null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CD getPredefinedCodeSuspend136141193761921NullNull() {
		return createHl7CodeFixedValue("SUSPEND", "1.3.6.1.4.1.19376.1.9.2.1", null, null);
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship,
	 * filled by: "COMP", null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship getPredefinedEntryRelationshipCompNull() {
		return createHl7EntryRelationshipFixedValue("COMP", null);
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship,
	 * filled by: "REFR", "false"
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship getPredefinedEntryRelationshipRefrFalse() {
		return createHl7EntryRelationshipFixedValue("REFR", "false");
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

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CS, filled by:
	 * "active", null, null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CS getPredefinedStatusCodeActiveNullNullNull() {
		return createHl7StatusCodeFixedValue("active", null, null, null);
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CS, filled by:
	 * "completed", null, null, null
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CS getPredefinedStatusCodeCompletedNullNullNull() {
		return createHl7StatusCodeFixedValue("completed", null, null, null);
	}

	public PharmaceuticalAdviceItemEntryContentModule() {
		super.setMoodCode(
				org.ehealth_connector.common.hl7cdar2.XActMoodDocumentObservation.fromValue("EVN"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.4.44"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.9.1.3.3"));
		super.setCode(createHl7CodeFixedValue("OK", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setCode(createHl7CodeFixedValue("CHANGE", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setCode(createHl7CodeFixedValue("CANCEL", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setCode(createHl7CodeFixedValue("SUSPEND", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setCode(createHl7CodeFixedValue("REFUSE", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setCode(createHl7CodeFixedValue("COMMENT", "1.3.6.1.4.1.19376.1.9.2.1", null, null));
		super.setStatusCode(createHl7StatusCodeFixedValue("active", null, null, null));
		super.setStatusCode(createHl7StatusCodeFixedValue("completed", null, null, null));
	}

	/**
	 * Adds a hl7Id Pharmaceutical Advice Item ID
	 */
	public void addHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		getId().add(value);
	}

	/**
	 * Adds a hl7Reference ID of parent container (Community Pharmaceutical
	 * Advice document)
	 */
	public void addHl7Reference(org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference value) {
		getReference().add(value);
	}

	/**
	 * Adds a hl7Id Pharmaceutical Advice Item ID
	 */
	public void clearHl7Id() {
		getId().clear();
	}

	/**
	 * Adds a hl7Reference ID of parent container (Community Pharmaceutical
	 * Advice document)
	 */
	public void clearHl7Reference() {
		getReference().clear();
	}

	/**
	 * Gets the hl7Author Pharmaceutical Adviser
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.POCDMT000040Author> getHl7Author() {
		return author;
	}

	/**
	 * Gets the hl7Code Observation Code
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return code;
	}

	/**
	 * Gets the hl7EffectiveTime Effective Time (Date of becoming effective)
	 */
	public org.ehealth_connector.common.hl7cdar2.IVLTS getHl7EffectiveTime() {
		return effectiveTime;
	}

	/**
	 * Gets the hl7EntryRelationship
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship> getHl7EntryRelationship() {
		return entryRelationship;
	}

	/**
	 * Gets the hl7StatusCode
	 */
	public org.ehealth_connector.common.hl7cdar2.CS getHl7StatusCode() {
		return statusCode;
	}

	/**
	 * Gets the hl7TemplateId
	 * CH-PHARM-PharmaceuticalAdviceItemEntryContentModule
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7TemplateId() {
		return templateId;
	}

	/**
	 * Gets the hl7Text
	 */
	public org.ehealth_connector.common.hl7cdar2.ED getHl7Text() {
		return text;
	}

	/**
	 * Sets the hl7Author Pharmaceutical Adviser
	 */
	public void setHl7Author(org.ehealth_connector.common.hl7cdar2.POCDMT000040Author value) {
		getAuthor().clear();
		getAuthor().add(value);
	}

	/**
	 * Sets the hl7Code Observation Code
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		this.code = value;
	}

	/**
	 * Sets the hl7EffectiveTime Effective Time (Date of becoming effective)
	 */
	public void setHl7EffectiveTime(org.ehealth_connector.common.hl7cdar2.IVLTS value) {
		this.effectiveTime = value;
	}

	/**
	 * Sets the hl7EntryRelationship
	 */
	public void setHl7EntryRelationship(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship value) {
		getEntryRelationship().clear();
		getEntryRelationship().add(value);
	}

	/**
	 * Sets the hl7StatusCode
	 */
	public void setHl7StatusCode(org.ehealth_connector.common.hl7cdar2.CS value) {
		this.statusCode = value;
	}

	/**
	 * Sets the hl7TemplateId
	 * CH-PHARM-PharmaceuticalAdviceItemEntryContentModule
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		getTemplateId().clear();
		getTemplateId().add(value);
	}

	/**
	 * Sets the hl7Text
	 */
	public void setHl7Text(org.ehealth_connector.common.hl7cdar2.ED value) {
		this.text = value;
	}
}