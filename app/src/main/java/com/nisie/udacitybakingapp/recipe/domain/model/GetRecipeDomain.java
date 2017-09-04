package com.nisie.udacitybakingapp.recipe.domain.model;

import java.util.List;

/**
 * @author by nisie on 9/1/17.
 */

public class GetRecipeDomain {

    List<RecipeResultDomain> listRecipe;

    public GetRecipeDomain(List<RecipeResultDomain> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public List<RecipeResultDomain> getListRecipe() {
        return listRecipe;
    }
}
