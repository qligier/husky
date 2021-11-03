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
package org.husky.xua.deserialization.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.husky.xua.authentication.AuthnRequest;
import org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl;
import org.husky.xua.deserialization.impl.OpenSaml2DeserializerImpl;
import org.husky.xua.exceptions.DeserializeException;
import org.husky.xua.utilities.impl.AbstractTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

public class AuthnRequestDeserializerImplTest extends AbstractTestHelper {

	private AuthnRequestDeserializerImpl testDeserializer;
	private byte[] testXmlByteArray;
	private Element testXmlElement;
	private String testXmlString;

	@Override
	@BeforeEach
	public void setUp() throws Exception {
		super.setUp();
		testDeserializer = new AuthnRequestDeserializerImpl();
		testXmlByteArray = Files
				.readAllBytes(Paths.get(getClass().getResource("/saml2/AuthnRequest.xml").toURI()));
		testXmlString = new String(testXmlByteArray);
		testXmlElement = new OpenSaml2DeserializerImpl<AuthnRequest>()
				.deserializeFromByteArrayToXmlElement(testXmlString.getBytes());
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl#fromXmlByteArray(byte[])}.
	 *
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlByteArray() throws DeserializeException {
		final AuthnRequest ref = testDeserializer.fromXmlByteArray(testXmlByteArray);
		assertNotNull(ref);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl#fromXmlElement(org.w3c.dom.Element)}.
	 *
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlElement() throws DeserializeException {
		final AuthnRequest ref = testDeserializer.fromXmlElement(testXmlElement);
		assertNotNull(ref);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl#fromXmlElement(org.w3c.dom.Element)}.
	 *
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlElement_InputNull() throws DeserializeException {
		assertThrows(DeserializeException.class, () -> testDeserializer.fromXmlElement(null));
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl#fromXmlString(java.lang.String)}.
	 *
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlString() throws DeserializeException {
		final AuthnRequest ref = testDeserializer.fromXmlString(testXmlString);
		assertNotNull(ref);
	}

}