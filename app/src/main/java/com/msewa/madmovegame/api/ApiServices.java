package com.msewa.madmovegame.api;

import com.google.gson.JsonObject;
import com.msewa.madmovegame.api.models.UserInfo;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("login/otp")
    Call<JSONObject> sendOTP(@Field("contactNo") String mobileNo);

    @FormUrlEncoded
    @POST("login/otpVerify")
    Call<JSONObject> verifyOTP(@Field("contactNo") String mobileNo, @Field("key") String otp);


    //@TODO add api parameter
    @FormUrlEncoded
    @POST("login/otp")
    Call<JSONObject> registerUser(@Field("contactNo") String mobileNo);

    @FormUrlEncoded
    @POST("/login/process")
    Call<JSONObject> login(@Field("username") String mobileNo, @Field("password") String password, @Field("deviceId") String deviceId);
}


