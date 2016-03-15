package org.ehealth_connector.cda.ch.lab;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.ch.AbstractCdaCh;
import org.ehealth_connector.cda.enums.LanguageCode;
import org.ehealth_connector.cda.ihe.lab.ReferralOrderingPhysician;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.IntendedRecipient;
import org.openhealthtools.mdht.uml.cda.AssignedCustodian;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.Custodian;
import org.openhealthtools.mdht.uml.cda.CustodianOrganization;
import org.openhealthtools.mdht.uml.cda.InFulfillmentOf;
import org.openhealthtools.mdht.uml.cda.InformationRecipient;
import org.openhealthtools.mdht.uml.cda.Participant1;
import org.openhealthtools.mdht.uml.cda.Section;
import org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratorySpecialtySection;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.ADXP;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.ON;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.x_InformationRecipient;

public abstract class AbstractLaboratoryReport<EClinicalDocument extends ClinicalDocument>
		extends AbstractCdaCh<EClinicalDocument> {

	protected AbstractLaboratoryReport(EClinicalDocument doc) {
		super(doc);
	}

	protected AbstractLaboratoryReport(EClinicalDocument doc, LanguageCode languageCode) {
		super(doc, languageCode);
	}

	protected AbstractLaboratoryReport(EClinicalDocument doc, LanguageCode languageCode,
			String styleSheet, String css) {
		super(doc, styleSheet, css);
		// If the language code is null use default ENGLISH
		if (languageCode == null) {
			this.setLanguageCode(LanguageCode.ENGLISH);
		} else {
			this.setLanguageCode(languageCode);
		}
		setTitle(getSpecialitySectionTitle());
		initCda();
		// Fix RealmCode
		final CS cs = DatatypesFactory.eINSTANCE.createCS();
		cs.setCode("CHE");
		getDoc().getRealmCodes().clear();
		getDoc().getRealmCodes().add(cs);
	}

	public void addIntendedRecipient(IntendedRecipient recipient) {
		getMdht().getInformationRecipients().add(recipient.getMdhtIntendedRecipient());
		int nb = getMdht().getInformationRecipients().size() - 1;
		getMdht().getInformationRecipients().get(nb).setTypeCode(x_InformationRecipient.PRCP);
	}

	public void addReferralOrderingPhysician(ReferralOrderingPhysician physician) {
		getMdht().getParticipants().add(physician.copy());
	}

	/**
	 * Convenience function that returns a list of all order ids of all
	 * inFulfillmentOf Elements
	 * 
	 * @return the order id list (from all underlying
	 *         /clinicalDocument/inFulfillmentOf/order/id)
	 */
	public List<Identificator> getInFulfillmentOfOrderIds() {
		ArrayList<Identificator> al = new ArrayList<Identificator>();
		for (InFulfillmentOf ifo : getMdht().getInFulfillmentOfs()) {
			for (II id : ifo.getOrder().getIds()) {
				al.add(new Identificator(id));
			}
		}
		return al;
	}

	public List<IntendedRecipient> getIntendedRecipients() {
		List<IntendedRecipient> il = new ArrayList<IntendedRecipient>();
		for (InformationRecipient ir : getMdht().getInformationRecipients()) {
			if (ir instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient) {
				org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient iheIr = (org.openhealthtools.mdht.uml.cda.ihe.lab.IntendedRecipient) ir;
				il.add(new IntendedRecipient(iheIr));
			}
		}
		return il;
	}

	private LaboratorySpecialtySection getLaboratorySpecialtySection() {
		for (Section s : getMdht().getAllSections()) {
			if (s instanceof LaboratorySpecialtySection) {
				return (LaboratorySpecialtySection) s;
			}
		}
		return null;
	}

	/**
	 * Returns the narrative Text of the LaboratorySpecialtySection.
	 *
	 * @return the narrative Text. Returns null, if this text does not exist.
	 */
	public String getNarrativeTextSectionLaboratorySpeciality() {
		if (getLaboratorySpecialtySection() != null
				&& getLaboratorySpecialtySection().getText() != null) {
			return getLaboratorySpecialtySection().getText().getText();
		}
		return null;
	}

	public List<ReferralOrderingPhysician> getReferralOrderingPhysicians() {
		List<ReferralOrderingPhysician> pl = new ArrayList<ReferralOrderingPhysician>();
		for (Participant1 p : getMdht().getParticipants()) {
			if (p instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.ReferralOrderingPhysician) {
				ReferralOrderingPhysician mdht = new ReferralOrderingPhysician(
						(org.openhealthtools.mdht.uml.cda.ihe.lab.ReferralOrderingPhysician) p);
				pl.add(mdht);
			}
		}
		return pl;
	}

	private String getSpecialitySectionTitle() {
		switch (this.getLanguageCode()) {
		case FRENCH:
			return ("Rapport de laboratoire");
		case GERMAN:
			return ("Laborbefund");
		case ITALIAN:
			return ("Rapporto di laboratorio");
		case ENGLISH:
			return ("Laboratory Specialty Section");
		}
		return "";
	}

	/**
	 * Creates an empty Custodian element, according to the LRXX specification.
	 */
	public void setEmtpyCustodian() {
		Custodian c = CDAFactory.eINSTANCE.createCustodian();
		AssignedCustodian ac = CDAFactory.eINSTANCE.createAssignedCustodian();
		CustodianOrganization co = CDAFactory.eINSTANCE.createCustodianOrganization();

		c.setAssignedCustodian(ac);
		ac.setRepresentedCustodianOrganization(co);

		// Id
		II ii = DatatypesFactory.eINSTANCE.createII();
		ii.setNullFlavor(NullFlavor.NASK);
		co.getIds().add(ii);

		// Name
		ON on = DatatypesFactory.eINSTANCE.createON();
		on.setNullFlavor(NullFlavor.NASK);
		co.setName(on);

		// Telecom
		TEL tel = DatatypesFactory.eINSTANCE.createTEL();
		tel.setNullFlavor(NullFlavor.NASK);
		co.setTelecom(tel);

		// Addr
		AD ad = DatatypesFactory.eINSTANCE.createAD();
		ad.setNullFlavor(NullFlavor.NASK);
		ADXP adxp = DatatypesFactory.eINSTANCE.createADXP();
		adxp.setNullFlavor(NullFlavor.NASK);
		ad.getStreetNames().add(adxp);
		co.setAddr(ad);

		getMdht().setCustodian(c);
	}

	/**
	 * Sets the section/text element for the LaboratorySpecialtySection.
	 *
	 * @param text
	 *          the text
	 */
	public void setNarrativeTextSectionLaboratorySpeciality(String text) {
		if (getLaboratorySpecialtySection() != null) {
			getLaboratorySpecialtySection().createStrucDocText(text);
		}
	}
}
