package com.burakgomec.shoppingapplication;

import java.util.ArrayList;

public class Product {

    public static ArrayList<Product> productsList = new ArrayList<>();
    public static Boolean control = false;


    private String uri;
    private String name;
    private final User user;


    public Product(String uri,String name,User user){
        this.name = name;
        this.uri = uri;
        this.user = user;
    }



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

}
