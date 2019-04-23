package com.msewa.madmovegame.auth.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.api.ApiClient;
import com.msewa.madmovegame.api.ApiServices;
import com.msewa.madmovegame.common.LoadingDialog;
import com.msewa.madmovegame.home.HomeActivity;
import com.msewa.madmovegame.util.AppAnimationUtil;
import com.msewa.madmovegame.util.AppSharePref;
import com.msewa.madmovegame.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

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

    private LoginFragmentListeners mListeners;
    private String deviceId;
    private ApiServices baseService;
    private Call<JSONObject> loginService;
    private Call<JSONObject> forgetPasswordService;
    private LoadingDialog loadingDialog;
    private TextInputLayout mobileTILayout, passwordTILayout;


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
        mobileNoEt = view.findViewById(R.id.contact_et);
        passwordEt = view.findViewById(R.id.password_et);
        forgetPasswordBt = view.findViewById(R.id.forget_password_bt);
        createAccountBt = view.findViewById(R.id.create_account_bt);
        loginBt = view.findViewById(R.id.login_bt);

        mobileTILayout = view.findViewById(R.id.contact_t_i_layout);
        passwordTILayout = view.findViewById(R.id.password_t_i_layout);


        // init animation for views
        AppAnimationUtil.setAnimation(getActivity(), R.anim.right_to_left, forgetPasswordBt);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, mobileTILayout);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.left_to_right, passwordTILayout);

        /**
         * set text change listener for text field  where  this method listen to text change {@link MyTextWatcher#afterTextChanged(Editable)}
         */
        mobileNoEt.addTextChangedListener(new MyTextWatcher(mobileNoEt));
        passwordEt.addTextChangedListener(new MyTextWatcher(passwordEt));


        /**
         * set listener for buttons where click handles on {@link #onClick(View view)} methods
         */
        forgetPasswordBt.setOnClickListener(this);
        createAccountBt.setOnClickListener(this);
        loginBt.setOnClickListener(this);

        // init custom progressing dialog
        loadingDialog = new LoadingDialog(getActivity());

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password_bt:
                if (mobileNoEt.getText().toString().length() == 10) {
                    sendOTPforgetPassword();
                    mListeners.openForgetPasswordFragment(mobileNoEt.getText().toString());
                } else {
                    mobileNoEt.requestFocus();
                    mobileTILayout.setError(getString(R.string.error_msg_10_digit_number));
                }
                break;

            case R.id.create_account_bt:
                mListeners.openCreateAccountFragment();
                break;

            case R.id.login_bt:
                if (isMobileNoValid() && isPasswordValid()) {
                    // login(mobileNoEt.getText().toString(), passwordEt.getText().toString());
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
                break;
        }

    }

    private void sendOTPforgetPassword() {

        loadingDialog.show();

        forgetPasswordService = baseService.forgetPassword(mobileNoEt.getText().toString());

        forgetPasswordService.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                loadingDialog.dismiss();
                try {

                    String code = response.body().getString("code");
                    String message = response.body().getString("message");

                    if (code != null && code.equals("S00")) {
                        JSONObject details = response.body().getJSONObject("details");
                    }

                    showToast(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                }

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                loadingDialog.dismiss();
                showToast(t.getMessage());
            }
        });
    }


    /**
     * this method is used for call the api for login
     *
     * @param mobileNo user mobile number
     * @param password user given password
     *                 {@link #deviceId}
     *                 <p>
     *                 this three data is required parameter for api login {@link #baseService}
     */

    private void login(String mobileNo, String password) {

        loadingDialog.show();

        if (Util.getAndroidId(getActivity()) != null) {
            deviceId = Util.getAndroidId(getActivity());
        }
        loginService = baseService.login(mobileNo, password, deviceId);

        loadingDialog.show();
        loginService.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                loadingDialog.dismiss();

                try {

                    String code = response.body().getString("code");
                    String message = response.body().getString("message");

                    if (code != null && code.equals("S00")) {
                        JSONObject details = response.body().getJSONObject("details");
                        String sessionId = details.getString("sessionId");

                        //@TODO for test
                        String firstName = "deW";
                        String lastName = "laliT";
                        String password = "123456";
                        String gender = "male";
                        String mobileNo = "7415240624";
                        String email = "lkumar@msewa.com";

                        // set user info locally
                        // AppSharePref.setUserInfo(firstName, lastName, password, sessionId, gender, email, mobileNo, getActivity().getApplicationContext());
                    }

                    showToast(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast(getString(R.string.something_went_wrong));
                }

                // api Response
                // {  "code": "S00",  "message": "Login Successful", "status": "Success",  "details": { "sessionId": "1tr1z6scyp4et7bglhqljnpxm"} }

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                loadingDialog.dismiss();
                showToast(t.getMessage());
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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

        void openForgetPasswordFragment(String mobile);

        void openCreateAccountFragment();

        void openHomeFragment();
    }

    class MyTextWatcher implements TextWatcher {
        View v;

        public MyTextWatcher(View v) {
            this.v = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (v.getId()) {
                case R.id.contact_et:
                    isMobileNoValid();
                    break;
                case R.id.password_et:
                    isPasswordValid();
                    break;
            }

        }
    }

    private boolean isPasswordValid() {
        if (passwordEt.getText().toString().isEmpty()) {
            passwordTILayout.setError(getString(R.string.error_msg_enter_password));
            passwordEt.requestFocus();
            return false;
        } else if (passwordEt.getText().toString().length() < 6) {
            passwordTILayout.setError(getString(R.string.error_msg_six_digit_password));
            passwordEt.requestFocus();
            return false;
        } else {
            passwordTILayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isMobileNoValid() {

        if (mobileNoEt.getText().toString().isEmpty()) {
            mobileTILayout.setError(getString(R.string.error_msg_enter_mobile_no));
            mobileNoEt.requestFocus();
            return false;

        } else if (mobileNoEt.getText().toString().trim().length() < 10) {
            mobileNoEt.requestFocus();
            mobileTILayout.setError(getString(R.string.error_msg_10_digit_number));
            return false;
        } else {
            mobileTILayout.setErrorEnabled(false);
        }

        return true;
    }
}
