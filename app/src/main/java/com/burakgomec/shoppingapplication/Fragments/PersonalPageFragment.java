package com.burakgomec.shoppingapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.burakgomec.shoppingapplication.R;

import java.util.ArrayList;

public class PersonalPageFragment extends Fragment {

    ListView listViewAd,listViewMessages,listViewFavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewAd = view.findViewById(R.id.listViewAd);
        listViewMessages = view.findViewById(R.id.listViewMessages);
        listViewFavorites = view.findViewById(R.id.listViewFavorites);
    }
}
