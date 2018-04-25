package com.martin.codestar.main;

import com.martin.codestar.API.models.RepositoriesResponse;
import com.martin.codestar.API.models.User;

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
        void onGetReposSuccess(RepositoriesResponse repos);

        void onGetReposError(String username);

        void onGetServerError();
    }
}
