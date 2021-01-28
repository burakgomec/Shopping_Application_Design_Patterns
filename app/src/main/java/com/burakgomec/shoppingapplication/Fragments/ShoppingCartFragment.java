package com.burakgomec.shoppingapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.RecyclerAdapters.ShoppingCartRecyclerAdapter;
import com.burakgomec.shoppingapplication.ShoppingCart;

public class ShoppingCartFragment extends Fragment {

    RecyclerView recyclerView;
    Boolean itemControl;

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
        if(itemControl){ //True ise adaptör ile view bagla
            recyclerView = view.findViewById(R.id.recyclerViewShoppingCart);
            ShoppingCartRecyclerAdapter shoppingCartRecyclerAdapter = new ShoppingCartRecyclerAdapter(view.getContext());
           // recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(shoppingCartRecyclerAdapter);
            shoppingCartRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
