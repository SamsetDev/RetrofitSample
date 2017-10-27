package com.samset.retrofitexample;

import android.app.Application;

import com.samset.retrofitexample.network.ApiFactory;
import com.samset.retrofitexample.network.ApiService;

/**
 * Created by Samset on 10/27/17.
 */

public class AppApplication extends Application {
    private ApiService apiService;
    private static AppApplication appApplication;


    public static AppApplication getInstance() {
        return appApplication == null ? new AppApplication() : appApplication;
    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = ApiFactory.create();
        }
        return apiService;
    }
}
