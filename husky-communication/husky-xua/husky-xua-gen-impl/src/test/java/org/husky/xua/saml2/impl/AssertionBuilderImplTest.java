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
package org.husky.xua.saml2.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.husky.xua.saml2.AssertionBuilder;
import org.husky.xua.saml2.impl.AssertionBuilderImpl;
import org.husky.xua.saml2.impl.AttributeBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openehealth.ipf.commons.ihe.xacml20.stub.saml20.assertion.AssertionType;
import org.openehealth.ipf.commons.ihe.xacml20.stub.saml20.assertion.AttributeType;

public class AssertionBuilderImplTest {

	private String testAttributeName;
	private String testAttributeValue;
	private AssertionBuilder testBuilder;
	private String testId;

	@BeforeEach
	public void setUp() throws Exception {
		testBuilder = new AssertionBuilderImpl();

		testId = UUID.randomUUID().toString();

		testAttributeName = "My Attribute Name";
		testAttributeValue = "My Attribute Value";
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.saml2.impl.AssertionBuilderImpl#addAttribute(org.husky.xua.saml2.Attribute)}.
	 */
	@Test
	public void testAddAttribute() {
		final AttributeType attribute = new AttributeBuilderImpl().name(testAttributeName)
				.value(testAttributeValue).create();
		final AssertionType ref = testBuilder.addAttribute(attribute).create();
		assertNotNull(ref);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.saml2.impl.AssertionBuilderImpl#id(java.lang.String)}.
	 */
	@Test
	public void testId() {
		final AssertionType ref = testBuilder.id(testId).create();
		assertEquals(testId, ref.getID());
	}

}