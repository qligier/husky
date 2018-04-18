/*
 *
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
package org.ehealth_connector.communication.ch.ppq.impl;

import org.ehealth_connector.communication.ch.ppq.PrivacyPolicyFeed.PpfMethod;
import org.ehealth_connector.communication.ch.ppq.PrivacyPolicyFeedResponse;
import org.ehealth_connector.communication.ch.ppq.PrivacyPolicyFeedResponseBuilder;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the interfaces PrivacyPolicyFeedResponseBuilder.</div>
 * <div class="de">Klasse welche das Interface PrivacyPolicyFeedResponseBuilder implementiert.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 */
public class PrivacyPolicyFeedResponseBuilderImpl implements PrivacyPolicyFeedResponseBuilder {

	private PrivacyPolicyFeedResponseImpl response;

	public PrivacyPolicyFeedResponseBuilderImpl() {
		response = new PrivacyPolicyFeedResponseImpl();
	}

	@Override
	public PrivacyPolicyFeedResponseBuilder status(String aStatus) {
		response.setStatus(aStatus);
		return this;
	}

	@Override
	public PrivacyPolicyFeedResponseBuilder method(PpfMethod aMethod) {
		response.setMethod(aMethod);
		return this;
	}

	@Override
	public PrivacyPolicyFeedResponse create() {
		return response;
	}

}
