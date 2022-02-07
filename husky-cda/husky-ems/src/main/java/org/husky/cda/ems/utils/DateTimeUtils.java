package org.husky.cda.ems.utils;

import java.time.ZonedDateTime;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.husky.common.enums.NullFlavor;
import org.husky.common.hl7cdar2.IVLTS;
import org.husky.common.hl7cdar2.TS;
import org.husky.common.utils.time.DateTimes;

public class DateTimeUtils {

	public static final String HL7_NAMESPACE = "urn:hl7-org:v3";

	public static IVLTS createIvlts(ZonedDateTime startDate, ZonedDateTime stopDate) {
		if (startDate == null) {
			return null;
		}

		IVLTS ivlts = new IVLTS();
		ivlts.getRest().add(new JAXBElement<>(new QName(HL7_NAMESPACE, "low", XMLConstants.DEFAULT_NS_PREFIX),
				TS.class, DateTimes.toDatetimeTs(startDate)));

		if (stopDate == null) {
			TS ts = new TS(NullFlavor.UNKNOWN);
			ivlts.getRest().add(new JAXBElement<>(new QName(HL7_NAMESPACE, "high", XMLConstants.DEFAULT_NS_PREFIX),
					TS.class, ts));
		} else {
			ivlts.getRest().add(new JAXBElement<>(new QName(HL7_NAMESPACE, "high", XMLConstants.DEFAULT_NS_PREFIX),
					TS.class, DateTimes.toDatetimeTs(stopDate)));
		}

		return ivlts;
	}

}
