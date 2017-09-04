package com.nisie.udacitybakingapp.recipe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.main.domain.executor.JobExecutor;
import com.nisie.udacitybakingapp.main.presentation.UIThread;
import com.nisie.udacitybakingapp.recipe.domain.interactor.GetRecipeUseCase;
import com.nisie.udacitybakingapp.recipe.domain.mapper.RecipeMapper;
import com.nisie.udacitybakingapp.recipe.domain.network.BakingService;
import com.nisie.udacitybakingapp.recipe.domain.repository.BakingRepository;
import com.nisie.udacitybakingapp.recipe.domain.repository.BakingRepositoryImpl;
import com.nisie.udacitybakingapp.recipe.view.listener.Recipe;
import com.nisie.udacitybakingapp.recipe.view.adapter.RecipeAdapter;
import com.nisie.udacitybakingapp.recipe.view.presenter.RecipePresenterImpl;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.RecipeViewModel;

import java.util.List;

public class RecipeActivity extends AppCompatActivity
        implements Recipe.View {

    RecyclerView recipeList;
    RecipeAdapter adapter;
    Recipe.Presenter presenter;
    boolean tabletSize;

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
                bakingService, recipeMapper
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
        presenter.getRecipe();
    }

    @Override
    public void onErrorGetRecipe() {

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
}
