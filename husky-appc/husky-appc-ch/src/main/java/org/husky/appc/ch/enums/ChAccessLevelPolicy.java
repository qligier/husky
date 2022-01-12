/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.ch.enums;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.enums.RuleEffect;
import org.husky.common.ch.enums.ConfidentialityCode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.husky.appc.ch.enums.ChAction.*;
import static org.husky.common.ch.enums.ConfidentialityCode.*;

/**
 * The access level policies defined by eHealth Suisse.
 *
 * @author Quentin Ligier
 **/
public enum ChAccessLevelPolicy {

    /**
     * Policy ID: read documents up to normal.
     */
    PERMIT_READING_NORMAL(
            "urn:e-health-suisse:2015:policies:permit-reading-normal",
            "6791e6fd-4acb-4db9-94b3-6c059b70c64d",
            RuleEffect.PERMIT,
            List.of(REGISTRY_STORED_QUERY, RETRIEVE_DOCUMENT_SET, CROSS_GATEWAY_QUERY, CROSS_GATEWAY_RETRIEVE,
                    RETRIEVE_IMAGING_DOCUMENT_SET, CROSS_GATEWAY_RETRIEVE_IMAGING_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE)
    ),

    /**
     * Policy ID: read documents up to restricted.
     */
    PERMIT_READING_RESTRICTED(
            "urn:e-health-suisse:2015:policies:permit-reading-restricted",
            "afe600e0-5078-44b7-8a58-de84acf914a7",
            RuleEffect.PERMIT,
            List.of(REGISTRY_STORED_QUERY, RETRIEVE_DOCUMENT_SET, CROSS_GATEWAY_QUERY, CROSS_GATEWAY_RETRIEVE,
                    RETRIEVE_IMAGING_DOCUMENT_SET, CROSS_GATEWAY_RETRIEVE_IMAGING_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE, RESTRICTED_ACCESSIBLE)
    ),

    /**
     * Policy set ID: read documents up to secret.
     */
    PERMIT_READING_SECRET(
            "urn:e-health-suisse:2015:policies:permit-reading-secret",
            "b5271b5b-1f82-4162-872a-6687f3d1d0e6",
            RuleEffect.PERMIT,
            List.of(REGISTRY_STORED_QUERY, RETRIEVE_DOCUMENT_SET, CROSS_GATEWAY_QUERY, CROSS_GATEWAY_RETRIEVE,
                    RETRIEVE_IMAGING_DOCUMENT_SET, CROSS_GATEWAY_RETRIEVE_IMAGING_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE, RESTRICTED_ACCESSIBLE, SECRET)
    ),

    /**
     * Policy ID: register new documents up to normal.
     */
    PERMIT_WRITING_NORMAL(
            "urn:e-health-suisse:2015:policies:permit-writing-normal",
            "77503c36-c927-400f-b31b-41b95a90d41c",
            RuleEffect.PERMIT,
            List.of(REGISTER_DOCUMENT_SET, PROVIDE_AND_REGISTER_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE)
    ),

    /**
     * Policy ID: register new documents up to restricted.
     */
    PERMIT_WRITING_RESTRICTED(
            "urn:e-health-suisse:2015:policies:permit-writing-restricted",
            "14f68bbd-7210-4edd-9188-de41b99b28a4",
            RuleEffect.PERMIT,
            List.of(REGISTER_DOCUMENT_SET, PROVIDE_AND_REGISTER_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE, RESTRICTED_ACCESSIBLE)
    ),

    /**
     * Policy ID: register new documents up to secret.
     */
    PERMIT_WRITING_SECRET(
            "urn:e-health-suisse:2015:policies:permit-writing-secret",
            "3438992c-fb84-46fe-9775-20cd3a24aad9",
            RuleEffect.PERMIT,
            List.of(REGISTER_DOCUMENT_SET, PROVIDE_AND_REGISTER_DOCUMENT_SET),
            List.of(NORMALLY_ACCESSIBLE, RESTRICTED_ACCESSIBLE, SECRET)
    ),

    /**
     * Policy ID: full policy administration.
     */
    FULL_ADMINISTRATION(
            "urn:e-health-suisse:2015:policies:full-policy-administration",
            "d4c9267b-1927-4bd3-acc0-c05c3ae1c02d",
            RuleEffect.PERMIT,
            List.of(POLICY_QUERY, ADD_POLICY, UPDATE_POLICY, DELETE_POLICY),
            null
    ),

    /**
     * Policy ID: deny all.
     */
    DENY_ALL(
            "urn:e-health-suisse:2015:policies:deny-all",
            "9a522e42-d0cc-47bd-a4c8-d1d0828d6bf8",
            RuleEffect.DENY,
            List.of(ChAction.values()),
            null
    )
    /*
     * TODO: missing last values. 1 more value in the Ergänzung 2.1 zu Anhang 5 der EPDV-EDI, 3 other values in
     *  https://github.com/ehealthsuisse/CH-EPR-ADR-PPQ
     */;

    /**
     * The policy URN.
     */
    private final String urn;

    /**
     * The rule Id.
     */
    private final String ruleId;

    /**
     * The rule effect.
     */
    private final RuleEffect ruleEffect;

    /**
     * The targeted actions.
     */
    private final List<ChAction> actions;

    /**
     * The targeted confidentiality codes if applicable or {@code null}.
     */
    @Nullable
    private final List<ConfidentialityCode> confidentialityCodes;

    /**
     * Constructor.
     *
     * @param urn                  The policy URN.
     * @param ruleId               The rule Id.
     * @param ruleEffect           The rule effect.
     * @param actions              The targeted actions.
     * @param confidentialityCodes The targeted confidentiality codes if applicable or {@code null}.
     */
    ChAccessLevelPolicy(final String urn,
                        final String ruleId,
                        final RuleEffect ruleEffect,
                        final List<ChAction> actions,
                        @Nullable final List<ConfidentialityCode> confidentialityCodes) {
        this.urn = Objects.requireNonNull(urn);
        this.ruleId = Objects.requireNonNull(ruleId);
        this.ruleEffect = Objects.requireNonNull(ruleEffect);
        this.actions = Objects.requireNonNull(actions);
        this.confidentialityCodes = confidentialityCodes;
    }

    /**
     * Searches if a URN is present in the enum.
     *
     * @param urn The URN to find in the enum.
     * @return {@code true} if the URN is found in the enum, {@code false} otherwise.
     */
    public static boolean urnInEnum(@Nullable final String urn) {
        return Arrays.stream(values()).anyMatch(policy -> policy.getUrn().equals(urn));
    }

    /**
     * Returns an enum instance that corresponds to a URN.
     *
     * @param urn The URN to find in the enum.
     * @return the {@link ChAccessLevelPolicySet} with the same URN.
     * @throws IllegalArgumentException if the URN is not found in the enum.
     */
    public static ChAccessLevelPolicy getByUrn(@Nullable final String urn) {
        return Arrays.stream(values())
                .filter(policy -> policy.getUrn().equals(urn))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The specified URN is not found in the enum " +
                        "ChAccessLevelPolicy"));
    }

    /**
     * Returns the URN.
     */
    public String getUrn() {
        return this.urn;
    }

    public String getRuleId() {
        return ruleId;
    }

    public RuleEffect getRuleEffect() {
        return ruleEffect;
    }

    public List<ChAction> getActions() {
        return actions;
    }

    @Nullable
    public List<ConfidentialityCode> getConfidentialityCodes() {
        return confidentialityCodes;
    }
}
