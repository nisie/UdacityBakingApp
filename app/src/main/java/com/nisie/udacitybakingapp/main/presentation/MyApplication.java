package com.nisie.udacitybakingapp.main.presentation;

import android.app.Application;

import com.nisie.udacitybakingapp.main.presentation.util.ConnectivityReceiver;

/**
 * @author by nisie on 10/1/17.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
