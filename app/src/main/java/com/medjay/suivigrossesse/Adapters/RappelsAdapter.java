package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.Rappel;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RappelsAdapter extends ArrayAdapter<Rappel> {

    Activity activity;
    int item_res;
    List<Rappel> list;


    public RappelsAdapter(@NonNull Activity context, int resource, @NonNull List<Rappel> objects) {
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

        TextView rappel = layout.findViewById(R.id.rappel_details);
        rappel.setText(list.get(position).getDetaills());

        return layout;

    }

}
