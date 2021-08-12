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
package org.ehealth_connector.communication.ch.enums;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.mdht.enums.ValueSetEnumInterface;

/**
 * <!-- @formatter:off -->
 * Enumeration of DocumentEntry.originalProviderRole values
 *
 * <div class="en">no designation found for language ENGLISH</div>
 * <div class="de">no designation found for language GERMAN</div>
 * <div class="fr">no designation found for language FRENCH</div>
 * <div class="it">no designation found for language ITALIAN</div>
 *
 * Identifier: 2.16.756.5.30.1.127.3.10.1.42
 * Effective date: 2021-04-01 17:23
 * Version: 202104.0-stable
 * Status: FINAL
 * <!-- @formatter:on -->
 */
@Generated(value = "org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-04-22")
public enum OriginalProviderRole implements ValueSetEnumInterface {

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Assistant</div>
	 * <div class="de">Hilfsperson</div>
	 * <div class="fr">Auxiliaire</div>
	 * <div class="it">Persona ausiliara</div>
	 * <!-- @formatter:on -->
	 */
	ASSISTANT("ASS", "2.16.756.5.30.1.127.3.10.6", "Assistant", "Assistant", "Hilfsperson",
			"Auxiliaire", "Persona ausiliara"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Document administrator</div>
	 * <div class="de">Dokumenten-Administrator</div>
	 * <div class="fr">Administrateur de documents</div>
	 * <div class="it">Amministratore dei documenti</div>
	 * <!-- @formatter:on -->
	 */
	DOCUMENT_ADMINISTRATOR("DADM", "2.16.756.5.30.1.127.3.10.6", "Document administrator",
			"Document administrator", "Dokumenten-Administrator", "Administrateur de documents",
			"Amministratore dei documenti"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Healthcare professional</div>
	 * <div class="de">Gesundheitsfachperson</div>
	 * <div class="fr">Professionnel de la santé</div>
	 * <div class="it">Professionista della salute</div>
	 * <!-- @formatter:on -->
	 */
	HEALTHCARE_PROFESSIONAL("HCP", "2.16.756.5.30.1.127.3.10.6", "Healthcare professional",
			"Healthcare professional", "Gesundheitsfachperson", "Professionnel de la santé",
			"Professionista della salute"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Patient</div>
	 * <div class="de">Patient</div>
	 * <div class="fr">Patient</div>
	 * <div class="it">Paziente</div>
	 * <!-- @formatter:on -->
	 */
	PATIENT("PAT", "2.16.756.5.30.1.127.3.10.6", "Patient", "Patient", "Patient", "Patient",
			"Paziente"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Representative</div>
	 * <div class="de">Stellvertretung</div>
	 * <div class="fr">Représentant</div>
	 * <div class="it">Rappresentante</div>
	 * <!-- @formatter:on -->
	 */
	REPRESENTATIVE("REP", "2.16.756.5.30.1.127.3.10.6", "Representative", "Representative",
			"Stellvertretung", "Représentant", "Rappresentante"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Technical user</div>
	 * <div class="de">Technischer Benutzer</div>
	 * <div class="fr">Utilisateur technique</div>
	 * <div class="it">Utente tecnico</div>
	 * <!-- @formatter:on -->
	 */
	TECHNICAL_USER("TCU", "2.16.756.5.30.1.127.3.10.6", "Technical user", "Technical user",
			"Technischer Benutzer", "Utilisateur technique", "Utente tecnico");

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Assistant</div>
	 * <div class="de">Code für Hilfsperson</div>
	 * <div class="fr">Code de Auxiliaire</div>
	 * <div class="it">Code per Persona ausiliara</div>
	 * <!-- @formatter:on -->
	 */
	public static final String ASSISTANT_CODE = "ASS";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Document administrator</div>
	 * <div class="de">Code für Dokumenten-Administrator</div>
	 * <div class="fr">Code de Administrateur de documents</div>
	 * <div class="it">Code per Amministratore dei documenti</div>
	 * <!-- @formatter:on -->
	 */
	public static final String DOCUMENT_ADMINISTRATOR_CODE = "DADM";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Healthcare professional</div>
	 * <div class="de">Code für Gesundheitsfachperson</div>
	 * <div class="fr">Code de Professionnel de la santé</div>
	 * <div class="it">Code per Professionista della salute</div>
	 * <!-- @formatter:on -->
	 */
	public static final String HEALTHCARE_PROFESSIONAL_CODE = "HCP";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Patient</div>
	 * <div class="de">Code für Patient</div>
	 * <div class="fr">Code de Patient</div>
	 * <div class="it">Code per Paziente</div>
	 * <!-- @formatter:on -->
	 */
	public static final String PATIENT_CODE = "PAT";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Representative</div>
	 * <div class="de">Code für Stellvertretung</div>
	 * <div class="fr">Code de Représentant</div>
	 * <div class="it">Code per Rappresentante</div>
	 * <!-- @formatter:on -->
	 */
	public static final String REPRESENTATIVE_CODE = "REP";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Technical user</div>
	 * <div class="de">Code für Technischer Benutzer</div>
	 * <div class="fr">Code de Utilisateur technique</div>
	 * <div class="it">Code per Utente tecnico</div>
	 * <!-- @formatter:on -->
	 */
	public static final String TECHNICAL_USER_CODE = "TCU";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Identifier of the value set</div>
	 * <div class="de">Identifikator für das Value Set</div>
	 * <!-- @formatter:on -->
	 */
	public static final String VALUE_SET_ID = "2.16.756.5.30.1.127.3.10.1.42";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Name of the value set</div>
	 * <div class="de">Name des Value Sets</div>
	 * <!-- @formatter:on -->
	 */
	public static final String VALUE_SET_NAME = "DocumentEntry.originalProviderRole";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the Enum with a given code</div>
	 * <div class="de">Liefert den Enum anhand eines gegebenen codes</div>
	 * <!-- @formatter:on -->
	 *
	 * @param code
	 *            <div class="de"> code</div>
	 * @return <div class="en">the enum</div>
	 */
	public static OriginalProviderRole getEnum(final String code) {
		for (final OriginalProviderRole x : values()) {
			if (x.getCodeValue().equals(code)) {
				return x;
			}
		}
		return null;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Checks if a given enum is part of this value set.</div>
	 * <div class="de">Prüft, ob der angegebene enum Teil dieses Value Sets ist.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param enumName
	 *            <div class="de"> enumName</div>
	 * @return true, if enum is in this value set
	 */
	public static boolean isEnumOfValueSet(final String enumName) {
		if (enumName == null) {
			return false;
		}
		try {
			Enum.valueOf(OriginalProviderRole.class, enumName);
			return true;
		} catch (final IllegalArgumentException ex) {
			return false;
		}
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Checks if a given code value is in this value set.</div>
	 * <div class="de">Prüft, ob der angegebene code in diesem Value Set vorhanden ist.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param codeValue
	 *            <div class="de"> code</div>
	 * @return true, if is in value set
	 */
	public static boolean isInValueSet(final String codeValue) {
		for (final OriginalProviderRole x : values()) {
			if (x.getCodeValue().equals(codeValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Machine interpretable and (inside this class) unique code</div>
	 * <div class="de">Maschinen interpretierbarer und (innerhalb dieser Klasse) eindeutiger Code</div>
	 * <!-- @formatter:on -->
	 */
	private String code;

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Identifier of the referencing code system.</div>
	 * <div class="de">Identifikator des referenzierende Codesystems.</div>
	 * <!-- @formatter:on -->
	 */
	private String codeSystem;

	/**
	 * The display names per language
	 */
	private Map<LanguageCode, String> displayNames;

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Instantiates this Enum Object with a given Code and Display Name</div>
	 * <div class="de">Instanziiert dieses Enum Object mittels eines Codes und einem Display Name</div>.
	 * <!-- @formatter:on -->
	 *
	 * @param code
	 *            code
	 * @param codeSystem
	 *            codeSystem
	 * @param displayName
	 *            the default display name
	 * @param displayNameEn
	 *            the display name en
	 * @param displayNameDe
	 *            the display name de
	 * @param displayNameFr
	 *            the display name fr
	 * @param displayNameIt
	 *            the display name it
	 */
	OriginalProviderRole(final String code, final String codeSystem, final String displayName,
			final String displayNameEn, final String displayNameDe, final String displayNameFr,
			final String displayNameIt) {
		this.code = code;
		this.codeSystem = codeSystem;
		this.displayNames = new HashMap<>();
		this.displayNames.put(null, displayName);
		this.displayNames.put(LanguageCode.ENGLISH, displayNameEn);
		this.displayNames.put(LanguageCode.GERMAN, displayNameDe);
		this.displayNames.put(LanguageCode.FRENCH, displayNameFr);
		this.displayNames.put(LanguageCode.ITALIAN, displayNameIt);
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the code system identifier.</div>
	 * <div class="de">Liefert den Code System Identifikator.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code system identifier</div>
	 */
	@Override
	public String getCodeSystemId() {
		return this.codeSystem;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the code system name.</div>
	 * <div class="de">Liefert den Code System Namen.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code system identifier</div>
	 */
	@Override
	public String getCodeSystemName() {
		String retVal = "";
		CodeSystems cs = CodeSystems.getEnum(this.codeSystem);
		if (cs != null)
			retVal = cs.getCodeSystemName();
		return retVal;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the actual Code as string</div>
	 * <div class="de">Liefert den eigentlichen Code als String</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code</div>
	 */
	@Override
	public String getCodeValue() {
		return this.code;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the display name defined by the language param. If
	 * there is no english translation, the default display name is returned.</div>
	 * <div class="de">Liefert display name gemäss Parameter, falls es keine
	 * Englische Übersetzung gibt, wird der default-Name zurückgegeben.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param languageCode
	 *            the language code to get the display name for
	 * @return returns the display name in the desired language. if language not
	 *         found, display name in german will returned
	 */
	@Override
	public String getDisplayName(LanguageCode languageCode) {
		String displayName = this.displayNames.get(languageCode);
		if (displayName == null && languageCode == LanguageCode.ENGLISH) {
			return this.displayNames.get(null);
		}
		return displayName;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the value set identifier.</div>
	 * <div class="de">Liefert den Value Set Identifikator.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the value set identifier</div>
	 */
	@Override
	public String getValueSetId() {
		return VALUE_SET_ID;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the value set name.</div>
	 * <div class="de">Liefert den Value Set Namen.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the value set name</div>
	 */
	@Override
	public String getValueSetName() {
		return VALUE_SET_NAME;
	}
}