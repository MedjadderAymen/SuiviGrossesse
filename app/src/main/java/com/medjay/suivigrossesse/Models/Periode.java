package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.util.List;

public class Periode {

    @Json(name="id")
    private int id ;

    @Json(name="periode")
    private String periode;

    @Json(name="duree")
    private String duree;

   @Json(name="activite_sportiv")
    private List<Activite_sportiv> activite_sportiv;

    public Periode(int id, String periode, String duree, List<Activite_sportiv> activite_sportiv) {
        this.id = id;
        this.periode = periode;
        this.duree = duree;
        this.activite_sportiv = activite_sportiv;
    }

    public int getId() {
        return id;
    }

    public String getPeriode() {
        return periode;
    }

    public String getDuree() {
        return duree;
    }

   public List<Activite_sportiv> getActivite_sportiv() {
        return activite_sportiv;
    }
}
