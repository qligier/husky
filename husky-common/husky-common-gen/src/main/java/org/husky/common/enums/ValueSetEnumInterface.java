/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
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

package org.husky.common.enums;

import org.husky.common.hl7cdar2.CE;
import org.husky.common.model.Code;
import org.husky.common.utils.XdsMetadataUtil;

/**
 * <div class="en">Interface for all dynamically created/updated value
 * sets.</div> <div class="de">Interface für alle dynamisch
 * erstellten/aktualisierten Valuesets.</div>
 */
public interface ValueSetEnumInterface extends CodedMetadataEnumInterface {

	/**
	 * <div class="en">Gets the Code of this Enum as MDHT Object.</div>
	 * <div class="de">Liefert den Code dieses Enum als MDHT Objekt.</div>
	 *
	 * @return <div class="en">The MDHT Code</div>
	 */
	default CE getCE() {
		final CE ce = new CE();
		ce.setCodeSystem(getCodeSystemId());
		ce.setCode(getCodeValue());
		ce.setCodeSystemName(getCodeSystemName());
		ce.setDisplayName(getDisplayName());
		return ce;
	}

	/**
	 * <div class="en">Gets the ehealthconnector Code Object</div>
	 * <div class="de">Liefert das ehealthconnector Code Objekt</div>
	 *
	 * @return <div class="en">the code</div>
	 */
	default Code getCode() {
		return new Code(getCodeValue(), getCodeSystemId(), getDisplayName());
	}

	/**
	 * <div class="en">Gets the OHT CodedMetadataType Object</div>
	 * <div class="de">Liefert das OHT CodedMetadataType Objekt</div>
	 *
	 * @return <div class="en">the codedMetadataType</div>
	 */
	@Override
	default org.openehealth.ipf.commons.ihe.xds.core.metadata.Code getIpfCode() {
		final var cmt = new org.openehealth.ipf.commons.ihe.xds.core.metadata.Code();
		cmt.setSchemeName(getCodeSystemId());
		cmt.setCode(getCodeValue());
		cmt.setDisplayName(XdsMetadataUtil.createInternationalString(getDisplayName(), "de-ch"));
		return cmt;
	}

	/**
	 * <div class="en">Gets the code system id.</div> <div class="de">Liefert
	 * die code system id.</div>
	 *
	 * @return <div class="en">the code system id</div>
	 */
	String getCodeSystemId();

	/**
	 * <div class="en">Gets the code system name.</div> <div class="de">Liefert
	 * den code system Namen.</div>
	 *
	 * @return <div class="en">the code system id</div>
	 */
	String getCodeSystemName();

	/**
	 * <div class="en">Gets the actual Code as string</div>
	 * <div class="de">Liefert den eigentlichen Code als String</div>
	 *
	 * @return <div class="en">the code</div>
	 */
	String getCodeValue();

	/**
	 * <div class="en">Gets the display name.</div> <div class="de">Liefert
	 * display name.</div>
	 *
	 * @return <div class="en">the display name</div>
	 */
	default String getDisplayName() {
		return getDisplayName(null);
	}

	/**
	 * <div class="en">Gets the display name defined by the language param. If
	 * language is unknow, german name is returned</div> <div class="de">Liefert
	 * display name gemäss Parameter, falls die Sprache unbekannt ist, wird
	 * standartmässig deutsch geliefert.</div>
	 *
	 * @param languageCode
	 *            the language code to get the display name for
	 * @return returns the display name in the desired language. if language not
	 *         found, display name in german will returned
	 */
	String getDisplayName(LanguageCode languageCode);

	/**
	 * <div class="en">Gets the value set identifier.</div>
	 * <div class="de">Liefert den Value Set Identifikator.</div>
	 *
	 * @return <div class="en">the value set identifier</div>
	 */
	String getValueSetId();

	/**
	 * <div class="en">Gets the value set name.</div> <div class="de">Liefert
	 * den Value Set Namen.</div>
	 *
	 * @return <div class="en">the value set name</div>
	 */
	String getValueSetName();

}