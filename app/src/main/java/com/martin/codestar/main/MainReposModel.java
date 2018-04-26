package com.martin.codestar.main;

import android.util.Log;

import com.martin.codestar.API.ApiAdapter;
import com.martin.codestar.API.models.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainReposModel implements IMainModel.Repos, Callback<List<Repository>> {

    private static final String TAG = "MainReposModel";

    private IMainModelCallback.Repos mCallback;
    private static String sUsername;

    @Override
    public void getUserRepos(String username, IMainModelCallback.Repos callback) {
        this.mCallback = callback;
        sUsername = username;

        Call<List<Repository>> call = ApiAdapter.getApiService().getUserRepos(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
        if (response.isSuccessful()) {
            Log.d(TAG, "onResponse: success");
            this.mCallback.onGetReposSuccess(response.body());
        } else {
            Log.d(TAG, "onResponse: not success");
            this.mCallback.onGetReposError(sUsername);
        }
    }

    @Override
    public void onFailure(Call<List<Repository>> call, Throwable t) {
        Log.d(TAG, "onFailure: not success. " + t.getMessage());
        this.mCallback.onGetServerError();
    }
}
