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
import org.husky.appc.ch.pmp.policysets.PolicySetPmpFullAccess;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.util.List;
import java.util.Objects;

/**
 * The model of a patient target.
 *
 * @author Quentin Ligier
 **/
public class PmpPolicySetPatient extends PmpPolicySet {

    /**
     * The patient EPR-SPID value.
     */
    private final String eprSpid;

    /**
     * Full constructor.
     *
     * @param eprSpid The patient EPR-SPID value.
     */
    public PmpPolicySetPatient(final String eprSpid) {
        super(
                "34c5ea8d-9820-4c3e-99d7-463341bbe131",
                null,
                List.of(PolicySetPmpFullAccess.INSTANCE),
                null,
                null
        );
        this.eprSpid = Objects.requireNonNull(eprSpid);
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public Role getRole() {
        return Role.PATIENT;
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
        return this.eprSpid;
    }

    /**
     * Returns the qualifier of the subject identifier or {@code null} if it's not relevant.
     */
    @Override
    public String getSubjectIdQualifier() {
        return ChAppcUrns.EPR_SPID;
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
