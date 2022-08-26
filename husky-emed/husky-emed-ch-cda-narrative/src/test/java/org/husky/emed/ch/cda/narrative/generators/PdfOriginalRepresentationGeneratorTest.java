package org.husky.emed.ch.cda.narrative.generators;

import org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument;
import org.husky.common.utils.xml.XmlFactories;
import org.husky.emed.ch.cda.digesters.CceDocumentDigester;
import org.husky.emed.ch.cda.services.EmedEntryDigestService;
import org.husky.emed.ch.cda.xml.CceDocumentUnmarshaller;
import org.husky.emed.ch.models.entry.EmedEntryDigest;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PdfOriginalRepresentationGeneratorTest {

    private final static Class<?> UNMARSHALLED_CLASS = POCDMT000040ClinicalDocument.class;
    private final Unmarshaller UNMARSHALLER;

    public PdfOriginalRepresentationGeneratorTest() throws JAXBException {
        final var context = JAXBContext.newInstance(UNMARSHALLED_CLASS);
        UNMARSHALLER = context.createUnmarshaller();
    }

    void testPdf() throws Exception {
        final var emedEntryDigestServiceImpl = new EmedEntryDigestServiceImpl();
        final var digester = new CceDocumentDigester(emedEntryDigestServiceImpl);

        emedEntryDigestServiceImpl.addAll(this.getEntryDigests("/MTP_01_valid.xml", digester));
        emedEntryDigestServiceImpl.addAll(this.getEntryDigests("/MTP_02_valid.xml", digester));

        final var pmlcDocument = this.loadDoc("/PMLC_01_valid.xml");
        final var digest = digester.digest(pmlcDocument);

        PdfOriginalRepresentationGenerator pdfGenerator = new PdfOriginalRepresentationGenerator();
        pdfGenerator.generate()

    }

    private List<EmedEntryDigest> getEntryDigests(String docPath, CceDocumentDigester digester) throws Exception {
        final var document = this.loadDoc(docPath);
        return digester.digest(document).getEntryDigests();
    }

    private POCDMT000040ClinicalDocument loadDoc(final String docName) throws SAXException {
        return CceDocumentUnmarshaller.unmarshall(PdfOriginalRepresentationGeneratorTest.class.getResourceAsStream(docName));
    }

    private POCDMT000040ClinicalDocument unmarshall(final String content) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        final var completeElement = """
                <ClinicalDocument xmlns:pharm="urn:ihe:pharm" xmlns="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:hl7-org:v3 ../../../../schemas/PHARM/schemas/cda/extendedschemas/CDA_extended_pharmacy.xsd">
                """ + content + """
                </ClinicalDocument>
                """;

        final var document =
                XmlFactories.newSafeDocumentBuilder().parse(new InputSource(new StringReader(completeElement)));

        final Object root = UNMARSHALLER.unmarshal(document, UNMARSHALLED_CLASS);
        return (POCDMT000040ClinicalDocument) JAXBIntrospector.getValue(root);
    }

    public static class EmedEntryDigestServiceImpl implements EmedEntryDigestService {

        private final List<EmedEntryDigest> digests = new ArrayList<>();

        public Optional<EmedEntryDigest> getById(UUID entryId) {
            return this.digests.stream().filter(digest -> digest.getEntryId().equals(entryId)).findAny();
        }

        public int getSequence(UUID medicationTreatmentId, Instant creationTime) {
            return 0;
        }

        public void add(EmedEntryDigest digest) {
            this.digests.add(digest);
        }

        public void addAll(List<EmedEntryDigest> digests) {
            this.digests.addAll(digests);
        }

        @Override
        public Optional<EmedEntryDigest> getById(String entryId) {
            return Optional.empty();
        }

        @Override
        public Optional<EmedEntryDigest> getByDocument(String documentUniqueId) {
            return Optional.empty();
        }

        @Override
        public long getSequence(String medicationTreatmentId, Instant documentationTime) {
            return 0;
        }
    }

}