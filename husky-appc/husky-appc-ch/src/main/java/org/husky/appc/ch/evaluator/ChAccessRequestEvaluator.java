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
import org.husky.appc.ch.models.*;
import org.husky.appc.ch.policy.ReferencedPolicy;
import org.husky.appc.ch.policyset.*;
import org.husky.appc.enums.AuthorizationDecisionResult;
import org.husky.appc.enums.RuleEffect;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
     * The list of registered policy sets.
     */
    private final List<ReferencedPolicySet> referencedPolicySets = new ArrayList<>();

    /**
     * Default constructor.
     */
    public ChAccessRequestEvaluator() {
        this.referencedPolicySets.addAll(List.of(
                PolicySetAccessLevelNormal.INSTANCE,
                PolicySetDocumentAdministration.INSTANCE,
                PolicySetPolicyAdministration.INSTANCE,
                PolicySetExclusionList.INSTANCE
        ));
    }

    /**
     * Custom constructor.
     *
     * @param referencedPolicySets The list of registered policy sets.
     */
    public ChAccessRequestEvaluator(final List<ReferencedPolicySet> referencedPolicySets) {
        this.referencedPolicySets.addAll(referencedPolicySets);
    }

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
        return this.denyOverridesAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluatePolicySet(final ChAccessRequest accessRequest,
                                            final ChChildPolicySet authorizationPolicy) {
        if (authorizationPolicy.getValidityStartDate() != null) {
            final Instant validityStartInstant =
                    authorizationPolicy.getValidityStartDate().atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant();
            if (validityStartInstant.isAfter(accessRequest.environmentDate())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        }
        if (authorizationPolicy.getValidityEndDate() != null) {
            final Instant validityEndInstant =
                    authorizationPolicy.getValidityEndDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
            if (validityEndInstant.isAfter(accessRequest.environmentDate())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        }

        if (authorizationPolicy instanceof final ChChildPolicySetEmergency emergencyPolicy) {
            if (accessRequest.purposeOfUse() != PurposeOfUse.EMERGENCY_ACCESS) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
            if (accessRequest.role() != Role.HEALTHCARE_PROFESSIONAL) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        } else if (authorizationPolicy instanceof final ChChildPolicySetRepresentative representativePolicy) {
            if (accessRequest.role() != Role.REPRESENTATIVE) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        } else if (authorizationPolicy instanceof final ChChildPolicySetHealthcareProfessional hcpPolicy) {
            if (accessRequest.role() != Role.HEALTHCARE_PROFESSIONAL) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        } else if (authorizationPolicy instanceof final ChChildPolicySetGroup groupPolicy) {
            if (!accessRequest.groupGlns().contains(groupPolicy.getGroupOid())) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
            if (accessRequest.role() != Role.HEALTHCARE_PROFESSIONAL) {
                return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
            }
        } else {
            throw new IllegalArgumentException("The authorization policy is unknown");
        }
        return this.evaluateReferencedPolicySet(accessRequest, authorizationPolicy.getReferencedPolicySetId());
    }

    AuthorizationDecision evaluateReferencedPolicySet(final ChAccessRequest accessRequest,
                                                      final String policySetId) {
        final var policySet = this.referencedPolicySets.stream()
                .filter(rps -> policySetId.equals(rps.getId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("The PolicySet id '%s' is unknown",
                        policySetId)));

        final var roles = policySet.getRoles();
        if (roles != null && !roles.contains(accessRequest.role())) {
            return new AuthorizationDecision(AuthorizationDecisionResult.NOT_APPLICABLE);
        }

        final List<AuthorizationDecision> decisions = policySet.getPolicies().stream()
                .map(policy -> this.evaluateReferencedPolicy(accessRequest, policy))
                .toList();
        return this.denyOverridesAlgorithm.combinePolicies(decisions);
    }

    AuthorizationDecision evaluateReferencedPolicy(final ChAccessRequest accessRequest,
                                                   final ReferencedPolicy policy) {
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
