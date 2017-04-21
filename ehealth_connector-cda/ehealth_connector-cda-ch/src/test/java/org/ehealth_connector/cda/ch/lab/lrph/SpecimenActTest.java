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
package org.ehealth_connector.cda.ch.lab.lrph;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;

import org.ehealth_connector.cda.testhelper.TestUtils;
import org.junit.Test;
import org.w3c.dom.Document;

public class SpecimenActTest extends TestUtils {

	@Test
	public void testLrph() throws XPathExpressionException {
		SpecimenAct act = new SpecimenAct();

		// LaboratoryIsolateOrganizer
		LaboratoryIsolateOrganizer labIsolateOrganizer = new LaboratoryIsolateOrganizer();
		act.addLaboratoryIsolateOrganizer(labIsolateOrganizer);

		assertNotNull(act.getLaboratoryIsolateOrganizers());
		assertTrue(!act.getLaboratoryIsolateOrganizers().isEmpty());
		Document document = act.getDocument();
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.3.1.5']"));

		// NotificationOrganizer
		NotificationOrganizer notificationOrganizer = new NotificationOrganizer();
		act.setNotificationOrganizer(notificationOrganizer);
		act.setNotificationOrganizer(new NotificationOrganizer());

		assertNotNull(act.getNotificationOrganizer());
		document = act.getDocument();
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.3.1.1']"));

		// OutbreakIdentification
		OutbreakIdentificationObservation oio = new OutbreakIdentificationObservation();
		act.setOutbreakIdentification(oio);
		act.setOutbreakIdentification(new OutbreakIdentificationObservation());

		assertNotNull(act.getOutbreakIdentification());
		document = act.getDocument();
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.3.1.1.3']"));
	}

	@Test
	public void testModel() throws XPathExpressionException {
		SpecimenAct act = new SpecimenAct();
		// add
		// LabortatoryBatteryOrganizer
		act.addLaboratoryBatteryOrganizer(new LaboratoryBatteryOrganizer());

		assertTrue(act.getLaboratoryBatteryOrganizers() != null
				&& !act.getLaboratoryBatteryOrganizers().isEmpty());
		Document document = act.getDocument();
		assertTrue(xExist(document, "//entryRelationship[@typeCode='COMP']"));
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.3.1.4']"));

		// SpecimenCollectionEntry
		org.ehealth_connector.cda.ch.lab.SpecimenCollectionEntry entry = new org.ehealth_connector.cda.ch.lab.SpecimenCollectionEntry();
		act.setSpecimenCollectionEntry(entry);
		assertNotNull(act.getSpecimenCollectionEntry());
		document = act.getDocument();
		xCount(document, "//entryRelationship[@typeCode='COMP']", 2);
		assertTrue(xExist(document, "//templateId[@root='1.3.6.1.4.1.19376.1.3.1.2']"));
	}
}
