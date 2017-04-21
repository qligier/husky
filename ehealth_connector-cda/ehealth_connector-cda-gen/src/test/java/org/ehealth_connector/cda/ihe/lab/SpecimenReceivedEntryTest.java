/*
 * 
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
package org.ehealth_connector.cda.ihe.lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;

import org.ehealth_connector.cda.testhelper.TestUtils;
import org.junit.Test;
import org.w3c.dom.Document;

public class SpecimenReceivedEntryTest extends TestUtils {
	@Test
	public void testModelAndEffectiveTime() throws XPathExpressionException {
		super.init();
		SpecimenReceivedEntry sre = new SpecimenReceivedEntry();

		sre.setEffectiveTime(endDate);
		assertEquals(endDate, sre.getEffectiveTime());

		Document document = sre.getDocument();
		assertTrue(xExist(document, "//code[@code='SPRECEIVE']"));
	}
}
