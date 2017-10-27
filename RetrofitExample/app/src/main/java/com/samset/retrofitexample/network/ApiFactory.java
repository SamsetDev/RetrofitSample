package com.samset.retrofitexample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.samset.retrofitexample.utils.Constants.BASEURL;


/**
 * Created by samset on 9/19/17.
 */

public class ApiFactory {
    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
