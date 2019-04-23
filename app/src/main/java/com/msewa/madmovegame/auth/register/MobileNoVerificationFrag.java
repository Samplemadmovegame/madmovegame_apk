package com.msewa.madmovegame.auth.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
 * {@link MobileNoVerificationFrag.OnMobileNoVerificationFragInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MobileNoVerificationFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileNoVerificationFrag extends Fragment implements TextWatcher {

    public static final String TAG = "MobileNoVerifyFrag";
    private Button verifyBt;
    private TextInputEditText mobileNoEt;
    private TextInputLayout mobileTILayout;


    private OnMobileNoVerificationFragInteractionListener mListener;


    private ApiServices baseService;
    private LoadingDialog loadingDialog;

    public MobileNoVerificationFrag() {
        // Required empty public constructor
    }

    public static MobileNoVerificationFrag newInstance() {
        Bundle args = new Bundle();
        MobileNoVerificationFrag fragment = new MobileNoVerificationFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseService = ApiClient.getInstance().getBaseService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mobile_number_verification, container, false);
        verifyBt = view.findViewById(R.id.verify_button);
        mobileNoEt = view.findViewById(R.id.contact_et);
        mobileTILayout = view.findViewById(R.id.contact_t_i_layout);

        mobileNoEt.addTextChangedListener(this);
        verifyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMobileNoValid())

                    // sendOTP(mobileNoEt.getText().toString().trim());

                    //@TODO for test
                    mListener.onClickVerify(mobileNoEt.getText().toString());

            }
        });

        //init progress bar
        loadingDialog = new LoadingDialog(getActivity());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void sendOTP(String mobileNo) {
        loadingDialog.show();
        Call<JSONObject> jsonObjectCall = baseService.sendOTP(mobileNo);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                loadingDialog.dismiss();

                try {

                    String code = response.body().getString("code");
                    String message = response.body().getString("message");

                    if (code != null && code.equals("S00")) {
                        JSONObject details = response.body().getJSONObject("details");
                        mListener.onClickVerify(mobileNoEt.getText().toString().trim());
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMobileNoVerificationFragInteractionListener) {
            mListener = (OnMobileNoVerificationFragInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMobileNoVerificationFragInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        isMobileNoValid();
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
    public interface OnMobileNoVerificationFragInteractionListener {
        void onClickVerify(String mobileNo);
    }
}
