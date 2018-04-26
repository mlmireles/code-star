package com.martin.codestar.main;

import com.martin.codestar.API.models.RepositoriesResponse;
import com.martin.codestar.API.models.Repository;
import com.martin.codestar.API.models.User;

import java.util.List;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainPresenter implements IMainPresenter, IMainModelCallback.Users,
        IMainModelCallback.Repos {

    private User mUserOne, mUserTwo;
    private RepositoriesResponse mReposOne, mReposTwo;

    private IMainView mView;
    private IMainModel.Users mUserModel;
    private IMainModel.Repos mReposModel;

    MainPresenter(IMainView mView, IMainModel.Users mUserModel, IMainModel.Repos mReposModel) {
        this.mView = mView;
        this.mUserModel = mUserModel;
        this.mReposModel = mReposModel;
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
        this.mUserModel.getUser(userOne, this);
        //this.mUserModel.getUser(userTwo, this);
    }

    @Override
    public void onGetUserSuccess(User user) {
        if (user.getLogin().equals(this.mView.getUserOne())) {
            this.mUserOne = user;
        } else {
            this.mUserTwo = user;
            this.mView.onUsersSuccess();
            this.mView.showUsersInfo(this.mUserOne, this.mUserTwo);
            this.getRepositories();
        }
    }

    @Override
    public void onGetUserError(String username) {
        this.mView.hideProgressBar();
        this.mView.showUserNotFoundError(username);
    }

    @Override
    public void onGetReposSuccess(RepositoriesResponse repos) {
        if (this.mReposOne == null) {
            this.mReposOne = repos;
        } else {
            this.mReposTwo = repos;
            this.starCount();
        }
    }

    @Override
    public void onGetReposError(String username) {
        this.mView.hideProgressBar();
        this.mView.showUserHasNoReposError(username);
    }

    @Override
    public void onGetServerError() {
        this.mView.hideProgressBar();
        this.mView.showServerError();
    }

    @Override
    public void getRepositories() {
        this.mReposModel.getUserRepos(this.mUserOne.getLogin(), this);
        this.mReposModel.getUserRepos(this.mUserTwo.getLogin(), this);
    }

    @Override
    public void starCount() {
        if (this.mReposOne.getRepositories().size() == 0) {
            this.mView.showUserHasNoReposError(this.mUserOne.getLogin());
            return;
        }

        if (this.mReposTwo.getRepositories().size() == 0) {
            this.mView.showUserHasNoReposError(this.mUserTwo.getLogin());
            return;
        }

        this.compareStars();
    }

    @Override
    public void compareStars() {
        int starsOne = this.getStars(this.mReposOne.getRepositories());
        int starsTwo = this.getStars(this.mReposTwo.getRepositories());

        if (starsOne == starsTwo) {
            this.mView.showTie();
            return;
        }

        if (starsOne > starsTwo) {
            this.mView.setWinner(this.mUserOne);
        } else if (starsTwo > starsOne) {
            this.mView.setWinner(this.mUserTwo);
        }

        this.mView.showWinner();
    }

    private int getStars(List<Repository> repositories) {
        int stars = 0;
        for (Repository repo : repositories) {
            stars += repo.getStargazers_count();
        }
        return stars;
    }
}
