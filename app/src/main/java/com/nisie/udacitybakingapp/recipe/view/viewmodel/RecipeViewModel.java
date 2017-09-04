package com.nisie.udacitybakingapp.recipe.view.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author by nisie on 9/1/17.
 */

public class RecipeViewModel implements Parcelable{
    private int id;
    private String name;
    private List<IngredientViewModel> ingredients = null;
    private List<StepViewModel> steps = null;
    private int servings;
    private String image;

    public RecipeViewModel(int id, String name, List<IngredientViewModel> ingredients,
                           List<StepViewModel> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    protected RecipeViewModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(IngredientViewModel.CREATOR);
        steps = in.createTypedArrayList(StepViewModel.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<RecipeViewModel> CREATOR = new Creator<RecipeViewModel>() {
        @Override
        public RecipeViewModel createFromParcel(Parcel in) {
            return new RecipeViewModel(in);
        }

        @Override
        public RecipeViewModel[] newArray(int size) {
            return new RecipeViewModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientViewModel> getIngredients() {
        return ingredients;
    }

    public List<StepViewModel> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
        parcel.writeInt(servings);
        parcel.writeString(image);
    }
}
