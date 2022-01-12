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
import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.enums.ChAccessLevelPolicy;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

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
        final List<@NonNull ChChildPolicySet> policySets = policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .map(JAXBElement::getValue)
                .map(object -> OptionalUtils.castOrNull(object, PolicySetType.class))
                .filter(Objects::nonNull)
                .map(ChAppcUnmarshaller::extractChildPolicySet)
                .toList();

        return new ChPolicySet(
                policySet.getPolicySetId(),
                policySet.getDescription(),
                extractPatientEprSpid(policySet),
                policySets
        );
    }

    /**
     * Extracts the mandatory patient EPR-SPID or throws.
     *
     * @param policySet The parsed policy set.
     * @return the extracted patient EPR-SPID value.
     * @throws InvalidSwissAppcContentException if the patient EPR-SPID is not found.
     */
    private static String extractPatientEprSpid(final PolicySetType policySet) throws InvalidSwissAppcContentException {
        return Optional.ofNullable(policySet.getTarget())
                .map(TargetType::getResources)
                .map(ResourcesType::getResource)
                .map(OptionalUtils::getListOnlyElement)
                .map(ResourceType::getResourceMatch)
                .map(OptionalUtils::getListOnlyElement)
                .map(ResourceMatchType::getAttributeValue)
                .flatMap(AttributeValueType::getSingleIi)
                .filter(ii -> ChAppcUrns.EPR_SPID.equals(ii.getRoot()))
                .map(II::getExtension)
                .orElseThrow(() -> new InvalidSwissAppcContentException("The patient EPR-SPID has not been found"));
    }

    private static ChChildPolicySet extractChildPolicySet(final PolicySetType policySet) {
        final String id = Optional.ofNullable(policySet.getPolicySetId())
                        .orElseThrow(() -> new InvalidSwissAppcContentException("The PolicySet has no Id"));
        final String description = policySet.getDescription();
        final List<@NonNull ChAccessLevelPolicy> policies = policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .map(object -> OptionalUtils.castOrNull(object, IdReferenceType.class))
                .filter(Objects::nonNull)
                .map(IdReferenceType::getValue)
                .filter(ChAccessLevelPolicy::urnInEnum)
                .map(ChAccessLevelPolicy::getByUrn)
                .toList();

        final BiFunction<List<@NonNull SubjectMatchType>, String, String> extractValue =
                (subjectMatches, attributeId) -> subjectMatches.stream()
                    .filter(subjectMatch -> subjectMatch.getSubjectAttributeDesignator() != null)
                    .filter(subjectMatch -> attributeId.equals(subjectMatch.getSubjectAttributeDesignator().getAttributeId()))
                    .findAny()
                    .map(SubjectMatchType::getAttributeValue)
                    .flatMap(AttributeValueType::getStringContent)
                    .orElse(null);

        final List<@NonNull SubjectMatchType> subjectMatches = Optional.ofNullable(policySet.getTarget())
                .map(TargetType::getSubjects)
                .map(SubjectsType::getSubject)
                .map(OptionalUtils::getListOnlyElement)
                .map(SubjectType::getSubjectMatch)
                .orElse(Collections.emptyList()).stream()
                .toList();
        final String subjectId = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ID);
        final String role = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ROLE);
        final String organizationId = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_ORG_ID);
        final String purposeOfUse = extractValue.apply(subjectMatches, AppcUrns.OASIS_SUBJECT_PURPOSE_USE);
        final LocalDate endDate = Optional.ofNullable(policySet.getTarget().getEnvironments()).stream()
                .map(EnvironmentsType::getEnvironment)
                .map(OptionalUtils::getListOnlyElement)
                .filter(Objects::nonNull)
                .map(EnvironmentType::getEnvironmentMatch)
                .map(OptionalUtils::getListOnlyElement)
                .filter(Objects::nonNull)
                .filter(envMatch -> envMatch.getEnvironmentAttributeDesignator() != null)
                .filter(envMatch -> AppcUrns.OASIS_ENV_CURRENT_DATE.equals(envMatch.getEnvironmentAttributeDesignator().getAttributeId()))
                .findAny()
                .map(EnvironmentMatchType::getAttributeValue)
                .flatMap(AttributeValueType::getStringContent)
                .map(LocalDate::parse)
                .orElse(null);

        if ("REP".equals(role) && subjectId != null) {
            return new ChChildPolicySetRepresentative(id, description, policies, subjectId);
        } else if ("HCP".equals(role) && subjectId != null) {
            return new ChChildPolicySetHcp(id, description, policies, subjectId);
        } else if (organizationId != null && endDate != null) {
            return new ChChildPolicySetGroup(id, description, policies, organizationId, endDate);
        } else if ("EMER".equals(purposeOfUse)) {
            return new ChChildPolicySetEmergency(id, description);
        }
        throw new InvalidSwissAppcContentException("The PolicySet is unparsable");
    }
}
