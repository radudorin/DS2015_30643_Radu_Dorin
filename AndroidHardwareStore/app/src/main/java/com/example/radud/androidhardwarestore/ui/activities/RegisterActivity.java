package com.example.radud.androidhardwarestore.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Member;
import com.example.radud.androidhardwarestore.model.Result;
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
 * Created by radud on 02/01/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.ar_username_et)
    EditText mUsernameEt;
    @Bind(R.id.ar_full_name_et)
    EditText mFullNameEt;
    @Bind(R.id.ar_password1_et)
    EditText mPassword1Et;
    @Bind(R.id.ar_password_et)
    EditText mPasswordEt;
    @Bind(R.id.ar_email_et)
    EditText mEmailEt;
    @Bind(R.id.ar_phone_et)
    EditText mPhoneEt;
    @Bind(R.id.ar_address_et)
    EditText mAddressEt;
    @Bind(R.id.ar_birth_date_et)
    EditText mBirthDateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setTitle("Register");
    }

    @OnClick(R.id.ar_done)
    void onDoneClicked() {
        String text = "";
        if (mFullNameEt.getText().toString().equals("")
                || mUsernameEt.getText().toString().equals("")
                || mPasswordEt.getText().toString().equals("")
                || mPassword1Et.getText().toString().equals("")
                || mAddressEt.getText().toString().equals("")
                || mEmailEt.getText().toString().equals("")
                || mPhoneEt.getText().toString().equals("")
                || mBirthDateEt.getText().toString().equals("")) {
            text = "Please fill all the fields";
            ToastUtils.showError(this, text);
            return;
        } else if (!mPasswordEt.getText().toString().equals(mPassword1Et.getText().toString())) {
            text = "Please check your password. Passwords must be the same.";
            ToastUtils.showError(this, text);
            return;
        }

        Member member = new Member(mUsernameEt.getText().toString(), mPasswordEt.getText().toString(),
                mEmailEt.getText().toString(), mFullNameEt.getText().toString(), mAddressEt.getText().toString(),
                mPhoneEt.getText().toString(), mBirthDateEt.getText().toString());

        ApiHelper.getApi().register(member, new Callback<Result<Member>>() {
            @Override
            public void success(Result<Member> memberResult, Response response) {
                if (memberResult.getHasErrors() && memberResult.getResponse() == null) {
                    ToastUtils.showError(RegisterActivity.this, memberResult.getMessage());
                } else {
                    SessionUtils.saveUserID(memberResult.getResponse().getId());
                    SessionUtils.saveUserROle(memberResult.getResponse().getRole().getId());
                    SessionUtils.saveUserFullName(memberResult.getResponse().getFullName());

                    ToastUtils.showError(RegisterActivity.this, memberResult.getResponse().getRole().getId() + "");
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
