package com.msewa.madmovegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TestAcitvity extends AppCompatActivity {

    ImageView p1,p11,p12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acitvity);

        p1=findViewById(R.id.p1_img);
        p11=findViewById(R.id.p11_img);
        p12=findViewById(R.id.p12_img);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        p1.startAnimation(animation);

    }


}
