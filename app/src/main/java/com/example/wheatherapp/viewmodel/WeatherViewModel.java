package com.example.wheatherapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.repository.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends AndroidViewModel {
    MutableLiveData<WeatherModel>weatherInfo;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<WeatherModel> getWeatherInfo(){
        if (weatherInfo==null){
            weatherInfo=new MutableLiveData<>();
            loadUrl();
        }
        return weatherInfo;
    }

    public void loadUrl(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ApiCall.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCall apiCall=retrofit.create(ApiCall.class);
        Call<WeatherModel> call=apiCall.getWeatherModelCall("13.842384","77.103338");
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
                Log.e("error", t.getMessage());
            }
        });
    }

}
