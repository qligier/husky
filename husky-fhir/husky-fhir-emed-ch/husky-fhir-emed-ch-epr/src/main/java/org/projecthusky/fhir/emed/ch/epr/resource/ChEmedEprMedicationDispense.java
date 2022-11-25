package org.projecthusky.fhir.emed.ch.epr.resource;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.MedicationDispense;
import org.hl7.fhir.r4.model.StringType;
import org.projecthusky.common.utils.datatypes.Uuids;
import org.projecthusky.fhir.emed.ch.common.annotation.ExpectsValidResource;
import org.projecthusky.fhir.emed.ch.common.enums.EmedEntryType;
import org.projecthusky.fhir.emed.ch.common.error.InvalidEmedContentException;
import org.projecthusky.fhir.emed.ch.common.util.FhirSystem;
import org.projecthusky.fhir.emed.ch.epr.model.common.EmedReference;
import org.projecthusky.fhir.emed.ch.epr.resource.dis.ChEmedEprMedicationDis;
import org.projecthusky.fhir.emed.ch.epr.resource.dosage.ChEmedDosage;
import org.projecthusky.fhir.emed.ch.epr.resource.extension.ChEmedExtPharmaceuticalAdvice;
import org.projecthusky.fhir.emed.ch.epr.resource.extension.ChEmedExtPrescription;
import org.projecthusky.fhir.emed.ch.epr.resource.extension.ChEmedExtTreatmentPlan;
import org.projecthusky.fhir.emed.ch.epr.util.References;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The HAPI custom structure for CH-EMED-EPR MedicationDispense.
 *
 * @author Ronaldo Loureiro
 **/
public abstract class ChEmedEprMedicationDispense extends MedicationDispense implements ChEmedEprEntry {

    /**
     * The treatment reason.
     */
    @Nullable
    @Child(name = "treatmentReason")
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-treatmentreason", definedLocally = false)
    protected StringType treatmentReason;

    /**
     * Reference to the medication prescription.
     */
    @Nullable
    @Child(name = "prescription")
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-prescription", definedLocally = false)
    protected ChEmedExtPrescription prescription;

    /**
     * Reference to the pharmaceutical advice.
     */
    @Nullable
    @Child(name = "pharmaceuticalAdvice")
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-pharmaceuticaladvice", definedLocally = false)
    protected ChEmedExtPharmaceuticalAdvice pharmaceuticalAdvice;

    /**
     * Reference to the medication treatment plan
     */
    @Nullable
    @Child(name = "treatmentPlan", min = 1)
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-treatmentplan", definedLocally = false)
    protected ChEmedExtTreatmentPlan treatmentPlan;

    /**
     * Empty constructor for the parser.
     */
    public ChEmedEprMedicationDispense() {
        super();
    }

    /**
     * Constructor that pre-populates fields.
     *
     * @param entryUuid The medication dispense id.
     */
    public ChEmedEprMedicationDispense(final UUID entryUuid,
                                       final Instant whenHandedOver) {
        super();
        this.setStatus(MedicationDispenseStatus.COMPLETED);
        this.getIdentifierFirstRep().setValue(Uuids.URN_PREFIX + entryUuid).setSystem(FhirSystem.URI);
        this.setWhenHandedOver(Date.from(whenHandedOver));
    }

    /**
     * Resolves the medication dispense UUID or throws.
     *
     * @return the medication dispense UUID.
     * @throws InvalidEmedContentException if the id is missing.
     */
    @ExpectsValidResource
    public UUID resolveIdentifier() throws InvalidEmedContentException {
        if (!this.hasIdentifier()) throw new InvalidEmedContentException("The ID is missing.");
        return Uuids.parseUrnEncoded(this.getIdentifierFirstRep().getValue());
    }

