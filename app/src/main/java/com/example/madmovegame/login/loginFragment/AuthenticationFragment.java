package com.example.madmovegame.login.loginFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madmovegame.R;
import com.example.madmovegame.util.AppAnimationUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenticationFragment extends Fragment {

    private Button registerButton, loginButton;

    public AuthenticationFragment() {
        // Required empty public constructor
    }

    public static AuthenticationFragment newInstance() {

        Bundle args = new Bundle();

        AuthenticationFragment fragment = new AuthenticationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);
        registerButton = view.findViewById(R.id.register_button);
        loginButton = view.findViewById(R.id.login_button);

        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, registerButton);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.bottom_to_top, 0, 0, 0)
                        .replace(R.id.container_view, RegisterFragment.newInstance())
                        .addToBackStack(null)
                        .commit();

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.bottom_to_top, 0, 0, 0)
                        .replace(R.id.container_view, LoginFragment.newInstance(), LoginFragment.TAG)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }


}
