/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.ch.models.common;

import org.husky.emed.ch.enums.ChEmedTimingEvent;

import java.util.Objects;

/**
 * A dosage instruction for an intake.
 *
 * @author Quentin Ligier
 */
public class MedicationDosageIntake {

    /**
     * The event timing.
     */
    private ChEmedTimingEvent eventTiming;

    /**
     * The dose quantity.
     */
    private QuantityWithUnitCode doseQuantity;

    /**
     * Constructor.
     *
     * @param eventTiming  The event timing.
     * @param doseQuantity The dose quantity.
     */
    public MedicationDosageIntake(final ChEmedTimingEvent eventTiming,
                                  final QuantityWithUnitCode doseQuantity) {
        this.eventTiming = Objects.requireNonNull(eventTiming);
        this.doseQuantity = Objects.requireNonNull(doseQuantity);
    }

    /**
     * Returns the event timing.
     */
    public ChEmedTimingEvent getEventTiming() {
        return eventTiming;
    }

    /**
     * Sets the event timing.
     *
     * @param eventTiming The event timing.
     */
    public void setEventTiming(final ChEmedTimingEvent eventTiming) {
        this.eventTiming = Objects.requireNonNull(eventTiming);
    }

    /**
     * Returns the dose quantity.
     */
    public QuantityWithUnitCode getDoseQuantity() {
        return doseQuantity;
    }

    /**
     * Sets the dose quantity.
     *
     * @param doseQuantity The dose quantity.
     */
    public void setDoseQuantity(final QuantityWithUnitCode doseQuantity) {
        this.doseQuantity = Objects.requireNonNull(doseQuantity);
    }

    @Override
    public String toString() {
        return "MedicationDosageIntake{" +
                "eventTiming=" + eventTiming +
                ", doseQuantity=" + doseQuantity +
                '}';
    }
}
