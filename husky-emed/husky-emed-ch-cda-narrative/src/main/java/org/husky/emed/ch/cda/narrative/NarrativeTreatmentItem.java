package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.enums.ProductCodeType;
import org.husky.emed.ch.cda.narrative.services.ValueSetEnumNarrativeForPatientService;
import org.husky.emed.ch.enums.*;
import org.husky.emed.ch.models.common.AuthorDigest;
import org.husky.emed.ch.models.common.MedicationDosageInstructions;
import org.husky.emed.ch.models.entry.EmedMtpEntryDigest;
import org.husky.emed.ch.models.treatment.MedicationProduct;
import org.husky.emed.ch.models.treatment.MedicationProductIngredient;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


/**
 * Represents an entry in a treatment card
 *
 * @author Ronaldo Loureiro
 */
public class NarrativeTreatmentItem {

    /**
     * The author of the original parent section or {@code null} if they're not known.
     */
    @Nullable
    private final NarrativeTreatmentAuthor sectionAuthor;

    /**
     * The author of the original parent document or {@code null} if they're not known.
     */
    @Nullable
    private final NarrativeTreatmentAuthor documentAuthor;

    /**
     * The planning time.
     */
    private final String planningTime;

    /**
     * The medication name.
     */
    private final String productName;

    /**
     * The medication code type in the GTIN system or in the ATC system.
     */
    private final ProductCodeType codeType;

    /**
     * The medication code in the GTIN system or in the ATC system
     */
    private final String productCode;

    /**
     * The form code
     */
    private final String productFormCode;

    /**
     * The product dose unit
     */
    private final String productDoseUnit;

    /**
     * The active ingredients
     */
    private final List<NarrativeTreatmentIngredient> productIngredients;

    /**
     * The lower bound of the planned item validity period.
     */
    private final String treatmentStart;

    /**
     * The higher bound of the planned item validity period or {@code null} if it's not bounded.
     */
    @Nullable
    private final String treatmentStop;

    /**
     * The narrative description of the dosage instructions.
     */
    @Nullable
    private final String narrativeDosageInstructions;

    /**
     * The number of doses to take in the morning
     */
    @Nullable
    private final String dosageIntakeMorning;

    /**
     * The number of doses to take in the noon
     */
    @Nullable
    private final String dosageIntakeNoon;

    /**
     * The number of doses to take in the evening
     */
    @Nullable
    private final String dosageIntakeEvening;

    /**
     * The number of doses to take in the night
     */
    @Nullable
    private final String dosageIntakeNight;

    /**
     * The unit of dosage
     */
    @Nullable
    private final String dosageUnit;

    /**
     * Number of repeats/refills (excluding the initial dispense). It's a non-zero integer if it's limited, it's zero if
     * no repeat/refill is authorized and {@code null} if unlimited repeats/refills are authorized.
     */
    @Nullable
    private final Integer repeatNumber;

    /**
     * The medication route of administration or {@code null} if it's not specified.
     */
    @Nullable
    private final String routeOfAdministration;

    /**
     * The treatment reason or {@code null} if it isn't provided.
     */
    @Nullable
    private final String treatmentReason;

    /**
     * The patient medication instructions or {@code null} if it isn't provided.
     */
    @Nullable
    private final String patientMedicationInstructions;

    /**
     * The fulfilment instructions or {@code null} if it isn't provided.
     */
    @Nullable
    private final String fulfilmentInstructions;

    /**
     * Whether the treatment is to be taken regularly ({@code false}) or only if required ({@code true}).
     */
    private final boolean inReserve;

    /**
     * The annotation comment or {@code null} if it isn't provided.
     */
    @Nullable
    private final String annotationComment;

