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
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.models.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.husky.appc.ch.enums.ChAccessLevelPolicy.PERMIT_READING_NORMAL;
import static org.husky.appc.ch.enums.ChAction.*;

/**
 * The model of a policy set targeting healthcare professionals accessing the patient record in an emergency purpose.
 *
 * @author Quentin Ligier
 **/
public class ChChildPolicySetEmergency extends ChChildPolicySet {

    /**
     * The list of policies that applies to this model.
     */
    public static final Set<ChAccessLevelPolicy> POLICIES = EnumSet.of(PERMIT_READING_NORMAL);

    /**
     * The list of actions that applies to this model.
     */
    public static final Set<ChAction> ACTIONS = EnumSet.of(REGISTRY_STORED_QUERY, RETRIEVE_DOCUMENT_SET,
            CROSS_GATEWAY_QUERY, CROSS_GATEWAY_RETRIEVE, RETRIEVE_IMAGING_DOCUMENT_SET,
            CROSS_GATEWAY_RETRIEVE_IMAGING_DOCUMENT_SET);

    /**
     * Simple constructor. A random Id is assigned.
     */
    public ChChildPolicySetEmergency() {
        this(UUID.randomUUID().toString(), null);
    }

    /**
     * Full constructor.
     *
     * @param id          The policy set identifier.
     * @param description The description.
     */
    public ChChildPolicySetEmergency(final String id,
                               @Nullable final String description) {
        super(id, description, POLICIES, ACTIONS, null);
        // TODO: confidentiality codes
    }

    /**
     * {@inheritDoc}
     */
    protected TargetType createPolicySetTarget() {
        final var subjectMatch1 = new SubjectMatchType(
                new AttributeValueType(new CV("HCP", "2.16.756.5.30.1.127.3.10.6")),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        final var subjectMatch2 = new SubjectMatchType(
                new AttributeValueType(new CV("EMER", "2.16.756.5.30.1.127.3.10.5")),
                new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_PURPOSE_USE, AppcUrns.CV),
                AppcUrns.FUNCTION_CV_EQUAL
        );
        // Conjunctive sequence of subject matches
        final var target = new TargetType(new SubjectsType(new SubjectType(List.of(subjectMatch1, subjectMatch2))));
        target.setActions(this.createPolicySetActions());
        return target;
    }

    @Override
    public String toString() {
        return "ChChildPolicySetEmergency{" +
                "id='" + this.id + '\'' +
                ", description='" + this.description + '\'' +
                ", policies=" + this.policies +
                ", actions=" + this.actions +
                '}';
    }
}
