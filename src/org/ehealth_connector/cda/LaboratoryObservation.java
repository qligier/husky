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
 * Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/
package org.ehealth_connector.cda;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.ch.enums.CodeSystems;
import org.ehealth_connector.cda.ch.enums.ObservationInterpretation;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.DateUtil;
import org.ehealth_connector.common.Organization;
import org.ehealth_connector.common.Value;
import org.openhealthtools.mdht.uml.cda.AssignedEntity;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.Performer2;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationPhysicalPerformer;

/**
 * <div class="de">Klasse Laborbefund</div>\n <div class="fr">Class LaboratoryObservation.</div>\n
 * <div class="it">Class LaboratoryObservation.</div>
 */
public class LaboratoryObservation {

  /** The m laboratory observation. */
  org.openhealthtools.mdht.uml.cda.ch.LaboratoryObservation mLaboratoryObservation;

  /**
   * Instantiates a new laboratory observation.
   */
  public LaboratoryObservation() {
    mLaboratoryObservation = CHFactory.eINSTANCE.createLaboratoryObservation().init();
  }

  /**
   * Instantiates a new laboratory observation.
   *
   * @param code <br>
   *        <div class="de">Code für einen bezüglich einer Impfung relevanten Laborbefund</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param immuneProtection <br>
   *        <div class="de">true, wenn ein Immunschutz besteht.</div> <div class="fr"></div> <div
   *        class="it"></div>
   * @param dateTimeOfResult <br>
   *        <div class="de">Datum und Uhrzeit, an dem das Resultat bekannt wurde.</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param laboratory <br>
   *        <div class="de">Das ausführende Labor.</div> <div class="fr"></div> <div
   *        class="it"></div>
   */
  public LaboratoryObservation(org.ehealth_connector.cda.ch.enums.Serologie code,
      boolean immuneProtection, Date dateTimeOfResult, Organization laboratory) {
    mLaboratoryObservation = CHFactory.eINSTANCE.createLaboratoryObservation().init();

    setCode(code.getCode());
    setImmuneProtection(immuneProtection);
    setEffectiveTime(dateTimeOfResult);
    setLaboratory(laboratory, dateTimeOfResult);
  }

  /**
   * Instantiates a new laboratory observation.
   *
   * @param code <br>
   *        <div class="de">Code für einen bezüglich einer Impfung relevanten Laborbefund</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param immuneProtection <br>
   *        <div class="de">true, wenn ein Immunschutz besteht.</div> <div class="fr"></div> <div
   *        class="it"></div>
   * @param dateTimeOfResult <br>
   *        <div class="de">Datum und Uhrzeit, an dem das Resultat bekannt wurde.</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param laboratory <br>
   *        <div class="de">Das ausführende Labor.</div> <div class="fr"></div> <div
   *        class="it"></div>
   * @param valueCode <br>
   *        <div class="de">Wert des Resultats (als Code-Objekt)</div> <div class="fr"></div> <div
   *        class="it"></div>
   */
  public LaboratoryObservation(org.ehealth_connector.cda.ch.enums.Serologie code,
      Organization laboratory, boolean immuneProtection, Date dateTimeOfResult, Code valueCode) {
    this(code, immuneProtection, dateTimeOfResult, laboratory);

    this.addValue(valueCode);
  }

  // TODO Create Constructor for unknown Types of "Erregernachweise"

  /**
   * Instantiates a new laboratory observation.
   *
   * @param code <br>
   *        <div class="de">Code für einen bezüglich einer Impfung relevanten Laborbefund</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param laboratory <br>
   *        <div class="de">Das ausführende Labor.</div> <div class="fr"></div> <div
   *        class="it"></div>
   * @param immuneProtection <br>
   *        <div class="de">true, wenn ein Immunschutz besteht.</div> <div class="fr"></div> <div
   *        class="it"></div>
   * @param dateTimeOfResult <br>
   *        <div class="de">Datum und Uhrzeit, an dem das Resultat bekannt wurde.</div> <div
   *        class="fr"></div> <div class="it"></div>
   * @param value <br>
   *        <div class="de">Wert des Resultats (als Value-Objekt)</div> <div class="fr"></div> <div
   *        class="it"></div>
   */
  public LaboratoryObservation(org.ehealth_connector.cda.ch.enums.Serologie code,
      Organization laboratory, boolean immuneProtection, Date dateTimeOfResult, Value value) {
    this(code, immuneProtection, dateTimeOfResult, laboratory);

    this.addValue(value);
  }

