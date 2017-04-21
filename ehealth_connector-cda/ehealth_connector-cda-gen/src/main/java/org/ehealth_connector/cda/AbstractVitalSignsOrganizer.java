/*
 * 
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

package org.ehealth_connector.cda;

import java.util.List;

import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.enums.NullFlavor;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.cda.ihe.VitalSignsOrganizer;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

public class AbstractVitalSignsOrganizer
		extends MdhtOrganizerFacade<org.openhealthtools.mdht.uml.cda.ihe.VitalSignsOrganizer> {

	protected AbstractVitalSignsOrganizer() {
		super(IHEFactory.eINSTANCE.createVitalSignsOrganizer().init());
		// Correct wrong MDHT CodeSystemName
		getMdht().getCode().setCodeSystemName("SNOMED CT");
	}

	protected AbstractVitalSignsOrganizer(VitalSignsOrganizer mdht) {
		super(mdht);
	}

	public void addId(Identificator id) {
		getMdht().getIds().add(id.getIi());
	}

	public NullFlavor getEffectiveTimeNullFlavor() {
		if ((getMdht().getEffectiveTime() != null)
				&& getMdht().getEffectiveTime().isSetNullFlavor()) {
			return NullFlavor.getEnum(getMdht().getEffectiveTime().getNullFlavor().getLiteral());
		}
		return null;
	}

	public List<Identificator> getIds() {
		return Util.convertIds(getMdht().getIds());
	}

	public void setEffectiveTimeNullFlavor(NullFlavor nullFlavor) {
		final IVL_TS ivlts = DatatypesFactory.eINSTANCE.createIVL_TS();
		ivlts.setNullFlavor(
				org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.get(nullFlavor.getCodeValue()));
		getMdht().setEffectiveTime(ivlts);
	}

}
