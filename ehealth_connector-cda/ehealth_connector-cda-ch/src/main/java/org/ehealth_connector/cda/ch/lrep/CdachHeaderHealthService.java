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

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.46
 * Template description: Information about a health service describing the context of this CDA document. All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST use this template by either reference or specialisation.
 *
 * Element description: Information about a health service describing the context of this CDA document.
 */
public class CdachHeaderHealthService extends org.ehealth_connector.common.hl7cdar2.POCDMT000040DocumentationOf {

	private org.ehealth_connector.common.hl7cdar2.POCDMT000040ServiceEvent hl7ServiceEvent;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	/**
	 * Gets the hl7ServiceEvent
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040ServiceEvent getHl7ServiceEvent() {
		return hl7ServiceEvent;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Sets the hl7ServiceEvent
	 */
	public void setHl7ServiceEvent(org.ehealth_connector.common.hl7cdar2.POCDMT000040ServiceEvent value) {
		hl7ServiceEvent = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}
}
