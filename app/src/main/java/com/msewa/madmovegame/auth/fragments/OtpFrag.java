package com.msewa.madmovegame.auth.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.msewa.madmovegame.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OtpFrag.OnOTPFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class OtpFrag extends Fragment {
   
    private OnOTPFragmentInteractionListener mListener;
    private Button submitBt;
    private TextView resendBt;
    private EditText otpEt;

    public OtpFrag() {
        // Required empty public constructor
    }

    public static OtpFrag newInstance() {
        
        Bundle args = new Bundle();
        
        OtpFrag fragment = new OtpFrag();
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
        View view= inflater.inflate(R.layout.fragment_otp, container, false);
        submitBt=view.findViewById(R.id.submit_bt);
        otpEt=view.findViewById(R.id.otp_et);
        resendBt=view.findViewById(R.id.resend_otp_bt);
        
        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickSubmit();
            }
        });
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOTPFragmentInteractionListener) {
            mListener = (OnOTPFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOTPFragmentInteractionListener");
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
    public interface OnOTPFragmentInteractionListener {
        void onClickSubmit();
    }
}
