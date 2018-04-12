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
package org.ehealth_connector.security.saml2.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.ehealth_connector.security.saml2.Subject;
import org.ehealth_connector.security.saml2.SubjectConfirmation;
import org.junit.Before;
import org.junit.Test;

public class SubjectBuilderImplTest {

	private SubjectBuilderImpl builder;
	private org.opensaml.saml.saml2.core.Subject testInnerObject;
	private String testNameIdValue;
	private String testNameIdFormat;
	private List<SubjectConfirmation> testSubjectConfirmations;
	private String testAddress;
	private SubjectConfirmation testSubjectConfirm;

	@Before
	public void setUp() throws Exception {
		builder = new SubjectBuilderImpl();

		testInnerObject = new org.opensaml.saml.saml2.core.impl.SubjectBuilder().buildObject();
		final org.opensaml.saml.saml2.core.NameID nameID = new org.opensaml.saml.saml2.core.impl.NameIDBuilder()
				.buildObject();

		testNameIdValue = UUID.randomUUID().toString();
		nameID.setValue(testNameIdValue);
		testInnerObject.setNameID(nameID);

		testNameIdFormat = "MyFormat for nameId";

		testAddress = "http://my.address.ch/test/it/now";

		testSubjectConfirmations = new ArrayList<>();
		testSubjectConfirm = new SubjectConfirmationBuilderImpl().address(testAddress).create();
		testSubjectConfirmations.add(testSubjectConfirm);
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.saml2.impl.SubjectBuilderImpl#nameIDFormat(java.lang.String)}.
	 */
	@Test
	public void testNameIDFormat() {
		final Subject ref = builder.nameIDFormat(testNameIdFormat).create();
		assertEquals(testNameIdFormat, ref.getNameIDFormat());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.saml2.impl.SubjectBuilderImpl#nameIDValue(java.lang.String)}.
	 */
	@Test
	public void testNameIDValue() {
		final Subject ref = builder.nameIDValue(testNameIdValue).create();
		assertEquals(testNameIdValue, ref.getNameIDValue());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.saml2.impl.SubjectBuilderImpl#subjectConfirmations(java.util.List)}.
	 */
	@Test
	public void testSubjectConfirmations() {
		final Subject ref = builder.subjectConfirmations(testSubjectConfirmations).create();
		assertArrayEquals(testSubjectConfirmations.toArray(new SubjectConfirmation[testSubjectConfirmations.size()]),
				ref.getSubjectConfirmations().toArray(new SubjectConfirmation[testSubjectConfirmations.size()]));
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.saml2.impl.SubjectBuilderImpl#addSubjectConfirmations(org.ehealth_connector.security.saml2.SubjectConfirmation)}.
	 */
	@Test
	public void testAddSubjectConfirmations() {
		final Subject ref = builder.addSubjectConfirmations(testSubjectConfirm).create();
		assertEquals(testSubjectConfirm.getAddress(), ref.getSubjectConfirmations().get(0).getAddress());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.saml2.impl.SubjectBuilderImpl#create(org.opensaml.saml.saml2.core.Subject)}.
	 */
	@Test
	public void testCreateSubject() {
		final Subject ref = builder.create(testInnerObject);
		assertEquals(testInnerObject, ((SubjectImpl) ref).getWrappedObject());
	}

}