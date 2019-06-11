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
package org.ehealth_connector.valueset.exceptions;

/**
 * <div class="en">Exception in case the value set initialization fails.</div>
 */
public class InitializationException extends Exception {

	/** The generated constant serialVersionUID. */
	private static final long serialVersionUID = 1057252687989322065L;

	/**
	 * Default constructor to instantiate the object.
	 *
	 * @param message
	 *            the message
	 */
	public InitializationException(String message) {
		super("The value set cannot be loaded. Please check the content of the file/stream.");
	}
}