    /**
     * Resolves the medication reference.
     *
     * @return the medication reference.
     * @throws InvalidEmedContentException if the medication reference is missing or if it isn't the right type.
     */
    @ExpectsValidResource
    public ChEmedEprMedicationDis resolveMedicationReference() throws InvalidEmedContentException {
        if (!this.hasMedicationReference())
            throw new InvalidEmedContentException("The medication reference is missing.");
        final var resource = this.getMedicationReference().getResource();
        if (resource instanceof ChEmedEprMedicationDis chMedication) {
            return chMedication;
        }
        throw new InvalidEmedContentException("The medication resource isn't the right type.");
    }

    /**
     * The number of packages.
     *
     * @return the number of packages.
     * @throws InvalidEmedContentException if the number of packages is missing.
     * @throws ArithmeticException         if this has a nonzero fractional part, or will not fit in an int.
     */
    @ExpectsValidResource
    public int resolveQuantity() throws InvalidEmedContentException, ArithmeticException {
        if (!this.hasQuantity()) throw new InvalidEmedContentException("The number of packages is missing.");
        return this.getQuantity().getValue().intValueExact();
    }

    /**
     * Resolves the date/time of when the product was distributed.
     *
     * @return the date/time of when the product was distributed.
     * @throws InvalidEmedContentException if the date/time of when the product was distributed is missing.
     */
    @ExpectsValidResource
    public Instant resolveWhenHandedOver() throws InvalidEmedContentException {
        if (!this.hasWhenHandedOver())
            throw new InvalidEmedContentException("the date/time of when the product was distributed is missing.");
        return this.getWhenHandedOver().toInstant();
    }

    /**
     * Resolves the base entry of the dosage instruction.
     *
     * @return the base entry of the dosage instruction
     * @throws InvalidEmedContentException if the base entry of the dosage instruction is missing.
     */
    @ExpectsValidResource
    public ChEmedDosage resolveBaseDosage() throws InvalidEmedContentException {
        if (!this.getDosageInstruction().isEmpty() && this.getDosageInstruction().get(0) instanceof final ChEmedDosage dosage) {
            return dosage;
        }
        throw new InvalidEmedContentException("Base entry of the dosage instruction is missing.");
    }

    /**
     * Resolves the reference to the treatment plan entry.
     *
     * @return the reference to the treatment plan entry.
     * @throws InvalidEmedContentException if the reference is missing.
     */
    @ExpectsValidResource
    public EmedReference resolveMtpReference() throws InvalidEmedContentException {
        if (!this.hasTreatmentPlan()) {
            throw new InvalidEmedContentException("The treatment plan reference is missing");
        }
        return this.getTreatmentPlanElement().resolveReference();
    }

    /**
     * Resolves the reference to the prescription entry (if any).
     *
     * @return the reference to the prescription entry or {@code null}.
     * @throws InvalidEmedContentException if one of the IDs is missing.
     */
    @ExpectsValidResource
    @Nullable
    public EmedReference resolvePreReference() throws InvalidEmedContentException {
        if (!this.hasPrescription()) {
            return null;
        }
        return this.getPrescriptionElement().resolveReference();
    }

    /**
     * Resolves additional entries of the dosage instruction. The list may be empty.
     *
     * @return additional entries of the dosage instruction.
     */
    public List<ChEmedDosage> resolveAdditionalDosage() {
        return this.getDosageInstruction().stream()
                .filter(ChEmedDosage.class::isInstance)
                .map(ChEmedDosage.class::cast)
                .skip(1)
                .toList();
    }

    @Override
    public EmedEntryType getEmedType() {
        return EmedEntryType.DIS;
    }

    /**
     * Gets the treatment reason. If it doesn't exist, it's created.
     *
     * @return the treatment reason.
     */
    public StringType getTreatmentReason() {
        if (this.treatmentReason == null) {
            this.treatmentReason = new StringType();
        }
        return this.treatmentReason;
    }

