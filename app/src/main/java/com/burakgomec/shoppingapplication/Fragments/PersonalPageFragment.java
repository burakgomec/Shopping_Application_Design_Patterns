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
    ArrayList<String> listAd,listMessages, listFavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listAd = new ArrayList<>(); listMessages = new ArrayList<>(); listFavorites = new ArrayList<>();
        listAd.add("Yayında Olanlar"); listAd.add("Yayında Olmayanlar");
        listMessages.add("İlan Mesajları"); listMessages.add("GeT Mesajları"); listMessages.add("Bilgilendirmeler");
        listFavorites.add("Favori İlanlar"); listFavorites.add("Favori Aramalar"); listFavorites.add("Favori Satıcılar");
        listViewAd = view.findViewById(R.id.listViewAd);
        listViewMessages = view.findViewById(R.id.listViewMessages);
        listViewFavorites = view.findViewById(R.id.listViewFavories);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,listAd);
        listViewAd.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1,listMessages);
        listViewMessages.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, listFavorites);
        listViewFavorites.setAdapter(arrayAdapter);
    }
}
