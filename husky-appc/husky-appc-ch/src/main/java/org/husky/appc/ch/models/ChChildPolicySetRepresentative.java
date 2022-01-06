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
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
import org.husky.appc.models.*;

import java.util.List;
import java.util.Objects;

/**
 * The model of a representative target.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetRepresentative extends ChChildPolicySet {

    /**
     * The representative ID.
     */
    private String representativeId;

    public ChChildPolicySetRepresentative(final String id,
                                          @Nullable final String description,
                                          final List<@NonNull ChAccessLevelPolicy> policies,
                                          final String representativeId) {
        super(id, description, policies);
        this.representativeId = Objects.requireNonNull(representativeId);
    }

    public String getRepresentativeId() {
        return this.representativeId;
    }

    public void setRepresentativeId(final String representativeId) {
        this.representativeId = Objects.requireNonNull(representativeId);
    }

    /**
     * {@inheritDoc}
     */
    public int getSortScore() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType(this.representativeId),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(ChAppcUrns.REPRESENTATIVE_ID),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID_QUALIFIER, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch3 = new SubjectMatchType(
                new AttributeValueType(new CV("REP", "2.16.756.5.30.1.127.3.10.6")),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        return new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2, subjectMatch3))));
    }
}
