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
package org.ehealth_connector.security.deserialization.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ehealth_connector.communication.ch.ppq.PrivacyPolicyFeed;
import org.ehealth_connector.security.exceptions.DeserializeException;
import org.ehealth_connector.security.utilities.impl.InitializerTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import oasis.names.tc.saml._2_0.assertion.Assertion;
import suisse.health.e._2015.policy_administration.AssertionBasedRequestType;

public class PrivacyPolicyFeedDeserialiserTest extends InitializerTestHelper {

	private Logger mLogger = LoggerFactory.getLogger(getClass());

	private PrivacyPolicyFeedDeserialiser testDeserializer;
	private Element testXmlElementAddPol;
	private String testXmlStringAddPol;
	private byte[] testXmlByteArrayAddPol;

	private Element testXmlElementUpdPol;
	private String testXmlStringUpdPol;
	private byte[] testXmlByteArrayUpdPol;

	private Element testXmlElementDelPol;
	private String testXmlStringDelPol;
	private byte[] testXmlByteArrayDelPol;

	private String testAddPolAssertionId;
	private String testDelPolAssertionId;
	private String testUpdPolAssertionId;

	@Before
	public void setUp() throws Exception {
		testDeserializer = new PrivacyPolicyFeedDeserialiser();
		testXmlByteArrayAddPol = Files.readAllBytes(
				Paths.get(getClass().getResource("/ch-ppq/add_policy_request_assertion_only.xml").toURI()));
		testXmlStringAddPol = new String(testXmlByteArrayAddPol);

		testXmlElementAddPol = getAssertionAsElement(
				Files.readAllBytes(Paths.get(getClass().getResource("/ch-ppq/add_policy_request.xml").toURI())));

		testXmlByteArrayUpdPol = Files.readAllBytes(
				Paths.get(getClass().getResource("/ch-ppq/update_policy_request_assertion_only.xml").toURI()));
		testXmlStringUpdPol = new String(testXmlByteArrayAddPol);
		testXmlElementUpdPol = getAssertionAsElement(
				Files.readAllBytes(Paths.get(getClass().getResource("/ch-ppq/update_policy_request.xml").toURI())));

		testXmlByteArrayDelPol = Files.readAllBytes(
				Paths.get(getClass().getResource("/ch-ppq/delete_policy_request_assertion_only.xml").toURI()));
		testXmlStringDelPol = new String(testXmlByteArrayDelPol);
		testXmlElementDelPol = getAssertionAsElement(
				Files.readAllBytes(Paths.get(getClass().getResource("/ch-ppq/delete_policy_request.xml").toURI())));

		testAddPolAssertionId = "_b882d240-45cd-4fa3-aa9f-65394115d0e0";
		testUpdPolAssertionId = "_b882d240-45cd-4fa3-aa9f-65394115d0e0";
		testDelPolAssertionId = "_9a71a18c-d7a7-4931-9168-150a8fbc5dc2";
	}

	private Element getAssertionAsElement(byte[] xmlByteArray) throws DeserializeException {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance("suisse.health.e._2015.policy_administration");
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final JAXBElement<?> jxbAssBasReqTyp = (JAXBElement<?>) unmarshaller
					.unmarshal(new ByteArrayInputStream(xmlByteArray));

			final AssertionBasedRequestType wsAssBasReqTyp = (AssertionBasedRequestType) jxbAssBasReqTyp.getValue();

			final Assertion wsAssertion = wsAssBasReqTyp.getAssertion();

			final Marshaller marshaller = jaxbContext.createMarshaller();

			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final Document doc = dbf.newDocumentBuilder().newDocument();
			marshaller.marshal(wsAssertion, doc);
			return doc.getDocumentElement();
		} catch (final Exception e) {
			mLogger.error("error in deserializing to Element.", e);
			throw new DeserializeException(e);
		}
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlElement(org.w3c.dom.Element)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlElementAddPolicy() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlElement(testXmlElementAddPol);
		assertNotNull(ref);
		assertEquals(testAddPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlElement(org.w3c.dom.Element)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlElementUpdatePolicy() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlElement(testXmlElementUpdPol);
		assertNotNull(ref);
		assertEquals(testUpdPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlElement(org.w3c.dom.Element)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlElementDeletePolicy() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlElement(testXmlElementDelPol);
		assertNotNull(ref);
		assertEquals(testDelPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlString(java.lang.String)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlStringAddPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlString(testXmlStringAddPol);
		assertNotNull(ref);
		assertEquals(testAddPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlString(java.lang.String)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlStringUpdPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlString(testXmlStringUpdPol);
		assertNotNull(ref);
		assertEquals(testUpdPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlString(java.lang.String)}.
	 * 
	 * @throws DeserializeException
	 */
	@Test
	public void testFromXmlStringDelPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlString(testXmlStringDelPol);
		assertNotNull(ref);
		assertEquals(testDelPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlByteArray(byte[])}.
	 */
	@Test
	public void testFromXmlByteArrayAddPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlByteArray(testXmlByteArrayAddPol);
		assertNotNull(ref);
		assertEquals(testAddPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlByteArray(byte[])}.
	 */
	@Test
	public void testFromXmlByteArrayUpdPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlByteArray(testXmlByteArrayUpdPol);
		assertNotNull(ref);
		assertEquals(testUpdPolAssertionId, ref.getAssertion().getId());
	}

	/**
	 * Test method for {@link org.ehealth_connector.security.deserialization.impl.PrivacyPolicyFeedDeserialiser#fromXmlByteArray(byte[])}.
	 */
	@Test
	public void testFromXmlByteArrayDelPol() throws DeserializeException {
		final PrivacyPolicyFeed ref = testDeserializer.fromXmlByteArray(testXmlByteArrayDelPol);
		assertNotNull(ref);
		assertEquals(testDelPolAssertionId, ref.getAssertion().getId());
	}

}