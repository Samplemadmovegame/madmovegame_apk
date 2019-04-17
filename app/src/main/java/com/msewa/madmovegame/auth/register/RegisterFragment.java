package com.msewa.madmovegame.auth.register;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.msewa.madmovegame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Button submitFormBt;
    private OnRegisterFragIntracationListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        submitFormBt = view.findViewById(R.id.submit_form_bt);
        submitFormBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickSubmitFormButton();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragIntracationListener) {
            mListener = (OnRegisterFragIntracationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragIntracationListener");
        }
    }

    public interface OnRegisterFragIntracationListener {
        void onClickSubmitFormButton();
    }

}
