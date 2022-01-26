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

import org.husky.appc.ch.models.*;
import org.husky.common.ch.enums.ConfidentialityCode;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.husky.appc.ch.enums.ChAccessLevelPolicy.*;
import static org.husky.appc.ch.enums.ChAction.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the {@link ChAppcMarshaller} and {@link ChAppcUnmarshaller} classes.
 *
 * @author Quentin Ligier
 **/
class ChAppcTest {

    @Test
    void marshallUnmarshall() throws JAXBException, ParserConfigurationException {
        final var expectedPolicySet = new ChPolicySet(
                "urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b",
                "Test policy set",
                "761337610435209810",
                List.of(
                        new ChChildPolicySetEmergency(
                                "urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1",
                                "description emergency",
                                true
                        ),
                        new ChChildPolicySetHcp(
                                "urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5",
                                "description hcp",
                                Set.of(PERMIT_READING_SECRET, PERMIT_WRITING_SECRET),
                                Set.of(ADD_POLICY, POLICY_QUERY),
                                ConfidentialityCode.NORMALLY_ACCESSIBLE,
                                "7601002860123"
                        ),
                        new ChChildPolicySetGroup(
                                "urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112",
                                "description group",
                                Set.of(PERMIT_READING_NORMAL),
                                Set.of(RETRIEVE_DOCUMENT_SET),
                                ConfidentialityCode.SECRET,
                                "1.2.3",
                                LocalDate.of(2032, 1, 1)
                        ),
                        new ChChildPolicySetRepresentative(
                                "urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c",
                                "description representative",
                                Set.of(PERMIT_READING_NORMAL),
                                Set.of(RETRIEVE_ATNA_AUDIT),
                                null,
                                "1.2.3.4"
                        )
                )
        );

        final var policySetString = ChAppcMarshaller.marshall(expectedPolicySet);
        assertNotNull(policySetString);
        assertTrue(policySetString.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PolicySet"));
        assertTrue(policySetString.length() > 200);

        final var policySet = ChAppcUnmarshaller.unmarshall(policySetString);
        assertEquals("urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b", policySet.getPolicySetId());
        assertEquals("Test policy set", policySet.getDescription());
        assertEquals("761337610435209810", policySet.getPatientEprSpid());
        assertEquals(4, policySet.getPolicySets().size());

        var childPolicySet = policySet.getPolicySets().get(0);
        assertInstanceOf(ChChildPolicySetEmergency.class, childPolicySet);
        assertEquals("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", childPolicySet.getId());
        assertEquals("description emergency", childPolicySet.getDescription());
        assertEquals(ConfidentialityCode.RESTRICTED_ACCESSIBLE, childPolicySet.getConfidentialityCode());
        assertEquals(1, childPolicySet.getPolicies().size());

        childPolicySet = policySet.getPolicySets().get(1);
        assertInstanceOf(ChChildPolicySetHcp.class, childPolicySet);
        assertEquals("urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5", childPolicySet.getId());
        assertEquals("description hcp", childPolicySet.getDescription());
        assertEquals("7601002860123",
                ((ChChildPolicySetHcp)childPolicySet).getHcpGln());
        assertEquals(2, childPolicySet.getPolicies().size());
        assertEquals(Set.of(PERMIT_READING_SECRET, PERMIT_WRITING_SECRET), childPolicySet.getPolicies());
        assertEquals(Set.of(ADD_POLICY, POLICY_QUERY), childPolicySet.getActions());
        assertEquals(ConfidentialityCode.NORMALLY_ACCESSIBLE, childPolicySet.getConfidentialityCode());

        childPolicySet = policySet.getPolicySets().get(2);
        assertInstanceOf(ChChildPolicySetGroup.class, childPolicySet);
        assertEquals("urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112", childPolicySet.getId());
        assertEquals("description group", childPolicySet.getDescription());
        assertEquals("1.2.3",
                ((ChChildPolicySetGroup)childPolicySet).getGroupOid());
        assertEquals(LocalDate.of(2032, 1, 1),
                ((ChChildPolicySetGroup)childPolicySet).getValidityEndDate());
        assertEquals(1, childPolicySet.getPolicies().size());
        assertEquals(Set.of(PERMIT_READING_NORMAL), childPolicySet.getPolicies());
        assertEquals(Set.of(RETRIEVE_DOCUMENT_SET), childPolicySet.getActions());
        assertEquals(ConfidentialityCode.SECRET, childPolicySet.getConfidentialityCode());

        childPolicySet = policySet.getPolicySets().get(3);
        assertInstanceOf(ChChildPolicySetRepresentative.class, childPolicySet);
        assertEquals("urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c", childPolicySet.getId());
        assertEquals("description representative", childPolicySet.getDescription());
        assertEquals("1.2.3.4",
                ((ChChildPolicySetRepresentative)childPolicySet).getRepresentativeId());
        assertEquals(1, childPolicySet.getPolicies().size());
        assertEquals(Set.of(PERMIT_READING_NORMAL), childPolicySet.getPolicies());
        assertEquals(Set.of(RETRIEVE_ATNA_AUDIT), childPolicySet.getActions());
        assertNull(childPolicySet.getConfidentialityCode());
    }
}