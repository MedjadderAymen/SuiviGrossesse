package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Activite_sportiv implements Serializable {

    @Json(name="id")
    private int id ;

    @Json(name="nom")
    private String nom;

    @Json(name="description")
    private String description;

    @Json(name="image")
    private String image;

    @Json(name="pivot")
    private Pivot pivot;

    public Activite_sportiv(int id, String nom, String description, String image, Pivot pivot) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.pivot=pivot;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Pivot getPivot() {
        return pivot;
    }
}
