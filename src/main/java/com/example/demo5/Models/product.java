package com.example.demo5.Models;

public class product {
    public int id;
    public int quantity;
    public Float price;
    public String name, NSX, image, description;

    public product(int id, String name, String NSX, String image, Float price, int quantity, String description) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.NSX = NSX;
        this.image = image;
        this.description = description;
    }

    public product(String name, String NSX, String image, Float price, int quantity, String description) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.NSX = NSX;
        this.image = image;
        this.description = description;
    }
}
