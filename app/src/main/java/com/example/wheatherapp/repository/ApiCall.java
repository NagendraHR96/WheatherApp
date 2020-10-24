package com.example.wheatherapp.repository;


import com.example.wheatherapp.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface   ApiCall {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    @GET("onecall?APPID=8781b41d87b90e570556df80bf2beabd")
    Call<WeatherModel> getWeatherModelCall(@Query("lat") double lat, @Query("lon") double lon);
}
