package com.msewa.madmovegame.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.home.HomeActivity;
import com.msewa.madmovegame.login.loginFragment.AuthenticationFragment;
import com.msewa.madmovegame.login.loginFragment.ForgetPwdFragment;
import com.msewa.madmovegame.login.loginFragment.LoginFragment;
import com.msewa.madmovegame.login.loginFragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListeners {

    public final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Open Authentication Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, AuthenticationFragment.newInstance())
                .addToBackStack(null).commit();
    }

    @Override
    public void openForgetPasswordFragment() {
        //Open Forget Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, ForgetPwdFragment.newInstance())
                .addToBackStack(null).commit();

    }

    @Override
    public void openCreateAccountFragment() {
        //Open Register Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, RegisterFragment.newInstance())
                .addToBackStack(null).commit();

    }

    @Override
    public void openHomeFragment() {
        //Open Main Activity
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}
