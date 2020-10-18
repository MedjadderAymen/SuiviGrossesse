package com.medjay.suivigrossesse.Models;

import java.io.Serializable;

public class Food implements Serializable {

    private String nom,image,description;
    private int id;

    public Food(String nom, String image, String description, int id) {
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.id = id;
    }

    public String getImage() {
        return image;
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
}
