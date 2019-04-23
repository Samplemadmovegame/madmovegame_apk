package com.msewa.madmovegame.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.auth.register.RegisterActivity;
import com.msewa.madmovegame.home.HomeActivity;

public class LoginActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListeners {

    public final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Open Authentication Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, LoginFragment.newInstance())
                .addToBackStack(null).commit();
    }

    @Override
    public void openForgetPasswordFragment(String mobileNo) {
        //Open Forget Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, ForgetPwdFragment.newInstance(mobileNo))
                .addToBackStack(null).commit();

    }

    @Override
    public void openCreateAccountFragment() {
        //Open Register Fragment
        startActivity(new Intent(this, RegisterActivity.class));

    }

    @Override
    public void openHomeFragment() {
        //Open Main Activity
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}
