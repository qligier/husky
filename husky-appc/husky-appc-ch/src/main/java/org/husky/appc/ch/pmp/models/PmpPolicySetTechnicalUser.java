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
import org.husky.appc.ch.pmp.policysets.PolicySetPmpEmedicationWrite;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.util.List;

/**
 * The model of a technical user target.
 *
 * @author Quentin Ligier
 **/
public class PmpPolicySetTechnicalUser extends PmpPolicySet {

    /**
     * Default constructor.
     */
    public PmpPolicySetTechnicalUser() {
        super(
                "8d85346c-5137-4b8c-b7f8-022e629fe404",
                null,
                List.of(PolicySetPmpEmedicationWrite.INSTANCE),
                null,
                null
        );
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable Role getRole() {
        return Role.TECHNICAL_USER;
    }

    /**
     * Returns the purpose of use or {@code null} if it's not relevant.
     */
    @Override
    public @Nullable PurposeOfUse getPurposeOfUse() {
        return PurposeOfUse.AUTOMATIC_UPLOAD;
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
}
