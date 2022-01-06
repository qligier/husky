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
 * The access level policies defined by eHealth Suisse.
 *
 * @author Quentin Ligier
 **/
public enum ChAccessLevelPolicy {

    /**
     * Policy ID: read documents up to normal.
     */
    PERMIT_READING_NORMAL("urn:e-health-suisse:2015:policies:permit-reading-normal"),

    /**
     * Policy ID: read documents up to restricted.
     */
    PERMIT_READING_RESTRICTED("urn:e-health-suisse:2015:policies:permit-reading-restricted"),

    /**
     * Policy set ID: read documents up to secret.
     */
    PERMIT_READING_SECRET("urn:e-health-suisse:2015:policies:permit-reading-secret"),

    /**
     * Policy ID: register new documents up to normal.
     */
    PERMIT_WRITING_NORMAL("urn:e-health-suisse:2015:policies:permit-writing-normal"),

    /**
     * Policy ID: register new documents up to restricted.
     */
    PERMIT_WRITING_RESTRICTED("urn:e-health-suisse:2015:policies:permit-writing-restricted"),

    /**
     * Policy ID: register new documents up to secret.
     */
    PERMIT_WRITING_SECRET("urn:e-health-suisse:2015:policies:permit-writing-secret"),

    /**
     * Policy ID: full policy administration.
     */
    FULL_ADMINISTRATION("urn:e-health-suisse:2015:policies:full-policy-administration"),

    /**
     * Policy ID: deny all.
     */
    DENY_ALL("urn:e-health-suisse:2015:policies:deny-all"),

    /**
     * Policy ID: delegation up to normal.
     */
    DELEGATION_UPTO_NORMAL("urn:e-health-suisse:2015:policies:delegation-up-to-normal");

    /**
     * The policy set URN.
     */
    private final String urn;

    ChAccessLevelPolicy(final String urn) {
        this.urn = Objects.requireNonNull(urn);
    }

    /**
     * Returns the URN.
     */
    public String getUrn() {
        return this.urn;
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
}
