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
package org.husky.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.husky.common.enums.TelecomAddressUse;
import org.husky.common.model.Telecom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Test of class Telecoms
 */
public class TelecomsTest {

	private String numS1;
	private String numS2;

	private String telS1;
	private String telS2;
	private String testWebsite1;

	/**
	 * Method implementing
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		numS1 = "1231425352";
		numS2 = "987653";
		telS1 = "+41.32.234.66.77";
		telS2 = "+44.32.234.66.99";
		testWebsite1 = "http://www.myweb.ch";
	}

	/**
	 * Test method for
	 * {@link org.husky.common.model.Telecoms#addEMail(java.lang.String, org.husky.common.enums.TelecomAddressUse)}
	 * .
	 */
	@Test
	public void testAddEMail() {
		final Telecom t = new Telecom();

		t.setMail("axel.helmer.job@gmail.com");
		t.setUsage(TelecomAddressUse.BUSINESS);

		assertTrue(t.getValue().contains("mailto:axel.helmer.job@gmail.com"));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.BUSINESS.getCodeValue());

		t.setMail("test@test.de");
		t.setUsage(TelecomAddressUse.PRIVATE);
		assertTrue(t.getValue().contains("mailto:test@test.de"));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.PRIVATE.getCodeValue());
	}

	/**
	 * Test method for
	 * {@link org.husky.common.model.Telecoms#addFax(java.lang.String, org.husky.common.enums.TelecomAddressUse)}
	 * .
	 */
	@Test
	public void testAddFax() {
		final Telecom t = new Telecom();

		t.setFax(numS1);
		t.setUsage(TelecomAddressUse.BUSINESS);
		assertTrue(t.getValue().contains("fax:" + numS1));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.BUSINESS.getCodeValue());

		t.setFax(numS2);
		t.setUsage(TelecomAddressUse.MOBILE);
		assertTrue(t.getValue().contains("fax:" + numS2));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.MOBILE.getCodeValue());
	}

	/**
	 * Test method for
	 * {@link org.husky.common.model.Telecoms#addPhone(java.lang.String, org.husky.common.enums.TelecomAddressUse)}
	 * .
	 */
	@Test
	public void testAddPhone() {
		final Telecom t = new Telecom();

		t.setPhone(telS1);
		t.setUsage(TelecomAddressUse.BUSINESS);

		t.setPhone(telS2);
		t.setUsage(TelecomAddressUse.MOBILE);

		assertTrue(t.getValue().contains("tel:" + telS2));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.MOBILE.getCodeValue());
	}

	/**
	 * Test method for
	 * {@link org.husky.common.model.Telecoms#addWebsite(java.lang.String, org.husky.common.enums.TelecomAddressUse)}
	 * .
	 */
	@Test
	public void testAddWebsite() {
		final Telecom t = new Telecom();

		t.setValue(testWebsite1);
		t.setUsage(TelecomAddressUse.BUSINESS);

		assertTrue(t.getValue().contains(testWebsite1));
		assertEquals(t.getUsage().getCodeValue(), TelecomAddressUse.BUSINESS.getCodeValue());
	}

	/**
	 * Test method for
	 * {@link org.husky.common.model.Telecoms#Telecoms(org.eclipse.emf.common.util.EList)}
	 * .
	 */
	@Test
	@Disabled
	public void testTelecomsEListOfTEL() {
		fail("Not yet implemented");
	}

}