    /**
     * Constructor
     * @param builder the builder
     */
    private NarrativeTreatmentItem(NarrativeTreatmentItemBuilder builder) {
        this.sectionAuthor = builder.sectionAuthor;
        this.documentAuthor = builder.documentAuthor;
        this.planningTime = Objects.requireNonNull(builder.planningTime);
        this.productName = Objects.requireNonNull(builder.productName);
        this.codeType = Objects.requireNonNull(builder.codeType);
        this.productCode = Objects.requireNonNull(builder.productCode);
        this.productFormCode = Objects.requireNonNull(builder.productFormCode);
        this.productDoseUnit = Objects.requireNonNull(builder.productDoseUnit);
        this.productIngredients = Objects.requireNonNull(builder.productIngredients);
        this.treatmentStart = Objects.requireNonNull(builder.treatmentStart);
        this.treatmentStop = builder.treatmentStop;
        this.narrativeDosageInstructions = builder.narrativeDosageInstructions;
        this.dosageIntakeMorning = builder.dosageIntakeMorning;
        this.dosageIntakeNoon = builder.dosageIntakeNoon;
        this.dosageIntakeEvening = builder.dosageIntakeEvening;
        this.dosageIntakeNight = builder.dosageIntakeNight;
        this.dosageUnit = builder.dosageUnit;
        this.repeatNumber = builder.repeatNumber;
        this.routeOfAdministration = builder.routeOfAdministration;
        this.treatmentReason = builder.treatmentReason;
        this.patientMedicationInstructions = builder.patientMedicationInstructions;
        this.fulfilmentInstructions = builder.fulfilmentInstructions;
        this.inReserve = builder.inReserve;
        this.annotationComment = builder.annotationComment;
    }

    /**
     * Creates builder to build {@link NarrativeTreatmentItem}.
     * @param narrativeLanguage language in which the item should be generated
     *
     * @return created builder
     * @throws IOException
     */
    public static NarrativeTreatmentItemBuilder builder(NarrativeLanguage narrativeLanguage) throws IOException {
        return new NarrativeTreatmentItemBuilder(narrativeLanguage);
    }

    @Nullable
    public NarrativeTreatmentAuthor getSectionAuthor() {
        return this.sectionAuthor;
    }

    @Nullable
    public NarrativeTreatmentAuthor getDocumentAuthor() {
        return this.documentAuthor;
    }

    @NonNull
    public String getPlanningTime() {
        return this.planningTime;
    }

    @NonNull
    public String getProductName() {
        return this.productName;
    }

    @NonNull
    public ProductCodeType getCodeType() {
        return this.codeType;
    }

    @NonNull
    public String getProductCode() {
        return this.productCode;
    }

    @NonNull
    public String getProductFormCode() {
        return this.productFormCode;
    }

    @NonNull
    public String getProductDoseUnit() {
        return this.productDoseUnit;
    }

    public List<NarrativeTreatmentIngredient> getProductIngredients() {
        return this.productIngredients;
    }

    @NonNull
    public String getTreatmentStart() {
        return this.treatmentStart;
    }

    @Nullable
    public String getTreatmentStop() {
        return this.treatmentStop;
    }

    @Nullable
    public String getNarrativeDosageInstructions() {
        return this.narrativeDosageInstructions;
    }

    @Nullable
    public String getDosageIntakeMorning() {
        return this.dosageIntakeMorning;
    }

    @Nullable
    public String getDosageIntakeNoon() {
        return this.dosageIntakeNoon;
    }

    @Nullable
    public String getDosageIntakeEvening() {
        return this.dosageIntakeEvening;
    }

    @Nullable
    public String getDosageIntakeNight() {
        return this.dosageIntakeNight;
    }

    @Nullable
    public String getDosageUnit() {
        return this.dosageUnit;
    }

    @Nullable
    public Integer getRepeatNumber() {
        return this.repeatNumber;
    }

    @Nullable
    public String getRouteOfAdministration() {
        return this.routeOfAdministration;
    }

    @Nullable
    public String getTreatmentReason() {
        return this.treatmentReason;
    }

    @Nullable
    public String getPatientMedicationInstructions() {
        return this.patientMedicationInstructions;
    }

    @Nullable
    public String getFulfilmentInstructions() {
        return this.fulfilmentInstructions;
    }

    public boolean isInReserve() {
        return this.inReserve;
    }

    @Nullable
    public String getAnnotationComment() {
        return this.annotationComment;
    }

    public static class NarrativeTreatmentItemBuilder {

        private final String DATE_PATTERN = "dd.MM.yyyy";
        private final String DATETIME_PATTERN = "dd.MM.yyyy hh:mm:ss";

        private NarrativeLanguage narrativeLanguage;
        private ValueSetEnumNarrativeForPatientService valueSetEnumNarrativeForPatientService;
        private NarrativeTreatmentAuthor sectionAuthor;
        private NarrativeTreatmentAuthor documentAuthor;
        private String planningTime;
        private String productName;
        private ProductCodeType codeType;
        private String productCode;
        private String productFormCode;
        private String productDoseUnit;
        private List<NarrativeTreatmentIngredient> productIngredients;
        private String treatmentStart;
        private String treatmentStop;
        private String narrativeDosageInstructions;
        private String dosageIntakeMorning;
        private String dosageIntakeNoon;
        private String dosageIntakeEvening;
        private String dosageIntakeNight;
        private String dosageUnit;
        private Integer repeatNumber;
        private String routeOfAdministration;
        private String treatmentReason;
        private String patientMedicationInstructions;
        private String fulfilmentInstructions;
        private boolean inReserve;
        private String annotationComment;