  /**
   * Instantiates a new laboratory observation.
   *
   * @param labObs <br>
   *        <div class="de"> lab obs</div> <div class="fr"> lab obs</div> <div class="it"> lab
   *        obs</div>
   */
  public LaboratoryObservation(org.openhealthtools.mdht.uml.cda.ch.LaboratoryObservation labObs) {
    mLaboratoryObservation = labObs;
  }

  /**
   * <div class="de">Copy mdht laboratory observation.</div> <div class="fr">Copy mdht laboratory
   * observation.</div> <div class="it">Copy mdht laboratory observation.</div>
   *
   * @return the org.openhealthtools.mdht.uml.cda.ch. laboratory observation
   */
  public org.openhealthtools.mdht.uml.cda.ch.LaboratoryObservation copyMdhtLaboratoryObservation() {
    return EcoreUtil.copy(mLaboratoryObservation);
  }

  /**
   * <div class="de">Gibt den Code der Beobachtung zurück.</div> <div class="fr"></div> <div
   * class="it"></div>
   *
   * @return the code
   */
  public Code getCode() {
    Code code = new Code(mLaboratoryObservation.getCode());
    return code;
  }

  /**
   * <div class="de">Gibt das Datum und die Uhrzeit zurück, wann die Untersuchung durchgeführt wurde.</div> <div
   * class="fr"></div> <div class="it"></div>
   *
   * @return the date time of result
   */
  public Date getDateTimeOfResult() {
    if (mLaboratoryObservation.getPerformers().size() > 0) {
      return DateUtil.parseIVL_TSVDateTimeValue(mLaboratoryObservation.getPerformers().get(0)
          .getTime());
    } else {
      return DateUtil.parseIVL_TSVDateTimeValue(mLaboratoryObservation.getEffectiveTime());
    }
  }
  
  /**
   * <div class="de">Gibt das Datum und die Uhrzeit zurück, wann die Untersuchung durchgeführt wurde als String zurück (z.B. "28.02.2015 16:00")</div> <div
   * class="fr"></div> <div class="it"></div>
   *
   * @return the date time of result
   */
  public String getDateTimeOfResultStr() {
    if (mLaboratoryObservation.getPerformers().size() > 0) {
      return DateUtil.formatDateTimeCh(DateUtil.parseIVL_TSVDateTimeValue(mLaboratoryObservation.getPerformers().get(0)
          .getTime()));
    } else {
      return DateUtil.formatDateTimeCh(DateUtil.parseIVL_TSVDateTimeValue(mLaboratoryObservation.getEffectiveTime()));
    }
  }

