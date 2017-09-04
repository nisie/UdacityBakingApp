package com.nisie.udacitybakingapp.recipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.adapter.StepsPagerAdapter;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by nisie on 9/2/17.
 */

public class RecipeStepsActivity extends AppCompatActivity {

    private static final String ARGS_DATA = RecipeStepsActivity.class.getSimpleName() + "ARGS_DATA";
    private static final String ARGS_POSITION = RecipeStepsActivity.class.getSimpleName() + "ARGS_POSITION";
    ViewPager viewPager;
    StepsPagerAdapter pagerAdapter;
    Button buttonPrev;
    Button buttonNext;
    ArrayList<StepViewModel> data;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        initVar();
        initView();
        prepareView();
        initData(savedInstanceState);
    }

    private void initVar() {

    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.steps);
        buttonPrev = (Button) findViewById(R.id.prev_button);
        buttonNext = (Button) findViewById(R.id.next_button);
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

    private void prepareView() {
        pagerAdapter = new StepsPagerAdapter(
                getSupportFragmentManager(),
                new ArrayList<StepViewModel>());
        viewPager.setAdapter(pagerAdapter);

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position -= 1;
                viewPager.setCurrentItem(position);
                setFooterButton();

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position += 1;
                viewPager.setCurrentItem(position);
                setFooterButton();
            }
        });
    }

    private void initData(Bundle savedInstanceState) {
        if (getIntent().getExtras() != null
                && getIntent().getExtras().getParcelableArrayList(ARGS_DATA) != null) {
            data = getIntent().getExtras().getParcelableArrayList(ARGS_DATA);
        } else if (savedInstanceState != null
                && savedInstanceState.getParcelableArrayList(ARGS_DATA) != null) {
            data = savedInstanceState.getParcelableArrayList(ARGS_DATA);
        } else {
            finish();
        }

        if (getIntent().getExtras() != null
                && getIntent().getExtras().getInt(ARGS_POSITION, -1) != -1) {
            position = getIntent().getExtras().getInt(ARGS_POSITION);
        } else if (savedInstanceState != null
                && savedInstanceState.getInt(ARGS_POSITION, -1) != -1) {
            position = savedInstanceState.getInt(ARGS_POSITION);
        } else {
            finish();
        }

        pagerAdapter.setList(data);
        viewPager.setCurrentItem(position);

        setFooterButton();
    }

    private void setFooterButton() {
        if (position == 0) {
            buttonPrev.setVisibility(View.INVISIBLE);
        } else {
            buttonPrev.setVisibility(View.VISIBLE);
        }

        if (position == data.size() - 1) {
            buttonNext.setVisibility(View.INVISIBLE);
        } else {
            buttonNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARGS_DATA, data);
    }

    public static Intent getCallingIntent(Context context,
                                          int position, List<StepViewModel> list) {
        Intent intent = new Intent(context, RecipeStepsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARGS_DATA, new ArrayList<Parcelable>(list));
        bundle.putInt(ARGS_POSITION, position);
        intent.putExtras(bundle);
        return intent;
    }
}
