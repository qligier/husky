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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
public class ChGenericPolicySet implements ChPolicySetInterface {

    private final String id;

    @Nullable
    private final String description;

    @Nullable
    private final Role role;

    @Nullable
    private final PurposeOfUse purposeOfUse;

    @Nullable
    private final List<@NonNull ConfidentialityCode> confidentialityCodes;

    @Nullable
    private final LocalDate validityStartDate;

    @Nullable
    private final LocalDate validityEndDate;

    @Nullable
    private final String subjectId;

    @Nullable
    private final String subjectIdQualifier;

    @Nullable
    private final String subjectOrganizationId;

    private final List<@NonNull ChPolicyInterface> referencedPolicies;

    private final List<@NonNull ChPolicySetInterface> referencedPolicySets;

    public ChGenericPolicySet(final String id,
                              @Nullable final String description,
                              @Nullable final Role role,
                              @Nullable final PurposeOfUse purposeOfUse,
                              @Nullable final List<@NonNull ConfidentialityCode> confidentialityCodes,
                              @Nullable final LocalDate validityStartDate,
                              @Nullable final LocalDate validityEndDate,
                              @Nullable final String subjectId,
                              @Nullable final String subjectIdQualifier,
                              @Nullable final String subjectOrganizationId,
                              final List<@NonNull ChPolicyInterface> referencedPolicies,
                              final List<@NonNull ChPolicySetInterface> referencedPolicySets) {
        this.id = Objects.requireNonNull(id);
        this.description = description;
        this.role = role;
        this.purposeOfUse = purposeOfUse;
        this.confidentialityCodes = confidentialityCodes;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.subjectId = subjectId;
        this.subjectIdQualifier = subjectIdQualifier;
        this.subjectOrganizationId = subjectOrganizationId;
        this.referencedPolicies = Objects.requireNonNull(referencedPolicies);
        this.referencedPolicySets = Objects.requireNonNull(referencedPolicySets);
    }

    /**
     * Returns the policy set Id.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Returns the policy set description or {@code null} if it isn't set.
     */
    @Override
    public @Nullable String getDescription() {
        return this.description;
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable Role getRole() {
        return this.role;
    }

    /**
     * Returns the purpose of use or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable PurposeOfUse getPurposeOfUse() {
        return this.purposeOfUse;
    }

    /**
     * Returns the list of document confidentiality codes. It may be {@code null} if it's not relevant but must not be
     * an empty list.
     */
    @Override
    public @Nullable List<@NonNull ConfidentialityCode> getConfidentialityCodes() {
        return this.confidentialityCodes;
    }

    /**
     * Returns the inclusive start date after which the policy set is valid or {@code null} if the validity period has
     * no lower bound.
     */
    @Override
    public @Nullable LocalDate getValidityStartDate() {
        return this.validityStartDate;
    }

    /**
     * Returns the inclusive end date until which the policy set is valid or {@code null} if the validity period has no
     * upper bound.
     */
    @Override
    public @Nullable LocalDate getValidityEndDate() {
        return this.validityEndDate;
    }

    /**
     * Returns the subject identifier value or {@code null} if it's not relevant. If it's an OID, it shall be
     * URN-encoded.
     */
    @Override
    public @Nullable String getSubjectId() {
        return this.subjectId;
    }

    /**
     * Returns the qualifier of the subject identifier or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable String getSubjectIdQualifier() {
        return this.subjectIdQualifier;
    }

    /**
     * Returns the identifier of the subject organization or {@code null} if it's not relevant. If it's an OID, it shall
     * be URN-encoded.
     */
    @Override
    public @Nullable String getSubjectOrganizationId() {
        return this.subjectOrganizationId;
    }

    /**
     * Returns the list of referenced policies. It may be empty.
     */
    @Override
    public List<@NonNull ChPolicyInterface> getReferencedPolicies() {
        return this.referencedPolicies;
    }

    /**
     * Returns the list of referenced policy sets. It may be empty.
     * @return
     */
    @Override
    public List<@NonNull ChPolicySetInterface> getReferencedPolicySets() {
        return this.referencedPolicySets;
    }
}
