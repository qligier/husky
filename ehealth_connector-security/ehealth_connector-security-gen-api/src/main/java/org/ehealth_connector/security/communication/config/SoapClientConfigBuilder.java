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
package org.ehealth_connector.security.communication.config;

/**
 * <!-- @formatter:off -->
 * <div class="en">Interface describing the ClientConfigBuilder methods.</div>
 * <div class="de">Interface beschreibende methoden des ClientConfigBuilders.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 */
public interface SoapClientConfigBuilder extends ClientConfigBuilder {

	/**
	 * 
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the port name of the security token webservice.</div>
	 * <div class="de">Methode um den Port Namen des security token webservices zu setzen.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param portName
	 * <div class="en">the port name</div>
	 * <div class="de">der Port Name</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>XuaClientConfig
	 * @return
	 * <div class="en">the actual instance of this builder</div>
	 * <div class="de">die aktuelle instanz des builders</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	SoapClientConfigBuilder portName(String portName);

	/**
	 * 
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the namespace of the port name of the security token webservice.</div>
	 * <div class="de">Methode um den Namespace des Port Namen des security token webservices zu setzen.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param portNamespace
	 * <div class="en">the namespace of the port name</div>
	 * <div class="de">der Namespace des Port Namens</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * @return
	 * <div class="en">the actual instance of this builder</div>
	 * <div class="de">die aktuelle instanz des builders</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	SoapClientConfigBuilder portNamespace(String portNamespace);

	/**
	 * 
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the service  name of the security token webservice.</div>
	 * <div class="de">Methode um den Service Namen des security token webservices zu setzen.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param serviceName
	 * <div class="en">the service name</div>
	 * <div class="de">der Service Namen</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * @return
	 * <div class="en">the actual instance of this builder</div>
	 * <div class="de">die aktuelle instanz des builders</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	SoapClientConfigBuilder serviceName(String serviceName);

	/**
	 * 
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the namespace of the service name of the security token webservice.</div>
	 * <div class="de">Methode um den Namespace des Service Namen des security token webservices zu setzen.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param serviceNamespace
	 * <div class="en">the namespace of the port name</div>
	 * <div class="de">der Namespace des Port Namens</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * @return
	 * <div class="en">the actual instance of this builder</div>
	 * <div class="de">die aktuelle instanz des builders</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	SoapClientConfigBuilder serviceNamespace(String serviceNamespace);

}
