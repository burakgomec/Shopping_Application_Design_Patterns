package com.burakgomec.shoppingapplication.ProductObserver;

import android.content.Context;

import java.util.ArrayList;

public abstract class ObservableProduct { //Gözlemlenen ilanlar

    private final ArrayList<IObserverUser> users;

    protected String message;

    public  ObservableProduct(){
        users = new ArrayList<>();
    }

    public void addObserver(IObserverUser observerUser){
        users.add(observerUser);
    }

    public void removeObserver(IObserverUser observerUser){
        users.remove(observerUser);
    }
    //Kullanıcı takip ettigi ürünü takip etmeye bırakmak isterse bu metot kullanılabilir

    public void notifyObserver(Context context){
        for(IObserverUser observerUser : users){
            observerUser.sendNotification(message,context);
        }
    }

}
