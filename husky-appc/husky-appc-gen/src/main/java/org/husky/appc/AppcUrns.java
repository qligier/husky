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

package org.husky.appc;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
public class AppcUrns {

    /**
     * This class is not instantiable.
     */
    private AppcUrns() {
    }

    /**
     * URNs defined by the W3C.
     */
    public static final String XS_STRING = "http://www.w3.org/2001/XMLSchema#string";
    public static final String XS_ANY_URI = "http://www.w3.org/2001/XMLSchema#anyURI";
    public static final String XS_DATE = "http://www.w3.org/2001/XMLSchema#date";

    /**
     * URNs defined by OASIS.
     */
    public static final String OASIS_ACTION_ID = "urn:oasis:names:tc:xacml:1.0:action:action-id";
    public static final String OASIS_SUBJECT_ID = "urn:oasis:names:tc:xacml:1.0:subject:subject-id";
    public static final String OASIS_SUBJECT_ID_QUALIFIER = "urn:oasis:names:tc:xacml:1.0:subject:subject-id-qualifier";
    public static final String OASIS_SUBJECT_ROLE = "urn:oasis:names:tc:xacml:2.0:subject:role";
    public static final String OASIS_SUBJECT_ORG_ID = "urn:oasis:names:tc:xspa:1.0:subject:organization-id";
    public static final String OASIS_SUBJECT_PURPOSE_USE = "urn:oasis:names:tc:xspa:1.0:subject:purposeofuse";
    public static final String OASIS_ALGO_DENY_OVERRIDES = "urn:oasis:names:tc:xacml:1" +
            ".0:policy-combining-algorithm:deny-overrides";
    public static final String OASIS_ALGO_FIRST_APPLICABLE = "urn:oasis:names:tc:xacml:1" +
            ".0:rule-combining-algorithm:first-applicable";
    public static final String OASIS_ENV_CURRENT_DATE = "urn:oasis:names:tc:xacml:1.0:environment:current-date";

    /**
     * URNs defined by HL7.
     */
    public static final String FUNCTION_II_EQUAL = "urn:hl7-org:v3:function:II-equal";
    public static final String FUNCTION_STRING_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
    public static final String FUNCTION_CV_EQUAL = "urn:hl7-org:v3:function:CV-equal";
    public static final String FUNCTION_ANY_URI_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal";
    public static final String FUNCTION_DATE_GT_EQ = "urn:oasis:names:tc:xacml:1.0:function:date-greater-than-or-equal";
    public static final String II = "urn:hl7-org:v3#II";
    public static final String CV = "urn:hl7-org:v3#CV";
}
