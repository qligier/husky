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

import java.util.Arrays;
import java.util.Objects;

/**
 * The access level policy sets defined by eHealth Suisse.
 *
 * @author Quentin Ligier
 */
public enum ChAccessLevelPolicySet {

    /**
     * Policy set ID: .
     */
    ACCESS_LEVEL_NORMAL("urn:e-health-suisse:2015:policies:access-level:normal"),

    /**
     * Policy set ID: .
     */
    ACCESS_LEVEL_RESTRICTED("urn:e-health-suisse:2015:policies:access-level:restricted"),

    /**
     * Policy set ID: .
     */
    ACCESS_LEVEL_DELEGATION_RESTRICTED("urn:e-health-suisse:2015:policies:access-level:delegation-and-restricted"),

    /**
     * Policy set ID: .
     */
    ACCESS_LEVEL_DELEGATION_NORMAL("urn:e-health-suisse:2015:policies:access-level:delegation-and-normal"),

    /**
     * Policy set ID: .
     */
    ACCESS_LEVEL_FULL("urn:e-health-suisse:2015:policies:access-level:full"),

    /**
     * Policy set ID: .
     */
    PROVIDE_LEVEL_NORMAL("urn:e-health-suisse:2015:policies:provide-level:normal"),

    /**
     * Policy set ID: .
     */
    PROVIDE_LEVEL_RESTRICTED("urn:e-health-suisse:2015:policies:provide-level:restricted"),

    /**
     * Policy set ID: .
     */
    PROVIDE_LEVEL_SECRET("urn:e-health-suisse:2015:policies:provide-level:secret"),

    /**
     * Policy set ID: .
     */
    EXCLUSION_LIST("urn:e-health-suisse:2015:policies:exclusion-list");

    /**
     * The policy set URN.
     */
    private final String urn;

    ChAccessLevelPolicySet(final String urn) {
        this.urn = Objects.requireNonNull(urn);
    }

    /**
     * Searches if a URN is present in the enum.
     *
     * @param urn The URN to find in the enum.
     * @return {@code true} if the URN is found in the enum, {@code false} otherwise.
     */
    public boolean urnInEnum(@Nullable final String urn) {
        return Arrays.stream(values()).anyMatch(policySet -> policySet.getUrn().equals(urn));
    }

    /**
     * Returns an enum instance that corresponds to a URN.
     *
     * @param urn The URN to find in the enum.
     * @return the {@link ChAccessLevelPolicySet} with the same URN.
     * @throws IllegalArgumentException if the URN is not found in the enum.
     */
    public ChAccessLevelPolicySet getByUrn(@Nullable final String urn) {
        return Arrays.stream(values())
                .filter(policySet -> policySet.getUrn().equals(urn))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The specified URN is not found in the enum " +
                        "ChAccessLevelPolicySet"));
    }

    /**
     * Returns the URN.
     */
    public String getUrn() {
        return this.urn;
    }
}
