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
package org.husky.appc.ch.xml;

import org.husky.appc.ch.models.ChChildPolicySetEmergency;
import org.husky.appc.ch.models.ChChildPolicySetGroup;
import org.husky.appc.ch.models.ChChildPolicySetHcp;
import org.husky.appc.ch.models.ChChildPolicySetRepresentative;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;

import java.time.LocalDate;

import static org.husky.appc.ch.enums.ChAccessLevelPolicy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
class ChAppcUnmarshallerTest {

    @Test
    void unmarshallString() throws ParserConfigurationException {
        final var policySet = ChAppcUnmarshaller.unmarshall("""
                <?xml version="1.0" encoding="UTF-8"?>
                <PolicySet PolicySetId="urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b" PolicyCombiningAlgId="urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:first-applicable" xmlns="urn:oasis:names:tc:xacml:2.0:policy:schema:os" xmlns:ns2="urn:hl7-org:v3">
                    <Description>Test policy set</Description>
                    <Target>
                        <Resources>
                            <Resource>
                                <ResourceMatch MatchId="urn:hl7-org:v3:function:II-equal">
                                    <AttributeValue DataType="urn:hl7-org:v3#II">
                                        <ns2:InstanceIdentifier root="2.16.756.5.30.1.127.3.10.3" extension="761337610435209810"/>
                                    </AttributeValue>
                                    <ResourceAttributeDesignator AttributeId="urn:e-health-suisse:2015:epr-spid" DataType="urn:hl7-org:v3#II"/>
                                </ResourceMatch>
                            </Resource>
                        </Resources>
                    </Target>
                    <PolicySet PolicySetId="urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1" PolicyCombiningAlgId="urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides">
                        <Description>description emergency</Description>
                        <Target>
                            <Subjects>
                                <Subject>
                                    <SubjectMatch MatchId="urn:hl7-org:v3:function:CV-equal">
                                        <AttributeValue DataType="urn:hl7-org:v3#CV">
                                            <ns2:CodedValue code="HCP" codeSystem="2.16.756.5.30.1.127.3.10.6"/>
                                        </AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:2.0:subject:role" DataType="urn:hl7-org:v3#CV"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:hl7-org:v3:function:CV-equal">
                                        <AttributeValue DataType="urn:hl7-org:v3#CV">
                                            <ns2:CodedValue code="EMER" codeSystem="2.16.756.5.30.1.127.3.10.5"/>
                                        </AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xspa:1.0:subject:purposeofuse" DataType="urn:hl7-org:v3#CV"/>
                                    </SubjectMatch>
                                </Subject>
                            </Subjects>
                        </Target>
                        <PolicyIdReference>urn:e-health-suisse:2015:policies:permit-reading-normal</PolicyIdReference>
                    </PolicySet>
                    <PolicySet PolicySetId="urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c" PolicyCombiningAlgId="urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides">
                        <Description>description representative</Description>
                        <Target>
                            <Subjects>
                                <Subject>
                                    <SubjectMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">1.2.3.4</AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">urn:e-health-suisse:representative-id</AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id-qualifier" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:hl7-org:v3:function:CV-equal">
                                        <AttributeValue DataType="urn:hl7-org:v3#CV">
                                            <ns2:CodedValue code="REP" codeSystem="2.16.756.5.30.1.127.3.10.6"/>
                                        </AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:2.0:subject:role" DataType="urn:hl7-org:v3#CV"/>
                                    </SubjectMatch>
                                </Subject>
                            </Subjects>
                        </Target>
                        <PolicyIdReference>urn:e-health-suisse:2015:policies:permit-reading-normal</PolicyIdReference>
                    </PolicySet>
                    <PolicySet PolicySetId="urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5" PolicyCombiningAlgId="urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides">
                        <Description>description hcp</Description>
                        <Target>
                            <Subjects>
                                <Subject>
                                    <SubjectMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">7601002860123</AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">urn:gs1:gln</AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id-qualifier" DataType="http://www.w3.org/2001/XMLSchema#string"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:hl7-org:v3:function:CV-equal">
                                        <AttributeValue DataType="urn:hl7-org:v3#CV">
                                            <ns2:CodedValue code="HCP" codeSystem="2.16.756.5.30.1.127.3.10.6"/>
                                        </AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:2.0:subject:role" DataType="urn:hl7-org:v3#CV"/>
                                    </SubjectMatch>
                                </Subject>
                            </Subjects>
                        </Target>
                        <PolicyIdReference>urn:e-health-suisse:2015:policies:permit-reading-secret</PolicyIdReference>
                        <PolicyIdReference>urn:e-health-suisse:2015:policies:permit-writing-secret</PolicyIdReference>
                    </PolicySet>
                    <PolicySet PolicySetId="urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112" PolicyCombiningAlgId="urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides">
                        <Description>description group</Description>
                        <Target>
                            <Subjects>
                                <Subject>
                                    <SubjectMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:anyURI-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#anyURI">urn:oid:1.2.3</AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xspa:1.0:subject:organization-id" DataType="http://www.w3.org/2001/XMLSchema#anyURI"/>
                                    </SubjectMatch>
                                    <SubjectMatch MatchId="urn:hl7-org:v3:function:CV-equal">
                                        <AttributeValue DataType="urn:hl7-org:v3#CV">
                                            <ns2:CodedValue code="HCP" codeSystem="2.16.756.5.30.1.127.3.10.6"/>
                                        </AttributeValue>
                                        <SubjectAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:2.0:subject:role" DataType="urn:hl7-org:v3#CV"/>
                                    </SubjectMatch>
                                </Subject>
                            </Subjects>
                            <Environments>
                                <Environment>
                                    <EnvironmentMatch MatchId="urn:oasis:names:tc:xacml:1.0:function:date-greater-than-or-equal">
                                        <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#date">2032-01-01</AttributeValue>
                                        <EnvironmentAttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:environment:current-date" DataType="http://www.w3.org/2001/XMLSchema#date"/>
                                    </EnvironmentMatch>
                                </Environment>
                            </Environments>
                        </Target>
                        <PolicyIdReference>urn:e-health-suisse:2015:policies:permit-reading-normal</PolicyIdReference>
                    </PolicySet>
                </PolicySet>
                """);

        assertEquals("urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b", policySet.getPolicySetId());
        assertEquals("Test policy set", policySet.getDescription());
        assertEquals("761337610435209810", policySet.getPatientEprSpid());
        assertEquals(4, policySet.getPolicySets().size());

        var childPolicySet = policySet.getPolicySets().get(0);
        assertInstanceOf(ChChildPolicySetEmergency.class, childPolicySet);
        assertEquals("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", childPolicySet.getId());
        assertEquals("description emergency", childPolicySet.getDescription());
        assertEquals(1, childPolicySet.getPolicies().size());

        childPolicySet = policySet.getPolicySets().get(1);
        assertInstanceOf(ChChildPolicySetRepresentative.class, childPolicySet);
        assertEquals("urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c", childPolicySet.getId());
        assertEquals("description representative", childPolicySet.getDescription());
        assertEquals("1.2.3.4",
                ((ChChildPolicySetRepresentative)childPolicySet).getRepresentativeId());
        assertEquals(1, childPolicySet.getPolicies().size());
        assertEquals(PERMIT_READING_NORMAL, childPolicySet.getPolicies().get(0));

        childPolicySet = policySet.getPolicySets().get(2);
        assertInstanceOf(ChChildPolicySetHcp.class, childPolicySet);
        assertEquals("urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5", childPolicySet.getId());
        assertEquals("description hcp", childPolicySet.getDescription());
        assertEquals("7601002860123",
                ((ChChildPolicySetHcp)childPolicySet).getHcpGln());
        assertEquals(2, childPolicySet.getPolicies().size());
        assertEquals(PERMIT_READING_SECRET, childPolicySet.getPolicies().get(0));
        assertEquals(PERMIT_WRITING_SECRET, childPolicySet.getPolicies().get(1));

        childPolicySet = policySet.getPolicySets().get(3);
        assertInstanceOf(ChChildPolicySetGroup.class, childPolicySet);
        assertEquals("urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112", childPolicySet.getId());
        assertEquals("description group", childPolicySet.getDescription());
        assertEquals("1.2.3",
                ((ChChildPolicySetGroup)childPolicySet).getGroupOid());
        assertEquals(LocalDate.of(2032, 1, 1),
                ((ChChildPolicySetGroup)childPolicySet).getValidityEndDate());
        assertEquals(1, childPolicySet.getPolicies().size());
        assertEquals(PERMIT_READING_NORMAL, childPolicySet.getPolicies().get(0));
    }
}