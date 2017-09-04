package com.nisie.udacitybakingapp.recipe.view.listener;

import com.nisie.udacitybakingapp.main.presentation.BasePresenter;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;

import java.util.List;

/**
 * @author by nisie on 9/1/17.
 */

public interface Recipe {

    interface Presenter extends BasePresenter {

        void getRecipe();
    }

    interface View {
        void onErrorGetRecipe();

        void onSuccessGetRecipe(List<RecipeViewModel> recipeViewModelList);

        void showLoading();

        void finishLoading();

        void onGoToDetailRecipe(RecipeViewModel recipeViewModel);

    }
}
