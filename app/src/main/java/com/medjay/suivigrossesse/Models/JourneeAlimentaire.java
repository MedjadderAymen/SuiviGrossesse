package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.util.List;

public class JourneeAlimentaire {

    @Json(name="id")
    private int id ;

    @Json(name="jour")
    private String jour;

    @Json(name="aliment")
    private List<Aliment> aliment;

    @Json(name="plat")
    private List<Plat> plat;

    public JourneeAlimentaire(int id, String jour, List<Aliment> aliment, List<Plat> plat) {
        this.id = id;
        this.jour = jour;
        this.aliment = aliment;
        this.plat = plat;
    }

    public int getId() {
        return id;
    }

    public String getJour() {
        return jour;
    }

    public List<Aliment> getAliment() {
        return aliment;
    }

    public List<Plat> getPlat() {
        return plat;
    }
}
