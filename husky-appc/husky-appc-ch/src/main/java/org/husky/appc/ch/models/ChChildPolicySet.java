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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.models.*;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
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
     * The identifier of the referenced policy set.
     */
    protected String referencedPolicySetId;

    /**
     * The inclusive start date after which the policy set is valid.
     */
    @Nullable
    protected LocalDate validityStartDate;

    /**
     * The inclusive end date until which the policy set is valid.
     */
    @Nullable
    protected LocalDate validityEndDate;

    /**
     * Constructor.
     *
     * @param id                The policy set identifier.
     * @param description       The description.
     * @param policySetId       The identifier of the referenced policy set.
     * @param validityStartDate The inclusive start date after which the policy set is valid.
     * @param validityEndDate   The inclusive end date until which the policy set is valid.
     */
    protected ChChildPolicySet(final String id,
                               @Nullable final String description,
                               final String policySetId,
                               @Nullable final LocalDate validityStartDate,
                               @Nullable final LocalDate validityEndDate) {
        this.id = Objects.requireNonNull(id);
        this.description = description;
        this.referencedPolicySetId = Objects.requireNonNull(policySetId);
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
    }

    /**
     * Returns the targeted role.
     */
    public abstract Role getRole();

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
        policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(appcFactory.createPolicyIdReference(new IdReferenceType(this.referencedPolicySetId)));

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

    public String getReferencedPolicySetId() {
        return referencedPolicySetId;
    }

    public void setReferencedPolicySetId(final String referencedPolicySetId) {
        this.referencedPolicySetId = Objects.requireNonNull(referencedPolicySetId);
    }

    @Nullable
    public LocalDate getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(@Nullable final LocalDate validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    @Nullable
    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(@Nullable final LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    /**
     * Creates the policy set target.
     *
     * @return the created {@link TargetType}.
     */
    protected abstract TargetType createPolicySetTarget();

    /**
     * Creates the policy set target Environments element.
     *
     * @return the created {@link EnvironmentsType} or {@code null} if it's empty.
     */
    @Nullable
    protected EnvironmentsType createPolicySetEnvironments() {
        if (this.validityStartDate == null && this.validityEndDate == null) {
            return null;
        }
        final List<EnvironmentMatchType> environmentMatches = new ArrayList<>();
        if (this.validityStartDate != null) {
            environmentMatches.add(new EnvironmentMatchType(
                    new AttributeValueType(this.validityStartDate),
                    new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                    AppcUrns.FUNCTION_DATE_GT_EQ
            ));
        }
        if (this.validityEndDate != null) {
            environmentMatches.add(new EnvironmentMatchType(
                    new AttributeValueType(this.validityEndDate),
                    new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                    AppcUrns.FUNCTION_DATE_LT_EQ
            ));
        }
        return new EnvironmentsType(new EnvironmentType(environmentMatches));
    }
}
