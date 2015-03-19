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

import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.openhealthtools.mdht.uml.cda.ExternalDocument;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Performer2;
import org.openhealthtools.mdht.uml.cda.ch.CDACHMSETBodyImmunizationL3Target;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.cda.ihe.Comment;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.SXCM_TS;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClassDocument;
import org.openhealthtools.mdht.uml.hl7.vocab.ActMood;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentSubstanceMood;

import com.sun.istack.internal.Nullable;

/**
 * Dieses Element enthält den empfohlenen Impfplan für den Patienten. Zudem kann zur Begründung ein Verweis auf entsprechende Guidelines angegeben werden.
 * Dieses Kapitel ist KONDITIONAL und nur dann erforderlich, wenn es sich beim Dokument um die Antwort aus einem Expertensystem für Impfempfehlungen handelt (Clinical Decision Support System; CDSS) oder wenn beabsichtigte aber noch nicht erfolgte Impfungen dokumentiert werden.
 */
public class ImmunizationRecommendation {

  private org.openhealthtools.mdht.uml.cda.ch.ImmunizationRecommendation mImmunizationRecommendation;

  /**
   * Instantiates a new immunization recommendation.
   */
  public ImmunizationRecommendation() {
    mImmunizationRecommendation = CHFactory.eINSTANCE.createImmunizationRecommendation().init();

    //Fix the TemplateID Extension of the CDA-CH.Body.MediL3 Template
    List<II> templateIds = mImmunizationRecommendation.getTemplateIds();
    for (II templateId: templateIds) {
      if (templateId.getRoot().equals("2.16.756.5.30.1.1.1.1.1")) {
        templateId.setExtension("CDA-CH.Body.MediL3");
      }
    }

    //Set the null values       
    mImmunizationRecommendation.setPriorityCode(Util.createCENullFlavorNASK());
    mImmunizationRecommendation.setRouteCode(Util.createCENullFlavorNASK());
    mImmunizationRecommendation.setDoseQuantity(Util.createIVL_PQNullFlavorNASK());
    mImmunizationRecommendation.setRateQuantity(Util.createIVL_PQNullFlavorNASK());   
  }

  /**
   * Erzeugt ein Objekt welches eine Impfempfehlung repräsentiert. Dieses Objekt
   * kann einer ImmunizationRecommendationsSection hinzugefügt werden.
   * @param consumable 
   * 			Impfstoff, der empfohlen wird
   * @param author
   *          Arzt, der diese Eintragung veranlasst hat
   * @param startOfPossibleAppliance
   * 			Beginn des Zeitraumes, in dem die Impfung erfolgen sollte
   * @param endOfPossibleAppliance 
   * 			Ende des Zeitraumes, in dem die Impfung erfolgen sollte
   * @param intendedOrProposed
   *          true, bei einer beabsichtigten, aber noch nicht erfolgten Impfung.
   *          false bei einer vorgeschlagenen Impfung.
   * @param shallNotBeAdministerd
   *          true, wenn die Impfung nicht verabreicht werden soll. false, wenn
   *          die Impfung zu verabreichen ist.
   */
  public ImmunizationRecommendation (Consumable consumable,
      org.ehealth_connector.common.Author author, Date startOfPossibleAppliance, Date endOfPossibleAppliance,
      boolean intendedOrProposed, boolean shallNotBeAdministerd) {      

    this();
    setConsumable(consumable);

    //Set the Attributes of this class
    addId(null);
    setIntendedOrProposed(intendedOrProposed);
    setShallNotBeAdministerd(shallNotBeAdministerd);
    setPossibleAppliance(startOfPossibleAppliance, endOfPossibleAppliance);
    setAuthor(author);
  }


  /**
   * Instantiates a new immunization recommendation.
   *
   * @param immunizationRecommendation <br>
   * 		<div class="de">Impfempfehlung</div>
   * 		<div class="fr"></div>
   * 		<div class="it"></div>
   */
  public ImmunizationRecommendation (org.openhealthtools.mdht.uml.cda.ch.ImmunizationRecommendation immunizationRecommendation) {
    mImmunizationRecommendation = immunizationRecommendation;
  }

  /**
   * <div class="de">Copy mdht immunization recommendation.</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   *
   * @return the org.openhealthtools.mdht.uml.cda.ch. immunization recommendation
   */
  public org.openhealthtools.mdht.uml.cda.ch.ImmunizationRecommendation copyMdhtImmunizationRecommendation() {
    return EcoreUtil.copy(mImmunizationRecommendation);
  }

  /**
   * Adds the id.
   *
   * @param id the new id
   */
  public void addId(Identificator id) {
    II ii = Util.createUuidVacdIdentificator(id);
    mImmunizationRecommendation.getIds().add(ii);
  }
  
  /**
   * Gets the author.
   *
   * @return the author
   */
  public org.ehealth_connector.common.Author getAuthor() {
    try {
      org.openhealthtools.mdht.uml.cda.Author author = EcoreUtil.copy(mImmunizationRecommendation.getAuthors().get(0));
      return new Author(author);
    } catch(IndexOutOfBoundsException e) {
      // no author available
      return null;
    }
  }

