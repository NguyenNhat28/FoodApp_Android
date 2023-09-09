package com.example.foodapp.AppFood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.activities.ShowDetailActivity;
import com.example.foodapp.models.FoodAppDomian;

import java.util.ArrayList;

public class PopularAppFood extends RecyclerView.Adapter<PopularAppFood.ViewHolder>{
    ArrayList<FoodAppDomian> foodAppDomians;

    public PopularAppFood(ArrayList<FoodAppDomian> popularList) {
        this.foodAppDomians = popularList;
    }

    @NonNull
    @Override
    public PopularAppFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular,parent,false);
        return new PopularAppFood.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodAppDomians.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodAppDomians.get(position).getFee()));


        int drawbleReourceId = holder.itemView.getContext().getResources().getIdentifier(foodAppDomians.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawbleReourceId).into(holder.popularImg);

        holder.addPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object",foodAppDomians.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodAppDomians.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView popularImg;
        ConstraintLayout mainlayout;
        ImageView addPopular;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlePopular);
            fee = itemView.findViewById(R.id.fee);
            mainlayout = itemView.findViewById(R.id.idPopular);
            popularImg = itemView.findViewById(R.id.picPopular);
            addPopular = itemView.findViewById(R.id.btnAdd_popular);
        }
    }
}
