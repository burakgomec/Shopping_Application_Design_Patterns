package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;

import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.ProductObserver.User;

import java.util.ArrayList;

public interface IOrder { //Proxy Tasarım Deseni
    void createOrder(User user, Context context, String address, ArrayList<Product> purchasedProducts);
}
