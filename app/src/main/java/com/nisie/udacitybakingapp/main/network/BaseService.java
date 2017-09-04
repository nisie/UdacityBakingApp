package com.nisie.udacitybakingapp.main.network;

/*
 * @author by nisie on 6/26/2017.
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseService<T> {

    protected T api;

    protected abstract void initApiService(Retrofit retrofit);

    protected abstract String getBaseUrl();

    public abstract T getApi();

    public BaseService() {
        initApiService(createRetrofitInstance(getBaseUrl()));
    }

    public BaseService(String overrideUrl) {
        initApiService(createRetrofitInstance(overrideUrl));
    }

    protected Retrofit createRetrofitInstance(String processedBaseUrl) {
        return new Retrofit.Builder()
                .baseUrl(processedBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
