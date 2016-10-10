package com.example.uapv1600460.mymeteo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by uapv1600460 on 14/09/16.
 */
public class CityAdapter extends BaseAdapter {

    private List<City> lCity;
    private Context mContext;
    private LayoutInflater mInflater;

    public CityAdapter(Context mContext, List<City> lCity) {
        this.mContext = mContext;
        this.lCity = lCity;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return lCity.size();
    }

    public void setlCity(List<City> lCity) {
        this.lCity = lCity;
    }

    @Override
    public Object getItem(int i) {
        return lCity.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layoutItem;

        if (view == null){
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.city_layout, viewGroup, false);
        }else{
            layoutItem = (LinearLayout) view;
        }

        TextView tx_nameCity = (TextView) layoutItem.findViewById(R.id.nameCity);
        ImageView img_weather = (ImageView)layoutItem.findViewById(R.id.img_weather);

        setIcon(lCity.get(i).status,img_weather);

        tx_nameCity.setText(lCity.get(i).name + " (" + lCity.get(i).country + ")\t" + lCity.get(i).temperature + "°C");

        return layoutItem;
    }

    private void setIcon(String status, ImageView img_weather){

        if (status != null) {
            if (status.equals("Mostly Cloudy")){
                img_weather.setBackgroundResource(R.drawable.ic_mostly_cloudy);
            }
            else if (status.equals("Partly Cloudy")){
                img_weather.setBackgroundResource(R.drawable.ic_mostly_cloudy);
            }
            else if (status.equals("Showers")){
                img_weather.setBackgroundResource(R.drawable.ic_rain);
            }
            else if (status.equals("Cloudy")){
                img_weather.setBackgroundResource(R.drawable.ic_cloud);
            }
            else if (status.equals("Sunny")){
                img_weather.setBackgroundResource(R.drawable.ic_sunny);
            }
            else if (status.equals("Clear")){
                img_weather.setBackgroundResource(R.drawable.ic_sunny);
            }
            else if (status.equals("Fair")){
                img_weather.setBackgroundResource(R.drawable.ic_storm);
            }
            else if (status.equals("Hot")){
                img_weather.setBackgroundResource(R.drawable.ic_sunny);
            }
            else if (status.equals("Snow")){
                img_weather.setBackgroundResource(R.drawable.ic_snow);
            }
            else if (status.equals("Heavy Snow")){
                img_weather.setBackgroundResource(R.drawable.ic_snow);
            }
            else if (status.equals("Rain")){
                img_weather.setBackgroundResource(R.drawable.ic_rain);
            }
        }

        //iv.setBackgroundResource(R.drawable.rain);
    }
}
