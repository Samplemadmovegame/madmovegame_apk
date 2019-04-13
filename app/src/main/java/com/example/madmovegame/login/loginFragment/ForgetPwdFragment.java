package com.example.madmovegame.login.loginFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madmovegame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends Fragment {


    public ForgetPwdFragment() {
        // Required empty public constructor
    }

    public static ForgetPwdFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_pwd, container, false);
    }

}
