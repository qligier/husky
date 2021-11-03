package org.husky.emed.cda.services.readers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.cda.errors.InvalidEmedContentException;
import org.husky.emed.cda.generated.artdecor.enums.ActivePharmaceuticalIngredient;
import org.husky.emed.cda.generated.artdecor.enums.PharmaceuticalDoseFormEdqm;
import org.husky.emed.cda.generated.hl7cdar2.CD;
import org.husky.emed.cda.generated.hl7cdar2.COCTMT230100UVIngredient;
import org.husky.emed.cda.generated.hl7cdar2.POCDMT000040Material;
import org.husky.emed.cda.models.common.QuantityWithUnit;
import org.husky.emed.cda.models.treatment.MedicationProduct;
import org.husky.emed.cda.models.treatment.MedicationProductIngredient;
import org.husky.emed.cda.utils.CdaR2Utils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Quentin Ligier
 */
public class ManufacturedMaterialReader {

    /**
     *
     */
    private final POCDMT000040Material material;

    /**
     * Constructor.
     *
     * @param material
     */
    public ManufacturedMaterialReader(final POCDMT000040Material material) {
        Objects.requireNonNull(material);
        this.material = material;
    }

    /**
     * Returns the GTIN code value or {@code null} if it's a magistral preparation/compound medicine.
     */
    @Nullable
    public String getGtinCode() {
        return this.material.getCode().getCode();
    }

    /**
     * Returns the name or {@code null} if it doesn't exist.
     */
    @Nullable
    public String getName() {
        return CdaR2Utils.getSingleNullableMixedOrThrow(this.material.getName());
    }

    /**
     * Returns the form code as a value of {@link PharmaceuticalDoseFormEdqm} or {@code null} if it's not defined.
     */
    @Nullable
    public PharmaceuticalDoseFormEdqm getFormCode() throws InvalidEmedContentException {
        if (this.material.getFormCode() == null) {
            return null;
        }
        if (!PharmaceuticalDoseFormEdqm.isInValueSet(this.material.getFormCode().getCode())) {
            throw new InvalidEmedContentException("The formCode is not given in the mandatory value set");
        }
        return PharmaceuticalDoseFormEdqm.getEnum(this.material.getFormCode().getCode());
    }

    /**
     * Returns the lot number text or {@code null} if it's not defined.
     */
    @Nullable
    public String getLotNumberText() {
        return Optional.ofNullable(this.material.getLotNumberText()).map(CdaR2Utils::getSingleNullableMixedOrThrow).orElse(null);
    }

    /**
     * Returns the packaging of the medicine or {@code null} if it's not defined.
     */
    @Nullable
    public ContainerPackagedMedicineReader getPackagedMedicine() {
        if (this.material.getAsContent() == null || this.material.getAsContent().getContainerPackagedMedicine() == null) {
            return null;
        }
        return new ContainerPackagedMedicineReader(this.material.getAsContent().getContainerPackagedMedicine());
    }

    /**
     * Converts the ManufacturedMaterial to a {@link MedicationProduct}.
     */
    public MedicationProduct toMedicationProduct() throws InvalidEmedContentException {
        final MedicationProduct product = new MedicationProduct();

        product.setGtinCode(this.getGtinCode());
        product.setName(this.getName());
        product.setFormCode(this.getFormCode());
        product.setLotNumber(this.getLotNumberText());

        final ContainerPackagedMedicineReader packagedMedicine = this.getPackagedMedicine();
        if (packagedMedicine != null) {
            product.setPackagedProduct(packagedMedicine.toMedicationPackagedProduct());
        }

        product.getIngredients().addAll(this.material.getIngredient().stream()
            .map((COCTMT230100UVIngredient ingredient) -> {
                final String name =
                        CdaR2Utils.getSingleNullableMixedOrThrow(ingredient.getIngredient().getValue().getName().get(0));
                final var code =
                    this.activePharmaceuticalIngredientFromCdOrNull(ingredient.getIngredient().getValue().getCode());

                QuantityWithUnit numerator = null;
                QuantityWithUnit denominator = null;
                if (ingredient.getQuantity() != null) {
                    if (ingredient.getQuantity().getNumerator() != null) {
                        numerator = QuantityWithUnit.fromPq(ingredient.getQuantity().getNumerator());
                    }
                    if (ingredient.getQuantity().getDenominator() != null) {
                        denominator = QuantityWithUnit.fromPq(ingredient.getQuantity().getDenominator());
                    }
                }
                return new MedicationProductIngredient(name, numerator, denominator, code);
            })
            .collect(Collectors.toList())
        );

        return product;
    }

    /**
     * Returns an instance of {@link ActivePharmaceuticalIngredient} corresponding to an HL7R2 {@link CD} or {@code
     * null}.
     *
     * @param cd The code to convert.
     * @return an instance of {@link ActivePharmaceuticalIngredient} or {@code null}.
     */
    @Nullable
    private ActivePharmaceuticalIngredient activePharmaceuticalIngredientFromCdOrNull(final CD cd) {
        Objects.requireNonNull(cd);
        final var activePharmaceuticalIngredient = ActivePharmaceuticalIngredient.getEnum(cd.getCode());
        if (activePharmaceuticalIngredient == null
                || !activePharmaceuticalIngredient.getCodeSystemId().equals(cd.getCodeSystem())
        ) {
            return null;
        }
        return activePharmaceuticalIngredient;
    }
}