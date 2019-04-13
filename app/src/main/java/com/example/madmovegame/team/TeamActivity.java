package com.example.madmovegame.team;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.madmovegame.R;
import com.example.madmovegame.home.model.AllSports;

public class TeamActivity extends AppCompatActivity implements TeamFrag.OnTeamFragmentInteractionListener, TeamPreview.OnTeamPreviewFragmentInteractionListener {

    public static final String PARAM="param";
    private AllSports sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
         sport=getIntent().getExtras().getParcelable(PARAM);
        getSupportFragmentManager().beginTransaction().add(R.id.container, TeamFrag.newInstance(sport)).commit();
    }

    @Override
    public void onclickTeamPreview() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, TeamPreview.newInstance()).commit();

    }

    @Override
    public void onClickContinue() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
