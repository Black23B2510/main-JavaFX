package com.example.demo5.Models;

public class customer {
    public int cusID;
    public String cusName, email, address, password;

    public customer(int cusID, String cusName, String email, String address, String password) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public customer(String cusName, String email, String address, String password) {
        this.cusName = cusName;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
