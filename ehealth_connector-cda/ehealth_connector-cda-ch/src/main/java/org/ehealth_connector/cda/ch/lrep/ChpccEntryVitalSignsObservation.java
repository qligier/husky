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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.21
 * Template description: Structured notation of a measured value resp. an observation of a single vital sign (such as body height, weight, blood pressure).
 */
public class ChpccEntryVitalSignsObservation extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Observation {

	/**
	 * The reference to the text in the narrative section of the section MUST be specified.
	 */
	private org.ehealth_connector.common.hl7cdar2.CD hl7Code;

	private org.ehealth_connector.common.hl7cdar2.IVLTS hl7EffectiveTime;

	/**
	 * An ID for this item MAY be filled for traceability.
	 */
	private org.ehealth_connector.common.hl7cdar2.II hl7Id;

	private ArrayList<org.ehealth_connector.common.hl7cdar2.CE> hl7InterpretationCode;

	private ArrayList<org.ehealth_connector.common.hl7cdar2.CE> hl7MethodCode;

	/**
	 * The status 'completed' indicates that the observation is final.
	 */
	private org.ehealth_connector.common.hl7cdar2.CS hl7StatusCode;

	private ArrayList<org.ehealth_connector.common.hl7cdar2.CD> hl7TargetSiteCode;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId1;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId2;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId3;

	/**
	 * According to table in [IHE PCC TF-2], 6.3.4.22.3
	 */
	private org.ehealth_connector.common.hl7cdar2.ANY hl7Value;

	/**
	 * Adds a hl7InterpretationCode
	 */
	public void addHl7InterpretationCode(org.ehealth_connector.common.hl7cdar2.CE value) {
		hl7InterpretationCode.add(value);
	}

	/**
	 * Adds a hl7MethodCode
	 */
	public void addHl7MethodCode(org.ehealth_connector.common.hl7cdar2.CE value) {
		hl7MethodCode.add(value);
	}

	/**
	 * Adds a hl7TargetSiteCode
	 */
	public void addHl7TargetSiteCode(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7TargetSiteCode.add(value);
	}

	/**
	 * Adds a hl7InterpretationCode
	 */
	public void clearHl7InterpretationCode() {
		hl7InterpretationCode.clear();
	}

	/**
	 * Adds a hl7MethodCode
	 */
	public void clearHl7MethodCode() {
		hl7MethodCode.clear();
	}

	/**
	 * Adds a hl7TargetSiteCode
	 */
	public void clearHl7TargetSiteCode() {
		hl7TargetSiteCode.clear();
	}

	/**
	 * Gets the hl7Code
	 * The reference to the text in the narrative section of the section MUST be specified.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return hl7Code;
	}

	/**
	 * Gets the hl7EffectiveTime
	 */
	public org.ehealth_connector.common.hl7cdar2.IVLTS getHl7EffectiveTime() {
		return hl7EffectiveTime;
	}

	/**
	 * Gets the hl7Id
	 * An ID for this item MAY be filled for traceability.
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7Id() {
		return hl7Id;
	}

	/**
	 * Gets the hl7StatusCode
	 * The status 'completed' indicates that the observation is final.
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
	 * Gets the hl7TemplateId2
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId2() {
		return hl7TemplateId2;
	}

	/**
	 * Gets the hl7TemplateId3
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId3() {
		return hl7TemplateId3;
	}

	/**
	 * Gets the hl7Value
	 * According to table in [IHE PCC TF-2], 6.3.4.22.3
	 */
	public org.ehealth_connector.common.hl7cdar2.ANY getHl7Value() {
		return hl7Value;
	}

	/**
	 * Sets the hl7Code
	 * The reference to the text in the narrative section of the section MUST be specified.
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Code = value;
	}

	/**
	 * Sets the hl7EffectiveTime
	 */
	public void setHl7EffectiveTime(org.ehealth_connector.common.hl7cdar2.IVLTS value) {
		hl7EffectiveTime = value;
	}

	/**
	 * Sets the hl7Id
	 * An ID for this item MAY be filled for traceability.
	 */
	public void setHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7Id = value;
	}

	/**
	 * Sets the hl7StatusCode
	 * The status 'completed' indicates that the observation is final.
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
	 * Sets the hl7TemplateId2
	 */
	public void setHl7TemplateId2(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId2 = value;
	}

	/**
	 * Sets the hl7TemplateId3
	 */
	public void setHl7TemplateId3(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId3 = value;
	}

	/**
	 * Sets the hl7Value
	 * According to table in [IHE PCC TF-2], 6.3.4.22.3
	 */
	public void setHl7Value(org.ehealth_connector.common.hl7cdar2.ANY value) {
		hl7Value = value;
	}
}