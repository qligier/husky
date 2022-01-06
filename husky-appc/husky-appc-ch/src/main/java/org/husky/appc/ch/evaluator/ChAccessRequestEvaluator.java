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

package org.husky.appc.ch.evaluator;

import org.husky.appc.ch.models.AuthorizationDecision;
import org.husky.appc.ch.models.ChAccessRequester;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
public class ChAccessRequestEvaluator {

    /*
     * Authorization decision - The result of evaluating applicable policy
     * Context - The canonical representation of a decision request and an authorization decision
     * Effect - The intended consequence of a satisfied rule (either "Permit" or "Deny") Environment
     * Environment - The set of attributes that are relevant to an authorization decision and are
                     independent of a particular subject, resource or action
     */

    public AuthorizationDecision evaluate(final ChAccessRequester accessRequester) {
        return null;
    }
}
