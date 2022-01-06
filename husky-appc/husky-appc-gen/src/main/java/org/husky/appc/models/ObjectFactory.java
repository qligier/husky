/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.checkerframework.framework.qual.TypeUseLocation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * org.husky.appc.models package.
 * <p>An ObjectFactory allows you to programmatically
 * construct new instances of the Java representation for XML content. The Java representation of XML content can
 * consist of schema derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
@DefaultQualifier(value = NonNull.class, locations = {TypeUseLocation.PARAMETER, TypeUseLocation.RETURN,
        TypeUseLocation.FIELD})
public class ObjectFactory {

    private static final String OASIS_OS_NS = "urn:oasis:names:tc:xacml:2.0:policy:schema:os";
    private static final QName _Subjects_QNAME = new QName(OASIS_OS_NS, "Subjects");
    private static final QName _InstanceIdentifier_QNAME = new QName("urn:hl7-org:v3", "InstanceIdentifier");
    private static final QName _PolicyDefaults_QNAME = new QName(OASIS_OS_NS, "PolicyDefaults");
    private static final QName _SubjectMatch_QNAME = new QName(OASIS_OS_NS, "SubjectMatch");
    private static final QName _RuleCombinerParameters_QNAME = new QName(OASIS_OS_NS, "RuleCombinerParameters");
    private static final QName _Function_QNAME = new QName(OASIS_OS_NS, "Function");
    private static final QName _Target_QNAME = new QName(OASIS_OS_NS, "Target");
    private static final QName _CombinerParameter_QNAME = new QName(OASIS_OS_NS, "CombinerParameter");
    private static final QName _PolicySet_QNAME = new QName(OASIS_OS_NS, "PolicySet");
    private static final QName _ResourceMatch_QNAME = new QName(OASIS_OS_NS, "ResourceMatch");
    private static final QName _PolicyIdReference_QNAME = new QName(OASIS_OS_NS, "PolicyIdReference");
    private static final QName _PolicySetCombinerParameters_QNAME = new QName(OASIS_OS_NS, "PolicySetCombinerParameters");
    private static final QName _CodedValue_QNAME = new QName("urn:hl7-org:v3", "CodedValue");
    private static final QName _VariableDefinition_QNAME = new QName(OASIS_OS_NS, "VariableDefinition");
    private static final QName _Obligation_QNAME = new QName(OASIS_OS_NS, "Obligation");
    private static final QName _ActionMatch_QNAME = new QName(OASIS_OS_NS, "ActionMatch");
    private static final QName _Apply_QNAME = new QName(OASIS_OS_NS, "Apply");
    private static final QName _PolicySetDefaults_QNAME = new QName(OASIS_OS_NS, "PolicySetDefaults");
    private static final QName _PolicyCombinerParameters_QNAME = new QName(OASIS_OS_NS, "PolicyCombinerParameters");
    private static final QName _Policy_QNAME = new QName(OASIS_OS_NS, "Policy");
    private static final QName _Description_QNAME = new QName(OASIS_OS_NS, "Description");
    private static final QName _Action_QNAME = new QName(OASIS_OS_NS, "Action");
    private static final QName _AttributeValue_QNAME = new QName(OASIS_OS_NS, "AttributeValue");
    private static final QName _XPathVersion_QNAME = new QName(OASIS_OS_NS, "XPathVersion");
    private static final QName _Environment_QNAME = new QName(OASIS_OS_NS, "Environment");
    private static final QName _Rule_QNAME = new QName(OASIS_OS_NS, "Rule");
    private static final QName _Resources_QNAME = new QName(OASIS_OS_NS, "Resources");
    private static final QName _EnvironmentMatch_QNAME = new QName(OASIS_OS_NS, "EnvironmentMatch");
    private static final QName _Expression_QNAME = new QName(OASIS_OS_NS, "Expression");
    private static final QName _ActionAttributeDesignator_QNAME = new QName(OASIS_OS_NS, "ActionAttributeDesignator");
    private static final QName _CombinerParameters_QNAME = new QName(OASIS_OS_NS, "CombinerParameters");
    private static final QName _PolicySetIdReference_QNAME = new QName(OASIS_OS_NS, "PolicySetIdReference");
    private static final QName _Environments_QNAME = new QName(OASIS_OS_NS, "Environments");
    private static final QName _EnvironmentAttributeDesignator_QNAME = new QName(OASIS_OS_NS, "EnvironmentAttributeDesignator");
    private static final QName _Obligations_QNAME = new QName(OASIS_OS_NS, "Obligations");
    private static final QName _ResourceAttributeDesignator_QNAME = new QName(OASIS_OS_NS, "ResourceAttributeDesignator");
    private static final QName _Subject_QNAME = new QName(OASIS_OS_NS, "Subject");
    private static final QName _AttributeSelector_QNAME = new QName(OASIS_OS_NS, "AttributeSelector");
    private static final QName _Resource_QNAME = new QName(OASIS_OS_NS, "Resource");
    private static final QName _SubjectAttributeDesignator_QNAME = new QName(OASIS_OS_NS, "SubjectAttributeDesignator");
    private static final QName _Actions_QNAME = new QName(OASIS_OS_NS, "Actions");
    private static final QName _VariableReference_QNAME = new QName(OASIS_OS_NS, "VariableReference");
    private static final QName _Condition_QNAME = new QName(OASIS_OS_NS, "Condition");
    private static final QName _AttributeAssignment_QNAME = new QName(OASIS_OS_NS, "AttributeAssignment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * org.husky.appc.models
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PolicyCombinerParametersType }
     */
    public PolicyCombinerParametersType createPolicyCombinerParametersType() {
        return new PolicyCombinerParametersType();
    }

