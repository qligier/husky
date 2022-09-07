package org.husky.emed.ch.cda.narrative.generators;

import org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument;
import org.husky.common.utils.xml.XmlFactories;
import org.husky.emed.ch.cda.digesters.CceDocumentDigester;
import org.husky.emed.ch.cda.narrative.services.IndexDbAugmentationService;
import org.husky.emed.ch.cda.narrative.treatment.NarrativeTreatmentDocument;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.services.EmedEntryDigestService;
import org.husky.emed.ch.cda.xml.CceDocumentUnmarshaller;
import org.husky.emed.ch.models.common.AddressDigest;
import org.husky.emed.ch.models.common.TelecomDigest;
import org.husky.emed.ch.models.entry.EmedEntryDigest;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

class PdfOriginalRepresentationGeneratorTest {

    private final static Class<?> UNMARSHALLED_CLASS = POCDMT000040ClinicalDocument.class;
    private final Unmarshaller UNMARSHALLER;

    public PdfOriginalRepresentationGeneratorTest() throws JAXBException {
        final var context = JAXBContext.newInstance(UNMARSHALLED_CLASS);
        UNMARSHALLER = context.createUnmarshaller();
    }

    @Test
    void testGeneratePdf() throws Exception {
        final var emedEntryDigestServiceImpl = new EmedEntryDigestServiceImpl();
        final var digester = new CceDocumentDigester(emedEntryDigestServiceImpl);

        final var pmlcDocument = this.loadDoc("/pmlc1.xml");
        final var digest = digester.digest(pmlcDocument);

        NarrativeTreatmentDocument doc = NarrativeTreatmentDocument.builder(NarrativeLanguage.FRENCH)
                .emedDocumentDigest(digest)
                .build();

        IndexDbAugmentationService indexDbAugmentationService = new IndexDbAugmentationService("jdbc:postgresql://localhost:5432/swissmeds_o", "postgres", "root");
        for (var i : doc.getActiveTreatments()) {
            indexDbAugmentationService.augment(i, doc.getNarrativeLanguage());
        }

        final var templateHeader = new String(Objects.requireNonNull(PdfOriginalRepresentationGenerator.class.getResourceAsStream("/narrative/default/template.header.html")).readAllBytes(), StandardCharsets.UTF_8);

        PdfOriginalRepresentationGenerator pdfGenerator = new PdfOriginalRepresentationGenerator();
        var pdf = pdfGenerator.generate(doc, templateHeader, "</body></html>");

        OutputStream pdfOut = new FileOutputStream("pdtOut.pdf");
        pdfOut.write(pdf);
        pdfOut.close();
    }

    private POCDMT000040ClinicalDocument loadDoc(final String docName) throws SAXException {
        return CceDocumentUnmarshaller.unmarshall(PdfOriginalRepresentationGeneratorTest.class.getResourceAsStream(docName));
    }

    public static class EmedEntryDigestServiceImpl implements EmedEntryDigestService {
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