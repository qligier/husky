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
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;

import static org.husky.appc.ch.enums.ChAccessLevelPolicy.*;

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
                        new ChChildPolicySetEmergency("urn:uuid:29e64cce-19f6-43c4-9cc9-0227cb361ba1", "description " +
                                "emergency"),
                        new ChChildPolicySetHcp("urn:uuid:46a1a4c0-ed1e-439b-8da8-a523b10ce2b5", "description hcp",
                                List.of(PERMIT_READING_SECRET, PERMIT_WRITING_SECRET),
                                "7601002860123"),
                        new ChChildPolicySetGroup("urn:uuid:23393af4-68aa-46d1-a807-767e80fbd112", "description group", List.of(PERMIT_READING_NORMAL), "1.2.3",
                                LocalDate.of(2032, 1, 1)),
                        new ChChildPolicySetRepresentative("urn:uuid:d2c24c5b-42b9-4dc2-874e-1c2803b6c07c",
                                "description representative", List.of(PERMIT_READING_NORMAL), "1.2.3.4")
                )
        );

        System.out.println(ChAppcMarshaller.marshall(policySet));

    }
}