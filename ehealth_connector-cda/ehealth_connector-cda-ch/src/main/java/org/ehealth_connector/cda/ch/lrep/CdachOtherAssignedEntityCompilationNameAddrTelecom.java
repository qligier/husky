/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
package org.ehealth_connector.cda.ch.lrep;

import java.util.ArrayList;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.9.17
 * Template description: Reusable template wherever an assigned entity with required name, addr and telecom is used in a CDA-CH V2 document. CDA-CH V2 derivatives, i.e. Swiss exchange formats MAY use this template by either reference or specialisation.
 *
 * Element description: The human-readable text MUST be generated automatically from the structured information of this element. The text element MUST contain the reference to the corresponding text in the human readable part, ONLY.
 */
public class CdachOtherAssignedEntityCompilationNameAddrTelecom extends org.ehealth_connector.common.hl7cdar2.CE {

	/**
	 * A translation of the code to another coding system.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.CD> hl7Translation;

	/**
	 * Adds a hl7Translation
	 * A translation of the code to another coding system.
	 */
	public void addHl7Translation(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Translation.add(value);
	}

	/**
	 * Adds a hl7Translation
	 * A translation of the code to another coding system.
	 */
	public void clearHl7Translation() {
		hl7Translation.clear();
	}
}
