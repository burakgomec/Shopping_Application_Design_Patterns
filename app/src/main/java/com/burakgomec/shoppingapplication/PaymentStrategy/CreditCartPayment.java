package com.burakgomec.shoppingapplication.PaymentStrategy;

public class CreditCartPayment implements IPayment {


    private String name;
    private String cvv;
    private String cardNumber;


    public CreditCartPayment(String name, String cardNumber, String cvv) {
        this.name = name;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
    }


    @Override
    public void pay(Integer amount) {

        System.out.println(amount+"Tutar kredi kartı ile ödendi");

    }
}
