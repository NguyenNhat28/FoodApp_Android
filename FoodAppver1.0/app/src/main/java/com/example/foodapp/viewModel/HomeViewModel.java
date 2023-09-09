package com.example.foodapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.models.CategoryModel;
import com.example.foodapp.repository.CategoryRepository;

public class HomeViewModel extends ViewModel {
    private CategoryRepository categoryRepository;

    public HomeViewModel() {
        categoryRepository = new CategoryRepository();
    }
    public MutableLiveData<CategoryModel> categoryModelMutableLiveData(){
        return categoryRepository.getCategory();
    }
}
