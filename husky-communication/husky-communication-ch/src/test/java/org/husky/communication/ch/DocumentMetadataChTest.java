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
package org.husky.communication.ch;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.common.enums.LanguageCode;
import org.husky.communication.DocumentMetadataTest;
import org.husky.communication.ch.DocumentMetadataCh;
import org.husky.communication.ch.enums.ClassCode;
import org.husky.communication.ch.enums.HealthcareFacilityTypeCode;
import org.husky.communication.ch.enums.MimeType;
import org.husky.communication.ch.enums.PracticeSettingCode;
import org.husky.communication.ch.enums.TypeCode;
import org.junit.jupiter.api.Test;

public class DocumentMetadataChTest extends DocumentMetadataTest {

	DocumentMetadataCh m = new DocumentMetadataCh();

	@Test
	public void testClassCodeEnum() {
		m.setClassCode(ClassCode.CARE_PLAN);
		assertTrue(isEqual(ClassCode.CARE_PLAN.getCode(), m.getClassCodeEnum().getCode()));
	}

	@Test
	public void testCodedLanguageEnum() {
		m.setCodedLanguage(LanguageCode.FRENCH);
		assertEquals(LanguageCode.FRENCH, m.getLanguageCodeEnum());
	}

	@Test
	public void testConfidentialityCodeEnum() {
		m.addConfidentialityCode(ConfidentialityCode.NORMALLY_ACCESSIBLE);
		assertTrue(isEqual(ConfidentialityCode.NORMALLY_ACCESSIBLE.getCode(),
				m.getConfidentialityCodesEnum().get(0).getCode()));
	}

	@Test
	public void testHealthcareFacilityEnum() {
		m.setHealthcareFacilityTypeCode(HealthcareFacilityTypeCode.AMBULATORY_CARE_SITE);
		assertEquals(HealthcareFacilityTypeCode.AMBULATORY_CARE_SITE,
				m.getHealthcareFacilityTypeCodeEnum());
	}

	@Test
	public void testMimeTypeEnum() {
		m.setMimeType(MimeType.CDA_LEVEL_1_MULTIPART);
		assertEquals(MimeType.CDA_LEVEL_1_MULTIPART.getCodeValue(), m.getMimeType());
	}

	@Test
	public void testPracticeSettingEnum() {
		m.setPracticeSettingCode(PracticeSettingCode.CLINICAL_IMMUNOLOGY_ALLERGY);
		assertEquals(PracticeSettingCode.CLINICAL_IMMUNOLOGY_ALLERGY,
				m.getPracticeSettingCodeEnum());
	}

	@Test
	public void testTypeCodeEnum() {
		m.setTypeCode(TypeCode.DISCHARGE_SUMMARY);
		assertEquals(TypeCode.DISCHARGE_SUMMARY, m.getTypeCodeEnum());
	}
}