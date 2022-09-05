/*
 * This code is made available under the terms of the Eclipse Public License v1.0 
 * in the github project https://github.com/project-husky/husky there you also 
 * find a list of the contributors and the license information.
 * 
 * This project has been developed further and modified by the joined working group Husky 
 * on the basis of the eHealth Connector opensource project from June 28, 2021, 
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */
package org.husky.communication.integration;

import org.apache.commons.io.IOUtils;
import org.husky.common.communication.AffinityDomain;
import org.husky.common.communication.Destination;
import org.husky.common.model.Code;
import org.husky.common.model.Identificator;
import org.husky.common.model.Name;
import org.husky.common.model.Person;
import org.husky.communication.ConvenienceCommunication;
import org.husky.communication.testhelper.TestApplication;
import org.husky.communication.testhelper.XdsTestUtils;
import org.husky.communication.xd.storedquery.FindDocumentsQuery;
import org.husky.communication.xd.storedquery.GetDocumentsQuery;
import org.husky.xua.communication.clients.XuaClient;
import org.husky.xua.communication.clients.impl.ClientFactory;
import org.husky.xua.communication.config.XuaClientConfig;
import org.husky.xua.communication.config.impl.XuaClientConfigBuilderImpl;
import org.husky.xua.communication.xua.RequestType;
import org.husky.xua.communication.xua.TokenType;
import org.husky.xua.communication.xua.XUserAssertionResponse;
import org.husky.xua.communication.xua.impl.XUserAssertionRequestBuilderImpl;
import org.husky.xua.deserialization.impl.AssertionDeserializerImpl;
import org.husky.xua.hl7v3.PurposeOfUse;
import org.husky.xua.hl7v3.Role;
import org.husky.xua.hl7v3.impl.CodedWithEquivalentsBuilder;
import org.husky.xua.saml2.Assertion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AvailabilityStatus;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.ObjectReference;
import org.openehealth.ipf.commons.ihe.xds.core.responses.*;
import org.opensaml.core.config.InitializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the RegistryStoredQuery [ITI-18] transaction with Swiss requirements. This
 * test performs the following steps:
 * 1. load a test IdP Assertion from the disk
 * 2. Use the IdP Assertion in conjunction with the claims (role, purposeOfUse, EPR-SPID of the patient health record)
 * and request a X-User Assertion.
 * 3. Use the X-User Assertion for authorization with the RegistryStoredQuery [ITI-18] transaction
 */
