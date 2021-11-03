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
package org.husky.xua.communication.config.impl;

import org.husky.xua.communication.config.SoapClientConfig;
import org.husky.xua.communication.config.SoapClientConfigBuilder;
import org.husky.xua.communication.config.SoapClientConfig.SoapVersion;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the interface ClientConfigBuilder serving as builder to create config for client with basic authentication.</div>
 * <div class="de">Klasse die das Interface ClienConfiguilder implementiert. Dient dazu eine Client Konfiguration zu bilden für die Kommunikation mit BasicAuthentication.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class SoapClientConfigBuilderImpl implements SoapClientConfigBuilder {

	private SoapClientConfig config;

	public SoapClientConfigBuilderImpl() {
		config = new BaseSoapClientConfigImpl();
	}

	@Override
	public SoapClientConfigBuilderImpl clientKeyStore(String clientKeyStore) {
		config.setKeyStore(clientKeyStore);
		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl clientKeyStorePassword(String clientKeyStorePassword) {
		config.setKeyStorePassword(clientKeyStorePassword);
		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl clientKeyStoreType(String clientKeyStoreType) {
		config.setKeyStoreType(clientKeyStoreType);
		return this;
	}

	public SoapClientConfig create() {
		return config;
	}

	@Override
	public SoapClientConfigBuilderImpl portName(String portName) {
		config.setPortName(portName);
		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl portNamespace(String portNamespace) {
		config.setPortNamespace(portNamespace);
		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl serviceName(String serviceName) {
		config.setServiceName(serviceName);
		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl serviceNamespace(String serviceNamespace) {
		config.setServiceNamespace(serviceNamespace);
		return this;
	}

	@Override
	public SoapClientConfigBuilder simple(boolean aSimple) {

		return this;
	}

	@Override
	public SoapClientConfigBuilderImpl soapVersion(SoapVersion soapVersion) {
		config.setSoapVersion(soapVersion);
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.husky.xua.communication.config.ClientConfigBuilder#url(java.lang.String)
	 */
	@Override
	public SoapClientConfigBuilderImpl url(String aEndpointUrl) {
		config.setUrl(aEndpointUrl);
		return this;
	}

}