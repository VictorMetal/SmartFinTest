package com.example.smartfintest.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smartfintest.db.dao.BasketDao;
import com.example.smartfintest.db.dao.CountryDao;
import com.example.smartfintest.db.dao.ProductsDao;
import com.example.smartfintest.model.Basket;
import com.example.smartfintest.model.BasketProduct;
import com.example.smartfintest.model.Country;
import com.example.smartfintest.model.Product;

@Database(entities = {Product.class, Country.class, BasketProduct.class, Basket.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    public abstract ProductsDao productsDao();

    public abstract BasketDao basketDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}