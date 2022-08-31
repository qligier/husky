package org.husky.emed.ch.cda.narrative.treatment;

import org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument;
import org.husky.common.utils.xml.XmlFactories;
import org.husky.emed.ch.cda.digesters.CceDocumentDigester;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.enums.ProductCodeType;
import org.husky.emed.ch.cda.services.EmedEntryDigestService;
import org.husky.emed.ch.cda.xml.CceDocumentUnmarshaller;
import org.husky.emed.ch.enums.CceDocumentType;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NarrativeTreatmentDocumentTest {

    private final String DIR_PMLC = "/CDA-CH-EMED/ByHand/pmlc/valid/";

    private final static Class<?> UNMARSHALLED_CLASS = POCDMT000040ClinicalDocument.class;
    private final Unmarshaller UNMARSHALLER;

    public NarrativeTreatmentDocumentTest() throws JAXBException {
        final var context = JAXBContext.newInstance(UNMARSHALLED_CLASS);
        UNMARSHALLER = context.createUnmarshaller();
    }

    @Test
    void testPmlc() throws Exception {
        final var emedEntryDigestServiceImpl = new NarrativeTreatmentDocumentTest.EmedEntryDigestServiceImpl();
        final var digester = new CceDocumentDigester(emedEntryDigestServiceImpl);

        final var pmlcDocument = this.loadDoc(DIR_PMLC + "PMLC_01_valid.xml");
        final var digest = digester.digest(pmlcDocument);

        NarrativeTreatmentDocument document = NarrativeTreatmentDocument.builder(NarrativeLanguage.FRENCH)
                .emedDocumentDigest(digest)
                .build();

        assertEquals("Familien Hausarzt", document.getAuthor1().getName());
        assertEquals("Hausarzt", document.getAuthor1().getOrganization());
        assertNull(document.getAuthor1().getAddress());
        assertEquals(document.getAuthor1(), document.getAuthor2());
        assertEquals(CceDocumentType.PMLC, document.getDocumentType());
        assertEquals("01.01.2022 11:34:00", document.getCreationTime());
        assertEquals("29.11.2011 11:00:00", document.getDocumentationTime());
        assertEquals("Monika Wegmüller", document.getPatientName());
//        assertEquals("", document.getPatientGender());
        assertEquals("15.05.1943", document.getPatientBirthDate());
        assertNull(document.getPatientAddress());
//        assertEquals("", document.getPatientContact());

        assertEquals(0, document.getRecentTreatments().size());
        assertEquals(2, document.getActiveTreatments().size());

        NarrativeTreatmentItem item1 = document.getActiveTreatments().get(0);
        assertEquals("Familien Hausarzt",item1.getSectionAuthor().getName());
        assertEquals("29.11.2011", item1.getPlanningTime());
        assertEquals("PROGRAF caps 0.5 mg 50 Stk", item1.getProductName());
        assertEquals(ProductCodeType.GTIN, item1.getCodeType());
        assertEquals("7680531520746", item1.getProductCode());
        assertEquals("Gélule", item1.getProductFormCode());
        assertEquals("mg", item1.getProductDoseUnit());
        assertEquals(1, item1.getProductIngredients().size());
        assertEquals("Tacrolimus", item1.getProductIngredients().get(0).getName());
        assertEquals("0.5", item1.getProductIngredients().get(0).getQuantityValue());
        assertEquals("10.01.2022", item1.getTreatmentStart());
        assertEquals("10.03.2022", item1.getTreatmentStop());
        assertNull(item1.getNarrativeDosageInstructions());
        assertEquals("0.5", item1.getDosageIntakeMorning());
        assertNull(item1.getDosageIntakeNoon());
        assertNull(item1.getDosageIntakeEvening());
        assertNull(item1.getDosageIntakeNight());
        assertEquals("mg", item1.getDosageUnit());
        assertEquals(1, item1.getRepeatNumber());
        assertEquals("voie orale", item1.getRouteOfAdministration());
        assertEquals("Treatment Reason", item1.getTreatmentReason());
        assertNull(item1.getPatientMedicationInstructions());
        assertNull(item1.getFulfilmentInstructions());
        assertFalse(item1.isInReserve());
        assertEquals("Annotation Comments", item1.getAnnotationComment());
    }

    private POCDMT000040ClinicalDocument loadDoc(final String docName) throws SAXException {
        return CceDocumentUnmarshaller.unmarshall(Objects.requireNonNull(NarrativeTreatmentDocumentTest.class.getResourceAsStream(docName)));
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