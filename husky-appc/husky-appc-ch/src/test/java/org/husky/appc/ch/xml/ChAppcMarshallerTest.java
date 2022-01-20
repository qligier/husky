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
import org.husky.appc.ch.models.ChPolicySet;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
class ChAppcMarshallerTest {

    @Test
    void marshall() throws JAXBException {
        final var policySet = new ChPolicySet(
                "urn:uuid:e3585197-9e3d-4ca3-9583-4540a3a5b64b",
                "Test policy set",
                "761337610435209810",
                List.of(
                        new ChChildPolicySetEmergency("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", null)
                )
        );

        System.out.println(ChAppcMarshaller.marshall(policySet));

    }
}