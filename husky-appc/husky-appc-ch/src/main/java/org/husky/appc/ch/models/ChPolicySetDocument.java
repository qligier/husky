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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The model of a Swiss APPC document.
 *
 * @author Quentin Ligier
 **/
public class ChPolicySetDocument {

    private final String id;

    private final String description;

    private final String patientEprSpid;

    private final List<@NonNull ChPolicySetInterface> policySets;

    public ChPolicySetDocument(final String id,
                               final String description,
                               final String patientEprSpid,
                               final List<@NonNull ChPolicySetInterface> policySets) {
        this.id = id;
        this.description = Objects.requireNonNull(description);
        this.patientEprSpid = patientEprSpid;
        this.policySets = new ArrayList<>(policySets);
    }

    public String getPatientEprSpid() {
        return patientEprSpid;
    }

    /**
     * Returns the policy set Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the policy set description or {@code null} if it isn't set.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the list of referenced policy sets. It may be empty.
     */
    public List<@NonNull ChPolicySetInterface> getContainedPolicySets() {
        return this.policySets;
    }
}
