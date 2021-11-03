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
package org.husky.xua.exceptions;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the BuildException.</div>
 * <div class="de">Klasse implemetiert die BuildException.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class BuildException extends Exception {

	/** Field referencing the serialVersionUID */
	private static final long serialVersionUID = -4015345681495951592L;

	/**
	 *
	 * <!-- @formatter:off -->
	 * <div class="en">Default constructor to instanciate the object.</div>
	 * <div class="de">Default Konstruktor für die Instanziierung des Objekts.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @param clazz
	 * <div class="en">the class wanted to be created</div>
	 * <div class="de">die Klasse die erstellt werden sollte</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 * @param cause
	 * <div class="en">the cause to be set</div>
	 * <div class="de">der Grund der gesetzt werden soll</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	public BuildException(Class<?> clazz, Throwable cause) {
		super("Error createing the class " + clazz.getName(), cause);
	}

}