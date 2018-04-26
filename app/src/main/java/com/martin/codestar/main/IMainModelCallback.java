package com.martin.codestar.main;

import com.martin.codestar.API.models.Repository;
import com.martin.codestar.API.models.User;

import java.util.List;

/**
 * Created by Martín on 23/04/2018
 *
 */

public interface IMainModelCallback {

    interface Users {
        void onGetUserSuccess(User user);

        void onGetUserError(String username);

        void onGetServerError();
    }

    interface Repos {
        void onGetReposSuccess(List<Repository> repos);

        void onGetReposError(String username);

        void onGetServerError();
    }
}
