/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.ch.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
import org.husky.appc.models.IdReferenceType;
import org.husky.appc.models.ObjectFactory;
import org.husky.appc.models.PolicySetType;
import org.husky.appc.models.TargetType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The model of an abstract Swiss target.
 *
 * @author Quentin Ligier
 **/
public abstract class ChChildPolicySet {

    /**
     * The policy set identifier.
     */
    protected String id;

    /**
     * The description.
     */
    @Nullable
    protected String description;

    /**
     * The list of contained policies.
     */
    protected final List<@NonNull ChAccessLevelPolicy> policies;

    protected ChChildPolicySet(final String id,
                               @Nullable final String description,
                               final List<@NonNull ChAccessLevelPolicy> policies) {
        this.id = Objects.requireNonNull(id);
        this.description = description;
        this.policies = new ArrayList<>(Objects.requireNonNull(policies));
    }

    /**
     * Creates the policy set model.
     *
     * @return the created {@link PolicySetType}.
     */
    public PolicySetType createPolicySet() {
        final var policySet = new PolicySetType();
        policySet.setPolicySetId(this.id);
        policySet.setPolicyCombiningAlgId(AppcUrns.OASIS_ALGO_DENY_OVERRIDES);
        policySet.setDescription(this.description);
        policySet.setTarget(this.createPolicySetTarget());

        final var appcFactory = new ObjectFactory();
        this.policies.stream()
                .map(ChAccessLevelPolicy::getUrn)
                .map(IdReferenceType::new)
                .map(appcFactory::createPolicyIdReference)
                .forEach(jaxb -> policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(jaxb));

        return policySet;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = Objects.requireNonNull(id);
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    public List<@NonNull ChAccessLevelPolicy> getPolicies() {
        return policies;
    }

    /**
     * Returns a score that is used to sort the different kind of policy sets in a Swiss APPC document.
     *
     * <p>It should go as follows:
     * <ol>
     *     <li>Access rights for emergency access</li>
     *     <li>Access rights for representatives</li>
     *     <li>Access rights for healthcare professionals</li>
     *     <li>Access rights for groups</li>
     * </ol>
     */
    public abstract int getSortScore();

    /**
     * Creates the policy set target.
     *
     * @return the created {@link TargetType}.
     */
    protected abstract TargetType createPolicySetTarget();
}
