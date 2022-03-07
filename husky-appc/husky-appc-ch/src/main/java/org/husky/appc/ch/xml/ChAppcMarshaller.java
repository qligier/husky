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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.models.ChPolicyInterface;
import org.husky.appc.ch.models.ChPolicySetDocument;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.appc.models.*;
import org.husky.appc.xml.AppcMarshaller;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.husky.common.enums.CodeSystems.SWISS_EPR_SPID;

/**
 * This class provides a marshaller implementation to convert Swiss APPC documents to their XML representation.
 *
 * @author Quentin Ligier
 **/
public class ChAppcMarshaller {

    private static final ObjectFactory APPC_FACTORY = new ObjectFactory();

    /**
     * This class is not instantiable.
     */
    private ChAppcMarshaller() {
    }

    /**
     * Marshalles a Swiss APPC document to a {@link String}.
     *
     * @param swissPolicySet The Swiss APPC document to marshall.
     * @return the XML representation of the {@code appcDocument}.
     * @throws JAXBException if an error was encountered while creating the marshaller.
     */
    public static String marshall(final ChPolicySetDocument swissPolicySet) throws JAXBException {
        Objects.requireNonNull(swissPolicySet);

        final var policySet = new PolicySetType();
        policySet.setPolicySetId(swissPolicySet.getId());
        policySet.setPolicyCombiningAlgId(AppcUrns.OASIS_ALGO_DENY_OVERRIDES);
        policySet.setDescription(swissPolicySet.getDescription());
        policySet.setTarget(createPatientTarget(swissPolicySet.getPatientEprSpid()));

        swissPolicySet.getContainedPolicySets().stream()
                .map(ChAppcMarshaller::createPolicySet)
                .map(APPC_FACTORY::createPolicySet)
                .forEach(child -> policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(child));

        return AppcMarshaller.marshall(policySet);
    }

    /**
     * Creates the resource target for a patient EPR-SPID.
     *
     * @param patientEprSpid the patient EPR-SPID.
     * @return the created {@link TargetType}.
     */
    static TargetType createPatientTarget(final String patientEprSpid) {
        final var instanceIdentifier = new II(SWISS_EPR_SPID.getCodeSystemId(), patientEprSpid);
        final var attributeValue = new AttributeValueType(instanceIdentifier);
        final var resAttrDesignator = new AttributeDesignatorType(ChAppcUrns.EPR_SPID, AppcUrns.II);

        final var resourceMatch = new ResourceMatchType();
        resourceMatch.setMatchId(AppcUrns.FUNCTION_II_EQUAL);
        resourceMatch.setAttributeValue(attributeValue);
        resourceMatch.setResourceAttributeDesignator(resAttrDesignator);

        return new TargetType(new ResourcesType(new ResourceType(resourceMatch)));
    }

    static PolicySetType createPolicySet(final ChPolicySetInterface chPolicySet) {
        final var target = new TargetType();
        target.setEnvironments(createPolicySetEnvironments(chPolicySet));
        target.setSubjects(createPolicySetSubjects(chPolicySet));

        final var policySet = new PolicySetType();
        policySet.setPolicySetId(chPolicySet.getId());
        policySet.setPolicyCombiningAlgId(AppcUrns.OASIS_ALGO_DENY_OVERRIDES);
        policySet.setDescription(chPolicySet.getDescription());
        policySet.setTarget(target);

        chPolicySet.getReferencedPolicySets().stream()
                .map(ChPolicySetInterface::getId)
                .map(IdReferenceType::new)
                .map(APPC_FACTORY::createPolicySetIdReference)
                .forEach(child -> policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(child));
        chPolicySet.getReferencedPolicies().stream()
                .map(ChPolicyInterface::getId)
                .map(IdReferenceType::new)
                .map(APPC_FACTORY::createPolicyIdReference)
                .forEach(child -> policySet.getPolicySetOrPolicyOrPolicySetIdReference().add(child));

        return policySet;
    }

    static SubjectsType createPolicySetSubjects(final ChPolicySetInterface chPolicySet) {
        final List<SubjectMatchType> subjectMatches = new ArrayList<>();

        if (chPolicySet.getSubjectId() != null) {
            subjectMatches.add(new SubjectMatchType(
                    new AttributeValueType(chPolicySet.getSubjectId()),
                    new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID, AppcUrns.XS_STRING),
                    AppcUrns.FUNCTION_STRING_EQUAL
            ));
        }
        if (chPolicySet.getSubjectIdQualifier() != null) {
            subjectMatches.add(new SubjectMatchType(
                    new AttributeValueType(chPolicySet.getSubjectIdQualifier()),
                    new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ID_QUALIFIER, AppcUrns.XS_STRING),
                    AppcUrns.FUNCTION_STRING_EQUAL
            ));
        }
        if (chPolicySet.getRole() != null) {
            subjectMatches.add(new SubjectMatchType(
                    new AttributeValueType(new CV(chPolicySet.getRole())),
                    new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ROLE, AppcUrns.CV),
                    AppcUrns.FUNCTION_CV_EQUAL
            ));
        }
        if (chPolicySet.getPurposeOfUse() != null) {
            subjectMatches.add(new SubjectMatchType(
                    new AttributeValueType(new CV(chPolicySet.getPurposeOfUse())),
                    new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_PURPOSE_USE, AppcUrns.CV),
                    AppcUrns.FUNCTION_CV_EQUAL
            ));
        }
        if (chPolicySet.getSubjectOrganizationId() != null) {
            subjectMatches.add(new SubjectMatchType(
                    new AttributeValueType(chPolicySet.getSubjectOrganizationId(), AppcUrns.XS_ANY_URI),
                    new SubjectAttributeDesignatorType(AppcUrns.OASIS_SUBJECT_ORG_ID, AppcUrns.XS_ANY_URI),
                    AppcUrns.FUNCTION_ANY_URI_EQUAL
            ));
        }

        // Conjunctive sequence of subject matches
        return new SubjectsType(new SubjectType(subjectMatches));
    }

    /**
     * Creates the policy set Environments element.
     *
     * @return the created {@link EnvironmentsType} or {@code null} if it's empty.
     */
    @Nullable
    static EnvironmentsType createPolicySetEnvironments(final ChPolicySetInterface chPolicySet) {
        if (chPolicySet.getValidityStartDate() == null && chPolicySet.getValidityEndDate() == null) {
            return null;
        }
        final List<EnvironmentMatchType> environmentMatches = new ArrayList<>();
        if (chPolicySet.getValidityStartDate() != null) {
            environmentMatches.add(new EnvironmentMatchType(
                    new AttributeValueType(chPolicySet.getValidityStartDate()),
                    new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                    AppcUrns.FUNCTION_DATE_GT_EQ
            ));
        }
        if (chPolicySet.getValidityEndDate() != null) {
            environmentMatches.add(new EnvironmentMatchType(
                    new AttributeValueType(chPolicySet.getValidityEndDate()),
                    new AttributeDesignatorType(AppcUrns.OASIS_ENV_CURRENT_DATE, AppcUrns.XS_DATE),
                    AppcUrns.FUNCTION_DATE_LT_EQ
            ));
        }
        return new EnvironmentsType(new EnvironmentType(environmentMatches));
    }
}
