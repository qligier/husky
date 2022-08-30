package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.common.enums.AdministrativeGender;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.services.ValueSetEnumNarrativeForPatientService;
import org.husky.emed.ch.enums.CceDocumentType;
import org.husky.emed.ch.models.common.AddressDigest;
import org.husky.emed.ch.models.common.AuthorDigest;
import org.husky.emed.ch.models.common.PatientDigest;
import org.husky.emed.ch.models.common.TelecomDigest;
import org.husky.emed.ch.models.document.EmedDocumentDigest;
import org.husky.emed.ch.models.document.EmedPmlcDocumentDigest;
import org.husky.emed.ch.models.entry.EmedMtpEntryDigest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a narrative treatment document
 *
 * @author Ronaldo Loureiro
 */
public class NarrativeTreatmentDocument {

    /**
     * The type of document
     */
    private final CceDocumentType documentType;

    /**
     * The document creation time
     */
    private final String creationTime;

    /**
     * The time of the documentation
     */
    private final String documentationTime;

    /**
     * The active treatments
     */
    private final List<NarrativeTreatmentItem> activeTreatments;

    /**
     * The recently stopped treatments
     */
    private final List<NarrativeTreatmentItem> recentTreatments;

    /**
     * The author of document
     */
    private final NarrativeTreatmentAuthor author1;

    /**
     * The last medical author of document
     */
    private final NarrativeTreatmentAuthor author2;

    /**
     * The name of patient
     */
    private final String patientName;

    /**
     * The gender of patient
     */
    private final String patientGender;

    /**
     * The birth date of patient
     */
    private final String patientBirthDate;

    /**
     * The address of patient
     */
    private final String patientAddress;

    /**
     * The number phone of patient
     */
    private final String patientContact;

    /**
     * Constructor
     * @param builder the builder
     */
    private NarrativeTreatmentDocument(NarrativeTreatmentDocumentBuilder builder) {
        this.documentType = Objects.requireNonNull(builder.documentType);
        this.creationTime = Objects.requireNonNull(builder.creationTime);
        this.documentationTime = Objects.requireNonNull(builder.documentationTime);
        this.activeTreatments = Objects.requireNonNull(builder.activeTreatments);
        this.recentTreatments = Objects.requireNonNull(builder.recentTreatments);
        this.author1 = Objects.requireNonNull(builder.author1);
        this.author2 = Objects.requireNonNull(builder.author2);
        this.patientName = Objects.requireNonNull(builder.patientName);
        this.patientGender = Objects.requireNonNull(builder.patientGender);
        this.patientBirthDate = Objects.requireNonNull(builder.patientBirthDate);
        this.patientAddress = builder.patientAddress;
        this.patientContact = builder.patientContact;
    }

    /**
     * Creates builder to build {@link NarrativeTreatmentDocument}.
     * @param narrativeLanguage language in which the item should be generated
     *
     * @return created builder
     * @throws IOException
     */
    public static NarrativeTreatmentDocumentBuilder builder(NarrativeLanguage narrativeLanguage) throws IOException {
        return new NarrativeTreatmentDocumentBuilder(narrativeLanguage);
    }

    @NonNull
    public CceDocumentType getDocumentType() {
        return this.documentType;
    }

    @NonNull
    public String getCreationTime() {
        return this.creationTime;
    }

    @NonNull
    public String getDocumentationTime() {
        return this.documentationTime;
    }

    @NonNull
    public List<NarrativeTreatmentItem> getActiveTreatments() {
        return this.activeTreatments;
    }

    @NonNull
    public List<NarrativeTreatmentItem> getRecentTreatments() {
        return this.recentTreatments;
    }

    @NonNull
    public NarrativeTreatmentAuthor getAuthor1() {
        return this.author1;
    }

    @NonNull
    public NarrativeTreatmentAuthor getAuthor2() {
        return this.author2;
    }

    @NonNull
    public String getPatientName() {
        return this.patientName;
    }

