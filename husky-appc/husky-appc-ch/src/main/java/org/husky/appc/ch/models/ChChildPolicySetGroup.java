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
import org.husky.appc.AppcUrns;
import org.husky.appc.models.*;
import org.husky.communication.ch.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The model of a group target.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetGroup extends ChChildPolicySet {

    /**
     * The group OID number.
     */
    private String groupOid;

    /**
     * Simple constructor. A random Id is assigned.
     *
     * @param policySetId     The identifier of the referenced policy set.
     * @param groupOid        The group OID number.
     * @param validityEndDate The inclusive end date until which the group is selected.
     */
    public ChChildPolicySetGroup(final String policySetId,
                                 final String groupOid,
                                 final LocalDate validityEndDate) {
        this(UUID.randomUUID().toString(), null, policySetId, null, validityEndDate, groupOid);
    }

    /**
     * Full constructor.
     *
     * @param id              The policy set identifier.
     * @param description     The description.
     * @param policySetId     The identifier of the referenced policy set.
     * @param validityStartDate The inclusive start date after which the policy set is valid.
     * @param validityEndDate   The inclusive end date until which the policy set is valid.
     * @param groupOid        The group OID number.
     */
    public ChChildPolicySetGroup(final String id,
                                 @Nullable final String description,
                                 final String policySetId,
                                 @Nullable final LocalDate validityStartDate,
                                 @Nullable final LocalDate validityEndDate,
                                 final String groupOid) {
        super(id, description, policySetId, validityStartDate, validityEndDate);
        this.setGroupOid(groupOid);
        Objects.requireNonNull(validityEndDate, "The validity end date is required");
    }

    /**
     * Returns the targeted role.
     */
    public Role getRole() {
        return Role.HEALTHCARE_PROFESSIONAL;
    }

    public String getGroupOid() {
        return this.groupOid;
    }

    public void setGroupOid(final String groupOid) {
        Objects.requireNonNull(groupOid);
        this.groupOid = groupOid.startsWith("urn:oid:") ? groupOid.substring(8) : groupOid;
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType("urn:oid:" + this.groupOid, AppcUrns.XS_ANY_URI),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ORG_ID, AppcUrns.XS_ANY_URI),
                AppcUrns.FUNCTION_ANY_URI_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(new CV(Role.HEALTHCARE_PROFESSIONAL)),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        // Conjunctive sequence of subject matches
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2))));
        target.setEnvironments(this.createPolicySetEnvironments());
        return target;
    }
}
