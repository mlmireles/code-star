package com.martin.codestar.main;

import com.martin.codestar.API.models.User;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainPresenter implements IMainPresenter, IMainModelCallback.Users {

    private User mUserOne, mUserTwo;

    private IMainView mView;
    private IMainModel.Users mModel;

    MainPresenter(IMainView mView, IMainModel.Users mModel) {

        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void onClickStart() {
        String userOne = this.mView.getUserOne();
        if (userOne.isEmpty()) {
            this.mView.showUserOneNullError();
            return;
        }

        String userTwo = this.mView.getUserTwo();
        if (userTwo.isEmpty()) {
            this.mView.showUserTwoNullError();
            return;
        }

        if (userOne.equals(userTwo)) {
            this.mView.showSameUserError();
            return;
        }

        this.mView.showProgressBar();
        this.mModel.getUser(userOne, this);
        this.mModel.getUser(userTwo, this);
    }

    @Override
    public void onGetUserSuccess(User user) {
        if (user.getLogin().equals(this.mView.getUserOne())) {
            this.mUserOne = user;
        } else {
            this.mUserTwo = user;
            this.mView.onUsersSuccess();
            this.mView.showUsersInfo(this.mUserOne, this.mUserTwo);
            this.starCount();
        }
    }

    @Override
    public void onGetUserError(String username) {
        this.mView.hideProgressBar();
        this.mView.showUserNotFoundError(username);
    }

    @Override
    public void onGetServerError() {
        this.mView.hideProgressBar();
        this.mView.showServerError();
    }

    @Override
    public void starCount() {

    }
}
