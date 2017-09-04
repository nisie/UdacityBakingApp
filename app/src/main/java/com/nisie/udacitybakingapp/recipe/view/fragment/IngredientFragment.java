package com.nisie.udacitybakingapp.recipe.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.adapter.IngredientsAdapter;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeIngredientViewModel;

/**
 * @author by nisie on 9/2/17.
 */

public class IngredientFragment extends Fragment {
    private static final String ARGS_DATA = "ARGS_DATA";
    TextView title;
    RecipeIngredientViewModel data;
    RecyclerView ingredients;
    IngredientsAdapter adapter;

    public static Fragment createInstance(RecipeIngredientViewModel recipeIngredientViewModel) {
        Fragment fragment = new IngredientFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_DATA, recipeIngredientViewModel);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);
        title = v.findViewById(R.id.title);
        ingredients = v.findViewById(R.id.ingredients_list);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredients.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager
                .VERTICAL, false));
        adapter = IngredientsAdapter.createInstance(data.getList());
        ingredients.setAdapter(adapter);
        title.setText(getString(R.string.ingredients));

    }
}
