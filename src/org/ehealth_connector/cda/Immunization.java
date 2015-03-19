/********************************************************************************
*
* The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
* All rights reserved. http://medshare.net
*
* Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
*
* This code is are made available under the terms of the Eclipse Public License v1.0.
*
* Accompanying materials are made available under the terms of the Creative Commons
* Attribution-ShareAlike 4.0 Switzerland License.
*
* Year of publication: 2015
*
********************************************************************************/


package org.ehealth_connector.cda;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Performer2;
import org.openhealthtools.mdht.uml.cda.ch.CDACHMSETBodyImmunizationL3Target;
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

import com.sun.istack.internal.Nullable;

/**
 * Dieses Element enthält die verabreichten Impfungen und die ausdrücklich nicht erwünschten Impfungen.
 */
public class Immunization {

  private org.openhealthtools.mdht.uml.cda.ch.Immunization mImmunization;

  /**
   * Instantiates a new immunization.
   */
  public Immunization() {
    mImmunization = CHFactory.eINSTANCE.createImmunization().init();
    mImmunization.setNegationInd(Boolean.FALSE);

    //Fix the TemplateID Extension of the CDA-CH.Body.MediL3 Template
    List<II> templateIds = mImmunization.getTemplateIds();
    for (II templateId: templateIds) {
      if (templateId.getRoot().equals("2.16.756.5.30.1.1.1.1.1")) {
        templateId.setExtension("CDA-CH.Body.MediL3");
      }
    }

    setPriorityCode(createPriorityCode());
    setRouteOfAdministration(null);
    setDosage(null);
  }

  /**
   * Dieses Element enthält die verabreichten Impfungen und die ausdrücklich nicht erwünschten Impfungen. 
   *
   * @author author Die eintragende Person
   * @param consumable Impfstoff
   * @param author <br>
   * 		<div class="de">Autor der Impfung</div>
   * 		<div class="fr"></div>
   * 		<div class="it"></div>
   * @param appliedAt Datum der Impfung
   */
  public Immunization(Consumable consumable, Author author, Date appliedAt) {
    this();
    mImmunization.setNegationInd(Boolean.FALSE);

    //mImmunization.setText(createText());
    setApplyDate(appliedAt);
    addId(null);
    setConsumable(consumable);
    setAuthor(author);
  }

  /**
   * Dieses Element enthält die verabreichten Impfungen und die ausdrücklich nicht erwünschten Impfungen. 
   *
   * @author author Die eintragende Person
   * @param consumable Impfstoff
   * @param author <br>
   * 		<div class="de">Autor der Impfung</div>
   * 		<div class="fr"></div>
   * 		<div class="it"></div>
   * @param appliedAt Datum der Impfung
   * @param route Einnahmeart (darf null sein)
   * @param doseQuantity in milliliters (e.g. 0.5) (darf null sein)
   */
  public Immunization(Consumable consumable, Author author, Date appliedAt, RouteOfAdministration route, Double doseQuantity) {
    this();
    mImmunization.setNegationInd(Boolean.FALSE);

    //mImmunization.setText(createText());
    setApplyDate(appliedAt);
    setRouteOfAdministration(route);
    setDosage(doseQuantity);
    addId(null);
    setConsumable(consumable);
    setAuthor(author);
  }

  /**
   * Constructor.
   *
   * @param immunization <br>
   * 		<div class="de">IHE Impf-Objekt</div>
   * 		<div class="fr"> immunization</div>
   * 		<div class="it"> immunization</div>
   */
  public Immunization(org.openhealthtools.mdht.uml.cda.ch.Immunization immunization) {
    mImmunization = immunization;
  }

  /**
   * Adds the id.
   *
   * @param codedId the new id
   */
  public void addId(Identificator codedId) {
    II ii = Util.createUuidVacdIdentificator(codedId);
    mImmunization.getIds().add(ii);
  }

  /**
   * Adds the reason for the immunization (the illness, which the immunization should prevent)
   *
   * @param code Code for the illness
   */
  public void addReason(Code code) {
    mImmunization.addObservation(createReason(code));
    mImmunization.getEntryRelationships().get(mImmunization.getEntryRelationships().size()-1).setTypeCode(x_ActRelationshipEntryRelationship.RSON);
    mImmunization.getEntryRelationships().get(mImmunization.getEntryRelationships().size()-1).setInversionInd(false);
  }