    @NonNull
    public  String getPatientGender() {
        return this.patientGender;
    }

    @NonNull
    public String getPatientBirthDate() {
        return this.patientBirthDate;
    }

    @Nullable
    public String getPatientAddress() { return this.patientAddress; }

    @Nullable
    public String getPatientContact() {
        return this.patientContact;
    }


    public static class NarrativeTreatmentDocumentBuilder {
        private final String DATE_PATTERN = "dd.MM.yyyy";
        private final String DATETIME_PATTERN = "dd.MM.yyyy hh:mm:ss";
        private final NarrativeLanguage narrativeLanguage;
        private final ValueSetEnumNarrativeForPatientService valueSetEnumNarrativeForPatientService;
        private CceDocumentType documentType;
        private String creationTime;
        private String documentationTime;
        private final List<NarrativeTreatmentItem> activeTreatments;
        private final List<NarrativeTreatmentItem> recentTreatments;
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
            this.activeTreatments = new ArrayList<>();
            this.recentTreatments = new ArrayList<>();
        }

        private String formatTemporalAccessor(String pattern, TemporalAccessor temporal) {
            return DateTimeFormatter.ofPattern(pattern, this.narrativeLanguage.getLocale())
                    .withZone(ZoneId.systemDefault())
                    .format(temporal);
        }

        public NarrativeTreatmentDocumentBuilder documentType(CceDocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public NarrativeTreatmentDocumentBuilder creationTime(TemporalAccessor creationTime) {
            this.creationTime = this.formatTemporalAccessor(DATETIME_PATTERN, creationTime);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder documentationTime(TemporalAccessor documentationTime) {
            this.documentationTime = this.formatTemporalAccessor(DATETIME_PATTERN, documentationTime);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder addActiveTreatments(List<NarrativeTreatmentItem> activeTreatments) {
            this.activeTreatments.addAll(activeTreatments);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder addActiveTreatments(NarrativeTreatmentItem... activeTreatments) {
            this.activeTreatments.addAll(List.of(activeTreatments));
            return this;
        }

        public NarrativeTreatmentDocumentBuilder addRecentTreatments(List<NarrativeTreatmentItem> recentTreatments) {
            this.recentTreatments.addAll(recentTreatments);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder addRecentTreatments(NarrativeTreatmentItem... recentTreatments) {
            this.recentTreatments.addAll(List.of(recentTreatments));
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
            this.patientBirthDate = this.formatTemporalAccessor(DATE_PATTERN, patientBirthDate);
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientAddress(AddressDigest address) {
            this.patientAddress = String.format("%s, %s %s", address.getStreetName(), address.getPostalCode(), address.getCity());
            return this;
        }

        public NarrativeTreatmentDocumentBuilder patientContact(TelecomDigest contact) {
            this.patientContact = "TO BE DEFINED";
            return this;
        }

        public NarrativeTreatmentDocumentBuilder emedDocumentDigest(EmedDocumentDigest documentDigest) {
            this.documentType(documentDigest.getDocumentType());
            this.creationTime(documentDigest.getCreationTime());
            this.documentationTime(documentDigest.getDocumentationTime());
            this.patient(documentDigest.getPatient());
            if (documentDigest.getAuthors().size() > 0) {
                this.author1(documentDigest.getAuthors().get(0));
                this.author2(documentDigest.getAuthors().get(documentDigest.getAuthors().size() == 2 ? 1 : 0));
            }

            if (documentDigest instanceof EmedPmlcDocumentDigest pmlcDocument) {
                this.addActiveTreatments(pmlcDocument.getEntryDigests().stream()
                        .map(mtp -> (EmedMtpEntryDigest)mtp)
                        .map(mtp -> {
                            try {
                                return NarrativeTreatmentItem.builder(this.narrativeLanguage).emedMtpEntryDigest(mtp).build();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList());
            }

            return this;
        }

        public NarrativeTreatmentDocument build() {
            return new NarrativeTreatmentDocument(this);
        }
    }
}
