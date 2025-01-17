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
package org.projecthusky.fhir.emed.ch.epr.resource.mtp;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.projecthusky.fhir.emed.ch.common.annotation.ExpectsValidResource;
import org.projecthusky.fhir.emed.ch.common.error.InvalidEmedContentException;
import org.projecthusky.fhir.emed.ch.epr.enums.SubstanceAdministrationSubstitutionCode;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprMedicationStatement;

import java.util.UUID;

/**
 * The HAPI custom structure for CH-EMED-EPR MedicationStatement (MTP).
 *
 * @author Quentin Ligier
 **/
@ResourceDef(profile = "https://fhir.cara.ch/StructureDefinition/ch-emed-epr-medicationstatement-treatmentplan")
public class ChEmedEprMedicationStatementMtp extends ChEmedEprMedicationStatement {

    /**
     * Whether the dispenser can substitute the prescribed medicine/package by another that is deemed equivalent, for
     * medical or logistical reasons. By default, substitution is authorized.
     */
    @Nullable
    @Child(name = "substitution")
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-substitution", definedLocally = false)
    protected CodeableConcept substitution;

    /**
     * Empty constructor for the parser.
     */
    public ChEmedEprMedicationStatementMtp() {
        super();
    }

    /**
     * Constructor that pre-populates fields.
     *
     * @param entryUuid the uuid of the medication statement.
     */
    public ChEmedEprMedicationStatementMtp(final UUID entryUuid) {
        super(entryUuid);
    }

    /**
     * Gets the substitution element in the medication statement.
     *
     * @return the substitution element.
     */
    public CodeableConcept getSubstitution() {
        if (this.substitution == null) {
            this.substitution = new CodeableConcept();
        }
        return this.substitution;
    }

    /**
     * Gets the substitution code in the medication statement.
     *
     * @return the substitution code.
     * @throws InvalidEmedContentException if the substitution code is invalid.
     */
    @ExpectsValidResource
    public SubstanceAdministrationSubstitutionCode resolveSubstitution() throws InvalidEmedContentException {
        if (!this.hasSubstitution()) {
            return SubstanceAdministrationSubstitutionCode.EQUIVALENT;
        }
        final var substitutionCode =
                SubstanceAdministrationSubstitutionCode.fromCoding(this.getSubstitution().getCodingFirstRep());
        if (substitutionCode == null) {
            throw new InvalidEmedContentException("The substitution code is invalid");
        }
        return substitutionCode;
    }

    /**
     * Returns whether substitution code exists.
     *
     * @return {@code true} if the substitution code exists, {@code false} otherwise.
     */
    public boolean hasSubstitution() {
        return this.substitution != null && !this.substitution.isEmpty();
    }

    /**
     * Sets the substitution element in the medication statement.
     *
     * @param value the substitution element.
     * @return this.
     */
    public ChEmedEprMedicationStatementMtp setSubstitutionElement(final CodeableConcept value) {
        this.substitution = value;
        return this;
    }

    /**
     * Sets the substitution code in the medication statement.
     *
     * @param value the substitution code.
     * @return this.
     */
    public ChEmedEprMedicationStatementMtp setSubstitution(final SubstanceAdministrationSubstitutionCode value) {
        this.setSubstitutionElement(value.getCodeableConcept());
        return this;
    }

    @Override
    public ChEmedEprMedicationStatementMtp copy() {
        final var copy = new ChEmedEprMedicationStatementMtp();
        this.copyValues(copy);
        return copy;
    }

    @Override
    public void copyValues(final MedicationStatement dst) {
        super.copyValues(dst);
        if (dst instanceof final ChEmedEprMedicationStatementMtp als) {
            als.substitution = substitution == null ? null : substitution.copy();
        }
    }
}
