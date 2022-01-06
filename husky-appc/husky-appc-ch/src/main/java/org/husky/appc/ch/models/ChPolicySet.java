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

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * The simplified model of a Swiss APPC document.
 *
 * @author Quentin Ligier
 **/
@Data
@AllArgsConstructor
public class ChPolicySet {

    private String policySetId;

    private String description;

    private String patientEprSpid;

    private final List<@NonNull ChChildPolicySet> policySets;
}
