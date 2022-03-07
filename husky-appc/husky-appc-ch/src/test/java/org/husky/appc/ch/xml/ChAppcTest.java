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

import org.husky.appc.ch.models.ChPolicySetDocument;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test of the {@link ChAppcMarshaller} and {@link ChAppcUnmarshaller} classes.
 *
 * @author Quentin Ligier
 **/
class ChAppcTest {

    @Test
    void marshallUnmarshall() throws JAXBException, ParserConfigurationException {
        final var expectedPolicySet = new ChPolicySetDocument(
                "urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b",
                "Test policy set",
                "761337610435209810",
                List.of(
                )
        );

        final var policySetString = ChAppcMarshaller.marshall(expectedPolicySet);
        assertNotNull(policySetString);
        assertTrue(policySetString.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PolicySet"));
        assertTrue(policySetString.length() > 200);
/*
        final var policySet = ChAppcUnmarshaller.unmarshall(policySetString);
        assertEquals("urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b", policySet.getPolicySetId());
        assertEquals("Test policy set", policySet.getDescription());
        assertEquals("761337610435209810", policySet.getPatientEprSpid());
        assertEquals(4, policySet.getPolicySets().size());

        var childPolicySet = policySet.getPolicySets().get(0);
        assertInstanceOf(PmpPolicySetEmergency.class, childPolicySet);
        assertEquals("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", childPolicySet.getId());
        assertEquals("description emergency", childPolicySet.getDescription());
        assertEquals("urn:e-health-suisse:2015:policies:access-level:normal", childPolicySet.getReferencedPolicySetId());

        childPolicySet = policySet.getPolicySets().get(1);
        assertInstanceOf(PmpPolicySetHealthcareProfessional.class, childPolicySet);
        assertEquals("urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5", childPolicySet.getId());
        assertEquals("description hcp", childPolicySet.getDescription());
        assertEquals("7601002860123",
                ((PmpPolicySetHealthcareProfessional)childPolicySet).getHcpGln());
        assertEquals("urn:e-health-suisse:2015:policies:exclusion-list", childPolicySet.getReferencedPolicySetId());
        assertEquals(LocalDate.of(2027, 2, 3), childPolicySet.getValidityStartDate());

        childPolicySet = policySet.getPolicySets().get(2);
        assertInstanceOf(PmpPolicySetGroup.class, childPolicySet);
        assertEquals("urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112", childPolicySet.getId());
        assertEquals("description group", childPolicySet.getDescription());
        assertEquals("1.2.3",
                ((PmpPolicySetGroup)childPolicySet).getGroupOid());
        assertEquals(LocalDate.of(2032, 1, 1),
                ((PmpPolicySetGroup)childPolicySet).getValidityEndDate());
        assertEquals("urn:e-health-suisse:2015:policies:access-level:normal", childPolicySet.getReferencedPolicySetId());

        childPolicySet = policySet.getPolicySets().get(3);
        assertInstanceOf(PmpPolicySetRepresentative.class, childPolicySet);
        assertEquals("urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c", childPolicySet.getId());
        assertEquals("description representative", childPolicySet.getDescription());
        assertEquals("1.2.3.4",
                ((PmpPolicySetRepresentative)childPolicySet).getRepresentativeId());
        assertEquals("urn:e-health-suisse:2015:policies:access-level:normal", childPolicySet.getReferencedPolicySetId
        ());
 */
    }
}
