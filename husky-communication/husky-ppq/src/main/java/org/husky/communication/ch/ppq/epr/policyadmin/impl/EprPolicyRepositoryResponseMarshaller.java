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
package org.husky.communication.ch.ppq.epr.policyadmin.impl;

import org.husky.communication.ch.ppq.epr.policyadmin.api.OpenSamlEprPolicyRepositoryResponse;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.AbstractXMLObjectMarshaller;
import org.opensaml.core.xml.io.MarshallingException;
import org.w3c.dom.Element;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the marshaller for OpenSamlAddPolicyRequest.</div>
 * <div class="de">Die Klasse implementiert den Marshaller für OpenSamlAddPolicyRequest.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class EprPolicyRepositoryResponseMarshaller extends AbstractXMLObjectMarshaller {

	@Override
	protected void marshallAttributes(XMLObject xmlObject, Element domElement)
			throws MarshallingException {
		final OpenSamlEprPolicyRepositoryResponse purposeOfUse = (OpenSamlEprPolicyRepositoryResponse) xmlObject;
		if (purposeOfUse.getStatus() != null) {
			domElement.setAttributeNS(null, "status", purposeOfUse.getStatus());
		}
	}

}