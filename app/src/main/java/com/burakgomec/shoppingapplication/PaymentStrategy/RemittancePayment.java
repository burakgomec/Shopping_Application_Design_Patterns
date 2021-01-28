package com.burakgomec.shoppingapplication.PaymentStrategy;

public class RemittancePayment implements IPayment{

    private String bankName;

    public RemittancePayment(String bankName){
        bankName = bankName;
    }

    @Override
    public void pay(Integer amount) {
        System.out.println(amount+"Tutar havale ile ödendi");
    }
}
