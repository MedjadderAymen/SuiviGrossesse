package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.JourneeAlimentaire;
import com.medjay.suivigrossesse.Models.Ordonnance;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrdonanncesAdapter extends ArrayAdapter<Ordonnance> {

    Activity activity;
    int item_res;
    List<Ordonnance> list;


    public OrdonanncesAdapter(@NonNull Activity context, int resource, @NonNull List<Ordonnance> objects) {
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

        TextView days = layout.findViewById(R.id.date);
        days.setText(list.get(position).getCreated_at());

        return layout;

    }
}
