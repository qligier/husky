package org.ehealth_connector.cda.enums;

import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.enums.CodeSystems;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;

public enum VitalSignCodes {
	//@formatter:off
	RESPIRATION_RATE("9279-1", "Atemfrequenz", null, null, "respiration rate"),
	HEART_BEAT("8867-4", "Herzfrequenz", null, null, "heart beat"),
	OXYGEN_SATURATION_PERCENT("2710-2", "Sauerstoffsättigung", null, null, "oxygen saturation"),
	INTRAVASCULAR_SYSTOLIC("8480-6", "Intravaskulärer systolischer Druck", null, null, "intravascular systolic"),
	INTRAVASCULAR_DIASTOLIC("8462-4", "Intrvaskulärer diastolischer Druck", null, null, "intravascular diastolic"),
	BODY_TEMPERATURE_CEL("8310-5", "Körpertemperatur", null, null, "body temperature"),
	BODY_HEIGHT("8302-2", "Körpergrösse (gemessen)", null, null, "body height (measured)"),
	BODY_HEIGHT_LYING("8306-3", "Körpergrösse im Liegen", null, null, "body height lying"),
	CIRCUMFRENCE_OCCIPITAL_FRONTAL("8287-5", "Kopfumfang okzipitofrontal", null, null, "circumfence occipital frontal"),
	BODY_WEIGHT("3141-9", "Körpergewicht (gewogen)", null, null, "body weight (measured)");
	//@formatter:on

	private String loinc;
	private String descriptionDe;
	private String descriptionFr;
	private String descriptionIt;
	private String descriptionEn;

	private VitalSignCodes(String loinc, String descriptionDe, String descriptionFr,
			String descriptionIt, String descriptionEn) {
		this.loinc = loinc;
		this.descriptionDe = descriptionDe;
		this.descriptionFr = descriptionFr;
		this.descriptionIt = descriptionIt;
		this.descriptionEn = descriptionEn;
	}

	public Code getCode() {
		Code ret = new Code(CodeSystems.LOINC, loinc);
		ret.setDisplayName(getDisplayName(null));
		return ret;
	}

	public Code getCode(CS languageCode) {
		Code ret = new Code(CodeSystems.LOINC, loinc);
		ret.setDisplayName(getDisplayName(languageCode));
		return ret;
	}

	public Object getLoinc() {
		return loinc;
	}

	public String getDisplayName(CS lc) {
		String lcStr = LanguageCode.ENGLISH.getCodeValue();
		if (lc != null) {
			lcStr = lc.getCode().toLowerCase();
		}
		if (lcStr.equals(LanguageCode.GERMAN.getCodeValue().toLowerCase()))
			return getDisplayNameDe();
		if (lcStr.equals(LanguageCode.FRENCH.getCodeValue().toLowerCase()))
			return getDisplayNameFr();
		if (lcStr.equals(LanguageCode.ITALIAN.getCodeValue().toLowerCase()))
			return getDisplayNameIt();
		if ("de".equals(lcStr))
			return getDisplayNameDe();
		if ("fr".equals(lcStr))
			return getDisplayNameFr();
		if ("it".equals(lcStr))
			return getDisplayNameIt();
		if ("en".equals(lcStr))
			return getDisplayNameEn();
		return getDisplayNameDe();
	}

	private String getDisplayNameEn() {
		if (descriptionEn != null) {
			return descriptionEn;
		}
		return name();
	}

	private String getDisplayNameFr() {
		if (descriptionFr != null) {
			return descriptionFr;
		}
		return getDisplayNameEn();
	}

	private String getDisplayNameIt() {
		if (descriptionIt != null) {
			return descriptionIt;
		}
		return getDisplayNameEn();
	}

	private String getDisplayNameDe() {
		if (descriptionDe != null) {
			return descriptionDe;
		}
		return getDisplayNameEn();
	}

	public static VitalSignCodes getEnum(String loincCode) {
		VitalSignCodes[] values = values();
		for (VitalSignCodes vitalSignCodes : values) {
			if (vitalSignCodes.getLoinc().equals(loincCode)) {
				return vitalSignCodes;
			}
		}
		return null;
	}
}