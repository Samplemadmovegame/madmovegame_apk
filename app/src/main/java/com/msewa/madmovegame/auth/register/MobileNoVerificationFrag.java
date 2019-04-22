package com.msewa.madmovegame.auth.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class MobileNoVerificationFrag extends Fragment {

    public static final String TAG = "MobileNoVerifyFrag";
    private Button verifyBt;
    private EditText mobileNoEt;


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
        mobileNoEt = view.findViewById(R.id.mobile_no_ed);
        verifyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobileNoEt.getText().toString().length() == 10)
                    sendOTP(mobileNoEt.getText().toString());
                else
                    mobileNoEt.setError(getString(R.string.error_msg_10_digit_number));
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
