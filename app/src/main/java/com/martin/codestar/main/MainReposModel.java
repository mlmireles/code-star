package com.martin.codestar.main;

import com.martin.codestar.API.models.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReposModel implements IMainModel.Repos, Callback<RepositoriesResponse> {



    @Override
    public void getUserRepos(String username, IMainModelCallback.Repos callback) {

    }

    @Override
    public void onResponse(Call<RepositoriesResponse> call, Response<RepositoriesResponse> response) {

    }

    @Override
    public void onFailure(Call<RepositoriesResponse> call, Throwable t) {

    }
}
