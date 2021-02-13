package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;

import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;
import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.ShoppingCart;
import com.burakgomec.shoppingapplication.ProductObserver.User;

import java.util.ArrayList;

public class OrderProxy implements IOrder { //Proxy Tasarım Deseni
    //Client tarafından direkt olarak siparis olusturulmadan once araya bir proxy(vekil) ataması yapılmaktadır
    //Vekil Sınıfı

    private final IOrder order;

    public OrderProxy() {
        order = new Order();
    }

    @Override
    public void createOrder(User user, Context context, String address, ArrayList<Product> purchasedProducts) {
        if(ShoppingCart.getInstance().pay()){ //Sepet tutarı ödendi ise

            order.createOrder(user,context,address,purchasedProducts); //Gerçek siparisi olustur

            Product.getProductsList().removeAll(ShoppingCart.getInstance().getSelectedProducts()); //Satılan ürünler, ürünler listesinden kaldırılıyor
            ShoppingCart.getInstance().getSelectedProducts().clear(); //Sepet listesi temizleniyor
        }
        else{ //Sepet tutarının ödemesinde sorun yasandi ise
            ToastMessage toastMessage = new ToastMessage(new ToastMessageSender());
            toastMessage.showMessage("Ödeme İşleminizi Reddedildi",context);
        }
    }
}
