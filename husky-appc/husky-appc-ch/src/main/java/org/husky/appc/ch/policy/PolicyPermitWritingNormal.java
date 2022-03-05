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
 * The referenced policy 'permit writing normal'.
 *
 * @author Quentin Ligier
 **/
public class PolicyPermitWritingNormal extends ReferencedPolicy {
    public static final PolicyPermitWritingNormal INSTANCE = new PolicyPermitWritingNormal();

    /**
     * Returns the policy Id.
     */
    @Override
    public String getId() {
        return "urn:e-health-suisse:2015:policies:permit-writing-normal";
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
        return List.of(ConfidentialityCode.NORMALLY_ACCESSIBLE);
    }

    /**
     * Returns the rule Id.
     */
    public String getRuleId() {
        return "77503c36-c927-400f-b31b-41b95a90d41c";
    }

    /**
     * Returns the rule effect.
     */
    @Override
    public RuleEffect getRuleEffect() {
        return RuleEffect.PERMIT;
    }
}
