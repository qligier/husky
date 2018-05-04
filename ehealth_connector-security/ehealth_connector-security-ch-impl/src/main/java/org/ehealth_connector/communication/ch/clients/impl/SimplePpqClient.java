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
package org.ehealth_connector.communication.ch.clients.impl;

import java.io.IOException;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.ehealth_connector.communication.ch.clients.PpqClient;
import org.ehealth_connector.communication.ch.ppq.PrivacyPolicyQuery;
import org.ehealth_connector.communication.ch.ppq.config.PpqClientConfig;
import org.ehealth_connector.security.communication.clients.impl.AbstractSoapClient;
import org.ehealth_connector.security.communication.soap.impl.WsaHeaderValue;
import org.ehealth_connector.security.core.SecurityHeaderElement;
import org.ehealth_connector.security.exceptions.ClientSendException;
import org.ehealth_connector.security.exceptions.SerializeException;
import org.ehealth_connector.security.saml2.Assertion;
import org.ehealth_connector.security.saml2.EncryptedAssertion;
import org.ehealth_connector.security.saml2.Response;
import org.ehealth_connector.security.saml2.impl.ResponseBuilderImpl;
import org.ehealth_connector.security.serialization.impl.AssertionSerializerImpl;
import org.ehealth_connector.security.serialization.impl.EncryptedAssertionSerializerImpl;
import org.ehealth_connector.security.serialization.impl.PrivacyPolicyQuerySerializerImpl;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.core.impl.ResponseUnmarshaller;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the simple ppq client.</div>
 * <div class="de">Klasser die den Simple PPQ Client implementiert.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 */
public class SimplePpqClient extends AbstractSoapClient<Response> implements PpqClient {

	public SimplePpqClient(PpqClientConfig clientConfiguration) {
		setLogger(LoggerFactory.getLogger(getClass()));
		setConfig(clientConfiguration);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.communication.ch.clients.PpqClient#send(org.ehealth_connector.security.saml2.Assertion,
	 *      org.ehealth_connector.communication.ch.ppq.PrivacyPolicyQuery)
	 */
	@Override
	public Response send(SecurityHeaderElement aAssertion, PrivacyPolicyQuery query) throws ClientSendException {
		try {
			final HttpPost post = getHttpPost();

			final WsaHeaderValue wsHeaders = new WsaHeaderValue("urn:uuid:" + UUID.randomUUID().toString(),
					"http://docs.oasis-open.org/ws-sx/ws-trust/200512/RST/Issue", null);

			post.setEntity(getSoapEntity(aAssertion, query, wsHeaders));

			return execute(post);
		} catch (final Throwable t) {
			throw new ClientSendException(t);
		}
	}

	private HttpEntity getSoapEntity(SecurityHeaderElement aSecurityHeaderElement, PrivacyPolicyQuery query,
			WsaHeaderValue wsHeaders) throws ParserConfigurationException, SerializeException, TransformerException {
		final Element envelopElement = createEnvelope();

		Element headerAssertionElement = null;
		if (aSecurityHeaderElement instanceof Assertion) {
			headerAssertionElement = new AssertionSerializerImpl().toXmlElement((Assertion) aSecurityHeaderElement);
		} else if (aSecurityHeaderElement instanceof EncryptedAssertion) {
			headerAssertionElement = new EncryptedAssertionSerializerImpl()
					.toXmlElement((EncryptedAssertion) aSecurityHeaderElement);
		}
		createHeader(headerAssertionElement, wsHeaders, envelopElement);

		// serialize the authnrequest to xml element
		final PrivacyPolicyQuerySerializerImpl serializer = new PrivacyPolicyQuerySerializerImpl();
		final Element authnRequestElement = serializer.toXmlElement(query);

		createBody(authnRequestElement, envelopElement);

		final String body = createXmlString(envelopElement);

		getLogger().debug("SOAP Message\n" + body);

		// add string as body to httpentity
		final StringEntity stringEntity = new StringEntity(body, "UTF-8");
		stringEntity.setChunked(false);

		return stringEntity;
	}

	@Override
	protected Response parseResponse(CloseableHttpResponse httpResponse) throws ClientSendException {
		try {
			// "urn:oasis:names:tc:SAML:2.0:protocol", "Response"
			final Element reponseElement = getResponseElement(httpResponse, SAMLConstants.SAML20P_NS,
					org.opensaml.saml.saml2.core.Response.DEFAULT_ELEMENT_LOCAL_NAME);

			final org.opensaml.saml.saml2.core.Response response = (org.opensaml.saml.saml2.core.Response) new ResponseUnmarshaller()
					.unmarshall(reponseElement);

			return new ResponseBuilderImpl().create(response);
		} catch (UnsupportedOperationException | IOException | TransformerFactoryConfigurationError
				| ParserConfigurationException | SAXException | UnmarshallingException | XPathExpressionException e) {
			throw new ClientSendException(e);
		}
	}

}
