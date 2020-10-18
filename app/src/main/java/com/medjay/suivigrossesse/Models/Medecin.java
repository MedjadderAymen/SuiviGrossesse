package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class Medecin {

    @Json(name="id")
    private int id ;

    @Json(name="nom_prenom")
    private user user;

    public Medecin(int id, com.medjay.suivigrossesse.Models.user user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.medjay.suivigrossesse.Models.user getUser() {
        return user;
    }

    public void setUser(com.medjay.suivigrossesse.Models.user user) {
        this.user = user;
    }
}
