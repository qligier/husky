/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by
 * medshare GmbH, Switzerland. All rights reserved.
 * http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the
 * Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the
 * Creative Commons Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2014
 *
 *******************************************************************************/

package org.ehc.cda.ch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.ehc.cda.ch.enums.EHealthConnectorVersions;
import org.ehc.cda.ch.enums.LanguageCode;
import org.ehc.common.DateUtil;
import org.ehc.common.Organization;
import org.ehc.common.Patient;
import org.ehc.common.Person;
import org.ehc.common.Util;
import org.ehc.common.ch.ConvenienceUtilsEnums.ParticipantType;
import org.openhealthtools.mdht.uml.cda.AssignedCustodian;
import org.openhealthtools.mdht.uml.cda.AssignedEntity;
import org.openhealthtools.mdht.uml.cda.AssociatedEntity;
import org.openhealthtools.mdht.uml.cda.Authenticator;
import org.openhealthtools.mdht.uml.cda.Author;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.CDAPackage;
import org.openhealthtools.mdht.uml.cda.Custodian;
import org.openhealthtools.mdht.uml.cda.DataEnterer;
import org.openhealthtools.mdht.uml.cda.DocumentRoot;
import org.openhealthtools.mdht.uml.cda.InfrastructureRootTypeId;
import org.openhealthtools.mdht.uml.cda.Participant1;
import org.openhealthtools.mdht.uml.cda.ch.CDACH;
import org.openhealthtools.mdht.uml.cda.internal.resource.CDAResource;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil.Query;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.ST;

/**
 * CDA Dokument, das den Vorgaben der Spezifikation CDA-CH entspricht
 * 
 */
public abstract class CdaCh {

	CDACH doc = null;      												// The CDA Document
	public DocumentRoot docRoot = null; 									// The OHT-Element that helds the document
	protected Query query;

	/**
	 * Standard Constructor
	 * 
	 * <div class="de">Erstellt ein CdaCh Objekt</div>
	 * <div class="fr"></div>
	 */
	public CdaCh(CDACH doc) {
		this.doc = doc;
		docRoot = CDAFactory.eINSTANCE.createDocumentRoot();
		docRoot.setClinicalDocument(doc);
		// Add the stylesheet processing instructions to the document root using featuremaputil
		// set xml namespace
		docRoot.getXMLNSPrefixMap().put("", CDAPackage.eNS_URI);

		// Set OID of the document
		//TODO zumindest die Extension muss als fortlaufende Nummer generiert werden (siehe Arztbrief Seite 44)
		II docID = DatatypesFactory.eINSTANCE.createII();
		doc.setId(docID);
		docID.setRoot(EHealthConnectorVersions.EHealthConnectorV1.getId());
		docID.setExtension("1817558762");

		// Set Confidentially Code
		// Standard is "N" for "normal". Can be changed through the set method
		CE confidentialityCode = DatatypesFactory.eINSTANCE.createCE();
		doc.setConfidentialityCode(confidentialityCode);
		confidentialityCode.setCode("N");

		// set xml namespace
		docRoot.getXMLNSPrefixMap().put("", CDAPackage.eNS_URI);

		// Set creation time of the document
		doc.setEffectiveTime(DateUtil.nowAsTS());

		//Type ID
		setTypeId();
	}

	/**
	 * <div class="de">Erstellt ein CdaCh Objekt mittels eines IHE DocumentRoot Objekts</div>
	 * <div class="fr"></div>
	 *
	 * @param root 
	 * 		<div class="de">DocumentRoot</div>
	 *		<div class="fr"></div>
	 */
	public CdaCh(DocumentRoot root) {
		docRoot = root;
	}

	/**
	 * Fügt dem CDA Dokument einen Unterzeichner hinzu
	 * 
	 * @param authenticator
	 *            Unterzeichner
	 */
	public void addAuthenticator(org.ehc.common.Author authenticator) {
		Authenticator auth = CDAFactory.eINSTANCE.createAuthenticator();
		AssignedEntity entity = CDAFactory.eINSTANCE.createAssignedEntity();

		auth.setAssignedEntity(entity);
		entity.setAssignedPerson(authenticator.copyMdhtAuthor().getAssignedAuthor().getAssignedPerson());

		doc.getAuthenticators().add(auth);
	}

	/**
	 * Fügt dem CDA Dokument einen Unterzeichner hinzu
	 * 
	 * @param authenticator
	 *            Unterzeichner
	 */
	public void addAuthenticator(Person authenticator) {
		Authenticator auth = CDAFactory.eINSTANCE.createAuthenticator();
		AssignedEntity entity = CDAFactory.eINSTANCE.createAssignedEntity();

		auth.setAssignedEntity(entity);
		entity.setAssignedPerson(authenticator.copyMdhtPerson());

		doc.getAuthenticators().add(auth);
	}

	/**
	 * Fügt einen Autoren hinzu.
	 * 
	 * @param author
	 *            Der Autor
	 */
	public void addAuthor(org.ehc.common.Author author) {
		Author docAuthor = author.copyMdhtAuthor();
		doc.getAuthors().add(docAuthor);
	}

