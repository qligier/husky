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

package org.husky.appc.ch.models;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.models.*;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * The model of a policy set targeting healthcare professionals accessing the patient record in an emergency purpose.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetEmergency extends ChChildPolicySet {

    /**
     * Simple constructor. A random Id is assigned.
     *
     * @param policySetId The identifier of the referenced policy set.
     */
    public ChChildPolicySetEmergency(final String policySetId) {
        this(UUID.randomUUID().toString(), null, policySetId, null, null);
    }

    /**
     * Full constructor.
     *
     * @param id          The policy set identifier.
     * @param description The description.
     * @param policySetId The identifier of the referenced policy set.
     * @param validityStartDate The inclusive start date after which the policy set is valid.
     * @param validityEndDate   The inclusive end date until which the policy set is valid.
     */
    public ChChildPolicySetEmergency(final String id,
                                     @Nullable final String description,
                                     final String policySetId,
                                     @Nullable final LocalDate validityStartDate,
                                     @Nullable final LocalDate validityEndDate) {
        super(id, description, policySetId, validityStartDate, validityEndDate);
    }

    /**
     * Returns the targeted role.
     */
    public Role getRole() {
        return Role.HEALTHCARE_PROFESSIONAL;
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType(new CV(Role.HEALTHCARE_PROFESSIONAL)),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(new CV(PurposeOfUse.EMERGENCY_ACCESS)),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_PURPOSE_USE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        // Conjunctive sequence of subject matches
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2))));
        target.setEnvironments(this.createPolicySetEnvironments());
        return target;
    }
}
