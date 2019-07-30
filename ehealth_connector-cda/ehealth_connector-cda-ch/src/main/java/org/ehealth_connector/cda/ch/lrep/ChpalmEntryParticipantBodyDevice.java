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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.10
 * Template description: Declaration of analyzer, test kit and manufacturer used to determine the result.All participating elements (including devices), MUST according to XD-LAB contain name, addr and telecom.
 */
public class ChpalmEntryParticipantBodyDevice extends org.ehealth_connector.common.hl7cdar2.POCDMT000040ParticipantRole {

	/**
	 * Analyzer used to determine the result.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040Device hl7PlayingDevice;

	/**
	 * The test kit and its manufacturer CAN be specified.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040Entity hl7ScopingEntity;

	/**
	 * Gets the hl7PlayingDevice
	 * Analyzer used to determine the result.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Device getHl7PlayingDevice() {
		return hl7PlayingDevice;
	}

	/**
	 * Gets the hl7ScopingEntity
	 * The test kit and its manufacturer CAN be specified.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Entity getHl7ScopingEntity() {
		return hl7ScopingEntity;
	}

	/**
	 * Sets the hl7PlayingDevice
	 * Analyzer used to determine the result.
	 */
	public void setHl7PlayingDevice(org.ehealth_connector.common.hl7cdar2.POCDMT000040Device value) {
		hl7PlayingDevice = value;
	}

	/**
	 * Sets the hl7ScopingEntity
	 * The test kit and its manufacturer CAN be specified.
	 */
	public void setHl7ScopingEntity(org.ehealth_connector.common.hl7cdar2.POCDMT000040Entity value) {
		hl7ScopingEntity = value;
	}
}
