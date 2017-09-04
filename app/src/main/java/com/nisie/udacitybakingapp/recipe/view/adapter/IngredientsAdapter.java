package com.nisie.udacitybakingapp.recipe.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.IngredientViewModel;

import java.util.List;

/**
 * @author by nisie on 9/3/17.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    List<IngredientViewModel> list;

    public IngredientsAdapter(List<IngredientViewModel> list) {
        this.list = list;
    }

    public static IngredientsAdapter createInstance(List<IngredientViewModel> list) {
        return new IngredientsAdapter(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredient = itemView.findViewById(R.id.ingredient);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredients, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ingredient.setText(
                getIngredientFormattedText(list.get(position))
        );
    }

    private String getIngredientFormattedText(IngredientViewModel ingredientViewModel) {
        return "- " + ingredientViewModel.getQuantity() + " " + ingredientViewModel.getMeasure()
                + " of " + ingredientViewModel.getIngredient();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
