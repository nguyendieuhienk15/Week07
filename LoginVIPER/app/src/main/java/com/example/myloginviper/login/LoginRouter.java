package com.example.myloginviper.login;

import android.app.Activity;
import android.content.Intent;

import com.example.myloginviper.MainActivity;

public class LoginRouter implements LoginContracts.Router{

    private Activity activity;

    public LoginRouter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void unRegister() {
        activity = null;
    }

    @Override
    public void presentHomeScreen() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
