package com.example.wheatherapp.model;

import java.util.ArrayList;

public class DailyForecastReport {
    private int dt;
    private int sunrise;
    private int sunset;
    private TemperatureObject temp;
    private ArrayList<WeatherResult> weather;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
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
}
