package org.ehealth_connector.cda.ch.lab.lrph;

import java.util.List;

import org.ehealth_connector.cda.ch.lab.AbstractLaboratoryReport;
import org.ehealth_connector.cda.ch.lab.LaboratoryBatteryOrganizer;
import org.ehealth_connector.cda.enums.LanguageCode;
import org.ehealth_connector.cda.ihe.lab.LaboratorySpecialtySection;
import org.ehealth_connector.common.Code;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;

public class CdaChLrph
		extends AbstractLaboratoryReport<org.openhealthtools.mdht.uml.cda.ch.CdaChLrph> {

	/**
	 * Instantiates a new cda ch mtps dis.
	 */
	public CdaChLrph(Code code) {
		this(code, LanguageCode.ENGLISH);
	}

	/**
	 * Instantiates a new cda ch lrph.
	 *
	 * @param languageCode
	 *          the language code
	 */
	public CdaChLrph(Code code, LanguageCode languageCode) {
		super(CHFactory.eINSTANCE.createCdaChLrph().init());
		super.initCda();
		LaboratorySpecialtySection specialtySection = new LaboratorySpecialtySection(code,
				languageCode);
		this.getDoc().addSection(specialtySection.getMdht());
	}

	/**
	 * Instantiates a new cda ch lrph.
	 *
	 * @param doc
	 *          mdht model document
	 */
	public CdaChLrph(org.openhealthtools.mdht.uml.cda.ch.CdaChLrph doc) {
		super(doc);
	}

	// Convenience function
	// Creates LaboratorySpecialtySection
	// Creates SpecimenAct
	// adds the Laboratory Battery to the SpecimenAct
	public void addLaboratoryBatteryOrganizer(LaboratoryBatteryOrganizer organizer) {

	}

	// Convenience Function
	// - Scan Observations for pseudonymization / anonymization related
	// NotifiableObservationLoinc or NotifiableObservationSnomed
	// - If such an element exists, use getPrivacyFilter() to determine
	// anonymization function
	// - Apply Anonymization function
	public void applyPrivacyFilter() {

	}

	// Convenience function
	// gets the LaboratorySpecialtySection
	// gets the SpecimenAct
	// gets the Laboratory Batteries from the SpecimenAct
	public List<LaboratoryBatteryOrganizer> getLaboratoryBatteryOrganizerList() {
		return null;

	}

	/**
	 * Gets the laboratory specialty section.
	 *
	 * @return the laboratory specialty section
	 */
	public List<LaboratorySpecialtySection> getLaboratorySpecialtySection() {
		return null;
	}
}
