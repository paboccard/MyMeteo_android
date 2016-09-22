package com.example.uapv1600460.mymeteo;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddCityActivity extends AppCompatActivity {

    private EditText nameCity;
    private EditText nameCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameCity = (EditText) findViewById(R.id.text_name_city);
        nameCountry = (EditText) findViewById(R.id.text_country);

    }

    public void addCity(View view){
        City newCity = new City(nameCity.getText().toString(),nameCountry.getText().toString(),"","0","0","0");
        CityActivity.cities.add(newCity);
        finish();
    }

}
