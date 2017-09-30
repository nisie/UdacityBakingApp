package com.nisie.udacitybakingapp.recipe.view.viewmodel;

/**
 * @author by nisie on 9/30/17.
 */

public class RecipeWidgetModel {
    int id;
    String recipeName;
    String ingredients;

    public RecipeWidgetModel(int id, String recipeName, String ingredients) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }
}
