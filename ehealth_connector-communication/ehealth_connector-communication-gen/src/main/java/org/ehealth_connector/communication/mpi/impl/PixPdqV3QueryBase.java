package org.ehealth_connector.communication.mpi.impl;

import java.io.Serializable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.camel.CamelContext;
import org.ehealth_connector.common.communication.AffinityDomain;
import org.ehealth_connector.common.enums.TelecomAddressUse;
import org.ehealth_connector.common.utils.DateUtil;
import org.ehealth_connector.communication.CamelService;
import org.ehealth_connector.communication.mpi.impl.pix.V3PixSourceMessageHelper;
import org.ehealth_connector.communication.utils.PixPdqV3Utils;
import org.ehealth_connector.fhir.structures.gen.FhirCommon;
import org.ehealth_connector.fhir.structures.gen.FhirPatient;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Address.AddressUse;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.Organization;
import org.hl7.fhir.dstu3.model.Patient.PatientCommunicationComponent;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ihe.gazelle.hl7v3.coctmt030007UV.COCTMT030007UVPerson;
import net.ihe.gazelle.hl7v3.coctmt150003UV03.COCTMT150003UV03ContactParty;
import net.ihe.gazelle.hl7v3.datatypes.AD;
import net.ihe.gazelle.hl7v3.datatypes.AdxpStreetAddressLine;
import net.ihe.gazelle.hl7v3.datatypes.CE;
import net.ihe.gazelle.hl7v3.datatypes.EN;
import net.ihe.gazelle.hl7v3.datatypes.EnFamily;
import net.ihe.gazelle.hl7v3.datatypes.EnGiven;
import net.ihe.gazelle.hl7v3.datatypes.EnPrefix;
import net.ihe.gazelle.hl7v3.datatypes.EnSuffix;
import net.ihe.gazelle.hl7v3.datatypes.II;
import net.ihe.gazelle.hl7v3.datatypes.ON;
import net.ihe.gazelle.hl7v3.datatypes.PN;
import net.ihe.gazelle.hl7v3.datatypes.TEL;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02BirthPlace;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02Citizen;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02Employee;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02LanguageCommunication;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02Nation;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02Patient;
import net.ihe.gazelle.hl7v3.prpamt201310UV02.PRPAMT201310UV02PersonalRelationship;
import net.ihe.gazelle.hl7v3.voc.HomeAddressUse;
import net.ihe.gazelle.hl7v3.voc.WorkPlaceAddressUse;

