package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;

import com.burakgomec.shoppingapplication.Observer.Product;
import com.burakgomec.shoppingapplication.Observer.User;

import java.util.ArrayList;

public interface IOrder {
    void createOrder(User user, Context context, String address, ArrayList<Product> purchasedProducts);
}
