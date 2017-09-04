package com.nisie.udacitybakingapp.recipe.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nisie.udacitybakingapp.recipe.view.fragment.StepFragment;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by nisie on 9/2/17.
 */

public class StepsPagerAdapter extends FragmentPagerAdapter {

    List<StepViewModel> list;

    public StepsPagerAdapter(FragmentManager fm, List<StepViewModel> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.createInstance((StepViewModel) list.get(position));
    }

    public void setList(ArrayList<StepViewModel> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }
}
