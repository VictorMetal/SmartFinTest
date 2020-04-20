package com.example.smartfintest.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "basket_products")
public class BasketProduct {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float cost;
    private float weight;
    private float summary;
    private int basketId;

    public BasketProduct(int id, String name, float cost, float weight, float summary, int basketId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.summary = summary;
        this.basketId = basketId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public float getWeight() {
        return weight;
    }

    public float getSummary() {
        return summary;
    }

    public int getBasketId() {
        return basketId;
    }
}
