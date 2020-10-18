package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Food;
import com.medjay.suivigrossesse.Models.Ordonnance;
import com.medjay.suivigrossesse.Models.PeriodeAliments;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapter extends ArrayAdapter<PeriodeAliments> {

    Activity activity;
    int item_res;
    List<PeriodeAliments> list;

    public MenuAdapter(@NonNull Activity context, int resource, @NonNull List<PeriodeAliments> objects) {
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

        TextView periode= layout.findViewById(R.id.periode);
        periode.setText(list.get(position).getPeriode());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        RecyclerView recyclerView = layout.findViewById(R.id.exerc);

        recyclerView.setLayoutManager(linearLayoutManager);

        FoodRecyclerAdapter foodRecyclerAdapter=new FoodRecyclerAdapter(layout,activity,getContext(),list.get(position).getFoods());

        recyclerView.setAdapter(foodRecyclerAdapter);


        return layout;

    }
}
