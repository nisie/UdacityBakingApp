package com.nisie.udacitybakingapp.recipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.fragment.DetailRecipeFragment;
import com.nisie.udacitybakingapp.recipe.view.fragment.IngredientFragment;
import com.nisie.udacitybakingapp.recipe.view.fragment.StepFragment;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeIngredientViewModel;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    private static final String ARGS_DATA = RecipeDetailActivity.class.getSimpleName() + "ARGS_DATA";
    RecipeViewModel data;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        initView(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);
        this.menu = menu;
        checkIsFavorite(data.getId());
        return true;
    }


    private void checkIsFavorite(int id) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.widget_shown_recipe), Context.MODE_PRIVATE);
        MenuItem favorite = menu.findItem(R.id.favorite);
        if (sharedPreferences.getInt(getString(R.string.widget_shown_recipe), 0) == id) {
            setMenuFavorite(favorite);
        } else {
            setUnFavorite(favorite);
        }

    }

    private void setUnFavorite(MenuItem item) {
        item.setIcon(getResources().getDrawable(R.drawable.ic_star_border_white_24dp));

    }

    private void setMenuFavorite(MenuItem item) {
        item.setIcon(getResources().getDrawable(R.drawable.ic_star_white_24dp));
    }

    private void initView(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null
                && getIntent().getExtras().getParcelable(ARGS_DATA) != null)
            data = getIntent().getExtras().getParcelable(ARGS_DATA);
        else if (savedInstanceState != null
                && savedInstanceState.getParcelable(ARGS_DATA) != null) {
            data = savedInstanceState.getParcelable(ARGS_DATA);
        } else {
            finish();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(DetailRecipeFragment
                .class.getSimpleName());
        if (fragment == null)
            fragment = DetailRecipeFragment.createInstance(data);

        fragmentTransaction.replace(R.id.list_frag, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.favorite:
                onFavoriteClicked(item);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void onFavoriteClicked(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.widget_shown_recipe), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.widget_shown_recipe), data.getId());
        editor.apply();
        setMenuFavorite(item);
    }

    public static Intent getCallingIntent(Context context, RecipeViewModel recipeViewModel) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_DATA, recipeViewModel);
        intent.putExtras(bundle);
        return intent;
    }

    public void openIngredient() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(IngredientFragment
                .class.getSimpleName());
        if (fragment == null)
            fragment = IngredientFragment.createInstance(
                    new RecipeIngredientViewModel(data.getIngredients()));

        fragmentTransaction.replace(R.id.details_frag, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void openStep(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = StepFragment.createInstance(data.getSteps().get(position));

        fragmentTransaction.replace(R.id.details_frag, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }
}