public class PixPdqV3QueryBase extends CamelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PixPdqV3QueryBase.class.getName());

	/** The domain to return namespace (optional). */
	protected String domainToReturnNamespace;

	/**
	 * The oid of the domain to return ids (optional, convenience if not other id
	 * for other domain oids are necessary) .
	 */
	protected String domainToReturnOid;

	/** The home community namespace (optional). */
	protected String homeCommunityNamespace;

	/** The home community oid. */
	protected String homeCommunityOid;

	/** oid of id domains which are not medical ids */
	protected Set<String> otherOidIds;

	/** The pdq consumer uri endpoint. */
	protected URI pdqConsumerUri;

	/** The pix query uri endpoint. */
	protected URI pixQueryUri;

	/** The pix source uri endpoint. */
	protected URI pixSourceUri;

	/** The receiver application oid. */
	protected String receiverApplicationOid;

	/** The receiver facility oid. */
	protected String receiverFacilityOid;

	/** The sender application oid. */
	protected String senderApplicationOid;

	/** The sender facility oid. */
	protected String senderFacilityOid;

	public PixPdqV3QueryBase() {

	}

	public PixPdqV3QueryBase(AffinityDomain affinityDomain, String homeCommunityOid, String homeCommunityNamespace,
			String domainToReturnOid, String domainToReturnNamespace, CamelContext context) {
		var pixQuery = affinityDomain.getPixDestination();
		var pdqQuery = affinityDomain.getPdqDestination();

		this.pixQueryUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pixSourceUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pdqConsumerUri = pdqQuery != null ? pdqQuery.getUri() : null;
		this.senderApplicationOid = pixQuery != null ? pixQuery.getSenderApplicationOid() : null;
		this.senderFacilityOid = pixQuery != null ? pixQuery.getSenderFacilityOid() : null;
		this.receiverApplicationOid = pixQuery != null ? pixQuery.getReceiverApplicationOid() : null;
		this.receiverFacilityOid = pixQuery != null ? pixQuery.getReceiverFacilityOid() : null;
		this.homeCommunityOid = homeCommunityOid;
		this.homeCommunityNamespace = homeCommunityNamespace;
		this.domainToReturnOid = domainToReturnOid;
		this.domainToReturnNamespace = domainToReturnNamespace;
		this.otherOidIds = affinityDomain.getOtherIdsOidSet();
		this.setCamelContext(context);
	}

	public PixPdqV3QueryBase(AffinityDomain affinityDomain, String homeCommunityOid, CamelContext context) {
		var pixQuery = affinityDomain.getPixDestination();
		var pdqQuery = affinityDomain.getPdqDestination();

		this.pixQueryUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pixSourceUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pdqConsumerUri = pdqQuery != null ? pdqQuery.getUri() : null;
		this.senderApplicationOid = pixQuery != null ? pixQuery.getSenderApplicationOid() : null;
		this.senderFacilityOid = pixQuery != null ? pixQuery.getSenderFacilityOid() : null;
		this.receiverApplicationOid = pixQuery != null ? pixQuery.getReceiverApplicationOid() : null;
		this.receiverFacilityOid = pixQuery != null ? pixQuery.getReceiverFacilityOid() : null;
		this.homeCommunityOid = homeCommunityOid;
		this.otherOidIds = affinityDomain.getOtherIdsOidSet();
		this.setCamelContext(context);
	}

	public void setAffinityDomain(AffinityDomain affinityDomain) {
		var pixQuery = affinityDomain.getPixDestination();
		var pdqQuery = affinityDomain.getPdqDestination();

		this.pixQueryUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pixSourceUri = pixQuery != null ? pixQuery.getUri() : null;
		this.pdqConsumerUri = pdqQuery != null ? pdqQuery.getUri() : null;
		this.senderApplicationOid = pixQuery != null ? pixQuery.getSenderApplicationOid() : null;
		this.senderFacilityOid = pixQuery != null ? pixQuery.getSenderFacilityOid() : null;
		this.receiverApplicationOid = pixQuery != null ? pixQuery.getReceiverApplicationOid() : null;
		this.receiverFacilityOid = pixQuery != null ? pixQuery.getReceiverFacilityOid() : null;
	}

	/**
	 * adds the demographic data to the pix queries, can be overloaded if additional
	 * information of the patient needs to be providied for the mpi.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 add message
	 */
	protected void addDemographicData(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if (v3PixSourceMessage == null) {
			return;
		}
		setScopingOrganization(patient, v3PixSourceMessage);
		addPatientIds(patient, v3PixSourceMessage);
		addPatientName(patient, v3PixSourceMessage);
		setPatientBirthTime(patient, v3PixSourceMessage);
		setPatientGender(patient, v3PixSourceMessage);
		addPatientAddresses(patient, v3PixSourceMessage);
		addPatientTelecoms(patient, v3PixSourceMessage);
		addLanguageCommunications(patient, v3PixSourceMessage);
		setPatientMaritalStatus(patient, v3PixSourceMessage);
		setDeceased(patient, v3PixSourceMessage);
		setMultipeBirth(patient, v3PixSourceMessage);
		setPatientMothersMaidenName(patient, v3PixSourceMessage);
		setBirthPlace(patient, v3PixSourceMessage);
		setPatientReligiousAffiliation(patient, v3PixSourceMessage);
		setNation(patient, v3PixSourceMessage);
		setEmployee(patient, v3PixSourceMessage);
	}

	/**
	 * adds the demographic data from the pdq query to the fhir patient, can be
	 * overloaded if additional information of the patient needs to be providied for
	 * the mpi.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addDemographicData(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if (pdqPatient == null) {
			return;
		}
		setScopingOrganization(pdqPatient, patient);
		addPatientIds(pdqPatient, patient);
		addPatientName(pdqPatient, patient);
		setPatientBirthTime(pdqPatient, patient);
		setPatientGender(pdqPatient, patient);
		addPatientAddresses(pdqPatient, patient);
		addPatientTelecoms(pdqPatient, patient);
		addLanguageCommunications(pdqPatient, patient);
		setPatientMaritalStatus(pdqPatient, patient);
		setDeceased(pdqPatient, patient);
		setMultipeBirth(pdqPatient, patient);
		setPatientMothersMaidenName(pdqPatient, patient);
		setBirthPlace(pdqPatient, patient);
		setPatientReligiousAffiliation(pdqPatient, patient);
		setNation(pdqPatient, patient);
		setEmployee(pdqPatient, patient);
	}

	/**
	 * Adds the language communications from the patient to the pix message. FHIR
	 * language code is based on http://tools.ietf.org/html/bcp47, HL7V3 makes no
	 * requirements
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void addLanguageCommunications(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if (!patient.getCommunication().isEmpty()) {
			for (final PatientCommunicationComponent communication : patient.getCommunication()) {
				v3PixSourceMessage.addLanguageCommunication(communication.getLanguage().getText());
			}
		}
	}

	/**
	 * Adds the language communications from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addLanguageCommunications(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null)
				&& (pdqPatient.getPatientPerson().getLanguageCommunication() != null)) {
			for (final PRPAMT201310UV02LanguageCommunication languageCommunication : pdqPatient.getPatientPerson()
					.getLanguageCommunication()) {
				if ((languageCommunication.getLanguageCode() != null)
						&& (languageCommunication.getLanguageCode().getCode() != null)) {
					final var communication = new PatientCommunicationComponent();
					final var language = new CodeableConcept();
					language.setText(languageCommunication.getLanguageCode().getCode());
					communication.setLanguage(language);
					patient.getCommunication().add(communication);

				}
			}
		}
	}

	/**
	 * Adds the patient addresses from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void addPatientAddresses(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if (!patient.getAddress().isEmpty()) {
			for (final Address address : patient.getAddress()) {

				List<String> addressLines = new ArrayList<>();
				for (StringType addressLine : address.getLine()) {
					addressLines.add(addressLine.getValueAsString());
				}

				String adressOtherDesignation = null;

				String addressType = null;
				if ((address.getUseElement() != null) && (address.getUseElement().getValue() != null)) {
					switch (address.getUseElement().getValue()) {
					case HOME:
						addressType = "H";
						break;
					case WORK:
						addressType = "WP";
						break;
					case TEMP:
						addressType = "TMP";
						break;
					case OLD:
						addressType = "OLD";
						break;
					default:
						break;
					}
				}
				v3PixSourceMessage.addPatientAddress(addressLines, address.getCity(), null, address.getState(),
						address.getCountry(), address.getPostalCode(), adressOtherDesignation, addressType);
			}
		}
	}

	/**
	 * Adds the patient addresses from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addPatientAddresses(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getAddr() != null)) {
			for (final AD ad : pdqPatient.getPatientPerson().getAddr()) {
				patient.getAddress().add(getAddressFromAD(ad));
			}
		}
	}

	/**
	 * Adds the patient ids from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void addPatientIds(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		for (final Identifier identifier : patient.getIdentifier()) {
			if ((identifier.getSystem().length() > 8)) {
				final String oid = FhirCommon.removeUrnOidPrefix(identifier.getSystem());
				if (this.otherOidIds.contains(oid)) {
					v3PixSourceMessage.addPatientOtherID(identifier.getValue(), oid);
				} else {
					if (this.homeCommunityOid != null && this.homeCommunityOid.equals(oid)) {
						v3PixSourceMessage.addPatientID(identifier.getValue(), this.homeCommunityOid,
								this.homeCommunityNamespace);
					} else {
						v3PixSourceMessage.addPatientID(identifier.getValue(), oid, "");
					}
				}
			}
		}
	}

	/**
	 * Adds the patient ids from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addPatientIds(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if (pdqPatient.getId() != null) {
			for (final II patientId : pdqPatient.getId()) {
				final var identifier = new Identifier();
				identifier.setSystem(FhirCommon.addUrnOid(patientId.getRoot()));
				identifier.setValue(patientId.getExtension());
				patient.getIdentifier().add(identifier);
			}
		}

		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getAsOtherIDs() != null)) {
			for (final var asOtherId : pdqPatient.getPatientPerson().getAsOtherIDs()) {
				if ((asOtherId.getId() != null) && (!asOtherId.getId().isEmpty())) {
					final II patientId = asOtherId.getId().get(0);
					if (patientId != null) {
						final var identifier = new Identifier();
						identifier.setSystem(FhirCommon.addUrnOid(patientId.getRoot()));
						identifier.setValue(patientId.getExtension());
						patient.getIdentifier().add(identifier);
					}
				}
			}
		}
	}

	/**
	 * Adds the patient name from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void addPatientName(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		// Name
		final String familyName = patient.getName().get(0).getFamily();
		final var givenName = patient.getName().get(0).getGivenAsSingleString();
		final var otherName = ""; // other is resolved into given in
									// addPatientName
		// below, we have that already with above lines
		final var prefixName = patient.getName().get(0).getPrefixAsSingleString();
		final var suffixName = patient.getName().get(0).getSuffixAsSingleString();
		v3PixSourceMessage.addPatientName(familyName, givenName, otherName, prefixName, suffixName);
	}

	/**
	 * Adds the patient name from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addPatientName(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		final List<PN> pns = pdqPatient.getPatientPerson().getName();
		for (var i = 0; i < pns.size(); ++i) {
			final var pn = pns.get(i);
			final var humanNameType = new HumanName();
			if (pn.getGiven() != null) {
				for (final EnGiven given : pn.getGiven()) {
					humanNameType.addGiven(getMixedValue(given.getMixed()));
				}
			}
			if (pn.getFamily() != null) {
				for (final EnFamily family : pn.getFamily()) {
					humanNameType.setFamily(getMixedValue(family.getMixed()));
				}
			}
			if (pn.getPrefix() != null) {
				for (final EnPrefix prefix : pn.getPrefix()) {
					humanNameType.addPrefix(getMixedValue(prefix.getMixed()));
				}
			}
			if (pn.getSuffix() != null) {
				for (final EnSuffix suffix : pn.getSuffix()) {
					humanNameType.addPrefix(getMixedValue(suffix.getMixed()));
				}
			}
			patient.getName().add(humanNameType);
		}
	}

	/**
	 * Adds the patient telecoms from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void addPatientTelecoms(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		// telecommunication addresses (only phone and email will be added to
		// source)
		if (patient.getTelecom() != null && !patient.getTelecom().isEmpty()) {
			for (final ContactPoint contactPoint : patient.getTelecom()) {
				// system I 0..1 code phone | fax | email | url
				// use M 0..1 code home | work | temp | old | mobile - purpose
				// of this contact point
				var telecomValue = "";
				var useValue = "";

				var system = "";
				var use = "";
				var value = "NULL";

				if (contactPoint.getSystem() != null)
					system = contactPoint.getSystem().toString().toLowerCase();
				if (contactPoint.getUse() != null)
					use = contactPoint.getUse().toString().toLowerCase();
				if (contactPoint.getValue() != null)
					value = contactPoint.getValue();

				if ("phone".equals(system)) {
					telecomValue = "tel:" + value;
					if ("home".equals(use)) {
						useValue = TelecomAddressUse.PRIVATE.getCodeValue();
					}
					if ("work".equals(use)) {
						useValue = TelecomAddressUse.BUSINESS.getCodeValue();
					}
					if ("mobile".equals(use)) {
						// ignore, because MC will produce an
						// java.lang.IllegalArgumentException: Invalid value:
						// 'org.ehealth_connector.communication.mpi.impl.V3PixSourceMessageHelper$1@7528089c'
						// for datatype :TelecommunicationAddressUse
					}
				}
				if ("email".equals(system)) {
					telecomValue = "mailto:" + value;
					if ("home".equals(use)) {
						useValue = TelecomAddressUse.PRIVATE.getCodeValue();
					}
					if ("work".equals(use)) {
						useValue = TelecomAddressUse.BUSINESS.getCodeValue();
					}
				}

				v3PixSourceMessage.addPatientTelecom(telecomValue, useValue);
			}
		}
	}

	/**
	 * Adds the patient telecoms from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void addPatientTelecoms(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getTelecom() != null)
				&& (!pdqPatient.getPatientPerson().getTelecom().isEmpty())) {
			for (final TEL tel : pdqPatient.getPatientPerson().getTelecom()) {
				final var contactPoint = new ContactPoint();
				if ((tel.getValue() != null) && tel.getValue().startsWith("tel:")) {
					contactPoint.setValue(tel.getValue().substring(4));
					contactPoint.setSystem(ContactPointSystem.PHONE);
					if (tel.getUse().contains(WorkPlaceAddressUse.WP.value())) {
						contactPoint.setUse(ContactPointUse.WORK);
					} else if (tel.getUse().contains(HomeAddressUse.H.value())
							|| tel.getUse().contains(HomeAddressUse.HP.value())) {
						contactPoint.setUse(ContactPointUse.HOME);
					} else if ((!tel.getUse().isEmpty()) && "MC".equals(tel.getUse())) {
						contactPoint.setUse(ContactPointUse.MOBILE);
					}
					patient.getTelecom().add(contactPoint);
				}
				if ((tel.getValue() != null) && tel.getValue().startsWith("mailto:")) {
					contactPoint.setValue(tel.getValue().substring(7));
					contactPoint.setSystem(ContactPointSystem.EMAIL);
					if (tel.getUse().contains(WorkPlaceAddressUse.WP.value())) {
						contactPoint.setUse(ContactPointUse.WORK);
					} else if (tel.getUse().contains(HomeAddressUse.H.value())
							|| tel.getUse().contains(HomeAddressUse.HP.value())) {
						contactPoint.setUse(ContactPointUse.HOME);
					}
					patient.getTelecom().add(contactPoint);
				}
			}

		}

	}

	/**
	 * Sets the scoping organization from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setScopingOrganization(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		// scoping organization set the scoping organization
		var organizationOid = "";
		var organizationName = "";
		var organizationTelecomValue = "NOTPROVIDED";

		final var organization = (Organization) patient.getManagingOrganization().getResource();

		if (organization != null) {
			if (organization.getIdentifier() != null && !organization.getIdentifier().isEmpty()) {
				final Identifier organizationId = organization.getIdentifier().get(0);
				if (organizationId.getSystem().startsWith(FhirCommon.OID_URN)) {
					organizationOid = FhirCommon.removeUrnOidPrefix(organizationId.getSystem());
				}
			}

			if (organization.getName() != null) {
				organizationName = organization.getName();
			}

			if (organization.getTelecom() != null && !organization.getTelecom().isEmpty()) {
				final var contactPoint = organization.getTelecomFirstRep();
				if (contactPoint != null) {
					organizationTelecomValue = contactPoint.getValue();
				}
			}
		}

		v3PixSourceMessage.setScopingOrganization(organizationOid, organizationName, organizationTelecomValue);
	}

	/**
	 * Sets the scoping organization from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setScopingOrganization(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if (pdqPatient.getProviderOrganization() != null) {
			final var organization = new Organization();
			patient.getManagingOrganization().setResource(organization);

			if ((pdqPatient.getProviderOrganization().getId() != null)
					&& (!pdqPatient.getProviderOrganization().getId().isEmpty())) {
				for (final II id : pdqPatient.getProviderOrganization().getId()) {
					final var identifier = new Identifier();
					identifier.setValue(FhirCommon.addUrnOid(id.getRoot()));
					organization.getIdentifier().add(identifier);
				}
			}

			final List<ON> ons = pdqPatient.getProviderOrganization().getName();
			if ((ons != null) && (!ons.isEmpty())) {
				organization.setName(getMixedValue(ons.get(0).getMixed()));
			}

			final List<COCTMT150003UV03ContactParty> contactParties = pdqPatient.getProviderOrganization()
					.getContactParty();
			if ((contactParties != null) && (!contactParties.isEmpty())) {
				final List<TEL> tels = contactParties.get(0).getTelecom();
				if ((tels != null) && (!tels.isEmpty())) {
					final var tel = tels.get(0);
					if ((tel.getValue() != null) && tel.getValue().startsWith("tel:")) {
						final var contactPoint = new ContactPoint();
						contactPoint.setValue(tel.getValue().substring(4));
						contactPoint.setSystem(ContactPointSystem.PHONE);
						organization.getTelecom().add(contactPoint);
					}
				}
			}
		}

	}

	/**
	 * Sets the birthplace from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setBirthPlace(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if (patient.getBirthPlace() != null) {
			final var fhirAddress = patient.getBirthPlace();
			String adressOtherDesignation = null;
			if (fhirAddress.getLine().size() > 1) {
				adressOtherDesignation = fhirAddress.getLine().get(1).getValueAsString();
			}
			final var patientAddress = PixPdqV3Utils.createAd(fhirAddress.getLine().get(0).getValue(),
					fhirAddress.getCity(), null, fhirAddress.getState(), fhirAddress.getCountry(),
					fhirAddress.getPostalCode(), adressOtherDesignation, null);
			v3PixSourceMessage.setPatientBirthPlace(patientAddress);
		}
	}

	/**
	 * Sets the birth place from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setBirthPlace(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getBirthPlace() != null)) {
			final PRPAMT201310UV02BirthPlace birthplace = pdqPatient.getPatientPerson().getBirthPlace();
			final var addr = birthplace.getAddr();
			if (addr != null) {
				patient.setBirthPlace(getAddressFromAD(addr));
			}
		}
	}

	/**
	 * sets the deceased status, either boolean or by datetime from the patient to
	 * the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setDeceased(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		final Type idDeceased = patient.getDeceased();
		if (idDeceased instanceof DateTimeType) {
			final DateTimeType deceased = (DateTimeType) idDeceased;
			if (deceased.getValue() != null) {
				final var dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				v3PixSourceMessage.setPatientDeceasedTime(dateFormat.format(deceased.getValue()));
				v3PixSourceMessage.setPatientDeceased(true);
			}
		}
		if (idDeceased instanceof BooleanType) {
			final BooleanType deceased = (BooleanType) idDeceased;
			if (deceased.getValue() != null) {
				v3PixSourceMessage.setPatientDeceased(deceased.getValue());
			}
		}
	}

	/**
	 * Sets the deceased status from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setDeceased(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if (pdqPatient.getPatientPerson() != null) {
			if (pdqPatient.getPatientPerson().getDeceasedInd() != null
					&& pdqPatient.getPatientPerson().getDeceasedInd().value != null
					&& pdqPatient.getPatientPerson().getDeceasedInd().value.booleanValue()) {
				final var dt = new BooleanType();
				dt.setValue(pdqPatient.getPatientPerson().getDeceasedInd().value);
				patient.setDeceased(dt);
			}
			if (pdqPatient.getPatientPerson().getDeceasedTime() != null) {
				final var deceasedTime = DateUtil
						.parseHl7Timestamp(pdqPatient.getPatientPerson().getDeceasedTime().getValue());
				patient.setDeceased(new DateTimeType(deceasedTime));
			}
		}
	}

	/**
	 * Sets the employee from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setEmployee(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if ((patient.getEmployeeOccupation() != null) && !patient.getEmployeeOccupation().isEmpty()) {
			v3PixSourceMessage.addEmployeeCode(patient.getEmployeeOccupation().getText());
		}
	}

	/**
	 * Sets the employee from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setEmployee(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient != null) && (pdqPatient.getPatientPerson() != null)
				&& (pdqPatient.getPatientPerson().getAsEmployee() != null)) {
			final List<PRPAMT201310UV02Employee> employees = pdqPatient.getPatientPerson().getAsEmployee();
			if (!employees.isEmpty()) {
				final PRPAMT201310UV02Employee employee = employees.get(0);
				if ((employee.getOccupationCode() != null) && (employee.getOccupationCode().getCode() != null)) {
					final var employeeOccupationCode = new CodeableConcept();
					employeeOccupationCode.setText(employee.getOccupationCode().getCode());
					patient.setEmployeeOccupation(employeeOccupationCode);
				}
			}
		}
	}

	/**
	 * Set the patient Birth Order from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setMultipeBirth(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		final Type iMultipleBirth = patient.getMultipleBirth();
		if (iMultipleBirth instanceof IntegerType) {
			final IntegerType multipleBirth = (IntegerType) iMultipleBirth;
			if (multipleBirth.getValue() != null) {
				v3PixSourceMessage.setPatientMultipleBirthOrderNumber(multipleBirth.getValue());
				v3PixSourceMessage.setPatientMultipleBirthIndicator(true);
			}
		}
		if (iMultipleBirth instanceof BooleanType) {
			final BooleanType multipleBirth = (BooleanType) iMultipleBirth;
			if (multipleBirth.getValue() != null) {
				v3PixSourceMessage.setPatientMultipleBirthIndicator(multipleBirth.getValue());
			}
		}
	}

	/**
	 * Sets the multipe birth from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setMultipeBirth(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if (pdqPatient.getPatientPerson() != null) {
			if ((pdqPatient.getPatientPerson().getMultipleBirthInd() != null)
					&& pdqPatient.getPatientPerson().getMultipleBirthInd().value != null
					&& pdqPatient.getPatientPerson().getMultipleBirthInd().value.booleanValue()) {
				final var dt = new BooleanType();
				dt.setValue(pdqPatient.getPatientPerson().getMultipleBirthInd().value);
				patient.setMultipleBirth(dt);
			}
			if (pdqPatient.getPatientPerson().getMultipleBirthOrderNumber() != null) {
				final Integer birthOrderNumber = pdqPatient.getPatientPerson().getMultipleBirthOrderNumber().getValue();
				patient.setMultipleBirth(new IntegerType(birthOrderNumber.intValue()));
			}
		}
	}

	/**
	 * Sets the nation from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setNation(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if ((patient.getNation() != null) && !patient.getNation().isEmpty()) {
			v3PixSourceMessage.addPatientNation(patient.getNation().getText());
		}
	}

	/**
	 * Sets the nation from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setNation(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getAsCitizen() != null)) {
			final List<PRPAMT201310UV02Citizen> citizens = pdqPatient.getPatientPerson().getAsCitizen();
			if (!citizens.isEmpty()) {
				final PRPAMT201310UV02Nation nation = citizens.get(0).getPoliticalNation();
				if ((nation != null) && (nation.getCode() != null) && (nation.getCode().getCode() != null)) {
					final var nationCode = new CodeableConcept();
					nationCode.setText(nation.getCode().getCode());
					patient.setNation(nationCode);
				}
			}
		}
	}

	/**
	 * Sets the patient birth time from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setPatientBirthTime(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		v3PixSourceMessage.setPatientBirthTime(patient.getBirthDateElement().getValueAsString().replace("-", ""));
	}

	/**
	 * Sets the patient birth time from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setPatientBirthTime(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getBirthTime() != null)) {
			final var ts = pdqPatient.getPatientPerson().getBirthTime();
			final String date = ts.getValue();
			if ((date != null) && (date.length() >= 4)) {
				var dateFhir = date.substring(0, 4);
				if (date.length() >= 6) {
					dateFhir += "-" + date.substring(4, 6);
				}
				if (date.length() >= 8) {
					dateFhir += "-" + date.substring(6, 8);
				}
				patient.getBirthDateElement().setValueAsString(dateFhir);
			}
		}

	}

	/**
	 * Sets the patient gender from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setPatientGender(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		// Gender
		if (patient.getGender() != null) {
			var gender = "";
			if (patient.getGender() == AdministrativeGender.MALE) {
				gender = "M";
			} else if (patient.getGender() == AdministrativeGender.FEMALE) {
				gender = "F";
			} else {
				gender = "U";
			}
			v3PixSourceMessage.setPatientGender(gender);
		}
	}

	/**
	 * Sets the patient gender from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setPatientGender(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null)
				&& (pdqPatient.getPatientPerson().getAdministrativeGenderCode() != null)) {
			final CE gender = pdqPatient.getPatientPerson().getAdministrativeGenderCode();
			if ("M".equals(gender.getCode())) {
				patient.setGender(AdministrativeGender.MALE);
			} else if ("F".equals(gender.getCode())) {
				patient.setGender(AdministrativeGender.FEMALE);
			} else if ("U".equals(gender.getCode())) {
				patient.setGender(AdministrativeGender.OTHER);
			}
		}
	}

	/**
	 * Adds the marital status from the patient to the pix message. To verify in an
	 * implementation: is the coding of marital status of fhir equivalent to HL7 V3
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setPatientMaritalStatus(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if (!patient.getMaritalStatus().isEmpty()) {
			v3PixSourceMessage.setPatientMaritalStatus(patient.getMaritalStatus().getCodingFirstRep().getCode());
		}
	}

	/**
	 * Sets the patient marital status the pdq message to the patient. To verify in
	 * an implementation: is the coding of marital status of fhir equivalent to HL7
	 * V3? http://hl7.org/implement/standards/FHIR-Develop/valueset
	 * -marital-status.html
	 *
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setPatientMaritalStatus(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null) && (pdqPatient.getPatientPerson().getMaritalStatusCode() != null)) {
			final CE maritalStatusCode = pdqPatient.getPatientPerson().getMaritalStatusCode();
			if (maritalStatusCode.getCode() != null) {
				final var maritalStatus = new CodeableConcept();
				maritalStatus.setText(maritalStatusCode.getCode());
				patient.setMaritalStatus(maritalStatus);
			}
		}
	}

	/**
	 * Sets the patients mother maiden name from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setPatientMothersMaidenName(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		final HumanName maidenName = patient.getMothersMaidenName();
		if (maidenName != null && maidenName.getFamily() != null) {
			final String familyName = maidenName.getFamily();
			final var givenName = maidenName.getGivenAsSingleString();
			final var otherName = ""; // other is resolved into given in
			// addPatientName
			final var prefixName = maidenName.getPrefixAsSingleString();
			final var suffixName = maidenName.getSuffixAsSingleString();
			v3PixSourceMessage.setPatientMothersMaidenName(familyName, givenName, otherName, suffixName, prefixName);
		}
	}

	/**
	 * Sets the patient mothers maiden name from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setPatientMothersMaidenName(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null)
				&& (pdqPatient.getPatientPerson().getPersonalRelationship() != null)) {
			for (final PRPAMT201310UV02PersonalRelationship personalRelationship : pdqPatient.getPatientPerson()
					.getPersonalRelationship()) {
				if ((personalRelationship.getCode() != null) && "MTH".equals(personalRelationship.getCode().getCode())
						&& "2.16.840.1.113883.5.111".equals(personalRelationship.getCode().getCodeSystem())) {
					final COCTMT030007UVPerson motherRelationShipHolder = personalRelationship.getRelationshipHolder1();
					if ((motherRelationShipHolder != null) && (motherRelationShipHolder.getName() != null)) {
						final List<EN> names = motherRelationShipHolder.getName();
						if ((names != null) && (!names.isEmpty())) {
							// ITI 2b Rev. 11.0 Final Text – 2014-09-23
							// MothersMaidenName Parameter (approx 6645)
							// This optional parameter specifies the maiden name
							// of the mother of the person whose
							// information is being queried. For this parameter
							// item, a single person name (PN) data item shall
							// be specified in the Person.value attribute.
							final EN pn = names.get(0);
							final var humanNameType = new HumanName();
							if (pn.getGiven() != null) {
								for (final EnGiven given : pn.getGiven()) {
									humanNameType.addGiven(getMixedValue(given.getMixed()));
								}
							}
							if (pn.getFamily() != null) {
								for (final EnFamily family : pn.getFamily()) {
									humanNameType.setFamily(getMixedValue(family.getMixed()));
								}
							}
							if (pn.getPrefix() != null) {
								for (final EnPrefix prefix : pn.getPrefix()) {
									humanNameType.addPrefix(getMixedValue(prefix.getMixed()));
								}
							}
							if (pn.getSuffix() != null) {
								for (final EnSuffix suffix : pn.getSuffix()) {
									humanNameType.addPrefix(getMixedValue(suffix.getMixed()));
								}
							}
							patient.setMothersMaidenName(humanNameType);
						}
					}
				}
			}
		}
	}

	/**
	 * Sets the patient religious affiliation from the patient to the pix message.
	 *
	 * @param patient            the patient
	 * @param v3PixSourceMessage the v3 pix source message
	 */
	protected void setPatientReligiousAffiliation(FhirPatient patient, V3PixSourceMessageHelper v3PixSourceMessage) {
		if ((patient.getReligiousAffiliation() != null) && !patient.getReligiousAffiliation().isEmpty()) {
			v3PixSourceMessage.setPatientReligiousAffiliation(patient.getReligiousAffiliation().getText());
		}
	}

	/**
	 * Sets the patient religious affiliation from the pdq message to the patient.
	 *
	 * @param pdqPatient the pdq patient
	 * @param patient    the patient
	 */
	protected void setPatientReligiousAffiliation(PRPAMT201310UV02Patient pdqPatient, FhirPatient patient) {
		if ((pdqPatient.getPatientPerson() != null)
				&& (pdqPatient.getPatientPerson().getReligiousAffiliationCode() != null)) {
			final CE religiousAffiliation = pdqPatient.getPatientPerson().getReligiousAffiliationCode();
			if (religiousAffiliation.getCode() != null) {
				final var religion = new CodeableConcept();
				religion.setText(religiousAffiliation.getCode());
				patient.setReligiousAffiliation(religion);
			}
		}
	}

	/**
	 * Helper function to convert the HL7 PDQ AD type to the coressponding FHIR type
	 *
	 * @param ad the ad
	 * @return the address dt from ad
	 */
	protected org.hl7.fhir.dstu3.model.Address getAddressFromAD(AD ad) {
		final var address = new org.hl7.fhir.dstu3.model.Address();
		if (ad.getUse() != null) {
			if ("H".equals(ad.getUse())) {
				address.setUse(AddressUse.HOME);
			}
			if ("WP".equals(ad.getUse())) {
				address.setUse(AddressUse.WORK);
			}
			if ("TMP".equals(ad.getUse())) {
				address.setUse(AddressUse.TEMP);
			}
			if ("OLD".equals(ad.getUse())) {
				address.setUse(AddressUse.OLD);
			}
		}
		if ((ad.getStreetAddressLine() != null) && (!ad.getStreetAddressLine().isEmpty())) {
			for (final AdxpStreetAddressLine addressStreetLine : ad.getStreetAddressLine()) {
				address.addLine(getMixedValue(addressStreetLine.getMixed()));
			}
		}
		if ((ad.getCity() != null) && (!ad.getCity().isEmpty())) {
			address.setCity(getMixedValue(ad.getCity().get(0).getMixed()));
		}
		if ((ad.getState() != null) && (!ad.getState().isEmpty())) {
			address.setState(getMixedValue(ad.getState().get(0).getMixed()));
		}
		if ((ad.getPostalCode() != null) && (!ad.getPostalCode().isEmpty())) {
			address.setPostalCode(getMixedValue(ad.getPostalCode().get(0).getMixed()));
		}
		if ((ad.getCountry() != null) && (!ad.getCountry().isEmpty())) {
			address.setCountry(getMixedValue(ad.getCountry().get(0).getMixed()));
		}
		return address;
	}

	/**
	 * Helper method which gets the value of the supplied FeatureMap
	 *
	 * @param mixed (the FeatureMap containing the value)
	 * @return String containing the value of the supplied FeatureMap.
	 */
	protected String getMixedValue(List<Serializable> mixed) {
		var returnValue = "";
		// if we have a mixed
		if (!mixed.isEmpty() && mixed.get(0) instanceof String) {
			returnValue = (String) mixed.get(0);
		}
		return returnValue;
	}

}
