package com.example.wheatherapp.viewmodel;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.repository.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends AndroidViewModel {
    MutableLiveData<WeatherModel> weatherInfo;
    private MediatorLiveData<Location> myLocation = new MediatorLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<WeatherModel> getWeatherInfo(){
        if (weatherInfo==null){
            weatherInfo=new MutableLiveData<>();
        }

        return weatherInfo;
    }

    public void loadUrl(double latitude, double longitude){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ApiCall.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCall apiCall=retrofit.create(ApiCall.class);
        Call<WeatherModel> call=apiCall.getWeatherModelCall(latitude,longitude);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.code()==200){
                    WeatherModel weatherModel=response.body();

                    Log.e("data", String.valueOf(response.body()));

                    if (weatherModel!=null){
                        weatherInfo.setValue(weatherModel);
                    }

                }
            }
            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                weatherInfo.setValue(null);

            }
        });
    }

    public int convertFtoC(double temp) {
        return (int) temp-273;
    }
}
