package com.example.foodapp.retrofit;

import com.example.foodapp.models.CategoryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();
}
