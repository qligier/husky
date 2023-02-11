package org.projecthusky.fhir.emed.ch.epr.resource.pml;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.projecthusky.fhir.emed.ch.common.annotation.ExpectsValidResource;
import org.projecthusky.fhir.emed.ch.common.error.InvalidEmedContentException;
import org.projecthusky.fhir.emed.ch.common.resource.ChCorePatientEpr;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprMedicationDispense;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprPractitionerRole;
import org.projecthusky.fhir.emed.ch.epr.util.References;

import java.time.Instant;
import java.util.UUID;

/**
 * The HAPI custom structure for CH-EMED-EPR MedicationDispense (PML).
 *
 * @author Ronaldo Loureiro
 **/
public class ChEmedEprMedicationDispensePml extends ChEmedEprMedicationDispense {

    /**
     * Author of the original document if different from the author of the medical decision
     * (MedicationDispense.performer.actor).
     */
    @Nullable
    @Child(name = "authorDocument")
    @Extension(url = "http://fhir.ch/ig/ch-core/StructureDefinition/ch-ext-author")
    protected Reference authorDocument;

    /**
     * Empty constructor for the parser.
     */
    public ChEmedEprMedicationDispensePml() {
        super();
    }

    /**
     * Constructor that pre-populates fields.
     *
     * @param entryUuid The medication dispense id.
     */
    public ChEmedEprMedicationDispensePml(final UUID entryUuid,
                                          final Instant whenHandedOver) {
        super(entryUuid, whenHandedOver);
    }


    /**
     * Resolves the author and her/his organization of the medical decision.
     *
     * @return the author and her/his organization of the medical decision.
     * @throws InvalidEmedContentException if the author and her/his organization of the medical decision are missing or
     *                                     aren't of the right type.
     */
    @ExpectsValidResource
    public ChEmedEprPractitionerRole resolvePerformerActor() throws InvalidEmedContentException {
        if (!this.hasPerformer()) {
            throw new InvalidEmedContentException(
                    "The the author and her/his organization of the medical decision is missing.");
        }
        final var resource = this.getPerformerFirstRep().getActor().getResource();
        if (resource instanceof ChEmedEprPractitionerRole chEmedEprPractitionerRole) {
            return chEmedEprPractitionerRole;
        }
        throw new InvalidEmedContentException(
                "The author and her/his organization of the medical decision resource isn't of the right type.");
    }

    /**
     * Gets the author document element in the medication dispense.
     *
     * @return the author document element.
     */
    public Reference getAuthorDocumentElement() {
        if (this.authorDocument == null) {
            this.authorDocument = new Reference();
        }
        return this.authorDocument;
    }

    /**
     * Gets the last author document resource in the medication statement if available.
     *
     * @return the author document resource or {@code null}.
     * @throws InvalidEmedContentException if the author document resource is invalid.
     */
    @Nullable
    @ExpectsValidResource
    public DomainResource getAuthorDocument() throws InvalidEmedContentException {
        final var resource = getAuthorDocumentElement().getResource();
        if (resource == null) return null;

        if (resource instanceof ChCorePatientEpr || resource instanceof ChEmedEprPractitionerRole) {
            return (DomainResource) resource;
        }
        throw new InvalidEmedContentException("The last author of the original document is invalid");
    }

    /**
     * Sets the author of the original document.
     *
     * @param author the author.
     * @return this.
     */
    public ChEmedEprMedicationDispensePml setAuthorDocument(final IBaseResource author) {
        this.authorDocument = References.createReference((Resource) author);
        return this;
    }

    /**
     * Sets the author and her/his organization of the medical decision.
     *
     * @param actor the author and her/his organization of the medical decision.
     * @return this.
     */
    public ChEmedEprMedicationDispensePml setPerformer(final ChEmedEprPractitionerRole actor) {
        this.getPerformerFirstRep().setActor(References.createReference(actor));
        return this;
    }

    /**
     * Returns whether author document exists.
     *
     * @return {@code true} if the author document exists, {@code false} otherwise.
     */
    public boolean hasAuthorDocument() {
        return this.authorDocument != null && !this.authorDocument.getReference().isEmpty();
    }
}