  private SXCM_TS convertDate(Date appliedAt) {
    SXCM_TS timestamp = DatatypesFactory.eINSTANCE.createSXCM_TS();
    timestamp.setValue(DateUtil.formatDate(appliedAt));
    return timestamp;
  }

  /**
   * Returns the encapsulated IHE class.
   * 
   * @return org.openhealthtools.mdht.uml.cda.ihe.Immunization
   */
  public org.openhealthtools.mdht.uml.cda.ch.Immunization copyMdhtImmunization() {
    return EcoreUtil.copy(mImmunization);
  }

  private CE createEpsosPivotCode() {
    CE code = DatatypesFactory.eINSTANCE.createCE();

    // TODO 141025, set correct code
    code.setCode("111164008");
    // TODO 141028, set correct display name
    code.setDisplayName("Poliomyelitis vaccine");

    code.setCodeSystem("1.3.6.1.4.1.12559.11.10.1.3.1.42.28");
    code.setCodeSystemName("epSOSVaccine");

    return code;
  }
  
  private Code createPriorityCode() {
    CD priorityCode = DatatypesFactory.eINSTANCE.createCD();
    priorityCode.setCode("R");
    priorityCode.setDisplayName("Routine");

    // TODO define OID constant
    priorityCode.setCodeSystem("2.16.840.1.113883.5.7");
    priorityCode.setCodeSystemName("ActPriority");
    return new Code(priorityCode);
  }
  
  private CDACHMSETBodyImmunizationL3Target createReason(Code code) {
    CDACHMSETBodyImmunizationL3Target t = CHFactory.eINSTANCE.createCDACHMSETBodyImmunizationL3Target().init();
    //Fix Template ID
    for (II i : t.getTemplateIds()) {
      if (i.getRoot().equals("2.16.756.5.30.1.1.1.1.3.2.1")) {
        i.setExtension("CDA-CH.MSET.Body.ImmunizationL3.Target");
      }
    }
    //Set Status Code
    t.setStatusCode(StatusCode.COMPLETED.getCS());
    
    //Set Code
    t.setCode(code.getCD());
    
    return t;
  }
  
  /**
   * Gets the apply date.
   *
   * @return the apply date
   */
  public Date getApplyDate() {
    SXCM_TS date = mImmunization.getEffectiveTimes().get(0);
    return DateUtil.parseDateyyyyMMdd(date.getValue());
  }

  /**
   * Returns author of this immunization.
   * 
   * @return Author
   */
  public Author getAuthor() {
    try {
      org.openhealthtools.mdht.uml.cda.Author author = EcoreUtil.copy(mImmunization.getAuthors().get(0));
      return new Author(author);
    } catch(IndexOutOfBoundsException e) {
      // no author available
      return null;
    }

  }

  /**
   * Gets the reference to the comment in the level 2 section text.
   *
   * @return the reference of the level 3 comment entry, which point to the level 2 text
   */
  public String getCommentRef() {
    return Util.getCommentRef(mImmunization.getEntryRelationships());
  }
  
  /**
   * Gets the text of the comment text element (this is not necessarily the comment itself)
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
    if (mImmunization.getDoseQuantity()!=null) {
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
		  if (mImmunization.getPerformers().get(0)!=null) {
			  return new Performer(mImmunization.getPerformers().get(0));
		  }
	  }
	return null;
  }

  /** 
   * A set of codes (e.g., for routine, emergency), specifying the urgency under which the Act happened, can happen, is happening, is intended to happen, or is requested/demanded to happen.
   * Codesystem: 2.16.840.1.113883.5.7
   * 
   * @return priorityCode
   */
  public Code getPriorityCode() {
	  return new Code(mImmunization.getPriorityCode());
  }
  
