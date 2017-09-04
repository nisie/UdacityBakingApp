package com.nisie.udacitybakingapp.recipe.domain.interactor;

import com.nisie.udacitybakingapp.main.domain.executor.PostExecutionThread;
import com.nisie.udacitybakingapp.main.domain.executor.ThreadExecutor;
import com.nisie.udacitybakingapp.main.presentation.UseCase;
import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;
import com.nisie.udacitybakingapp.recipe.domain.repository.BakingRepository;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * @author by nisie on 9/1/17.
 */

public class GetRecipeUseCase extends UseCase<GetRecipeDomain> {

    private BakingRepository bakingRepository;

    public GetRecipeUseCase(ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread,
                            BakingRepository bakingRepository) {
        super(threadExecutor, postExecutionThread);
        this.bakingRepository = bakingRepository;
    }

    public static Map<String, Object> getParam() {
        return new HashMap<>();
    }

    @Override
    public Observable<GetRecipeDomain> createObservable(Map<String, Object> requestParams) {
        return bakingRepository.getRecipe(requestParams);
    }

}