	/**
	 * Fügt dem CDA Dokument einen Erfasser hinzu
	 * 
	 * @param dataEnterer
	 *            Erfasser oder Sachbearbeiter/-in, welche(r) das Dokument
	 *            erstellt oder Beiträge dazu geliefert hat.
	 */
	public void addDataEnterer(Person dataEnterer) {
		DataEnterer enterer = CDAFactory.eINSTANCE.createDataEnterer();
		AssignedEntity entity = CDAFactory.eINSTANCE.createAssignedEntity();

		enterer.setAssignedEntity(entity);
		entity.setAssignedPerson(dataEnterer.copyMdhtPerson());

		doc.getDataEnterer().setAssignedEntity(entity);
	}

	/**
	 * Fügt eine Versicherung hinzu
	 * 
	 * @param versicherung
	 *            Die Versicherung als Organization Objekt
	 */
	public void addInsurance(Organization versicherung) {
		addParticipant(versicherung, ParticipantType.Insurance);
	}

	/**
	 * Fügt dem CDA Dokument eine Partizipation hinzu
	 * 
	 * @param organization
	 *            Organisation
	 * @param participantType
	 *            Art der Partizipation (z.B. Versicherung)
	 */
	public void addParticipant(Organization organization,
			ParticipantType participantType) {
		// Set the given organization as Participant of this document.
		final Participant1 participant = CDAFactory.eINSTANCE
				.createParticipant1();
		doc.getParticipants().add(participant);
		final AssociatedEntity assEnt = CDAFactory.eINSTANCE
				.createAssociatedEntity();
		participant.setAssociatedEntity(assEnt);

		org.openhealthtools.mdht.uml.cda.Organization docOrganization = CDAFactory.eINSTANCE
				.createOrganization();
		docOrganization = organization.getMdhtOrganization();
		assEnt.setScopingOrganization(docOrganization);
	}

	/**
	 * Gibt den Autor des Dokuments zurück
	 * 
	 * @return das eHealthConnector Author Objekt
	 */
	public org.ehc.common.Author getAuthor() {
		org.ehc.common.Author author = new org.ehc.common.Author(
				doc.getAuthors().get(0));
		return author;
	}

	/**
	 * Gibt alle Autoren des Dokuments zurück
	 * 
	 * @return das eHealthConnector Author Objekt
	 */
	public ArrayList<org.ehc.common.Author> getAuthors() {
		ArrayList<org.ehc.common.Author> authors = new ArrayList<org.ehc.common.Author>();
		for (Author mAutor : doc.getAuthors()){
			org.ehc.common.Author author = new org.ehc.common.Author(mAutor);
			authors.add(author);
		}
		return authors;
	}

	/**
	 * Gibt den Verantwortlichen für das Dokument zurück
	 * 
	 * @return das openHealthTools Custodian Objekt
	 */
	public Custodian getCustodian() {
		return doc.getCustodian();
	}

	/**
	 * Gibt alle Autoren des Dokuments zurück
	 * 
	 * @return das eHealthConnector Author Objekt
	 */
	public org.ehc.common.Person getDataEnterer() {
		if (doc.getDataEnterer()!=null) {
			if (doc.getDataEnterer().getAssignedEntity()!=null) {
				if (doc.getDataEnterer().getAssignedEntity().getAssignedPerson()!=null) {
					org.ehc.common.Person person = new org.ehc.common.Person(doc.getDataEnterer().getAssignedEntity().getAssignedPerson());
					return person;
				}
			}
		}
		return null;
	}

	/**
	 * Gibt alle Versicherungen zurück
	 * 
	 * @param versicherung
	 *            Die Versicherung als Organization Objekt
	 */
	public ArrayList<Organization> getInsurances(Organization versicherung) {
		ArrayList<Organization> organizations = new ArrayList<Organization>();
		for (Participant1 part : doc.getParticipants()) {
			if (part.getTypeCode().equals(ParticipantType.Insurance)) {
				Organization org = new Organization(part.getAssociatedEntity().getScopingOrganization());
				organizations.add(org);
			}
		}
		return organizations;
	}

	/**
	 * Gibt alle rechtliche Unterzeichner des Dokuments zurück
	 * 
	 * @return die rechtlichen Unterzeichner
	 */
	public ArrayList<org.ehc.common.Person> getLegalAuthenticators() {
		ArrayList<org.ehc.common.Person> persons = new ArrayList<org.ehc.common.Person>();
		for (Authenticator mAutor : doc.getAuthenticators()){
			org.ehc.common.Person person = new org.ehc.common.Person(mAutor.getAssignedEntity().getAssignedPerson());
			persons.add(person);
		}
		return persons;
	}

	public ByteArrayOutputStream getOutputStream () {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			CDAUtil.save(doc, baos);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return baos;
	}

