package com.martin.codestar.main;

import com.martin.codestar.API.models.RepositoriesResponse;
import com.martin.codestar.API.models.Repository;
import com.martin.codestar.API.models.User;
import com.martin.codestar.injector.Injector;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


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
    private IMainModel.Users mUserModel;
    @Mock
    private IMainModel.Repos mReposModel;
    @Mock
    private User mUserOne;
    @Mock
    private User mUserTwo;
    @Mock
    private RepositoriesResponse mReposOne;
    @Mock
    private RepositoriesResponse mReposTwo;

    private IMainPresenter mPresenter;

    public MainActivityTest() {
        MockitoAnnotations.initMocks(this);

        this.mPresenter = new MainPresenter(mView, mUserModel, mReposModel);
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
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptor
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);

        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserOne()).thenReturn(USER_BAD);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_BAD),
                callbackCaptor.capture()
        );

        callbackCaptor.getValue().onGetUserError(USER_BAD);

        Mockito.verify(this.mView).hideProgressBar();
        Mockito.verify(this.mView).showUserNotFoundError(USER_BAD);
    }

    @Test
    public void shouldShowAServerError() {
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptor
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptor.capture()
        );

        callbackCaptor.getValue().onGetServerError();

        Mockito.verify(this.mView).hideProgressBar();
        Mockito.verify(this.mView).showServerError();
    }

    @Test
    public void shouldCallOnGetUserSuccess() {
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorOne
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorTwo
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptorOne.capture()
        );
        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_TWO),
                callbackCaptorTwo.capture()
        );

        Mockito.when(this.mUserOne.getLogin()).thenReturn(USER_ONE);
        callbackCaptorOne.getValue().onGetUserSuccess(mUserOne);
        Mockito.when(this.mUserTwo.getLogin()).thenReturn(USER_TWO);
        callbackCaptorTwo.getValue().onGetUserSuccess(mUserTwo);

        Mockito.verify(this.mView).onUsersSuccess();
        Mockito.verify(this.mView).showUsersInfo(this.mUserOne, this.mUserTwo);
    }

    @Test
    public void shouldShowErrorWhenUserHasNoPublicRepos() {
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorOne
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorTwo
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptorOne.capture()
        );
        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_TWO),
                callbackCaptorTwo.capture()
        );

        Mockito.when(this.mUserOne.getLogin()).thenReturn(USER_ONE);
        callbackCaptorOne.getValue().onGetUserSuccess(mUserOne);
        Mockito.when(this.mUserTwo.getLogin()).thenReturn(USER_TWO);
        callbackCaptorTwo.getValue().onGetUserSuccess(mUserTwo);

        Mockito.verify(this.mView).onUsersSuccess();
        Mockito.verify(this.mView).showUsersInfo(this.mUserOne, this.mUserTwo);

        ArgumentCaptor<IMainModelCallback.Repos> callbackReposOne
                = ArgumentCaptor.forClass(IMainModelCallback.Repos.class);
        ArgumentCaptor<IMainModelCallback.Repos> callbackReposTwo
                = ArgumentCaptor.forClass(IMainModelCallback.Repos.class);

        //this.mPresenter.getRepositories();
        Mockito.verify(this.mReposModel).getUserRepos(
                Mockito.eq(USER_ONE),
                callbackReposOne.capture()
        );
        Mockito.verify(this.mReposModel).getUserRepos(
                Mockito.eq(USER_TWO),
                callbackReposTwo.capture()
        );

        Mockito.when(this.mReposOne.getRepositories()).thenReturn(new ArrayList<Repository>());
        Mockito.when(this.mReposTwo.getRepositories()).thenReturn(new ArrayList<Repository>());

        callbackReposOne.getValue().onGetReposSuccess(this.mReposOne);
        callbackReposTwo.getValue().onGetReposSuccess(this.mReposTwo);

        Mockito.verify(this.mView).showUserHasNoReposError(USER_ONE);
    }

    @Test
    public void shouldShowCodeStarWinner() {
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorOne
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);
        ArgumentCaptor<IMainModelCallback.Users> callbackCaptorTwo
                = ArgumentCaptor.forClass(IMainModelCallback.Users.class);

        Mockito.when(this.mView.getUserOne()).thenReturn(USER_ONE);
        Mockito.when(this.mView.getUserTwo()).thenReturn(USER_TWO);

        this.mPresenter.onClickStart();

        Mockito.verify(this.mView).getUserOne();
        Mockito.verify(this.mView).getUserTwo();
        Mockito.verify(this.mView).showProgressBar();

        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_ONE),
                callbackCaptorOne.capture()
        );
        Mockito.verify(this.mUserModel).getUser(
                Mockito.eq(USER_TWO),
                callbackCaptorTwo.capture()
        );

        Mockito.when(this.mUserOne.getLogin()).thenReturn(USER_ONE);
        callbackCaptorOne.getValue().onGetUserSuccess(mUserOne);
        Mockito.when(this.mUserTwo.getLogin()).thenReturn(USER_TWO);
        callbackCaptorTwo.getValue().onGetUserSuccess(mUserTwo);

        Mockito.verify(this.mView).onUsersSuccess();
        Mockito.verify(this.mView).showUsersInfo(this.mUserOne, this.mUserTwo);

        ArgumentCaptor<IMainModelCallback.Repos> callbackReposOne
                = ArgumentCaptor.forClass(IMainModelCallback.Repos.class);
        ArgumentCaptor<IMainModelCallback.Repos> callbackReposTwo
                = ArgumentCaptor.forClass(IMainModelCallback.Repos.class);

        //this.mPresenter.getRepositories();
        //Mockito.when(this.mReposModel).thenReturn(Injector.provideMainReposModel());
        Mockito.verify(this.mReposModel).getUserRepos(
                Mockito.eq(USER_ONE),
                callbackReposOne.capture()
        );
        Mockito.verify(this.mReposModel).getUserRepos(
                Mockito.eq(USER_TWO),
                callbackReposTwo.capture()
        );

        List<Repository> repos = new ArrayList<>();
        Repository a = new Repository();
        a.setName("repo");
        a.setFull_name("a/repo");
        a.setLanguage("Java");
        a.setStargazers_count(10);
        repos.add(a);
        Repository b = new Repository();
        b.setName("name");
        b.setFull_name("b/name");
        b.setLanguage("Java");
        b.setStargazers_count(20);
        repos.add(b);
        Repository c = new Repository();
        c.setName("other-name");
        c.setFull_name("c/other-name");
        c.setLanguage("Go");
        c.setStargazers_count((int) Math.random());
        repos.add(c);

        Mockito.when(this.mReposOne.getRepositories()).thenReturn(repos);

        List<Repository> reposTwo = new ArrayList<>();
        Repository d = new Repository();
        d.setName("other-name");
        d.setFull_name("c/other-name");
        d.setLanguage("Go");
        d.setStargazers_count((int) Math.random());
        reposTwo.add(d);
        Mockito.when(this.mReposTwo.getRepositories()).thenReturn(reposTwo);

        callbackReposOne.getValue().onGetReposSuccess(this.mReposOne);
        callbackReposTwo.getValue().onGetReposSuccess(this.mReposTwo);

        Mockito.verify(this.mView).showWinner();
    }
}