    /**
     * Gets the prescription element. If it doesn't exist, it's created.
     *
     * @return the prescription element.
     */
    public ChEmedExtPrescription getPrescriptionElement() {
        if (this.prescription == null) {
            this.prescription = new ChEmedExtPrescription();
        }
        return this.prescription;
    }

    /**
     * Gets the pharmaceutical advice element. If it doesn't exist, it's created.
     *
     * @return the pharmaceutical advice element.
     */
    public ChEmedExtPharmaceuticalAdvice getPharmaceuticalAdviceElement() {
        if (this.pharmaceuticalAdvice == null) {
            this.pharmaceuticalAdvice = new ChEmedExtPharmaceuticalAdvice();
        }
        return this.pharmaceuticalAdvice;
    }

    /**
     * Gets the treatment plan element. If it doesn't exist, it's created.
     *
     * @return the treatment plan element.
     */
    public ChEmedExtTreatmentPlan getTreatmentPlanElement() {
        if (this.treatmentPlan == null) {
            this.treatmentPlan = new ChEmedExtTreatmentPlan();
        }
        return this.treatmentPlan;
    }

    /**
     * Sets the ID of a medication dispense, if it's already exists, it's replaced.
     *
     * @param identifier the ID of a medication dispense.
     * @return this.
     */
    public ChEmedEprMedicationDispense setIdentifier(final UUID identifier) {
        if (!this.hasIdentifier()) {
            this.getIdentifierFirstRep().setSystem(FhirSystem.URI);
        }
        this.getIdentifierFirstRep()
                .setValue(Uuids.URN_PREFIX + identifier);
        return this;
    }

    /**
     * Sets the treatment reason.
     *
     * @param treatmentReason the treatment reason.
     * @return this.
     */
    public ChEmedEprMedicationDispense setTreatmentReason(final String treatmentReason) {
        this.getTreatmentReason().setValue(treatmentReason);
        return this;
    }

    /**
     * Sets the prescription reference.
     *
     * @param prescription the prescription reference.
     * @return this.
     */
    public ChEmedEprMedicationDispense setPrescriptionElement(final ChEmedExtPrescription prescription) {
        this.prescription = prescription;
        return this;
    }

    /**
     * Sets the pharmaceutical advice reference.
     *
     * @param pharmaceuticalAdvice the pharmaceutical advice reference.
     * @return this.
     */
    public ChEmedEprMedicationDispense setPharmaceuticalAdviceElement(final ChEmedExtPharmaceuticalAdvice pharmaceuticalAdvice) {
        this.pharmaceuticalAdvice = pharmaceuticalAdvice;
        return this;
    }

    /**
     * Sets the treatment plan reference.
     *
     * @param treatmentPlan the treatment plan reference.
     * @return this.
     */
    public ChEmedEprMedicationDispense setTreatmentPlanElement(final ChEmedExtTreatmentPlan treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
        return this;
    }

    /**
     * Sets the medication reference.
     *
     * @param chEmedEprMedication the medication resource.
     * @return this.
     */
    public ChEmedEprMedicationDispense setMedicationReference(final ChEmedEprMedicationDis chEmedEprMedication) {
        this.setMedication(References.createReference(chEmedEprMedication));
        this.addContained(chEmedEprMedication);
        return this;
    }

    /**
     * Sets the number of packages.
     *
     * @param quantity the number of packages.
     * @return this.
     */
    public ChEmedEprMedicationDispense setQuantity(final int quantity) {
        this.getQuantity().setValue(quantity);
        return this;
    }

    /**
     * Sets when product was given out.
     *
     * @param whenHandedOver when product was given out.
     * @return this.
     */
    public ChEmedEprMedicationDispense setWhenHandedOver(final Instant whenHandedOver) {
        super.setWhenHandedOver(Date.from(whenHandedOver));
        return this;
    }

    /**
     * Sets the base entry of the dosage instruction. If it already exists, it will be replaced.
     *
     * @param dosageBaseEntry the base entry of the dosage instruction.
     * @return this.
     */
    public ChEmedEprMedicationDispense setDosageBaseEntry(final ChEmedDosage dosageBaseEntry) {
        this.getDosageInstruction().set(0, dosageBaseEntry);
        return this;
    }

