package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.ch.cda.narrative.enums.ProductCodeType;

import java.util.List;


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
    private NarrativeTreatmentAuthor sectionAuthor;

    /**
     * The author of the original parent document or {@code null} if they're not known.
     */
    @Nullable
    private NarrativeTreatmentAuthor documentAuthor;

    /**
     * The planning time.
     */
    @NonNull
    private String planningTime;

    /**
     * The medication name.
     */
    @NonNull
    private String productName;

    /**
     * The medication code in the GTIN system or in the ATC system.
     */
    @NonNull
    private ProductCodeType codeType;

    /**
     * The form code
     */
    @NonNull
    private String productFormCode;

    /**
     * The product dose capacity
     */
    @NonNull
    private String productDoseCapacity;

    /**
     * The product dose unit
     */
    @NonNull
    private String productDoseUnit;

    /**
     * The active ingredients
     */
    @NonNull
    private List<String> productIngredients;

    /**
     * The lower bound of the planned item validity period.
     */
    @NonNull
    private String treatmentStart;

    /**
     * The higher bound of the planned item validity period or {@code null} if it's not bounded.
     */
    @Nullable
    private String treatmentStop;

    /**
     * The narrative description of the dosage instructions.
     */
    @Nullable
    private String narrativeDosageInstructions;

    /**
     * The number of doses to take in the morning
     */
    @Nullable
    private String dosageIntakeMorning;

    /**
     * The number of doses to take in the noon
     */
    @Nullable
    private String dosageIntakeNoon;

    /**
     * The number of doses to take in the evening
     */
    @Nullable
    private String dosageIntakeEvening;

    /**
     * The number of doses to take in the night
     */
    @Nullable
    private String dosageIntakeNight;

    /**
     * The unit of dosage
     */
    @Nullable
    private String dosageFormCode;

    /**
     * Number of repeats/refills (excluding the initial dispense). It's a non-zero integer if it's limited, it's zero if
     * no repeat/refill is authorized and {@code null} if unlimited repeats/refills are authorized.
     */
    @Nullable
    private Integer repeatNumber;

    /**
     * The medication route of administration or {@code null} if it's not specified.
     */
    @Nullable
    private String routeOfAdministration;

    /**
     * The treatment reason or {@code null} if it isn't provided.
     */
    @Nullable
    private String treatmentReason;

    /**
     * The patient medication instructions or {@code null} if it isn't provided.
     */
    @Nullable
    private String patientMedicationInstructions;

    /**
     * The fulfilment instructions or {@code null} if it isn't provided.
     */
    @Nullable
    private String fulfilmentInstructions;

    /**
     * Whether the treatment is to be taken regularly ({@code false}) or only if required ({@code true}).
     */
    @NonNull
    private boolean inReserve;

    /**
     * The annotation comment or {@code null} if it isn't provided.
     */
    @Nullable
    private String annotationComment;


}
