package com.burakgomec.shoppingapplication.PaymentStrategy;

public class RemittancePayment implements IPayment{

    //Havale yöntemi ile ödeme

    private String bankName;

    public RemittancePayment(String bankName){
        bankName = bankName;
    }

    @Override
    public boolean pay(Integer amount) {

        if(true){ //Test icin true degeri atanmaktadır
            //İsletme hesabına belirtilen tutar yattı ise
            System.out.println(amount+"  TL Tutar havale ile ödendi");
            return true;
        }
        else{
            System.out.println("Ödeme yönteminde bir sorun oluştu!");
            return false;
        }

    }
}
