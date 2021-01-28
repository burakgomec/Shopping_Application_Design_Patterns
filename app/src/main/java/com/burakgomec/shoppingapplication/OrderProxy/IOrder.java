package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;

import com.burakgomec.shoppingapplication.User;

public interface IOrder {
    void createOrder(User user, Context context);
}
