package com.example.smartfintest.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartfintest.model.Basket;
import com.example.smartfintest.model.BasketProduct;

import java.util.List;

@Dao
public interface BasketDao {

    @Query("SELECT * FROM basket_products WHERE basketId = :id")
    LiveData<List<BasketProduct>> getBasket(int id);

    @Query("SELECT id FROM basket WHERE isBasketCompleted = 0 LIMIT 1")
    LiveData<Integer> getActiveBasketLiveData();

    @Query("SELECT id FROM basket WHERE isBasketCompleted = 0 LIMIT 1")
    Integer getActiveBasket();

    @Insert
    long createBasket(Basket basket);

    @Insert
    void addToBasket(BasketProduct product);

    @Update
    void updateBasket(Basket basket);

    @Delete
    void clearBasket(List<BasketProduct> products);
}
