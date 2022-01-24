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

package org.husky.appc.ch.enums;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * The enumeration of Swiss actions, as defined in <em>Nationale Integrationsprofile nach Artikel 5 Absatz 1
 * Buchstabe c EPDV-EDI, Authorization Decision Request (CH:ADR) and Privacy Policy Query (CH:PPQ)</em> (version 2.9
 * of February 3, 2020).
 *
 * @author Quentin Ligier
 **/
public enum ChAction {

    REGISTRY_STORED_QUERY("urn:ihe:iti:2007:RegistryStoredQuery"),
    RETRIEVE_DOCUMENT_SET("urn:ihe:iti:2007:RetrieveDocumentSet"),
    CROSS_GATEWAY_QUERY("urn:ihe:iti:2007:CrossGatewayQuery"),
    CROSS_GATEWAY_RETRIEVE("urn:ihe:iti:2007:CrossGatewayRetrieve"),
    REGISTER_DOCUMENT_SET("urn:ihe:iti:2007:RegisterDocumentSet-b"),
    PROVIDE_AND_REGISTER_DOCUMENT_SET("urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b"),
    RETRIEVE_IMAGING_DOCUMENT_SET("urn:ihe:rad:2009:RetrieveImagingDocumentSet"),
    CROSS_GATEWAY_RETRIEVE_IMAGING_DOCUMENT_SET("urn:ihe:rad:2011:CrossGatewayRetrieveImagingDocumentSet"),
    POLICY_QUERY("urn:e-health-suisse:2015:policy-administration:PolicyQuery"),
    ADD_POLICY("urn:e-health-suisse:2015:policy-administration:AddPolicy"),
    UPDATE_POLICY("urn:e-health-suisse:2015:policy-administration:UpdatePolicy"),
    DELETE_POLICY("urn:e-health-suisse:2015:policy-administration:DeletePolicy"),
    UPDATE_DOCUMENT_SET("urn:ihe:iti:2010:UpdateDocumentSet"),
    RESTRICTED_UPDATE_DOCUMENT_SET("urn:ihe:iti:2018:RestrictedUpdateDocumentSet"),
    RETRIEVE_ATNA_AUDIT("urn:e-health-suisse:2015:patient-audit-administration:RetrieveAtnaAudit");

    /**
     * The action URN.
     */
    private final String urn;

    ChAction(final String urn) {
        this.urn = Objects.requireNonNull(urn);
    }

    /**
     * Returns the URN.
     */
    public String getUrn() {
        return urn;
    }

    /**
     * Searches if a URN is present in the enum.
     *
     * @param urn The URN to find in the enum.
     * @return {@code true} if the URN is found in the enum, {@code false} otherwise.
     */
    public static boolean urnInEnum(@Nullable final String urn) {
        return Arrays.stream(values()).anyMatch(action -> action.getUrn().equals(urn));
    }

    /**
     * Returns an enum instance that corresponds to a URN.
     *
     * @param urn The URN to find in the enum.
     * @return the {@link ChAction} with the same URN.
     * @throws IllegalArgumentException if the URN is not found in the enum.
     */
    public static ChAction getByUrn(@Nullable final String urn) {
        return Arrays.stream(values())
                .filter(action -> action.getUrn().equals(urn))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The specified URN is not found in the enum ChAction"));
    }
}
