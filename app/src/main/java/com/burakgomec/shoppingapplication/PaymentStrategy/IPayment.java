package com.burakgomec.shoppingapplication.PaymentStrategy;

public interface IPayment { //Strategy Tasarım Deseni
    //Uygulayacak sınıflar tarafından override edilecektir

    boolean pay(Integer amount);
}
