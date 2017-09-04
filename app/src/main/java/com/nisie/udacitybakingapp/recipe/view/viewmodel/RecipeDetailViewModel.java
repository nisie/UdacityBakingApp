package com.nisie.udacitybakingapp.recipe.view.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeDetailViewModel implements Parcelable{

    public RecipeDetailViewModel() {
    }

    protected RecipeDetailViewModel(Parcel in) {
    }

    public static final Creator<RecipeDetailViewModel> CREATOR = new Creator<RecipeDetailViewModel>() {
        @Override
        public RecipeDetailViewModel createFromParcel(Parcel in) {
            return new RecipeDetailViewModel(in);
        }

        @Override
        public RecipeDetailViewModel[] newArray(int size) {
            return new RecipeDetailViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
