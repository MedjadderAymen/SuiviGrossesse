package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class Rendez_vous {

    @Json(name="id")
    private int id ;

    @Json(name="rendez_vous_date")
    private String rendez_vous_date ;

    public Rendez_vous(int id, String rendez_vous_date) {
        this.id = id;
        this.rendez_vous_date = rendez_vous_date;
    }

    public int getId() {
        return id;
    }

    public String getRendez_vous_date() {
        return rendez_vous_date;
    }
}
