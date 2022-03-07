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
package org.husky.appc.ch.policysets;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.models.ChPolicyInterface;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.appc.ch.policies.PolicyPermitReadingNormal;
import org.husky.appc.ch.policies.PolicyUpdateMetadata;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * The referenced policy set 'access-level normal'.
 *
 * @author Quentin Ligier
 **/
public class PolicySetAccessLevelNormal implements ChPolicySetInterface {
    public static final PolicySetAccessLevelNormal INSTANCE = new PolicySetAccessLevelNormal();

    /**
     * Returns the policy set Id.
     */
    public String getId() {
        return "urn:e-health-suisse:2015:policies:access-level:normal";
    }

    /**
     * Returns the policy set description or {@code null} if it isn't set.
     */
    @Override
    public @Nullable String getDescription() {
        return null;
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable Role getRole() {
        return null;
    }

    /**
     * Returns the purpose of use or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable PurposeOfUse getPurposeOfUse() {
        return null;
    }

    /**
     * Returns the list of document confidentiality codes. It may be {@code null} if it's not relevant but must not be
     * an empty list.
     */
    @Override
    public @Nullable List<@NonNull ConfidentialityCode> getConfidentialityCodes() {
        return null;
    }

    /**
     * Returns the inclusive start date after which the policy set is valid or {@code null} if the validity period has
     * no lower bound.
     */
    @Override
    public @Nullable LocalDate getValidityStartDate() {
        return null;
    }

    /**
     * Returns the inclusive end date until which the policy set is valid or {@code null} if the validity period has no
     * upper bound.
     */
    @Override
    public @Nullable LocalDate getValidityEndDate() {
        return null;
    }

    /**
     * Returns the subject identifier value or {@code null} if it's not relevant. If it's an OID, it shall be
     * URN-encoded.
     */
    @Override
    public @Nullable String getSubjectId() {
        return null;
    }

    /**
     * Returns the qualifier of the subject identifier or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable String getSubjectIdQualifier() {
        return null;
    }

    /**
     * Returns the identifier of the subject organization or {@code null} if it's not relevant. If it's an OID, it shall
     * be URN-encoded.
     */
    @Override
    public @Nullable String getSubjectOrganizationId() {
        return null;
    }

    /**
     * Returns the list of referenced policies. It may be empty.
     */
    @Override
    public List<@NonNull ChPolicyInterface> getReferencedPolicies() {
        return List.of(
                PolicyPermitReadingNormal.INSTANCE,
                PolicyUpdateMetadata.INSTANCE
        );
    }

    /**
     * Returns the list of referenced policy sets. It may be empty.
     * @return
     */
    @Override
    public List<@NonNull ChPolicySetInterface> getReferencedPolicySets() {
        return Collections.emptyList();
    }
}
