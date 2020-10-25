package com.example.wheatherapp.repository;

import android.util.Log;

import com.example.wheatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerClient {

    public Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(ApiCall.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
