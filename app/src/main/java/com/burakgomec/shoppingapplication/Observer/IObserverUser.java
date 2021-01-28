package com.burakgomec.shoppingapplication.Observer;

import android.content.Context;

public interface IObserverUser { //Gözlemci Kullanıcılar

    void sendNotification(String message, Context context);

}
