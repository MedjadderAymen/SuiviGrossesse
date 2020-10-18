package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class Plat {

    @Json(name="id")
    private int id ;

    @Json(name="nom")
    private String nom;

    @Json(name="image")
    private String image;

    @Json(name="description")
    private String description;

    @Json(name="pivot")
    private Pivot pivot;


    public Pivot getPivot() {
        return pivot;
    }


    public Plat(int id, String nom, String image, String description, Pivot pivot) {

        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.pivot=pivot;

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

}
