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
import android.widget.ImageButton;
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

    public static final String TAG = "Login_Fragment";
    private EditText mobileNoEt, passwordEt;
    private TextView forgetPasswordBt, createAccountBt;
    private Button loginBt;
    private ImageButton passwordEyeBt;

    private LoginFragmentListeners mListeners;
    private String deviceId;
    private ApiServices baseService;
    private Call<JsonObject> loginService;
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
        mobileNoEt = view.findViewById(R.id.mobileNo_et);
        passwordEt = view.findViewById(R.id.password_et);
        forgetPasswordBt = view.findViewById(R.id.forget_password_bt);
        createAccountBt = view.findViewById(R.id.create_account_bt);
        passwordEyeBt = view.findViewById(R.id.password_eye_bt);
        loginBt = view.findViewById(R.id.login_bt);

        // init animation for views
        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, forgetPasswordBt);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, mobileNoEt);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, passwordEt);


        /**
         * set listener for buttons where click handles on {@link #onClick(View view)} methods
         */
        forgetPasswordBt.setOnClickListener(this);
        createAccountBt.setOnClickListener(this);
        loginBt.setOnClickListener(this);
        passwordEyeBt.setOnClickListener(this);

        // init custom progressing dialog
        loadingDialog = new LoadingDialog(getActivity());

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password_bt:
                if (mobileNoEt.getText().toString().length() == 10)
                    mListeners.openForgetPasswordFragment();
                else
                    mobileNoEt.setError(getString(R.string.error_msg_10_digit_number));
                break;

            case R.id.create_account_bt:
                mListeners.openCreateAccountFragment();
                break;

            case R.id.login_bt:
                if (mobileNoEt.getText().toString().length() == 10) {
                    if (passwordEt.getText().toString().length() == 6)
                        login(mobileNoEt.getText().toString(), passwordEt.getText().toString());
                    else
                        passwordEt.setError(getString(R.string.error_msg_six_digit_password));

                } else {
                    mobileNoEt.setError(getString(R.string.error_msg_10_digit_number));
                }
                break;

            case R.id.password_eye_bt:

                break;
        }

    }


    /**
     * this method is used for call the api for login
     *
     *
     * @param mobileNo user mobile number
     * @param password user given password
     *                 {@link #deviceId}
     *                 <p>
     *                 this three data is required parameter for api login {@link #baseService}
     */

    private void login(String mobileNo, String password) {

        if (Util.getAndroidId(getActivity()) != null) {
            deviceId = Util.getAndroidId(getActivity());
        }
        loginService = baseService.login(mobileNo, password, deviceId);

        loadingDialog.show();
        loginService.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                loadingDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loadingDialog.dismiss();

            }
        });
        //@TODO need to remove ,just use for completing the flow
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
