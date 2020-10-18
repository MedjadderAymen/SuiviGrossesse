package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Pivot implements Serializable {

    @Json(name="id")
    private int id;

    @Json(name="periode")
    private String periode;

    @Json(name="duree")
    private String duree;

    public Pivot(int id, String periode, String duree) {
        this.id = id;
        this.periode = periode;
        this.duree=duree;
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
}
