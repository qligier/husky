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
import org.husky.appc.enums.AuthorizationDecisionResult;

/**
 * The result of evaluating applicable policy.
 *
 * @author Quentin Ligier
 *
 * @param result The result of the evaluation.
 * @param ruleId The ID of the rule that led to this result ({@code PERMIT} or {@code DENY}) or {@code null}.
 **/
public record AuthorizationDecision(AuthorizationDecisionResult result,
                                    @Nullable String ruleId) {
}
