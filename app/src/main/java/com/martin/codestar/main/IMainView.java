package com.martin.codestar.main;

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
}
