package com.burakgomec.shoppingapplication.PaymentStrategy;

import com.burakgomec.shoppingapplication.ShoppingCart;

public class CreditCartPayment implements IPayment {

    //Kredi kartı yöntemi ile ödeme

    private String name;
    private String cvv;
    private String cardNumber;


    public CreditCartPayment(String name, String cardNumber, String cvv) {
        this.name = name;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
    }


    @Override
    public boolean pay(Integer amount) {
        //Bu kısımda banka servisi ile iletisim kurulabilir, onay durumunda odeme degeri true atanmaktadır,
        //bankadan red gelmesi durumunda false atanıp hata verilmektedir.

        if(true){ //Test icin true degeri atanmaktadır
            System.out.println(amount+"  TL Tutar kredi kartı ile ödendi");
            return true;
        }
        else{
            System.out.println("Ödeme yönteminde bir sorun oluştu!");
            return false;
        }

    }
}
