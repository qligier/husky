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
package org.husky.appc.ch.policy;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.enums.RuleEffect;
import org.husky.common.ch.enums.ConfidentialityCode;

import java.util.List;

/**
 * The referenced policy 'permit writing restricted'.
 *
 * @author Quentin Ligier
 **/
public class PolicyPermitWritingRestricted extends ReferencedPolicy {
    public static final PolicyPermitWritingRestricted INSTANCE = new PolicyPermitWritingRestricted();

    /**
     * Returns the policy Id.
     */
    @Override
    public String getId() {
        return "urn:e-health-suisse:2015:policies:permit-writing-restricted";
    }

    /**
     * Returns the list of targeted actions. It shall not be an empty list.
     */
    @Override
    public List<@NonNull ChAction> getActions() {
        return List.of(
                ChAction.PROVIDE_AND_REGISTER_DOCUMENT_SET,
                ChAction.REGISTER_DOCUMENT_SET
        );
    }

    /**
     * Returns the list of targeted confidentiality codes or {@code null} if it's not relevant. It shall not be an
     * empty list.
     */
    @Override
    public @Nullable List<@NonNull ConfidentialityCode> getConfidentialityCodes() {
        return List.of(
                ConfidentialityCode.NORMALLY_ACCESSIBLE,
                ConfidentialityCode.RESTRICTED_ACCESSIBLE
        );
    }

    /**
     * Returns the rule Id.
     */
    public String getRuleId() {
        return "14f68bbd-7210-4edd-9188-de41b99b28a4";
    }

    /**
     * Returns the rule effect.
     */
    @Override
    public RuleEffect getRuleEffect() {
        return RuleEffect.PERMIT;
    }
}
