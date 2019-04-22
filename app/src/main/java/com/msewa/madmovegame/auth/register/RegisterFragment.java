package com.msewa.madmovegame.auth.register;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.msewa.madmovegame.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Button submitFormBt;
    private OnRegisterFragIntracationListener mListener;
    private TextInputEditText birthdayEt;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        submitFormBt = view.findViewById(R.id.submit_form_bt);
        birthdayEt = view.findViewById(R.id.birthday_et);
        submitFormBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickSubmitFormButton();
            }
        });

        birthdayEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayEt.setFocusable(false);
                openDateDialog();
            }
        });
        return view;
    }

    private void openDateDialog(){
        DatePickerDialog dialogDate = new DatePickerDialog(getActivity(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar userAge = new GregorianCalendar(year, (monthOfYear + 1), dayOfMonth);
                Calendar minAdultAge = new GregorianCalendar();
                minAdultAge.add(Calendar.YEAR, -18);
                String formattedDay, formattedMonth;
                if (minAdultAge.before(userAge)) {
                   // ilRegisterDob.setError("You must be at least 18 years old to Register");
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

//                   etRegisterDob.setText(String.valueOf(year) + "-"
//                            + String.valueOf(formattedMonth) + "-"
//                            + String.valueOf(formattedDay));
//
//                    ilRegisterDob.setErrorEnabled(false);
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

}
