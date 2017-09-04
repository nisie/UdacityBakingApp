package com.nisie.udacitybakingapp.recipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.fragment.IngredientFragment;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeIngredientViewModel;

/**
 * @author by nisie on 9/3/17.
 */

public class IngredientActivity extends AppCompatActivity {

    private static final String ARGS_DATA = IngredientActivity.class.getSimpleName() + "ARGS_DATA";
    RecipeIngredientViewModel data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
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
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(IngredientFragment
                .class.getSimpleName());
        if (fragment == null)
            fragment = IngredientFragment.createInstance(data);

        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public static Intent getCallingIntent(Context context, RecipeIngredientViewModel ingredients) {
        Intent intent = new Intent(context, IngredientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_DATA, ingredients);
        intent.putExtras(bundle);
        return intent;
    }
}
