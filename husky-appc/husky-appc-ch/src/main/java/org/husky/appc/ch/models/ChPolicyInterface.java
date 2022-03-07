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
import org.husky.appc.ch.enums.ChAction;
import org.husky.appc.enums.RuleEffect;
import org.husky.common.ch.enums.ConfidentialityCode;

import java.util.List;

/**
 * The interface for policies used in this project.
 *
 * @author Quentin Ligier
 **/
public interface ChPolicyInterface {

    /**
     * Returns the policy Id.
     */
    String getId();

    /**
     * Returns the list of targeted actions. It shall not be an empty list.
     */
    List<@NonNull ChAction> getActions();

    /**
     * Returns the list of targeted confidentiality codes or {@code null} if it's not relevant. It shall not be an
     * empty list.
     */
    @Nullable List<@NonNull ConfidentialityCode> getConfidentialityCodes();

    /**
     * Returns the rule Id.
     */
    String getRuleId();

    /**
     * Returns the rule effect.
     */
    RuleEffect getRuleEffect();
}
