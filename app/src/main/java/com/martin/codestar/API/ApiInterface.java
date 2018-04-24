package com.martin.codestar.API;

import com.martin.codestar.API.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    /**
     * USERS endpoint to get user information
     */
    abstract class USERS {
        private static final String PATH = "/users/{username}";

        public static final String PARAM_USERNAME = "username";
    }
    @GET(USERS.PATH)
    Call<User> getUser(@QueryMap HashMap<String, String> params);
}
