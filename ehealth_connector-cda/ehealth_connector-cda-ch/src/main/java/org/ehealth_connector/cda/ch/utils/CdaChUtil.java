/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/
package org.ehealth_connector.cda.ch.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.ehealth_connector.cda.ch.edes.CdaChEdesCommon;
import org.ehealth_connector.cda.ch.edes.CdaChEdesCtnn;
import org.ehealth_connector.cda.ch.edes.CdaChEdesEdpn;
import org.ehealth_connector.cda.ch.lab.lrph.CdaChLrph;
import org.ehealth_connector.cda.ch.lab.lrqc.CdaChLrqc;
import org.ehealth_connector.cda.ch.lab.lrtp.CdaChLrtp;
import org.ehealth_connector.cda.ch.vacd.CdaChVacd;
import org.ehealth_connector.cda.ch.vacd.enums.SectionsVACD;
import org.ehealth_connector.cda.utils.CdaUtil;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.ihe.utils.UUID;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;

/**
 * <div class="en">A util class with helper functions.</div>
 * <div class="de">Eine Klasse mit Hilfsfunktionen.</div>
 */
public abstract class CdaChUtil extends CdaUtil {

	/**
	 * <div class="en">Creates a UUID for EDES documents with the EDES root ID
	 * and a generated extension.</div>
	 *
	 * @param id
	 *            <br>
	 *            <div class="en"> the id</div>
	 * @return the ii
	 */
	public static Identificator createUuidEdes(String id) {
		final II ii = DatatypesFactory.eINSTANCE.createII();
		ii.setRoot(CdaChEdesCommon.OID_MAIN);
		if (id == null) {
			ii.setExtension(UUID.generate());
		} else {
			ii.setExtension(id);
		}
		return new Identificator(ii);
	}

	/**
	 * <div class="en">Creates a UUID for VACD documents with the VACD root ID
	 * and a generated extension.</div>
	 *
	 * @param id
	 *            <br>
	 *            <div class="en"> the id</div>
	 * @return the ii
	 */
	public static II createUuidVacd(String id) {
		final II ii = DatatypesFactory.eINSTANCE.createII();
		ii.setRoot(CdaChVacd.OID_MAIN);
		if (id == null) {
			ii.setExtension(UUID.generate());
		} else {
			// TODO should this not be ii.setExtension(id) ?
			// ii.setRoot(id);
			ii.setExtension(id);
		}
		return ii;
	}

	/**
	 * <div class="en">Creates a UUID for VACD documents with the VACD root ID
	 * (if the root id is null, otherwise the provided id will be used) and a
	 * generated extension.</div>
	 *
	 * @param id
	 *            <br>
	 *            <div class="en"> the id</div>
	 * @return the ii
	 */
	public static II createUuidVacdIdentificator(Identificator id) {
		II ii;
		if (id == null) {
			ii = createUuidVacd(null);
		} else {
			ii = id.getIi();
		}
		return ii;
	}