  /**
   * Gets the consumable.
   *
   * @return the consumable
   */
  public Consumable getConsumable() {
    Consumable consumable = new Consumable(mImmunizationRecommendation.getConsumable());
    return consumable;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Identificator getId() {
    Identificator id = new Identificator(mImmunizationRecommendation.getIds().get(0));
    return id;
  }

  /**
   * <div class="de">Gibt zurück, ob eine Impfung beabsichtigt, aber noch nicht erfolgt, oder vorgeschlagen ist (moodCode).</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   * @return true, wenn eine Impfung beabsichtigt, aber noch nicht erfolgt ist. false, wenn eine Impfung vorgeschlagen ist.
   */
  public boolean getIntendedOrProposed() {
    if (mImmunizationRecommendation.getMoodCode().equals(x_DocumentSubstanceMood.INT)) return true;
    if (mImmunizationRecommendation.getMoodCode().equals(x_DocumentSubstanceMood.PRP)) return false;
    return true;
  }

  /**
   * Gets the reference to the comment in the level 2 section text.
   *
   * @return the reference of the level 3 comment entry, which point to the level 2 text
   */
  public String getCommentRef() {
	  return Util.getCommentRef(mImmunizationRecommendation.getEntryRelationships());
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
    mImmunizationRecommendation.addAct(mComment);
          
    EntryRelationship er = mImmunizationRecommendation.getEntryRelationships().get(mImmunizationRecommendation.getEntryRelationships().size()-1);
    er.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
    er.setInversionInd(true);
  }
  
  /**
   * Gets the text of the comment text element (this is not necessarily the comment itself)
   *
   * @return the comment text
   */
  public String getCommentText() {
    return Util.getCommentText(mImmunizationRecommendation.getEntryRelationships());
  }
  
  /**
   * Gets the mdht immunization recommendation.
   *
   * @return the mdht immunization recommendation
   */
  public org.openhealthtools.mdht.uml.cda.ch.ImmunizationRecommendation getMdhtImmunizationRecommendation() {
    return mImmunizationRecommendation;
  }

  /**
   * <div class="de">Liefert, den Zeitraum, in dem die Impfung verabreicht werden soll als String (z.B. "01.01.2015 - 01.03.1015")</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   *
   * @return 
   *      <div class="de">Zeitraum, in dem die Impfung verabreicht werden soll als String</div>
   *      <div class="fr"></div>
   *      <div class="it"></div>
   */
  public String getPossibleAppliance() {
    List<SXCM_TS> effectiveTimes = mImmunizationRecommendation.getEffectiveTimes();
    return DateUtil.convertSXCM_TSToEurString(effectiveTimes);
  }
  
  /**
   * Gets the Performer (Person, die die Impfung durchgeführt hat)
   *
   * @return the performer
   */
  public Performer getPerformer() {
	  if (mImmunizationRecommendation.getPerformers() != null) {
		  if (mImmunizationRecommendation.getPerformers().get(0)!=null) {
			  return new Performer(mImmunizationRecommendation.getPerformers().get(0));
		  }
	  }
	return null;
  }

  /**
   * <div class="de">Gibt an, ob eine Impfung nicht verabreicht werden soll.</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   *
   * @return true, wenn die Impfung nicht verabreicht werden soll, sonst false
   */
  public boolean gettShallNotBeAdministerd() {
    return mImmunizationRecommendation.getNegationInd();
  }

  /**
   * Sets the author.
   *
   * @param author the new author
   */
  public void setAuthor(org.ehealth_connector.common.Author author) {
    mImmunizationRecommendation.getAuthors().clear();
    mImmunizationRecommendation.getAuthors().add(author.copyMdhtAuthor());
  }

  /**
   * Sets the consumable.
   *
   * @param consumable the new consumable
   */
  public void setConsumable(Consumable consumable) {
    mImmunizationRecommendation.setConsumable(consumable.copyMdhtConsumable());  
  }
  
  /**
   * Sets the dosage.
   *
   * @param doseQuantity the new dosage (use null, if not asked)
   */
  public void setDosage(@Nullable Double doseQuantity) {
    if (doseQuantity==null) {
      mImmunizationRecommendation.setDoseQuantity(Util.createIVL_PQNullFlavorNA());
    }
    else {
      IVL_PQ ivl_pq = DatatypesFactory.eINSTANCE.createIVL_PQ();
      ivl_pq.setUnit("ml");
      ivl_pq.setValue(doseQuantity);
      mImmunizationRecommendation.setDoseQuantity(ivl_pq);
    }
  }
  
  /**
   * Gibt die Dosis der Impfung zurück.
   *
   * @return Dosis Dosis der Impfung
   */
  public Value getDosage() {
    if (mImmunizationRecommendation.getDoseQuantity()!=null) {
      Value value = new Value(mImmunizationRecommendation.getDoseQuantity());
      return value;
    }
    return null;
  }	

  /**
   * <div class="de">Setzt, ob eine Impfung beabsichtigt, aber noch nicht erfolgt, oder vorgeschlagen ist (moodCode).</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   * @param intendedOrProposed true, wenn eine Impfung beabsichtigt, aber noch nicht erfolgt ist. false, wenn eine Impfung vorgeschlagen ist.
   */
  public void setIntendedOrProposed(boolean intendedOrProposed) {
    if (intendedOrProposed) {
      mImmunizationRecommendation.setMoodCode(x_DocumentSubstanceMood.INT);
    }
    else {
      mImmunizationRecommendation.setMoodCode(x_DocumentSubstanceMood.PRP);
    }
  }

  /**
   * <div class="de">Setzt, den Zeitraum, in dem die Impfung verabreicht werden soll.</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   *
   * @param startOfPossibleAppliacne <br>
   * 		<div class="de">Startpunkt des Zeitraumes, wann die Impfung verabreicht werden soll.</div>
   * 		<div class="fr"></div>
   * 		<div class="it"></div>
   * @param endOfPossibleAppliance          
   * 		<div class="de">Endpunkt des Zeitraumes, wann die Impfung verabreicht werden soll.</div>
   * 		<div class="fr"></div>
   * 		<div class="it"></div>
   */
  public void setPossibleAppliance(Date startOfPossibleAppliacne, Date endOfPossibleAppliance) {
    mImmunizationRecommendation.getEffectiveTimes().add(0, DateUtil.createSTCM_TS(startOfPossibleAppliacne, endOfPossibleAppliance));
  }  

  /**
   * <div class="de">Gibt an, ob eine Impfung nicht verabreicht werden soll.</div>
   * <div class="fr"></div>
   * <div class="it"></div>
   *
   * @param shallNotBeAdministerd
   *      <div class="de">true, wenn die Impfung nicht verabreicht werden soll, sonst false</div>
   *      <div class="fr"></div>
   *      <div class="it"></div>
   */
  public void setShallNotBeAdministerd(boolean shallNotBeAdministerd) {
    if (shallNotBeAdministerd) {
      mImmunizationRecommendation.setNegationInd(true);
    }
    else {
      mImmunizationRecommendation.setNegationInd(false);
    }
  }

  /**
   * Sets the Person, who performs the Immunization 
   *
   * @param performer the new performer (Convenience Author will be converted to a performer)
   */  
  public void setPerformer(Author performer) {
	  Performer2 p2 = CDAFactory.eINSTANCE.createPerformer2();
	  mImmunizationRecommendation.getPerformers().clear();
	  mImmunizationRecommendation.getPerformers().add(p2);
	  
	  p2.setAssignedEntity(Util.createAssignedEntityFromAssignedAuthor(performer.copyMdhtAuthor().getAssignedAuthor()));
	  try {
		p2.setTime(DateUtil.createIVL_TSFromEuroDate(new Date()));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
   * Gets a list of reasons for the immunization (the illnesses, which the immunization should prevent). 
   *
   * @return A ArrayList of Code  
   * 
   */
  public ArrayList<Code> getReasons() {
    ArrayList<Code> cl = new ArrayList<Code>();
    EList<EntryRelationship> erl = mImmunizationRecommendation.getEntryRelationships();
    for (EntryRelationship er : erl) {
      if (er.getTypeCode().equals(x_ActRelationshipEntryRelationship.RSON)) {
        Observation o = er.getObservation();
        cl.add(new Code (o.getCode()));
      }
    }
    return cl;
  }
  
  /**
   * Adds the reason for the immunization (the illness, which the immunization should prevent)
   *
   * @param code Code for the illness
   */
  public void addReason(Code code) {
    mImmunizationRecommendation.addObservation(createReason(code));
    mImmunizationRecommendation.getEntryRelationships().get(mImmunizationRecommendation.getEntryRelationships().size()-1).setTypeCode(x_ActRelationshipEntryRelationship.RSON);
    mImmunizationRecommendation.getEntryRelationships().get(mImmunizationRecommendation.getEntryRelationships().size()-1).setInversionInd(false);   
  }
  
  public void setExternalDocument(URL link, Identificator id) {
	  ExternalDocument e = CDAFactory.eINSTANCE.createExternalDocument();
	  e.setClassCode(ActClassDocument.DOC);
	  e.setMoodCode(ActMood.EVN);
	  
	  //set the given or generate an ID for the document
	  e.setSetId(Util.createUuidVacdIdentificator(id));
	  
	  //set the URL
	  e.setText(Util.createEd(link.toString()));
	  
	  //Was ist das ExternalDocument für ein Objekt? Kein Act, Keine EntryRelationship, wie füge ich es der Immunization Recommendation hinzu?
//	  Observation o = CDAFactory.eINSTANCE.createObservation();
//	  
//	  mImmunizationRecommendation.getEntryRelationships().add(e);
  }

  /**
   * Sets the Person, who performs the Immunization 
   *
   * @param performer the new performer
   */  
  public void setPerformer(Performer performer) {
	  mImmunizationRecommendation.getPerformers().clear();
	  mImmunizationRecommendation.getPerformers().add(performer.copyMdhtPerfomer());
  }

}
