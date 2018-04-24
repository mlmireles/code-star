package com.martin.codestar.main;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Test
    public void shouldShowErrorWhenUserOneNull() {
        Mockito.when(this.mView.getUserOne()).thenReturn("");
    }
}