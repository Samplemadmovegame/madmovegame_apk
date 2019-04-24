package com.msewa.madmovegame.auth.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity implements MobileNoVerificationFrag.OnMobileNoVerificationFragInteractionListener
        , OtpFrag.OnOTPFragmentInteractionListener, RegisterFragment.OnRegisterFragIntracationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MobileNoVerificationFrag.newInstance()).commit();
    }

    @Override
    public void onClickVerify(String mobileNo,String countryCode) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, OtpFrag.newInstance(mobileNo,countryCode)).commit();
    }

    @Override
    public void onClickSubmit(String mobileNo,String countryCode) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, RegisterFragment.newInstance(mobileNo,countryCode)).commit();
    }

    @Override
    public void onClickSubmitFormButton() {
        //Open Main Activity
        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
    }

}
