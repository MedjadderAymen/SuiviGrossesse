package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class user {

    @Json(name="id")
    private int id ;

    @Json(name="nom_prenom")
    private String nom_prenom ;

    public user(int id, String nom_prenom) {
        this.id = id;
        this.nom_prenom = nom_prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }
}
