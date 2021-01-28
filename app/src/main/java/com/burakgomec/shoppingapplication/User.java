package com.burakgomec.shoppingapplication;

import android.content.Context;
import android.widget.Toast;

import com.burakgomec.shoppingapplication.Observer.IObserverUser;

public class User implements IObserverUser { //Singleton Design Pattern

    //Uygulamada uyelik sistemi olmadıgı icin kullanıcı sadece 1 adet yaratılmaktadır.


    //Singleton tasarım deseni ile olusturulan tek bir obje program boyunca kullanılmaktadır.
    private static User user;
    private  String name;
    private  String location;

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


    public static  User getUser(){
        if(user == null){
            user = new User("Burak Technology","Bursa");
        }
        return  user;
    }

    private User(String name,String location){
        this.name=name;
        this.location=location;
    }


    @Override
    public void sendNotification(String message, Context context) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
