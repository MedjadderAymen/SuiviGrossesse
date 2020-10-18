package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.util.List;

public class ProgrammeAlimentaire {

    @Json(name="id")
    private int id ;

    @Json(name="titre")
    private String titre;

    @Json(name="description")
    private String description;

    @Json(name="tag")
    private String tag;

    @Json(name="journee_alimentaire")
    private List<JourneeAlimentaire> journee_alimentaire;

    public ProgrammeAlimentaire(int id, String titre, String description, String tag, List<JourneeAlimentaire> journee_alimentaire) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tag = tag;
        this.journee_alimentaire = journee_alimentaire;
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

    public List<JourneeAlimentaire> getJournee_alimentaire() {
        return journee_alimentaire;
    }
}