    /**
     * Adds additional entry of the dosage instruction.
     *
     * @param dosageAdditionalEntry additional entry of the dosage instruction.
     * @return this.
     */
    public ChEmedEprMedicationDispense addDosageAdditionalEntry(final ChEmedDosage dosageAdditionalEntry) {
        this.getDosageInstruction().add(dosageAdditionalEntry);
        return this;
    }

    /**
     * Returns whether the treatment reason.
     *
     * @return {@code true} if the treatment reason exists, {@code false} otherwise.
     */
    public boolean hasTreatmentReason() {
        return this.treatmentReason != null && !this.treatmentReason.isEmpty();
    }

    /**
     * Returns whether the prescription reference.
     *
     * @return {@code true} if the prescription reference exists, {@code false} otherwise.
     */
    public boolean hasPrescription() {
        return this.prescription != null;
    }

    /**
     * Returns whether the pharmaceutical advice reference.
     *
     * @return {@code true} if the pharmaceutical advice reference exists, {@code false} otherwise.
     */
    public boolean hasPharmaceuticalAdvice() {
        return this.pharmaceuticalAdvice != null;
    }

    /**
     * Returns whether the treatment plan reference.
     *
     * @return {@code true} if the treatment plan reference exists, {@code false} otherwise.
     */
    public boolean hasTreatmentPlan() {
        return this.treatmentPlan != null;
    }

    /**
     * Returns whether the base entry of the dosage instruction.
     *
     * @return {@code true} if the base entry of the dosage instruction exists, {@code false} otherwise.
     */
    public boolean hasDosageBaseEntry() {
        return this.hasDosageInstruction();
    }

    /**
     * Returns whether additional entry of the dosage instruction.
     *
     * @return {@code true} if additional entry of the dosage instruction exists, {@code false} otherwise.
     */
    public boolean hasDosageAdditionalEntry() {
        return this.hasDosageInstruction() && this.getDosageInstruction().size() > 1;
    }

    /**
     * @return {@link #dosageInstruction} (Indicates how the medication is/was or should be taken by the patient.)
     */
    @Override
    public List<Dosage> getDosageInstruction() {
        if (this.dosageInstruction == null)
            this.dosageInstruction = new ArrayList<>();
        else {
            for (int i = 0; i < this.dosageInstruction.size(); ++i) {
                if (!(this.dosageInstruction.get(i) instanceof ChEmedDosage)) {
                    final var newDosage = new ChEmedDosage();
                    this.dosageInstruction.get(i).copyValues(newDosage);
                    this.dosageInstruction.set(i, newDosage);
                }
            }
        }
        return this.dosageInstruction;
    }

    /**
     * @param theDosage
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    @Override
    public MedicationDispense setDosageInstruction(final List<Dosage> theDosage) {
        return super.setDosageInstruction(theDosage);
    }

    @Override
    public ChEmedDosage addDosageInstruction() {
        final var dosage = new ChEmedDosage();
        this.addDosageInstruction(dosage);
        return dosage;
    }

    @Override
    public MedicationDispense addDosageInstruction(final Dosage t) {
        if (t instanceof final ChEmedDosage chEmedDosage) {
            this.dosageInstruction.add(t);
        }
        final var newDosage = new ChEmedDosage();
        t.copyValues(newDosage);
        this.dosageInstruction.add(newDosage);
        return this;
    }

    /**
     * @return The first repetition of repeating field {@link #dosageInstruction}, creating it if it does not already exist
     */
    @Override
    public ChEmedDosage getDosageInstructionFirstRep() {
        if (getDosageInstruction().isEmpty()) {
            addDosageInstruction();
        }
        return (ChEmedDosage) getDosageInstruction().get(0);
    }
}
