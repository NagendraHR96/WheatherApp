package com.example.wheatherapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheatherapp.adapter.binder.ForecastDisplayAdapter;
import com.example.wheatherapp.databinding.ActivityWeatherDisplayBinding;
import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;

public class WeatherDisplayActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION = 23;
    WeatherViewModel weatherViewModel;
    ActivityWeatherDisplayBinding displayBinding;
    ProgressDialog progressDialog;
    LocationManager manager;
    RecyclerView forecast_recyclerview;
    ForecastDisplayAdapter forecastDisplayAdapter;


    private LocationListener currentLocation = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location.getLatitude() != 0 && location.getLongitude() != 0) {
                manager.removeUpdates(this);
                if (isNetworkConnectvity()) {
                    weatherViewModel.loadUrl(location.getLatitude(), location.getLongitude());
                } else {
                    if (progressDialog!=null){
                        progressDialog.dismiss();
                    }
                    new AlertDialog.Builder(WeatherDisplayActivity.this)
                            .setTitle(getResources().getString(R.string.app_name))
                            .setMessage("Please Connect internet")
                            .setCancelable(false)
                            .setPositiveButton("OK", null).show();
                }
            }
        }



        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_display);
        forecastDisplayAdapter=new ForecastDisplayAdapter();
        displayBinding.forecastRecyclerview.setAdapter(forecastDisplayAdapter);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherInfo().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(WeatherModel weatherModel) {

                progressDialog.dismiss();
                if (weatherModel==null){
                    new AlertDialog.Builder(WeatherDisplayActivity.this)
                            .setTitle(getResources().getString(R.string.app_name))
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    initLocation();
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }else {
                    displayBinding.setWeather(weatherModel);
                    forecastDisplayAdapter.setDailyForecastReport(weatherModel.getDaily());
                }

            }
        });
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        initLocation();
    }

    private void initLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_FINE_LOCATION);
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 10, currentLocation);
        progressDialog=new ProgressDialog(this,R.style.CustomProgress);
//        if( progressDialog.getWindow() != null)
//            progressDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        if( progressDialog.getWindow() != null) {
            progressDialog.getWindow().setGravity(Gravity.CENTER);
        }
        progressDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

     initLocation();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean isNetworkConnectvity() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }


}