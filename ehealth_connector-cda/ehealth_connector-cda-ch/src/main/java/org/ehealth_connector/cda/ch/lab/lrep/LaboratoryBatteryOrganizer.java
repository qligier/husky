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
package org.ehealth_connector.cda.ch.lab.lrep;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ehealth_connector.cda.AbstractObservation;
import org.ehealth_connector.cda.AbstractObservationComparator;
import org.ehealth_connector.cda.ObservationMediaEntry;
import org.ehealth_connector.cda.ihe.lab.AbstractLaboratoryBatteryOrganizer;
import org.ehealth_connector.cda.utils.CdaUtil;
import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Performer;
import org.ehealth_connector.common.utils.DateUtil;
import org.openhealthtools.mdht.uml.cda.ObservationMedia;
import org.openhealthtools.mdht.uml.cda.Performer2;
import org.openhealthtools.mdht.uml.hl7.vocab.ActRelationshipHasComponent;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationPhysicalPerformer;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;

/**
 * The Class LaboratoryBatteryOrganizer.
 *
 * <div class="en">The Laboratory Battery Organizer provides grouping of results
 * </div> <div class="de">Der Laboratory Battery Organizer erlaubt gemäss IHE
 * XD-LAB die Gruppierung von Resultaten.</div>
 */
public class LaboratoryBatteryOrganizer extends AbstractLaboratoryBatteryOrganizer {

	/**
	 * Instantiates a new laboratory battery organizer.
	 */
	public LaboratoryBatteryOrganizer() {
		super();
	}

	/**
	 * Instantiates the class with the required elements.
	 *
	 * @param effectiveTime
	 *            <div class="en">the point in time of the measurement. If
	 *            unknown, effectiveTime has to be declared with
	 *            nullFlavor.</div> <div class="de">Zeitpunkt der Messung. Ist
	 *            dieser unbekannt, MUSS effectiveTime mit nullFlavor angegeben
	 *            werden. nullFlavor ist nur erlaubt, wenn der Organizer
	 *            ausschliesslich Körpergrösse oder Gewicht enthält. Wenn der
	 *            Organizer mindestens eine andere Beobachtung enthält, muss ein
	 *            Wert angegeben werden.</div>
	 * @param observation
	 *            the observation
	 */
	public LaboratoryBatteryOrganizer(Date effectiveTime, LaboratoryObservation observation) {
		this();
		setEffectiveTime(effectiveTime);
		addLaboratoryObservation(observation);
	}

	/**
	 * Instantiates a new laboratory battery organizer.
	 *
	 * @param mdht
	 *            the mdht
	 */
	public LaboratoryBatteryOrganizer(
			org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer mdht) {
		super(mdht);
	}

	/**
	 * Adds the author.
	 *
	 * @param author
	 *            the author
	 */
	public void addAuthor(Author author) {
		addAuthor(author, null);
	}

	/**
	 * Adds a author.
	 *
	 * @param author
	 *            the author
	 * @param dateTimeOfDocumentation
	 *            <div class="en">date and time, when the result was known</div>
	 *            <div class="de">Datum und Uhrzeit, an dem das Resultat bekannt
	 *            wurde.</div> <div class="fr"></div> <div class="it"></div>
	 */
	public void addAuthor(Author author, Date dateTimeOfDocumentation) {
		final org.openhealthtools.mdht.uml.cda.Author mAuthor = author.copyMdhtAuthor();
		mAuthor.setTypeCode(ParticipationType.AUT);
		try {
			mAuthor.setTime(DateUtil.createIVL_TSFromEuroDate(dateTimeOfDocumentation));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		getMdht().getAuthors().add(mAuthor);
	}

	/**
	 * Adds the laboratory observation.
	 *
	 * @param observation
	 *            the observation
	 */
	public void addLaboratoryObservation(LaboratoryObservation observation) {
		org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryObservation mdht = observation.copy();
		CdaUtil.addTemplateIdOnce(mdht, new Identificator("2.16.756.5.30.1.1.10.4.3"));
		getMdht().addObservation(mdht);

		final int nb = getMdht().getComponents().size() - 1;
		getMdht().getComponents().get(nb).setTypeCode(ActRelationshipHasComponent.COMP);

	}

	/**
	 * Adds the observation media entry.
	 *
	 * @param observationMedia
	 *            the observation media
	 */
	public void addObservationMediaEntry(ObservationMediaEntry observationMedia) {
		getMdht().addObservationMedia(observationMedia.copy());
		final int nb = getMdht().getComponents().size() - 1;
		getMdht().getComponents().get(nb).setTypeCode(ActRelationshipHasComponent.COMP);
	}

	/**
	 * Adds a performer.
	 *
	 * @param performer
	 *            the performer
	 */
	public void addPerformer(Performer performer) {
		addPerformer(performer, null);
	}

	/**
	 * Adds a performer.
	 *
	 * @param performer
	 *            the performer
	 * @param dateTimeOfPerformance
	 *            <div class="en">date and time, when the result was known</div>
	 *            <div class="de">Datum und Uhrzeit, an dem das Resultat bekannt
	 *            wurde.</div> <div class="fr"></div> <div class="it"></div>
	 */
	public void addPerformer(Performer performer, Date dateTimeOfPerformance) {
		final Performer2 mPerformer = performer.copyMdhtPerfomer();
		mPerformer.setTypeCode(ParticipationPhysicalPerformer.PRF);
		try {
			mPerformer.setTime(DateUtil.createIVL_TSFromEuroDate(dateTimeOfPerformance));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		getMdht().getPerformers().add(mPerformer);
	}

	/**
	 * Gets the laboratory observations.
	 *
	 * @return the laboratory observations
	 */
	@Override
	public List<AbstractObservation> getLaboratoryObservations() {
		final List<AbstractObservation> loList = new ArrayList<AbstractObservation>();
		for (final org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryObservation lo : getMdht()
				.getLaboratoryObservations()) {
			loList.add(new AbstractObservation(lo));
		}
		loList.sort(new AbstractObservationComparator());
		return loList;
	}

	/**
	 * Gets the laboratory observations.
	 *
	 * @return the laboratory observations
	 */
	public List<LaboratoryObservation> getLrqcLaboratoryObservations() {
		final List<LaboratoryObservation> loList = new ArrayList<LaboratoryObservation>();
		for (final org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryObservation lo : getMdht()
				.getLaboratoryObservations()) {
			loList.add(new LaboratoryObservation(lo));
		}
		return loList;
	}

	/**
	 * Gets the observation media entries.
	 *
	 * @return the observation media entries
	 */
	public List<ObservationMediaEntry> getObservationMediaEntries() {
		final List<ObservationMediaEntry> ol = new ArrayList<ObservationMediaEntry>();
		for (final ObservationMedia om : getMdht().getObservationMedia()) {
			ol.add(new ObservationMediaEntry(om));
		}
		return ol;
	}

	/**
	 * Sets the effective time interval.
	 *
	 * @param low
	 *            the low value
	 * @param high
	 *            the high value
	 */
	public void setEffectiveTime(Date low, Date high) {
		getMdht().setEffectiveTime(DateUtil.convertDateToIvlTsyyyyMMddHHmmssZZZZ(low, high));
	}

}