    /**
     * Create an instance of {@link PolicyType }
     */
    public PolicyType createPolicyType() {
        return new PolicyType();
    }

    /**
     * Create an instance of {@link DefaultsType }
     */
    public DefaultsType createDefaultsType() {
        return new DefaultsType();
    }

    /**
     * Create an instance of {@link ActionType }
     */
    public ActionType createActionType() {
        return new ActionType();
    }

    /**
     * Create an instance of {@link AttributeValueType }
     */
    public AttributeValueType createAttributeValueType() {
        return new AttributeValueType();
    }

    /**
     * Create an instance of {@link VariableDefinitionType }
     */
    public VariableDefinitionType createVariableDefinitionType() {
        return new VariableDefinitionType();
    }

    /**
     * Create an instance of {@link ObligationType }
     */
    public ObligationType createObligationType() {
        return new ObligationType();
    }

    /**
     * Create an instance of {@link PolicySetCombinerParametersType }
     */
    public PolicySetCombinerParametersType createPolicySetCombinerParametersType() {
        return new PolicySetCombinerParametersType();
    }

    /**
     * Create an instance of {@link ActionMatchType }
     */
    public ActionMatchType createActionMatchType() {
        return new ActionMatchType();
    }

    /**
     * Create an instance of {@link ApplyType }
     */
    public ApplyType createApplyType() {
        return new ApplyType();
    }

    /**
     * Create an instance of {@link IdReferenceType }
     */
    public IdReferenceType createIdReferenceType() {
        return new IdReferenceType();
    }

    /**
     * Create an instance of {@link ResourceMatchType }
     */
    public ResourceMatchType createResourceMatchType() {
        return new ResourceMatchType();
    }

    /**
     * Create an instance of {@link CombinerParameterType }
     */
    public CombinerParameterType createCombinerParameterType() {
        return new CombinerParameterType();
    }

    /**
     * Create an instance of {@link PolicySetType }
     */
    public PolicySetType createPolicySetType() {
        return new PolicySetType();
    }

    /**
     * Create an instance of {@link RuleCombinerParametersType }
     */
    public RuleCombinerParametersType createRuleCombinerParametersType() {
        return new RuleCombinerParametersType();
    }

