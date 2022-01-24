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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
     * The inclusive end date until which the group is selected.
     */
    private LocalDate validityEndDate;

    /**
     * Simple constructor. A random Id is assigned.
     *
     * @param policies        The set of contained policies.
     * @param actions         The set of action.
     * @param groupOid        The group OID number.
     * @param validityEndDate The inclusive end date until which the group is selected.
     */
    public ChChildPolicySetGroup(final Set<@NonNull ChAccessLevelPolicy> policies,
                                 final Set<@NonNull ChAction> actions,
                                 final String groupOid,
                                 final LocalDate validityEndDate) {
        this(UUID.randomUUID().toString(), null, policies, actions, groupOid, validityEndDate);
    }

    /**
     * Full constructor.
     *
     * @param id              The policy set identifier.
     * @param description     The description.
     * @param policies        The set of contained policies.
     * @param actions         The set of action.
     * @param groupOid        The group OID number.
     * @param validityEndDate The inclusive end date until which the group is selected.
     */
    public ChChildPolicySetGroup(final String id,
                                 @Nullable final String description,
                                 final Set<@NonNull ChAccessLevelPolicy> policies,
                                 final Set<@NonNull ChAction> actions,
                                 final String groupOid,
                                 final LocalDate validityEndDate) {
        super(id, description, policies, actions);
        this.setGroupOid(groupOid);
        this.validityEndDate = Objects.requireNonNull(validityEndDate);
    }

    public String getGroupOid() {
        return this.groupOid;
    }

    public void setGroupOid(final String groupOid) {
        Objects.requireNonNull(groupOid);
        this.groupOid = groupOid.startsWith("urn:oid:") ? groupOid.substring(8) : groupOid;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(final LocalDate validityEndDate) {
        this.validityEndDate = Objects.requireNonNull(validityEndDate);
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
                new AttributeValueType(new CV("HCP", "2.16.756.5.30.1.127.3.10.6")),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        // Conjunctive sequence of subject matches
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2))));
        target.setEnvironments(new EnvironmentsType(new EnvironmentType(new EnvironmentMatchType(
                new AttributeValueType(this.validityEndDate),
                new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                AppcUrns.FUNCTION_DATE_GT_EQ
        ))));
        target.setActions(this.createPolicySetActions());
        return target;
    }

    @Override
    public String toString() {
        return "ChChildPolicySetGroup{" +
                "id='" + this.id + '\'' +
                ", description='" + this.description + '\'' +
                ", policies=" + this.policies +
                ", actions=" + this.actions +
                ", groupOid='" + this.groupOid + '\'' +
                ", validityEndDate=" + this.validityEndDate +
                '}';
    }
}
