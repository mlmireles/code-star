package com.martin.codestar.main;

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

}
