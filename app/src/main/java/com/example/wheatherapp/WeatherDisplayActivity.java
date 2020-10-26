package com.example.wheatherapp;

import android.Manifest;
import android.app.AlertDialog;
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
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wheatherapp.adapter.ForecastDisplayAdapter;
import com.example.wheatherapp.databinding.ActivityWeatherDisplayBinding;
import com.example.wheatherapp.model.GetLocationByCitynameModel;
import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.viewmodel.WeatherViewModel;

public class WeatherDisplayActivity extends AppCompatActivity {
    private static final int REQUEST_FINE_LOCATION = 23;
    WeatherViewModel weatherViewModel;
    ActivityWeatherDisplayBinding displayBinding;
    ProgressDialog progressDialog;
    public LocationManager manager;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_xml, menu);

        MenuItem search_item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("City Name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if (!TextUtils.isEmpty(s)) {
                    weatherViewModel.loadLocation(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

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

        weatherViewModel.getLocationInfo().observe(this, new Observer<GetLocationByCitynameModel>() {
            @Override
            public void onChanged(GetLocationByCitynameModel getLocationByCitynameModel) {
                progressDialog.dismiss();
                if (getLocationByCitynameModel!=null){
                    if (progressDialog!=null){
                        progressDialog.show();
                    }
                    weatherViewModel.loadUrl(getLocationByCitynameModel.getCoord().getLat(),getLocationByCitynameModel.getCoord().getLat());

                }else {
                    Toast.makeText(WeatherDisplayActivity.this,"city not found",Toast.LENGTH_LONG).show();
                    if (progressDialog!=null && progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
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