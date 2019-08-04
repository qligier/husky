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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.56
 * Template description: A LOINC based document type of a CDA document instance including a translation to the Swiss EPR XDS.b metadata.
 * - Multidisciplinary laboratory findings:The LOINC code of the document MUST read: 11502-2 (LABORATORY REPORT.TOTAL)
 * - Laboratory reports of a single laboratory discipline:The LOINC code of the document MUST be taken from the value set 'Laboratory Specialties'
 *
 * Element description: A LOINC based document type of a CDA document instance including a translation to the Swiss EPR XDS.b metadata.
 * - Multidisciplinary laboratory findings:The LOINC code of the document MUST read: 11502-2 (LABORATORY REPORT.TOTAL)
 * - Laboratory reports of a single laboratory discipline:The LOINC code of the document MUST be taken from the value-set 'Laboratory Specialties'
 */
public class CdachlrepHeaderDocumentCode extends org.ehealth_connector.common.hl7cdar2.CE {

	/**
	 * The translation to the Swiss EPR XDS.b metadata attribute typeCode.
	 */
	private org.ehealth_connector.common.hl7cdar2.CD hl7Translation;

	/**
	 * Gets the hl7Translation
	 * The translation to the Swiss EPR XDS.b metadata attribute typeCode.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Translation() {
		return hl7Translation;
	}

	/**
	 * Sets the hl7Translation
	 * The translation to the Swiss EPR XDS.b metadata attribute typeCode.
	 */
	public void setHl7Translation(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Translation = value;
	}
}