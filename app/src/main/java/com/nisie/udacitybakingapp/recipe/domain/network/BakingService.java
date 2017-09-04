package com.nisie.udacitybakingapp.recipe.domain.network;

import com.nisie.udacitybakingapp.main.network.BaseService;
import com.nisie.udacitybakingapp.recipe.domain.network.api.BakingAPI;

import retrofit2.Retrofit;

/**
 * @author by nisie on 9/1/17.
 */

public class BakingService extends BaseService<BakingAPI> {

    private static final String BASE_URL = "https://go.udacity.com/";

    @Override
    protected void initApiService(Retrofit retrofit) {
        api = retrofit.create(BakingAPI.class);
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public BakingAPI getApi() {
        return api;
    }
}