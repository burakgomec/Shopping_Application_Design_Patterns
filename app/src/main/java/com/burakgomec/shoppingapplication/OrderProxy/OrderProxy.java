package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;

import com.burakgomec.shoppingapplication.ShoppingCart;
import com.burakgomec.shoppingapplication.User;

public class OrderProxy implements IOrder { //Proxy Tasarım Deseni
    //Client tarafından direkt olarak siparis olusturulmadan once araya bir proxy(vekil) ataması yapılmaktadır

    private IOrder order;

    public OrderProxy() {
        order = new Order();
    }

    @Override
    public void createOrder(User user, Context context) {
        if(checkPaymentAndInformations()){
            order.createOrder(user,context);
        }
    }


    private boolean checkPaymentAndInformations(){
        //Ödeme sonucuna göre true,false yanıtı veren method
        //Canlı bir uygulamada bir çok koşul eklenebilir
        return ShoppingCart.getInstance().getPaid();
        //Ödeme islemi tamamlanma durumuna göre TRUE||FALSE dönmektedir
    }





}
