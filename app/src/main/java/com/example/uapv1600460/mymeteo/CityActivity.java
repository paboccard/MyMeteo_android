package com.example.uapv1600460.mymeteo;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private ListView myListCity;
    static ArrayList<City> cities = new ArrayList<>();
    CityAdapter cityAdapter;
    SwipeRefreshLayout swipeRefreshCities;

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
                Intent intent = new Intent(getBaseContext(), AddCityActivity.class);
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        myListCity = (ListView) findViewById(R.id.listView);
        swipeRefreshCities = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        cities = City.getAllCities();

        cityAdapter = new CityAdapter(this, cities);
        myListCity.setAdapter(cityAdapter);

        /*
        Click on an item event
         */
        myListCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                City citySelect = (City) myListCity.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext(), CityListActivity.class);
                intent.putExtra("city-selected", citySelect);
                startActivity(intent);
            }
        });

        /*
        Click long on item event
         */
        myListCity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                City city = (City) myListCity.getItemAtPosition(i);
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(CityActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle("Supprimer");
                builder.setMessage("Voulez-vous supprimer la ville : " +
                        city.name);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cities.remove(position);
                        cityAdapter = new CityAdapter(CityActivity.this, cities);
                        myListCity.setAdapter(cityAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

                return true;
            }
        });

        swipeRefreshCities.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReloadListView reloadListView = new ReloadListView();
                reloadListView.execute();
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

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        myListCity = (ListView) findViewById(R.id.listView);

        cityAdapter = new CityAdapter(this, cities);
        myListCity.setAdapter(cityAdapter);
    }


    private class ReloadListView extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                JSONResponseHandler json = new JSONResponseHandler();
                String query;
                List<String> results;
                int index = 0;
                for (City acity: cities) {
                    query = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text='"+acity.name+",%20"+acity.country+"')&format=json";
                    results = json.handleResponse(new URL("https://query.yahooapis.com/v1/public/yql?q=" + query).openStream(),"");
                    cities.get(index).speedWind = results.get(0).toString();
                    cities.get(index).temperature = results.get(1).toString();
                    cities.get(index).pressur = results.get(2).toString();
                    cities.get(index).dateLastRelev = results.get(3).toString();
                    index++;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Traitement en cours...",Toast.LENGTH_LONG).show();
            swipeRefreshCities.setRefreshing(true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(getApplicationContext(),"Le traitement asynchrone est terminé",Toast.LENGTH_LONG).show();
            swipeRefreshCities.setRefreshing(false);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }
    }

    private void reloadCity(){
        try {
            JSONResponseHandler json = new JSONResponseHandler();
            String query;
            List<String> results;
            int index = 0;
            for (City acity: cities) {
                query = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text='"+acity.name+",%20"+acity.country+"')&format=json";
                results = json.handleResponse(new URL("https://query.yahooapis.com/v1/public/yql?q=" + query).openStream(),"");
                cities.get(index).speedWind = results.get(0).toString();
                cities.get(index).temperature = results.get(1).toString();
                cities.get(index).pressur = results.get(2).toString();
                cities.get(index).dateLastRelev = results.get(3).toString();
                index++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}