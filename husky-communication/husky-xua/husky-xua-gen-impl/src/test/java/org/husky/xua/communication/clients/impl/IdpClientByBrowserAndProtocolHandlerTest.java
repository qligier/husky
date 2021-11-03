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
package org.husky.xua.communication.clients.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.husky.xua.authentication.AuthnRequest;
import org.husky.xua.communication.clients.impl.IdpClientByBrowserAndProtocolHandler;
import org.husky.xua.communication.config.impl.IdpClientByBrowserAndProtocolHandlerConfigBuilderImpl;
import org.husky.xua.deserialization.impl.AuthnRequestDeserializerImpl;
import org.husky.xua.exceptions.ClientSendException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class IdpClientByBrowserAndProtocolHandlerTest extends ServerTestHelper {

	private static Logger logger = LoggerFactory
			.getLogger(IdpClientByBrowserAndProtocolHandlerTest.class);

	private static HttpServer server;

	@SuppressWarnings("unused")
	private static int httpPort;

	@BeforeAll
	public static void setUpBefore() throws IOException {
		final SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(15000)
				.setTcpNoDelay(true).build();

		server = ServerBootstrap.bootstrap().setServerInfo("Test/1.1").setSocketConfig(socketConfig)
				.registerHandler("*", new HttpRequestHandler() {

					@Override
					public void handle(HttpRequest request, HttpResponse response,
							HttpContext context) throws HttpException, IOException {
						logger.debug("The request %s", request.getRequestLine());
						response.setStatusCode(500);
						response.setEntity(new StringEntity("Hello this is a testserver"));
					}

				}).create();

		server.start();
		httpPort = server.getLocalPort();
	}

	@AfterAll
	public static void tearDownAfter() {
		server.stop();
	}

	private IdpClientByBrowserAndProtocolHandler client;

	private String testFilename;

	private AuthnRequest testAuthnRequest;

	/**
	 * set up test parameters
	 */
	@BeforeEach
	public void setUp() throws Exception {
		testFilename = "/credential-criteria-registry.properties";

		client = new IdpClientByBrowserAndProtocolHandler(
				new IdpClientByBrowserAndProtocolHandlerConfigBuilderImpl()
						.url("http://localhost:" + getHttpPort() + "/testit").create());
		final Element xmlElement = loadXmlDokument("/saml2/AuthnRequest.xml");
		testAuthnRequest = new AuthnRequestDeserializerImpl().fromXmlElement(xmlElement);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.communication.clients.impl.IdpClientByBrowserAndProtocolHandler#readFromJARFile(java.lang.String)}.
	 *
	 * @throws IOException
	 */
	@Test
	public void testReadFromJARFile() throws IOException {
		final String content = client.readFromJARFile(testFilename);
		assertNotNull(content);
		assertTrue(content.startsWith("org.opensaml.core.criterion.EntityIdCriterion"));
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.communication.clients.impl.IdpClientByBrowserAndProtocolHandler#send(org.husky.xua.authentication.AuthnRequest)}.
	 *
	 * @throws ClientSendException
	 */
	@Test
	@Disabled("This test is not executable in this way.")
	public void testSend() throws ClientSendException {

		final Object ref = client.send(testAuthnRequest);
		assertNotNull(ref);

	}

}