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
 * Year of publication: 2015
 *
 *******************************************************************************/

package org.ehealth_connector.cda;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.ch.CdaCh;
import org.ehealth_connector.cda.ch.enums.MedicationsSpecialConditions;
import org.ehealth_connector.cda.ch.enums.RouteOfAdministration;
import org.ehealth_connector.cda.enums.StatusCode;
import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.DateUtil;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Performer;
import org.ehealth_connector.common.Util;
import org.ehealth_connector.common.Value;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.cda.Performer2;
import org.openhealthtools.mdht.uml.cda.ch.CDACHMSETBodyImmunizationL3Reason;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.cda.ihe.Comment;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.SXCM_TS;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentSubstanceMood;

/**
 * <div class="en">This element contains the given immunizations and the
 * immunizations, which are not wanted.</div><div class="de">Dieses Element
 * enthält die verabreichten Impfungen und die ausdrücklich nicht erwünschten
 * Impfungen.</div>
 */
public class Immunization {

	private final org.openhealthtools.mdht.uml.cda.ch.Immunization mImmunization;

	/**
	 * <div class="en">Creates an object, which represents an
	 * immunization.</div><div class="de">Erzeugt ein Objekt welches eine Impfung
	 * repräsentiert. Dieser Konstruktor wird verwendet, wenn ein leeres Object
	 * initialisiert werden soll</div>
	 */
	public Immunization() {
		mImmunization = CHFactory.eINSTANCE.createImmunization().init();
		mImmunization.setNegationInd(Boolean.FALSE);

		// Get the TemplateIds save them, clear the list and add the modified
		// item again
		List<II> savedTemplateIds = (List<II>) EcoreUtil.copyAll(mImmunization.getTemplateIds());
		mImmunization.getTemplateIds().clear();

		int i = 0;
		for (II templateId : savedTemplateIds) {
			// Fix the extension for the swiss template id
			if (templateId.getRoot().equals(CdaCh.OID_V1)) {
				templateId.setExtension("CDA-CH.Body.MediL3");
			}
			// Add every template that is not Immunization
			if (!templateId.getRoot().equals("1.3.6.1.4.1.19376.1.5.3.1.4.12")) {
				mImmunization.getTemplateIds().add(templateId);
			} else {
				i++;
				// Add only the first Immunization Template Id
				if (i < 2) {
					mImmunization.getTemplateIds().add(templateId);
				}
			}

		}

		setRouteOfAdministration(null);
		setDosage(null);
		setPriorityCode(createPriorityCode());
	}

	/**
	 * <div class="en">This element contains the administered immunizations and
	 * the immunizations, which are not wanted.</div><div class="de">Dieses
	 * Element enthält die verabreichten Impfungen und die ausdrücklich nicht
	 * erwünschten Impfungen.</div>
	 * 
	 * @param consumable
	 *          <div class="en">vaccine</div><div class="de">Impfstoff</div>
	 * 
	 * @param author
	 *          <div class="en">Author of the immunization</div> <div
	 *          class="de">Autor der Impfung</div>
	 * @param appliedAt
	 *          <div class="en">date of the immunization</div><div
	 *          class="de">Datum der Impfung</div>
	 */
	public Immunization(Consumable consumable, Author author, Date appliedAt) {
		this();
		mImmunization.setNegationInd(Boolean.FALSE);

		setApplyDate(appliedAt);
		addId(null);
		setConsumable(consumable);
		setAuthor(author);
	}

	/**
	 * <div class="en">This element contains the administered immunizations and
	 * the immunizations, which are not wanted.</div><div class="de">Dieses
	 * Element enthält die verabreichten Impfungen und die ausdrücklich nicht
	 * erwünschten Impfungen.</div>
	 * 
	 * @param consumable
	 *          <div class="en">vaccine</div><div class="de">Impfstoff</div>
	 * 
	 * @param author
	 *          <div class="en">Author of the immunization</div> <div
	 *          class="de">Autor der Impfung</div>
	 * @param appliedAt
	 *          <div class="en">date of the immunization</div><div
	 *          class="de">Datum der Impfung</div>
	 * @param route
	 *          <div class="en">route of administration (can be null></div><div
	 *          class="de">Einnahmeart (darf null sein)</div>
	 * 
	 * @param doseQuantity
	 *          in milliliters (e.g. 0.5) (can be null)
	 */
	public Immunization(Consumable consumable, Author author, Date appliedAt,
			RouteOfAdministration route, Double doseQuantity) {
		this();
		mImmunization.setNegationInd(Boolean.FALSE);

		setApplyDate(appliedAt);
		setRouteOfAdministration(route);
		setDosage(doseQuantity);
		addId(null);
		setConsumable(consumable);
		setAuthor(author);
	}

