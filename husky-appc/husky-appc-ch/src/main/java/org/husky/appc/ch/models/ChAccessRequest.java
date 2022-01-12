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
import org.husky.appc.ch.enums.ChAction;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.Instant;
import java.util.List;

/**
 * The model of an access request. It contains attributes of the subject, the action and the environment.
 *
 * @author Quentin Ligier
 *
 * @param action The action being performed.
 * @param groupGlns The GLNs of the groups the subject belongs to.
 * @param purposeOfUse The purpose of use of the current access.
 * @param role The role of the subject.
 * @param confidentialityCode The confidentiality code of the document being accessed if applicable, or {@code null}.
 * @param environmentDate The date of the access request.
 **/
public record ChAccessRequest(ChAction action,
                              List<@NonNull String> groupGlns,
                              PurposeOfUse purposeOfUse,
                              Role role,
                              @Nullable ConfidentialityCode confidentialityCode,
                              Instant environmentDate) {

}
