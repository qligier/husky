package org.husky.emed.ch.cda.narrative;

import lombok.Data;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.ch.models.common.AddressDigest;
import org.husky.emed.ch.models.common.AuthorDigest;
import org.husky.emed.ch.models.common.OrganizationDigest;

/**
 * Represents the author of an eMed document, section or item entry
 *
 * @author Ronaldo Loureiro
 */
@Data
public class NarrativeTreatmentAuthor {

    @NonNull
    private String name;

    @Nullable
    private String address;

    @NonNull
    private String organization;

    public NarrativeTreatmentAuthor(@NonNull AuthorDigest author) {
        if (author.getOrganization() == null || author.getOrganization().getNames().size() == 0) {
            throw new IllegalStateException("The author must have an organization name.");
        }

        this.name = String.format("%s %s", author.getGivenName(), author.getFamilyName());

        if (author.getAddresses().size() > 0) {
            AddressDigest address = author.getAddresses().get(0);
            this.address = String.format("%s %s %s", address.getStreetName(), address.getPostalCode(), address.getCity());
        }

        OrganizationDigest organization = author.getOrganization();
        this.organization = organization.getNames().get(0);
    }
}
