package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Conseil;
import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConsAdapter extends ArrayAdapter<Conseil> {

    Activity activity;
    int item_res;
    List<Conseil> list;


    public ConsAdapter(@NonNull Activity context, int resource, @NonNull List<Conseil> objects) {
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

        TextView content = layout.findViewById(R.id.content);
        content.setText(list.get(position).getCommentaire().substring(0,13)+"...");

        TextView titre = layout.findViewById(R.id.titre);
        titre.setText(" "+list.get(position).getTitre());

        TextView date = layout.findViewById(R.id.date);
        date.setText(list.get(position).getCreated_at());

        RelativeLayout vue=layout.findViewById(R.id.vue);

        if (list.get(position).isVue()==0){
            vue.setBackgroundColor(activity.getResources().getColor(R.color.oppacity_dark));
        }

        return layout;

    }
}

