package com.example.smartfintest.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.smartfintest.SmartFinApp;
import com.example.smartfintest.db.AppDatabase;
import com.example.smartfintest.model.Country;
import com.example.smartfintest.model.Product;

import java.util.Arrays;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
        if (!SmartFinApp.getInstance().isDatabaseInitiated())
            initDatabase();
    }

    private void initDatabase() {
        new Thread(() -> {
            AppDatabase.getInstance(getApplication()).countryDao().addCountries(getInitCountries());
            AppDatabase.getInstance(getApplication()).productsDao().addProducts(getInitProducts());
            SmartFinApp.getInstance().setDatabaseInitiated();
        }).start();

    }

    private List<Product> getInitProducts() {
        return Arrays.asList(
                new Product(0, "Картофель", "https://e3.edimdoma.ru/data/ingredients/0000/1032/1032-ed4_wide.jpg?1487748037", 42.5F, 1),
                new Product(1, "Лук", "https://kuking.net/resize/197/600/w/uploads/content/1403258655.jpg", 36.2F, 1),
                new Product(2, "Морковь", "https://e0.edimdoma.ru/data/ingredients/0000/1043/1043-ed4_wide.jpg?1487748623", 50F, 0),
                new Product(3, "Петрушка", "https://images.lady.mail.ru/456347/", 304.5F, 0),
                new Product(4, "Свекла", "https://www.foodhouse.pro/assets/images/s/ovoshhi/svekla.png", 90F, 0),
                new Product(5, "Укроп", "https://ekozemledelie.ru/wp-content/uploads/Ukrop.jpg", 243F, 0),
                new Product(6, "Чеснок", "https://gazeta.a42.ru/uploads/daf/dafc6960-c22f-11e9-a03d-19e5336559ea.jpg", 40.7F, 1)
        );
    }

    private List<Country> getInitCountries() {
        return Arrays.asList(
                new Country(0, "Россия", "#0bbf29"),
                new Country(1, "Беларусь", "#eda51f")
        );
    }
}
