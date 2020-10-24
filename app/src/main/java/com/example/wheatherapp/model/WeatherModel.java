package com.example.wheatherapp.model;



import java.io.Serializable;
import java.util.ArrayList;

public class WeatherModel implements Serializable {
    private double lat;
    private double lon;
    private CurrentModel  current;
    private ArrayList<DailyForecastReport>  daily;
    private ArrayList<WeatherResult> weather;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public ArrayList<DailyForecastReport> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<DailyForecastReport> daily) {
        this.daily = daily;
    }

    public ArrayList<WeatherResult> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherResult> weather) {
        this.weather = weather;
    }

    public CurrentModel getCurrent() {
        return current;
    }

    public void setCurrent(CurrentModel current) {
        this.current = current;
    }


}
