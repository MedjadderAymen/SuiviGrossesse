package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.medjay.suivigrossesse.Models.Medicament;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.R;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MedicGrid extends BaseAdapter {

    private Context mContext;
    private Activity mActivity;
    private List<Medicament> mListMedic;


    public MedicGrid(Activity mActivity,Context context,List<Medicament> mListMedic){
        this.mContext=context;
        this.mActivity=mActivity;
        this.mListMedic=mListMedic;
    }

    @Override
    public int getCount() {
        return mListMedic.size();
    }

    public Object getItem(int position){
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View view1 = mActivity.getLayoutInflater().inflate(R.layout.medic_layout,null);

        TextView name = view1.findViewById(R.id.name);
        TextView voie = view1.findViewById(R.id.voie);
        TextView quand = view1.findViewById(R.id.quand);/*
        ImageView image = view1.findViewById(R.id.image);
        RelativeLayout to_white = view1.findViewById(R.id.to_white);*/

        name.setText(mListMedic.get(i).getNom());
        voie.setText("Voie "+mListMedic.get(i).getVoie());
        quand.setText(mListMedic.get(i).getQuand()+"/J");
/*
        image.setImageResource(R.drawable.medicatio);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        to_white.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimaryDark));*/


        return view1;
    }
}
