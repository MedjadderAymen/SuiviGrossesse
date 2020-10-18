package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Conseil implements Serializable {

    @Json(name="id")
    private int id ;

    @Json(name="commentaire")
    private String commentaire;

    @Json(name="titre")
    private String titre;

    @Json(name="vue")
    private int vue;

    @Json(name="created_at")
    private String created_at;

    public Conseil(int id, String commentaire, int vue, String titre, String created_at) {
        this.id = id;
        this.commentaire = commentaire;
        this.vue = vue;
        this.titre=titre;
        this.created_at=created_at;
    }

    public int getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int isVue() {
        return vue;
    }

    public String getTitre() {
        return titre;
    }

    public String getCreated_at() {
        return created_at;
    }
}
