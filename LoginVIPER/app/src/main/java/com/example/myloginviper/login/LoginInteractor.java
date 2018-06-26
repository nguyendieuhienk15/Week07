package com.example.myloginviper.login;

public class LoginInteractor implements LoginContracts.Interactor{

    private LoginContracts.InteractorOutput output;

    public LoginInteractor(LoginContracts.InteractorOutput output) {
        this.output = output;
    }

    @Override
    public void loginUser(String userName, String password) {

        //dummy validation
        if(userName.isEmpty() || password.isEmpty()) {
            output.loginResultFail();
        } else {
            output.loginResultSuccess();
        }

    }

    @Override
    public void unRegister() {
        output = null;
    }
}
