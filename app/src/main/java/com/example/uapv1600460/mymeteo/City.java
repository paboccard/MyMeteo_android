package com.example.uapv1600460.mymeteo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by uapv1600460 on 14/09/16.
 */
public class City {

    String name;
    String country;
    String dateLastRelev;
    float speedWind;
    String directionWind;
    float pressur;
    float temperatur;

    public City(String name, String country, String dateLastRelev, float pressur, float speedWind, float temperatur, String directionWind) {
        this.name = name;
        this.country = country;
        this.dateLastRelev = dateLastRelev;
        this.pressur = pressur;
        this.speedWind = speedWind;
        this.temperatur = temperatur;
        this.directionWind = directionWind;
    }

    public static ArrayList<City> getAllCities(){
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City("Brest","France", "14/09/2016",1003,50,21,"Est"));
        cities.add(new City("Marseille","France", "14/09/2016",1024,10,32,"Ouest"));
        cities.add(new City("Montreal","Canada", "14/09/2016",1023,0,21,"Sud"));
        cities.add(new City("Istanbul","Turquie", "14/09/2016",1000,23,26,"Nord"));
        cities.add(new City("Seoul","Korea", "14/09/2016",1002,10,22,"Est"));

        return cities;
    }
}