  /**
   * <div class="de">Gibt zurück, ob ein Impfschutz besteht (Interpretation Code).</div> <div
   * class="fr"></div> <div class="it"></div>
   * 
   * @return true, wenn ein Impfschutz besteht, false sonst.
   */
  public boolean getImmuneProtection() {
    //mLaboratoryObservation.getInterpretationCodes().get(0).getCode();
    
    if (mLaboratoryObservation
        .getInterpretationCodes().get(0).getCode().equals(ObservationInterpretation.POSITIVE_PATHOGEN_FOUND_IN_SPECIMEN.getCodeValue())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * <div class="de">Gibt zurück, ob ein Impfschutz besteht (Interpretation Code).</div> <div
   * class="fr"></div> <div class="it"></div>
   *
   * @return the interpretation code
   */
  public String getInterpretationCode() {
    return mLaboratoryObservation.getInterpretationCodes().get(0).getCode();
  }

  /**
   * <div class="de">Gibt das Labor zurück, welches das Ergebnis ermittelt hat.</div> <div
   * class="fr"></div> <div class="it"></div>
   * 
   * @return the laboratory
   */
  public Organization getLaboratory() {
    if (mLaboratoryObservation.getPerformers().size() > 0) {
      if (mLaboratoryObservation.getPerformers().get(0).getAssignedEntity() != null) {
        if (mLaboratoryObservation.getPerformers().get(0).getAssignedEntity()
            .getRepresentedOrganizations().size() > 0) {
          return new Organization(mLaboratoryObservation.getPerformers().get(0).getAssignedEntity()
              .getRepresentedOrganizations().get(0));
        }
      }
    }
    return null;
  }

  /**
   * Gets the mdht laboratory observation.
   *
   * @return the mdht laboratory observation
   */
  public org.openhealthtools.mdht.uml.cda.ch.LaboratoryObservation getMdhtLaboratoryObservation() {
    return mLaboratoryObservation;
  }

  /**
   * Sets the code.
   *
   * @param code the new code
   */
  public void setCode(Code code) {
    mLaboratoryObservation.setCode(code.getCD());
  }

  /**
   * Sets the date time of result.
   *
   * @param dateTimeOfResult the new date time of result
   */
  public void setEffectiveTime(Date dateTimeOfResult) {
    try {
      mLaboratoryObservation.setEffectiveTime(DateUtil
          .createIVL_TSFromEuroDateTime(dateTimeOfResult));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Gets the Effective Time
   *
   * @param dateTimeOfResult the new date time of result
   */
  public Date getEffectiveTime() {
    return DateUtil.parseIVL_TSVDateTimeValue(mLaboratoryObservation.getEffectiveTime());
  }

  /**
   * Sets the immune protection.
   *
   * <div class="de">Setzt, ob ein Immunschutz besteht (Interpretations Code)</div> <div
   * class="fr"></div> <div class="it"></div>
   * @param immuneProtection the new immune protection
   * <div class="de">true, wenn ein Immunschutz besteht (Interpretation Code: POSITIVE_PATHOGEN_FOUND_IN_SPECIMEN). false, wenn kein Immunschutz besteht (Interpretation Code: NEGATIVE_PATHOGEN_COULDNT_BE_DETERMINED_IN_SPECI_MEN)</div>
   */
  public void setImmuneProtection(boolean immuneProtection) {
    if (immuneProtection == false) {
      mLaboratoryObservation.getInterpretationCodes().add(
          ObservationInterpretation.NEGATIVE_PATHOGEN_COULDNT_BE_DETERMINED_IN_SPECI_MEN.getCE());
    } else {
      mLaboratoryObservation.getInterpretationCodes().add(
          ObservationInterpretation.POSITIVE_PATHOGEN_FOUND_IN_SPECIMEN.getCE());
    }
  }

  /**
   * Sets the interpretation code.
   *
   * @param code the new interpretation code
   */
  public void setInterpretationCode(ObservationInterpretation code) {
    mLaboratoryObservation.getInterpretationCodes().clear();
    mLaboratoryObservation.getInterpretationCodes().add(code.getCE());
  }

  /**
   * <div class="de">Sets the laboratory.</div> <div class="fr">Sets the laboratory.</div> <div
   * class="it">Sets the laboratory.</div>
   *
   * @param laboratory <br>
   *        <div class="de"> laboratory</div> <div class="fr"> laboratory</div> <div class="it">
   *        laboratory</div>
   * @param dateTimeOfResult <br>
   *        <div class="de"> date time of result</div> <div class="fr"> date time of result</div>
   *        <div class="it"> date time of result</div>
   */
  public void setLaboratory(Organization laboratory, Date dateTimeOfResult) {
    Performer2 perf = CDAFactory.eINSTANCE.createPerformer2();
    AssignedEntity asEnt = CDAFactory.eINSTANCE.createAssignedEntity();

    II ii =
        DatatypesFactory.eINSTANCE.createII(CodeSystems.GLN.getCodeSystemId(), laboratory.getId());
    asEnt.getIds().add(ii);

    asEnt.getRepresentedOrganizations().add(laboratory.copyMdhtOrganization());
    perf.setAssignedEntity(asEnt);
    perf.setTypeCode(ParticipationPhysicalPerformer.PRF);
    try {
      perf.setTime(DateUtil.createIVL_TSFromEuroDateTime(dateTimeOfResult));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    mLaboratoryObservation.getPerformers().add(perf);
  }

  /**
   * Sets the value.
   *
   * @param code the new value
   */
  public void addValue(Code code) {
    mLaboratoryObservation.getValues().add(code.getCD());
  }

  /**
   * Get the (first) problem value. The Value may be a coded or uncoded String.
   * 
   * @return the (first) problem value as string.
   */
  public Value getValue() {
    if (!mLaboratoryObservation.getValues().isEmpty()) {
      return new Value(mLaboratoryObservation.getValues().get(0));
    }
    return null;
  }

  /**
   * Get a list of all problem values. Each Value may be a coded or uncoded String.
   * 
   * @return all problem values as ArrayList.
   */
  public ArrayList<Value> getValues() {
    ArrayList<Value> vl = new ArrayList<Value>();
    for (ANY a : mLaboratoryObservation.getValues()) {
      Value v = new Value(a);
      vl.add(v);
    }
    return vl;
  }

  /**
   * Adds the value.
   *
   * @param value the new value
   */
  public void addValue(Value value) {
    if (value.isPhysicalQuantity()) {
      mLaboratoryObservation.getValues().add(value.copyMdhtPhysicalQuantity());
    }
    if (value.isCode()) {
      mLaboratoryObservation.getValues().add(value.copyMdhtCode());
    }
  }
}