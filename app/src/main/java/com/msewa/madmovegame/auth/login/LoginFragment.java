package com.msewa.madmovegame.auth.login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.msewa.madmovegame.R;
import com.msewa.madmovegame.api.ApiClient;
import com.msewa.madmovegame.api.ApiServices;
import com.msewa.madmovegame.common.LoadingDialog;
import com.msewa.madmovegame.util.AppAnimationUtil;
import com.msewa.madmovegame.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "Login Fragment";
    private EditText mobileNumberTIEditText, passwordTIEditText;
    private TextView forgetPasswordTV, createAccountTV;
    private Button loginButton;

    private LoginFragmentListeners mListeners;
    private String deviceId;
    private ApiServices baseService;
    private Call<JsonObject> login;
    private LoadingDialog loadingDialog;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseService = ApiClient.getInstance().getBaseService();
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

        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, forgetPasswordTV);

        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, mobileNumberTIEditText);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, passwordTIEditText);

        loginButton = view.findViewById(R.id.login_button);

        forgetPasswordTV.setOnClickListener(this);
        createAccountTV.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        loadingDialog = new LoadingDialog(getActivity());

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_password_tv:
                mListeners.openForgetPasswordFragment();
                break;

            case R.id.create_account_tv:
                mListeners.openCreateAccountFragment();
                break;

            case R.id.login_button:
                checkValidation();
                break;
        }

    }

    private void checkValidation() {

        if (mobileNumberTIEditText.getText().toString().length() == 10) {
            if (passwordTIEditText.getText().toString().length() == 6)
                login(mobileNumberTIEditText.getText().toString(), passwordTIEditText.getText().toString());
            else
                passwordTIEditText.setError(getString(R.string.error_msg_six_digit_password));

        } else {
            mobileNumberTIEditText.setError(getString(R.string.error_msg_10_digit_number));
        }

    }

    private void login(String mobileNo, String password) {

        if (Util.getAndroidId(getActivity()) != null) {
            deviceId = Util.getAndroidId(getActivity());
        }

        login = baseService.login(mobileNo, password, deviceId);

        loadingDialog.show();
        login.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                loadingDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loadingDialog.dismiss();

            }
        });
        mListeners.openHomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListeners = (LoginFragmentListeners) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement LoginFragmentListeners");
        }


    }

    //Interface
    public interface LoginFragmentListeners {

        void openForgetPasswordFragment();

        void openCreateAccountFragment();

        void openHomeFragment();
    }
}
