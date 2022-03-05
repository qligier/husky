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
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.models.*;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The model of a representative target.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetRepresentative extends ChChildPolicySet {

    /**
     * The representative Id.
     */
    private String representativeId;

    /**
     * Simple constructor. A random Id is assigned.
     *
     * @param policySetId      The identifier of the referenced policy set.
     * @param representativeId The representative Id.
     */
    public ChChildPolicySetRepresentative(final String policySetId,
                                          final String representativeId) {
        this(UUID.randomUUID().toString(), null, policySetId, null, null, representativeId);
    }

    /**
     * Full constructor.
     *
     * @param id               The policy set identifier.
     * @param description      The description.
     * @param policySetId      The identifier of the referenced policy set.
     * @param validityStartDate The inclusive start date after which the policy set is valid.
     * @param validityEndDate   The inclusive end date until which the policy set is valid.
     * @param representativeId The representative Id.
     */
    public ChChildPolicySetRepresentative(final String id,
                                          @Nullable final String description,
                                          final String policySetId,
                                          @Nullable final LocalDate validityStartDate,
                                          @Nullable final LocalDate validityEndDate,
                                          final String representativeId) {
        super(id, description, policySetId, validityStartDate, validityEndDate);
        this.representativeId = Objects.requireNonNull(representativeId);
    }

    /**
     * Returns the targeted role.
     */
    public Role getRole() {
        return Role.REPRESENTATIVE;
    }

    public String getRepresentativeId() {
        return this.representativeId;
    }

    public void setRepresentativeId(final String representativeId) {
        this.representativeId = Objects.requireNonNull(representativeId);
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType(this.representativeId),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(ChAppcUrns.REPRESENTATIVE_ID),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID_QUALIFIER, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch3 = new SubjectMatchType(
                new AttributeValueType(new CV(Role.REPRESENTATIVE)),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        // Conjunctive sequence of subject matches
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2,
                subjectMatch3))));
        target.setEnvironments(this.createPolicySetEnvironments());
        return target;
    }
}
