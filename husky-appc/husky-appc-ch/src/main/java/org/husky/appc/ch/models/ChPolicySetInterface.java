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

/**
 * The interface for policy sets used in this project.
 *
 * @author Quentin Ligier
 **/
public interface ChPolicySetInterface {

    /**
     * Returns the policy set Id.
     */
    String getId();

    /**
     * Returns the policy set description or {@code null} if it isn't set.
     */
    @Nullable String getDescription();

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Nullable Role getRole();

    /**
     * Returns the purpose of use or {@code null} if it's not relevant.
     */
    @Nullable PurposeOfUse getPurposeOfUse();

    /**
     * Returns the list of document confidentiality codes. It may be {@code null} if it's not relevant but must not be
     * an empty list.
     */
    @Nullable List<@NonNull ConfidentialityCode> getConfidentialityCodes();

    /**
     * Returns the inclusive start date after which the policy set is valid or {@code null} if the validity period
     * has no lower bound.
     */
    @Nullable LocalDate getValidityStartDate();

    /**
     * Returns the inclusive end date until which the policy set is valid or {@code null} if the validity period
     * has no upper bound.
     */
    @Nullable LocalDate getValidityEndDate();

    /**
     * Returns the subject identifier value or {@code null} if it's not relevant. If it's an OID, it shall be
     * URN-encoded.
     */
    @Nullable String getSubjectId();

    /**
     * Returns the qualifier of the subject identifier or {@code null} if it's not relevant.
     */
    @Nullable String getSubjectIdQualifier();

    /**
     * Returns the identifier of the subject organization or {@code null} if it's not relevant. If it's an OID, it
     * shall be URN-encoded.
     */
    @Nullable String getSubjectOrganizationId();

    /**
     * Returns the list of referenced policies. It may be empty.
     */
    List<@NonNull ChPolicyInterface> getReferencedPolicies();

    /**
     * Returns the list of referenced policy sets. It may be empty.
     */
    List<@NonNull ChPolicySetInterface> getReferencedPolicySets();
}
