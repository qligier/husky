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
package org.ehealth_connector.valueset.config;

import java.io.File;
import java.io.InputStream;

/**
 * Java Prototype for Value Set Management Concept.
 *
 * Die Validierungs-Methoden sollen nicht nur die Struktur prüfen, sondern auch
 * ob die zu lesenden Daten in die Datenstruktur aus ValueSet und ValueSetEntry
 * Klassen überführt werden können.
 *
 */
public interface ValueSetValidator {

	public boolean validateIheSvs(File valueSet);

	public boolean validateIheSvs(InputStream valueSet);

	public boolean validateJson(File valueSet);

	public boolean validateJson(InputStream valueSet);
}