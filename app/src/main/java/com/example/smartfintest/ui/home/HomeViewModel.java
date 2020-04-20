package com.example.smartfintest.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.smartfintest.db.AppDatabase;
import com.example.smartfintest.model.Country;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<List<Country>> mTabs = Transformations.map(AppDatabase.getInstance(getApplication()).countryDao().getCountries(), this::transformToTabs);

    LiveData<List<Country>> getTabs() {
        return mTabs;
    }

    private List<Country> transformToTabs(List<Country> countries) {
        countries.add(0, new Country(-1, "Все", null));
        return countries;
    }
}
