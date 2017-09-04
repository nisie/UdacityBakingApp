package com.nisie.udacitybakingapp.main.domain.executor;

import rx.Scheduler;

/**
 * @author by nisie on 6/27/2017.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();
}
