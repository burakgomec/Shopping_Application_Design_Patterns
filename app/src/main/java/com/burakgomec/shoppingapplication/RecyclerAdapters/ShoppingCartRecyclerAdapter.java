package com.burakgomec.shoppingapplication.RecyclerAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.burakgomec.shoppingapplication.Fragments.ShoppingCartFragment;
import com.burakgomec.shoppingapplication.Observer.Product;
import com.burakgomec.shoppingapplication.R;
import com.burakgomec.shoppingapplication.ShoppingCart;

import java.util.ArrayList;

public class ShoppingCartRecyclerAdapter extends RecyclerView.Adapter<ShoppingCartRecyclerAdapter.ViewHolder> {

    ArrayList<Product> selectedProducts;
    Context context;
    TextView totalPrice;

    public ShoppingCartRecyclerAdapter(Context context,TextView totalPrice){
        this.selectedProducts = ShoppingCart.getInstance().getSelectedProducts();
        this.context = context;
        this.totalPrice = totalPrice;
    }

    @NonNull
    @Override
    public ShoppingCartRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_cart,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShoppingCartRecyclerAdapter.ViewHolder holder, int position) {
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(context).load(selectedProducts.get(position).getUri()).fitCenter().into(holder.imageView);
        holder.productName.setText(selectedProducts.get(position).getName());
        holder.productPrice.setText(selectedProducts.get(position).getPrice() + " TL");

    }

    @Override
    public int getItemCount() {
        return selectedProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName,productPrice;
        Button removeProduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageShoppingCart);
            productName = itemView.findViewById(R.id.productNameShoppingCart);
            productPrice = itemView.findViewById(R.id.productPriceShoppingCart);
            removeProduct = itemView.findViewById(R.id.removeProduct);

            removeProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeProduct();
                }
            });
        }

        private void removeProduct(){
            int position = getLayoutPosition();
            calculateTotalPrice(position);
            ShoppingCart.getInstance().removeProductToShoppingCart(selectedProducts.get(position));
            notifyItemRemoved(position);




            if(selectedProducts.size() == 0){
                Fragment shoppingCartFragment = new ShoppingCartFragment();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentContainer,shoppingCartFragment).commit();
            }
        }

        private void calculateTotalPrice(Integer position){
            if(selectedProducts.size() == 1){
                totalPrice.setText(String.valueOf(0));
            }
            else{
                int sum = Integer.parseInt(totalPrice.getText().toString());
                sum -= selectedProducts.get(position).getPrice();
                totalPrice.setText(String.valueOf(sum));
            }

        }
    }
}
