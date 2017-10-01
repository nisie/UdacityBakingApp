package com.nisie.udacitybakingapp.recipe.view.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.main.domain.executor.JobExecutor;
import com.nisie.udacitybakingapp.main.presentation.MyApplication;
import com.nisie.udacitybakingapp.main.presentation.UIThread;
import com.nisie.udacitybakingapp.main.presentation.util.ConnectivityReceiver;
import com.nisie.udacitybakingapp.recipe.domain.interactor.GetRecipeUseCase;
import com.nisie.udacitybakingapp.recipe.domain.mapper.RecipeMapper;
import com.nisie.udacitybakingapp.recipe.domain.network.BakingService;
import com.nisie.udacitybakingapp.recipe.domain.repository.BakingRepository;
import com.nisie.udacitybakingapp.recipe.domain.repository.BakingRepositoryImpl;
import com.nisie.udacitybakingapp.recipe.view.adapter.RecipeAdapter;
import com.nisie.udacitybakingapp.recipe.view.listener.Recipe;
import com.nisie.udacitybakingapp.recipe.view.presenter.RecipePresenterImpl;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;

import java.util.List;

public class RecipeActivity extends AppCompatActivity
        implements Recipe.View, ConnectivityReceiver.ConnectivityReceiverListener {

    private static final String KEY_RECYCLER_STATE = "KEY_RECYCLER_STATE";
    private static final String KEY_RECYCLER_ITEMS = "KEY_RECYCLER_ITEMS";
    RecyclerView recipeList;
    RecipeAdapter adapter;
    Recipe.Presenter presenter;
    boolean tabletSize;
    private static Bundle mBundleRecyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        tabletSize = getResources().getBoolean(R.bool.isTablet);
        initVar();
        initView();
        initPresenter();
        prepareView();
        initData();
    }

    private void initVar() {
        adapter = RecipeAdapter.createAdapter(this);
    }

    private void initView() {
        recipeList = (RecyclerView) findViewById(R.id.recipe_list);
    }

    private void initPresenter() {

        BakingService bakingService = new BakingService();
        RecipeMapper recipeMapper = new RecipeMapper();

        BakingRepository bakingRepository = new BakingRepositoryImpl(
                this, bakingService, recipeMapper
        );

        GetRecipeUseCase getRecipeUseCase = new GetRecipeUseCase(
                new JobExecutor(), new UIThread(), bakingRepository);
        presenter = new RecipePresenterImpl(this, getRecipeUseCase);
    }

    private void prepareView() {
        if (tabletSize) {
            recipeList.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL,
                    false));
        } else {
            recipeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                    false));
        }

        recipeList.setAdapter(adapter);
    }

    private void initData() {
        if (mBundleRecyclerViewState == null
                && adapter.getItemCount() == 0)
            presenter.getRecipe();
    }

    @Override
    public void onErrorGetRecipe() {
        Toast.makeText(this, "Error receiving data. Please try again later", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessGetRecipe(List<RecipeViewModel> recipeViewModelList) {
        adapter.setList(recipeViewModelList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void finishLoading() {

    }

    @Override
    public void onGoToDetailRecipe(RecipeViewModel recipeViewModel) {
        startActivity(RecipeDetailActivity.getCallingIntent(this, recipeViewModel));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recipeList.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        outState.putParcelable(KEY_RECYCLER_STATE, mBundleRecyclerViewState);
        outState.putParcelableArrayList(KEY_RECYCLER_ITEMS, adapter.getList());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mBundleRecyclerViewState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
            if (adapter != null) {
                adapter.setList(savedInstanceState.<RecipeViewModel>getParcelableArrayList(KEY_RECYCLER_ITEMS));
            }

            if (mBundleRecyclerViewState != null) {
                Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
                recipeList.getLayoutManager().onRestoreInstanceState(listState);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected)
            Toast.makeText(this, "No network connection. Please close the app and try again later",
                    Toast.LENGTH_LONG)
                    .show();
    }

}
