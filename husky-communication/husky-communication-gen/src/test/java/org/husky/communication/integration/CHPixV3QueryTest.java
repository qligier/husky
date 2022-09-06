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

import org.hl7.fhir.r4.model.Identifier;
import org.husky.common.communication.AffinityDomain;
import org.husky.common.communication.Destination;
import org.husky.communication.ConvenienceMasterPatientIndexV3;
import org.husky.communication.mpi.impl.PixV3Query;
import org.husky.communication.testhelper.TestApplication;
import org.husky.fhir.structures.gen.FhirCommon;
import org.husky.fhir.structures.gen.FhirPatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
@ExtendWith(value = SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = { TestApplication.class })
@EnableAutoConfiguration
class CHPixV3QueryTest {

	protected static Logger LOGGER = LoggerFactory.getLogger(CHPixV3QueryTest.class);

	@Autowired
	private ConvenienceMasterPatientIndexV3 convenienceMasterPatientIndexV3Client;

	@Autowired
	protected AuditContext auditContext;
	final String pixUri = "https://ehealthsuisse.ihe-europe.net/PAMSimulator-ejb/PIXManager_Service/PIXManager_PortType";

	final String facilityName = "Waldspital Bern"; // "2.16.840.1.113883.3.72.6.1";
	final String receiverApplicationOid = "1.3.6.1.4.1.12559.11.20.1.10";
	final String senderApplicationOid = "1.2.3.4.123456";

	// local ID settings
	final String localAssigningAuthorityOid = "1.2.3.4.123456.1";
	final String localIdNamespace = "WALDSPITAL";
	final String localId = "waldspital-Id-1234";

	// EPR-SPID settings
	final String spidAssigningAuthorityOid = "2.16.756.5.30.1.127.3.10.3";
	final String spidNamespace = "SPID";

	final String eprSPID = "761337713436974989"; // expected to be returned

	// Community MPI setting
	final String communityAssigningAuthorityOid = "1.3.6.1.4.1.12559.11.20.1";
	final String communityIdNamespace = "CHPAM2";
	final String communityId = "IHE-12361761818786818818"; // adjust to value from community PIX Manager

	/**
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		var app = new SpringApplication(TestApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run();
	}
	
	/**
	 *
	 */
	@Test
	void queryTest() {

		final AffinityDomain affinityDomain = new AffinityDomain();

		final Destination dest = new Destination();
		dest.setUri(URI.create(pixUri));
		dest.setSenderApplicationOid(senderApplicationOid);
		dest.setReceiverApplicationOid(receiverApplicationOid);
		dest.setReceiverFacilityOid(facilityName);
		affinityDomain.setPdqDestination(dest);
		affinityDomain.setPixDestination(dest);

		PixV3Query pixV3Query = new PixV3Query(affinityDomain, localAssigningAuthorityOid, localIdNamespace,
				spidAssigningAuthorityOid, spidNamespace,
				convenienceMasterPatientIndexV3Client.getContext(),
				convenienceMasterPatientIndexV3Client.getAuditContext());

		//
		final Identifier localIdentifier = new Identifier();
		localIdentifier.setValue(localId);
		localIdentifier.setSystem(FhirCommon.addUrnOid(localAssigningAuthorityOid));

		final FhirPatient patient = new FhirPatient();
		patient.getIdentifier().add(localIdentifier);

		// data source settings
		List<String> queryDomainOids = new ArrayList();
		queryDomainOids.add(spidAssigningAuthorityOid);
		queryDomainOids.add(communityAssigningAuthorityOid);

		List<String> returnedIds = pixV3Query.queryPatientId(patient, queryDomainOids, null, null, null);

		assertTrue(returnedIds.size() > 0);

		assertEquals(returnedIds.get(0), eprSPID);

		// TODO this should return the ids in the order set in the queryDomainIds, but does not so in gazelle test system
		// assertEquals(returnedIds.get(1), communityId);
	}

}
