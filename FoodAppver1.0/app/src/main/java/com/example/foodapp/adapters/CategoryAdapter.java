package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.databinding.ItemCategoryBinding;
import com.example.foodapp.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    List<Category> list;

    public CategoryAdapter(List<Category> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(list.get(position));
        Glide.with(holder.itemView).load(list.get(position).getCategoryThumb()).into(holder.binding.imageCate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemCategoryBinding binding;

        public MyViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setBinding(Category category){
            binding.setCategory(category);
        }
    }
}
