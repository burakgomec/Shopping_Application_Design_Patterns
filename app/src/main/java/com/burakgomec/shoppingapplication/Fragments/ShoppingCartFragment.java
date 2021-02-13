package com.burakgomec.shoppingapplication.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.RecyclerAdapters.ShoppingCartRecyclerAdapter;
import com.burakgomec.shoppingapplication.ShoppingCart;

public class ShoppingCartFragment extends Fragment {

    public static ShoppingCartRecyclerAdapter shoppingCartRecyclerAdapter;
    RecyclerView recyclerView;
    Boolean itemControl;
    static TextView totalPrice;
    Button completeOrder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(ShoppingCart.getInstance().getSelectedProducts().size() == 0){
            //Sepette ürün yok ise mesaj gösteren bir view göster ve control'a false ata
            itemControl = false;
            return inflater.inflate(R.layout.error_shopping_cart,container,false);
        }
        else{
            itemControl = true; //Sepette ürün var ise true ata
            return inflater.inflate(R.layout.fragment_shopping_cart,container,false);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalPrice = view.findViewById(R.id.textViewPrice);
        completeOrder = view.findViewById(R.id.completeOrder);
        if(itemControl){ //True ise adaptör ile view bagla
            recyclerView = view.findViewById(R.id.recyclerViewShoppingCart);
            shoppingCartRecyclerAdapter = new ShoppingCartRecyclerAdapter(view.getContext(),totalPrice);
            shoppingCartRecyclerAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(shoppingCartRecyclerAdapter);

            calculateTotalPrice();
            completeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    completeOrder(v);
                }
            });

        }
    }

    private void completeOrder(View view){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new CompleteOrderFragment())
                .addToBackStack(null)
                .commit();
    }

    public static void calculateTotalPrice(){
        if(ShoppingCart.getInstance().getSelectedProducts().size() != 0 ){
            int sum = 0 ;
            for (Product product: ShoppingCart.getInstance().getSelectedProducts()) {
                sum += product.getPrice();
            }
            totalPrice.setText(String.valueOf(sum));
        }
    }

}
