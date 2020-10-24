package com.example.wheatherapp.model;

import java.util.ArrayList;

public class CurrentModel {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private int humidity;
    private ArrayList<WeatherResult> weather;

    public ArrayList<WeatherResult> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherResult> weather) {
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
