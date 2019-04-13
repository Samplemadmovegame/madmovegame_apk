package com.example.madmovegame.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.madmovegame.R;

public class NavigationActivity extends AppCompatActivity {
    public static final String PARAM = "param";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        //setup toolbar for this activity
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set custom back button image
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back_button_backgroud));

        // get tag value to open target fragment
        String tag = getIntent().getExtras().getString(PARAM);
        openFrag(tag);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void openFrag(String tag) {
        switch (tag) {
            case UserFrag.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, UserFrag.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.my_account));
                break;

            case PointSystemFrag.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PointSystemFrag.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.point_system));
                break;

            case RewardFrag.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, RewardFrag.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.my_reward_offer));
                break;

            case BalanceFrag.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, BalanceFrag.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.my_balance));
                break;

            case MyInfoSetting.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, MyInfoSetting.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.my_setting));
                break;

            case InviteFrag.TAG:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, InviteFrag.newInstance()).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.invite_ref));
                break;

        }

    }
}