    /**
     * Create an instance of {@link FunctionType }
     */
    public FunctionType createFunctionType() {
        return new FunctionType();
    }

    /**
     * Create an instance of {@link TargetType }
     */
    public TargetType createTargetType() {
        return new TargetType();
    }

    /**
     * Create an instance of {@link SubjectMatchType }
     */
    public SubjectMatchType createSubjectMatchType() {
        return new SubjectMatchType();
    }

    /**
     * Create an instance of {@link SubjectsType }
     */
    public SubjectsType createSubjectsType() {
        return new SubjectsType();
    }

    /**
     * Create an instance of {@link ConditionType }
     */
    public ConditionType createConditionType() {
        return new ConditionType();
    }

    /**
     * Create an instance of {@link AttributeAssignmentType }
     */
    public AttributeAssignmentType createAttributeAssignmentType() {
        return new AttributeAssignmentType();
    }

    /**
     * Create an instance of {@link VariableReferenceType }
     */
    public VariableReferenceType createVariableReferenceType() {
        return new VariableReferenceType();
    }

    /**
     * Create an instance of {@link ActionsType }
     */
    public ActionsType createActionsType() {
        return new ActionsType();
    }

    /**
     * Create an instance of {@link ResourceType }
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link SubjectAttributeDesignatorType }
     */
    public SubjectAttributeDesignatorType createSubjectAttributeDesignatorType() {
        return new SubjectAttributeDesignatorType();
    }

    /**
     * Create an instance of {@link AttributeDesignatorType }
     */
    public AttributeDesignatorType createAttributeDesignatorType() {
        return new AttributeDesignatorType();
    }

    /**
     * Create an instance of {@link SubjectType }
     */
    public SubjectType createSubjectType() {
        return new SubjectType();
    }

    /**
     * Create an instance of {@link AttributeSelectorType }
     */
    public AttributeSelectorType createAttributeSelectorType() {
        return new AttributeSelectorType();
    }

    /**
     * Create an instance of {@link EnvironmentsType }
     */
    public EnvironmentsType createEnvironmentsType() {
        return new EnvironmentsType();
    }

    /**
     * Create an instance of {@link ObligationsType }
     */
    public ObligationsType createObligationsType() {
        return new ObligationsType();
    }

    /**
     * Create an instance of {@link CombinerParametersType }
     */
    public CombinerParametersType createCombinerParametersType() {
        return new CombinerParametersType();
    }

    /**
     * Create an instance of {@link EnvironmentType }
     */
    public EnvironmentType createEnvironmentType() {
        return new EnvironmentType();
    }

    /**
     * Create an instance of {@link RuleType }
     */
    public RuleType createRuleType() {
        return new RuleType();
    }

    /**
     * Create an instance of {@link ResourcesType }
     */
    public ResourcesType createResourcesType() {
        return new ResourcesType();
    }

    /**
     * Create an instance of {@link EnvironmentMatchType }
     */
    public EnvironmentMatchType createEnvironmentMatchType() {
        return new EnvironmentMatchType();
    }

    /**
     * Create an instance of {@link CV }
     */
    public CV createCV() {
        return new CV();
    }

