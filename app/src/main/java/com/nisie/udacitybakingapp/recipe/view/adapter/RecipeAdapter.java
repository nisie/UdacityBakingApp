package com.nisie.udacitybakingapp.recipe.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.listener.Recipe;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by nisie on 9/1/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final Recipe.View viewListener;
    ArrayList<RecipeViewModel> list;

    public void setList(List<RecipeViewModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;

        ViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewListener.onGoToDetailRecipe(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public RecipeAdapter(Recipe.View viewListener) {
        this.viewListener = viewListener;
        this.list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recipeName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static RecipeAdapter createAdapter(Recipe.View viewListener) {
        return new RecipeAdapter(viewListener);
    }


}
