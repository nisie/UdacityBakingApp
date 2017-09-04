package com.nisie.udacitybakingapp.recipe.view.presenter;

import com.nisie.udacitybakingapp.recipe.domain.interactor.GetRecipeUseCase;
import com.nisie.udacitybakingapp.recipe.view.listener.Recipe;
import com.nisie.udacitybakingapp.recipe.view.subscriber.GetRecipeSubscriber;

/**
 * @author by nisie on 9/1/17.
 */

public class RecipePresenterImpl implements Recipe.Presenter {

    private final Recipe.View viewListener;
    private final GetRecipeUseCase getRecipeUseCase;

    public RecipePresenterImpl(Recipe.View viewListener,
                               GetRecipeUseCase getRecipeUseCase) {
        this.viewListener = viewListener;
        this.getRecipeUseCase = getRecipeUseCase;
    }

    @Override
    public void unbind() {

    }

    @Override
    public void getRecipe() {
        viewListener.showLoading();
        getRecipeUseCase.execute(GetRecipeUseCase.getParam(), new GetRecipeSubscriber(viewListener));
    }
}
