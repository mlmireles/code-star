package com.martin.codestar.main;

import org.mockito.MockitoAnnotations;


/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainActivityTest {

    IMainPresenter mPresenter;

    public MainActivityTest() {
        MockitoAnnotations.initMocks(this);

        this.mPresenter = new MainPresenter(mView, mModel);
    }

}