package org.husky.emed.ch.cda.narrative;

import lombok.Data;
import lombok.NonNull;
import org.husky.emed.ch.models.treatment.MedicationProductIngredient;

/**
 * Represents the ingredient of a medicine
 *
 * @author Ronaldo Loureiro
 */
@Data
public class NarrativeTreatmentIngredient {

    /**
     * The name of ingredient
     */
    @NonNull
    private String name;

    /**
     * the quantity of an ingredient in a medicine
     */
    @NonNull
    private String quantityValue;

    /**
     * Constructor
     * @param ingredient An ingredient of a medication product.
     */
    public NarrativeTreatmentIngredient(MedicationProductIngredient ingredient) {
        this.name = ingredient.getName();

        if (ingredient.getQuantityNumerator() == null || ingredient.getQuantityNumerator().getValue() == null) {
            throw new IllegalStateException("The quantity of the ingredient must be specified");
        }

        this.quantityValue = ingredient.getQuantityNumerator().getValue();
    }
}
