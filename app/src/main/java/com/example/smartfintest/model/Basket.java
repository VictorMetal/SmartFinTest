package com.example.smartfintest.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "basket")
public class Basket {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean isBasketCompleted;

    public Basket(int id, boolean isBasketCompleted) {
        this.id = id;
        this.isBasketCompleted = isBasketCompleted;
    }

    public int getId() {
        return id;
    }

    public boolean isBasketCompleted() {
        return isBasketCompleted;
    }
}
