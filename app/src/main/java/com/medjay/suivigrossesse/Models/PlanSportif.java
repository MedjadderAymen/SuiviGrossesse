package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.util.List;

public class PlanSportif {

    @Json(name="id")
    private int id ;

    @Json(name="titre")
    private String titre;

    @Json(name="description")
    private String description;

    @Json(name="tag")
    private String tag;

    @Json(name="journee")
    private List<Journee> journee;

    public PlanSportif(int id, String titre, String description, String tag, List<Journee> journee) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tag = tag;
        this.journee = journee;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public List<Journee> getJournee() {
        return journee;
    }
}
