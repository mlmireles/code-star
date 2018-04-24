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

    private static final String USER_BAD = "asdfqwer";
    private static final String USER_ONE = "mlmireles";
    //private static final String USER_TWO = "google";

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
        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn("");

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
    }

    @Test
    public void shouldShowErrorWhenSameUser() {
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showSameUserError();
    }

    @Test
    public void shouldShowErrorWhenUserNotFound() {
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserOne()).thenReturn(USER_BAD);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();
    }
}