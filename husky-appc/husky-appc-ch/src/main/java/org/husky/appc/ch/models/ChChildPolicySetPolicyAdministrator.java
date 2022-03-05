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
package org.husky.appc.ch.models;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.models.TargetType;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * The model of a policy administrator target.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetPolicyAdministrator extends ChChildPolicySet {

    /**
     * The administrator Id.
     */
    private String administratorId;

    /**
     * Simple constructor. A random Id is assigned.
     *
     * @param policySetId     The identifier of the referenced policy set.
     * @param administratorId The administrator Id.
     */
    public ChChildPolicySetPolicyAdministrator(final String policySetId,
                                               final String administratorId) {
        this(UUID.randomUUID().toString(), null, policySetId, null, null, administratorId);
    }

    /**
     * Full constructor.
     *
     * @param id                The policy set identifier.
     * @param description       The description.
     * @param policySetId       The identifier of the referenced policy set.
     * @param validityStartDate The inclusive start date after which the policy set is valid.
     * @param validityEndDate   The inclusive end date until which the policy set is valid.
     * @param administratorId   The administrator Id.
     */
    public ChChildPolicySetPolicyAdministrator(final String id,
                                               @Nullable final String description,
                                               final String policySetId,
                                               @Nullable final LocalDate validityStartDate,
                                               @Nullable final LocalDate validityEndDate,
                                               final String administratorId) {
        super(id, description, policySetId, validityStartDate, validityEndDate);
        this.administratorId = Objects.requireNonNull(administratorId);
    }

    /**
     * Returns the targeted role.
     */
    public Role getRole() {
        return Role.POLICY_ADMINISTRATOR;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(final String administratorId) {
        this.administratorId = Objects.requireNonNull(administratorId);
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        return new TargetType();
    }
}
