package com.example.smartfintest.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.smartfintest.model.Product;

import java.util.List;

@Dao
public interface ProductsDao {

    @Query("SELECT * FROM products ORDER BY name ASC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM products WHERE countryId = :id ORDER BY name ASC")
    LiveData<List<Product>> getProductsByCountry(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProducts(List<Product> products);
}
