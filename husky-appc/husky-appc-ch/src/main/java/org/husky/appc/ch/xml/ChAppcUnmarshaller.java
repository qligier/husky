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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.husky.appc.AppcUrns;
import org.husky.appc.ch.errors.InvalidSwissAppcContentException;
import org.husky.appc.ch.models.*;
import org.husky.appc.models.*;
import org.husky.appc.xml.AppcUnmarshaller;
import org.husky.common.utils.OptionalUtils;
import org.xml.sax.InputSource;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;

import static org.husky.common.enums.CodeSystems.SWISS_EPR_SPID;

/**
 * This class provides an unmarshaller implementation to read Swiss APPC documents from their XML representation.
 *
 * <p>This unmarshaller assumes the document is valid regarding the specifications. If it isn't, the unmarshalled
 * object may not reflect the original document perfectly. Use the validator before.
 *
 * @author Quentin Ligier
 **/
public class ChAppcUnmarshaller {

    /**
     * This class is not instantiable.
     */
    private ChAppcUnmarshaller() {
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param appcContent The Swiss APPC content as a string.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static ChPolicySet unmarshall(final String appcContent) throws DataBindingException, ParserConfigurationException {
        return mapSwissModel(AppcUnmarshaller.unmarshall(appcContent));
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param appcInputStream The Swiss APPC content as an {@link InputStream}.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static ChPolicySet unmarshall(final InputStream appcInputStream) throws DataBindingException, ParserConfigurationException {
        return mapSwissModel(AppcUnmarshaller.unmarshall(appcInputStream));
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param inputSource The Swiss APPC content as an {@link InputSource}.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static ChPolicySet unmarshall(final InputSource inputSource) throws DataBindingException, ParserConfigurationException {
        return mapSwissModel(AppcUnmarshaller.unmarshall(inputSource));
    }

    /**
     * Maps a {@link PolicySetType} to a {@link ChPolicySet}.
     *
     * @param policySet The parsed policy set.
     * @return the mapped Swiss model.
     */
    private static ChPolicySet mapSwissModel(final PolicySetType policySet) {
        final List<@NonNull ChChildPolicySet> policySets = policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream().map(JAXBElement::getValue).map(object -> OptionalUtils.castOrNull(object, PolicySetType.class)).filter(Objects::nonNull).map(ChAppcUnmarshaller::extractChildPolicySet).toList();

        return new ChPolicySet(Objects.requireNonNull(policySet.getPolicySetId(), "The PolicySet Id is required"), Objects.requireNonNull(policySet.getDescription(), "The root PolicySet description is required"), extractPatientEprSpid(policySet), policySets);
    }

    /**
     * Extracts the mandatory patient EPR-SPID or throws.
     *
     * @param policySet The parsed policy set.
     * @return the extracted patient EPR-SPID value.
     * @throws InvalidSwissAppcContentException if the patient EPR-SPID is not found.
     */
    private static String extractPatientEprSpid(final PolicySetType policySet) throws InvalidSwissAppcContentException {
        return Optional.ofNullable(policySet.getTarget()).map(TargetType::getResources).map(ResourcesType::getResource).map(OptionalUtils::getListOnlyElement).map(ResourceType::getResourceMatch).map(OptionalUtils::getListOnlyElement).map(ResourceMatchType::getAttributeValue).flatMap(AttributeValueType::getSingleIi).filter(ii -> SWISS_EPR_SPID.getCodeSystemId().equals(ii.getRoot())).map(II::getExtension).orElseThrow(() -> new InvalidSwissAppcContentException("The patient EPR-SPID has not been found"));
    }

    private static ChChildPolicySet extractChildPolicySet(final PolicySetType policySet) {
        final String id = Optional.ofNullable(policySet.getPolicySetId())
                .orElseThrow(() -> new InvalidSwissAppcContentException("The PolicySet has no Id"));
        final String description = policySet.getDescription();
        final List<String> policySetIds = policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .map(JAXBElement::getValue).filter(IdReferenceType.class::isInstance).map(IdReferenceType.class::cast)
                // TODO filter only PolicySetId
                .map(IdReferenceType::getValue).filter(Objects::nonNull).toList();
        if (policySetIds.size() != 1) {
            throw new InvalidSwissAppcContentException("The PolicySet hasn't a single PolicySetId reference.");
        }
        final String policySetId = policySetIds.get(0);

        final BiFunction<List<SubjectMatchType>, String, String> extractValue =
                (subjectMatches, attributeId) -> subjectMatches.stream()
                        .filter(subjectMatch -> subjectMatch.getSubjectAttributeDesignator() != null)
                        .filter(subjectMatch -> attributeId.equals(subjectMatch.getSubjectAttributeDesignator().getAttributeId()))
                        .findAny()
                        .map(SubjectMatchType::getAttributeValue)
                        .flatMap(AttributeValueType::getStringContent)
                        .orElse(null);
        final BiFunction<List<SubjectMatchType>, String, String> extractCv =
                (subjectMatches, attributeId) -> subjectMatches.stream()
                        .filter(subjectMatch -> subjectMatch.getSubjectAttributeDesignator() != null)
                        .filter(subjectMatch -> attributeId.equals(subjectMatch.getSubjectAttributeDesignator().getAttributeId()))
                        .findAny()
                        .map(SubjectMatchType::getAttributeValue)
                        .flatMap(AttributeValueType::getCvContent)
                        .map(CV::getCode)
                        .orElse(null);

        final List<SubjectMatchType> subjectMatches = Optional.ofNullable(policySet.getTarget())
                .map(TargetType::getSubjects)
                .map(SubjectsType::getSubject)
                .map(OptionalUtils::getListOnlyElement)
                .map(SubjectType::getSubjectMatch)
                .orElse(Collections.emptyList())
                .stream().toList();
        final String subjectId = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ID);
        final String role = extractCv.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ROLE);
        final String organizationId = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ORG_ID);
        final String purposeOfUse = extractCv.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_PURPOSE_USE);

