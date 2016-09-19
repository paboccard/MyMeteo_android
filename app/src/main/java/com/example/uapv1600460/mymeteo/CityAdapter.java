package com.example.uapv1600460.mymeteo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        tx_nameCity.setText(lCity.get(i).name + " (" + lCity.get(i).country + ")");

        return layoutItem;
    }
}
