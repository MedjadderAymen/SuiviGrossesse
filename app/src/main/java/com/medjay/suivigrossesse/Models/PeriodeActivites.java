package com.medjay.suivigrossesse.Models;

import java.util.List;

public class PeriodeActivites {

    private String periode;
    private List<Activite_sportiv> sportivList;

    public PeriodeActivites(String periode, List<Activite_sportiv> sportivList) {
        this.periode = periode;
        this.sportivList = sportivList;
    }

    public String getPeriode() {
        return periode;
    }

    public List<Activite_sportiv> getSportivList() {
        return sportivList;
    }
}
