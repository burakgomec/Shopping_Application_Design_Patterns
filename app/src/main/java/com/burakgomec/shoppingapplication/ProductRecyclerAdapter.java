package com.burakgomec.shoppingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    private String uri;
    private String text;
    private final Context context;
    private final ArrayList<Product> productList = Product.productsList;

    public ProductRecyclerAdapter(Context context){
        //this.productList = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ViewHolder holder, int position) {

            holder.textView.setText(productList.get(position).getName());
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(context).load(productList.get(position).getUri()).override(400,400).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewColumn1);
            textView = itemView.findViewById(R.id.textViewColumn1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClickListener(v);
                }
            });
        }

        private void itemOnClickListener(View v){
            int position = getLayoutPosition();
            Toast.makeText(v.getContext(),Product.productsList.get(position).getName() + " / " +
                    Product.productsList.get(position).getUser().getLocation()
                    +" / " + Product.productsList.get(position).getUser().getName(), Toast.LENGTH_SHORT).show();

        }
    }
}
