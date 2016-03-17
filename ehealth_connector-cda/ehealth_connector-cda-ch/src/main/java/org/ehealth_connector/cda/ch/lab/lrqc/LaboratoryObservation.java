package org.ehealth_connector.cda.ch.lab.lrqc;

import org.ehealth_connector.cda.SectionAnnotationCommentEntry;
import org.ehealth_connector.cda.ch.lab.lrqc.enums.LabObsList;
import org.ehealth_connector.common.Value;
import org.ehealth_connector.common.enums.ObservationInterpretation;

public class LaboratoryObservation
		extends org.ehealth_connector.cda.ch.lab.AbstractLaboratoryObservation {

	public LaboratoryObservation() {
		super();
	}

	/**
	 * Instantiates the class with the required elements
	 *
	 * @param code
	 *          the code
	 * @param interpretationCode
	 *          the interpretation code
	 * @param textReference
	 *          Reference to the human readable text
	 * @param reference
	 *          <div class="en">reference to the human readable text, regarding
	 *          the lot number of the anylizer kit</div><div class="de">Dieses
	 *          Element ermöglicht zu jedem Entry einen Kommentar anzugeben. Bei
	 *          Laborbefunden für die Qualitätskontrolle MUSS darin deklariert
	 *          werden, wie die Probe analysiert worden ist. Wenn dazu ein
	 *          Analyzer verwendet worden ist, soll dies folgendermassen
	 *          deklariert werden (Freitext): Test-Hersteller [R]: Name des
	 *          Unternehmens, Test-Gerät [R]: Name und Typ des Gerätes, Test-Kit
	 *          [R2]: Genaue Bezeichnung des Kits</div>
	 */
	public LaboratoryObservation(LabObsList code, Value value,
			ObservationInterpretation interpretationCode, String textReference,
			SectionAnnotationCommentEntry reference) {
		this();
		setCode(code);
		addValue(value);
		addInterpretationCode(interpretationCode);
		setTextReference(textReference);
		addCommentEntry(reference);
	}

	public LaboratoryObservation(
			org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryObservation mdht) {
		super(mdht);
	}

	public org.ehealth_connector.cda.ch.lab.lrqc.enums.LabObsList getCodeAsEnum() {
		if (getMdht().getCode() != null && getMdht().getCode().getCode() != null) {
			return org.ehealth_connector.cda.ch.lab.lrqc.enums.LabObsList
					.getEnum(getMdht().getCode().getCode());
		}
		return null;
	}

	public void setCode(org.ehealth_connector.cda.ch.lab.lrqc.enums.LabObsList code) {
		getMdht().setCode(code.getCD());
	}
}
