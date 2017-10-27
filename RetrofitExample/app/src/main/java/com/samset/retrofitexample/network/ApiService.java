package com.samset.retrofitexample.network;

import com.samset.retrofitexample.model.Repository;
import com.samset.retrofitexample.model.User;
import com.samset.retrofitexample.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by samset on 12/09/17.
 */

public interface ApiService {

    @GET(Constants.DATALIST)
    Call<List<Repository>> getRepositories(@Path("username") String username);

    @GET
    Call<User> userFromUrl(@Url String userUrl);


}
