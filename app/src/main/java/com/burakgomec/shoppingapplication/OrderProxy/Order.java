package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;
import android.widget.Toast;

import com.burakgomec.shoppingapplication.User;

public class Order implements  IOrder{



    @Override
    public void createOrder(User user, Context context) {
        Toast.makeText(context,"Sayın "+user.getName()+" Siparişiniz Başarıyla Oluşturuldu",
                Toast.LENGTH_SHORT).show();
    }
}
