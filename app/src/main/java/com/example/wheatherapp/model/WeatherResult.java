package com.example.wheatherapp.model;

import android.widget.ImageView;


import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class WeatherResult  {
    private String id;
    private String main;
    private String description;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    @BindingAdapter({"bind:icon"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Picasso.get().load("http://openweathermap.org/img/w/"+url+".png").into(imageView); // replace with your fav image loading lib
        }
    }
}
