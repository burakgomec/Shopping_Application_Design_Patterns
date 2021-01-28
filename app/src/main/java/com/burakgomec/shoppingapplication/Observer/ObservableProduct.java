package com.burakgomec.shoppingapplication.Observer;

import android.content.Context;

import java.util.ArrayList;

public abstract class ObservableProduct {

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

    public void notifyObserver(Context context){
        for(IObserverUser observerUser : users){
            observerUser.sendNotification(message,context);
        }
    }

}
