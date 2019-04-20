package com.msewa.madmovegame.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * this class {@link AppSharePref} manages all prefrence of app.
 */

public class AppSharePref {

    private static final String PREF_KEY = "madMoveAppPref";
    private static final String ONE_TIME_INTRO = "oneTime";
    private static final String LOGIN = "isLoggedIn";


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
}
