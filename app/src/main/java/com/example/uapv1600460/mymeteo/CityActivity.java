package com.example.uapv1600460.mymeteo;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    private ListView myListCity;
    static ArrayList<City> cities = new ArrayList<>();
    CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myListCity = (ListView) findViewById(R.id.listView);
        cities = City.getAllCities();

        cityAdapter = new CityAdapter(this,cities);

        myListCity.setAdapter(cityAdapter);

        /*
        Click on an item event
         */
        myListCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                City citySelect = (City) myListCity.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext() , CityListActivity.class);
                intent.putExtra("city-selected",citySelect);
                startActivity(intent);
            }
        });

        /*
        Click long on item event
         */
        myListCity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                City city = (City)myListCity.getItemAtPosition(i);
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(CityActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle("Supprimer");
                builder.setMessage("Voulez-vous supprimer la ville : " +
                        city.name);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cities.remove(position);
                        cityAdapter = new CityAdapter(CityActivity.this,cities);
                        myListCity.setAdapter(cityAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
