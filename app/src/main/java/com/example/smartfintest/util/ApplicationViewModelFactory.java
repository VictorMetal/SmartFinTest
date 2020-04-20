package com.example.smartfintest.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartfintest.ui.basket.BasketViewModel;
import com.example.smartfintest.ui.dialog.PickerViewModel;
import com.example.smartfintest.ui.main.MainViewModel;
import com.example.smartfintest.ui.products.ProductsViewModel;

public class ApplicationViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;

    public ApplicationViewModelFactory(Application application) {
        mApplication = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(mApplication);
        if (modelClass.isAssignableFrom(ProductsViewModel.class))
            return (T) new ProductsViewModel(mApplication);
        if (modelClass.isAssignableFrom(BasketViewModel.class))
            return (T) new BasketViewModel(mApplication);
        if (modelClass.isAssignableFrom(PickerViewModel.class)) {
            return (T) new PickerViewModel(mApplication);
        }
        throw new IllegalStateException("");
    }
}
