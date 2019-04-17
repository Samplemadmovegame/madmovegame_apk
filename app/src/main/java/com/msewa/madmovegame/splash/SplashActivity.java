package com.msewa.madmovegame.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.auth.login.LoginActivity;
import com.msewa.madmovegame.home.HomeActivity;
import com.msewa.madmovegame.intro.IntroductionActivity;
import com.msewa.madmovegame.util.AppAnimationUtil;

public class SplashActivity extends AppCompatActivity {
    private ImageView topImageView, bottomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_splash);
        topImageView = findViewById(R.id.logo_img);
        bottomImageView = findViewById(R.id.bottom_imageView);

        AppAnimationUtil.setAnimation(this, R.anim.top_to_bottom, topImageView);
        AppAnimationUtil.setAnimation(this, R.anim.bottom_to_top, bottomImageView);

        // For Splash Screen Time Out
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences intro = getSharedPreferences("intro", 0);
                boolean oneTime = intro.getBoolean("oneTime", false);

                if (oneTime) {
                    boolean loggedin = intro.getBoolean("loggedin", false);

                    if (loggedin) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    intro.edit().putBoolean("oneTime", true).apply();
                    Intent intent = new Intent(SplashActivity.this, IntroductionActivity.class);
                    // Pass data object in the bundle and populate details activity.
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(SplashActivity.this, (View) topImageView, "logo");
                    startActivity(intent, options.toBundle());
                    finish();
                }
            }
        }, 1000);
    }
}
