package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class Ordonnance implements Serializable {

    @Json(name="id")
    private int id ;

    @Json(name="date_debut")
    private String date_debut;

    @Json(name="created_at")
    private String created_at;

    @Json(name="date_fin")
    private String date_fin;

    @Json(name="medicament")
    private List<Medicament> medicament;

    public Ordonnance(int id, String date_debut, String date_fin, List<Medicament> medicament, String created_at) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.medicament = medicament;
        this.created_at=created_at;
    }

    public int getId() {
        return id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public List<Medicament> getMedicament() {
        return medicament;
    }

    public String getCreated_at() {
        return created_at;
    }
}
