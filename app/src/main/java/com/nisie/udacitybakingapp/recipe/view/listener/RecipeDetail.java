package com.nisie.udacitybakingapp.recipe.view.listener;

import com.nisie.udacitybakingapp.main.presentation.BasePresenter;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeDetailViewModel;

import java.util.ArrayList;

/**
 * @author by nisie on 9/2/17.
 */

public interface RecipeDetail {

    interface Presenter extends BasePresenter {

    }

    interface View {

        void onGoToDetailStep(int adapterPosition);

        void onGoToDetailIngredients();
    }
}
