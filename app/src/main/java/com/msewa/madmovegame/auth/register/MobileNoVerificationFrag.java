package com.msewa.madmovegame.auth.register;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.msewa.madmovegame.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MobileNoVerificationFrag.OnMobileNoVerificationFragInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MobileNoVerificationFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileNoVerificationFrag extends Fragment {

    private Button verifyBt;
    private EditText mobileNoEt;

    private OnMobileNoVerificationFragInteractionListener mListener;

    public MobileNoVerificationFrag() {
        // Required empty public constructor
    }

    public static MobileNoVerificationFrag newInstance() {

        Bundle args = new Bundle();

        MobileNoVerificationFrag fragment = new MobileNoVerificationFrag();
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
        View view = inflater.inflate(R.layout.fragment_mobile_number_verification, container, false);
        verifyBt = view.findViewById(R.id.verify_button);
        mobileNoEt = view.findViewById(R.id.mobile_no_ed);
        verifyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickVerify(mobileNoEt.getText().toString());
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMobileNoVerificationFragInteractionListener) {
            mListener = (OnMobileNoVerificationFragInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMobileNoVerificationFragInteractionListener");
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
    public interface OnMobileNoVerificationFragInteractionListener {
        void onClickVerify(String mobileNo);
    }
}
