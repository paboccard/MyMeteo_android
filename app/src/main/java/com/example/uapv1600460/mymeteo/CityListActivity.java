package com.example.uapv1600460.mymeteo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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

        TextView tx_nameCity = (TextView) findViewById(R.id.nameCity);

        TextView tx_nameCountry = (TextView) findViewById(R.id.nameCountry);
        TextView tx_wind = (TextView) findViewById(R.id.wind);
        TextView tx_temperature = (TextView) findViewById(R.id.temperature);
        TextView tx_pressur = (TextView) findViewById(R.id.pressur);
        TextView tx_date = (TextView) findViewById(R.id.date);

        tx_nameCity.setText(serializableCity.name);
        tx_nameCountry.setText(serializableCity.country);
        tx_wind.setText(serializableCity.speedWind);
        tx_temperature.setText(serializableCity.temperature + " °C");
        tx_pressur.setText(serializableCity.pressur + " hPa");
        tx_date.setText(serializableCity.dateLastRelev + " UTC");
    }

}
