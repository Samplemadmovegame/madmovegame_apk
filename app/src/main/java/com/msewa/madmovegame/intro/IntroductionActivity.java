package com.msewa.madmovegame.intro;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.intro.adapter.IntroViewPagerAdapter;
import com.msewa.madmovegame.auth.LoginActivity;

public class IntroductionActivity extends AppCompatActivity {

    private ViewPager introductionViewPager;
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        introductionViewPager = findViewById(R.id.introduction_viewpager);

        // it applies sliding effects on fragment in view pager
        introductionViewPager.setPageTransformer(false, new FadePageTransformer());

        skipButton = findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroductionActivity.this, LoginActivity.class));
                finish();
            }
        });

        setUpViewAdapter();
    }

    public void setUpViewAdapter() {
        IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter(getSupportFragmentManager());
        introViewPagerAdapter.addFragment(IntroFragment1.newInstance());
        introViewPagerAdapter.addFragment(IntroFragment2.newInstance());
        introViewPagerAdapter.addFragment(IntroFragment3.newInstance());
        introViewPagerAdapter.addFragment(IntroFragment4.newInstance());
        introductionViewPager.setAdapter(introViewPagerAdapter);
    }
}
