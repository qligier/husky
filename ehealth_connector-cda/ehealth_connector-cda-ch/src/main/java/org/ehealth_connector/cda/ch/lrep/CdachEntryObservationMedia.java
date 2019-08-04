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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.83
 * Template description: Multimedia objects (e.g., PDF representations of the CDA document, pictures, Reiber diagrams, electrophoresis, etc.) MAY be integrated into a CDA document, either by reference to external multimedia objects or by means of XML embedding.This template defines only the embedding of multimedia objects in the CDA document. References to external documents can be created with the ExternalDocument template.For embedding in XML, the multimedia objects Base-64 must be encoded.Due to the amount of data, only light objects should be embedded.Heavy objects should be integrated using links to external documents.
 */
public class CdachEntryObservationMedia extends org.ehealth_connector.common.hl7cdar2.POCDMT000040ObservationMedia {

	/**
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship hl7EntryRelationship;

	/**
	 * IDs for this item MAY be filled for traceability.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.II> hl7Id;

	/**
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	private org.ehealth_connector.common.hl7cdar2.CS hl7LanguageCode;

	/**
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference hl7Reference;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	/**
	 * The Base-64 encoded multimedia object.
	 */
	private org.ehealth_connector.common.hl7cdar2.ED hl7Value;

	/**
	 * Adds a hl7Id
	 * IDs for this item MAY be filled for traceability.
	 */
	public void addHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7Id.add(value);
	}

	/**
	 * Adds a hl7Id
	 * IDs for this item MAY be filled for traceability.
	 */
	public void clearHl7Id() {
		hl7Id.clear();
	}

	/**
	 * Gets the hl7EntryRelationship
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship getHl7EntryRelationship() {
		return hl7EntryRelationship;
	}

	/**
	 * Gets the hl7LanguageCode
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	public org.ehealth_connector.common.hl7cdar2.CS getHl7LanguageCode() {
		return hl7LanguageCode;
	}

	/**
	 * Gets the hl7Reference
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference getHl7Reference() {
		return hl7Reference;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Gets the hl7Value
	 * The Base-64 encoded multimedia object.
	 */
	public org.ehealth_connector.common.hl7cdar2.ED getHl7Value() {
		return hl7Value;
	}

	/**
	 * Sets the hl7EntryRelationship
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public void setHl7EntryRelationship(org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship value) {
		hl7EntryRelationship = value;
	}

	/**
	 * Sets the hl7LanguageCode
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	public void setHl7LanguageCode(org.ehealth_connector.common.hl7cdar2.CS value) {
		hl7LanguageCode = value;
	}

	/**
	 * Sets the hl7Reference
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public void setHl7Reference(org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference value) {
		hl7Reference = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}

	/**
	 * Sets the hl7Value
	 * The Base-64 encoded multimedia object.
	 */
	public void setHl7Value(org.ehealth_connector.common.hl7cdar2.ED value) {
		hl7Value = value;
	}
}