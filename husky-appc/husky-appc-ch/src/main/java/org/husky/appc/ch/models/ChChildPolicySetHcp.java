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
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.models.*;
import org.husky.common.utils.datatypes.Gln;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The model of a policy set targeting a healthcare professional.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetHcp extends ChChildPolicySet {

    /**
     * The healthcare professional GLN number.
     */
    private String hcpGln;

    public ChChildPolicySetHcp(final String id,
                               @Nullable final String description,
                               final Set<@NonNull ChAccessLevelPolicy> policies,
                               final Set<@NonNull ChAction> actions,
                               final String hcpGln) {
        super(id, description, policies, actions);
        if (!Gln.match(Objects.requireNonNull(hcpGln))) {
            throw new IllegalArgumentException("The healthcare professional GLN is invalid");
        }
        this.hcpGln = hcpGln;
    }

    public String getHcpGln() {
        return this.hcpGln;
    }

    public void setHcpGln(final String hcpGln) {
        if (!Gln.match(Objects.requireNonNull(hcpGln))) {
            throw new IllegalArgumentException("The healthcare professional GLN is invalid");
        }
        this.hcpGln = hcpGln;
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType(this.hcpGln),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(ChAppcUrns.GLN),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID_QUALIFIER, AppcUrns.XS_STRING),
                AppcUrns.FUNCTION_STRING_EQUAL
        );
        final var subjectMatch3 = new SubjectMatchType(
                new AttributeValueType(new CV("HCP", "2.16.756.5.30.1.127.3.10.6")),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2,
                subjectMatch3))));
        target.setActions(this.createPolicySetActions());
        return target;
    }
}
