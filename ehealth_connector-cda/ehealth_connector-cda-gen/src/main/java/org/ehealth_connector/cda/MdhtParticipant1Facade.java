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

import java.util.Date;

import org.ehealth_connector.common.utils.DateUtil;
import org.openhealthtools.mdht.uml.cda.Participant1;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassAssociative;

/**
 * MdhtEntryObservationFacade is a facade for extending the mdht objects
 * generated by the model The design enables that all derived convenience
 * objects can use the underlying mdht model but the exposing api of the classes
 * is independent of the mdht implementation.
 *
 * @param <E>
 *          the model type to provide for implementing the facade to it,
 *          extending an Act
 */
public class MdhtParticipant1Facade<E extends Participant1> extends MdhtFacade<E> {

	/**
	 * Instantiates a new facade for the provided mdht object.
	 *
	 * @param mdht
	 *          the mdht model object
	 */
	protected MdhtParticipant1Facade(E mdht) {
		super(mdht, null, null);
	}

	public AssociatedEntity getAssociatedEntity() {
		if (getMdht().getAssociatedEntity() != null) {
			return new AssociatedEntity(getMdht().getAssociatedEntity());
		}
		return null;
	}

	/**
	 * Gets the time as Java Date Object
	 *
	 * @return the time
	 */
	public Date getTime() {
		if (getMdht().getTime() != null) {
			return DateUtil.parseIVL_TSVDateTimeValue(getMdht().getTime());
		}
		return null;
	}

	public void setAssociatedEntity(AssociatedEntity entity) {
		getMdht().setAssociatedEntity(entity.getMdht());
		getMdht().getAssociatedEntity().setClassCode(RoleClassAssociative.PROV);
	}

	/**
	 * Sets the time as Data object
	 *
	 * @param date
	 *          the date
	 */
	public void setTime(Date date) {
		getMdht().setTime(DateUtil.convertDateyyyyMMddHHmmssZZZZ(date));
	}
}
