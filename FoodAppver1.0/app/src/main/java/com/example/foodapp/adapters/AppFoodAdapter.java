package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.AppFood.AppFood;
import com.example.foodapp.R;

import java.util.List;

public class AppFoodAdapter extends BaseAdapter {
    private Context context;
    private int layoput;
    private List<AppFood> appFoodList;

    public AppFoodAdapter(Context context, int layoput, List<AppFood> appFoodList) {
        this.context = context;
        this.layoput = layoput;
        this.appFoodList = appFoodList;
    }

    @Override
    public int getCount() {
        return appFoodList.size();
    }

    @Override
    public Object getItem(int i) {
        return appFoodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return appFoodList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if(view  == null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoput,null);
            holder.id = (ConstraintLayout) view.findViewById(R.id.IdCatogory);
            holder.ImgFood = (ImageView) view .findViewById(R.id.image_cate);
            holder.txtName = (TextView) view .findViewById(R.id.txtViewFood);
            view.setTag(holder);
        }else{
            holder = (Viewholder) view.getTag();
        }
        AppFood appFood = appFoodList.get(i);
        ConstraintSet m = new ConstraintSet();
        holder.id.setConstraintSet(m);
        holder.ImgFood.setImageURI(appFood.getImg());
        holder.txtName.setText(String.valueOf(appFood.getName()));
        return view;
    }

    private class Viewholder{
        ConstraintLayout id;
        TextView txtName;
        ImageView ImgFood;
    }
}
