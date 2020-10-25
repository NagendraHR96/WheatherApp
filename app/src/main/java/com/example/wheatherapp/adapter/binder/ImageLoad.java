package com.example.wheatherapp.adapter.binder;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.wheatherapp.R;
import com.squareup.picasso.Picasso;

public class ImageLoad {
    @BindingAdapter({"bind:icon"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            Picasso.get().load(R.drawable.ic_launcher_background).into(imageView);
        } else {
            Picasso.get().load("http://openweathermap.org/img/w/"+url+".png").into(imageView); // replace with your fav image loading lib
        }
    }

}
