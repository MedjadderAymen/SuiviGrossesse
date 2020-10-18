package com.medjay.suivigrossesse.Models;

import android.media.MediaPlayer;

import com.squareup.moshi.Json;

import java.util.List;

public class Patiente {

    @Json(name="id")
    private int id ;

    @Json(name="first_last_name")
    private String first_last_name ;

    @Json(name="state")
    private String state ;

    @Json(name="telephone_number")
    private String telephone_number;

    @Json(name="medecin")
    private Medecin medecin;

    @Json(name="redez_vous")
    private Rendez_vous redez_vous [];

    @Json(name="ordonnance")
    private List<Ordonnance> ordonnance;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }


    public Rendez_vous[] getRedez_vous() {
        return redez_vous;
    }

    public List<Ordonnance> getOrdonnance() {
        return ordonnance;
    }

    public Patiente(int id, String first_last_name, String state, String telephone_number, Medecin medecin, Rendez_vous[] redez_vous, List<Ordonnance> ordonnance) {
        this.id = id;
        this.first_last_name = first_last_name;
        this.state = state;
        this.telephone_number = telephone_number;
        this.medecin = medecin;
        this.redez_vous = redez_vous;
        this.ordonnance = ordonnance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_last_name() {
        return first_last_name;
    }

    public void setFirst_last_name(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
}
