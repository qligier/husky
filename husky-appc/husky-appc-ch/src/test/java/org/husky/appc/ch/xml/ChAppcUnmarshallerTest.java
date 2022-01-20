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

import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;

import static org.junit.jupiter.api.Assertions.*;

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
                </PolicySet>
                """);
        System.out.println(policySet);
    }
}