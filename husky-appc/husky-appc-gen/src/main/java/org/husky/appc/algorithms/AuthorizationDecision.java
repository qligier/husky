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

package org.husky.appc.algorithms;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.enums.AuthorizationDecisionResult;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

/**
 * The result of evaluating applicable policy.
 *
 * @param result The result of the evaluation.
 * @param ruleId The ID of the rule that led to this result ({@code PERMIT} or {@code DENY}) or {@code null}.
 * @author Quentin Ligier
 **/
@Immutable
public record AuthorizationDecision(AuthorizationDecisionResult result,
                                    @Nullable String ruleId) {

    /**
     * Full constructor.
     *
     * @param result The result of the evaluation.
     * @param ruleId The ID of the rule that led to this result ({@code PERMIT} or {@code DENY}) or {@code null}.
     */
    public AuthorizationDecision(final AuthorizationDecisionResult result,
                                 @Nullable final String ruleId) {
        this.result = Objects.requireNonNull(result);
        this.ruleId = ruleId;
    }

    /**
     * Simplified constructor. It sets the {@code ruleId} to {@code null}.
     *
     * @param result The result of the evaluation.
     */
    public AuthorizationDecision(final AuthorizationDecisionResult result) {
        this(result, null);
    }
}
