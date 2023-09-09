package com.example.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.AppFood.CardListAppFood;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeetxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);

        managementCart = new ManagementCart(this );

        initView();
        initList();
        bottomNavigation();
        calculateCard();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.HomeBack);
        LinearLayout cartBtn = findViewById(R.id.IdPrice_Cart);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,HomeActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CardListAppFood(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percenTax = 0.02; // you can change this item for tax price
        double delivery = 10; // you can change this item you need price for delivery

        tax = Math.round((managementCart.getTotalFee() * percenTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeetxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeetxt = findViewById(R.id.txtPrice_items);
        taxTxt = findViewById(R.id.txtPrice_tax);
        deliveryTxt = findViewById(R.id.txtPrice_Delivery);
        totalTxt = findViewById(R.id.txtPrice_Total);
        recyclerViewList = findViewById(R.id.ViewCart);
        scrollView = findViewById(R.id.scrollView_Cart);
        emptyTxt = findViewById(R.id.emptyTxt);
    }
}