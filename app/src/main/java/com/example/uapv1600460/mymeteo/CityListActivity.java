package com.example.uapv1600460.mymeteo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class CityListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityview_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        Intent intent = getIntent();
        City serializableCity = (City) intent.getSerializableExtra("city-selected");


        this.setBackground(serializableCity.status);


        TextView tx_nameCity = (TextView) findViewById(R.id.city);

        //TextView tx_nameCountry = (TextView) findViewById(R.id.nameCountry);
        //TextView tx_wind = (TextView) findViewById(R.id.wind);
        TextView tx_temperature = (TextView) findViewById(R.id.temp);
        //TextView tx_pressur = (TextView) findViewById(R.id.pressur);
        TextView tx_date = (TextView) findViewById(R.id.date);
        TextView tx_detail = (TextView) findViewById(R.id.details_field);

        tx_nameCity.setText(serializableCity.name);
        //tx_nameCountry.setText(serializableCity.country);
        //tx_wind.setText(serializableCity.speedWind);
        tx_temperature.setText(serializableCity.temperature + " °C");
        //tx_pressur.setText(serializableCity.pressur + " hPa");
        tx_date.setText(serializableCity.dateLastRelev + " UTC");
        tx_detail.setText(serializableCity.status);
    }

    private void setBackground(String status){
        RelativeLayout iv = (RelativeLayout)findViewById(R.id.relLayContentCityList);
        iv.setBackgroundResource(R.drawable.background);
        TextView weather_icon = (TextView)findViewById(R.id.weather_icon);
        System.out.println("STATUS : " + status);
        if (status.equals("Mostly Cloudy")){
            weather_icon.setBackgroundResource(R.drawable.ic_mostly_cloudy);
        }
        else if (status.equals("Partly Cloudy")){
            weather_icon.setBackgroundResource(R.drawable.ic_mostly_cloudy);
        }
        else if (status.equals("Showers")){
            weather_icon.setBackgroundResource(R.drawable.ic_rain);
        }
        else if (status.equals("Cloudy")){
            weather_icon.setBackgroundResource(R.drawable.ic_cloud);
        }
        else if (status.equals("Sunny")){
            weather_icon.setBackgroundResource(R.drawable.ic_sunny);
        }
        else if (status.equals("Clear")){
            weather_icon.setBackgroundResource(R.drawable.ic_sunny);
        }
        else if (status.equals("Fair")){
            weather_icon.setBackgroundResource(R.drawable.ic_storm);
        }
        else if (status.equals("Hot")){
            weather_icon.setBackgroundResource(R.drawable.ic_sunny);
        }
        else if (status.equals("Snow")){
            weather_icon.setBackgroundResource(R.drawable.ic_snow);
        }
        else if (status.equals("Heavy Snow")){
            weather_icon.setBackgroundResource(R.drawable.ic_snow);
        }
        else if (status.equals("Rain")){
            weather_icon.setBackgroundResource(R.drawable.ic_rain);
        }

        //iv.setBackgroundResource(R.drawable.rain);
    }

}
