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
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.appc.AppcUrns;
import org.husky.appc.ch.errors.InvalidSwissAppcContentException;
import org.husky.appc.ch.models.ChGenericPolicySet;
import org.husky.appc.ch.models.ChPolicyInterface;
import org.husky.appc.ch.models.ChPolicySetDocument;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.appc.ch.policies.*;
import org.husky.appc.ch.policysets.PolicySetAccessLevelNormal;
import org.husky.appc.ch.policysets.PolicySetDocumentAdministration;
import org.husky.appc.ch.policysets.PolicySetExclusionList;
import org.husky.appc.ch.policysets.PolicySetPolicyAdministration;
import org.husky.appc.models.*;
import org.husky.appc.xml.AppcUnmarshaller;
import org.husky.common.utils.OptionalUtils;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;
import org.xml.sax.InputSource;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

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

    private static final String OASIS_OS_NS = "urn:oasis:names:tc:xacml:2.0:policy:schema:os";
    private static final QName _PolicySetIdReference_QNAME = new QName(OASIS_OS_NS, "PolicySetIdReference");
    private static final QName _PolicyIdReference_QNAME = new QName(OASIS_OS_NS, "PolicyIdReference");

    /**
     * The list of known policies.
     */
    private final List<@NonNull ChPolicyInterface> knownPolicies;

    /**
     * The list of known policy sets.
     */
    private final List<@NonNull ChPolicySetInterface> knownPolicySets;

    /**
     * Default constructor.
     */
    public ChAppcUnmarshaller() {
        this.knownPolicies = List.of(
                PolicyDenyAll.INSTANCE,
                PolicyFullPolicyAdministration.INSTANCE,
                PolicyPermitReadingNormal.INSTANCE,
                PolicyPermitReadingRestricted.INSTANCE,
                PolicyPermitReadingSecret.INSTANCE,
                PolicyPermitWritingNormal.INSTANCE,
                PolicyPermitWritingRestricted.INSTANCE,
                PolicyPermitWritingSecret.INSTANCE,
                PolicyUpdateMetadata.INSTANCE
        );
        this.knownPolicySets = List.of(
                PolicySetAccessLevelNormal.INSTANCE,
                PolicySetDocumentAdministration.INSTANCE,
                PolicySetPolicyAdministration.INSTANCE,
                PolicySetExclusionList.INSTANCE
        );
    }

    /**
     * Custom constructor.
     *
     * @param knownPolicies   The list of known policies.
     * @param knownPolicySets The list of known policy sets.
     */
    public ChAppcUnmarshaller(final List<@NonNull ChPolicyInterface> knownPolicies,
                              final List<@NonNull ChPolicySetInterface> knownPolicySets) {
        this.knownPolicies = Objects.requireNonNull(knownPolicies);
        this.knownPolicySets = Objects.requireNonNull(knownPolicySets);
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param appcContent The Swiss APPC content as a string.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public ChPolicySetDocument unmarshall(final String appcContent) throws DataBindingException, ParserConfigurationException {
        return this.mapSwissModel(AppcUnmarshaller.unmarshall(appcContent));
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param appcInputStream The Swiss APPC content as an {@link InputStream}.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public ChPolicySetDocument unmarshall(final InputStream appcInputStream) throws DataBindingException, ParserConfigurationException {
        return this.mapSwissModel(AppcUnmarshaller.unmarshall(appcInputStream));
    }

    /**
     * Unmarshalles a Swiss APPC document as a {@link PolicySetType} object.
     *
     * @param inputSource The Swiss APPC content as an {@link InputSource}.
     * @return the unmarshalled Swiss APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public ChPolicySetDocument unmarshall(final InputSource inputSource) throws DataBindingException, ParserConfigurationException {
        return this.mapSwissModel(AppcUnmarshaller.unmarshall(inputSource));
    }

    /**
     * Maps a {@link PolicySetType} to a {@link ChPolicySetDocument}.
     *
     * @param policySet The parsed policy set.
     * @return the mapped Swiss model.
     */
    protected ChPolicySetDocument mapSwissModel(final PolicySetType policySet) {
        final List<@NonNull ChPolicySetInterface> policySets = policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .map(JAXBElement::getValue)
                .map(object -> OptionalUtils.castOrNull(object, PolicySetType.class))
                .filter(Objects::nonNull)
                .map(this::extractChildPolicySet)
                .toList();

        return new ChPolicySetDocument(
                Objects.requireNonNull(policySet.getPolicySetId(), "The PolicySet Id is required"),
                Objects.requireNonNull(policySet.getDescription(), "The root PolicySet description is required"),
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
    String extractPatientEprSpid(final PolicySetType policySet) throws InvalidSwissAppcContentException {
        return Optional.ofNullable(policySet.getTarget())
                .map(TargetType::getResources)
                .map(ResourcesType::getResource)
                .map(OptionalUtils::getListOnlyElement)
                .map(ResourceType::getResourceMatch)
                .map(OptionalUtils::getListOnlyElement)
                .map(ResourceMatchType::getAttributeValue)
                .flatMap(AttributeValueType::getSingleIi)
                .filter(ii -> SWISS_EPR_SPID.getCodeSystemId().equals(ii.getRoot()))
                .map(II::getExtension)
                .orElseThrow(() -> new InvalidSwissAppcContentException("The patient EPR-SPID has not been found"));
    }

    protected ChPolicySetInterface extractChildPolicySet(final PolicySetType policySet) {
        final String id = Optional.ofNullable(policySet.getPolicySetId())
                .orElseThrow(() -> new InvalidSwissAppcContentException("The PolicySet has no Id"));
        final var policies = this.extractReferencedPolicies(policySet);
        final var policySets = this.extractReferencedPolicySets(policySet);

        final List<SubjectMatchType> subjectMatches = Optional.ofNullable(policySet.getTarget())
                .map(TargetType::getSubjects)
                .map(SubjectsType::getSubject)
                .map(OptionalUtils::getListOnlyElement)
                .map(SubjectType::getSubjectMatch)
                .orElse(Collections.emptyList())
                .stream().toList();

        final var role = Role.getEnum(this.extractSubjectCvValue(subjectMatches, AppcUrns.OASIS_SUBJECT_ROLE));
        if (role == null) {
            throw new InvalidSwissAppcContentException("The role is unknown");
        }
        final var purposeOfUse = PurposeOfUse.getEnum(this.extractSubjectCvValue(subjectMatches,
                AppcUrns.OASIS_SUBJECT_PURPOSE_USE));
        if (purposeOfUse == null) {
            throw new InvalidSwissAppcContentException("The purpose of use is unknown");
        }

        return new ChGenericPolicySet(
                id,
                policySet.getDescription(),
                role,
                purposeOfUse,
                null, // TODO
                this.extractEnvironmentDate(policySet, AppcUrns.FUNCTION_DATE_GT_EQ),
                this.extractEnvironmentDate(policySet, AppcUrns.FUNCTION_DATE_LT_EQ),
                this.extractSubjectStringValue(subjectMatches, AppcUrns.OASIS_SUBJECT_ID),
                this.extractSubjectStringValue(subjectMatches, AppcUrns.OASIS_SUBJECT_ID_QUALIFIER),
                this.extractSubjectStringValue(subjectMatches, AppcUrns.OASIS_SUBJECT_ORG_ID),
                policies,
                policySets
        );
    }

    @Nullable
    LocalDate extractEnvironmentDate(final PolicySetType policySet,
                                     final String matchId) {
        return Optional.ofNullable(policySet.getTarget())
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
    }

    @Nullable
    String extractSubjectStringValue(final List<@NonNull SubjectMatchType> subjectMatches,
                               final String attributeId) {
        return subjectMatches.stream()
                .filter(subjectMatch -> subjectMatch.getSubjectAttributeDesignator() != null)
                .filter(subjectMatch -> attributeId.equals(subjectMatch.getSubjectAttributeDesignator().getAttributeId()))
                .findAny()
                .map(SubjectMatchType::getAttributeValue)
                .flatMap(AttributeValueType::getStringContent)
                .orElse(null);
    }

    @Nullable
    String extractSubjectCvValue(final List<@NonNull SubjectMatchType> subjectMatches,
                               final String attributeId) {
        return subjectMatches.stream()
                .filter(subjectMatch -> subjectMatch.getSubjectAttributeDesignator() != null)
                .filter(subjectMatch -> attributeId.equals(subjectMatch.getSubjectAttributeDesignator().getAttributeId()))
                .findAny()
                .map(SubjectMatchType::getAttributeValue)
                .flatMap(AttributeValueType::getCvContent)
                .map(CV::getCode)
                .orElse(null);
    }

    List<@NonNull ChPolicySetInterface> extractReferencedPolicySets(final PolicySetType policySet) {
        return policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .filter(element -> _PolicySetIdReference_QNAME.equals(element.getName()))
                .map(JAXBElement::getValue)
                .filter(IdReferenceType.class::isInstance)
                .map(IdReferenceType.class::cast)
                .map(IdReferenceType::getValue)
                .filter(Objects::nonNull)
                .map(id -> this.knownPolicySets.stream()
                        .filter(ps -> ps.getId().equals(id))
                        .findAny()
                        .orElseThrow(() -> new InvalidSwissAppcContentException(String.format("The referenced policy " +
                                "set '%s' is unknown", id)))
                )
                .toList();
    }

    List<@NonNull ChPolicyInterface> extractReferencedPolicies(final PolicySetType policySet) {
        return policySet.getPolicySetOrPolicyOrPolicySetIdReference().stream()
                .filter(element -> _PolicyIdReference_QNAME.equals(element.getName()))
                .map(JAXBElement::getValue)
                .filter(IdReferenceType.class::isInstance)
                .map(IdReferenceType.class::cast)
                .map(IdReferenceType::getValue)
                .filter(Objects::nonNull)
                .map(id -> this.knownPolicies.stream()
                        .filter(ps -> ps.getId().equals(id))
                        .findAny()
                        .orElseThrow(() -> new InvalidSwissAppcContentException(String.format("The referenced policy " +
                                "'%s' is unknown", id)))
                )
                .toList();
    }
}
