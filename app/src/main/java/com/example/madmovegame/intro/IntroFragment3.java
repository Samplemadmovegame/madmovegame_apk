package com.example.madmovegame.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madmovegame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment3 extends Fragment {

    public IntroFragment3() {
        // Required empty public constructor
    }

    public static IntroFragment3 newInstance() {

        Bundle args = new Bundle();

        IntroFragment3 fragment = new IntroFragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_intro_fragment3, container, false);

    }

}