	/**
	 * Erzeugt ein Objekt welches eine Impfung repräsentiert. Dieser Konstruktor
	 * wird verwendet, wenn codiert werden soll, dass keine Impfungen bekannt
	 * sind.
	 * 
	 * @param specialCode
	 *          the special code that indicates, why no information is present in
	 *          this element
	 * @param author
	 *          the author of this immunization
	 */
	public Immunization(MedicationsSpecialConditions specialCode, Author author) {
		this();
		setAuthor(author);
		setCode(specialCode);
	}

	/**
	 * Constructor.
	 * 
	 * @param immunization
	 * <br>
	 *          <div class="de">IHE Impf-Objekt</div> <div class="fr">
	 *          immunization</div> <div class="it"> immunization</div>
	 */
	public Immunization(org.openhealthtools.mdht.uml.cda.ch.Immunization immunization) {
		mImmunization = immunization;
	}

	/**
	 * Adds the id.
	 * 
	 * @param codedId
	 *          the new id
	 */
	public void addId(Identificator codedId) {
		II ii = Util.createUuidVacdIdentificator(codedId);
		mImmunization.getIds().add(ii);
	}

	/**
	 * Adds the reason for the immunization (the illness, which the immunization
	 * should prevent)
	 * 
	 * @param reason
	 *          the reason
	 */
	public void addReason(Reason reason) {
		mImmunization.addObservation(reason.copyMdhtCDACHMSETBodyImmunizationL3Reason());
		mImmunization.getEntryRelationships().get(mImmunization.getEntryRelationships().size() - 1)
				.setTypeCode(x_ActRelationshipEntryRelationship.RSON);
		mImmunization.getEntryRelationships().get(mImmunization.getEntryRelationships().size() - 1)
				.setInversionInd(false);
	}

	/**
	 * Returns the encapsulated IHE class.
	 * 
	 * @return org.openhealthtools.mdht.uml.cda.ihe.Immunization
	 */
	public org.openhealthtools.mdht.uml.cda.ch.Immunization copyMdhtImmunization() {
		return EcoreUtil.copy(mImmunization);
	}

	/**
	 * Gets the apply date.
	 * 
	 * @return the apply date
	 */
	public Date getApplyDate() {
		if (mImmunization.getEffectiveTimes() != null && mImmunization.getEffectiveTimes().size() > 0) {
			SXCM_TS date = mImmunization.getEffectiveTimes().get(0);
			return DateUtil.parseDateyyyyMMdd(date.getValue());
		} else
			return null;
	}

	/**
	 * Returns author of this immunization.
	 * 
	 * @return Author
	 */
	public Author getAuthor() {
		try {
			org.openhealthtools.mdht.uml.cda.Author author = EcoreUtil.copy(mImmunization.getAuthors()
					.get(0));
			return new Author(author);
		} catch (IndexOutOfBoundsException e) {
			// no author available
			return null;
		}

	}

	/**
	 * Gets the reference to the comment in the level 2 section text.
	 * 
	 * @return the reference of the level 3 comment entry, which point to the
	 *         level 2 text
	 */
	public String getCommentRef() {
		return Util.getCommentRef(mImmunization.getEntryRelationships());
	}

	/**
	 * Gets the text of the comment text element (this is not necessarily the
	 * comment itself)
	 * 
	 * @return the comment text
	 */
	public String getCommentText() {
		return Util.getCommentText(mImmunization.getEntryRelationships());
	}

	/**
	 * Gets the consumable.
	 * 
	 * @return the consumable
	 */
	public Consumable getConsumable() {
		Consumable consumable = new Consumable(mImmunization.getConsumable());
		return consumable;
	}

