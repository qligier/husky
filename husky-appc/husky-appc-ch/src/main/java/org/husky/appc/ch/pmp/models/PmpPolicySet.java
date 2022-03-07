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
package org.husky.appc.ch.pmp.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.models.ChPolicyInterface;
import org.husky.appc.ch.models.ChPolicySetInterface;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The model of an abstract Swiss target.
 *
 * @author Quentin Ligier
 **/
public abstract class PmpPolicySet implements ChPolicySetInterface {

    /**
     * The policy set identifier.
     */
    protected final String id;

    /**
     * The description.
     */
    @Nullable
    protected final String description;

    /**
     * The inclusive start date after which the policy set is valid.
     */
    @Nullable
    protected final LocalDate validityStartDate;

    /**
     * The inclusive end date until which the policy set is valid.
     */
    @Nullable
    protected final LocalDate validityEndDate;

    /**
     * The referenced policy sets.
     */
    protected final List<@NonNull ChPolicySetInterface> referencedPolicySets;

    /**
     * Constructor.
     *
     * @param id                   The policy set identifier.
     * @param description          The description.
     * @param referencedPolicySets The referenced policy sets.
     * @param validityStartDate    The inclusive start date after which the policy set is valid.
     * @param validityEndDate      The inclusive end date until which the policy set is valid.
     */
    protected PmpPolicySet(final String id,
                           @Nullable final String description,
                           final List<@NonNull ChPolicySetInterface> referencedPolicySets,
                           @Nullable final LocalDate validityStartDate,
                           @Nullable final LocalDate validityEndDate) {
        this.id = Objects.requireNonNull(id);
        this.description = description;
        this.referencedPolicySets = Objects.requireNonNull(referencedPolicySets);
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
    }

    /**
     * Returns the policy set Id.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Returns the policy set description or {@code null} if it isn't set.
     */
    @Override
    public @Nullable String getDescription() {
        return this.description;
    }

    /**
     * Returns the inclusive start date after which the policy set is valid or {@code null} if the validity period has
     * no lower bound.
     */
    @Override
    public @Nullable LocalDate getValidityStartDate() {
        return this.validityStartDate;
    }

    /**
     * Returns the inclusive end date until which the policy set is valid or {@code null} if the validity period has no
     * upper bound.
     */
    @Override
    public @Nullable LocalDate getValidityEndDate() {
        return this.validityEndDate;
    }

    /**
     * Returns the list of referenced policies. It may be empty.
     */
    @Override
    public List<@NonNull ChPolicyInterface> getReferencedPolicies() {
        return Collections.emptyList();
    }

    /**
     * Returns the list of referenced policy sets. It may be empty.
     */
    @Override
    public List<@NonNull ChPolicySetInterface> getReferencedPolicySets() {
        return this.referencedPolicySets;
    }
}
