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
package org.ehealth_connector.valueset.enums;

/**
 * The Class DesignationType. Designations are language dependent display names
 * for the code. For any language there might be multiple, each specifying the
 * type (fully specified name, preferred, synonym, ...). See also
 * https://art-decor.org/mediawiki/index.php?title=DECOR-terminology
 */
public enum DesignationType {

	PREFERRED,

	SYNONYM,

	ABBREVIATION,

	FULLY_SPECIFIED_NAME;

	/**
	 * <div class="en">Gets the Enum with a given code</div>
	 * <div class="de">Liefert den Enum anhand eines gegebenen codes</div>
	 *
	 * @param code
	 *            <br>
	 *            <div class="de"> code</div>
	 * @return <div class="en">the enum</div>
	 */
	public static DesignationType getEnum(String code) {
		DesignationType retVal = null;
		if ("abbreviation".equals(code))
			retVal = ABBREVIATION;
		if ("preferred".equals(code))
			retVal = PREFERRED;
		if ("synonym".equals(code))
			retVal = SYNONYM;
		if ("fsn".equals(code))
			retVal = FULLY_SPECIFIED_NAME;
		return retVal;
	}

}
