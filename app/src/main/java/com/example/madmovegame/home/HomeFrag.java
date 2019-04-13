package com.example.madmovegame.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madmovegame.R;
import com.example.madmovegame.contest.ContestActivity;
import com.example.madmovegame.home.adapter.AllSportsListAdapter;
import com.example.madmovegame.home.adapter.SportsListAdapter;
import com.example.madmovegame.home.model.AllSports;
import com.example.madmovegame.util.HomeUtil;
import com.example.madmovegame.util.AppAnimationUtil;


public class HomeFrag extends Fragment {
    public static final String TAG = "HomeFrag";
    private SportsListAdapter mSportsListAdapter;
    private AllSportsListAdapter mAllSportsListAdapter;
    private RecyclerView sportRecyclerView, allSportsRecyclerView;
    private TextView updateText;


    public HomeFrag() {
        // Required empty public constructor
    }

    public static HomeFrag newInstance() {
        HomeFrag fragment = new HomeFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        allSportsRecyclerView = view.findViewById(R.id.all_sports_recycler_view);
        sportRecyclerView = view.findViewById(R.id.sport_recycler_view);
        updateText = view.findViewById(R.id.text);

        AppAnimationUtil.setAnimation(getActivity(),R.anim.left_to_right,updateText);

        setUpSportsAdapter();
        setUpAllSportsAdapter();
        return view;
    }

    private void setUpSportsAdapter() {
        mSportsListAdapter = new SportsListAdapter(getActivity(), new SportsListAdapter.SportsListAdapterListener() {
            @Override
            public void onItemClick(String sportName) {
                Log.i(TAG, sportName);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        sportRecyclerView.setLayoutManager(mLayoutManager);
        sportRecyclerView.setAdapter(mSportsListAdapter);
    }

    private void setUpAllSportsAdapter() {
        mAllSportsListAdapter = new AllSportsListAdapter(getActivity(), HomeUtil.getAllSportsCricketList(), new AllSportsListAdapter.SportsListAdapterListener() {
            @Override
            public void onItemClick(AllSports sportName) {
                Intent intent = new Intent(getActivity(), ContestActivity.class);
                intent.putExtra(ContestActivity.PARAM, sportName);
                startActivity(intent);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        allSportsRecyclerView.setLayoutManager(mLayoutManager);
        allSportsRecyclerView.setAdapter(mAllSportsListAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
