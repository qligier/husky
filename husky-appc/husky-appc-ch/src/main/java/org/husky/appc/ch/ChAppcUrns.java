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

package org.husky.appc.ch;

/**
 * URNs defined in Swiss APPC documents. See also {@link org.husky.appc.AppcUrns}.
 *
 * @author Quentin Ligier
 **/
public class ChAppcUrns {

    /**
     * This class is not instantiable.
     */
    private ChAppcUrns() {
    }

    /**
     * URNs defined by eHealth Suisse.
     */
    public static final String EPR_SPID = "urn:e-health-suisse:2015:epr-spid";
    public static final String REPRESENTATIVE_ID = "urn:e-health-suisse:representative-id";

    /**
     * Other URNs.
     */
    public static final String GLN = "urn:gs1:gln";
}
