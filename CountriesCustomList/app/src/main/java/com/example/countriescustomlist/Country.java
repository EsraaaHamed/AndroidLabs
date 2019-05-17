package com.example.countriescustomlist;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Country implements Serializable {
    String rank;
    String name;
    String population;
    String imageurl;



    public Country() {
    }

    public Country(String rank, String name, String population, String imageurl) {
        this.rank = rank;
        this.name = name;
        this.population = population;
        this.imageurl = imageurl;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
