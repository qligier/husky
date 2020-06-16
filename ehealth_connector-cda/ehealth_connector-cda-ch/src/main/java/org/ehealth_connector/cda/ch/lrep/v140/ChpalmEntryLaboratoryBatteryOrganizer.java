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
package org.ehealth_connector.cda.ch.lrep.v140;

import org.ehealth_connector.common.hl7cdar2.ObjectFactory;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.19 Template
 * description: According to IHE XD-LAB, the Laboratory Battery Organizer allows
 * the grouping of results.Laboratory reports MUST contain at least one result
 * group with at least one result.The grouping of multiple results
 * (Observations) into multiple result groups (Laboratory Battery Organizers) is
 * permitted.
 */
public class ChpalmEntryLaboratoryBatteryOrganizer
		extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Organizer {

	public ChpalmEntryLaboratoryBatteryOrganizer() {
		super.setClassCode(org.ehealth_connector.common.hl7cdar2.XActClassDocumentEntryOrganizer
				.fromValue("BATTERY"));
		super.getMoodCode().add("EVN");
		super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.4.19"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.3.1.4"));
		super.setStatusCode(createHl7StatusCodeFixedValue());
	}

	/**
	 * Adds a hl7Author This CAN be used to identify other parties: - Who has
	 * verified the result (AUTHEN). - Which external laboratory has delivered
	 * the result (RESP). - Which device (analyzer) was used to determine the
	 * result (DEV).
	 */
	public void addHl7Author(org.ehealth_connector.common.hl7cdar2.POCDMT000040Author value) {
		getAuthor().add(value);
	}

	/**
	 * Adds a hl7Component
	 */
	public void addHl7Component(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040Component4 value) {
		getComponent().add(value);
	}

	/**
	 * Adds a hl7Participant Who has verified the result (AUTHEN). This
	 * information supersedes any information recorded at higher level.
	 */
	public void addHl7Participant(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040Participant2 value) {
		getParticipant().add(value);
	}

	/**
	 * Adds a hl7Performer This CAN be used to indicate who has performed the
	 * test. This information supersedes any information recorded at higher
	 * level.
	 */
	public void addHl7Performer(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040Performer2 value) {
		getPerformer().add(value);
	}

	/**
	 * Adds a hl7Reference This CAN be used to make reference to an external
	 * document.
	 */
	public void addHl7Reference(org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference value) {
		getReference().add(value);
	}

	/**
	 * Adds a hl7Author This CAN be used to identify other parties: - Who has
	 * verified the result (AUTHEN). - Which external laboratory has delivered
	 * the result (RESP). - Which device (analyzer) was used to determine the
	 * result (DEV).
	 */
	public void clearHl7Author() {
		getAuthor().clear();
	}

	/**
	 * Adds a hl7Component
	 */
	public void clearHl7Component() {
		getComponent().clear();
	}

	/**
	 * Adds a hl7Participant Who has verified the result (AUTHEN). This
	 * information supersedes any information recorded at higher level.
	 */
	public void clearHl7Participant() {
		getParticipant().clear();
	}

	/**
	 * Adds a hl7Performer This CAN be used to indicate who has performed the
	 * test. This information supersedes any information recorded at higher
	 * level.
	 */
	public void clearHl7Performer() {
		getPerformer().clear();
	}

	/**
	 * Adds a hl7Reference This CAN be used to make reference to an external
	 * document.
	 */
	public void clearHl7Reference() {
		getReference().clear();
	}

	/**
	 * Creates fixed contents for CDA Element hl7Author
	 *
	 * @param typeCode
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.POCDMT000040Author createHl7AuthorFixedValue(
			String typeCode) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.POCDMT000040Author retVal = factory
				.createPOCDMT000040Author();
		retVal.getTypeCode().add(typeCode);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7Component
	 *
	 * @param typeCode
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.POCDMT000040Component4 createHl7ComponentFixedValue(
			String typeCode) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.POCDMT000040Component4 retVal = factory
				.createPOCDMT000040Component4();
		retVal.setTypeCode(org.ehealth_connector.common.hl7cdar2.ActRelationshipHasComponent
				.fromValue(typeCode));
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
	 */
	private static org.ehealth_connector.common.hl7cdar2.CS createHl7StatusCodeFixedValue() {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CS retVal = factory.createCS();
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
	 * Gets the hl7Code If the results in this result group are associated to a
	 * codable group (e.g., LOINC Code 55429-5 for 'short blood count panel'),
	 * then the code SHOULD be specified. The code system is not further
	 * specified. LOINC (2.16.840.1.113883.6.1) or SNOMED CT
	 * (2.16.840.1.113883.6.96) codes or values from the HL7 vocabulary
	 * ObservationType (2.16.840.1.113883.1.11.16226) SHOULD be used.If this
	 * result group contains any other results, then the code SHOULD be omitted.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return code;
	}

	/**
	 * Gets the hl7EffectiveTime Timestamp or period of findings
	 * (physiologically relevant period) for all results in this result group.
	 */
	public org.ehealth_connector.common.hl7cdar2.IVLTS getHl7EffectiveTime() {
		return effectiveTime;
	}

	/**
	 * Gets the hl7Id An ID for this item MAY be filled for traceability.
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7Id() {
		return id;
	}

	/**
	 * Gets the hl7StatusCode The status 'completed' means that all expected
	 * results for this results group are present and in a final state.The
	 * status 'aborted' means that the examinations had to be aborted. Some
	 * results MAY be available.
	 */
	public org.ehealth_connector.common.hl7cdar2.CS getHl7StatusCode() {
		return statusCode;
	}

	/**
	 * Gets the hl7Subject
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Subject getHl7Subject() {
		return subject;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7TemplateId() {
		return templateId;
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040Author, filled by:
	 * "AUT"
	 * 
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040Author getPredefinedAuthorAut() {
		return createHl7AuthorFixedValue("AUT");
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040Component4, filled by:
	 * "COMP"
	 * 
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040Component4 getPredefinedComponentComp() {
		return createHl7ComponentFixedValue("COMP");
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference, filled by:
	 * "REFR"
	 * 
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference getPredefinedReferenceRefr() {
		return createHl7ReferenceFixedValue("REFR");
	}

	/**
	 * Sets the hl7Code If the results in this result group are associated to a
	 * codable group (e.g., LOINC Code 55429-5 for 'short blood count panel'),
	 * then the code SHOULD be specified. The code system is not further
	 * specified. LOINC (2.16.840.1.113883.6.1) or SNOMED CT
	 * (2.16.840.1.113883.6.96) codes or values from the HL7 vocabulary
	 * ObservationType (2.16.840.1.113883.1.11.16226) SHOULD be used.If this
	 * result group contains any other results, then the code SHOULD be omitted.
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		this.code = value;
	}

	/**
	 * Sets the hl7EffectiveTime Timestamp or period of findings
	 * (physiologically relevant period) for all results in this result group.
	 */
	public void setHl7EffectiveTime(org.ehealth_connector.common.hl7cdar2.IVLTS value) {
		this.effectiveTime = value;
	}

	/**
	 * Sets the hl7Id An ID for this item MAY be filled for traceability.
	 */
	public void setHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		getId().clear();
		getId().add(value);
	}

	/**
	 * Sets the hl7StatusCode The status 'completed' means that all expected
	 * results for this results group are present and in a final state.The
	 * status 'aborted' means that the examinations had to be aborted. Some
	 * results MAY be available.
	 */
	public void setHl7StatusCode(org.ehealth_connector.common.hl7cdar2.CS value) {
		this.statusCode = value;
	}

	/**
	 * Sets the hl7Subject
	 */
	public void setHl7Subject(org.ehealth_connector.common.hl7cdar2.POCDMT000040Subject value) {
		this.subject = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		getTemplateId().clear();
		getTemplateId().add(value);
	}
}