        final BiFunction<PolicySetType, String, LocalDate> extractDate =
                (policySet2, matchId) -> Optional.ofNullable(policySet2.getTarget())
                        .map(TargetType::getEnvironments)
                        .map(EnvironmentsType::getEnvironment)
                        .map(OptionalUtils::getListOnlyElement)
                        .map(EnvironmentType::getEnvironmentMatch)
                        .stream()
                        .flatMap(Collection::stream)
                        .filter(envMatch -> matchId.equals(envMatch.getMatchId()))
                        .filter(envMatch -> envMatch.getEnvironmentAttributeDesignator() != null && AppcUrns.OASIS_ENV_CURRENT_DATE.equals(envMatch.getEnvironmentAttributeDesignator().getAttributeId()))
                        .findAny()
                        .map(EnvironmentMatchType::getAttributeValue)
                        .flatMap(AttributeValueType::getStringContent)
                        .map(LocalDate::parse)
                        .orElse(null);

        final LocalDate startDate = extractDate.apply(policySet, AppcUrns.FUNCTION_DATE_GT_EQ);
        final LocalDate endDate = extractDate.apply(policySet, AppcUrns.FUNCTION_DATE_LT_EQ);

        if ("REP".equals(role) && subjectId != null) {
            return new ChChildPolicySetRepresentative(id, description, policySetId, startDate, endDate, subjectId);
        } else if ("HCP".equals(role) && subjectId != null && !"EMER".equals(purposeOfUse)) {
            return new ChChildPolicySetHealthcareProfessional(id, description, policySetId, startDate, endDate, subjectId);
        } else if (organizationId != null && endDate != null) {
            return new ChChildPolicySetGroup(id, description, policySetId, startDate, endDate, organizationId);
        } else if ("EMER".equals(purposeOfUse) && "HCP".equals(role)) {
            return new ChChildPolicySetEmergency(id, description, policySetId, startDate, endDate);
        }
        throw new InvalidSwissAppcContentException("The PolicySet is unparsable");
    }
}
