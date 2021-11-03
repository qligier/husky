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
package org.husky.xua.communication.xua.impl;

import org.husky.xua.communication.xua.XUserAssertionResponse;
import org.husky.xua.communication.xua.XUserAssertionResponseBuilder;
import org.husky.xua.core.SecurityObjectBuilder;
import org.opensaml.soap.wstrust.RequestSecurityTokenResponse;
import org.opensaml.soap.wstrust.impl.RequestSecurityTokenResponseBuilder;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the corresponding interfaces XUserAssertionResponseBuilder and SecurityObjectBuilder.</div>
 * <div class="de">Die Klasse implementiert die entsprechenden Interfaces XUserAssertionResponseBuilder und SecurityObjectBuilder.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class XUserAssertionResponseBuilderImpl implements XUserAssertionResponseBuilder,
		SecurityObjectBuilder<RequestSecurityTokenResponse, XUserAssertionResponse> {

	private RequestSecurityTokenResponse response;

	public XUserAssertionResponseBuilderImpl() {
		response = new RequestSecurityTokenResponseBuilder().buildObject();
	}

	@Override
	public XUserAssertionResponse create() {
		return new XUserAssertionResponseImpl(response);
	}

	@Override
	public XUserAssertionResponse create(RequestSecurityTokenResponse aInternalObject) {
		return new XUserAssertionResponseImpl(aInternalObject);
	}

}