/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2016
 *
 *******************************************************************************/
package org.ehealth_connector.cda.ch.lab.lrtp;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.AbstractVitalSignObservation;
import org.ehealth_connector.cda.SectionAnnotationCommentEntry;
import org.ehealth_connector.cda.ch.lab.lrtp.enums.VitalSignList;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Value;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.cda.ihe.Comment;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;

/**
 * The Class VitalSignsObservation. <div class="en">Structured measurement /
 * observation (e.g. hight, weight, blood preasure)</div>
 * <div class="de">Strukturierte Angabe eines Messwerts resp. einer Beobachtung
 * zu einem einzelnen Vitalzeichen (wie z.B. Grösse, Gewicht, Blutdruck).</div>
 */
public class VitalSignObservation extends AbstractVitalSignObservation {

	/**
	 * Instantiates a new vital signs observation.
	 */
	public VitalSignObservation() {
		initMdht();
		setMethodCodeTranslation(null);
	}

	/**
	 * Instantiates a new vital sign observation.
	 *
	 * @param observation
	 *            <div class="en">Existing vital sign observation</div>
	 *            <div class="de">Existierende vital sign observation</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(
			org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation observation) {
		mVitalSignObservation = observation;
	}

	/**
	 * Instantiates the class with the required elements.
	 *
	 * @param code
	 *            the code according to Lrtp specification chap. 5.6.5
	 * @param value
	 *            the value according to [IHE PCC TF-2] 6.3.4.22.3
	 */
	public VitalSignObservation(VitalSignList code, Value value) {
		this();
		setCode(code.getCode());
		setValue(value);
		super.mVitalSignObservation.getInterpretationCodes().clear();
		super.mVitalSignObservation.setText(null);
	}

	/**
	 * Adds the comment entry.
	 *
	 * @param entry
	 *            the entry
	 */
	public void addCommentEntry(SectionAnnotationCommentEntry entry) {
		mVitalSignObservation.addAct(entry.getMdht());
		int nb = mVitalSignObservation.getEntryRelationships().size() - 1;
		mVitalSignObservation.getEntryRelationships().get(nb)
				.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
		mVitalSignObservation.getEntryRelationships().get(nb).setInversionInd(true);
	}

	// /**
	// * Gets the code enum.
	// *
	// * @return the code enum
	// */
	// public VitalSignList getCodeEnum() {
	// if (getCode() != null) {
	// return VitalSignList.getEnum(getCode().getCode());
	// }
	// return null;
	// }

	/**
	 * Gets the comment entries.
	 *
	 * @return the comment entries
	 */
	public List<SectionAnnotationCommentEntry> getCommentEntries() {
		ArrayList<SectionAnnotationCommentEntry> sacl = new ArrayList<SectionAnnotationCommentEntry>();
		for (EntryRelationship er : mVitalSignObservation.getEntryRelationships()) {
			if (er.getTypeCode().equals(x_ActRelationshipEntryRelationship.SUBJ)
					&& er.getInversionInd().equals(true)) {
				if (er.getAct().getTemplateIds().get(0).getRoot()
						.equals("2.16.840.1.113883.10.20.1.40")
						|| er.getAct().getTemplateIds().get(0).getRoot()
								.equals("1.3.6.1.4.1.19376.1.5.3.1.4.2")) {
					sacl.add(new SectionAnnotationCommentEntry((Comment) er.getAct()));
				}
			}
		}
		return sacl;
	}

	/**
	 * Gets the method code translation (translation of the code element as
	 * code, if present).
	 *
	 * @return the method code translation
	 */
	public Code getMethodCodeTranslation() {
		if (!mVitalSignObservation.getMethodCodes().isEmpty()
				&& !mVitalSignObservation.getMethodCodes().get(0).getTranslations().isEmpty()) {
			return new Code(mVitalSignObservation.getMethodCodes().get(0).getTranslations().get(0));
		}
		return null;
	}

	// /**
	// * Gets the observation interpretation code enum.
	// *
	// * @return the observation interpretation code enum
	// */
	// public ObservationInterpretationForVitalSign
	// getObservationInterpretationCodeEnum() {
	// if (!mVitalSignObservation.getInterpretationCodes().isEmpty()) {
	// return ObservationInterpretationForVitalSign
	// .getEnum(mVitalSignObservation.getInterpretationCodes().get(0).getCode());
	// }
	// return null;
	// }
	//
	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.cda.AbstractVitalSignObservation#initMdht()
	 */
	@Override
	protected void initMdht() {
		super.initMdht();
		Identificator id = new Identificator("2.16.756.5.30.1.1.1.1.3.4.1",
				"CDA-CH-LRTP.Body.VitalSignL3");
		mVitalSignObservation.getTemplateIds().add(id.getIi());
	}

	// /**
	// * Sets the code.
	// *
	// * @param code
	// * the new code
	// */
	// public void setCode(VitalSignList code) {
	// setCode(code.getCode());
	// }

	// /**
	// * Set a new interpretations of the vital sign observation.
	// *
	// * @param code
	// * <div class="de">Beurteilung des Resultats</div>
	// * <div class="fr"></div> <div class="it"></div>
	// */
	// public void setInterpretationCode(ObservationInterpretationForVitalSign
	// code) {
	// if (code != null) {
	// mVitalSignObservation.getInterpretationCodes().clear();
	// mVitalSignObservation.getInterpretationCodes().add(code.getCE());
	// }
	// }

	/**
	 * Sets the method code translation (code with NullFlavor.Na and the given
	 * translation)
	 *
	 * @param translation
	 *            the new method code translation
	 */
	public void setMethodCodeTranslation(Code translation) {
		mVitalSignObservation.getMethodCodes().clear();
		CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setNullFlavor(NullFlavor.NA);
		if (translation != null) {
			ce.getTranslations().add(translation.getCE());
		}
		mVitalSignObservation.getMethodCodes().add(ce);
	}
}
