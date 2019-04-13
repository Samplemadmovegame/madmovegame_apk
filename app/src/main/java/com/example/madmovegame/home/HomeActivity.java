package com.example.madmovegame.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.madmovegame.More.MoreActivity;
import com.example.madmovegame.More.MoreFrag;
import com.example.madmovegame.R;
import com.example.madmovegame.feed.FeedActivity;
import com.example.madmovegame.feed.FeedFrag;
import com.example.madmovegame.matches.MatchesFrag;
import com.example.madmovegame.matches.MathcesActivity;
import com.example.madmovegame.navigation.BalanceFrag;
import com.example.madmovegame.navigation.InviteFrag;
import com.example.madmovegame.navigation.MyInfoSetting;
import com.example.madmovegame.navigation.NavigationActivity;
import com.example.madmovegame.navigation.PointSystemFrag;
import com.example.madmovegame.navigation.RewardFrag;
import com.example.madmovegame.navigation.UserFrag;
import com.example.madmovegame.notification.NotificationActivity;
import com.example.madmovegame.setting.SettingActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ImageView backImg = navigationView.getHeaderView(0)
                .findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // set custom image for navigation icon
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.profile_circle_backgroud));

        //init bottom navigation with default
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportActionBar().setLogo(getResources().getDrawable(R.drawable.app_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, HomeFrag.newInstance()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contest_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notification) {
            startActivity(new Intent(this, NotificationActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = new Intent(this, NavigationActivity.class);
        int id = item.getItemId();

        if (id == R.id.account) {
            intent.putExtra(NavigationActivity.PARAM, UserFrag.TAG);
            startActivity(intent);
        } else if (id == R.id.balance) {
            intent.putExtra(NavigationActivity.PARAM, BalanceFrag.TAG);
            startActivity(intent);

        } else if (id == R.id.reward) {
            intent.putExtra(NavigationActivity.PARAM, RewardFrag.TAG);
            startActivity(intent);

        } else if (id == R.id.invite) {
            intent.putExtra(NavigationActivity.PARAM, InviteFrag.TAG);
            startActivity(intent);

        } else if (id == R.id.my_setting) {
            intent.putExtra(NavigationActivity.PARAM, MyInfoSetting.TAG);
            startActivity(intent);

        } else if (id == R.id.point) {
            intent.putExtra(NavigationActivity.PARAM, PointSystemFrag.TAG);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportActionBar().setLogo(getResources().getDrawable(R.drawable.app_logo));
                    getSupportActionBar().setDisplayUseLogoEnabled(true);
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, HomeFrag.newInstance()).commit();
                    return true;
                case R.id.nav_feed:
                    getSupportActionBar().setTitle(getResources().getString(R.string.feed));
                    getSupportActionBar().setDisplayUseLogoEnabled(false);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, FeedFrag.newInstance()).commit();
                    return true;
                case R.id.nav_matches:
                    getSupportActionBar().setTitle(getResources().getString(R.string.matches));
                    getSupportActionBar().setDisplayUseLogoEnabled(false);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, MatchesFrag.newInstance()).commit();
                    return true;
                case R.id.nav_more:
                    getSupportActionBar().setTitle(getResources().getString(R.string.more));
                    getSupportActionBar().setDisplayUseLogoEnabled(false);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, MoreFrag.newInstance()).commit();
                    return true;
            }
            return false;
        }
    };

}
