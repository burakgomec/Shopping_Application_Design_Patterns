package com.burakgomec.shoppingapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burakgomec.shoppingapplication.RecyclerAdapters.ProductRecyclerAdapter;
import com.burakgomec.shoppingapplication.R;

public class HomeFragment  extends Fragment {

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewProducts);
        ProductRecyclerAdapter productRecyclerAdapter = new ProductRecyclerAdapter(view.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerView.setAdapter(productRecyclerAdapter);

        //AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(view.getContext(), 2);

    }
}
