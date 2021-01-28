package com.burakgomec.shoppingapplication;

import com.burakgomec.shoppingapplication.PaymentStrategy.IPayment;

import java.util.ArrayList;

public class ShoppingCart {
    //Her bir kullanıcı aynı anda tek bir alışveriş sepetine sahip olabilecegi icin singleton tasarım deseni kullanılmıstır

    private Boolean paid = true;
    private final ArrayList<Product> selectedProducts; //Farklı sınıflardan erişmek amacıyla public erişim belirtiliyor
    private IPayment payment;
    private static ShoppingCart shoppingCart;

    private ShoppingCart(){
        selectedProducts = new ArrayList<>();
    } //Tek bir alısveris listesi yaratılarak uygulama boyunca bu liste kullanılmaktadır


    public static ShoppingCart getInstance(){
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

    public void pay(){
        int sum = calculatePrice();
        payment.pay(sum);
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
