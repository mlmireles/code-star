package com.martin.codestar.API;

import com.martin.codestar.API.models.Repository;
import com.martin.codestar.API.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    /**
     * USERS endpoint to get user information
     */
    abstract class USERS {
        private static final String PATH = "/users/{username}";
        private static final String PATH_REPOS = "/users/{username}/repos";

        static final String PARAM_USERNAME = "username";
    }
    @GET(USERS.PATH)
    Call<User> getUser(@Path(USERS.PARAM_USERNAME) String username);

    @GET(USERS.PATH_REPOS)
    Call<List<Repository>> getUserRepos(@Path(USERS.PARAM_USERNAME) String username);
}
