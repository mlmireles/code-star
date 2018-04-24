package com.martin.codestar.main;

import android.support.annotation.NonNull;
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
    private static String sUsername;

    @Override
    public void getUser(String username, IMainModelCallback listener) {
        this.mCallback = listener;
        sUsername = username;

        Call<User> call;
        call = ApiAdapter.getApiService().getUser(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
        Log.d(TAG, "onResponse: ");
        if (response.isSuccessful()) {
            Log.d(TAG, "onResponse: success");
            this.mCallback.onGetUserSuccess(response.body());
        } else {
            Log.d(TAG, "onResponse: not success");
            this.mCallback.onGetUserError(sUsername);
        }
    }

    @Override
    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
        Log.d(TAG, "onFailure ");
        this.mCallback.onGetServerError();
    }
}
