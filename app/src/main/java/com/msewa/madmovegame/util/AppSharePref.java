package com.msewa.madmovegame.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.msewa.madmovegame.api.models.UserInfo;

/**
 * this class {@link AppSharePref} manages all prefrence of app.
 */

public class AppSharePref {

    private static final String PREF_KEY = "madMoveAppPref";
    private static final String ONE_TIME_INTRO = "oneTime";
    private static final String LOGIN = "isLoggedIn";

    // userInfo
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String SESSION_ID = "sessionId";
    public static final String PASSWORD = "password";
    public static final String MOBILE_NO = "mobileNo";


    private static SharedPreferences getAppSharePref(Context context) {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    /**
     * this method checks is the first time opening app after installing .
     *
     * @param context it should be application context  {@link Context#getApplicationContext()}
     */

    public static boolean isFirstTimeUser(Context context) {
        return getAppSharePref(context).getBoolean(ONE_TIME_INTRO, false);
    }

    /**
     * this method checks if user logged in or not .
     *
     * @param context it should be application context  {@link Context#getApplicationContext()}
     */
    public static boolean isUserLoggedIn(Context context) {
        return getAppSharePref(context).getBoolean(LOGIN, false);
    }


    public static void setUserLoggedIn(Context context, boolean value) {
        getAppSharePref(context).edit().putBoolean(LOGIN, value).apply();
    }

    public static void setFirstTimeUser(Context context, boolean value) {
        getAppSharePref(context).edit().putBoolean(ONE_TIME_INTRO, value).apply();
    }

    /**
     * this method  is used to set User information locally
     *
     * @param firstName users first Name
     * @param lastName  users last Name
     * @param password  users password
     * @param sessionId after login device session id
     * @param gender    users gender
     * @param email     users email
     * @param mobileNo  users mobile number
     * @param context   it should be application context  {@link Context#getApplicationContext()}
     */

    public static void setUserInfo(String firstName, String lastName, String password, String sessionId, String gender, String email, String mobileNo, Context context) {
        SharedPreferences.Editor edit = getAppSharePref(context).edit();
        edit.putString(FIRST_NAME, firstName);
        edit.putString(LAST_NAME, lastName);
        edit.putString(PASSWORD, password);
        edit.putString(SESSION_ID, sessionId);
        edit.putString(EMAIL, email);
        edit.putString(GENDER, gender);
        edit.putString(MOBILE_NO, mobileNo);
        edit.apply();
    }


    /**
     * this method is used to fetch locally stored  User Information as {@link UserInfo} jave object.
     */

    public static UserInfo getUserInfo(Context context) {
        String firstName = getAppSharePref(context).getString(FIRST_NAME, "");
        String lastName = getAppSharePref(context).getString(LAST_NAME, "");
        String password = getAppSharePref(context).getString(PASSWORD, "");
        String sessionId = getAppSharePref(context).getString(SESSION_ID, "");
        String email = getAppSharePref(context).getString(EMAIL, "");
        String gender = getAppSharePref(context).getString(GENDER, "");
        String mobileNo = getAppSharePref(context).getString(MOBILE_NO, "");

        return new UserInfo(firstName, lastName, email, gender, mobileNo, password, sessionId);
    }
}
