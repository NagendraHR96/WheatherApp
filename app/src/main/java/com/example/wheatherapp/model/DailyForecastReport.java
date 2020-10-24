package com.example.wheatherapp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyForecastReport {
    private long dt;
    private int sunrise;
    private int sunset;
    private TemperatureObject temp;
    private ArrayList<WeatherResult> weather;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public TemperatureObject getTemp() {
        return temp;
    }

    public void setTemp(TemperatureObject temp) {
        this.temp = temp;
    }

    public ArrayList<WeatherResult> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherResult> weather) {
        this.weather = weather;
    }
    public String getDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(getDt()*1000));
    }
}