  /**
   * Gets a list of reasons for the immunization (the illnesses, which the immunization should prevent). 
   *
   * @return A ArrayList of Code  
   * 
   */
  public ArrayList<Code> getReasons() {
    ArrayList<Code> cl = new ArrayList<Code>();
    EList<EntryRelationship> erl = mImmunization.getEntryRelationships();
    for (EntryRelationship er : erl) {
      if (er.getTypeCode().equals(x_ActRelationshipEntryRelationship.RSON)) {
        Observation o = er.getObservation();
        cl.add(new Code (o.getCode()));
      }
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
   * @param appliedAt the new apply date
   */
  public void setApplyDate(Date appliedAt) {
    mImmunization.getEffectiveTimes().add(convertDate(appliedAt));
  }

  /**
   * Sets the author.
   *
   * @param author the new author
   */
  public void setAuthor(Author author) {
    mImmunization.getAuthors().clear();
    mImmunization.getAuthors().add(author.copyMdhtAuthor());
  }

  /**
   * Sets a comment text
   *
   * @param text the text
   */
  public void setCommentText(String text) {
    Comment mComment = IHEFactory.eINSTANCE.createComment().init();
    ED ed = DatatypesFactory.eINSTANCE.createED();
    ed.addText(text);
    mComment.setText(ed);
    //mComment.setText(Util.createEd(text));
    mImmunization.addAct(mComment);
          
    EntryRelationship er = mImmunization.getEntryRelationships().get(mImmunization.getEntryRelationships().size()-1);
    er.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
    er.setInversionInd(true);
  }

  /**
   * Sets the consumable.
   *
   * @param consumable the new consumable
   */
  public void setConsumable(Consumable consumable) {
    mImmunization.setConsumable(consumable.copyMdhtConsumable());
  }
  
  /**
   * Sets the dosage.
   *
   * @param doseQuantity the new dosage (use null, if not known)
   */
  public void setDosage(@Nullable Double doseQuantity) {
    if (doseQuantity==null) {
      mImmunization.setDoseQuantity(Util.createIVL_PQNullFlavorNA());
    }
    else {
      IVL_PQ ivl_pq = DatatypesFactory.eINSTANCE.createIVL_PQ();
      ivl_pq.setUnit("ml");
      ivl_pq.setValue(doseQuantity);
      mImmunization.setDoseQuantity(ivl_pq);
    }
  }
  
  /**
   * Sets the Person, who performs the Immunization 
   *
   * @param performer the new performer (Convenience Author will be converted to a performer)
   */  
  public void setPerformer(Author performer) {
	  Performer2 p2 = CDAFactory.eINSTANCE.createPerformer2();
	  mImmunization.getPerformers().clear();
	  mImmunization.getPerformers().add(p2);
	  
	  p2.setAssignedEntity(Util.createAssignedEntityFromAssignedAuthor(performer.copyMdhtAuthor().getAssignedAuthor()));
	  try {
		p2.setTime(DateUtil.createIVL_TSFromEuroDate(new Date()));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  /**
   * Sets the Person, who performs the Immunization 
   *
   * @param performer the new performer
   */  
  public void setPerformer(Performer performer) {
	  mImmunization.getPerformers().clear();
	  mImmunization.getPerformers().add(performer.copyMdhtPerfomer());
  }

  /** 
   * A set of codes (e.g., for routine, emergency), specifying the urgency under which the Act happened, can happen, is happening, is intended to happen, or is requested/demanded to happen.
   * Codesystem: 2.16.840.1.113883.5.7
   * 
   * @param priorityCode
   */
  public void setPriorityCode(Code priorityCode) {
    mImmunization.setPriorityCode(priorityCode.getCE());
  }

  /**
   * Optionally, one can set the route code (Einnahmearten).
   * If not set, <routeCode nullFlavor="UNK"/> is written to CDA document.
   *
   * @param routeCode the new route of administration
   */
  public void setRouteOfAdministration(RouteOfAdministration routeCode) {
    if (routeCode == null) {
      CE ce = DatatypesFactory.eINSTANCE.createCE();
      ce.setNullFlavor(NullFlavor.UNK);
      mImmunization.setRouteCode(ce);
    }
    else {
      mImmunization.setRouteCode(routeCode.getCE());
    }
  }

}
