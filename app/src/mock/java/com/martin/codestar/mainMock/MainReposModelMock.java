package com.martin.codestar.mainMock;

import android.os.Handler;

import com.martin.codestar.API.models.Repository;
import com.martin.codestar.main.IMainModel;
import com.martin.codestar.main.IMainModelCallback;

import java.util.ArrayList;
import java.util.List;


public class MainReposModelMock implements IMainModel.Repos {

    private Handler mHandler;

    @Override
    public void getUserRepos(String username, IMainModelCallback.Repos listener) {
        final IMainModelCallback.Repos callback = listener;

        this.mHandler = new Handler();
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Repository> repos = new ArrayList<>();

                Repository a = new Repository();
                
            }
        }, 2000);
    }
}
