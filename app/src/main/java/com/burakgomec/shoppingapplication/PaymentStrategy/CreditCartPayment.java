package com.burakgomec.shoppingapplication.PaymentStrategy;

public class CreditCartPayment implements IPayment { //Kredi kartı yöntemi ile ödeme


    private String name; //Değişkenler prototip uygulama oldugu icin eklenmistir
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

        if(true){ //Test icin true degeri atanmaktadır ve bilgiler konsola yazdırılmıstır
            System.out.println(amount+"  TL Tutar kredi kartı ile ödendi");
            return true;
        }
        else{
            System.out.println("Ödeme yönteminde bir sorun oluştu!");
            return false;
        }

    }
}
