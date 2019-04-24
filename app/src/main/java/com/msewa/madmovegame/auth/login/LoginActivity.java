package com.msewa.madmovegame.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.auth.register.RegisterActivity;
import com.msewa.madmovegame.home.HomeActivity;
import com.msewa.madmovegame.util.AppAnimationUtil;

public class LoginActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListeners, ForgetPwdFragment.OnForgetPwdFragIntractionListener {

    public final String TAG = "LoginActivity";
    private RelativeLayout layout1;
    private FrameLayout layout2;
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.container_view);
        titleTv = findViewById(R.id.title_tv);

        //Open Authentication Fragment
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                .add(R.id.container_view, LoginFragment.newInstance()).commit();

        AppAnimationUtil.setAnimation(this, R.anim.top_to_bottom, layout1);
        // AppAnimationUtil.setAnimation(this, R.anim.bottom_to_top, layout2);
    }

    @Override
    public void openForgetPasswordFragment(String mobileNo, String countryCode) {
        //Open Forgot Fragment
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.container_view, ForgetPwdFragment.newInstance(mobileNo, countryCode))
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

    @Override
    public void setLoginFragTitle(String s) {
        titleTv.setText(s);
    }

    @Override
    public void setForgetPwdTitle(String s) {
        titleTv.setText(s);
    }
}
