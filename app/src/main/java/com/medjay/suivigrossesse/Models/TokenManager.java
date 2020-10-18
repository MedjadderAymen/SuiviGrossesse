package com.medjay.suivigrossesse.Models;

import android.content.SharedPreferences;


public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE=null;

    private TokenManager(SharedPreferences prefs){
        this.prefs=prefs;
        this.editor=prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if (INSTANCE==null){
            INSTANCE=new TokenManager(prefs);
        }

        return INSTANCE;
    }

    public void saveToken(AccessToken token){
        editor.putString("Access_Token",token.getToken()).commit();
    }

    public void deleteToken(){
        editor.remove("Access_Token").commit();
    }

    public AccessToken getToken(){
        AccessToken  token=new AccessToken();
        token.setToken(prefs.getString("Access_Token",null));

        return token;
    }

}
