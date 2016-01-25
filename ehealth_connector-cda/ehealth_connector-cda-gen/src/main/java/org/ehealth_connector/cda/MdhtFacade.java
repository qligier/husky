/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/
package org.ehealth_connector.cda;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.rim.InfrastructureRoot;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * MdhtFace is a facade for extending the mdht objects generated by the model
 * The design enables that all derived convenience objects can use the
 * underlying mdht model but the exposing api of the classes is independent of
 * the mdht implementation
 * 
 * @param <E>
 *            the model type to provide for implemting the facade to it
 */
public class MdhtFacade<E extends InfrastructureRoot> {

	/** The log. */
	private final Log log = LogFactory.getLog(MdhtFacade.class);

	/** The facade objects. */
	private E mdht;

	/**
	 * Instantiates a new facade for the provided mdht object.
	 * 
	 * @param mdht
	 *            the mdht model object
	 */
	protected MdhtFacade(E mdht) {
		this.mdht = mdht;
	}

	/**
	 * Instantiates a new facade object for the provided model.
	 * 
	 * @param mdht
	 *            the mdht object which a facade is provided
	 * @param templateIdRoot
	 *            the template id root
	 * @param templateIdExtension
	 *            the template id extension, mdht does not provide templateId
	 *            extension attribute for modeling, this extension necessary for
	 *            swiss templates and can be added during for the specified root
	 *            during constructing the facade
	 */
	protected MdhtFacade(E mdht, String templateIdRoot, String templateIdExtension) {
		this.mdht = mdht;
		if (mdht != null) {
			for (final II ii : mdht.getTemplateIds()) {
				if ((templateIdRoot != null) && templateIdRoot.equals(ii.getRoot())) {
					ii.setExtension(templateIdExtension);
				}
			}
		}
	}

	/**
	 * returns the text of a Level2 section specified by a contendId
	 * 
	 * @param section
	 *            the section
	 * @param reference
	 *            the content id the section should be searched for by the
	 *            reference, reference has to start with a # (relative)
	 * @return the string
	 */
	public String getContentIdText(MdhtFacade<?> section, String reference) {
		final Document document = section.getDocument();

		String contentId = null;

		if ((reference == null) || !reference.startsWith("#")) {
			log.error("reference has to be relative (#)");
			return null;
		}

		contentId = reference.substring(1);

		final XPathFactory xpathFactory = XPathFactory.newInstance();
		final XPath xpath = xpathFactory.newXPath();
		final String xpathExpr = "//content[@ID='" + contentId + "']";
		XPathExpression expr;
		try {
			expr = xpath.compile(xpathExpr);
		} catch (final XPathExpressionException e) {
			log.error("XPathExpression Error", e);
			return null;
		}

		NodeList nodes = null;
		try {
			nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		} catch (final XPathExpressionException e) {
			log.error("XPathExpression Error", e);
			return null;
		}

		if ((nodes == null) || (nodes.getLength() == 0)) {
			return null;
		}

		if (nodes.getLength() > 1) {
			log.error("multiple content IDs found with: " + xpathExpr);
		}

		final String textContent = nodes.item(0).getTextContent();
		if (textContent != null) {
			return textContent.trim();
		}
		return null;
	}

	/**
	 * returns the object serialized as a DOM document which can be used in
	 * testing for correct xml model generation
	 * 
	 * @return the document
	 */
	public Document getDocument() {
		final ByteArrayOutputStream boas = new ByteArrayOutputStream();
		try {
			CDAUtil.saveSnippet(this.getMdht(), boas);
			log.debug(boas.toString());
			final InputSource source = new InputSource(new StringReader(boas.toString()));
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document document = db.parse(source);
			return document;
		} catch (final Exception e1) {
			log.error("exception", e1);
			return null;
		}
	}

	/**
	 * Copy.
	 * 
	 * @return copies the object.
	 */
	public E copy() {
		return EcoreUtil.copy(mdht);
	}

	/**
	 * Gets the mdht object.
	 * 
	 * @return the mdht
	 */
	public E getMdht() {
		return mdht;
	}

}