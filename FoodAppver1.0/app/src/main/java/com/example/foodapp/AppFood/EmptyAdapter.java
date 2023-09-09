package com.example.foodapp.AppFood;

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

import java.util.ArrayList;

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder>{

    ArrayList<CategoryAppFood> categoryAppFoods;

    public EmptyAdapter(ArrayList<CategoryAppFood> categoryAppFoods) {
        this.categoryAppFoods = categoryAppFoods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull EmptyAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryAppFoods.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl = "piazzacl";
                break;
            }
            case 1:{
                picUrl = "bugercl";
                break;
            }
            case 2:{
                picUrl = "hotdogcl";
                break;
            }
            case 3:{
                picUrl = "drinkcl";
                break;
            }
            case 4:{
                picUrl = "cat_5";
                break;
            }
        }
        int drawbleReourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawbleReourceId).into(holder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return categoryAppFoods.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImg;
        ConstraintLayout mainlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txtViewFood);
            categoryImg = itemView.findViewById(R.id.image_cate);
            mainlayout = itemView.findViewById(R.id.IdCatogory);
        }
    }
}
