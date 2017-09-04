package com.nisie.udacitybakingapp.recipe.domain.model;

import java.util.List;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeResultDomain {

    private int id;
    private String name;
    private List<IngredientDomain> ingredients = null;
    private List<StepDomain> steps = null;
    private int servings;
    private String image;

    public RecipeResultDomain(int id, String name, List<IngredientDomain> ingredients,
                           List<StepDomain> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientDomain> getIngredients() {
        return ingredients;
    }

    public List<StepDomain> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
