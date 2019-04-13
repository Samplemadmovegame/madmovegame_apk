package com.example.madmovegame.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.madmovegame.R;
import com.example.madmovegame.home.HomeActivity;
import com.example.madmovegame.login.loginFragment.AuthenticationFragment;
import com.example.madmovegame.login.loginFragment.ForgetPwdFragment;
import com.example.madmovegame.login.loginFragment.LoginFragment;
import com.example.madmovegame.login.loginFragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListeners {

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
