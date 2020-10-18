package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Medicament implements Serializable {

    @Json(name="id")
    private int id ;

    @Json(name="nom")
    private String nom;

    @Json(name="voie")
    private String voie;

    @Json(name="quand")
    private String quand;

    public Medicament(int id, String nom, String voie, String quand) {
        this.id = id;
        this.nom = nom;
        this.voie = voie;
        this.quand = quand;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getVoie() {
        return voie;
    }

    public String getQuand() {
        return quand;
    }
}
