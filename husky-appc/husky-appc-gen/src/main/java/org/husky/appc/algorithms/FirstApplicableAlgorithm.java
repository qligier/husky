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

import org.husky.appc.enums.AuthorizationDecisionResult;

import java.util.List;

/**
 * The implementation of the First-applicable policy-combining algorithm of a policy set.
 * <p>
 * It is described in <em>eXtensible Access Control Markup Language 3 (XACML) Version 2.0, Appendix C.1</em>.
 *
 * @author Quentin Ligier
 **/
public class FirstApplicableAlgorithm implements PolicyCombiningAlgorithm {

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationDecision combinePolicies(final List<AuthorizationDecision> decisions) {
        for (final var decision : decisions) {
            if (decision.result() == AuthorizationDecisionResult.NOT_APPLICABLE) {
                continue;
            }
            return decision;
        }
        return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE, "default");
    }
}
