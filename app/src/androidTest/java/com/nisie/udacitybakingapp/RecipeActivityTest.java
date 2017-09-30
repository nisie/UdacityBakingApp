package com.nisie.udacitybakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nisie.udacitybakingapp.recipe.view.activity.RecipeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * @author by nisie on 9/4/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {
    @Rule public ActivityTestRule<RecipeActivity> myActivityTestRule =
            new ActivityTestRule<>(RecipeActivity.class);

    @Test
    public void recyclerViewExist() {
        onView(withId(R.id.recipe_list)).check(matches(isDisplayed()));
    }
}
