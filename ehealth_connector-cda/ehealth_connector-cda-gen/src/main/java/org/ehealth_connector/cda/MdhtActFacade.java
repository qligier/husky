/*******************************************************************************
	 *
	 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
	 * All rights reserved. http://medshare.net
	 *
	 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
	 *
	 * This code is are made available under the terms of the Eclipse Public License v1.0.
	 *
	 * Accompanying materials are made available under the terms of the Creative Commons
	 * Attribution-ShareAlike 4.0 License.
	 *
	 * Year of publication: 2015
	 *
	 *******************************************************************************/
package org.ehealth_connector.cda;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openhealthtools.mdht.uml.cda.Act;

/**
 * MdhtEntryObservationFacade is a facade for extending the mdht objects
 * generated by the model The design enables that all derived convenience
 * objects can use the underlying mdht model but the exposing api of the classes
 * is independent of the mdht implementation.
 *
 * @param <E>
 *          the model type to provide for implementing the facade to it,
 *          extending an Participant
 */
public class MdhtActFacade<E extends Act> extends MdhtFacade<E> {

	/** The log. */
	private final Log log = LogFactory.getLog(MdhtFacade.class);

	/**
	 * Instantiates a new facade for the provided mdht object.
	 *
	 * @param mdht
	 *          the mdht model object
	 */
	protected MdhtActFacade(E mdht) {
		super(mdht, null, null);
	}
}
