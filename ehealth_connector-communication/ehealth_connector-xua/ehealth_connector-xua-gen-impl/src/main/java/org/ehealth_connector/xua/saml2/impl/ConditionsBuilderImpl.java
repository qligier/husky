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
package org.ehealth_connector.xua.saml2.impl;

import org.ehealth_connector.xua.core.SecurityObjectBuilder;
import org.ehealth_connector.xua.saml2.ConditionsBuilder;
import org.openehealth.ipf.commons.ihe.xacml20.stub.saml20.assertion.ConditionsType;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the corresponding interface for Condition building.</div>
 * <div class="de">Die Klasse implementiert das entsprechende Interface um Conditions bilden zu k&ooml;nnen.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class ConditionsBuilderImpl implements ConditionsBuilder,
		SecurityObjectBuilder<org.opensaml.saml.saml2.core.Conditions, ConditionsType> {

	private org.opensaml.saml.saml2.core.Conditions conditions;

	public ConditionsBuilderImpl() {
		conditions = new org.opensaml.saml.saml2.core.impl.ConditionsBuilder().buildObject();
	}

	@Override
	public ConditionsType create() {
		return new ConditionsImpl(conditions);
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.core.SecurityObjectBuilder#create(java.lang.Object)
	 */
	public ConditionsType create(org.opensaml.saml.saml2.core.Conditions aInternalObject) {
		return new ConditionsImpl(aInternalObject);
	}

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.core.SecurityObjectBuilder#create(java.lang.Object)
	 */
	public org.opensaml.saml.saml2.core.Conditions create(ConditionsType aInternalObject) {
		return new ConditionsImpl(aInternalObject).getWrappedObject();
	}

}