	/**
	 * <div class="en">Loads a CdaChEdesCtnn document from a given File.</div>
	 * <div class="de">Lädt ein CdaChEdesCtnn aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChEdesCtnn Document
	 */
	public static CdaChEdesCtnn loadEdesCtnnFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChEdesCtnn> loader = new CdaChLoader<CdaChEdesCtnn>();
		return loader.loadFromStream(inputstream, CdaChEdesCtnn.class,
				org.openhealthtools.mdht.uml.cda.ch.CdaChEdesCtnn.class);
	}

	/**
	 * <div class="en">Loads a CdaChEdesEdpn document from a given File.</div>
	 * <div class="de">Lädt ein CdaChEdesEdpn aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChEdesEdpn Document
	 */
	public static CdaChEdesEdpn loadEdesEdpnFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChEdesEdpn> loader = new CdaChLoader<CdaChEdesEdpn>();
		return loader.loadFromStream(inputstream, CdaChEdesEdpn.class,
				org.openhealthtools.mdht.uml.cda.ch.CdaChEdesEdpn.class);
	}

	/**
	 * <div class="en">Loads a CdaChLrph document from a given File.</div>
	 * <div class="de">Lädt ein CdaChLrph aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChLrph Document
	 */
	public static CdaChLrph loadLrphFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChLrph> loader = new CdaChLoader<CdaChLrph>();
		return loader.loadFromStream(inputstream, CdaChLrph.class,
				org.openhealthtools.mdht.uml.cda.ch.CdaChLrph.class);
	}

	/**
	 * <div class="en">Loads a CdaChLrqc document from a given File.</div>
	 * <div class="de">Lädt ein CdaChLrqc aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChLrqc Document
	 */
	public static CdaChLrqc loadLrqcFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChLrqc> loader = new CdaChLoader<CdaChLrqc>();
		return loader.loadFromStream(inputstream, CdaChLrqc.class,
				org.openhealthtools.mdht.uml.cda.ch.CdaChLrqc.class);
	}

	/**
	 * <div class="en">Loads a CdaChLrtp document from a given File.</div>
	 * <div class="de">Lädt ein CdaChLrtp aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChLrtp Document
	 */
	public static CdaChLrtp loadLrtpFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChLrtp> loader = new CdaChLoader<CdaChLrtp>();
		return loader.loadFromStream(inputstream, CdaChLrtp.class,
				org.openhealthtools.mdht.uml.cda.ch.CdaChLrtp.class);
	}

	/**
	 * <div class="en">Loads a CdaChVacd document from a given File.</div>
	 * <div class="de">Lädt ein CdaChVacd aus einer Datei.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param filePath
	 *            path to the XML file
	 * @throws Exception
	 *             the exception
	 * @return the CdaChVacd Document
	 */
	public static CdaChVacd loadVacdFromFile(String filePath) throws Exception {
		final InputStream inputstream = new FileInputStream(filePath);
		final CdaChLoader<CdaChVacd> loader = new CdaChLoader<CdaChVacd>();
		return loader.loadFromStream(inputstream, CdaChVacd.class,
				org.openhealthtools.mdht.uml.cda.ch.VACD.class);
	}

	/**
	 * Updates a Reference if it is a comment (in a deph of two counters)
	 *
	 * @param er
	 *            the EntryRelationship
	 * @param i
	 *            first counter
	 * @param j
	 *            second counter
	 * @param prefix
	 *            the prefix of the reference
	 * @return the EntryRelationship
	 */
	public static EntryRelationship updateRefIfComment(EntryRelationship er, int i, int j,
			SectionsVACD prefix) {
		if (er.getTypeCode().equals(x_ActRelationshipEntryRelationship.SUBJ)
				&& er.getInversionInd()) {
			// Get the ed and update it with the reference
			final ED ed = er.getAct().getText();
			final TEL tel = DatatypesFactory.eINSTANCE.createTEL();
			ed.setReference(tel);
			if (CdaChVacd.CDA_LEVEL2_TEXT_GENERATION) {
				tel.setValue("#" + prefix.getContentIdPrefix() + "-comment" + i + j);
			} else {
				tel.setValue(("#" + prefix.getContentIdPrefix() + "1"));
			}
			er.getAct().setText(ed);
		}
		return er;
	}

	/**
	 * Updates a Reference if it is a comment
	 *
	 * @param er
	 *            the EntryRelationship
	 * @param ref
	 *            the reference
	 * @param prefix
	 *            the prefix of the reference
	 * @return the EntryRelationship
	 */
	public static EntryRelationship updateRefIfComment(EntryRelationship er, String ref,
			SectionsVACD prefix) {
		if (Util.isComment(er)) {
			// Get the ed and update it with the reference
			final ED ed = er.getAct().getText();
			final TEL tel = DatatypesFactory.eINSTANCE.createTEL();
			ed.setReference(tel);
			if (CdaChVacd.CDA_LEVEL2_TEXT_GENERATION) {
				tel.setValue("#" + prefix.getContentIdPrefix() + "-comment" + ref);
			} else {
				tel.setValue(("#" + prefix.getContentIdPrefix() + "1"));
			}
			er.getAct().setText(ed);
		}
		return er;
	}
}
