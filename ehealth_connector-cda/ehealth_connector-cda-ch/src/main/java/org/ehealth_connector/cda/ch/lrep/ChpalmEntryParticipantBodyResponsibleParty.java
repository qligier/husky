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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.9
 * Template description: External laboratory that has delivered the results. The following applies:
 * - The external laboratory MUST be declared, including its responsible person.
 * - All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
 */
public class ChpalmEntryParticipantBodyResponsibleParty extends org.ehealth_connector.common.hl7cdar2.POCDMT000040ParticipantRole {

	/**
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.AD> hl7Addr;

	/**
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040PlayingEntity hl7PlayingEntity;

	/**
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.TEL> hl7Telecom;

	/**
	 * Adds a hl7Addr
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public void addHl7Addr(org.ehealth_connector.common.hl7cdar2.AD value) {
		hl7Addr.add(value);
	}

	/**
	 * Adds a hl7Telecom
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public void addHl7Telecom(org.ehealth_connector.common.hl7cdar2.TEL value) {
		hl7Telecom.add(value);
	}

	/**
	 * Adds a hl7Addr
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public void clearHl7Addr() {
		hl7Addr.clear();
	}

	/**
	 * Adds a hl7Telecom
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public void clearHl7Telecom() {
		hl7Telecom.clear();
	}

	/**
	 * Gets the hl7PlayingEntity
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040PlayingEntity getHl7PlayingEntity() {
		return hl7PlayingEntity;
	}

	/**
	 * Sets the hl7PlayingEntity
	 * All persons and organizations, MUST according to XD-LAB contain name, addr and telecom.
	 */
	public void setHl7PlayingEntity(org.ehealth_connector.common.hl7cdar2.POCDMT000040PlayingEntity value) {
		hl7PlayingEntity = value;
	}
}
