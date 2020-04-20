package com.example.smartfintest.model;

public class ProductItem {
    private int id;
    private String name;
    private String image;
    private String color;

    public ProductItem(Product product, String color) {
        this.id = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }
}
