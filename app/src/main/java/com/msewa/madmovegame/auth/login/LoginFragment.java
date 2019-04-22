package com.msewa.madmovegame.auth.login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.msewa.madmovegame.R;
import com.msewa.madmovegame.api.ApiClient;
import com.msewa.madmovegame.api.ApiServices;
import com.msewa.madmovegame.common.LoadingDialog;
import com.msewa.madmovegame.util.AppAnimationUtil;
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
    private ImageButton passwordEyeBt;

    private LoginFragmentListeners mListeners;
    private String deviceId;
    private ApiServices baseService;
    private Call<JSONObject> loginService;
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
                else {
                    mobileNoEt.requestFocus();
                    mobileNoEt.setError(getString(R.string.error_msg_10_digit_number));
                }
                break;

            case R.id.create_account_bt:
                mListeners.openCreateAccountFragment();
                break;

            case R.id.login_bt:

                if (mobileNoEt.getText().toString().length() == 10) {
                    if (passwordEt.getText().toString().length() == 6)
                        login(mobileNoEt.getText().toString(), passwordEt.getText().toString());
                    else {
                        passwordEt.requestFocus();
                        passwordEt.setError(getString(R.string.error_msg_six_digit_password));
                    }

                } else {
                    mobileNoEt.requestFocus();
                    mobileNoEt.setError(getString(R.string.error_msg_10_digit_number));
                }
                break;

            case R.id.password_eye_bt:

                if (passwordEyeBt.isSelected()) {
                    passwordEyeBt.setSelected(false);
                    passwordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordEt.setSelection(passwordEt.length());
                    passwordEt.setTypeface(mobileNoEt.getTypeface());

                } else {
                    passwordEyeBt.setSelected(true);
                    passwordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordEt.setSelection(passwordEt.length());
                    passwordEt.setTypeface(mobileNoEt.getTypeface());

                }

                break;
        }

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

        void openForgetPasswordFragment();

        void openCreateAccountFragment();

        void openHomeFragment();
    }
}
