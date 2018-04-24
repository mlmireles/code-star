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

    @Mock
    private IMainView mView;
    @Mock
    private IMainModel mModel;

    private IMainPresenter mPresenter;

    public MainActivityTest() {
        MockitoAnnotations.initMocks(this);

        this.mPresenter = new MainPresenter(mView, mModel);
    }

    @Test
    public void shouldShowErrorWhenUserOneNull() {
        Mockito.when(this.mView.getUserOne()).thenReturn("");

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).showUserOneNullError();
    }

    @Test
    public void shouldShowErrorWhenUserTwoNull() {
        Mockito.when(this.mView.getUserOne()).thenReturn("mlmireles");
        Mockito.when(this.mView.getUserTwo()).thenReturn("");

    }
}