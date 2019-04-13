package com.example.madmovegame.contest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madmovegame.constant.Const;
import com.example.madmovegame.R;
import com.example.madmovegame.contest.model.Contest;
import com.example.madmovegame.home.model.AllSports;
import com.example.madmovegame.payment.PaymentActivity;
import com.example.madmovegame.team.TeamActivity;
import com.example.madmovegame.util.AppAnimationUtil;

public class ContestActivity extends AppCompatActivity implements ContestFrag.OnContestFragmentInteractionListener, ContestInfo.OnContestInfoFragmentInteractionListener {
    public static final String PARAM = "param";
    private ImageView team1Img, team2Img;
    private TextView team1Name, team2Name;
    private AllSports sport;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        mContext = this;

        Bundle extras = getIntent().getExtras();
        sport = extras.getParcelable(PARAM);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back_button_backgroud));

        getSupportFragmentManager().beginTransaction().add(R.id.container, ContestFrag.newInstance()).commit();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        team1Img = findViewById(R.id.team1_image);
        team2Img = findViewById(R.id.team2_image);
        team1Name = findViewById(R.id.team1_name);
        team2Name = findViewById(R.id.team2_name);
        setTeam1Image();
        setTeam2Image();
        team1Name.setText(sport.getTeam1());
        team2Name.setText(sport.getTeam2());

        AppAnimationUtil.setAnimation(this,R.anim.left_to_right,team1Img);
        AppAnimationUtil.setAnimation(this,R.anim.left_to_right,team1Name);
        AppAnimationUtil.setAnimation(this,R.anim.right_to_left,team2Img);
        AppAnimationUtil.setAnimation(this,R.anim.right_to_left,team2Name);

    }


    private void setTeam1Image() {

        switch (sport.getTeam1()) {

            case Const.CSK:
                Glide.with(this)
                        .load(mContext.getResources().getDrawable(R.drawable.csk_icon))
                        .into(team1Img);
                break;


            case Const.MI:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mi_icon))
                        .into(team1Img);
                break;


            case Const.KKR:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.kkr_icon))
                        .into(team1Img);
                break;


            case Const.PUNE:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.pune_icon))
                        .into(team1Img);

                break;


            case Const.HYD:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.hyd_icon))
                        .into(team1Img);

                break;


            case Const.RCB:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.rcb_icon))
                        .into(team1Img);

                break;
        }
    }

    private void setTeam2Image() {

        switch (sport.getTeam2()) {

            case Const.CSK:
                Glide.with(this)
                        .load(mContext.getResources().getDrawable(R.drawable.csk_icon))
                        .into(team2Img);
                break;


            case Const.MI:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mi_icon))
                        .into(team2Img);
                break;


            case Const.KKR:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.kkr_icon))
                        .into(team2Img);
                break;


            case Const.PUNE:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.pune_icon))
                        .into(team2Img);

                break;


            case Const.HYD:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.hyd_icon))
                        .into(team2Img);

                break;


            case Const.RCB:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.rcb_icon))
                        .into(team2Img);

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.constests, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.wallet) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void openContestInfo(Contest contest) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, ContestInfo.newInstance(contest)).commit();

    }

    @Override
    public void openTeamAcitivity() {
        AllSports sport=new AllSports(Const.CSK, Const.MI, "Premier League", 0);
        Intent intent = new Intent(this, TeamActivity.class);
        intent.putExtra(TeamActivity.PARAM, sport);
        startActivity(intent);
    }

    @Override
    public void openPaymentAcitvity(Contest contest) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PaymentActivity.PARAM, contest);
        startActivity(intent);
    }

    public AllSports getSport() {
        return sport;
    }
}
