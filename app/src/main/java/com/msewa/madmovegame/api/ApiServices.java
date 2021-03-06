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
    @POST("Verify/Otp")
    Call<JSONObject> verifyOTP(@Field("contactNo") String mobileNo, @Field("key") String otp);


    @FormUrlEncoded
    @POST("register")
    Call<JSONObject> registerUser(@Field("firstName") String firstName, @Field("lastName") String lastName, @Field("email") String email, @Field("gender") String gender, @Field("contactNo") String contactNo, @Field("password") String password, @Field("operatingSystem") String os);

    @FormUrlEncoded
    @POST("login/process")
    Call<JSONObject> login(@Field("username") String mobileNo, @Field("password") String password, @Field("deviceId") String deviceId, @Field("operatingSystem") String os);

    @FormUrlEncoded
    @POST("ChangePassword")
    Call<JSONObject> changePassword(@Field("username") String mobileNo, @Field("password") String password, @Field("deviceId") String deviceId, @Field("sessionId") String sessionId, @Field("newPassword") String newPassword, @Field("confirmPassword") String confirmPassword);


    @FormUrlEncoded
    @POST("ForgetPassword")
    Call<JSONObject> forgetPassword(@Field("username") String mobileNo);


    @FormUrlEncoded
    @POST("VerifyForgotPassword")
    Call<JSONObject> verifyForgetPassword(@Field("username") String mobileNo, @Field("key") String otp, @Field("password") String password, @Field("confirmPassword") String confirmPassword);

}


