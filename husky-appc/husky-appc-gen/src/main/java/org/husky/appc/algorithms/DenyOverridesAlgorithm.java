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
 * The implementation of the Deny-overrides policy-combining algorithm.
 * <p>
 * It is described in <em>eXtensible Access Control Markup Language 3 (XACML) Version 2.0, Appendix C.1</em>.
 *
 * @author Quentin Ligier
 **/
public class DenyOverridesAlgorithm implements PolicyCombiningAlgorithm {

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationDecisionResult combinePolicies(List<AuthorizationDecisionResult> decisions) {
        var atLeastOnePermit = false;
        for (final var decision : decisions) {
            if (decision == AuthorizationDecisionResult.DENY || decision == AuthorizationDecisionResult.INDETERMINATE) {
                return AuthorizationDecisionResult.DENY;
            } else if (decision == AuthorizationDecisionResult.PERMIT) {
                atLeastOnePermit = true;
            }
        }
        return atLeastOnePermit ? AuthorizationDecisionResult.PERMIT : AuthorizationDecisionResult.NOT_APPLICABLE;
    }
}
