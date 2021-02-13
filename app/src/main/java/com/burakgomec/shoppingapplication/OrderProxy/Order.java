package com.burakgomec.shoppingapplication.OrderProxy;

import android.content.Context;
import android.widget.Toast;

import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;
import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.ProductObserver.User;

import java.util.ArrayList;
import java.util.Random;

public class Order implements  IOrder{

    @Override
    public void createOrder(User user, Context context, String address, ArrayList<Product> purchasedProducts) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000); //Siparis numarası, prototip oldugu icin random sayı olarak verilmektedir

        String info = "Sayın " + user.getName() +
                "  Siparişiniz Başarıyla Oluşturuldu. \nTakip Numaranız: "+randomNumber;

        ToastMessage toastMessage = new ToastMessage(new ToastMessageSender());
        toastMessage.showMessage(info,context);
        //Uygulama prototip olarak gelistirildigi icin adres, kullanıcı ve satın alınan urunler
        // parametre olarak alınmıstır. Kargo vb islemler buradan devam edebilmektedir
    }
}