    /**
     * Create an instance of {@link II }
     */
    public II createII() {
        return new II();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Subjects")
    public JAXBElement<SubjectsType> createSubjects(SubjectsType value) {
        return new JAXBElement<>(_Subjects_QNAME, SubjectsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link II }{@code >}}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "InstanceIdentifier")
    public JAXBElement<II> createInstanceIdentifier(II value) {
        return new JAXBElement<>(_InstanceIdentifier_QNAME, II.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefaultsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicyDefaults")
    public JAXBElement<DefaultsType> createPolicyDefaults(DefaultsType value) {
        return new JAXBElement<>(_PolicyDefaults_QNAME, DefaultsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectMatchType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "SubjectMatch")
    public JAXBElement<SubjectMatchType> createSubjectMatch(SubjectMatchType value) {
        return new JAXBElement<>(_SubjectMatch_QNAME, SubjectMatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RuleCombinerParametersType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "RuleCombinerParameters")
    public JAXBElement<RuleCombinerParametersType> createRuleCombinerParameters(RuleCombinerParametersType value) {
        return new JAXBElement<>(_RuleCombinerParameters_QNAME, RuleCombinerParametersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FunctionType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Function", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<FunctionType> createFunction(FunctionType value) {
        return new JAXBElement<>(_Function_QNAME, FunctionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TargetType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Target")
    public JAXBElement<TargetType> createTarget(TargetType value) {
        return new JAXBElement<>(_Target_QNAME, TargetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CombinerParameterType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "CombinerParameter")
    public JAXBElement<CombinerParameterType> createCombinerParameter(CombinerParameterType value) {
        return new JAXBElement<>(_CombinerParameter_QNAME, CombinerParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicySetType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicySet")
    public JAXBElement<PolicySetType> createPolicySet(PolicySetType value) {
        return new JAXBElement<>(_PolicySet_QNAME, PolicySetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceMatchType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "ResourceMatch")
    public JAXBElement<ResourceMatchType> createResourceMatch(ResourceMatchType value) {
        return new JAXBElement<>(_ResourceMatch_QNAME, ResourceMatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicyIdReference")
    public JAXBElement<IdReferenceType> createPolicyIdReference(IdReferenceType value) {
        return new JAXBElement<>(_PolicyIdReference_QNAME, IdReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicySetCombinerParametersType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicySetCombinerParameters")
    public JAXBElement<PolicySetCombinerParametersType> createPolicySetCombinerParameters(PolicySetCombinerParametersType value) {
        return new JAXBElement<>(_PolicySetCombinerParameters_QNAME, PolicySetCombinerParametersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CV }{@code >}}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "CodedValue")
    public JAXBElement<CV> createCodedValue(CV value) {
        return new JAXBElement<>(_CodedValue_QNAME, CV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VariableDefinitionType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "VariableDefinition")
    public JAXBElement<VariableDefinitionType> createVariableDefinition(VariableDefinitionType value) {
        return new JAXBElement<>(_VariableDefinition_QNAME, VariableDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Obligation")
    public JAXBElement<ObligationType> createObligation(ObligationType value) {
        return new JAXBElement<>(_Obligation_QNAME, ObligationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionMatchType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "ActionMatch")
    public JAXBElement<ActionMatchType> createActionMatch(ActionMatchType value) {
        return new JAXBElement<>(_ActionMatch_QNAME, ActionMatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplyType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Apply", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<ApplyType> createApply(ApplyType value) {
        return new JAXBElement<>(_Apply_QNAME, ApplyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefaultsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicySetDefaults")
    public JAXBElement<DefaultsType> createPolicySetDefaults(DefaultsType value) {
        return new JAXBElement<>(_PolicySetDefaults_QNAME, DefaultsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyCombinerParametersType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicyCombinerParameters")
    public JAXBElement<PolicyCombinerParametersType> createPolicyCombinerParameters(PolicyCombinerParametersType value) {
        return new JAXBElement<>(_PolicyCombinerParameters_QNAME, PolicyCombinerParametersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Policy")
    public JAXBElement<PolicyType> createPolicy(PolicyType value) {
        return new JAXBElement<>(_Policy_QNAME, PolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Action")
    public JAXBElement<ActionType> createAction(ActionType value) {
        return new JAXBElement<>(_Action_QNAME, ActionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeValueType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "AttributeValue", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<AttributeValueType> createAttributeValue(AttributeValueType value) {
        return new JAXBElement<>(_AttributeValue_QNAME, AttributeValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "XPathVersion")
    public JAXBElement<String> createXPathVersion(String value) {
        return new JAXBElement<>(_XPathVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Environment")
    public JAXBElement<EnvironmentType> createEnvironment(EnvironmentType value) {
        return new JAXBElement<>(_Environment_QNAME, EnvironmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RuleType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Rule")
    public JAXBElement<RuleType> createRule(RuleType value) {
        return new JAXBElement<>(_Rule_QNAME, RuleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourcesType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Resources")
    public JAXBElement<ResourcesType> createResources(ResourcesType value) {
        return new JAXBElement<>(_Resources_QNAME, ResourcesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentMatchType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "EnvironmentMatch")
    public JAXBElement<EnvironmentMatchType> createEnvironmentMatch(EnvironmentMatchType value) {
        return new JAXBElement<>(_EnvironmentMatch_QNAME, EnvironmentMatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Expression")
    public JAXBElement<ExpressionType> createExpression(ExpressionType value) {
        return new JAXBElement<>(_Expression_QNAME, ExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "ActionAttributeDesignator", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<AttributeDesignatorType> createActionAttributeDesignator(AttributeDesignatorType value) {
        return new JAXBElement<>(_ActionAttributeDesignator_QNAME, AttributeDesignatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CombinerParametersType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "CombinerParameters")
    public JAXBElement<CombinerParametersType> createCombinerParameters(CombinerParametersType value) {
        return new JAXBElement<>(_CombinerParameters_QNAME, CombinerParametersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "PolicySetIdReference")
    public JAXBElement<IdReferenceType> createPolicySetIdReference(IdReferenceType value) {
        return new JAXBElement<>(_PolicySetIdReference_QNAME, IdReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Environments")
    public JAXBElement<EnvironmentsType> createEnvironments(EnvironmentsType value) {
        return new JAXBElement<>(_Environments_QNAME, EnvironmentsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "EnvironmentAttributeDesignator", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<AttributeDesignatorType> createEnvironmentAttributeDesignator(AttributeDesignatorType value) {
        return new JAXBElement<>(_EnvironmentAttributeDesignator_QNAME, AttributeDesignatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Obligations")
    public JAXBElement<ObligationsType> createObligations(ObligationsType value) {
        return new JAXBElement<>(_Obligations_QNAME, ObligationsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "ResourceAttributeDesignator", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<AttributeDesignatorType> createResourceAttributeDesignator(AttributeDesignatorType value) {
        return new JAXBElement<>(_ResourceAttributeDesignator_QNAME, AttributeDesignatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Subject")
    public JAXBElement<SubjectType> createSubject(SubjectType value) {
        return new JAXBElement<>(_Subject_QNAME, SubjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeSelectorType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "AttributeSelector", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<AttributeSelectorType> createAttributeSelector(AttributeSelectorType value) {
        return new JAXBElement<>(_AttributeSelector_QNAME, AttributeSelectorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Resource")
    public JAXBElement<ResourceType> createResource(ResourceType value) {
        return new JAXBElement<>(_Resource_QNAME, ResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAttributeDesignatorType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "SubjectAttributeDesignator", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<SubjectAttributeDesignatorType> createSubjectAttributeDesignator(SubjectAttributeDesignatorType value) {
        return new JAXBElement<>(_SubjectAttributeDesignator_QNAME, SubjectAttributeDesignatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionsType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Actions")
    public JAXBElement<ActionsType> createActions(ActionsType value) {
        return new JAXBElement<>(_Actions_QNAME, ActionsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "VariableReference", substitutionHeadNamespace = OASIS_OS_NS, substitutionHeadName = "Expression")
    public JAXBElement<VariableReferenceType> createVariableReference(VariableReferenceType value) {
        return new JAXBElement<>(_VariableReference_QNAME, VariableReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConditionType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "Condition")
    public JAXBElement<ConditionType> createCondition(ConditionType value) {
        return new JAXBElement<>(_Condition_QNAME, ConditionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeAssignmentType }{@code >}}
     */
    @XmlElementDecl(namespace = OASIS_OS_NS, name = "AttributeAssignment")
    public JAXBElement<AttributeAssignmentType> createAttributeAssignment(AttributeAssignmentType value) {
        return new JAXBElement<>(_AttributeAssignment_QNAME, AttributeAssignmentType.class, null, value);
    }

}
