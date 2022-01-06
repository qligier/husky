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
 * Interface of policy-combining algorithms.
 * <p>
 * Algorithms are described in <em>eXtensible Access Control Markup Language 3 (XACML) Version 2.0</em>.
 *
 * @author Quentin Ligier
 **/
public interface PolicyCombiningAlgorithm {

    /**
     * Combines the result of multiple policies that have been evaluated to an authorization decision.
     *
     * @param decisions The results of the policies evaluation.
     * @return The combined authorization decision.
     */
    AuthorizationDecisionResult combinePolicies(final List<AuthorizationDecisionResult> decisions);
}
