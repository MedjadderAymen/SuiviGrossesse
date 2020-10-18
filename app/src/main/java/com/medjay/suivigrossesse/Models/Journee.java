package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.util.List;

public class Journee {

    @Json(name="id")
    private int id ;

    @Json(name="jour")
    private String jour;

    /*@Json(name="periode")
    private List<Periode> periode;*/

    @Json(name="activite_sportiv")
    private List<Activite_sportiv> activite_sportiv;

    public Journee(int id, String jour, List<Activite_sportiv> activite_sportiv) {
        this.id = id;
        this.jour = jour;
        this.activite_sportiv = activite_sportiv;
    }

    public int getId() {
        return id;
    }

    public String getJour() {
        return jour;
    }

    public List<Activite_sportiv> getActivite_sportivs() {
        return activite_sportiv;
    }
}
