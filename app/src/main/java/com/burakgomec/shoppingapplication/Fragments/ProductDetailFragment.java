package com.burakgomec.shoppingapplication.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.burakgomec.shoppingapplication.Product;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.ShoppingCart;


public class ProductDetailFragment extends Fragment {

    ImageView imageView;
    TextView textDetail,textName,textPrice,textSeller;
    Button addProductToShoppingCart;
    Product getProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imageViewProduct);
        textDetail = view.findViewById(R.id.textViewProductDescription);
        textName = view.findViewById(R.id.textViewProductName);
        textPrice = view.findViewById(R.id.textViewProductPrice);
        textSeller = view.findViewById(R.id.textViewProductSeller);
        addProductToShoppingCart = view.findViewById(R.id.addProductToShoppingCart);


        addProductToShoppingCart.setOnClickListener(new View.OnClickListener() { //Sepete Ekle Click listener
            @Override
            public void onClick(View v) {
                addProductToShoppingCart(v);
            }
        });

        setTexts(view);
    }

    private void addProductToShoppingCart(View view){
        ShoppingCart.getInstance().addProductToShoppingCart(getProduct);
        Toast.makeText(view.getContext(),"Ürün sepetinize eklendi!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }



    @SuppressLint("SetTextI18n")
    private void setTexts(View view){
        Bundle b = getArguments();
        int position = b.getInt("position");
        getProduct = Product.getProductsList().get(position);
        Glide.with(view.getContext()).load(getProduct.getUri()).centerCrop().fitCenter().into(imageView);
        textDetail.setText(getProduct.getDetail());
        textName.setText(getProduct.getName());
        textPrice.setText("Fiyat: "+ getProduct.getPrice() + " TL");
        textSeller.setText(getProduct.getUser().getName() + " / " + getProduct.getUser().getLocation());

    }


}