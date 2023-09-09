package com.example.foodapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.AppFood.AppFood;
import com.example.foodapp.AppFood.CategoryAppFood;
import com.example.foodapp.AppFood.EmptyAdapter;
import com.example.foodapp.AppFood.PopularAppFood;
import com.example.foodapp.R;
import com.example.foodapp.SQLite.FoodAppDatabse;
import com.example.foodapp.adapters.AppFoodAdapter;
import com.example.foodapp.models.FoodAppDomian;
import com.example.foodapp.viewModel.HomeViewModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;
    //ActivityHomeBinding binding;

    FoodAppDatabse foodAppDatabse;
    RecyclerView lvFoodApp;
    ArrayList<AppFood> appFoodArrayList;
    AppFoodAdapter appFoodAdapter;

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        MenuOption();

    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.HomeBack);
        LinearLayout cartBtn = findViewById(R.id.IdPrice_Cart);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CartActivity.class));
            }
        });

    }

    private void LoadDataFoodApp() {
        String sqlSelect = "SELECT * FROM AppFood";
        Cursor dataAppFood = foodAppDatabse.QueryGetData(sqlSelect);
        while (dataAppFood.moveToNext()){
            int Id = dataAppFood.getInt(0);
            Uri HinhAnh = dataAppFood.getNotificationUri();
            String strTenMon = dataAppFood.getString(2);
            appFoodArrayList.add(new AppFood(Id,HinhAnh,strTenMon));
        }
        appFoodAdapter.notifyDataSetChanged();
    }

    private void InsertDataFoodApp() {
        String sqlIns1 = "insert into AppFood values(null,'https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/09_2022/quan-com-tam-o-ha-noi-.jpg','Cơm tấm')";
        foodAppDatabse.QueryData(sqlIns1);
    }

//    private void initView() {
//        binding.rcCategories.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        binding.rcCategories.setLayoutManager(layoutManager);
//    }
//
//    private void initData() {
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        homeViewModel.categoryModelMutableLiveData().observe(this,categoryModel -> {
//            if(categoryModel.isSuccess()){
//                CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult());
//                binding.rcCategories.setAdapter(adapter);
//            }
//        });

        private void recyclerViewCategory(){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewCategoryList = findViewById(R.id.rc_Categories);
            recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

            ArrayList<CategoryAppFood> categoryAppFoodArrayList = new ArrayList<>();
            categoryAppFoodArrayList.add(new CategoryAppFood("Pizza","piazzacl"));
            categoryAppFoodArrayList.add(new CategoryAppFood("Burger","cat_2"));
            categoryAppFoodArrayList.add(new CategoryAppFood("Hotdog","cat_3"));
            categoryAppFoodArrayList.add(new CategoryAppFood("Drink","cat_4"));
            categoryAppFoodArrayList.add(new CategoryAppFood("Donut","cat_5"));

            adapter = new EmptyAdapter(categoryAppFoodArrayList);
            recyclerViewCategoryList.setAdapter(adapter);
        }

        private void recyclerViewPopular(){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            recyclerViewPopularList = findViewById(R.id.rc_Popular);
            recyclerViewPopularList.setLayoutManager(linearLayoutManager);

            ArrayList<FoodAppDomian> foodList = new ArrayList<>();
            foodList.add(new FoodAppDomian("Pepperoni pizza", "pizza1","slices pepperoni ,mozzarella cheese, fresh oregano, ground black pepar, pizza sauce",13.0,5,20,1000));
            foodList.add(new FoodAppDomian("Chesse Burger", "burger","beef, Gouda Chesse, Special sauce, Lettuce, tomato",15.20,4,18,1500));
            foodList.add(new FoodAppDomian("Vegatable pizza", "pizza3","olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil",11.0,3,16,800));

            adapter2 = new PopularAppFood(foodList);
            recyclerViewPopularList.setAdapter(adapter2);
        }

        private void MenuOption(){
            LinearLayout CaiDatMenu = findViewById(R.id.CaiDatMenu);


        }


}