	/**
	 * Gibt die Dosis der Impfung zurück.
	 * 
	 * @return Dosis Dosis der Impfung
	 */
	public Value getDosage() {
		if (mImmunization.getDoseQuantity() != null) {
			Value value = new Value(mImmunization.getDoseQuantity());
			return value;
		}
		return null;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Identificator getId() {
		Identificator id = new Identificator(mImmunization.getIds().get(0));
		return id;
	}

	/**
	 * <div class="en">Gets the information, if an immunization is intended, but
	 * not performed, yet. (moodCode:INT).</div> <div class="de">Liefert die
	 * Information, ob eine Impfung beabsichtigt, aber noch nicht erfolgt ist
	 * (moodCode:INT).</div> <div class="fr"></div> <div class="it"></div>
	 * 
	 * @return <div class="en"> true, if an immunization is intended. Otherwise:
	 *         false</div> <div class="de"> true, wenn eine Impfung beabsichtigt,
	 *         aber noch nicht erfolgt ist. Sonst: false</div>
	 */
	public boolean getIntended() {
		if (mImmunization.getMoodCode().equals(x_DocumentSubstanceMood.INT))
			return true;
		else
			return false;
	}

	/**
	 * Returns the encapsulated IHE class.
	 * 
	 * @return org.openhealthtools.mdht.uml.cda.ihe.Immunization
	 */
	public org.openhealthtools.mdht.uml.cda.ihe.Immunization getMdhtImmunization() {
		return mImmunization;
	}

	/**
	 * Gets the Performer (Person, die die Impfung durchgeführt hat)
	 * 
	 * @return the performer
	 */
	public Performer getPerformer() {
		if (mImmunization.getPerformers() != null) {
			if (mImmunization.getPerformers().get(0) != null) {
				return new Performer(mImmunization.getPerformers().get(0));
			}
		}
		return null;
	}

	/**
	 * A set of codes (e.g., for routine, emergency), specifying the urgency under
	 * which the Act happened, can happen, is happening, is intended to happen, or
	 * is requested/demanded to happen. Codesystem: 2.16.840.1.113883.5.7
	 * 
	 * @return priorityCode
	 */
	public Code getPriorityCode() {
		return new Code(mImmunization.getPriorityCode());
	}

	/**
	 * Gets a list of reasons for the immunization (the illness, which the
	 * immunization should prevent).
	 * 
	 * @return A ArrayList of Reasons
	 * 
	 */
	public ArrayList<Reason> getReasons() {
		ArrayList<Reason> cl = new ArrayList<Reason>();
		EList<CDACHMSETBodyImmunizationL3Reason> erl = mImmunization
				.getCDACHMSETBodyImmunizationL3Reasons();
		for (CDACHMSETBodyImmunizationL3Reason er : erl) {
			cl.add(new Reason(er));
		}
		return cl;
	}

	/**
	 * Gets the route of administration.
	 * 
	 * @return the route of administration
	 */
	public RouteOfAdministration getRouteOfAdministration() {
		return RouteOfAdministration.getEnum(mImmunization.getRouteCode().getCode());
	}

	/**
	 * Sets the apply date.
	 * 
	 * @param appliedAt
	 *          the new apply date
	 */
	public void setApplyDate(Date appliedAt) {
		mImmunization.getEffectiveTimes().clear();
		mImmunization.getEffectiveTimes().add(convertDate(appliedAt));
	}

	/**
	 * Sets the author.
	 * 
	 * @param author
	 *          the new author
	 */
	public void setAuthor(Author author) {
		mImmunization.getAuthors().clear();
		org.openhealthtools.mdht.uml.cda.Author immmunizationAuthor = author.copyMdhtAuthor();
		// Remove author function Code if present
		if (immmunizationAuthor.getFunctionCode() != null) {
			CE ce = null;
			immmunizationAuthor.setFunctionCode(ce);
		}
		mImmunization.getAuthors().add(immmunizationAuthor);
	}

	/**
	 * Sets a special code that explains the absence of immunizations in this
	 * document
	 * 
	 * @param specialCode
	 *          expresses a special condition for this element
	 */
	public void setCode(MedicationsSpecialConditions specialCode) {
		mImmunization.setCode(specialCode.getCD());
		mImmunization.setStatusCode(StatusCode.COMPLETED.getCS());
		CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setNullFlavor(NullFlavor.UNK);
		mImmunization.setPriorityCode(ce);
		mImmunization.setDoseQuantity(Util.createIVL_PQNullFlavorUNK());
		mImmunization.getEffectiveTimes().add(DateUtil.createSTCM_TS(new Date()));
		mImmunization.getIds().add(Util.createUuidVacd(null));
		Consumable c = new Consumable(false);
		setConsumable(c);
	}

	/**
	 * Sets a comment text
	 * 
	 * @param text
	 *          the text
	 */
	public void setCommentText(String text) {
		Comment mComment = IHEFactory.eINSTANCE.createComment().init();
		ED ed = DatatypesFactory.eINSTANCE.createED();
		ed.addText(text);
		mComment.setText(ed);
		mImmunization.addAct(mComment);

		EntryRelationship er = mImmunization.getEntryRelationships().get(
				mImmunization.getEntryRelationships().size() - 1);
		er.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
		er.setInversionInd(true);
	}

	/**
	 * Sets the consumable.
	 * 
	 * @param consumable
	 *          the new consumable
	 */
	public void setConsumable(Consumable consumable) {
		mImmunization.setConsumable(consumable.copyMdhtConsumable());
	}

	/**
	 * Sets the dosage.
	 * 
	 * @param doseQuantity
	 *          the new dosage (use null, if not known)
	 */
	public void setDosage(Double doseQuantity) {
		if (doseQuantity == null) {
			mImmunization.setDoseQuantity(Util.createIVL_PQNullFlavorUNK());
		} else {
			IVL_PQ ivl_pq = DatatypesFactory.eINSTANCE.createIVL_PQ();
			ivl_pq.setUnit("ml");
			ivl_pq.setValue(doseQuantity);
			mImmunization.setDoseQuantity(ivl_pq);
		}
	}

	/**
	 * <div class="en">Sets the information that an immunization is intended, but
	 * not performed, yet. (moodCode:INT).</div> <div class="de">Setzt, die
	 * Information, dass eine Impfung beabsichtigt, aber noch nicht erfolgt ist
	 * (moodCode:INT).</div> <div class="fr"></div> <div class="it"></div>
	 * 
	 */
	public void setIntended() {
		mImmunization.setMoodCode(x_DocumentSubstanceMood.INT);
	}

	/**
	 * Sets the Person, who performs the Immunization
	 * 
	 * @param performer
	 *          the new performer (Convenience Author will be converted to a
	 *          performer)
	 */
	public void setPerformer(Author performer) {
		Performer2 p2 = CDAFactory.eINSTANCE.createPerformer2();
		mImmunization.getPerformers().clear();
		mImmunization.getPerformers().add(p2);

		p2.setAssignedEntity(Util.createAssignedEntityFromAssignedAuthor(performer.copyMdhtAuthor()
				.getAssignedAuthor()));
		try {
			p2.setTime(DateUtil.createIVL_TSFromEuroDate(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the Person, who performs the Immunization
	 * 
	 * @param performer
	 *          the new performer
	 */
	public void setPerformer(Performer performer) {
		mImmunization.getPerformers().clear();
		mImmunization.getPerformers().add(performer.copyMdhtPerfomer());
	}

	/**
	 * A set of codes (e.g., for routine, emergency), specifying the urgency under
	 * which the Act happened, can happen, is happening, is intended to happen, or
	 * is requested/demanded to happen. Codesystem: 2.16.840.1.113883.5.7
	 * 
	 * @param priorityCode
	 *          the priority code
	 */
	public void setPriorityCode(Code priorityCode) {
		mImmunization.setPriorityCode(priorityCode.getCE());
	}

	/**
	 * Optionally, one can set the route code (Einnahmearten). If not set,
	 * 'routeCode nullFlavor="UNK"' is written to CDA document.
	 * 
	 * @param routeCode
	 *          the new route of administration
	 */
	public void setRouteOfAdministration(RouteOfAdministration routeCode) {
		if (routeCode == null) {
			CE ce = DatatypesFactory.eINSTANCE.createCE();
			ce.setNullFlavor(NullFlavor.UNK);
			mImmunization.setRouteCode(ce);
		} else {
			mImmunization.setRouteCode(routeCode.getCE());
		}
	}

	private SXCM_TS convertDate(Date appliedAt) {
		SXCM_TS timestamp = DatatypesFactory.eINSTANCE.createSXCM_TS();
		timestamp.setValue(DateUtil.formatDate(appliedAt));
		return timestamp;
	}

	private Code createPriorityCode() {
		CD priorityCode = DatatypesFactory.eINSTANCE.createCD();
		priorityCode.setNullFlavor(NullFlavor.UNK);
		return new Code(priorityCode);
	}

}
