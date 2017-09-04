package com.nisie.udacitybakingapp.recipe.domain.repository;

import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;

import java.util.Map;

import rx.Observable;

/**
 * @author by nisie on 9/1/17.
 */

public interface BakingRepository {

    Observable<GetRecipeDomain> getRecipe(Map<String, Object> params);

}
