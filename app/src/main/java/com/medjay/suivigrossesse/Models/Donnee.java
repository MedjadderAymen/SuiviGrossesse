package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class Donnee {

    @Json(name="id")
    private int id ;

    @Json(name="mesure")
    private String mesure;

    public String getCreated_at() {
        return created_at;
    }

    @Json(name="valeur")
    private float valeur;

    @Json(name="created_at")
    private String created_at;

    public Donnee(int id, String mesure, float valeur, String created_at) {
        this.id = id;
        this.mesure = mesure;
        this.valeur = valeur;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getMesure() {
        return mesure;
    }

    public float getValeur() {
        return valeur;
    }
}
