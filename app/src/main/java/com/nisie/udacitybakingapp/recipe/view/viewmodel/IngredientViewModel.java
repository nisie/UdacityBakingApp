package com.nisie.udacitybakingapp.recipe.view.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by nisie on 9/2/17.
 */

public class IngredientViewModel implements Parcelable{

    private String quantity;
    private String measure;
    private String ingredient;

    public IngredientViewModel(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    protected IngredientViewModel(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<IngredientViewModel> CREATOR = new Creator<IngredientViewModel>() {
        @Override
        public IngredientViewModel createFromParcel(Parcel in) {
            return new IngredientViewModel(in);
        }

        @Override
        public IngredientViewModel[] newArray(int size) {
            return new IngredientViewModel[size];
        }
    };

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
