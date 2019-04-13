package com.example.madmovegame.team;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madmovegame.constant.Const;
import com.example.madmovegame.R;
import com.example.madmovegame.home.model.AllSports;
import com.example.madmovegame.util.AppAnimationUtil;

import java.util.ArrayList;
import java.util.List;


public class TeamFrag extends Fragment {

    public static final String TAG = "TeamFrag";
    public static final String PARAM = "param";
    private ViewPager viewPager;
    private Button teamPreviewBt;
    private ImageView team1Img, team2Img, backBt;
    private TextView team1Name, team2Name;
    private AllSports sport;
    private Context mContext;
    private TabLayout tabLayout;

    private TextView title, playerTv, creditLeftTv, creditLeft, maxPlayerTv, totalPlayerNoTv, totalSelectedPlayer, slashSymbolTv;

    private OnTeamFragmentInteractionListener mListener;

    public TeamFrag() {
        // Required empty public constructor
    }

    public static TeamFrag newInstance() {

        Bundle args = new Bundle();

        TeamFrag fragment = new TeamFrag();
        fragment.setArguments(args);
        return fragment;
    }

    public static TeamFrag newInstance(AllSports sports) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM, sports);
        TeamFrag fragment = new TeamFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sport = getArguments().getParcelable(PARAM);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("WK"));
        tabLayout.addTab(tabLayout.newTab().setText("BAT"));
        tabLayout.addTab(tabLayout.newTab().setText("AR"));
        tabLayout.addTab(tabLayout.newTab().setText("BOWL"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        backBt = view.findViewById(R.id.back_bt);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        teamPreviewBt = view.findViewById(R.id.team_preview_bt);
        title = view.findViewById(R.id.title);
        maxPlayerTv = view.findViewById(R.id.max_player_tv);
        creditLeftTv = view.findViewById(R.id.credit_left_v);
        playerTv = view.findViewById(R.id.player_v);
        creditLeft = view.findViewById(R.id.credit_left);
        totalPlayerNoTv = view.findViewById(R.id.total_player_no_tv);
        slashSymbolTv = view.findViewById(R.id.slash_symb);
        totalSelectedPlayer = view.findViewById(R.id.total_selected_player);

        teamPreviewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onclickTeamPreview();
            }
        });

        team1Img = view.findViewById(R.id.team1_img);
        team2Img = view.findViewById(R.id.team2_img);
        team1Name = view.findViewById(R.id.team1_name);
        team2Name = view.findViewById(R.id.team2_name);
        setTeam1Image();
        setTeam2Image();
        team1Name.setText(sport.getTeam1());
        team2Name.setText(sport.getTeam2());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // setupAnimation

        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, title);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, playerTv);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, creditLeftTv);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, maxPlayerTv);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, totalSelectedPlayer);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, totalPlayerNoTv);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, slashSymbolTv);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, creditLeft);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.rotate, backBt);

        return view;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPagerAdapter();
    }

    private void setUpViewPagerAdapter() {
        PlayerListPagerAdapter mPlayerListPagerAdapter = new PlayerListPagerAdapter(getChildFragmentManager());
        mPlayerListPagerAdapter.addFragment(PlayerListFrag.newInstance());
        mPlayerListPagerAdapter.addFragment(PlayerListFrag.newInstance());
        mPlayerListPagerAdapter.addFragment(PlayerListFrag.newInstance());
        mPlayerListPagerAdapter.addFragment(PlayerListFrag.newInstance());
        viewPager.setAdapter(mPlayerListPagerAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTeamFragmentInteractionListener) {
            mListener = (OnTeamFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnTeamFragmentInteractionListener {
        void onclickTeamPreview();
    }


    public class PlayerListPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();

        public PlayerListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {

            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int i) {

            return fragmentList.get(i);

        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


    }
}
