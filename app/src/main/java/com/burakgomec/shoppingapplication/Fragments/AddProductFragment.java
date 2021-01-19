package com.burakgomec.shoppingapplication.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.burakgomec.shoppingapplication.R;

import org.w3c.dom.Text;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends Fragment {

    ImageView imageView;
    EditText editText;
    String url = "https://static.thenounproject.com/png/558642-200.png";
    Uri imageData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_product_fragment,container,false);
        imageView = view.findViewById(R.id.productImage);
        editText = view.findViewById(R.id.imageUrl);




        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .error(R.drawable.ic_baseline_post_add_24)
                .into(imageView);

        editTextChangeListener(view);
        setImageFromGallery();
        return view;
    }



    private void editTextChangeListener(View view){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }
            @Override
            public void afterTextChanged(Editable s) {
                url = s.toString();
                Glide.with(view.getContext())
                        .load(url)
                        .centerCrop()
                        .error(R.drawable.ic_baseline_post_add_24)
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
                    Toast.makeText(getContext(),"Uygulamanın galeriye erişim ayarlarını değiştiriniz", Toast.LENGTH_SHORT).show();
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
        if(requestCode == 2 && resultCode == RESULT_OK && data !=null){
            imageData = data.getData();
            Glide.with(getContext())
                    .load(imageData)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_post_add_24)
                    .into(imageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
