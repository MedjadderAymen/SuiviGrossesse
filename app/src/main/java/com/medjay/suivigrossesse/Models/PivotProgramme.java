package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class PivotProgramme {

    @Json(name="id")
    private int id;

    public PivotProgramme(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
