package com.burakgomec.shoppingapplication;

import com.burakgomec.shoppingapplication.Observer.Product;
import com.burakgomec.shoppingapplication.PaymentStrategy.IPayment;

import java.util.ArrayList;

public class ShoppingCart { //Singleton Tasarım Deseni
    //Kullanıcı tek bir alışveriş sepetine sahip olabilecegi icin singleton tasarım deseni kullanılmıstır

    private final ArrayList<Product> selectedProducts; //Farklı sınıflardan erişmek amacıyla public erişim belirtiliyor
    private IPayment payment;
    private static ShoppingCart shoppingCart;

    private ShoppingCart(){
        selectedProducts = new ArrayList<>();
    } //Tek bir alısveris listesi yaratılarak uygulama boyunca bu liste kullanılmaktadır


    public static ShoppingCart getInstance(){ //Tek bir obje döndüren method(Single)
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setPaymentMethod(IPayment paymentMethod){
        payment = paymentMethod;
    }


    public void addProductToShoppingCart(Product selectedProduct){
        selectedProducts.add(selectedProduct);
    }
    public void clearShoppingCart(){
        selectedProducts.clear();
    }

    public void removeProductToShoppingCart(Product selectedProduct){
        selectedProducts.remove(selectedProduct);
    }


    public int calculatePrice(){
        int price = 0;
        for (Product product: selectedProducts) {
            price+= product.getPrice();
        }
        return price;
    }

    public boolean pay(){
        int sum = calculatePrice();
        return payment.pay(sum); //Ödeme sonucu true/false dönmektedir
    }


}
