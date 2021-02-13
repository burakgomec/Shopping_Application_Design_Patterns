package com.burakgomec.shoppingapplication.RecyclerAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.burakgomec.shoppingapplication.Fragments.ProductDetailFragment;
import com.burakgomec.shoppingapplication.ProductObserver.Product;
import com.burakgomec.shoppingapplication.R;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> { //Vitrindeki ürünler için recyler view adapter sınıfı
    //Adapter Tasarım Deseni uygulanarak veriler bir view'a baglanmaktadır

    private final Context context;
    private final ArrayList<Product> productList = Product.getProductsList();


    public ProductRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ViewHolder holder, int position) {
            holder.textView.setText(productList.get(position).getName());
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(context).load(productList.get(position).getUri()).fitCenter().into(holder.imageView);
            //Glide kütüphanesi fotograf yükleniyor

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Context context;

        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context = context;
            imageView = itemView.findViewById(R.id.imageViewColumn1);
            textView = itemView.findViewById(R.id.textViewColumn1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClickListener(v);
                    //Kullanıcının vitrindeki bir ürüne tıklama olayı
                }
            });
        }

        private void itemOnClickListener(View v){
            int position = getLayoutPosition();

            Fragment productDetailFragment = new ProductDetailFragment();
            FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragmentContainer,productDetailFragment).addToBackStack(null).commit();

            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            productDetailFragment.setArguments(bundle);
            //Fragmentler arasında ürün pozisyonu tasınıyor

        }
    }
}
