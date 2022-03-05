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
package org.husky.appc.ch.policyset;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.ch.policy.*;
import org.husky.communication.ch.enums.Role;

import java.util.List;

/**
 * The referenced policy set 'access-level normal'.
 *
 * @author Quentin Ligier
 **/
public class PolicySetDocumentAdministration extends ReferencedPolicySet {
    public static final PolicySetDocumentAdministration INSTANCE = new PolicySetDocumentAdministration();

    /**
     * Returns the policy set Id.
     */
    @Override
    public String getId() {
        return "urn:e-health-suisse:2015:policies:doc-admin";
    }

    /**
     * Returns the list of targeted subject roles.
     */
    @Override
    public @Nullable List<@NonNull Role> getRoles() {
        return List.of(Role.DOCUMENT_ADMINISTRATOR);
    }

    /**
     * Returns the list of targeted policies. It shall not be an empty list.
     */
    @Override
    public List<@NonNull ReferencedPolicy> getPolicies() {
        return List.of(
                PolicyUpdateMetadata.INSTANCE,
                PolicyPermitReadingNormal.INSTANCE,
                PolicyPermitReadingRestricted.INSTANCE,
                PolicyPermitReadingSecret.INSTANCE,
                PolicyPermitWritingNormal.INSTANCE,
                PolicyPermitWritingRestricted.INSTANCE,
                PolicyPermitWritingNormal.INSTANCE
        );
    }
}
