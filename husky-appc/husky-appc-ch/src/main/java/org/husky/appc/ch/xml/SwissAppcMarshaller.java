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

import org.husky.appc.AppcUrns;
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.models.ChChildPolicySet;
import org.husky.appc.ch.models.ChPolicySet;
import org.husky.appc.models.*;
import org.husky.appc.xml.AppcMarshaller;

import javax.xml.bind.JAXBException;
import java.util.Comparator;
import java.util.Objects;

import static org.husky.common.enums.CodeSystems.SwissEprSpid;

/**
 * This class provides a marshaller implementation to convert Swiss APPC documents to their XML representation.
 *
 * @author Quentin Ligier
 **/
public class SwissAppcMarshaller {

    /**
     * This class is not instantiable.
     */
    private SwissAppcMarshaller() {
    }

    /**
     * Marshalles a Swiss APPC document to a {@link String}.
     *
     * @param swissPolicySet The Swiss APPC document to marshall.
     * @return the XML representation of the {@code appcDocument}.
     * @throws JAXBException if an error was encountered while creating the marshaller.
     */
    public static String marshall(final ChPolicySet swissPolicySet) throws JAXBException {
        Objects.requireNonNull(swissPolicySet);

        final var policySet = new PolicySetType();
        policySet.setPolicySetId(swissPolicySet.getPolicySetId());
        policySet.setPolicyCombiningAlgId(AppcUrns.OASIS_ALGO_FIRST_APPLICABLE);
        policySet.setDescription(swissPolicySet.getDescription());
        policySet.setTarget(createPatientTarget(swissPolicySet.getPatientEprSpid()));

        final var appcFactory = new ObjectFactory();
        swissPolicySet.getPolicySets().stream()
                .sorted(Comparator.comparingInt(ChChildPolicySet::getSortScore))
                .map(ChChildPolicySet::createPolicySet)
                .map(appcFactory::createPolicySet)
                .forEach(child -> policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(child));

        return AppcMarshaller.marshall(policySet);
    }

    /**
     * Creates the resource target for a patient EPR-SPID.
     *
     * @param patientEprSpid the patient EPR-SPID.
     * @return the created {@link TargetType}.
     */
    private static TargetType createPatientTarget(final String patientEprSpid) {
        final var instanceIdentifier = new II(SwissEprSpid.getCodeSystemId(), patientEprSpid);
        final var attributeValue = new AttributeValueType(instanceIdentifier);
        final var resAttrDesignator = new AttributeDesignatorType(ChAppcUrns.EPR_SPID, AppcUrns.II);

        final var resourceMatch = new ResourceMatchType();
        resourceMatch.setMatchId(AppcUrns.FUNCTION_II_EQUAL);
        resourceMatch.setAttributeValue(attributeValue);
        resourceMatch.setResourceAttributeDesignator(resAttrDesignator);

        return new TargetType(new ResourcesType(new ResourceType(resourceMatch)));
    }
}
