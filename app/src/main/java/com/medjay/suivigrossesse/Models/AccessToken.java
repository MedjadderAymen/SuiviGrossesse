package com.medjay.suivigrossesse.Models;

import com.squareup.moshi.Json;

public class AccessToken {

    @Json(name="token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
