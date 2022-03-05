/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.ch.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.enums.ChAction;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import javax.annotation.concurrent.Immutable;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The model of an access request. It contains attributes of the subject, the action and the environment.
 *
 * @param action               The action being performed.
 * @param subjectResponsibleId The identifier of the responsible subject. It shall be:
 *                             <ul>
 *                             <li>PAT: the patient EPR-SPID value.</li>
 *                             <li>REP: the representative ID.</li>
 *                             <li>HCP: the healthcare professional GLN.</li>
 *                             <li>TCU: the responsible healthcare professional GLN.</li>
 *                             <li>ASS: the responsible healthcare professional GLN.</li>
 *                             <li>DADM: the document administrator ID.</li>
 *                             <li>PADM: the policy administrator ID.</li>
 *                             </ul>
 * @param groupGlns            The GLNs of the groups the subject belongs to. Only for HCPs, TCUs, ASS'.
 * @param purposeOfUse         The purpose of use of the current access.
 * @param role                 The role of the subject.
 * @param confidentialityCode  The confidentiality code of the document being accessed if applicable, or {@code null}.
 * @param environmentDate      The date of the access request.
 * @author Quentin Ligier
 **/
@Immutable // The collection is also immutable
public record ChAccessRequest(ChAction action,
                              String subjectResponsibleId,
                              List<@NonNull String> groupGlns,
                              PurposeOfUse purposeOfUse,
                              Role role,
                              @Nullable ConfidentialityCode confidentialityCode,
                              Instant environmentDate) {

    /**
     * Constructor.
     *
     * @param action               The action being performed.
     * @param subjectResponsibleId The identifier of the responsible subject. It shall be:
     *                             <ul>
     *                             <li>PAT: the patient EPR-SPID value.</li>
     *                             <li>REP: the representative ID.</li>
     *                             <li>HCP: the healthcare professional GLN.</li>
     *                             <li>TCU: the responsible healthcare professional GLN.</li>
     *                             <li>ASS: the responsible healthcare professional GLN.</li>
     *                             <li>DADM: the document administrator ID.</li>
     *                             <li>PADM: the policy administrator ID.</li>
     *                             </ul>
     * @param groupGlns            The GLNs of the groups the subject belongs to. Only for HCPs, TCUs, ASS'.
     * @param purposeOfUse         The purpose of use of the current access.
     * @param role                 The role of the subject.
     * @param confidentialityCode  The confidentiality code of the document being accessed if applicable, or {@code
     *                             null}.
     * @param environmentDate      The date of the access request.
     */
    public ChAccessRequest(final ChAction action,
                           final String subjectResponsibleId,
                           final List<@NonNull String> groupGlns,
                           final PurposeOfUse purposeOfUse,
                           final Role role,
                           final @Nullable ConfidentialityCode confidentialityCode,
                           final Instant environmentDate) {
        this.action = Objects.requireNonNull(action);
        this.subjectResponsibleId = Objects.requireNonNull(subjectResponsibleId);
        this.groupGlns = Collections.unmodifiableList(Objects.requireNonNull(groupGlns));
        this.purposeOfUse = Objects.requireNonNull(purposeOfUse);
        this.role = Objects.requireNonNull(role);
        this.confidentialityCode = confidentialityCode;
        this.environmentDate = Objects.requireNonNull(environmentDate);
    }
}
