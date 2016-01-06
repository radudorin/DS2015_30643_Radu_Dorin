package com.example.radud.androidhardwarestore.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Member;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiConstants;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.utils.SessionUtils;
import com.example.radud.androidhardwarestore.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 30/12/2015.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.al_login_btn)
    Button mLoginBtn;
    @Bind(R.id.al_register_btn)
    Button mRegisterBtn;
    @Bind(R.id.al_password_et)
    EditText mPasswordET;
    @Bind(R.id.al_username_et)
    EditText mUsernameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("Login");
        if (SessionUtils.isUserLoggedIn()) {
            startHomeScreen();
        }
    }

    @OnClick(R.id.al_login_btn)
    void onLoginClicked() {

        if (mUsernameET.getText().toString().equals("") || mPasswordET.getText().toString().equals("")) {
            ToastUtils.showError(this, "Please enter a valid username and password");
            return;
        }

        ApiHelper.getApi().login(mUsernameET.getText().toString(), mPasswordET.getText().toString(), new Callback<Result<Member>>() {
            @Override
            public void success(Result<Member> result, Response response) {
                if (result.getHasErrors()) {
                    ToastUtils.showError(LoginActivity.this, result.getMessage());
                } else {
                    if (result.getResponse() != null) {
                        SessionUtils.saveUserID(result.getResponse().getId());
                        SessionUtils.saveUserROle(result.getResponse().getRole().getId());
                        SessionUtils.saveUserFullName(result.getResponse().getFullName());

                        startHomeScreen();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void startHomeScreen() {
        if (SessionUtils.getUserRole() == ApiConstants.CLIENT_ROLE) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            ToastUtils.showError(LoginActivity.this, "Currently only users can log in");
        }
    }

    @OnClick(R.id.al_register_btn)
    void onRegisterClicked() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
