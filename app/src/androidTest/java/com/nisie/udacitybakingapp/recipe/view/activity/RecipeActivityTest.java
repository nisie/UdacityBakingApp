package com.nisie.udacitybakingapp.recipe.view.activity;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.nisie.udacitybakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.nisie.udacitybakingapp.util.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>
            (RecipeActivity.class);

    @Test
    public void loadData() {
        Espresso.registerIdlingResources(mActivityTestRule.getActivity().getIdlingResource());

        onView(withRecyclerView(R.id.recipe_list).atPositionOnView(0, R.id.recipe_name)).check
                (matches
                        (withText("Nutella Pie")));

        onView(withRecyclerView(R.id.recipe_list).atPositionOnView(1, R.id.recipe_name)).check
                (matches
                        (withText("Brownies")));

        onView(withRecyclerView(R.id.recipe_list).atPositionOnView(2, R.id.recipe_name)).check
                (matches
                        (withText("Yellow Cake")));
    }

    @Test
    public void goToDetail() {

        Espresso.registerIdlingResources(mActivityTestRule.getActivity().getIdlingResource());

        ViewInteraction appCompatTextView = onView(withRecyclerView(R.id.recipe_list).atPositionOnView(0, R.id.recipe_name)).check
                (matches
                        (withText("Nutella Pie")));
        appCompatTextView.perform(click());

        onView(
                allOf(
                        isAssignableFrom(TextView.class),
                        withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Nutella Pie")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
