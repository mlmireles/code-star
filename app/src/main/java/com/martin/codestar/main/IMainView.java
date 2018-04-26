package com.martin.codestar.main;

import com.martin.codestar.API.models.User;

/**
 * Created by Martín on 23/04/2018
 *
 */

public interface IMainView {

    String getUserOne();

    void showUserOneNullError();

    String getUserTwo();

    void showUserTwoNullError();

    void showSameUserError();

    void showProgressBar();

    void showUserNotFoundError(String username);

    void hideProgressBar();

    void showServerError();

    void onUsersSuccess();

    void showUsersInfo(User userOne, User userTwo);

    void showUserHasNoReposError(String username);

    void showTie();

    void setWinner(User user);

    void showWinner();

    void launchRepoListActivity();

    void launchUserProfileactivity(User user);
}
