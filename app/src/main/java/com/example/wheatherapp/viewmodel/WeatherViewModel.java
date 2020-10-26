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

import com.example.wheatherapp.model.GetLocationByCitynameModel;
import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.repository.ApiCall;
import com.example.wheatherapp.repository.ResultHandler;
import com.example.wheatherapp.repository.ServicecallMaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends AndroidViewModel {
    MutableLiveData<WeatherModel> weatherInfo;
    MutableLiveData<GetLocationByCitynameModel> locationInfo;
    private MediatorLiveData<Location> myLocation = new MediatorLiveData<>();
    private ServicecallMaster servicecallMaster;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        servicecallMaster=new ServicecallMaster();
    }
    public LiveData<WeatherModel> getWeatherInfo(){
        if (weatherInfo==null){
            weatherInfo=new MutableLiveData<>();
        }
        if (locationInfo==null){
            locationInfo=new MutableLiveData<>();
        }

        return weatherInfo;
    }

    public LiveData<GetLocationByCitynameModel> getLocationInfo(){
        if (locationInfo==null){
            locationInfo=new MutableLiveData<>();
        }

        return locationInfo;
    }

    public void loadUrl(double latitude, double longitude){
        servicecallMaster.getData(latitude, longitude, new ResultHandler<WeatherModel>() {
            @Override
            public void setResult(WeatherModel data) {
                weatherInfo.setValue(data);
            }
        });
    }
    public void loadLocation(String name){
        servicecallMaster.getLocation(name, new ResultHandler<GetLocationByCitynameModel>() {
            @Override
            public void setResult(GetLocationByCitynameModel data) {
                locationInfo.setValue(data);
            }
        });
    }

}
