package com.nisie.udacitybakingapp.recipe.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.activity.IngredientActivity;
import com.nisie.udacitybakingapp.recipe.view.activity.RecipeDetailActivity;
import com.nisie.udacitybakingapp.recipe.view.activity.RecipeStepsActivity;
import com.nisie.udacitybakingapp.recipe.view.adapter.RecipeDetailAdapter;
import com.nisie.udacitybakingapp.recipe.view.listener.RecipeDetail;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeIngredientViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

/**
 * @author by nisie on 9/4/17.
 */

public class DetailRecipeFragment extends Fragment
        implements RecipeDetail.View {


    private static final String ARGS_DATA = DetailRecipeFragment.class.getSimpleName() + "ARGS_DATA";

    RecyclerView detailList;
    RecipeDetailAdapter adapter;
    RecipeViewModel data;
    boolean tabletSize;

    public static Fragment createInstance(RecipeViewModel recipeViewModel) {
        Fragment fragment = new DetailRecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_DATA, recipeViewModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabletSize = getResources().getBoolean(R.bool.isTablet);

        if (getArguments() != null
                && getArguments().getParcelable(ARGS_DATA) != null) {
            data = getArguments().getParcelable(ARGS_DATA);
        } else if (savedInstanceState != null
                && savedInstanceState.getParcelable(ARGS_DATA) != null) {
            data = savedInstanceState.getParcelable(ARGS_DATA);
        } else {
            getActivity().finish();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_recipe, container, false);

        detailList = (RecyclerView) v.findViewById(R.id.recipe_detail_list);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = RecipeDetailAdapter.createAdapter(this);
        detailList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false));
        detailList.setAdapter(adapter);

        adapter.addItem(new RecipeIngredientViewModel(data.getIngredients()));
        for (StepViewModel step : data.getSteps()) {
            adapter.addItem(step);
        }
        adapter.notifyDataSetChanged();

        getActivity().setTitle(data.getName());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ARGS_DATA, data);
    }

    @Override
    public void onGoToDetailStep(int position) {
        if (tabletSize && getActivity() instanceof RecipeDetailActivity) {
            ((RecipeDetailActivity) getActivity()).openStep(position);
        } else {
            startActivity(RecipeStepsActivity.getCallingIntent(getActivity(), position, data.getSteps()));
        }
    }

    @Override
    public void onGoToDetailIngredients() {
        if (tabletSize && getActivity() instanceof RecipeDetailActivity) {
            ((RecipeDetailActivity) getActivity()).openIngredient();
        } else {
            startActivity(IngredientActivity.getCallingIntent(getActivity(), new RecipeIngredientViewModel(
                    data.getIngredients()
            )));
        }
    }


}
