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

package org.ehealth_connector.cda.ch;

import static org.junit.Assert.assertEquals;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.ehealth_connector.cda.ExternalDocumentEntry;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * The Class ExternalDocumentEntryTests.
 */
public class ExternalDocumentEntryTest {

	private XPathFactory xpathFactory = XPathFactory.newInstance();
	private XPath xpath = xpathFactory.newXPath();

	@Test
	public void testSerializeEmpty() throws Exception {
		ExternalDocumentEntry entry = new ExternalDocumentEntry();

		Document document = entry.getDocument();

		XPathExpression expr = xpath
				.compile("externaldocument/templateId[@root='2.16.840.1.113883.10.20.1.36']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("externaldocument[@classCode='DOC' and @moodCode='EVN']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	@Test
	public void testTextReference() throws XPathExpressionException {
		ExternalDocumentEntry entry = new ExternalDocumentEntry();

		entry.setReference("http://www.bag.admin.ch/ekif/04423/04428/index.html",
				"Schweizerischer Impfplan");

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"externaldocument/text/reference[@value='http://www.bag.admin.ch/ekif/04423/04428/index.html']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals("http://www.bag.admin.ch/ekif/04423/04428/index.html",
				entry.getReferenceUrl());

		assertEquals("Schweizerischer Impfplan", entry.getReferenceNarrativeText());

	}

}
