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
package org.husky.appc.ch.pmp.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.common.utils.datatypes.Gln;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The model of a policy set targeting a healthcare professional.
 *
 * @author Quentin Ligier
 **/
public class PmpPolicySetHealthcareProfessional extends PmpPolicySet {

    /**
     * The healthcare professional GLN number.
     */
    private final String hcpGln;

    /**
     * Full constructor.
     *
     * @param id                   The policy set identifier.
     * @param description          The description.
     * @param referencedPolicySets The referenced policy sets.
     * @param validityStartDate    The inclusive start date after which the policy set is valid.
     * @param validityEndDate      The inclusive end date until which the policy set is valid.
     * @param hcpGln               The healthcare professional GLN number.
     */
    public PmpPolicySetHealthcareProfessional(final String id,
                                              @Nullable final String description,
                                              final List<@NonNull ChPolicySetInterface> referencedPolicySets,
                                              @Nullable final LocalDate validityStartDate,
                                              @Nullable final LocalDate validityEndDate,
                                              final String hcpGln) {
        super(id, description, referencedPolicySets, validityStartDate, validityEndDate);
        if (!Gln.match(Objects.requireNonNull(hcpGln))) {
            throw new IllegalArgumentException("The healthcare professional GLN is invalid");
        }
        this.hcpGln = hcpGln;
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public Role getRole() {
        return Role.HEALTHCARE_PROFESSIONAL;
    }

    /**
     * Returns the purpose of use or {@code null} if it's not relevant.
     */
    @Override
    public PurposeOfUse getPurposeOfUse() {
        return PurposeOfUse.NORMAL_ACCESS;
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
     * Returns the subject identifier value or {@code null} if it's not relevant. If it's an OID, it shall be
     * URN-encoded.
     */
    @Override
    public String getSubjectId() {
        return this.hcpGln;
    }

    /**
     * Returns the qualifier of the subject identifier or {@code null} if it's not relevant.
     */
    @Override
    public String getSubjectIdQualifier() {
        return ChAppcUrns.GLN;
    }

    /**
     * Returns the identifier of the subject organization or {@code null} if it's not relevant. If it's an OID, it shall
     * be URN-encoded.
     */
    @Override
    public @Nullable String getSubjectOrganizationId() {
        return null;
    }
}
