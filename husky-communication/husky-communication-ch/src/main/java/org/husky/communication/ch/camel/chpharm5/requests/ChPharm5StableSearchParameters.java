/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */
package org.husky.communication.ch.camel.chpharm5.requests;

import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenOrListParam;
import ca.uhn.fhir.rest.param.TokenParam;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * husky
 *
 * @author Quentin Ligier
 **/
public abstract class ChPharm5StableSearchParameters extends ChPharm5SearchParameters {

    @Nullable
    protected DateRangeParam date;
    @Nullable
    protected DateRangeParam period;
    @Nullable
    protected TokenParam identifier;
    @Nullable
    protected TokenOrListParam facility;
    @Nullable
    protected TokenOrListParam setting;
    @Nullable
    protected TokenOrListParam event;
    @Nullable
    protected TokenOrListParam securityLabel;
    @Nullable
    protected StringParam authorFamilyName;
    @Nullable
    protected StringParam authorGivenName;

    protected ChPharm5StableSearchParameters(final String operation) {
        super(operation);
    }

    @Nullable
    public DateRangeParam getDate() {
        return date;
    }

    public void setDate(@Nullable final DateRangeParam date) {
        this.date = date;
    }

    @Nullable
    public DateRangeParam getPeriod() {
        return period;
    }

    public void setPeriod(@Nullable final DateRangeParam period) {
        this.period = period;
    }

    @Nullable
    public TokenParam getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@Nullable final TokenParam identifier) {
        this.identifier = identifier;
    }

    @Nullable
    public TokenOrListParam getFacility() {
        return facility;
    }

    public void setFacility(@Nullable final TokenOrListParam facility) {
        this.facility = facility;
    }

    @Nullable
    public TokenOrListParam getSetting() {
        return setting;
    }

    public void setSetting(@Nullable final TokenOrListParam setting) {
        this.setting = setting;
    }

    @Nullable
    public TokenOrListParam getEvent() {
        return event;
    }

    public void setEvent(@Nullable final TokenOrListParam event) {
        this.event = event;
    }

    @Nullable
    public TokenOrListParam getSecurityLabel() {
        return securityLabel;
    }

    public void setSecurityLabel(@Nullable final TokenOrListParam securityLabel) {
        this.securityLabel = securityLabel;
    }

    @Nullable
    public StringParam getAuthorFamilyName() {
        return authorFamilyName;
    }

    public void setAuthorFamilyName(@Nullable final StringParam authorFamilyName) {
        this.authorFamilyName = authorFamilyName;
    }

    @Nullable
    public StringParam getAuthorGivenName() {
        return authorGivenName;
    }

    public void setAuthorGivenName(@Nullable final StringParam authorGivenName) {
        this.authorGivenName = authorGivenName;
    }
}
