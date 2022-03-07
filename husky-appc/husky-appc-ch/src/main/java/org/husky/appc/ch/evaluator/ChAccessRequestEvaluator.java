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

import org.husky.appc.algorithms.AuthorizationDecision;
import org.husky.appc.algorithms.DenyOverridesAlgorithm;
import org.husky.appc.ch.models.ChAccessRequest;
import org.husky.appc.ch.models.ChPolicyInterface;
import org.husky.appc.ch.models.ChPolicySetDocument;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.appc.enums.AuthorizationDecisionResult;
import org.husky.appc.enums.RuleEffect;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;

/**
 * The request evaluator for Swiss access requests.
 *
 * @author Quentin Ligier
 **/
@ThreadSafe
public class ChAccessRequestEvaluator {

    /**
     * The implementation of the Deny-overrides policy-combining algorithm of a policy set.
     */
    private final DenyOverridesAlgorithm denyOverridesAlgorithm = new DenyOverridesAlgorithm();

    /**
     * Default constructor.
     */
    public ChAccessRequestEvaluator() {
        /*this.referencedPolicySets.addAll(List.of(
                PolicySetAccessLevelNormal.INSTANCE,
                PolicySetDocumentAdministration.INSTANCE,
                PolicySetPolicyAdministration.INSTANCE,
                PolicySetExclusionList.INSTANCE
        ));*/
    }

    /*
     * Authorization decision - The result of evaluating applicable policy
     * Context - The canonical representation of a decision request and an authorization decision
     * Effect - The intended consequence of a satisfied rule (either "Permit" or "Deny") Environment
     * Environment - The set of attributes that are relevant to an authorization decision and are
                     independent of a particular subject, resource or action
     */

    public AuthorizationDecision evaluate(final ChAccessRequest accessRequest,
                                          final ChPolicySetDocument policySetDocument) {
        final List<AuthorizationDecision> decisions = policySetDocument.getContainedPolicySets().stream()
                .map(policySet -> this.evaluatePolicySet(accessRequest, policySet))
                .toList();
        return this.denyOverridesAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluatePolicySet(final ChAccessRequest accessRequest,
                                            final ChPolicySetInterface policySet) {
        if (policySet.getValidityStartDate() != null) {
            final Instant validityStartInstant =
                    policySet.getValidityStartDate().atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant();
            if (validityStartInstant.isAfter(accessRequest.environmentDate())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        }
        if (policySet.getValidityEndDate() != null) {
            final Instant validityEndInstant =
                    policySet.getValidityEndDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
            if (validityEndInstant.isAfter(accessRequest.environmentDate())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        }
        if (policySet.getRole() != null && policySet.getRole() != accessRequest.role()) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        if (policySet.getPurposeOfUse() != null && policySet.getPurposeOfUse() != accessRequest.purposeOfUse()) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }
        if (policySet.getSubjectOrganizationId() != null && !accessRequest.organizationIds().contains(policySet.getSubjectOrganizationId())) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }

        final List<AuthorizationDecision> decisions = Stream.concat(
                policySet.getReferencedPolicySets().stream()
                        .map(policySet2 -> this.evaluatePolicySet(accessRequest, policySet2)),
                policySet.getReferencedPolicies().stream()
                        .map(policy -> this.evaluatePolicy(accessRequest, policy))
        ).toList();
        return this.denyOverridesAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluatePolicy(final ChAccessRequest accessRequest,
                                         final ChPolicyInterface policy) {
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
