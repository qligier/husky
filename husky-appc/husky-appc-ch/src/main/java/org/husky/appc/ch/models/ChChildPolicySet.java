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
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.models.*;
import org.husky.common.ch.enums.ConfidentialityCode;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * The model of an abstract Swiss target.
 *
 * @author Quentin Ligier
 **/
public abstract class ChChildPolicySet {

    /**
     * The set of contained policies (conjunctive sequence).
     */
    protected final Set<@NonNull ChAccessLevelPolicy> policies;
    
    /**
     * The set of actions (disjunctive sequence).
     */
    protected final Set<@NonNull ChAction> actions;
    
    /**
     * The confidentiality code of the documents if applicable or {@code null}. It represents the stricter level of 
     * confidentiality (i.e. {@code secret} also implies {@code restricted} and {@code normal}).
     */
    @Nullable
    protected final ConfidentialityCode confidentialityCode;
    
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
     * Constructor.
     *
     * @param description         The description.
     * @param policies            The set of contained policies.
     * @param actions             The set of action (disjunctive sequence).
     * @param confidentialityCode The confidentiality code of the documents if applicable or {@code null}.
     */
    protected ChChildPolicySet(final String id,
                               @Nullable final String description,
                               final Set<@NonNull ChAccessLevelPolicy> policies,
                               final Set<@NonNull ChAction> actions,
                               @Nullable final ConfidentialityCode confidentialityCode) {
        this.id = Objects.requireNonNull(id);
        this.description = description;
        this.policies = EnumSet.copyOf(Objects.requireNonNull(policies));
        this.actions = EnumSet.copyOf(Objects.requireNonNull(actions));
        this.confidentialityCode = confidentialityCode;
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

    public Set<@NonNull ChAccessLevelPolicy> getPolicies() {
        return policies;
    }

    public Set<@NonNull ChAction> getActions() {
        return actions;
    }

    @Nullable
    public ConfidentialityCode getConfidentialityCode() {
        return confidentialityCode;
    }

    /**
     * Creates the policy set target.
     *
     * @return the created {@link TargetType}.
     */
    protected abstract TargetType createPolicySetTarget();

    /**
     * Creates the policy set actions as a disjunctive sequence.
     *
     * @return the created {@link ActionsType}.
     */
    protected ActionsType createPolicySetActions() {
        final var policySetActions = new ActionsType();
        for (final var chAction : this.actions) {
            policySetActions.getAction().add(new ActionType(new ActionMatchType(
                    new AttributeValueType(chAction.getUrn(), AppcUrns.XS_ANY_URI),
                    new AttributeDesignatorType(AppcUrns.OASIS_ACTION_ID, AppcUrns.XS_ANY_URI),
                    AppcUrns.FUNCTION_ANY_URI_EQUAL
            )));
        }
        return policySetActions;
    }

    /**
     * Creates the policy set resources.
     *
     * @return the created {@link ResourcesType} or {@code null}.
     */
    @Nullable
    protected ResourcesType createPolicySetResources() {
        if (this.confidentialityCode == null) {
            return null;
        }
        return new ResourcesType(new ResourceType(new ResourceMatchType(
                new AttributeValueType(new CV(this.confidentialityCode)),
                new AttributeDesignatorType(AppcUrns.IHE_CONFIDENTIALITY_CODE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        )));
    }
}
