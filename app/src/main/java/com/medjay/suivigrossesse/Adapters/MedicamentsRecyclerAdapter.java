package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Medicament;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicamentsRecyclerAdapter extends RecyclerView.Adapter<MedicamentsRecyclerAdapter.ViewHolder>{

    private List<Medicament> medicaments;
    private Context mContext;
    private Activity mActivity;
    private View RootView;

    public MedicamentsRecyclerAdapter(View RootView, Activity mActivity, Context mContext,List<Medicament> medicaments) {
        this.medicaments = medicaments;
        this.mContext = mContext;
        this.mActivity= mActivity;
        this.RootView= RootView;

    }

    @NonNull
    @Override
    public MedicamentsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medic_element, parent, false);

        return new MedicamentsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MedicamentsRecyclerAdapter.ViewHolder holder, final int position) {

        holder.name.setText(medicaments.get(position).getNom());
        holder.voie.setText(medicaments.get(position).getVoie());
        holder.quand.setText(medicaments.get(position).getQuand());
        holder.image.setImageResource(R.drawable.medicatio);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.to_white.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimaryDark));


    }

    @Override
    public int getItemCount() {
        return medicaments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,voie,quand;
        ImageView image;
        RelativeLayout to_white;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            voie=itemView.findViewById(R.id.voie);
            quand=itemView.findViewById(R.id.quand);
            image=itemView.findViewById(R.id.image);
            to_white=itemView.findViewById(R.id.to_white);

        }
    }

}
