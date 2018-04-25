package com.martin.codestar.main;

/**
 * Created by Martín on 23/04/2018
 *
 */

public interface IMainModel {

    interface Users {
        void getUser(String username, IMainModelCallback.Users callback);
    }

    interface Repos {
        void getUserRepos(String username, IMainModelCallback.Repos callback);
    }
}
