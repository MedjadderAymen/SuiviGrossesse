package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class Rappel {

    @Json(name="id")
    private int id;

    @Json(name="periode")
    private String periode;

    @Json(name="detaills")
    private String detaills;

    public Rappel(int id, String periode, String detaills) {
        this.id = id;
        this.periode = periode;
        this.detaills = detaills;
    }

    public int getId() {
        return id;
    }

    public String getPeriode() {
        return periode;
    }

    public String getDetaills() {
        return detaills;
    }
}
