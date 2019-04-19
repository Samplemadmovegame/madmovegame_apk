package com.msewa.madmovegame.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient myInstance;

    private static String BASE_URL = ApiUrl.URL_MAIN;

    private Retrofit retrofit;

    private final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build();


    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ApiClient getInstance() {
        if (myInstance == null) {
            myInstance = new ApiClient();
        }
        return myInstance;
    }


    public ApiServices getBaseService() {
        return retrofit.create(ApiServices.class);
    }


}