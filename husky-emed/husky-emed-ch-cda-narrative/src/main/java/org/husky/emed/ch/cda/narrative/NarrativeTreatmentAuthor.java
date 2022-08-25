package org.husky.emed.ch.cda.narrative;

import lombok.NonNull;

/**
 * Represents the author of an eMed document, section or item entry
 *
 * @author Ronaldo Loureiro
 */
public class NarrativeTreatmentAuthor {

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private String organization;
}