        public NarrativeTreatmentItemBuilder(NarrativeLanguage narrativeLanguage) throws IOException {
            this.narrativeLanguage = narrativeLanguage;
            this.valueSetEnumNarrativeForPatientService = new ValueSetEnumNarrativeForPatientService();
            this.productIngredients = new ArrayList<>();
        }

        public NarrativeTreatmentItemBuilder sectionAuthor(AuthorDigest sectionAuthor) {
            this.sectionAuthor = sectionAuthor != null ? new NarrativeTreatmentAuthor(sectionAuthor) : null;
            return this;
        }

        public NarrativeTreatmentItemBuilder documentAuthor(AuthorDigest documentAuthor) {
            this.documentAuthor = documentAuthor != null ? new NarrativeTreatmentAuthor(documentAuthor) : null;
            return this;
        }

        public NarrativeTreatmentItemBuilder planningTime(Instant planningTime) {
            this.planningTime = DateTimeFormatter.ofPattern(DATE_PATTERN, this.narrativeLanguage.getLocale())
                    .format(planningTime);
            return this;
        }

        public NarrativeTreatmentItemBuilder product(MedicationProduct product) {
            this.productName(product.getName());
            if (product.getFormCode() != null)
                this.productFormCode(product.getFormCode());

            if (product.getGtinCode() != null) {
                this.codeType(ProductCodeType.GTIN);
                this.productCode(product.getGtinCode());
            } else if (product.getAtcCode() != null) {
                this.codeType(ProductCodeType.ATC);
                this.productCode(product.getAtcCode());
            }

            this.productDoseUnit(product.getIngredients().stream()
                    .map(MedicationProductIngredient::getQuantityNumerator)
                    .filter(Objects::nonNull)
                    .map(numerator -> this.valueSetEnumNarrativeForPatientService.getMessage(numerator.getUnit(), narrativeLanguage))
                    .findAny()
                    .orElse(null));

            this.addProductIngredients(product.getIngredients());

            return this;
        }

        public NarrativeTreatmentItemBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public NarrativeTreatmentItemBuilder codeType(ProductCodeType codeType) {
            this.codeType = codeType;
            return this;
        }

        public NarrativeTreatmentItemBuilder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public NarrativeTreatmentItemBuilder productFormCode(PharmaceuticalDoseFormEdqm formCode) {
            this.productFormCode = this.valueSetEnumNarrativeForPatientService.getMessage(formCode, this.narrativeLanguage);
            return this;
        }

        public NarrativeTreatmentItemBuilder productDoseUnit(String productDoseUnit) {
            this.productDoseUnit = productDoseUnit;
            return this;
        }

        public NarrativeTreatmentItemBuilder addProductIngredients(List<MedicationProductIngredient> productIngredients) {
            this.productIngredients.addAll(productIngredients.stream()
                    .map(NarrativeTreatmentIngredient::new)
                    .toList());
            return this;
        }

        public NarrativeTreatmentItemBuilder addProductIngredients(MedicationProductIngredient... productIngredients) {
            this.productIngredients.addAll(Stream.of(productIngredients)
                    .map(NarrativeTreatmentIngredient::new)
                    .toList());
            return this;
        }

        public NarrativeTreatmentItemBuilder dosage(MedicationDosageInstructions dosage) {
            if (dosage.getNarrativeDosageInstructions() != null) {
                return narrativeDosageInstructions(dosage.getNarrativeDosageInstructions());
            }

            if (dosage.getIntakes().size() > 0) {
                for (var intake : dosage.getIntakes()) {
                    String dosageIntake = intake.getDoseQuantity().getValue();
                    switch (intake.getEventTiming()) {
                        case MORNING -> dosageIntakeMorning(dosageIntake);
                        case NOON -> dosageIntakeNoon(dosageIntake);
                        case EVENING -> dosageIntakeEvening(dosageIntake);
                        case NIGHT -> dosageIntakeNight(dosageIntake);
                    }
                }
                dosageUnit(dosage.getIntakes().get(0).getDoseQuantity().getUnit());
            }
            return this;
        }

