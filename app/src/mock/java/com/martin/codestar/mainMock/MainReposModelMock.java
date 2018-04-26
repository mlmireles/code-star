package com.martin.codestar.mainMock;

import android.os.Handler;

import com.martin.codestar.API.models.Repository;
import com.martin.codestar.main.IMainModel;
import com.martin.codestar.main.IMainModelCallback;

import java.util.ArrayList;
import java.util.List;


public class MainReposModelMock implements IMainModel.Repos {

    @Override
    public void getUserRepos(String username, IMainModelCallback.Repos listener) {
        final IMainModelCallback.Repos callback = listener;

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Repository> repos = new ArrayList<>();

                Repository a = new Repository();
                a.setName("repo");
                a.setFull_name("a/repo");
                a.setLanguage("Java");
                a.setStargazers_count(10);
                repos.add(a);
                Repository b = new Repository();
                b.setName("name");
                b.setFull_name("b/name");
                b.setLanguage("Java");
                b.setStargazers_count(20);
                repos.add(b);
                Repository c = new Repository();
                c.setName("other-name");
                c.setFull_name("c/other-name");
                c.setLanguage("Go");
                c.setStargazers_count((int) Math.random());
                repos.add(c);

                RepositoriesResponse response = new RepositoriesResponse();
                response.setRepositories(repos);

                callback.onGetReposSuccess(response);
            }
        }, 2000);
    }
}
