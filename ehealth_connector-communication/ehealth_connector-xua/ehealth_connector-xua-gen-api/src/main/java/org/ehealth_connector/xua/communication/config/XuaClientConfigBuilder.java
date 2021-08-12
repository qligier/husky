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
package org.ehealth_connector.xua.communication.config;

/**
 * <!-- @formatter:off -->
 * <div class="en">Interface describing the XuaClientConfigBuilder methods.</div>
 * <div class="de">Interface welches die Methoden des XuaClientConfigBuilders beschreibt.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public interface XuaClientConfigBuilder extends SoapClientConfigBuilder {

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#clientKeyStore(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder clientKeyStore(String clientKeyStoreFile);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#clientKeyStorePassword(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder clientKeyStorePassword(String clientKeyStorePassword);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#clientKeyStoreType(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder clientKeyStoreType(String clientKeyStoreType);

	/**
	 *
	 * <!-- @formatter:off -->
	 * <div class="en">Method to create the config with the parameters set.</div>
	 * <div class="de">Methode um die Konfiguration mit den gesetzten Parametern zu erstellen.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @return
	 * <div class="en">the creakted XuaClientConfig instace.</div>
	 * <div class="de">Die erstellte XuaClientConfig Instanz.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	XuaClientConfig create();

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#portName(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder portName(String portName);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#portNamespace(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder portNamespace(String portNamespace);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#serviceName(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder serviceName(String serviceName);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#serviceNamespace(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder serviceNamespace(String serviceNamespace);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.SoapClientConfigBuilder#simple(boolean)
	 */
	@Override
	XuaClientConfigBuilder simple(boolean aSimple);

	/**
	 *
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.xua.communication.config.ClientConfigBuilder#url(java.lang.String)
	 */
	@Override
	XuaClientConfigBuilder url(String aEndpointUri);
}