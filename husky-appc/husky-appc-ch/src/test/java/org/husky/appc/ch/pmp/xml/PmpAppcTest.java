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
package org.husky.appc.ch.pmp.xml;

import org.husky.appc.ch.models.ChPolicySetDocument;
import org.husky.appc.ch.pmp.models.PmpPolicySetEmergency;
import org.husky.appc.ch.pmp.models.PmpPolicySetGroup;
import org.husky.appc.ch.pmp.models.PmpPolicySetHealthcareProfessional;
import org.husky.appc.ch.pmp.policysets.PolicySetPmpEmedicationAccess;
import org.husky.appc.ch.policysets.PolicySetExclusionList;
import org.husky.appc.ch.xml.ChAppcMarshaller;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
class PmpAppcTest {

    @Test
    void marshallUnmarshall() throws JAXBException, ParserConfigurationException {
        final var expectedPolicySet = new ChPolicySetDocument(
                "urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b",
                "Test policy set",
                "761337610435209810",
                List.of(
                        new PmpPolicySetEmergency(
                                "urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1",
                                "description emergency",
                                List.of(PolicySetPmpEmedicationAccess.INSTANCE),
                                null,
                                null
                        ),
                        new PmpPolicySetHealthcareProfessional(
                                "urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5",
                                "description hcp",
                                List.of(PolicySetExclusionList.INSTANCE),
                                LocalDate.of(2027, 2, 3),
                                null,
                                "7601002860123"
                        ),
                        new PmpPolicySetGroup(
                                "urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112",
                                "description group",
                                List.of(PolicySetPmpEmedicationAccess.INSTANCE),
                                null,
                                LocalDate.of(2032, 1, 1),
                                "1.2.3"
                        )
                )
        );

        final var policySetString = ChAppcMarshaller.marshall(expectedPolicySet);
        assertNotNull(policySetString);
        assertTrue(policySetString.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PolicySet"));
        assertTrue(policySetString.length() > 200);

        final var policySet = (new PmpAppcUnmarshaller()).unmarshall(policySetString);
        assertEquals("urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b", policySet.getId());
        assertEquals("Test policy set", policySet.getDescription());
        assertEquals("761337610435209810", policySet.getPatientEprSpid());
        assertEquals(3, policySet.getContainedPolicySets().size());

        var childPolicySet = policySet.getContainedPolicySets().get(0);
        assertInstanceOf(PmpPolicySetEmergency.class, childPolicySet);
        assertEquals("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", childPolicySet.getId());
        assertEquals("description emergency", childPolicySet.getDescription());
        assertTrue(childPolicySet.getReferencedPolicies().isEmpty());
        assertEquals(1, childPolicySet.getReferencedPolicySets().size());
        assertEquals("urn:e-health-suisse:2022:policies:pmp:emedication-access",
                childPolicySet.getReferencedPolicySets().get(0).getId());

        childPolicySet = policySet.getContainedPolicySets().get(1);
        assertInstanceOf(PmpPolicySetHealthcareProfessional.class, childPolicySet);
        assertEquals("urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5", childPolicySet.getId());
        assertEquals("description hcp", childPolicySet.getDescription());
        assertTrue(childPolicySet.getReferencedPolicies().isEmpty());
        assertEquals(1, childPolicySet.getReferencedPolicySets().size());
        assertEquals("7601002860123",
                ((PmpPolicySetHealthcareProfessional) childPolicySet).getSubjectId());
        assertEquals("urn:e-health-suisse:2015:policies:exclusion-list",
                childPolicySet.getReferencedPolicySets().get(0).getId());
        assertEquals(LocalDate.of(2027, 2, 3), childPolicySet.getValidityStartDate());

        childPolicySet = policySet.getContainedPolicySets().get(2);
        assertInstanceOf(PmpPolicySetGroup.class, childPolicySet);
        assertEquals("urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112", childPolicySet.getId());
        assertEquals("description group", childPolicySet.getDescription());
        assertTrue(childPolicySet.getReferencedPolicies().isEmpty());
        assertEquals(1, childPolicySet.getReferencedPolicySets().size());
        assertEquals("urn:oid:1.2.3",
                ((PmpPolicySetGroup) childPolicySet).getSubjectOrganizationId());
        assertEquals(LocalDate.of(2032, 1, 1), childPolicySet.getValidityEndDate());
        assertEquals("urn:e-health-suisse:2022:policies:pmp:emedication-access",
                childPolicySet.getReferencedPolicySets().get(0).getId());
    }
}
