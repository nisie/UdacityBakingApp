package com.nisie.udacitybakingapp.recipe.view.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeIngredientViewModel extends RecipeDetailViewModel implements Parcelable {
    List<IngredientViewModel> list;

    public RecipeIngredientViewModel(List<IngredientViewModel> list) {
        this.list = list;
    }

    protected RecipeIngredientViewModel(Parcel in) {
        super(in);
        list = in.createTypedArrayList(IngredientViewModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeIngredientViewModel> CREATOR = new Creator<RecipeIngredientViewModel>() {
        @Override
        public RecipeIngredientViewModel createFromParcel(Parcel in) {
            return new RecipeIngredientViewModel(in);
        }

        @Override
        public RecipeIngredientViewModel[] newArray(int size) {
            return new RecipeIngredientViewModel[size];
        }
    };

    public List<IngredientViewModel> getList() {
        return list;
    }
}
