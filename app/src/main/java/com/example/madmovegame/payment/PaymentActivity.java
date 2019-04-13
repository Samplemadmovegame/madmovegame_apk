package com.example.madmovegame.payment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.madmovegame.R;
import com.example.madmovegame.contest.model.Contest;

public class PaymentActivity extends AppCompatActivity {

    public static final String  TAG="Payment Acitity";
    public static final String PARAM ="param";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Bundle extras = getIntent().getExtras();
        Contest contest = extras.getParcelable(PARAM);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back_button_backgroud));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.container,PaymentFrag.newInstance(contest)).commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
