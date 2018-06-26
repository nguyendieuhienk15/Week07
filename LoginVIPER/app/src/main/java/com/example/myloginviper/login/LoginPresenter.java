package com.example.myloginviper.login;

import android.app.Activity;

import com.example.myloginviper.R;

public class LoginPresenter implements LoginContracts.Presenter, LoginContracts.InteractorOutput{

    private LoginContracts.View mView;
    private LoginContracts.Interactor interactor;
    private LoginContracts.Router router;

    LoginPresenter(LoginContracts.View view) {
        interactor = new LoginInteractor(this);
        router = new LoginRouter((Activity) view);
        mView = view;
    }

    @Override
    public void onDestroy() {
        interactor.unRegister();
        interactor = null;
        router.unRegister();
        router = null;
    }

    @Override
    public void onLoginButtonPressed(String userName, String password) {
        interactor.loginUser(userName, password);
    }

    //Dummy use of router
    @Override
    public void loginResultSuccess() {
        router.presentHomeScreen();
    }

    @Override
    public void loginResultFail() {
        mView.showError(R.string.login_failed);
    }
}
