package com.martin.codestar.main;

import android.util.Log;

import com.martin.codestar.API.ApiAdapter;
import com.martin.codestar.API.models.User;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainModel implements IMainModel , Callback<User> {

    private static final String TAG = "MainModel";

    private IMainModelCallback mCallback;

    @Override
    public void getUser(String username, IMainModelCallback listener) {
        this.mCallback = listener;

        Call<User> call;
        call = ApiAdapter.getApiService().getUser(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response != null) {
            Log.d(TAG, "onResponse: ");
            if (response.isSuccessful()) {
                Log.d(TAG, "onResponse: success");
                this.mCallback.onGetUserSuccess(response.body());
            } else {
                Log.d(TAG, "onResponse: not success");
                this.mCallback.onGetServerError();
            }
        } else {
            this.mCallback.onGetServerError();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.d(TAG, "onFailure ");
        this.mCallback.onGetServerError();
    }
}
