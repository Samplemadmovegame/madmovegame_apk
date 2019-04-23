package com.msewa.madmovegame.auth.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.api.ApiClient;
import com.msewa.madmovegame.api.ApiServices;
import com.msewa.madmovegame.common.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends Fragment {

    public static final String TAG = "ForgetPwdFragment";
    public static final String ARGS_PARAM = "param1";
    private TextInputEditText otpEt, passwordEt, conformPasswordEt;
    private TextInputLayout otpTILayout, passwordTILayout, conformTILayout;
    private Button chageBt, resendOtpBt;
    private TextView msg1, mobileNoTv, msg2;
    private String mobileNo;
    private ApiServices baseService;
    private Call<JSONObject> sentOTPService;
    private Call<JSONObject> verifyForgotPasswordService;
    private LoadingDialog loadingDialog;

    public ForgetPwdFragment() {
        // Required empty public constructor
    }

    public static ForgetPwdFragment newInstance(String mobileNo) {
        Bundle args = new Bundle();
        args.putString(ARGS_PARAM, mobileNo);
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String param = getArguments().getString(ARGS_PARAM);
        if (param != null && !param.equals("")) {
            mobileNo = param;
        }

        baseService = ApiClient.getInstance().getBaseService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_pwd, container, false);
        msg1 = view.findViewById(R.id.msg1);
        mobileNoTv = view.findViewById(R.id.mobileNo_tv);
        msg2 = view.findViewById(R.id.msg2);
        otpEt = view.findViewById(R.id.otp_et);
        passwordEt = view.findViewById(R.id.password_et);
        conformPasswordEt = view.findViewById(R.id.conform_password_et);
        otpTILayout = view.findViewById(R.id.otp_t_i_layout);
        passwordTILayout = view.findViewById(R.id.password_t_i_layout);
        conformTILayout = view.findViewById(R.id.conform_password_t_i_layout);
        chageBt = view.findViewById(R.id.change_bt);
        resendOtpBt = view.findViewById(R.id.resend_otp_bt);

        // set default msg after sent OTP.
        msg1.setText(getString(R.string.msg_sent_otp_1));
        mobileNoTv.setText(" "+mobileNo);
        msg2.setText(getString(R.string.msg_sent_otp_2));

        // set click listener for buttons
        chageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOTPValid() && isPasswordValid() && isConformPasswordValid())
                    verifyForgotPassword();
            }
        });

        resendOtpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOTP();
            }
        });

        //init loading layout
        loadingDialog = new LoadingDialog(getActivity());
        return view;
    }


    private void resendOTP() {
        loadingDialog.show();
        sentOTPService = baseService.sendOTP(mobileNo);

        sentOTPService.enqueue(new Callback<JSONObject>() {
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

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    private void verifyForgotPassword() {
        loadingDialog.show();
        verifyForgotPasswordService = baseService.verifyForgetPassword(mobileNo, otpEt.getText().toString(), passwordEt.getText().toString(), conformPasswordEt.getText().toString());

        verifyForgotPasswordService.enqueue(new Callback<JSONObject>() {
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

    private boolean isConformPasswordValid() {
        if (conformPasswordEt.getText().toString().isEmpty()) {
            conformTILayout.setError(getString(R.string.error_msg_enter_password));
            conformPasswordEt.requestFocus();
            return false;
        } else if (!passwordEt.getText().toString().trim().equals(conformPasswordEt.getText().toString().trim())) {
            conformTILayout.setError(getString(R.string.error_msg_password_not_matched));
            conformPasswordEt.requestFocus();
            return false;
        } else {
            conformTILayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isOTPValid() {

        if (otpEt.getText().toString().isEmpty()) {
            otpTILayout.setError(getString(R.string.error_msg_enter_otp));
            otpEt.requestFocus();
            return false;

        } else if (otpEt.getText().toString().trim().length() < 6) {
            otpEt.requestFocus();
            otpTILayout.setError(getString(R.string.error_msg_six_digit_otp));
            return false;
        } else {
            otpTILayout.setErrorEnabled(false);
        }

        return true;
    }

}
