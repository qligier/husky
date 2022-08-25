package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;
import org.husky.common.enums.AdministrativeGender;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.services.ValueSetEnumNarrativeForPatientService;
import org.husky.emed.ch.enums.CceDocumentType;
import org.husky.emed.ch.models.common.AddressDigest;
import org.husky.emed.ch.models.common.AuthorDigest;
import org.husky.emed.ch.models.common.PatientDigest;
import org.husky.emed.ch.models.common.TelecomDigest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a narrative treatment document
 *
 * @author Ronaldo Loureiro
 */
public class NarrativeTreatmentDocument {

    /**
     * The type of document
     */
    @NonNull
    private CceDocumentType documentType;

    /**
     * The active treatments
     */
    @NonNull
    private List<NarrativeTreatmentItem> activeTreatments;

    /**
     * The recently stopped treatments
     */
    @NonNull
    private List<NarrativeTreatmentItem> recentTreatments;

    /**
     * The author of document
     */
    @NonNull
    private NarrativeTreatmentAuthor author1;

    /**
     * The last medical author of document
     */
    @NonNull
    private NarrativeTreatmentAuthor author2;

    /**
     * The name of patient
     */
    @NonNull
    private String patientName;

    /**
     * The gender of patient
     */
    @NonNull
    private String patientGender;

    /**
     * The birth date of patient
     */
    @NonNull
    private String patientBirthDate;

    /**
     * The address of patient
     */
    @NonNull
    private String patientAddress;

    /**
     * The number phone of patient
     */
    @NonNull
    private String patientContact;

    private NarrativeTreatmentDocument(NarrativeTreatmentDocumentBuilder builder) {
        // TODO
    }

    public static NarrativeTreatmentDocumentBuilder builder(NarrativeLanguage narrativeLanguage) throws IOException {
        return new NarrativeTreatmentDocumentBuilder(narrativeLanguage);
    }


    public static class NarrativeTreatmentDocumentBuilder {
        private final String DATE_PATTERN = "dd.MM.yyyy";
        private final String DATETIME_PATTERN = "dd.MM.yyyy hh:mm:ss";
        private NarrativeLanguage narrativeLanguage;
        private ValueSetEnumNarrativeForPatientService valueSetEnumNarrativeForPatientService;
        private CceDocumentType documentType;
        private List<NarrativeTreatmentItem> activeTreatments;
        private List<NarrativeTreatmentItem> recentTreatments;
        private NarrativeTreatmentAuthor author1;
        private NarrativeTreatmentAuthor author2;
        private String patientName;
        private String patientGender;
        private String patientBirthDate;
        private String patientAddress;
        private String patientContact;

        public NarrativeTreatmentDocumentBuilder(NarrativeLanguage narrativeLanguage) throws IOException {
            this.narrativeLanguage = narrativeLanguage;
            this.valueSetEnumNarrativeForPatientService = new ValueSetEnumNarrativeForPatientService();
        }

        public NarrativeTreatmentDocumentBuilder documentType(CceDocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public NarrativeTreatmentDocumentBuilder activeTreatments(List<NarrativeTreatmentItem> activeTreatments) {
            this.activeTreatments = activeTreatments;
            return this;
        }

        public NarrativeTreatmentDocumentBuilder recentTreatments(List<NarrativeTreatmentItem> recentTreatments) {
            this.recentTreatments = recentTreatments;
            return this;
        }

        public NarrativeTreatmentDocumentBuilder author1(AuthorDigest author) {
            this.author1 = new NarrativeTreatmentAuthor(author);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder author2(AuthorDigest author) {
            this.author2 = new NarrativeTreatmentAuthor(author);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patient(PatientDigest patient) {
            this.patientName(String.format("%s %s", patient.givenName(), patient.familyName()));
            this.patientGender(patient.gender());
            this.patientBirthDate(patient.birthdate());
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientName(String patientName) {
            this.patientName = patientName;
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientGender(AdministrativeGender gender) {
            this.patientGender = this.valueSetEnumNarrativeForPatientService.getMessage(gender, this.narrativeLanguage);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientBirthDate(LocalDate patientBirthDate) {
            this.patientBirthDate = DateTimeFormatter.ofPattern(DATE_PATTERN, this.narrativeLanguage.getLocale())
                    .format(patientBirthDate);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientAddress(AddressDigest address) {
            this.patientAddress = String.format("%s %s %s", address.getStreetName(), address.getPostalCode(), address.getCity());
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientContact(TelecomDigest contact) {
            // TODO : To be defined
            this.patientContact = "TO BE DEFINED";
            return this;
        }

        public NarrativeTreatmentDocument build() {
            NarrativeTreatmentDocument document = new NarrativeTreatmentDocument(this);
            validateNarrativeTreatmentDocument(document);
            return document;
        }

        private void validateNarrativeTreatmentDocument(NarrativeTreatmentDocument document) {
            if (document.documentType == null) {
                throw new IllegalStateException("The document type must be specified");
            }

            if (document.author1 == null) {
                throw new IllegalStateException("The author of document must be specified");
            }

            if (document.author2 == null) {
                throw new IllegalStateException("The last medical author of document must be specified");
            }

            if (document.patientName == null || document.patientGender == null || document.patientAddress == null || document.patientBirthDate == null || document.patientContact == null) {
                throw new IllegalStateException("The patient information are incomplete");
            }
        }
    }
}
