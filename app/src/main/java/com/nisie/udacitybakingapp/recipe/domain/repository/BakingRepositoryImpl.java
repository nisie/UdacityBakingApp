package com.nisie.udacitybakingapp.recipe.domain.repository;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.nisie.udacitybakingapp.recipe.data.RecipeContract;
import com.nisie.udacitybakingapp.recipe.domain.mapper.RecipeMapper;
import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.IngredientDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.RecipeResultDomain;
import com.nisie.udacitybakingapp.recipe.domain.network.BakingService;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author by nisie on 9/1/17.
 */

public class BakingRepositoryImpl implements BakingRepository {

    private final Context context;
    BakingService bakingService;
    RecipeMapper recipeMapper;

    public BakingRepositoryImpl(Context context, BakingService bakingService, RecipeMapper
            recipeMapper) {
        this.context = context;
        this.bakingService = bakingService;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public Observable<GetRecipeDomain> getRecipe(Map<String, Object> params) {
        return bakingService.getApi()
                .getRecipeList(params)
                .map(recipeMapper)
                .doOnNext(saveToContentProvider());
    }

    private Action1<GetRecipeDomain> saveToContentProvider() {
        return new Action1<GetRecipeDomain>() {
            @Override
            public void call(GetRecipeDomain getRecipeDomain) {
                ContentResolver contentResolver = context.getContentResolver();
                ContentValues contentValues = new ContentValues();

                for (RecipeResultDomain recipeResultDomain : getRecipeDomain.getListRecipe()) {
                    contentValues.put(RecipeContract.RecipeEntry.COLUMN_ID, recipeResultDomain.getId());
                    contentValues.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipeResultDomain.getName());

                    contentValues.put(RecipeContract.RecipeEntry.COLUMN_INGREDIENT, getIngredient
                            (recipeResultDomain.getIngredients()));
                    contentResolver.insert(RecipeContract.RecipeEntry.CONTENT_URI, contentValues);
                }
            }
        };
    }

    private String getIngredient(List<IngredientDomain> ingredients) {
        String ingredient = "";
        for (IngredientDomain ingredientDomain : ingredients) {
            ingredient += "- " + ingredientDomain.getQuantity() + " " + ingredientDomain
                    .getMeasure() + " of " + ingredientDomain
                    .getIngredient() + "\n";
        }
        return ingredient;
    }
}
