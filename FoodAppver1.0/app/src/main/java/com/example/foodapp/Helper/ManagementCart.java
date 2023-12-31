package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.models.FoodAppDomian;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodAppDomian item){
        ArrayList<FoodAppDomian> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < listFood.size();i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = 1;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodAppDomian> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<FoodAppDomian> listfood, int position, ChangeNumberItemListener changeNumberItemListener){
        if(listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemListener.changed();
    }

    public void plusNumberFood(ArrayList<FoodAppDomian> listfood, int position, ChangeNumberItemListener changeNumberItemListener){
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodAppDomian> listfood2 = getListCart();
        double fee = 0;
        for(int i = 0; i<listfood2.size();i++){
            fee = fee+(listfood2.get(i).getFee()*listfood2.get(i).getNumberInCart());
        }
        return fee;
    }
}
