package com.msewa.madmovegame.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Util {


    public static String getAndroidId(Context context) {
        @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public static String getIMEI(Context context){
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds") String imei = mTelephonyMgr.getDeviceId();
        return imei;
    }


}
