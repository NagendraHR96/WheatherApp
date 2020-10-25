package com.example.wheatherapp.repository;

import android.util.Log;

import com.example.wheatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicecallMaster {
    public void getData(double lati, double longi, final ResultHandler<WeatherModel>weatherModelResultHandler){
        ServerClient serverClient=new ServerClient();
        ApiCall apiCall=serverClient.retrofit.create(ApiCall.class);
        Call<WeatherModel> call=apiCall.getWeatherModelCall(lati,longi);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.code()==200){
                    WeatherModel weatherModel=response.body();

                    Log.e("data", String.valueOf(response.body()));

                    if (weatherModel!=null){

                        weatherModelResultHandler.setResult(weatherModel);
                    }

                }
            }
            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                weatherModelResultHandler.setResult(null);

            }
        });
    }
}
