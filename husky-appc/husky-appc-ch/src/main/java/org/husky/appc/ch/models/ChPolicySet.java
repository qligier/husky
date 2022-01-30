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

import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The simplified model of a Swiss APPC document.
 *
 * @author Quentin Ligier
 **/
@Data
public class ChPolicySet {

    private String policySetId;

    private String description;

    private String patientEprSpid;

    private List<@NonNull ChChildPolicySet> policySets;

    public ChPolicySet(final String policySetId, final String description, final String patientEprSpid, final List<@NonNull ChChildPolicySet> policySets) {
        this.policySetId = policySetId;
        this.description = description;
        this.patientEprSpid = patientEprSpid;
        this.policySets = new ArrayList<>(policySets);
    }

    public String getPolicySetId() {
        return policySetId;
    }

    public void setPolicySetId(final String policySetId) {
        this.policySetId = policySetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPatientEprSpid() {
        return patientEprSpid;
    }

    public void setPatientEprSpid(final String patientEprSpid) {
        this.patientEprSpid = patientEprSpid;
    }

    public List<ChChildPolicySet> getPolicySets() {
        return policySets;
    }

    public void setPolicySets(final List<ChChildPolicySet> policySets) {
        this.policySets = new ArrayList<>(policySets);
    }

    public List<ChChildPolicySetEmergency> getEmergencyPolicySets() {
        return this.policySets.stream()
                .filter(ChChildPolicySetEmergency.class::isInstance)
                .map(ChChildPolicySetEmergency.class::cast)
                .toList();
    }

    public List<ChChildPolicySetGroup> getGroupPolicySets() {
        return this.policySets.stream()
                .filter(ChChildPolicySetGroup.class::isInstance)
                .map(ChChildPolicySetGroup.class::cast)
                .toList();
    }

    public List<ChChildPolicySetHcp> getHcpPolicySets() {
        return this.policySets.stream()
                .filter(ChChildPolicySetHcp.class::isInstance)
                .map(ChChildPolicySetHcp.class::cast)
                .toList();
    }

    public List<ChChildPolicySetRepresentative> getRepresentativePolicySets() {
        return this.policySets.stream()
                .filter(ChChildPolicySetRepresentative.class::isInstance)
                .map(ChChildPolicySetRepresentative.class::cast)
                .toList();
    }
}
