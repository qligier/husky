package org.husky.emed.ch.cda.narrative.treatment;

import lombok.Data;
import lombok.NonNull;
import org.husky.emed.ch.models.treatment.MedicationProductIngredient;

import java.util.Objects;

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
    private String quantity;

    /**
     * The unit of quantity
     */
    @NonNull
    private String unit;

    /**
     * Constructor
     * @param name the ingredient's name
     * @param quantity the ingredient's quantity
     * @param unit the quantity's unit
     */
    public NarrativeTreatmentIngredient(@NonNull String name, @NonNull String quantity, @NonNull String unit) {
        this.name = Objects.requireNonNull(name);
        this.quantity = Objects.requireNonNull(quantity);
        this.unit = Objects.requireNonNull(unit);
    }
}