@ExtendWith(value = SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = { TestApplication.class })
@EnableAutoConfiguration
class CHRegistryStoredQueryTest extends XdsTestUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CHRegistryStoredQueryTest.class.getName());

	@Autowired
	private ConvenienceCommunication convenienceCommunication;

	@Autowired
	protected AuditContext auditContext;

	final private String applicationName = "2.16.840.1.113883.3.72.6.5.100.1399";
	final private String facilityName = null;

	final private String senderApplicationOid = "1.2.3.4";

	private AffinityDomain affinityDomain = null;

	/**
	 * This method initializes opensaml, creates and start spring test application and sets the
	 * endpoint of XDS service for querying metadata.
	 *
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {

		// initialize the open saml factories
		InitializationService.initialize();

		// create and start spring test application
		var app = new SpringApplication(TestApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run();

		// sets XDS service endpoint
		affinityDomain = new AffinityDomain();
		final Destination dest = new Destination();

		dest.setUri(new URI(
					"http://ehealthsuisse.ihe-europe.net:8280/xdstools7/sim/epr-testing__for_init_gw_testing/rep/xcq"));

		dest.setSenderApplicationOid(senderApplicationOid);
		dest.setReceiverApplicationOid(applicationName);
		dest.setReceiverFacilityOid(facilityName);
		affinityDomain.setRegistryDestination(dest);
		affinityDomain.setRepositoryDestination(dest);
	}

	/**
	 * Query the XDS Registry for documents of a specific type, class and format code.
	 *
	 * This test checks the behavior of the
	 * {@link ConvenienceCommunication#queryDocuments(org.husky.communication.xd.storedquery.AbstractStoredQuery, org.husky.xua.core.SecurityHeaderElement, String messageId)}
	 * 
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings("java:S5961")
	void queryFindDocuments() throws Exception {

		convenienceCommunication.setAffinityDomain(affinityDomain);

		Identificator patientId = new Identificator("1.3.6.1.4.1.21367.13.20.1000", "IHERED-1024");

		final Code type = new Code("41000179103", "2.16.840.1.113883.6.96", "Immunization Record (record artifact)");
		final Code clazz = new Code("184216000", "2.16.840.1.113883.6.96", "Patient record type (record artifact)");
		final Code format = new Code("urn:che:epr:EPR_Unstructured_Document", "2.16.756.5.30.1.127.3.10.10",
				"Unstructured EPR document");

		FindDocumentsQuery findDocumentsQuery = new FindDocumentsQuery(patientId, AvailabilityStatus.APPROVED, type, clazz, format);

		// Get the X-User Assertion to authorize the Document Submission.
		Assertion xUserAssertion = getXUserAssertion();
		assertNotNull(xUserAssertion);

		// query metadata of documents with patient ID and approved as availability status
		final QueryResponse response = convenienceCommunication.queryDocuments(findDocumentsQuery, xUserAssertion, null);

		// check if query was successful
		assertTrue(response.getErrors().isEmpty());
		assertEquals(Status.SUCCESS, response.getStatus());

		// check that at least one document is stored
		assertTrue(response.getDocumentEntries().size() > 0);

		// output details of the first entry found
		LOGGER.info("**");
		LOGGER.info("Document Entry size is "+response.getDocumentEntries().size());
		DocumentEntry documentEntry = response.getDocumentEntries().get(0);
		LOGGER.info("First document entry is "+documentEntry);
		LOGGER.info("**");

	}

	/**
	 * Retrieve an X-User Assertion for a Healthcare Provider from the test environment. In this test the IdP Assertion
	 * required to authenticate the user in the Get X-User Assertion request is loaded from the disk.
	 *
	 * @throws Exception if something unexpected happens
	 */
	private Assertion getXUserAssertion() throws Exception {

		final String urlToXua = "https://ehealthsuisse.ihe-europe.net:10443/STS?wsdl";
		final String clientKeyStore = "src/test/resources/testKeystoreXua.jks";
		final String clientKeyStorePass = "changeit";

		// initialize XUA client to query XUA assertion
		XuaClientConfig xuaClientConfig = new XuaClientConfigBuilderImpl().clientKeyStore(clientKeyStore)
				.clientKeyStorePassword(clientKeyStorePass).clientKeyStoreType("jks").url(urlToXua).create();

		XuaClient client = ClientFactory.getXuaClient(xuaClientConfig);

		try (InputStream is = new FileInputStream(new File("src/test/resources/IdPAssertionHCP.xml"))) {

			// for testing load an IdP Assertion from disk.
			var idpAssertion = new AssertionDeserializerImpl().fromXmlByteArray(IOUtils.toByteArray(is));

			// define the attributes for the X-User Assertion request
			var role = new CodedWithEquivalentsBuilder().code("HCP").codeSystem("2.16.756.5.30.1.127.3.10.6")
					.displayName("Behandelnde(r)")
					.buildObject(Role.DEFAULT_NS_URI, Role.DEFAULT_ELEMENT_LOCAL_NAME, Role.DEFAULT_PREFIX);

			var purposeOfUse = new CodedWithEquivalentsBuilder().code("NORM").codeSystem("2.16.756.5.30.1.127.3.10.6")
					.displayName("Normal Access").buildObject(PurposeOfUse.DEFAULT_NS_URI,
							PurposeOfUse.DEFAULT_ELEMENT_LOCAL_NAME, PurposeOfUse.DEFAULT_PREFIX);

			String eprSpid = "761337610411265304^^^SPID&2.16.756.5.30.1.127.3.10.3&ISO";

			// build the  X-User Assertion request
			var assertionRequest = new XUserAssertionRequestBuilderImpl().requestType(RequestType.WST_ISSUE)
					.tokenType(TokenType.OASIS_WSS_SAML_PROFILE_11_SAMLV20)
					.purposeOfUse(purposeOfUse).subjectRole(role).resourceId(eprSpid).create();

			// query the  X-User Assertion
			List<XUserAssertionResponse> response = client.send(idpAssertion, assertionRequest);
			return response.get(0).getAssertion();
		}
	}

}
