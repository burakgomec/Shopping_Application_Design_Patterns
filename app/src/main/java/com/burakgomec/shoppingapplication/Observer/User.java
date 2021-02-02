package com.burakgomec.shoppingapplication.Observer;

import android.content.Context;

import com.burakgomec.shoppingapplication.MessagesBridge.EmailMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.EmailMessageSender;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;

import java.util.ArrayList;

public class User implements IObserverUser { //Concrete Observer Class


    private final ArrayList<Product> myProductList = new ArrayList<>(); //Kullanıcının kendi ekledigi ilanları tutan liste

    private static User user; // Program içerisinde kullanıcıya her yerden erişmek için sabit bir user objesi kullanılıyor
    private  String name;
    private  String location;
    private final Integer ID;


    public User(Integer ID,String name,String location){
        this.ID=ID;
        this.name=name;
        this.location=location;
    }


    public static  User getUser(){
        if(user == null){ //Uygulama prototip oldugu ve veri tabanı olmadıgı icin ID ataması hardCode yapılmaktadır
            user = new User(2,"Burak Technology","Bursa");
        }
        return  user;
    }


    @Override
    public void sendNotification(String message, Context context) {
        ToastMessage toastNotification = new ToastMessage(new ToastMessageSender());
        toastNotification.showMessage(message,context); //Toast mesajı verilirken kullanılan bridge yapısı

        EmailMessage emailNotification = new EmailMessage(new EmailMessageSender());
        emailNotification.showMessage(message,context); //Kullanıcıya e-mail ile haber verildigi durum
    }


    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getMyProductList() {
        return myProductList;
    }
}
