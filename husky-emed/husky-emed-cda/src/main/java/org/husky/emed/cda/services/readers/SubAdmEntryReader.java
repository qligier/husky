package org.husky.emed.cda.services.readers;

import org.husky.emed.cda.errors.InvalidEmedContentException;
import org.husky.emed.cda.generated.artdecor.enums.ActSubstanceAdminSubstitutionCode;
import org.husky.emed.cda.generated.hl7cdar2.*;
import org.husky.emed.cda.utils.EntryRelationshipUtils;
import org.husky.emed.cda.utils.TemplateIds;

import static org.husky.emed.cda.utils.TemplateIds.SUBSTITUTION_PERMISSION;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A reader for CDA-CH-EMED SubstanceAdministration elements.
 *
 * @author Quentin Ligier
 */
public class SubAdmEntryReader extends DosageInstructionsReader {

    /**
     * Constructor.
     *
     * @param subAdm The substance administration.
     * @throws InvalidEmedContentException if the substance administration is invalid.
     */
    public SubAdmEntryReader(final POCDMT000040SubstanceAdministration subAdm) throws InvalidEmedContentException {
        super(subAdm);
    }

    /**
     * Returns the item entry ID; it shall contain a root and may contain an extension.
     *
     * @return the item entry ID.
     * @throws InvalidEmedContentException if the item entry ID is missing.
     */
    public II getEntryId() throws InvalidEmedContentException {
        if (this.subAdm.getId().isEmpty() || this.subAdm.getId().get(0) == null) {
            throw new InvalidEmedContentException("Missing ID in the SubstanceAdministration");
        }
        return this.subAdm.getId().get(0);
    }

    /**
     * Returns the reader of the manufactured material.
     */
    public ManufacturedMaterialReader getManufacturedMaterialReader() throws InvalidEmedContentException {
        return Optional.ofNullable(this.subAdm.getConsumable())
            .map(POCDMT000040Consumable::getManufacturedProduct)
            .map(POCDMT000040ManufacturedProduct::getManufacturedMaterial)
            .map(ManufacturedMaterialReader::new)
            .orElseThrow(() -> new InvalidEmedContentException("The manufactured material is missing in the SubstanceAdministration"));
    }

    /**
     * Returns the list of substance substitution acts.
     */
    public List<ActSubstanceAdminSubstitutionCode> getSubstitutionPermissions() {
        return this.subAdm.getEntryRelationship().stream()
            .filter(entryRelationship -> entryRelationship.getTypeCode() == XActRelationshipEntryRelationship.COMP)
            .map(POCDMT000040EntryRelationship::getAct)
            .filter(Objects::nonNull)
            .filter(act -> TemplateIds.isInList(SUBSTITUTION_PERMISSION, act.getTemplateId()))
            .filter(act -> act.getCode() != null)
            .filter(act -> ActSubstanceAdminSubstitutionCode.NONE_L1.getCodeSystemId().equals(act.getCode().getCodeSystem()))
            .map(act -> act.getCode().getCode())
            .map(ActSubstanceAdminSubstitutionCode::getEnum)
            .toList();
    }

    /**
     * Returns the author of the item entry.
     *
     * @return an {@link Optional} that may contain the item entry author.
     */
    public Optional<POCDMT000040Author> getEntryAuthorElement() {
        return Optional.ofNullable(!this.subAdm.getAuthor().isEmpty() ? this.subAdm.getAuthor().get(0) : null);
    }

    /**
     * Returns the author of the original parent document. It's generally set in PML documents only, as the author of
     * the PML document may be different than the author of the MTP/PRE/DIS/PADV document.
     *
     * @return an {@link Optional} tht may contain the parent document author.
     */
    public Optional<POCDMT000040Author> getParentDocumentAuthorElement() {
        return Optional.ofNullable(this.subAdm.getAuthor().size() >= 2 ? this.subAdm.getAuthor().get(1) : null);
    }

    /**
     * Returns the patient medication instructions.
     *
     * @return an {@link Optional} that may contain the patient medication instructions.
     */
    public Optional<String> getPatientMedicationInstructions() throws InvalidEmedContentException {
        return EntryRelationshipUtils.getPatientMedicationInstructions(this.subAdm.getEntryRelationship())
                .map(POCDMT000040Act::getText)
                .map(ED::getTextContent);
    }

    /**
     * Returns the fulfillment instructions.
     *
     * @return an {@link Optional} that may contain the fulfillment instructions.
     */
    public Optional<String> getFulfillmentInstructions() throws InvalidEmedContentException {
        return EntryRelationshipUtils.getFulfillmentInstructions(this.subAdm.getEntryRelationship())
                .map(POCDMT000040Act::getText)
                .map(ED::getTextContent);
    }

    /**
     * Returns the treatment reason.
     *
     * @return an {@link Optional} that may contain the treatment reason.
     */
    public Optional<String> getTreatmentReason() {
        return EntryRelationshipUtils.getObservationsFromEntryRelationshipsByTemplateId(this.subAdm.getEntryRelationship(),
                TemplateIds.TREATMENT_REASON).stream()
                .findFirst()
                .map(POCDMT000040Observation::getText)
                .map(ED::getTextContent);
    }

    /**
     * Returns the dosage intake mode or {@code null} if it's not specified.
     *
     * @return an {@link Optional} that may contain the dosage intake mode.
     */
    public Optional<POCDMT000040SubstanceAdministration> getDosageIntakeModeElement() {
        final List<POCDMT000040SubstanceAdministration> sas =
                EntryRelationshipUtils.getSubstanceAdministrationsFromEntryRelationshipsByTemplateId(this.subAdm.getEntryRelationship(),
                TemplateIds.DOSAGE_INTAKE_MODE);
        return Optional.ofNullable(!sas.isEmpty() ? sas.get(0) : null);
    }

    /**
     * Returns the dosage instructions or {@code null} if it's not specified.
     *
     * @return an {@link Optional} that may contain the dosage instructions.
     */
    public Optional<POCDMT000040SubstanceAdministration> getDosageInstructionsElement() {
        final List<POCDMT000040SubstanceAdministration> sas =
                EntryRelationshipUtils.getSubstanceAdministrationsFromEntryRelationshipsByTemplateId(this.subAdm.getEntryRelationship(),
                TemplateIds.DOSAGE_INSTRUCTIONS);
        return Optional.ofNullable(!sas.isEmpty() ? sas.get(0) : null);
    }

    /**
     * Returns the precondition criterion or {@code null} if it's not specified.
     *
     * @return an {@link Optional} that may contain the precondition criterion.
     */
    public Optional<POCDMT000040Criterion> getPreconditionCriterionElement() {
        return Optional.ofNullable(!this.subAdm.getPrecondition().isEmpty() ?
            this.subAdm.getPrecondition().get(0).getCriterion() : null);
    }

    /**
     * Returns the annotation comment.
     *
     * @return an {@link Optional} that may contain the annotation comment.
     */
    public Optional<String> getAnnotationComment() {
        return EntryRelationshipUtils.getActsFromEntryRelationshipsByTemplateId(this.subAdm.getEntryRelationship(),
                TemplateIds.ANNOTATION_COMMENT).stream()
                .findFirst()
                .map(POCDMT000040Act::getText)
                .map(ED::getTextContent);
    }
}