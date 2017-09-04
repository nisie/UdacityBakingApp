package com.nisie.udacitybakingapp.recipe.domain.model;

/**
 * @author by nisie on 9/1/17.
 */

public class IngredientDomain {

    private String quantity;
    private String measure;
    private String ingredient;

    public IngredientDomain(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
