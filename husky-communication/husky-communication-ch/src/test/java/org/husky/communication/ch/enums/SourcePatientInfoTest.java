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
package org.husky.communication.ch.enums;

import static org.husky.common.model.ValueSetEnumInterfaceTest.assertValueSetEnumEntries;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.husky.communication.ch.enums.SourcePatientInfo;
import org.junit.jupiter.api.Test;

public class SourcePatientInfoTest {

	@Test
	public void testSourcePatientInfo() {

		assertEquals("2.16.840.1.113883.4.642.3.1", SourcePatientInfo.VALUE_SET_ID);
		assertValueSetEnumEntries(SourcePatientInfo.values());

	}

}