package com.example.uapv1600460.mymeteo;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

/**
 * Created by uapv1600460 on 21/09/16.
 */
public class ReloadListView extends AsyncTask<String, Void, List<City>> {

    @Override
    protected  void onPostExecute(List<City> result){
        super.onPostExecute(result);
        //Toast.makeText(getApplicationContext(),"Le traitement asynchrone est terminé",Toast.LENGTH_LONG).show();
    }

    @Override
    protected List<City> doInBackground(String... strings) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
