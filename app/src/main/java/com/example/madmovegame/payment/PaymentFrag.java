package com.example.madmovegame.payment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madmovegame.R;
import com.example.madmovegame.contest.model.Contest;
import com.example.madmovegame.payment.adapter.PaymentListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private OnFragmentInteractionListener mListener;

    private Contest contest;
    private RecyclerView mPaymentRecyclerView;
    private PaymentListAdapter mPaymentListAdapter;
    private TextView price;

    public PaymentFrag() {
        // Required empty public constructor
    }


    public static PaymentFrag newInstance(Contest contest) {
        PaymentFrag fragment = new PaymentFrag();
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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
         price = view.findViewById(R.id.price);
        mPaymentRecyclerView = view.findViewById(R.id.payment_recycler_view);
        price.setText(contest.getEntryPrice());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        price.setText(getActivity().getResources().getString(R.string.Rs)+"  "+"10");
        setUpPaymentListAdapter();
    }

    private void setUpPaymentListAdapter() {
        mPaymentListAdapter = new PaymentListAdapter(getContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mPaymentRecyclerView.setLayoutManager(mLayoutManager);
        mPaymentRecyclerView.setAdapter(mPaymentListAdapter);

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
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
