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

import java.util.ArrayList;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.16
 * Template description: If an examination falls under the category "accumulation of observations" or "specific epidemiologically relevant event", this CAN be declared in the CDA body using this element.Precise specification on the usage of this element is documented in the specification "Laboratory reports for public health (CDA-CH-LRPH)"
 */
public class ChpalmEntryOutbreakIdentification extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Observation {

	/**
	 * A code that defines the 'outbreak' MUST be specified.Note:The SNOMED-CT code 416534008 (Outbreak) has been defined for reportable laboratory findings in Switzerland.
	 */
	private org.ehealth_connector.common.hl7cdar2.CD hl7Code;

	/**
	 * An ID for this item CAN be filled for traceability.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.II> hl7Id;

	/**
	 * The status 'completed' means the patient has been associated with the given outbreak.
	 */
	private org.ehealth_connector.common.hl7cdar2.CS hl7StatusCode;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId1;

	/**
	 * A code that defines the 'outbreak' condition MUST be specified. If no code is known, nullFlavor='NA' MUST be used.Note:For reportable laboratory findings in Switzerland, nullFlavor = 'NA' MUST MUST be used.
	 */
	private org.ehealth_connector.common.hl7cdar2.CE hl7Value;

	/**
	 * Adds a hl7Id
	 * An ID for this item CAN be filled for traceability.
	 */
	public void addHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7Id.add(value);
	}

	/**
	 * Adds a hl7Id
	 * An ID for this item CAN be filled for traceability.
	 */
	public void clearHl7Id() {
		hl7Id.clear();
	}

	/**
	 * Gets the hl7Code
	 * A code that defines the 'outbreak' MUST be specified.Note:The SNOMED-CT code 416534008 (Outbreak) has been defined for reportable laboratory findings in Switzerland.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return hl7Code;
	}

	/**
	 * Gets the hl7StatusCode
	 * The status 'completed' means the patient has been associated with the given outbreak.
	 */
	public org.ehealth_connector.common.hl7cdar2.CS getHl7StatusCode() {
		return hl7StatusCode;
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
	 * Gets the hl7Value
	 * A code that defines the 'outbreak' condition MUST be specified. If no code is known, nullFlavor='NA' MUST be used.Note:For reportable laboratory findings in Switzerland, nullFlavor = 'NA' MUST MUST be used.
	 */
	public org.ehealth_connector.common.hl7cdar2.CE getHl7Value() {
		return hl7Value;
	}

	/**
	 * Sets the hl7Code
	 * A code that defines the 'outbreak' MUST be specified.Note:The SNOMED-CT code 416534008 (Outbreak) has been defined for reportable laboratory findings in Switzerland.
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Code = value;
	}

	/**
	 * Sets the hl7StatusCode
	 * The status 'completed' means the patient has been associated with the given outbreak.
	 */
	public void setHl7StatusCode(org.ehealth_connector.common.hl7cdar2.CS value) {
		hl7StatusCode = value;
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

	/**
	 * Sets the hl7Value
	 * A code that defines the 'outbreak' condition MUST be specified. If no code is known, nullFlavor='NA' MUST be used.Note:For reportable laboratory findings in Switzerland, nullFlavor = 'NA' MUST MUST be used.
	 */
	public void setHl7Value(org.ehealth_connector.common.hl7cdar2.CE value) {
		hl7Value = value;
	}
}