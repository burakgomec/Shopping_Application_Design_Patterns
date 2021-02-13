package com.burakgomec.shoppingapplication.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;
import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.ShoppingCart;
import com.burakgomec.shoppingapplication.ProductObserver.User;


public class ProductDetailFragment extends Fragment { //İlan detayı sayfası

    ImageView imageView;
    TextView textDetail,textName,textPrice,textSeller;
    Button addProductToShoppingCart;
    Product getProduct;
    String productUser;

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

        setTexts(view);
        if(!productUser.equals(User.getUser().getName())){
            //İlan kullanıcıya ait ise sepete ekle yetkisi kaldırılıp düzenleme yetkisi(Text) veriliyor
            addProductToShoppingCart.setOnClickListener(new View.OnClickListener() {//Sepete Ekle Click listener
                @Override
                public void onClick(View v) {
                    addProductToShoppingCart(v);
                }
            });
        }



    }

    private void addProductToShoppingCart(View view){
        ShoppingCart.getInstance().addProductToShoppingCart(getProduct);
        ToastMessage toastMessage = new ToastMessage(new ToastMessageSender()); //Bridge Tasarım Deseni
        toastMessage.showMessage("Ürün sepetinze eklendi",view.getContext());
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

        productUser = getProduct.getUser().getName();
        if(productUser.equals(User.getUser().getName())){ //İlan kullanıcıya ait ise sepete ekle yerine "Düzenle" metni atanıyor
            addProductToShoppingCart.setText("Düzenle");
        }

    }


}