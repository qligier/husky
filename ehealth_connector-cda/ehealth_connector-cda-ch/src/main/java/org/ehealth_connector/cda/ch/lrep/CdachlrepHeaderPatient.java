/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
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
package org.ehealth_connector.cda.ch.lrep;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.58
 * Template description: Patient (Human Patient).Patient identifiersThe id (patient identification number) can be declared multiple times. If multiple identifications are known, all known IDs should be specified. For laboratory reports, all IDs specified by the ordering system are to be returned. This allows the receiver to assign its internal patient identification. OIDs of code systems, which are published in the public OID registry for the Swiss health care system (oid.refdata.ch) are REQUIRED. Others are NOT ALLOWED.PseudonymizationIn special cases, the demographic data of the patient need not be transmitted or they need to be pseudonymized (e.g., results of genetic or forensic toxicology). The affected values must be replaced by a nullFlavor of type "MSK" (masked), in order to shield the data.
 *
 * Element description: Patient (Human Patient).
 */
public class CdachlrepHeaderPatient extends org.ehealth_connector.common.hl7cdar2.POCDMT000040RecordTarget {

	/**
	 * Exactly one patient MUST be declared.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040PatientRole hl7PatientRole;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId1;

	/**
	 * Gets the hl7PatientRole
	 * Exactly one patient MUST be declared.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040PatientRole getHl7PatientRole() {
		return hl7PatientRole;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Gets the hl7TemplateId1
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId1() {
		return hl7TemplateId1;
	}

	/**
	 * Sets the hl7PatientRole
	 * Exactly one patient MUST be declared.
	 */
	public void setHl7PatientRole(org.ehealth_connector.common.hl7cdar2.POCDMT000040PatientRole value) {
		hl7PatientRole = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}

	/**
	 * Sets the hl7TemplateId1
	 */
	public void setHl7TemplateId1(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId1 = value;
	}
}