package com.martin.codestar.main;

import com.martin.codestar.API.models.User;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainPresenter implements IMainPresenter, IMainModelCallback {

    private IMainView mView;
    private IMainModel mModel;

    MainPresenter(IMainView mView, IMainModel mModel) {

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
    }

    @Override
    public void onGetUserSuccess(User user) {

    }

    @Override
    public void onGetUserError(String username) {
        this.mView.showUserNotFoundError(username);
        this.mView.hideProgressBar();
    }

    @Override
    public void onGetServerError() {

    }
}
