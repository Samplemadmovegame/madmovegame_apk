package com.example.madmovegame.login.loginFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madmovegame.R;
import com.example.madmovegame.util.AppAnimationUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "Login Fragment";
    private EditText mobileNumberTIEditText, passwordTIEditText;
    private TextView forgetPasswordTV, createAccountTV;
    private Button loginButton;

    LoginFragmentListeners loginFragmentListeners;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mobileNumberTIEditText = view.findViewById(R.id.mobile_numberTIEditText);
        passwordTIEditText = view.findViewById(R.id.password_TIEditText);

        forgetPasswordTV = view.findViewById(R.id.forget_password_tv);
        createAccountTV = view.findViewById(R.id.create_account_tv);

        AppAnimationUtil.setAnimation(getActivity(),R.anim.right_to_left,forgetPasswordTV);

        AppAnimationUtil.setAnimation(getActivity(),R.anim.left_to_right,mobileNumberTIEditText);
        AppAnimationUtil.setAnimation(getActivity(),R.anim.left_to_right,passwordTIEditText);

        loginButton = view.findViewById(R.id.login_button);

        forgetPasswordTV.setOnClickListener(this);
        createAccountTV.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_password_tv:
               loginFragmentListeners.openForgetPasswordFragment();
                break;

            case R.id.create_account_tv:
               loginFragmentListeners.openCreateAccountFragment();
                break;

            case R.id.login_button:
                loginFragmentListeners.openHomeFragment();
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            loginFragmentListeners = (LoginFragmentListeners) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }


    }

    //Interface
    public interface LoginFragmentListeners {

        void openForgetPasswordFragment();

        void openCreateAccountFragment();

        void openHomeFragment();
    }
}