	/**
	 * Gibt alle Participants zurück
	 * 
	 * @param versicherung
	 *            Die Versicherung als Organization Objekt
	 */
	public ArrayList<Organization> getParticipants(Organization versicherung) {
		ArrayList<Organization> organizations = new ArrayList<Organization>();
		for (Participant1 part : doc.getParticipants()) {
			Organization org = new Organization(part.getAssociatedEntity().getScopingOrganization());
			organizations.add(org);
		}
		return organizations;
	}

	/**
	 * Liefert das Patientenobjekt zurück
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		Patient patient = new Patient(doc.getRecordTargets().get(0));
		return patient;
	}

	public String getTitle() {
		return doc.getTitle().getText();
	}

	/**
	 * Gibt die XML-Repräsentation des Dokuments auf der Konsole aus
	 */
	public void printXmlToConsole() {
		try {
			CDAUtil.save(doc, System.out);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Speichert das CDA Dokument als XML Datei
	 * 
	 * @param fileName
	 *            Dateiname (inkl. Pfadangaben)
	 * @throws Exception
	 */
	public void saveToFile(String fileName) throws Exception {
		File yourFile = new File(fileName);
		if (!yourFile.exists()) {
			yourFile.createNewFile();
		}
		FileOutputStream oFile = new FileOutputStream(yourFile, false);

		// create emf resource
		CDAResource resource = (CDAResource) CDAResource.Factory.INSTANCE
				.createResource(URI.createURI(CDAPackage.eNS_URI));

		// add the document root to the resource
		docRoot.setClinicalDocument(doc);
		resource.getContents().add(docRoot);

		// save resource to console
		resource.save(oFile, null);
	}

	/**
	 * Weist dem CDA Dokument die verwaltende Organisation zu
	 * 
	 * @param organization
	 *            verwaltende Organisation
	 */
	public void setCustodian(Organization organization) {
		// create and set the mdht Custodian object
		final Custodian mdhtCustodian = CDAFactory.eINSTANCE.createCustodian();
		doc.setCustodian(mdhtCustodian);

		final AssignedCustodian assCust = CDAFactory.eINSTANCE
				.createAssignedCustodian();
		mdhtCustodian.setAssignedCustodian(assCust);

		mdhtCustodian
		.getAssignedCustodian()
		.setRepresentedCustodianOrganization(
				Util
				.createCustodianOrganizationFromOrganization(organization));

		//mdhtCustodian.setNullFlavor(NullFlavor.);
		//Setzt die GLN des Arztes
		II id = DatatypesFactory.eINSTANCE.createII();
		if (organization.getMdhtOrganization().getIds().size()>0) {
			id = organization.getMdhtOrganization().getIds().get(0);
		}
		mdhtCustodian.getAssignedCustodian().getRepresentedCustodianOrganization().getIds().add(id);
	}

	public void setLanguageCode(LanguageCode language) {
		// Set language of the document
		doc.setLanguageCode(language.getCS());
	}

	/**
	 * Weist dem CDA Dokument einen rechtsgültigen Unterzeichner hinzu
	 * 
	 * @param legalAuthenticator
	 *            rechtsgültiger Unterzeichner
	 */
	public void setLegalAuthenticator(
			org.ehc.common.Author legalAuthenticator) {
		doc.setLegalAuthenticator(Util
				.createLagalAuthenticatorFromAuthor(legalAuthenticator));
	}

	/**
	 * Weist dem CDA Dokument den Patienten zu
	 * 
	 * @param patient
	 *            Patient
	 */
	public void setPatient(Patient patient) {
		doc.getRecordTargets().add(patient.getMdhtRecordTarget());
	}

	protected void setProcessingInstructions(String stylesheet) {
		// Add the stylesheet processing instructions to the document
		if (stylesheet == null) {
			stylesheet = "../../../../stylesheets/HL7.ch/CDA-CH/v1.2/cda-ch.xsl";	
		}
		FeatureMapUtil.addProcessingInstruction(docRoot.getMixed(),
				"xml-stylesheet", "type=\"text/xsl\" href=\""+stylesheet+"\"");// xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\") xsi:schemaLocation=\"urn:hl7-org:v3 CDA.xsd\"" ); 
	}

	public void setTitle(String title) {
		ST titleSt = DatatypesFactory.eINSTANCE.createST();
		titleSt.addText(title);
		doc.setTitle(titleSt);
	}

	/**
	 * Setzt die Metadaten, die für Dokumente der CDA-CH-Spezifikation verwendet werden
	 * (DocumentID, TypeID, Confidentially Code, Language Code, Stylesheet)
	 * 
	 * @param german
	 *          Dokument-Sprache (CDA: /ClinicalDocument/languageCode)
	 * @param stylesheet
	 *          Stylesheet, welches im CDA mittels <?xml-stylesheet> für die
	 *          menschlich Lesbare Darstellung referenziert werden soll.
	 */

	public void setTypeId() {
		// Set Type ID 
		// Identifies the Type of the xml document
		InfrastructureRootTypeId typeId = CDAFactory.eINSTANCE.createInfrastructureRootTypeId();
		doc.setTypeId(typeId);
		typeId.setRoot("2.16.840.1.113883.1.3");
		typeId.setExtension("POCD_HD000040");
	}
}
