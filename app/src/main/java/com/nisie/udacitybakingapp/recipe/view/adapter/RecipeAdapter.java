package com.nisie.udacitybakingapp.recipe.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.main.presentation.util.ImageHandler;
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

    public ArrayList<RecipeViewModel> getList() {
        return list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipeImage;

        ViewHolder(View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_image);
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
        if (!TextUtils.isEmpty(list.get(position).getImage())) {
            ImageHandler.loadImageFromUrl(holder.recipeImage, list.get(position).getImage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static RecipeAdapter createAdapter(Recipe.View viewListener) {
        return new RecipeAdapter(viewListener);
    }


}
