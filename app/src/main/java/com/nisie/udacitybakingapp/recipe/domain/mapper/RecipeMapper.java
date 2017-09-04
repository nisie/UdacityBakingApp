package com.nisie.udacitybakingapp.recipe.domain.mapper;

import com.nisie.udacitybakingapp.main.network.ErrorNetworkException;
import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.IngredientDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.RecipeResultDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.StepDomain;
import com.nisie.udacitybakingapp.recipe.domain.network.pojo.Ingredient;
import com.nisie.udacitybakingapp.recipe.domain.network.pojo.RecipeResult;
import com.nisie.udacitybakingapp.recipe.domain.network.pojo.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author by nisie on 9/1/17.
 */

public class RecipeMapper implements Func1<Response<List<RecipeResult>>, GetRecipeDomain> {

    @Override
    public GetRecipeDomain call(Response<List<RecipeResult>> resultResponse) {
        GetRecipeDomain domain;
        if (resultResponse.isSuccessful())
            domain = convertToDomain(resultResponse.body());
        else
            throw new ErrorNetworkException(String.valueOf(resultResponse.code()));
        return domain;
    }

    private GetRecipeDomain convertToDomain(List<RecipeResult> body) {
        ArrayList<RecipeResultDomain> listDomain = new ArrayList<>();
        for (RecipeResult result : body) {
            listDomain.add(new RecipeResultDomain(result.getId(),
                    result.getName(),
                    convertToIngredientsDomain(result.getIngredients()),
                    convertToStepDomain(result.getSteps()),
                    result.getServings(),
                    result.getImage()));
        }
        return new GetRecipeDomain(listDomain);
    }

    private List<StepDomain> convertToStepDomain(List<Step> steps) {
        List<StepDomain> list = new ArrayList<>();
        for (Step result : steps) {
            list.add(new StepDomain(result.getId(),
                    result.getShortDescription(),
                    result.getDescription(),
                    result.getVideoURL(),
                    result.getThumbnailURL()));
        }
        return list;
    }

    private List<IngredientDomain> convertToIngredientsDomain(List<Ingredient> ingredients) {
        List<IngredientDomain> list = new ArrayList<>();
        for (Ingredient result : ingredients) {
            list.add(new IngredientDomain(result.getQuantity(),
                    result.getMeasure(),
                    result.getIngredient()));
        }
        return list;
    }

}
