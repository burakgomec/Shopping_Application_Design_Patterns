package com.burakgomec.shoppingapplication;

public class User { //Singleton Design Pattern

    //Uygulamada uyelik sistemi olmadıgı icin kullanıcı sadece 1 adet yaratılmaktadır.
    //Singleton tasarım deseni ile olusturulan tek bir obje program boyunca kullanılmaktadır.
    private static User user;

    private final String name;
    private final String location;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
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



}
