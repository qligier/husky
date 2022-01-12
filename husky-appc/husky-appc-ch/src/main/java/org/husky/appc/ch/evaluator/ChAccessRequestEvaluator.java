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

import org.husky.appc.algorithms.DenyOverridesAlgorithm;
import org.husky.appc.algorithms.FirstApplicableAlgorithm;
import org.husky.appc.algorithms.AuthorizationDecision;
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
import org.husky.appc.ch.models.*;
import org.husky.appc.enums.AuthorizationDecisionResult;
import org.husky.appc.enums.RuleEffect;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
public class ChAccessRequestEvaluator {

    /**
     *
     */
    private final FirstApplicableAlgorithm firstApplicableAlgorithm = new FirstApplicableAlgorithm();

    /**
     *
     */
    private final DenyOverridesAlgorithm denyOverridesAlgorithm = new DenyOverridesAlgorithm();

    /*
     * Authorization decision - The result of evaluating applicable policy
     * Context - The canonical representation of a decision request and an authorization decision
     * Effect - The intended consequence of a satisfied rule (either "Permit" or "Deny") Environment
     * Environment - The set of attributes that are relevant to an authorization decision and are
                     independent of a particular subject, resource or action
     */

    public AuthorizationDecision evaluate(final ChAccessRequest accessRequest,
                                          final ChPolicySet authorizationPolicies) {
        final List<AuthorizationDecision> decisions = authorizationPolicies.getPolicySets().stream()
                .map(policySet -> this.evaluatePolicySet(accessRequest, policySet))
                .toList();
        return this.firstApplicableAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluatePolicySet(final ChAccessRequest accessRequest,
                                   final ChChildPolicySet authorizationPolicy) {
        if (authorizationPolicy instanceof final ChChildPolicySetEmergency emergencyPolicy) {
            return this.evaluatePolicySet(accessRequest, emergencyPolicy);
        } else if (authorizationPolicy instanceof final ChChildPolicySetRepresentative representativePolicy) {

        } else if (authorizationPolicy instanceof final ChChildPolicySetHcp hcpPolicy) {

        } else if (authorizationPolicy instanceof final ChChildPolicySetGroup groupPolicy) {
            return this.evaluatePolicySet(accessRequest, groupPolicy);
        }
        throw new IllegalArgumentException("The authorization policy is unknown");
    }

    AuthorizationDecision evaluatePolicySet(final ChAccessRequest accessRequest,
                                            final ChChildPolicySetEmergency emergencyPolicy) {
        if (accessRequest.purposeOfUse() != PurposeOfUse.EMERGENCY_ACCESS) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        if (accessRequest.role() != Role.HEALTHCARE_PROFESSIONAL) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }

        return this.evaluatePolicies(accessRequest, emergencyPolicy.getPolicies());
    }

    AuthorizationDecision evaluatePolicySet(final ChAccessRequest accessRequest,
                                            final ChChildPolicySetGroup groupPolicy) {
        if (!accessRequest.groupGlns().contains(groupPolicy.getGroupOid())) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        final Instant validityEndInstant =
                groupPolicy.getValidityEndDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
        if (validityEndInstant.isAfter(accessRequest.environmentDate())) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        return this.evaluatePolicies(accessRequest, groupPolicy.getPolicies());
    }

    AuthorizationDecision evaluatePolicies(final ChAccessRequest accessRequest,
                                           final List<ChAccessLevelPolicy> policies) {
        final List<AuthorizationDecision> decisions = policies.stream()
                .map(policy -> this.evaluatePolicy(accessRequest, policy))
                .toList();
        return this.denyOverridesAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluatePolicy(final ChAccessRequest accessRequest,
                                         final ChAccessLevelPolicy policy) {
        if (!policy.getActions().contains(accessRequest.action())) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        if (policy.getConfidentialityCodes() != null) {
            if (accessRequest.confidentialityCode() == null) {
                return new AuthorizationDecision(AuthorizationDecisionResult.INDETERMINATE);
            }
            if (!policy.getConfidentialityCodes().contains(accessRequest.confidentialityCode())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        }
        return new AuthorizationDecision(
                (policy.getRuleEffect() == RuleEffect.PERMIT) ? AuthorizationDecisionResult.PERMIT : AuthorizationDecisionResult.DENY,
                policy.getRuleId()
        );
    }
}
