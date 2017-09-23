package com.nisie.udacitybakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nisie.udacitybakingapp.recipe.view.activity.RecipeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author by nisie on 9/4/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeAtivityTest {
    @Rule public ActivityTestRule<RecipeActivity> myActivityTestRule =
            new ActivityTestRule<>(RecipeActivity.class);

}
