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
import org.husky.appc.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public ChChildPolicySetGroup(final String id,
                                 @Nullable final String description,
                                 final List<@NonNull ChAccessLevelPolicy> policies,
                                 final String groupOid,
                                 final LocalDate validityEndDate) {
        super(id, description, policies);
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
    public int getSortScore() {
        return 3;
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
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2))));
        target.setEnvironments(new EnvironmentsType(new EnvironmentType(new EnvironmentMatchType(
                new AttributeValueType(this.validityEndDate),
                new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                AppcUrns.FUNCTION_DATE_GT_EQ
        ))));
        return target;
    }
}
