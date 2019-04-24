package com.msewa.madmovegame.auth.register;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.api.ApiClient;
import com.msewa.madmovegame.api.ApiServices;
import com.msewa.madmovegame.api.models.UserInfo;
import com.msewa.madmovegame.auth.login.LoginActivity;
import com.msewa.madmovegame.auth.login.LoginFragment;
import com.msewa.madmovegame.common.LoadingDialog;
import com.msewa.madmovegame.termAndCondition.TermAndConditionActivity;
import com.msewa.madmovegame.util.AppSharePref;
import com.msewa.madmovegame.util.Constant;
import com.msewa.madmovegame.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    public static final String ARG_PARAM1 = "param1";
    private Button submitFormBt;
    private OnRegisterFragIntracationListener mListener;
    private TextInputEditText birthdayEt, mobileNoEt, passwordEt, firstNameEt, lastNameEt, emailEt;
    private TextInputLayout mobileTILayout, passwordTILayout, firstNameTILayout, lastNameTILayout, emailTILayout, birthdayTILayout;
    private RadioGroup genderRadioGroup;
    private TextView termAndconditionBt;
    private String mMobileNo, mGender;
    private Call<JSONObject> userService;
    private LoadingDialog loadingDialog;
    private ApiServices baseService;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RegisterFragment newInstance(String mobileNo) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mobileNo);
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseService = ApiClient.getInstance().getBaseService();
        mMobileNo = getArguments().getString(ARG_PARAM1);
        mGender = getString(R.string.male);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        submitFormBt = view.findViewById(R.id.submit_form_bt);
        mobileNoEt = view.findViewById(R.id.contact_et);
        passwordEt = view.findViewById(R.id.password_et);
        firstNameEt = view.findViewById(R.id.first_name_et);
        lastNameEt = view.findViewById(R.id.last_name_et);
        emailEt = view.findViewById(R.id.email_et);
        birthdayEt = view.findViewById(R.id.birthday_et);
        mobileTILayout = view.findViewById(R.id.contact_t_i_layout);
        passwordTILayout = view.findViewById(R.id.password_t_i_layout);
        firstNameTILayout = view.findViewById(R.id.first_name_t_i_layout);
        lastNameTILayout = view.findViewById(R.id.last_name_t_i_layout);
        emailTILayout = view.findViewById(R.id.email_t_i_layout);
        birthdayTILayout = view.findViewById(R.id.birthday_t_i_layout);
        genderRadioGroup = view.findViewById(R.id.radio_group_gender);
        termAndconditionBt = view.findViewById(R.id.term_n_condition_tv);
        termAndconditionBt.setPaintFlags(termAndconditionBt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        passwordEt.addTextChangedListener(new MyTextWatcher(passwordEt));
        firstNameEt.addTextChangedListener(new MyTextWatcher(firstNameEt));
        emailEt.addTextChangedListener(new MyTextWatcher(emailEt));
        birthdayEt.addTextChangedListener(new MyTextWatcher(birthdayEt));

        submitFormBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPasswordValid() || !isFirstNameValid() || !isEmailValid() || !isDOBValid()) {
                    return;
                } else {
                    //registerUser();

                    //@TODO for test
                    String firstName = firstNameEt.getText().toString();
                    String lastName = lastNameEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    String mobileNo = mobileNoEt.getText().toString();
                    String gender = mGender;
                    String email = emailEt.getText().toString();
                    String sessionId = "9420200-wjsdfjlsj000";
                    AppSharePref.setUserInfo(firstName, lastName, password, sessionId, gender, email, mobileNo, getActivity().getApplicationContext());
                    //
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            }
        });

        birthdayEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayEt.setFocusable(false);
                openDateDialog();
            }
        });


        // set click listener for Radio group to get selected option.
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.male:
                        mGender = getString(R.string.male);
                        break;

                    case R.id.female:
                        mGender = getString(R.string.female);
                        break;

                    case R.id.trasgender:
                        mGender = getString(R.string.trangender);
                        break;

                }
            }
        });

        termAndconditionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO open trem and condition web view in activity
                startActivity(new Intent(getActivity(), TermAndConditionActivity.class));
            }
        });

        // set data
        mobileNoEt.setText(mMobileNo);
        mobileNoEt.setFocusable(false);

        // init loading dialog
        loadingDialog = new LoadingDialog(getActivity());
        return view;
    }

    private void registerUser() {

        String firstName = firstNameEt.getText().toString();
        String lastName = lastNameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String mobileNo = mobileNoEt.getText().toString();
        String gender = mGender;
        String email = emailEt.getText().toString();

        userService = baseService.registerUser(firstName, lastName, email, gender, mobileNo, password, Constant.OS);

        loadingDialog.show();

        userService.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                loadingDialog.dismiss();

                try {

                    String code = response.body().getString("code");
                    String message = response.body().getString("message");
                    String status = response.body().getString("status");

                    if (code != null && code.equals("S00") && status.equals("Success")) {
                        mListener.onClickSubmitFormButton();
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


    private void openDateDialog() {
        DatePickerDialog dialogDate = new DatePickerDialog(getActivity(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar userAge = new GregorianCalendar(year, (monthOfYear + 1), dayOfMonth);
                Calendar minAdultAge = new GregorianCalendar();
                minAdultAge.add(Calendar.YEAR, -18);
                String formattedDay, formattedMonth;
                if (minAdultAge.before(userAge)) {
                    birthdayEt.setText("");
                    birthdayTILayout.setError(getString(R.string.error_msg_valid_18_years));
                    return;
                } else {
                    if (dayOfMonth < 10) {
                        formattedDay = "0" + dayOfMonth;
                    } else {
                        formattedDay = dayOfMonth + "";
                    }

                    if ((monthOfYear + 1) < 10) {
                        formattedMonth = "0" + String.valueOf(monthOfYear + 1);
                    } else {
                        formattedMonth = String.valueOf(monthOfYear + 1) + "";
                    }

                    birthdayEt.setText(String.valueOf(year) + "-"
                            + String.valueOf(formattedMonth) + "-"
                            + String.valueOf(formattedDay));

                    birthdayTILayout.setErrorEnabled(false);
                }
            }
        }, 1990, 00, 01);
        dialogDate.setButton(DatePickerDialog.BUTTON_POSITIVE, getActivity().getResources().getString(R.string.ok), dialogDate);
        dialogDate.setButton(DatePickerDialog.BUTTON_NEGATIVE, getActivity().getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDate.getDatePicker().setMaxDate(new Date().getTime());
        dialogDate.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragIntracationListener) {
            mListener = (OnRegisterFragIntracationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragIntracationListener");
        }
    }


    public interface OnRegisterFragIntracationListener {
        void onClickSubmitFormButton();
    }


    private class MyTextWatcher implements TextWatcher {
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
                case R.id.password_et:
                    isPasswordValid();
                    break;
                case R.id.first_name_et:
                    isFirstNameValid();
                    break;
                case R.id.email_et:
                    isEmailValid();
                    break;
                case R.id.birthday_et:
                    isDOBValid();
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

    private boolean isFirstNameValid() {
        if (firstNameEt.getText().toString().isEmpty()) {
            firstNameTILayout.setError(getString(R.string.error_msg_enter_first_name));
            firstNameEt.requestFocus();
            return false;
        } else if (firstNameEt.getText().toString().trim().length() < 3) {
            firstNameTILayout.setError(getString(R.string.error_msg_enter_valid_first_name));
            firstNameEt.requestFocus();
            return false;
        } else {
            firstNameTILayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isEmailValid() {

        if (emailEt.getText().toString().isEmpty()) {
            emailTILayout.setError(getString(R.string.error_msg_enter_email));
            emailEt.requestFocus();
            return false;
        } else if (!Util.validEmailChecker(emailEt.getText().toString())) {
            emailTILayout.setError(getString(R.string.error_msg_enter_valid_email));
            emailEt.requestFocus();
            return false;
        } else {
            emailTILayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isDOBValid() {

        if (birthdayEt.getText().toString().isEmpty()) {
            birthdayTILayout.setError(getString(R.string.error_msg_date_of_birth));
            birthdayEt.requestFocus();
        } else {
            birthdayTILayout.setErrorEnabled(false);
        }

        return true;
    }

}
