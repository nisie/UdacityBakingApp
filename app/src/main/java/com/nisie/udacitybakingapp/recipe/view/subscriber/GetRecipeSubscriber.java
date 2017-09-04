package com.nisie.udacitybakingapp.recipe.view.subscriber;

import com.nisie.udacitybakingapp.recipe.domain.model.GetRecipeDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.IngredientDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.RecipeResultDomain;
import com.nisie.udacitybakingapp.recipe.domain.model.StepDomain;
import com.nisie.udacitybakingapp.recipe.view.listener.Recipe;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.IngredientViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author by nisie on 9/1/17.
 */

public class GetRecipeSubscriber extends Subscriber<GetRecipeDomain> {
    private final Recipe.View viewListener;

    public GetRecipeSubscriber(Recipe.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        viewListener.finishLoading();
        viewListener.onErrorGetRecipe();
    }

    @Override
    public void onNext(GetRecipeDomain getRecipeDomain) {
        viewListener.finishLoading();
        viewListener.onSuccessGetRecipe(convertToViewModel(getRecipeDomain));

    }

    private List<RecipeViewModel> convertToViewModel(GetRecipeDomain getRecipeDomain) {
        List<RecipeViewModel> list = new ArrayList<>();
        for (RecipeResultDomain domain : getRecipeDomain.getListRecipe()) {
            list.add(new RecipeViewModel(domain.getId(),
                    domain.getName(),
                    convertToListIngredients(domain.getIngredients()),
                    convertToListSteps(domain.getSteps()),
                    domain.getServings(),
                    domain.getImage()));
        }

        return list;
    }

    private List<StepViewModel> convertToListSteps(List<StepDomain> steps) {
        List<StepViewModel> list = new ArrayList<>();
        for (StepDomain domain : steps) {
            list.add(new StepViewModel(
                    domain.getId(),
                    domain.getShortDescription(),
                    domain.getDescription(),
                    domain.getVideoURL(),
                    domain.getThumbnailURL()
            ));
        }
        return list;
    }

    private List<IngredientViewModel> convertToListIngredients(List<IngredientDomain> ingredients) {
        List<IngredientViewModel> list = new ArrayList<>();
        for (IngredientDomain domain : ingredients) {
            list.add(new IngredientViewModel(
                    domain.getQuantity(),
                    domain.getMeasure(),
                    domain.getIngredient()
            ));
        }
        return list;
    }
}
