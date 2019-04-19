package com.msewa.madmovegame.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("login/otp")
    Call<JsonObject> sendOTP(@Field("contactNo") String mobileNo);

    @FormUrlEncoded
    @POST("login/otpVerify")
    Call<JsonObject> verifyOTP(@Field("contactNo") String mobileNo, @Field("key") String otp);

    @POST("/login/process")
    Call<JsonObject> login(@Field("username") String mobileNo, @Field("password") String password, @Field("deviceId") String deviceId);
}


