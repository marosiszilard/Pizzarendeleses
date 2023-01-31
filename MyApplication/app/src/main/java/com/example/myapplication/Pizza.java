package com.example.myapplication;

public class Pizza {
    private String name;
    private double price;
    private String description;
    private int imageResourceId;

    public Pizza(String name, double price, String description, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {  return price ; }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}


