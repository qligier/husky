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
import org.husky.appc.ch.pmp.policysets.PolicySetPmpEmedicationAccess;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.util.List;

/**
 * The model of a document administrator target.
 *
 * @author Quentin Ligier
 **/
public class PmpPolicySetDocumentAdministrator extends PmpPolicySet {

    /**
     * Default constructor.
     */
    public PmpPolicySetDocumentAdministrator() {
        super(
                "86bfba5d-003c-47bf-a02f-03b8897cca08",
                null,
                List.of(PolicySetPmpEmedicationAccess.INSTANCE),
                null,
                null
        );
    }

    /**
     * Returns the subject role or {@code null} if it's not relevant.
     */
    @Override
    public Role getRole() {
        return Role.DOCUMENT_ADMINISTRATOR;
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
