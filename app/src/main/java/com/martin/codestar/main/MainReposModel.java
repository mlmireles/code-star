package com.martin.codestar.main;

import com.martin.codestar.API.ApiAdapter;
import com.martin.codestar.API.models.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReposModel implements IMainModel.Repos, Callback<RepositoriesResponse> {

    private IMainModelCallback.Repos mCallback;
    private static String sUsername;

    @Override
    public void getUserRepos(String username, IMainModelCallback.Repos callback) {
        this.mCallback = callback;
        sUsername = username;

        Call<RepositoriesResponse> call = ApiAdapter.getApiService().getUserRepos(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RepositoriesResponse> call, Response<RepositoriesResponse> response) {
        if (response.isSuccessful()) {
            this.mCallback.onGetReposSuccess(response.body());
        } else {
            this.mCallback.onGetReposError(sUsername);
        }
    }

    @Override
    public void onFailure(Call<RepositoriesResponse> call, Throwable t) {

    }
}
