package com.msewa.madmovegame.contest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.contest.adapter.RankListAdapter;
import com.msewa.madmovegame.contest.model.Contest;
import com.msewa.madmovegame.util.AppAnimationUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContestInfo.OnContestInfoFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContestInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContestInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private Contest contest;
    private RecyclerView mRankRecyclerView;
    private RankListAdapter mRankListAdapter;
    private Button joinContestBt;
    private TextView entryPrice, spotsLeft, totalSpots;


    private OnContestInfoFragmentInteractionListener mListener;

    public ContestInfo() {
        // Required empty public constructor
    }


    public static ContestInfo newInstance(Contest contest) {
        ContestInfo fragment = new ContestInfo();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, contest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contest = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contest_info, container, false);
        mRankRecyclerView = view.findViewById(R.id.rank_recycler_view);
        entryPrice = view.findViewById(R.id.entry_price);
        spotsLeft = view.findViewById(R.id.spots_left);
        totalSpots = view.findViewById(R.id.total_spots);

        entryPrice.setText(getActivity().getResources().getString(R.string.Rs) + " " + contest.getEntryPrice());
        joinContestBt = view.findViewById(R.id.join_contest_bt);
        joinContestBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openPaymentAcitvity(new Contest());
            }
        });

        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, spotsLeft);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, totalSpots);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, joinContestBt);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRankAdapter();
    }

    public void setUpRankAdapter() {
        mRankListAdapter = new RankListAdapter(getActivity(), new RankListAdapter.RankListAdapterListener() {
            @Override
            public void onItemClick(Contest contest) {

            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRankRecyclerView.setLayoutManager(mLayoutManager);
        mRankRecyclerView.setAdapter(mRankListAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContestInfoFragmentInteractionListener) {
            mListener = (OnContestInfoFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnContestInfoFragmentInteractionListener {
        // TODO: Update argument type and name
        void openPaymentAcitvity(Contest contest);
    }
}
