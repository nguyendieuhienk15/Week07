package com.example.myloginviper.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myloginviper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View{

    @BindView(R.id.email)
    EditText userName;
    @BindView(R.id.password)
    EditText userPassword;
    @BindView(R.id.login_button)
    Button login;

    private LoginContracts.Presenter mPresenter;

    private void onLoginButtonPressed() {
        String userNameText = userName.getText().toString();
        String passwordText = userPassword.getText().toString();
        mPresenter.onLoginButtonPressed(userNameText, passwordText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonPressed();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showError(int id) {

        String message = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
