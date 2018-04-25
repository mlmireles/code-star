package com.martin.codestar.main;

import com.martin.codestar.API.ApiInterface;
import com.martin.codestar.API.models.User;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
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
    private static final String USER_TWO = "google";

    @Mock
    private IMainView mView;
    @Mock
    private IMainModel mModel;
    @Mock
    private User mUserOne;
    @Mock
    private User mUserTwo;

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
        ArgumentCaptor<IMainModelCallback> callbackCaptor
                = ArgumentCaptor.forClass(IMainModelCallback.class);

        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserOne()).thenReturn(USER_BAD);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mModel).getUser(
                Mockito.eq(USER_BAD),
                callbackCaptor.capture()
        );

        callbackCaptor.getValue().onGetUserError(USER_BAD);

        Mockito.verify(this.mView).hideProgressBar();
        Mockito.verify(this.mView).showUserNotFoundError(USER_BAD);
    }

    @Test
    public void shouldShowAServerError() {
        ArgumentCaptor<IMainModelCallback> callbackCaptor
                = ArgumentCaptor.forClass(IMainModelCallback.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptor.capture()
        );

        callbackCaptor.getValue().onGetServerError();

        Mockito.verify(this.mView).hideProgressBar();
        Mockito.verify(this.mView).showServerError();
    }

    @Test
    public void shouldCallOnGetUserSuccess() {
        ArgumentCaptor<IMainModelCallback> callbackCaptorOne
                = ArgumentCaptor.forClass(IMainModelCallback.class);
        ArgumentCaptor<IMainModelCallback> callbackCaptorTwo
                = ArgumentCaptor.forClass(IMainModelCallback.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptorOne.capture()
        );
        Mockito.verify(this.mModel).getUser(
                Mockito.eq(USER_TWO),
                callbackCaptorTwo.capture()
        );

        Mockito.when(this.mUserOne.getLogin()).thenReturn(USER_ONE);
        callbackCaptorOne.getValue().onGetUserSuccess(mUserOne);
        Mockito.when(this.mUserTwo.getLogin()).thenReturn(USER_TWO);
        callbackCaptorTwo.getValue().onGetUserSuccess(mUserTwo);

        Mockito.verify(this.mView).onUsersSuccess();
    }

    @Test
    public void shouldShowUsersInfo() {

    }
}