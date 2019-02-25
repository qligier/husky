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
package org.ehealth_connector.valueset.api;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.ehealth_connector.valueset.config.ValueSetConfig;
import org.ehealth_connector.valueset.model.ValueSet;

/**
 * Java Prototype for Value Set Management Concept.
 */
public interface ValueSetManager {

	public OutputStream downloadValueSet(ValueSetConfig valueSetConfig);

	public ValueSetConfig loadValueSetConfig(File config);

	public ValueSetConfig loadValueSetConfig(InputStream config);

	public ValueSet loadValueSetIheSvs(File valueSet);

	public ValueSet loadValueSetIheSvs(InputStream valueSet);

	public ValueSet loadValueSetJson(File valueSet);

	public ValueSet loadValueSetJson(InputStream valueSet);

}
