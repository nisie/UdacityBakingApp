package com.nisie.udacitybakingapp.recipe.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.listener.RecipeDetail;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeDetailViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder> {

    private static final int VIEW_INGREDIENTS = 101;
    private static final int VIEW_STEPS = 102;

    private final RecipeDetail.View viewListener;
    ArrayList<RecipeDetailViewModel> list;

    public void addItem(RecipeDetailViewModel recipeDetailViewModel) {
        list.add(recipeDetailViewModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() == 0) {
                        viewListener.onGoToDetailIngredients();
                    } else {
                        viewListener.onGoToDetailStep(getAdapterPosition() - 1);
                    }
                }
            });
        }

        public String getString(int resId) {
            return itemView.getContext().getString(resId);
        }
    }

    public RecipeDetailAdapter(RecipeDetail.View viewListener) {
        this.viewListener = viewListener;
        this.list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_INGREDIENTS) {
            holder.title.setText(holder.getString(R.string.ingredients));
        } else {
            holder.title.setText(((StepViewModel) list.get(position)).getShortDescription());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_INGREDIENTS;
        else
            return VIEW_STEPS;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static RecipeDetailAdapter createAdapter(RecipeDetail.View viewListener) {
        return new RecipeDetailAdapter(viewListener);
    }

    public void setList(List<RecipeDetailViewModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
