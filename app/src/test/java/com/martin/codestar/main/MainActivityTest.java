package com.martin.codestar.main;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainActivityTest {

    @Mock IMainView mView;
    @Mock IMainModel mModel;

    IMainPresenter mPresenter;

    public MainActivityTest() {
        MockitoAnnotations.initMocks(this);

        this.mPresenter = new MainPresenter(mView, mModel);
    }

}