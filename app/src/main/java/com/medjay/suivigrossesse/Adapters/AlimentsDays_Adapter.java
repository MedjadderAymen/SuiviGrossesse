package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.JourneeAlimentaire;
import com.medjay.suivigrossesse.R;

import java.util.List;


public class AlimentsDays_Adapter extends ArrayAdapter<JourneeAlimentaire> {

    Activity activity;
    int item_res;
    List<JourneeAlimentaire> list;


    public AlimentsDays_Adapter(@NonNull Activity context, int resource, @NonNull List<JourneeAlimentaire> objects) {
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

        TextView days = layout.findViewById(R.id.day);
        days.setText(list.get(position).getJour());

        ImageView image= layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.nutrients);

        return layout;

    }
}
