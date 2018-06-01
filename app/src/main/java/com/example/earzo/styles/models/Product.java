package com.example.earzo.styles.models;

import com.orm.SugarRecord;

public class Product extends SugarRecord {
    //@SerializedName(("Id"))
    private String Name;
    private String ShortDescription;
    private String LongDescription;
    private int Category;
    private double Price;
    private int Quantity;
    private boolean Active;
    private String ScaledImage;
    private String FullImage;

    public Product() {
    }

    public Product(String name, String shortDescription, String longDescription, int category, double price, int quantity, boolean active, String scaledImage, String fullImage) {
        Name = name;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
        Category = category;
        Price = price;
        Quantity = quantity;
        Active = active;
        ScaledImage = scaledImage;
        FullImage = fullImage;
    }

    public String getName() {
        return Name;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public int getCategory() {
        return Category;
    }

    public double getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public boolean isActive() {
        return Active;
    }

    public String getScaledImage() {
        return ScaledImage;
    }

    public String getFullImage() {
        return FullImage;
    }
}
