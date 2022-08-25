package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;
import org.husky.emed.ch.enums.CceDocumentType;

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
}
