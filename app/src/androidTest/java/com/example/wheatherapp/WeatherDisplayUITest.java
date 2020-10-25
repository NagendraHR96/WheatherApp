package com.example.wheatherapp;

import android.location.LocationManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class WeatherDisplayUITest {
    WeatherDisplayActivity weatherDisplayActivity;
    @Rule
    public ActivityTestRule<WeatherDisplayActivity> mActivityTestRule = new ActivityTestRule<>(WeatherDisplayActivity.class);

    @Test
    public void onCreate(){
        View view=weatherDisplayActivity.findViewById(R.id.forecast_recyclerview);
        assertNotNull(view);
        assertTrue(view instanceof RecyclerView);
    }
    @Test
    public void uiTest(){
        View temperature_text=weatherDisplayActivity.findViewById(R.id.temperature);
        assertNotNull(temperature_text);
        assertTrue(temperature_text instanceof TextView);

        View image=weatherDisplayActivity.findViewById(R.id.background_image);
        assertNotNull(image);
        assertTrue(image instanceof ImageView);

        View today_text=weatherDisplayActivity.findViewById(R.id.tod_temp);
        assertNotNull(today_text);
        assertTrue(today_text instanceof TextView);

        View tmro_text=weatherDisplayActivity.findViewById(R.id.tom_temp);
        assertNotNull(tmro_text);
        assertTrue(tmro_text instanceof TextView);

    }
    @Test
    public void networkConnect(){
        assertTrue(weatherDisplayActivity.isNetworkConnectvity());
    }
    @Test
    public void networknotConnect(){
        assertFalse(weatherDisplayActivity.isNetworkConnectvity());
    }

    @Test
    public void locationConnect(){
        assertEquals(LocationManager.NETWORK_PROVIDER,weatherDisplayActivity.manager.getProvider(LocationManager.NETWORK_PROVIDER).getName());
    }
    @Test
    public void adapterInstance(){
        View view=weatherDisplayActivity.findViewById(R.id.forecast_recyclerview);
        assertEquals(view.getClass(),RecyclerView.class);
    }

    @Before
    public void toStartTest(){
        weatherDisplayActivity=mActivityTestRule.getActivity();
    }
    @After
    public  void clear(){
        weatherDisplayActivity=null;
    }

}
