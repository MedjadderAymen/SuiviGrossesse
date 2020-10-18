package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.medjay.suivigrossesse.Exercices;
import com.medjay.suivigrossesse.Models.Activite_sportiv;
import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.Periode;
import com.medjay.suivigrossesse.Models.PeriodeActivites;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;
import com.medjay.suivigrossesse.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Programme_Adapter extends ArrayAdapter<PeriodeActivites> {

    Activity activity;
    int item_res;
    List<PeriodeActivites> list;

    WebService service;
    Call<Periode> call;


    public Programme_Adapter(@NonNull Activity context, int resource, @NonNull List<PeriodeActivites> objects) {
        super(context, resource, objects);

        activity=context;
        item_res=resource;
        list=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View layout=convertView;
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getLayoutInflater();
            layout=layoutInflater.inflate(item_res,parent,false);
        }

        TextView periode = layout.findViewById(R.id.periode);
        periode.setText(list.get(position).getPeriode());

        /*TextView duree = layout.findViewById(R.id.duree);
        duree.setText(list.get(position).getDuree());*/

        //**************activitées***************************

        /*service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getExercices(list.get(position).getId()+"");

        View finalLayout = layout;
        call.enqueue(new Callback<Periode>() {
           @Override
           public void onResponse(Call<Periode> call, Response<Periode> response) {

               if (response.code()==200){

                   List<Activite_sportiv> activite_sportivs=response.body().getActivite_sportiv();

                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

                   RecyclerView recyclerView = finalLayout.findViewById(R.id.exerc);

                   recyclerView.setLayoutManager(linearLayoutManager);

                   ExercicesRecyclerAdapter categoryRecyclerAdapter=new ExercicesRecyclerAdapter(finalLayout,activity,getContext(),activite_sportivs);

                   recyclerView.setAdapter(categoryRecyclerAdapter);


               }

           }

           @Override
           public void onFailure(Call<Periode> call, Throwable t) {
                Toasty.error(activity,t.getMessage(),Toasty.LENGTH_LONG).show();
           }
       });*/


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        RecyclerView recyclerView = layout.findViewById(R.id.exerc);

        recyclerView.setLayoutManager(linearLayoutManager);

        ExercicesRecyclerAdapter categoryRecyclerAdapter=new ExercicesRecyclerAdapter(layout,activity,getContext(),list.get(position).getSportivList());

        recyclerView.setAdapter(categoryRecyclerAdapter);


        //*************************************************************


        return layout;

    }

}
