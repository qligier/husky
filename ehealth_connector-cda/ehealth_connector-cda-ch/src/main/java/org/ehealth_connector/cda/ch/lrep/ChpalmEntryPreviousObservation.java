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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.22
 * Template description: A laboratory result can be supplemented with any number of previous results, if this information is important. Previous laboratory results MUST be associated to the same patient, the same test, the same procedure, and the same test kit, otherwise they are NOT ALLOWED, here.
 */
public class ChpalmEntryPreviousObservation extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Observation {

	/**
	 * MUST contain the same code as the current laboratory result.
	 */
	private org.ehealth_connector.common.hl7cdar2.CD hl7Code;

	/**
	 * Timestamp of the findings (physiologically relevant time) of this previous result. Accuracy of the value: Date and time with hour and minute.
	 */
	private org.ehealth_connector.common.hl7cdar2.IVLTS hl7EffectiveTime;

	/**
	 * MUST be set to 'completed'.
	 */
	private org.ehealth_connector.common.hl7cdar2.CS hl7StatusCode;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	/**
	 * The previous result obtained for this test.
	 */
	private org.ehealth_connector.common.hl7cdar2.ANY hl7Value;

	/**
	 * Gets the hl7Code
	 * MUST contain the same code as the current laboratory result.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return hl7Code;
	}

	/**
	 * Gets the hl7EffectiveTime
	 * Timestamp of the findings (physiologically relevant time) of this previous result. Accuracy of the value: Date and time with hour and minute.
	 */
	public org.ehealth_connector.common.hl7cdar2.IVLTS getHl7EffectiveTime() {
		return hl7EffectiveTime;
	}

	/**
	 * Gets the hl7StatusCode
	 * MUST be set to 'completed'.
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
	 * Gets the hl7Value
	 * The previous result obtained for this test.
	 */
	public org.ehealth_connector.common.hl7cdar2.ANY getHl7Value() {
		return hl7Value;
	}

	/**
	 * Sets the hl7Code
	 * MUST contain the same code as the current laboratory result.
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Code = value;
	}

	/**
	 * Sets the hl7EffectiveTime
	 * Timestamp of the findings (physiologically relevant time) of this previous result. Accuracy of the value: Date and time with hour and minute.
	 */
	public void setHl7EffectiveTime(org.ehealth_connector.common.hl7cdar2.IVLTS value) {
		hl7EffectiveTime = value;
	}

	/**
	 * Sets the hl7StatusCode
	 * MUST be set to 'completed'.
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
	 * Sets the hl7Value
	 * The previous result obtained for this test.
	 */
	public void setHl7Value(org.ehealth_connector.common.hl7cdar2.ANY value) {
		hl7Value = value;
	}
}
