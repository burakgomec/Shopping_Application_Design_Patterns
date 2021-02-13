package com.burakgomec.shoppingapplication.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessage;
import com.burakgomec.shoppingapplication.MessagesBridge.ToastMessageSender;
import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.ProductObserver.User;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends Fragment {

    ImageView imageView;
    EditText imageUrl,productTitle,productCategory,productPrice,productDetail;
    String uri = "https://static.thenounproject.com/png/558642-200.png";
    Uri imageData;
    Button saveAd;

    ToastMessage toastMessage = new ToastMessage(new ToastMessageSender());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_product_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.productImage);
        imageUrl = view.findViewById(R.id.imageUrl);
        productTitle = view.findViewById(R.id.productTitle);
        productCategory = view.findViewById(R.id.productCategory);
        productPrice = view.findViewById(R.id.productPrice);
        productDetail = view.findViewById(R.id.productDetail);
        saveAd = view.findViewById(R.id.saveAd);



        saveAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAd(v);
            }
        });



        Glide.with(view.getContext())
                .load(R.drawable.addimages)
                .centerCrop()
                .error(R.drawable.ic_baseline_post_add_24)
                .into(imageView);

        editTextChangeListener(view);
        setImageFromGallery();
    }

    private void saveAd(View view){

        if(productTitle.getText().toString().trim().length() < 2 || productTitle.getText().toString().trim().length() > 18){
            productTitle.setError("Lütfen alanı doldurunuz(2-18 Karakter)");
        }
        else if(productCategory.getText().toString().trim().length() <2 || productTitle.getText().toString().trim().length() > 15){
            productCategory.setError("Lütfen alanı doldurunuz(2-10 Karakter)");
        }
        else if(productPrice.getText().toString().trim().equals("")){
            productPrice.setError("Lütfen alanı doldurunuz");
        }
        else if(productDetail.getText().toString().trim().length()<3){
            productDetail.setError("Lütfen alanı doldurunuz(En Az 3 Karakter)");
        }
        else{
            int size = Product.getProductsList().size()+1;
            Product product = new Product(size,uri,productTitle.getText().toString(), User.getUser(),Integer.parseInt(productPrice.getText().toString())
            ,productDetail.getText().toString());
            Product.getProductsList().add(product);
            toastMessage.showMessage("İlanınız başarıyla kaydedildi!",view.getContext());
            User.getUser().getMyProductList().add(product);
        }
    }

    private void editTextChangeListener(View view){
        imageUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }
            @Override
            public void afterTextChanged(Editable s) { //Link girisi bittikten sonra
                uri = s.toString();
                Glide.with(view.getContext())
                        .load(uri)
                        .centerCrop().fitCenter()
                        .error(Drawable.createFromPath(uri))
                        .into(imageView);
            }
        });
    }

    private void setImageFromGallery(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //Api>23 sonrası icin gerekli
                    ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                    toastMessage.showMessage("Uygulamanın galeriye erişim ayarlarını değiştiriniz", getContext());
                }
                else{
                    //İzin hali hazırda varsa
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGallery,2);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Galeriye erisim izni sonucu
        if(requestCode == 1){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Galeriden resim secildigi metot
        if(requestCode == 2 && resultCode == RESULT_OK && data !=null){
            imageData = data.getData();
            uri = String.valueOf(imageData);
            Glide.with(getContext())
                    .load(imageData)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_post_add_24)
                    .into(imageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
