package com.example.foodapp.AppFood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.R;
import com.example.foodapp.models.FoodAppDomian;

import java.util.ArrayList;

public class CardListAppFood extends RecyclerView.Adapter<CardListAppFood.ViewHolder>{
    ArrayList<FoodAppDomian> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemListener changeNumberItemListener;

    public CardListAppFood(ArrayList<FoodAppDomian> popularList, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listFoodSelected = popularList;
        managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public CardListAppFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new CardListAppFood.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListAppFood.ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$" + listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("$" + Math.round((listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));


        int drawbleReourceId = holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawbleReourceId).into(holder.popularImg);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView popularImg, plusItem, minusItem;
        ConstraintLayout mainlayout;
        TextView totalEachItem, num;
        ImageView addPopular;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            mainlayout = itemView.findViewById(R.id.idCart);
            popularImg = itemView.findViewById(R.id.imageView);
            addPopular = itemView.findViewById(R.id.btnAdd_popular);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plus_btn_Card);
            minusItem = itemView.findViewById(R.id.minus_btn_Card);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
