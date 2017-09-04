package com.nisie.udacitybakingapp.recipe.domain.repository;

import com.nisie.udacitybakingapp.recipe.domain.mapper.RecipeMapper;
import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;
import com.nisie.udacitybakingapp.recipe.domain.network.BakingService;

import java.util.Map;

import rx.Observable;

/**
 * @author by nisie on 9/1/17.
 */

public class BakingRepositoryImpl implements BakingRepository {

    BakingService bakingService;
    RecipeMapper recipeMapper;

    public BakingRepositoryImpl(BakingService bakingService, RecipeMapper recipeMapper) {
        this.bakingService = bakingService;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public Observable<GetRecipeDomain> getRecipe(Map<String, Object> params) {
        return bakingService.getApi().getRecipeList(params)
                .map(recipeMapper);
    }
}
