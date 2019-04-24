package com.msewa.madmovegame.auth.register;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
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
 * Activities that contain this fragment must implement the
 * {@link OtpFrag.OnOTPFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class OtpFrag extends Fragment {

    private static final String TAG = "OtpFrag";
    private static final String ARG_PARAM_1 = "param1";
    private static final String ARG_PARAM_2 = "param2";
    private OnOTPFragmentInteractionListener mListener;
    private Button submitBt;
    private TextView resendBt, mobileNoTv;
    private TextInputEditText otpEt;
    private TextInputLayout otpTILayout;
    private ApiServices baseService;
    private String mobileNo;
    private LoadingDialog loadingDialog;
    private Call<JSONObject> jsonObjectCall;
    private String countryCode;

    public OtpFrag() {
        // Required empty public constructor
    }

    public static OtpFrag newInstance(String mobileNo, String countryCode) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_1, mobileNo);
        args.putString(ARG_PARAM_2, countryCode);
        OtpFrag fragment = new OtpFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mobileNo = getArguments().getString(ARG_PARAM_1);
        countryCode = getArguments().getString(ARG_PARAM_2);
        baseService = ApiClient.getInstance().getBaseService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        submitBt = view.findViewById(R.id.submit_bt);
        otpEt = view.findViewById(R.id.otp_et);
        mobileNoTv = view.findViewById(R.id.mobileNo_tv);
        otpTILayout = view.findViewById(R.id.otp_t_i_layout);
        resendBt = view.findViewById(R.id.resend_otp_bt);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpEt.getText().toString().length() == 6)
                    sendVerifyOTP(otpEt.getText().toString());
                else
                    otpTILayout.setError(getString(R.string.error_msg_six_digit_otp));
            }
        });

        resendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(mobileNo);
            }
        });

        loadingDialog = new LoadingDialog(getActivity());

        mobileNoTv.setText(" "+countryCode + " " + mobileNo);

        return view;
    }

    private void sendVerifyOTP(String otp) {

        loadingDialog.show();

        jsonObjectCall = baseService.verifyOTP(mobileNo, otp);

        jsonObjectCall.enqueue(new Callback<JSONObject>() {
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

            }
        });

        //@TODO
        mListener.onClickSubmit(mobileNo, countryCode);
    }


    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    private void sendOTP(String mobileNo) {

        loadingDialog.show();

        Call<JSONObject> jsonObjectCall = baseService.sendOTP(mobileNo);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                loadingDialog.dismiss();
                JSONObject body = response.body();
                // Log.i(TAG, body.toString());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                loadingDialog.dismiss();
                // Log.i(TAG, t.getCause().toString());
            }

        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOTPFragmentInteractionListener) {
            mListener = (OnOTPFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOTPFragmentInteractionListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (jsonObjectCall != null) {
            jsonObjectCall.cancel();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnOTPFragmentInteractionListener {
        void onClickSubmit(String mobileNo, String countryCode);
    }
}