        public NarrativeTreatmentItemBuilder treatmentStart(Instant treatmentStart) {
            this.treatmentStart = DateTimeFormatter.ofPattern(DATE_PATTERN, this.narrativeLanguage.getLocale())
                    .format(treatmentStart);
            return this;
        }

        public NarrativeTreatmentItemBuilder treatmentStop(Instant treatmentStop) {
            if (treatmentStop != null) {
                this.treatmentStop = DateTimeFormatter.ofPattern(DATE_PATTERN, this.narrativeLanguage.getLocale())
                        .format(treatmentStop);
            }
            return this;
        }

        public NarrativeTreatmentItemBuilder narrativeDosageInstructions(String narrativeDosageInstructions) {
            this.narrativeDosageInstructions = narrativeDosageInstructions;
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageIntakeMorning(String dosageIntakeMorning) {
            this.dosageIntakeMorning = dosageIntakeMorning;
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageIntakeNoon(String dosageIntakeNoon) {
            this.dosageIntakeNoon = dosageIntakeNoon;
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageIntakeEvening(String dosageIntakeEvening) {
            this.dosageIntakeEvening = dosageIntakeEvening;
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageIntakeNight(String dosageIntakeNight) {
            this.dosageIntakeNight = dosageIntakeNight;
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageUnit(RegularUnitCodeAmbu unit) {
            this.dosageUnit = this.valueSetEnumNarrativeForPatientService.getMessage(unit, this.narrativeLanguage);
            return this;
        }

        public NarrativeTreatmentItemBuilder dosageUnit(UnitCode unit) {
            this.dosageUnit = this.valueSetEnumNarrativeForPatientService.getMessage(unit, this.narrativeLanguage);
            return this;
        }

        public NarrativeTreatmentItemBuilder repeatNumber(Integer repeatNumber) {
            this.repeatNumber = repeatNumber;
            return this;
        }

        public NarrativeTreatmentItemBuilder routeOfAdministration(RouteOfAdministrationAmbu routeOfAdministration) {
            if (routeOfAdministration != null) {
                this.routeOfAdministration = this.valueSetEnumNarrativeForPatientService.getMessage(routeOfAdministration, this.narrativeLanguage);
            }
            return this;
        }

        public NarrativeTreatmentItemBuilder routeOfAdministration(RouteOfAdministrationEdqm routeOfAdministration) {
            if (routeOfAdministration != null) {
                this.routeOfAdministration = this.valueSetEnumNarrativeForPatientService.getMessage(routeOfAdministration, this.narrativeLanguage);
            }
            return this;
        }

        public NarrativeTreatmentItemBuilder treatmentReason(String treatmentReason) {
            this.treatmentReason = treatmentReason;
            return this;
        }

        public NarrativeTreatmentItemBuilder patientMedicationInstructions(String patientMedicationInstructions) {
            this.patientMedicationInstructions = patientMedicationInstructions;
            return this;
        }

        public NarrativeTreatmentItemBuilder fulfilmentInstructions(String fulfilmentInstructions) {
            this.fulfilmentInstructions = fulfilmentInstructions;
            return this;
        }

        public NarrativeTreatmentItemBuilder inReserve(boolean inReserve) {
            this.inReserve = inReserve;
            return this;
        }

        public NarrativeTreatmentItemBuilder annotationComment(String annotationComment) {
            this.annotationComment = annotationComment;
            return this;
        }

        public NarrativeTreatmentItemBuilder emedMtpEntryDigest(EmedMtpEntryDigest entryDigest) {
            this.planningTime(entryDigest.getPlanningTime());
            this.documentAuthor(entryDigest.getDocumentAuthor());
            this.sectionAuthor(entryDigest.getSectionAuthor());
            this.annotationComment(entryDigest.getAnnotationComment());
            this.dosage(entryDigest.getDosageInstructions());
            this.product(entryDigest.getProduct());
            this.repeatNumber(entryDigest.getRepeatNumber());
            this.routeOfAdministration(entryDigest.getRouteOfAdministration());
            this.treatmentStart(entryDigest.getPlannedItemValidityStart());
            this.treatmentStop(entryDigest.getPlannedItemValidityStop());
            this.treatmentReason(entryDigest.getTreatmentReason());
            this.patientMedicationInstructions(entryDigest.getPatientMedicationInstructions());
            this.fulfilmentInstructions(entryDigest.getFulfilmentInstructions());
            this.inReserve(entryDigest.isInReserve());

            return this;
        }

        public NarrativeTreatmentItem build() {
            return new NarrativeTreatmentItem(this);
        }
    }
}
