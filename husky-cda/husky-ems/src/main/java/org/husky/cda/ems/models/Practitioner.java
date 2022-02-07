/*
* This code is made available under the terms of the Eclipse Public License v1.0
* in the github project https://github.com/project-husky/husky there you also
* find a list of the contributors and the license information.
*
* This project has been developed further and modified by the joined working group Husky
* on the basis of the eHealth Connector opensource project from June 28, 2021,
* whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
*/
package org.husky.cda.ems.models;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.husky.cda.ems.generated.artdecor.AtcdabbrHeaderAuthor;
import org.husky.cda.ems.generated.artdecor.AtcdabbrHeaderLegalAuthenticator;
import org.husky.cda.ems.generated.artdecor.AtcdabbrOtherAuthorBodyEImpfpass;
import org.husky.common.enums.NullFlavor;
import org.husky.common.hl7cdar2.CE;
import org.husky.common.hl7cdar2.EntityClassDevice;
import org.husky.common.hl7cdar2.II;
import org.husky.common.hl7cdar2.IVLTS;
import org.husky.common.hl7cdar2.PN;
import org.husky.common.hl7cdar2.POCDMT000040AssignedAuthor;
import org.husky.common.hl7cdar2.POCDMT000040AssignedEntity;
import org.husky.common.hl7cdar2.POCDMT000040AssociatedEntity;
import org.husky.common.hl7cdar2.POCDMT000040Author;
import org.husky.common.hl7cdar2.POCDMT000040AuthoringDevice;
import org.husky.common.hl7cdar2.POCDMT000040LegalAuthenticator;
import org.husky.common.hl7cdar2.POCDMT000040Organization;
import org.husky.common.hl7cdar2.POCDMT000040Participant1;
import org.husky.common.hl7cdar2.POCDMT000040Person;
import org.husky.common.hl7cdar2.SC;
import org.husky.common.hl7cdar2.TEL;
import org.husky.common.hl7cdar2.TS;
import org.husky.common.model.Address;
import org.husky.common.model.Code;
import org.husky.common.model.Identificator;
import org.husky.common.model.Name;
import org.husky.common.model.Organization;
import org.husky.common.model.Telecom;
import org.husky.common.utils.time.DateTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Practitioner {

	/** The SLF4J logger instance. */
	private static Logger LOGGER = LoggerFactory.getLogger(Practitioner.class);
	private List<Identificator> identifiers;
	private Organization organization;
	private List<Telecom> telecoms;
	private List<Name> names;
	private List<Address> addresses;
	private Code code;
	private Code speciality;
	private String softwareName;
	private String modelNameOfDevice;

	public Practitioner() {
		this.identifiers = new ArrayList<>();
		this.telecoms = new ArrayList<>();
		this.names = new ArrayList<>();
	}

	public Practitioner(POCDMT000040Author author) {
		if (author != null && author.getAssignedAuthor() != null) {

			if (author.getAssignedAuthor().getAssignedPerson() != null) {
				for (PN pn : author.getAssignedAuthor().getAssignedPerson().getName()) {
					if (pn != null) {
						this.names.add(new Name(pn));
					}
				}
			}

			this.identifiers = new ArrayList<>();
			for (II id : author.getAssignedAuthor().getId()) {
				this.identifiers.add(new Identificator(id));
			}

			if (author.getAssignedAuthor().getAssignedPerson() != null
					&& author.getAssignedAuthor().getRepresentedOrganization() != null) {
				this.organization = new Organization(author.getAssignedAuthor().getRepresentedOrganization());
			}

			if (author.getAssignedAuthor().getCode() != null) {
				this.code = new Code(author.getAssignedAuthor().getCode());
			}

			this.telecoms = new ArrayList<>();
			for (TEL tel : author.getAssignedAuthor().getTelecom()) {
				this.telecoms.add(new Telecom(tel));
			}
		}
	}

	public Practitioner(POCDMT000040LegalAuthenticator authenticator) {
		if (authenticator != null && authenticator.getAssignedEntity() != null) {

			this.identifiers = new ArrayList<>();
			for (II id : authenticator.getAssignedEntity().getId()) {
				this.identifiers.add(new Identificator(id));
			}

			if (authenticator.getAssignedEntity().getAssignedPerson() != null) {
				for (PN pn : authenticator.getAssignedEntity().getAssignedPerson().getName()) {
					if (pn != null) {
						this.names.add(new Name(pn));
					}
				}
			}

			if (authenticator.getAssignedEntity().getAssignedPerson() != null
					&& authenticator.getAssignedEntity().getRepresentedOrganization() != null) {
				this.organization = new Organization(authenticator.getAssignedEntity().getRepresentedOrganization());
			}

			if (authenticator.getAssignedEntity().getCode() != null) {
				this.code = new Code(authenticator.getAssignedEntity().getCode());
			}

			this.telecoms = new ArrayList<>();
			for (TEL tel : authenticator.getAssignedEntity().getTelecom()) {
				this.telecoms.add(new Telecom(tel));
			}

			if (authenticator.getSignatureCode() != null) {
				this.speciality = new Code(authenticator.getSignatureCode());
			}
		}
	}

	public List<Identificator> getIdentifier() {
		return identifiers;
	}

	public void setIdentifier(List<Identificator> identifier) {
		this.identifiers = identifier;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Telecom> getTelecom() {
		return telecoms;
	}

	public void setTelecom(List<Telecom> telecom) {
		this.telecoms = telecom;
	}

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Code getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Code speciality) {
		this.speciality = speciality;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getModelNameOfDevice() {
		return modelNameOfDevice;
	}

	public void setModelNameOfDevice(String modelNameOfDevice) {
		this.modelNameOfDevice = modelNameOfDevice;
	}

	public AtcdabbrHeaderAuthor getAtcdabbrHeaderAuthor(ZonedDateTime timeAuthor) {
		AtcdabbrHeaderAuthor author = new AtcdabbrHeaderAuthor();

		TS time = new TS();

		if (timeAuthor == null) {
			time.getNullFlavor().add(NullFlavor.UNKNOWN_CODE);
		} else {
			time.setValue(DateTimes.toDatetimeTs(timeAuthor).getValue());
		}

		author.setTime(time);

		if (code != null) {
			author.setFunctionCode(code.getHl7CdaR2Ce());
		}

		author.setAssignedAuthor(getHl7CdaR2Pocdmt000040AssignedAuthor(author.getAssignedAuthor()));
		return author;
	}

	public AtcdabbrHeaderLegalAuthenticator getHeaderLegalAuthenticator(ZonedDateTime timeLegalAuthen) {
		AtcdabbrHeaderLegalAuthenticator legalAuthenticator = new AtcdabbrHeaderLegalAuthenticator();

		TS time = new TS();

		if (timeLegalAuthen == null) {
			time.getNullFlavor().add(NullFlavor.UNKNOWN_CODE);
		} else {
			time.setValue(DateTimes.toDatetimeTs(timeLegalAuthen).getValue());
		}

		legalAuthenticator.setHl7Time(time);
		legalAuthenticator.setAssignedEntity(
				createAssignedEntity(new POCDMT000040AssignedEntity()));
		return legalAuthenticator;
	}

	protected POCDMT000040AssociatedEntity getHl7CdaR2Pocdmt000040AssociatedEntity() {
		POCDMT000040AssociatedEntity associatedEntity = new POCDMT000040AssociatedEntity();

		if (this.identifiers != null && !identifiers.isEmpty()) {
			for (Identificator id : identifiers) {
				if (id != null) {
					associatedEntity.getId().add(id.getHl7CdaR2Ii());
				}
			}
		}

		if (this.addresses != null && !this.addresses.isEmpty()) {
			for (Address address : this.addresses) {
				if (address != null) {
					associatedEntity.getAddr().add(address.getHl7CdaR2Ad());
				}
			}
		}

		if (this.telecoms != null && !this.telecoms.isEmpty()) {
			for (Telecom tel : telecoms) {
				if (tel != null) {
					associatedEntity.getTelecom().add(tel.getHl7CdaR2Tel());
				}
			}
		}

		if (this.organization != null) {
			associatedEntity.setScopingOrganization(this.organization.getHl7CdaR2Pocdmt000040Organization());
		}

		if (this.names != null) {
			associatedEntity
					.setAssociatedPerson(getHl7CdaR2Pocdmt000040Person(new POCDMT000040Person()));
		} else {
			POCDMT000040Person person = new POCDMT000040Person();
			person.getNullFlavor().add("UNK");
			associatedEntity.setAssociatedPerson(person);
		}

		return associatedEntity;
	}

	protected POCDMT000040AuthoringDevice getAtcdabbrOtherDeviceCompilation() {
		POCDMT000040AuthoringDevice device = new POCDMT000040AuthoringDevice();
		device.setClassCode(EntityClassDevice.DEV);
		device.setDeterminerCode("INSTANCE");
		device.setManufacturerModelName(new SC(modelNameOfDevice));
		device.setSoftwareName(new SC(softwareName));
		return device;
	}

	protected POCDMT000040AssignedAuthor getHl7CdaR2Pocdmt000040AssignedAuthor(
			POCDMT000040AssignedAuthor assignedAuthor) {

		if (this.organization != null) {
			POCDMT000040Organization representedOrganization = assignedAuthor.getRepresentedOrganization();

			if (representedOrganization == null) {
				representedOrganization = new POCDMT000040Organization();
				representedOrganization.setClassCode("ORG");
				representedOrganization.setDeterminerCode("INSTANCE");
			}

			assignedAuthor.setRepresentedOrganization(
					this.organization.getHl7CdaR2Pocdmt000040Organization(representedOrganization));
		}

		if (this.identifiers != null && !this.identifiers.isEmpty()) {
			for (Identificator id : this.identifiers) {
				if (id != null) {
					assignedAuthor.getId().add(id.getHl7CdaR2Ii());
				}
			}
		}

		if (this.telecoms != null && !this.telecoms.isEmpty()) {
			for (Telecom tel : this.telecoms) {
				if (tel != null) {
					assignedAuthor.getTelecom().add(tel.getHl7CdaR2Tel());
				}
			}
		}

		if (this.addresses != null) {
			for (Address address : this.addresses) {
				if (address != null) {
					assignedAuthor.getAddr().add(address.getHl7CdaR2Ad());
				}
			}
		}

		if (this.names != null) {
			assignedAuthor.setAssignedPerson(getHl7CdaR2Pocdmt000040Person(assignedAuthor.getAssignedPerson()));
		}

		if (this.speciality != null) {
			assignedAuthor.setCode(this.speciality.getHl7CdaR2Ce());
		}

		if (this.softwareName != null && this.modelNameOfDevice != null) {
			assignedAuthor.setAssignedAuthoringDevice(getAtcdabbrOtherDeviceCompilation());
		}

		return assignedAuthor;
	}

	protected POCDMT000040AssignedEntity getHl7CdaR2Pocdmt000040AssignedEntity(
			POCDMT000040AssignedEntity assignedEntity) {
		if (assignedEntity == null) {
			return assignedEntity;
		}

		if (this.identifiers != null && !identifiers.isEmpty()) {
			for (Identificator id : identifiers) {
				if (id != null) {
					assignedEntity.getId().add(id.getHl7CdaR2Ii());
				}
			}
		}

		if (this.code != null) {
			assignedEntity.setCode(this.code.getHl7CdaR2Ce());
		} else {
			CE codeCE = new CE();
			codeCE.nullFlavor = new ArrayList<>();
			codeCE.nullFlavor.add("UNK");
			assignedEntity.setCode(codeCE);
		}

		if (this.telecoms != null && !this.telecoms.isEmpty()) {
			for (Telecom tel : telecoms) {
				if (tel != null) {
					assignedEntity.getTelecom().add(tel.getHl7CdaR2Tel());
				}
			}
		}

		if (this.organization != null) {
			assignedEntity.setRepresentedOrganization(this.organization.getHl7CdaR2Pocdmt000040Organization());
		}

		if (this.names != null) {
			assignedEntity.setAssignedPerson(
					getHl7CdaR2Pocdmt000040Person(assignedEntity.getAssignedPerson()));
		}

		return assignedEntity;
	}

	protected POCDMT000040Person getHl7CdaR2Pocdmt000040Person(POCDMT000040Person person) {
		if (person == null) {
			person = new POCDMT000040Person();
		}

		if (this.names != null) {
			for (Name nam : this.names) {
				if (nam != null) {
					person.getName().add(nam.getHl7CdaR2Pn());
				}
			}

			if (person.getClassCode().isEmpty()) {
				person.getClassCode().add("PSN");
			}
			person.setDeterminerCode("INSTANCE");
		}
		return person;
	}

	public POCDMT000040Participant1 getHeaderOrderingProvider(ZonedDateTime timeAuthor) {
		POCDMT000040Participant1 participant = new POCDMT000040Participant1();
		participant.getTypeCode().add("REF");

		IVLTS time = new IVLTS();
		if (timeAuthor == null) {
			time.getNullFlavor().add(NullFlavor.UNKNOWN_CODE);
		} else {
			time.setValue(DateTimes.toDatetimeTs(timeAuthor).getValue());
		}

		participant.setTime(time);

		participant.setAssociatedEntity(getHl7CdaR2Pocdmt000040AssociatedEntity());
		return participant;
	}

	protected POCDMT000040AssignedEntity createAssignedEntity(POCDMT000040AssignedEntity assignedEntity) {
		if (assignedEntity == null) {
			assignedEntity = new POCDMT000040AssignedEntity();
		}

		assignedEntity.setClassCode("ASSIGNED");

		if (this.identifiers != null) {
			for (Identificator id : this.identifiers) {
				if (id != null) {
					assignedEntity.getId().add(id.getHl7CdaR2Ii());
				}
			}
		}

		if (this.names != null) {
			assignedEntity.setAssignedPerson(createPerson(new POCDMT000040Person()));
		}

		if (this.organization != null) {
			POCDMT000040Organization legalAuthenOrg = new POCDMT000040Organization();
			legalAuthenOrg.setClassCode("ORG");
			legalAuthenOrg.setDeterminerCode("INSTANCE");
			legalAuthenOrg = this.organization.getHl7CdaR2Pocdmt000040Organization(legalAuthenOrg);
			assignedEntity.setRepresentedOrganization(legalAuthenOrg);
		}

		return assignedEntity;
	}

	protected POCDMT000040Person createPerson(POCDMT000040Person person) {
		if (person == null) {
			person = new POCDMT000040Person();
		}

		if (names != null) {
			for (Name nam : names) {
				if (nam != null) {
					person.getName().add(nam.getHl7CdaR2Pn());
				}
			}
		}

		if (person.getClassCode().isEmpty()) {
			person.getClassCode().add("PSN");
		}
		person.setDeterminerCode("INSTANCE");
		return person;
	}

	public AtcdabbrOtherAuthorBodyEImpfpass getAtcdabbrOtherAuthorBodyEImpfpass(ZonedDateTime timeAuthor) {
		AtcdabbrOtherAuthorBodyEImpfpass author = new AtcdabbrOtherAuthorBodyEImpfpass();

		TS time = new TS();
		if (timeAuthor != null) {
			time.setValue(DateTimes.toDatetimeTs(timeAuthor).getValue());
		} else {
			time.nullFlavor = new ArrayList<>();
			time.getNullFlavor().add(NullFlavor.UNKNOWN_CODE);
		}
		author.setTime(time);

		author.setAssignedAuthor(getHl7CdaR2Pocdmt000040AssignedAuthor(author.getAssignedAuthor()));
		return author;
	}

	public String getGdaIndex() {
		if (identifiers == null) {
			return null;
		}

		Optional<Identificator> optionalId = identifiers.stream()
				.filter(id -> id != null && (id.getExtension() == null || id.getExtension().isEmpty())
						&& id.getAssigningAuthorityName() != null
						&& id.getAssigningAuthorityName().toUpperCase().contains("GDA"))
				.findFirst();
		if (optionalId.isPresent() && optionalId.get() != null) {
			return optionalId.get().getRoot();
		}

		return null;
	}

}
