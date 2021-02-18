package com.burakgomec.shoppingapplication.ProductObserver;

import android.content.Context;

import com.burakgomec.shoppingapplication.Fragments.ShoppingCartFragment;

import java.util.ArrayList;

public class Product extends ObservableProduct {  //Dinlenen ürün sınıfından kalıtım yapılıyor
    //Product sınıfı her bir ilanı temsil etmektedir


    private static final ArrayList<Product> productsList = new ArrayList<>();

    private Integer id;
    private String detail;
    private String uri;
    private String name;
    private Integer price;
    private final User user;

    public Product(Integer ID,String uri, String name, User user, Integer price,String detail){
        this.id = ID;
        this.name = name;
        this.uri = uri;
        this.user = user;
        this.price = price;
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price, Context context) {
        if(!this.price.equals(price)){ //Ürünün fiyatı degisir ise fiyatı güncelle ve bilgilendir

            this.price = price;
            this.message = name + " İsimli Ürünün Fiyatı  " + price + " Oldu!";

            super.notifyObserver(context); //Ana Class da yer alan dinleyicilere haber veren method çağırılıyor
            //ve toast mesajı vermek icin context argümanı veriliyor
        }

    }

    public static ArrayList<Product> getProductsList() {
        return productsList;
    }//Uygulama boyunca güncel ürünler listesine erismek icin tanımlama yapılıyor(Public-Static)


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
