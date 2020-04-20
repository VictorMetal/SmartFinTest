package com.example.smartfintest.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;
    private String image;
    private float cost;
    private int countryId;

    public Product(int id, String name, String image, float cost, int countryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cost = cost;
        this.countryId = countryId;
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

    public float getCost() {
        return cost;
    }

    public int getCountryId() {
        return countryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeFloat(this.cost);
        dest.writeInt(this.countryId);
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
        this.cost = in.readFloat();
        this.countryId = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
