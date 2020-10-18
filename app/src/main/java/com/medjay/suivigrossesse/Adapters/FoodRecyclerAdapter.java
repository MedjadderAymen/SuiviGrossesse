package com.medjay.suivigrossesse.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medjay.suivigrossesse.DetailAliments;
import com.medjay.suivigrossesse.SportDetail;
import com.medjay.suivigrossesse.Models.Food;
import com.medjay.suivigrossesse.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.ViewHolder>{

    private List<Food> foodList;
    private Context mContext;
    private Activity mActivity;
    private View RootView;

    public FoodRecyclerAdapter(View RootView, Activity mActivity, Context mContext,List<Food> foodList) {
        this.foodList = foodList;
        this.mContext = mContext;
        this.mActivity= mActivity;
        this.RootView= RootView;

    }

    @NonNull
    @Override
    public FoodRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout, parent, false);

        return new FoodRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodRecyclerAdapter.ViewHolder holder, final int position) {

        holder.name.setText(foodList.get(position).getNom());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj",foodList.get(position));

                Intent intent = new Intent(mActivity, DetailAliments.class);
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

        Glide.with(mActivity)
                .load(foodList.get(position).getImage())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);

        }
    }

}
