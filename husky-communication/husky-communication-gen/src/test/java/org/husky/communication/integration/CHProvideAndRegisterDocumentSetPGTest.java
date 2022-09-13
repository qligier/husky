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

import org.husky.common.basetypes.NameBaseType;
import org.husky.common.communication.AffinityDomain;
import org.husky.common.communication.Destination;
import org.husky.common.communication.DocumentMetadata;
import org.husky.common.communication.SubmissionSetMetadata;
import org.husky.common.enums.DocumentDescriptor;
import org.husky.common.enums.EhcVersions;
import org.husky.common.enums.LanguageCode;
import org.husky.common.model.Author;
import org.husky.common.model.Code;
import org.husky.common.model.Identificator;
import org.husky.common.model.Name;
import org.husky.communication.ConvenienceCommunication;
import org.husky.communication.testhelper.TestApplication;
import org.husky.communication.testhelper.XdsTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openehealth.ipf.commons.core.OidGenerator;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.responses.Response;
import org.openehealth.ipf.commons.ihe.xds.core.responses.Status;
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
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class to test the ProvideAndRegisterDocumentSet [ITI-41] transaction with Swiss metadata requirements
 * against the EPD Playground.
 */
@ExtendWith(value = SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestApplication.class})
@EnableAutoConfiguration
class CHProvideAndRegisterDocumentSetPGTest extends XdsTestUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(CHProvideAndRegisterDocumentSetPGTest.class.getName());

    @Autowired
    private ConvenienceCommunication convenienceCommunication;

    private AffinityDomain affinityDomain = null;

    // the local patient ID as registered in the PIX Feed transaction
    final String localAssigningAuthorityOid = "1.2.3.4.123456.1";
    final String localPatientId = "waldspital-Id-1234";

    // The global patient ID generated by the community as received with the PIX Query response.
    // The global patient ID is required in this test as destinationId parameter of the transaction.
    final String globalAssigningAuthorityOid = "1.1.1.99.1";
    final String globalPatientId = "2dc7a783-78b1-4627-94fb-610a23135c42";


    /**
     * Creates and start spring test application and set the endpoint of XDS Repository.
     *
     * @throws Exception if something unexpected happens
     */
    @BeforeEach
    public void setUp() throws Exception {

        // initialize the open saml factories
        InitializationService.initialize();

        // create and start spring test application
        var app = new SpringApplication(TestApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run();

        final Destination dest = new Destination();

        // set the URL of the community repository you want to store the document to
        final String repositoryURL = "https://epdplayground.i4mi.bfh.ch:6443/Repository/services/RepositoryService";

        dest.setUri(new URI(repositoryURL));

        // add the OID of your application as assigned by the community
        final String senderApplicationOid = "1.2.3.4";
        dest.setSenderApplicationOid(senderApplicationOid);

        // add an application name
        final String applicationOid = "2.16.840.1.113883.3.72.6.5.100.1399";
        dest.setReceiverApplicationOid(applicationOid);

        // add the name of your institution
        final String facilityOid = null; // TODO use OID
        dest.setReceiverFacilityOid(facilityOid);

        affinityDomain = new AffinityDomain();
        affinityDomain.setRegistryDestination(dest);
        affinityDomain.setRepositoryDestination(dest);

        // remove cached documents in ConvenienceCommunication
        convenienceCommunication.clearDocuments();
    }

    /**
     * This tests the
     * {@link ConvenienceCommunication#submit(SubmissionSetMetadata, org.husky.xua.core.SecurityHeaderElement, String)}
     * with a JSON Document, Swiss compliant metadata and a X-User Assertion
     *
     * @throws Exception if something unexpected happens
     */
    @Test
    void submitJSONDocumentTest() throws Exception {

        convenienceCommunication.setAffinityDomain(affinityDomain);
        convenienceCommunication.clearDocuments();

        // read and add file
        final File file = new File("src/test/resources/docSource/FHIR-Vaccination.json");
        final DocumentMetadata documentMetadata = convenienceCommunication.addDocument(DocumentDescriptor.FHIR_JSON, new FileInputStream(file));

        documentMetadata.setTitle("Impfung");

        // add a extra metadata attribute
        String key = "urn:e-health-suisse:2020:originalProviderRole";
        String code = "HCP^^^&2.16.756.5.30.1.127.3.10.6&ISO"; // TODO should be serialized Code
        DocumentEntry xDoc = documentMetadata.getXDoc();
        Map<String, List<String>> extraMetadata = new HashMap();
        List values = List.of(code);
        extraMetadata.put(key,values);
        xDoc.setExtraMetadata(extraMetadata);

        // set the author data
        final Name name = new Name(new NameBaseType());
        name.setGiven("Peter");
        name.setFamily("Müller");
        name.setPrefix("Dr. med");

        final Author author = new Author();
        author.addName(name);

        final Code role = new Code("HCP", "2.16.756.5.30.1.127.3.10.6", "Healthcare professional");
        author.setRoleFunction(role);

        documentMetadata.addAuthor(author);

        final Identificator globalId = new Identificator(globalAssigningAuthorityOid, globalPatientId);
        documentMetadata.setDestinationPatientId(globalId);

        final Identificator localId = new Identificator(localAssigningAuthorityOid, localPatientId);
        documentMetadata.setSourcePatientId(localId);

        documentMetadata.setCodedLanguage(LanguageCode.ENGLISH_CODE);

        final Code type = new Code("41000179103", "2.16.840.1.113883.6.96", "Immunization Record (record artifact)");
        documentMetadata.setTypeCode(type);

        /*
		    format code for vaccinations shall be ("urn:che:epr:ch-vacd:immunization-administration:2022", "1.3.6.1.4.1.19376.1.2.3", "CH VACD Immunization Administration")
		    but this code is currently not supported by the test system
		    */
        final Code format = new Code("urn:che:epr:EPR_Unstructured_Document", "2.16.756.5.30.1.127.3.10.10",
                "Unstructured EPR document");
        documentMetadata.setFormatCode(format);

        final Code clazz = new Code("184216000", "2.16.840.1.113883.6.96", "Patient record type (record artifact)");
        documentMetadata.setClassCode(clazz);

        final Code facility = new Code("22232009", "2.16.840.1.113883.6.96", "Hospital (environment)");
        documentMetadata.setHealthcareFacilityTypeCode(facility);

        final Code practice = new Code("394802001", "2.16.840.1.113883.6.96", "General medicine (qualifier value)");
        documentMetadata.setPracticeSettingCode(practice);

        final Code confidentiality = new Code("17621005", "2.16.840.1.113883.6.96", "Normal");
        documentMetadata.addConfidentialityCode(confidentiality);


        // submission set metadata settings
        final SubmissionSetMetadata submissionSetMetadata = new SubmissionSetMetadata();
        submissionSetMetadata.setUniqueId(OidGenerator.uniqueOid().toString());

        submissionSetMetadata.setSourceId(EhcVersions.getCurrentVersion().getOid());
        submissionSetMetadata.setEntryUUID(UUID.randomUUID().toString());

        submissionSetMetadata.setDestinationPatientId(globalId);

        // set the provider data
        final Name providerName = new Name(new NameBaseType());
        providerName.setGiven("Gabriela");
        providerName.setFamily("Meier");
        providerName.setPrefix("Dr. med");

        final Author provider = new Author();
        provider.addName(providerName);

        // TODO:
        // Workaround for EPD Playground: When adding a submission set author we run into a problem with the
        // role resulting in a value set error. We need to clarify with ITH icoserve what is expected.
        // Not setting the submission set author solves the problem, but is not compliant with the Swiss EPR
        // specifications.

        final Code providerRole = new Code("HCP", "2.16.756.5.30.1.127.3.10.6", "Healthcare professional");
        provider.setRoleFunction(providerRole);
        submissionSetMetadata.addAuthor(provider);

        final Code contentType = new Code("71388002", "2.16.840.1.113883.6.96", "Procedure (procedure)");
        submissionSetMetadata.setContentTypeCode(contentType);

        // provide and register the document
        final Response response = convenienceCommunication.submit(submissionSetMetadata, null, null);

        // checks whether the document has been successfully submitted
        assertTrue(response.getErrors().isEmpty());
        assertEquals(Status.SUCCESS, response.getStatus());
    }

}
