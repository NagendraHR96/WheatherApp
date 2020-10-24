package com.example.wheatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wheatherapp.databinding.ActivityWeatherDisplayBinding;
import com.example.wheatherapp.model.WeatherModel;
import com.example.wheatherapp.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeatherDisplayActivity extends AppCompatActivity {
    TextView tempratureView,description_view,tomorrow_view,today_view,today_tempview,tmro_tempview;
    WeatherViewModel weatherViewModel;
    ActivityWeatherDisplayBinding displayBinding;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         displayBinding= DataBindingUtil.setContentView(this, R.layout.activity_weather_display);
        tempratureView=findViewById(R.id.temperature);
        description_view=findViewById(R.id.description);
        tomorrow_view=findViewById(R.id.tomorrow);
        today_view=findViewById(R.id.today);
        today_tempview=findViewById(R.id.tod_temp);
        tmro_tempview=findViewById(R.id.tom_temp);
        next=findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        weatherViewModel  = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherInfo().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(WeatherModel weatherModel) {
                double a=Double.parseDouble(String.valueOf(weatherModel.getCurrent().getTemp()));
                int temp=(int)(a-273);
                tempratureView.setText(String.valueOf(temp)+getResources().getString(R.string.degree));
                displayBinding.setWeather(weatherModel.getCurrent().getWeather().get(0));
                description_view.setText(weatherModel.getCurrent().getWeather().get(0).getDescription());

                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy", Locale.UK);
                description_view.setText(df2.format(new Date(weatherModel.getCurrent().getDt())));
                today_tempview.setText(weatherModel.getDaily().get(0).getTemp().getMax()+getResources().getString(R.string.degree)+" / "+weatherModel.getDaily().get(0).getTemp().getMin()+getResources().getString(R.string.degree));
                tomorrow_view.setText(weatherModel.getDaily().get(1).getTemp().getMax()+getResources().getString(R.string.degree)+" / "+weatherModel.getDaily().get(1).getTemp().getMin()+getResources().getString(R.string.degree));

            }
        });

    }
}