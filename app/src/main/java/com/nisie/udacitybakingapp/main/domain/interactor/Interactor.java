package com.nisie.udacitybakingapp.main.domain.interactor;

import java.util.Map;

import rx.Subscriber;

/**
 * @author by nisie on 6/26/2017.
 */

public interface Interactor<T> {

    void execute(Map<String, Object> requestParams, Subscriber<T> subscriber);


}
