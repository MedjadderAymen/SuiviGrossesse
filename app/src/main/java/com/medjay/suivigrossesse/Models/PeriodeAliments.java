package com.medjay.suivigrossesse.Models;

import java.util.List;

public class PeriodeAliments {

    private String periode;
    private List<Food> foods;

    public PeriodeAliments(String periode, List<Food> foods) {
        this.periode = periode;
        this.foods = foods;
    }

    public String getPeriode() {
        return periode;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
