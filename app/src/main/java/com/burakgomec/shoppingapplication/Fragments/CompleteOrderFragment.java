package com.burakgomec.shoppingapplication.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;
import com.burakgomec.shoppingapplication.OrderProxy.IOrder;
import com.burakgomec.shoppingapplication.OrderProxy.OrderProxy;
import com.burakgomec.shoppingapplication.PaymentStrategy.CreditCartPayment;
import com.burakgomec.shoppingapplication.PaymentStrategy.RemittancePayment;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.ShoppingCart;
import com.burakgomec.shoppingapplication.Observer.User;

public class CompleteOrderFragment extends Fragment {

    TextView textViewPrice;
    RadioButton creditCartPayment,remittancePayment;
    Button completePayment;

    ToastMessage toastMessage = new ToastMessage(new ToastMessageSender());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_complete_order,container,false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        creditCartPayment = view.findViewById(R.id.creditCartPayment);
        remittancePayment = view.findViewById(R.id.remittancePayment);
        completePayment = view.findViewById(R.id.completePayment);
        textViewPrice = view.findViewById(R.id.textViewPrice);

        textViewPrice.setText(ShoppingCart.getInstance().calculatePrice() + " TL");

        completePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completePayment(v);
            }
        });

        
    }


    private boolean checkControl(View view){
        if(creditCartPayment.isChecked()){
            ShoppingCart.getInstance().setPaymentMethod(new CreditCartPayment("Burak","49232","123"));
            return  true;
        }
        else if(remittancePayment.isChecked()){
            RemittancePayment remittancePayment = new RemittancePayment("Garanti");
            ShoppingCart.getInstance().setPaymentMethod(remittancePayment);
            return  true;
        }
        else{

            toastMessage.showMessage("Bir Ödeme Yöntemi Seçiniz",view.getContext());
            return false;

        }
    }

    private void completePayment(View view){
       boolean control = checkControl(view);

        if(control){ //Odeme yöntemi secildi ise islemler yapılıyor

            IOrder orderNew = new OrderProxy(); //Proxy üzerinden yeni bir siparis olusturuluyor
            orderNew.createOrder(User.getUser(), view.getContext(),User.getUser().getLocation(),ShoppingCart.getInstance().getSelectedProducts());

            if(ShoppingCart.getInstance().getSelectedProducts().size() == 0){ //Proxy tarafından islem tamamlandıysa tekrar Sepet Fragment'ine dön
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new ShoppingCartFragment()).commit(); //Sepet ana sayfasına geri dönülüyor
            }

        }



    }
}
