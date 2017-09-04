package com.nisie.udacitybakingapp.recipe.domain.network.api;

import com.nisie.udacitybakingapp.recipe.domain.network.pojo.RecipeResult;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author by nisie on 9/1/17.
 */

public interface BakingAPI {

    @GET("android-baking-app-json")
    Observable<Response<List<RecipeResult>>> getRecipeList(@QueryMap Map<String, Object> params);
}
