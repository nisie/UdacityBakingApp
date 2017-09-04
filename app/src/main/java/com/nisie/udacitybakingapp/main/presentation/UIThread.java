package com.nisie.udacitybakingapp.main.presentation;


import com.nisie.udacitybakingapp.main.domain.executor.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author by nisie on 6/27/2017.
 */

public class UIThread implements PostExecutionThread {

    public UIThread(){}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
