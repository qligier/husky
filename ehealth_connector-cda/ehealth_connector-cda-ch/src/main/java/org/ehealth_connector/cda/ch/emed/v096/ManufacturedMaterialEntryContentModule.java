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
package org.ehealth_connector.cda.ch.emed.v096;

import java.util.ArrayList;

import org.ehealth_connector.common.hl7cdar2.IVLTS;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.33 Template
 * description: A Medicine entry describes a medicine and is used within
 * Medication Treatment- Prescription- or Dispensation Items. It describes
 * either a medicinal product, a generic/scientific name or a magistral
 * preparation/compound medicine and contains information such as name,
 * medication form, packaging information and active ingredients.
 */
public class ManufacturedMaterialEntryContentModule
		extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Material {

	/**
	 * Creates fixed contents for CDA Element hl7Code
	 *
	 * @param codeSystem
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.CE createHl7CodeFixedValue(
			String codeSystem) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CE retVal = factory.createCE();
		retVal.setCodeSystem(codeSystem);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7Name
	 *
	 * @param nullFlavor
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.EN createHl7NameFixedValue(
			String nullFlavor) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.EN retVal = factory.createEN();
		retVal.nullFlavor = new ArrayList<String>();
		retVal.nullFlavor.add(nullFlavor);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element hl7TemplateId
	 *
	 * @param root
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.II createHl7TemplateIdFixedValue(
			String root) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.II retVal = factory.createII();
		retVal.setRoot(root);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element pharmAsContent
	 *
	 * @param classCode
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.COCTMT230100UVContent createPharmAsContentFixedValue(
			String classCode) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.COCTMT230100UVContent retVal = factory
				.createCOCTMT230100UVContent();
		retVal.setClassCode(
				org.ehealth_connector.common.hl7cdar2.RoleClassContent.fromValue(classCode));
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element pharmFormCode
	 */
	private static org.ehealth_connector.common.hl7cdar2.CE createPharmFormCodeFixedValue() {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CE retVal = factory.createCE();
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Element pharmIngredient
	 *
	 * @param classCode
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.COCTMT230100UVIngredient createPharmIngredientFixedValue(
			String classCode) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.COCTMT230100UVIngredient retVal = factory
				.createCOCTMT230100UVIngredient();
		retVal.setClassCode(classCode);
		return retVal;
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.COCTMT230100UVContent, filled by:
	 * "CONT"
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.COCTMT230100UVContent getPredefinedAsContentCont() {
		return createPharmAsContentFixedValue("CONT");
	}

	/**
	 * Adds a predefined org.ehealth_connector.common.hl7cdar2.CE, filled by:
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.CE getPredefinedFormCode() {
		return createPharmFormCodeFixedValue();
	}

	/**
	 * Adds a predefined
	 * org.ehealth_connector.common.hl7cdar2.COCTMT230100UVIngredient, filled
	 * by: "ACTI"
	 *
	 * @return the predefined element.
	 */
	public static org.ehealth_connector.common.hl7cdar2.COCTMT230100UVIngredient getPredefinedIngredientActi() {
		return createPharmIngredientFixedValue("ACTI");
	}

	public ManufacturedMaterialEntryContentModule() {
		super.setClassCode("MMAT");
		super.setDeterminerCode(
				org.ehealth_connector.common.hl7cdar2.EntityDeterminerDetermined.fromValue("KIND"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.4.33"));
		super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.9.1.3.1"));
		super.setCode(createHl7CodeFixedValue("2.16.840.1.113883.6.73"));
		super.setName(createHl7NameFixedValue("NA"));
	}

	/**
	 * Adds a pharmAsContent
	 */
	public void addPharmAsContent(
			org.ehealth_connector.common.hl7cdar2.COCTMT230100UVContent value) {
		asContent = value;
	}

	/**
	 * Adds a pharmIngredient One or more active ingredients SHOULD be
	 * represented with this structure.
	 */
	public void addPharmIngredient(
			org.ehealth_connector.common.hl7cdar2.COCTMT230100UVIngredient value) {
		getIngredient().add(value);
	}

	/**
	 * Adds a pharmIngredient One or more active ingredients SHOULD be
	 * represented with this structure.
	 */
	public void clearPharmIngredient() {
		getIngredient().clear();
	}

	/**
	 * Gets the hl7Code ATC Code of the medicine, if it as magistral
	 * preparation/compound medicin nullFlavor SHALL be NA
	 */
	public org.ehealth_connector.common.hl7cdar2.CE getHl7Code() {
		return code;
	}

	/**
	 * Gets the hl7LotNumberText
	 */
	public org.ehealth_connector.common.hl7cdar2.ST getHl7LotNumberText() {
		return lotNumberText;
	}

	/**
	 * Gets the hl7Name The element SHALL contain the name of the medication.
	 */
	public org.ehealth_connector.common.hl7cdar2.EN getHl7Name() {
		return name;
	}

	/**
	 * Gets the hl7TemplateId CH-PHARM Manufactured Material Content Module
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7TemplateId() {
		return templateId;
	}

	/**
	 * Gets the pharmExpirationTime
	 */
	public org.ehealth_connector.common.hl7cdar2.TS getPharmExpirationTime() {
		return expirationTime;
	}

	/**
	 * Gets the pharmFormCode This code represents the pharmaceutical dose form
	 * (e.g., tablet, capsule, liquid) and SHOULD be present, if not implied by
	 * the product.
	 */
	public org.ehealth_connector.common.hl7cdar2.CE getPharmFormCode() {
		return formCode;
	}

	/**
	 * Sets the hl7Code ATC Code of the medicine, if it as magistral
	 * preparation/compound medicin nullFlavor SHALL be NA
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CE value) {
		this.code = value;
	}

	/**
	 * Sets the hl7LotNumberText
	 */
	public void setHl7LotNumberText(org.ehealth_connector.common.hl7cdar2.ST value) {
		this.lotNumberText = value;
	}

	/**
	 * Sets the hl7Name The element SHALL contain the name of the medication.
	 */
	public void setHl7Name(org.ehealth_connector.common.hl7cdar2.EN value) {
		this.name = value;
	}

	/**
	 * Sets the hl7TemplateId CH-PHARM Manufactured Material Content Module
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		getTemplateId().clear();
		getTemplateId().add(value);
	}

	/**
	 * Sets the pharmExpirationTime
	 */
	public void setPharmExpirationTime(org.ehealth_connector.common.hl7cdar2.TS value) {
		ObjectFactory factory = new ObjectFactory();
		IVLTS ivlts = factory.createIVLTS();
		ivlts.setValue(value.getValue());
		this.expirationTime = ivlts;
	}

	/**
	 * Sets the pharmFormCode This code represents the pharmaceutical dose form
	 * (e.g., tablet, capsule, liquid) and SHOULD be present, if not implied by
	 * the product.
	 */
	public void setPharmFormCode(org.ehealth_connector.common.hl7cdar2.CE value) {
		this.formCode = value;
	}
}