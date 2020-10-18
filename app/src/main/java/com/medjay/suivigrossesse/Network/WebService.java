package com.medjay.suivigrossesse.Network;

import com.medjay.suivigrossesse.Models.Activite_sportiv;
import com.medjay.suivigrossesse.Models.Conseil;
import com.medjay.suivigrossesse.Models.Donnee;
import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.JourneeAlimentaire;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.Periode;
import com.medjay.suivigrossesse.Models.PlanSportif;
import com.medjay.suivigrossesse.Models.ProgrammeAlimentaire;
import com.medjay.suivigrossesse.Models.Rappel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebService {

    @POST("Patiente/login")
    @FormUrlEncoded
    Call<Patiente> Login(@Field("matricule") String matricule);

    @POST("Patiente/AddToken")
    @FormUrlEncoded
    Call<String> AddToken(@Field("token") String token,@Field("matricule") String matricule);

    @POST("Patiente/Patient")
    @FormUrlEncoded
    Call<Patiente> Patient(@Field("token") String token);

    @POST("Patiente/edit")
    @FormUrlEncoded
    Call<Patiente> editPhone(@Field("token") String token,@Field("phone") String phone);

    @POST("Patiente/Programmes")
    @FormUrlEncoded
    Call<List<PlanSportif>> getProgrammes(@Field("token") String token);

    @POST("Patiente/Programmes/Day")
    @FormUrlEncoded
    Call<Journee> getDay(@Field("id") String id);


    @POST("Patiente/Programmes/Exercices")
    @FormUrlEncoded
    Call<Periode> getExercices(@Field("id") String id);

    @POST("Patiente/ProgrammesAliments")
    @FormUrlEncoded
    Call<List<ProgrammeAlimentaire>> getProgrammesAliments(@Field("token") String token);

    @POST("Patiente/Ordonnances")
    @FormUrlEncoded
    Call<Patiente> getOrdonnaces(@Field("token") String token);

    @POST("Patiente/ProgrammesAliments/Day")
    @FormUrlEncoded
    Call<JourneeAlimentaire> getMenu(@Field("id") String id);

    @POST("Patiente/FeedBackSport")
    @FormUrlEncoded
    Call<String> setFeedBackSport(@Field("token") String token, @Field("commentaire") String commentaire, @Field("reaction") String reaction);

    @POST("Patiente/FeedBackRegime")
    @FormUrlEncoded
    Call<String> setFeedBackRegime(@Field("token") String token, @Field("commentaire") String commentaire, @Field("reaction") String reaction);

    @POST("Patiente/Conseils")
    @FormUrlEncoded
    Call<List<Conseil>> getConseils(@Field("token") String token);

    @POST("Patiente/Conseils/update")
    @FormUrlEncoded
    Call<String> setConseils(@Field("id") String id);

    @POST("Patiente/Mesures")
    @FormUrlEncoded
    Call<List<Donnee>> getMesure(@Field("token") String token, @Field("mesure") String mesure);

    @POST("Patiente/Mesures/create")
    @FormUrlEncoded
    Call<String> setMesure(@Field("token") String token, @Field("mesure") String mesure, @Field("valeur") float valeur);

    @POST("Patiente/Rappels")
    @FormUrlEncoded
    Call<List<Rappel>> getRappels(@Field("token") String token, @Field("periode") String periode);

}
