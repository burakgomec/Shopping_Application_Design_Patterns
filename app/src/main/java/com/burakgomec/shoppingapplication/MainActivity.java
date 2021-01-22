package com.burakgomec.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.burakgomec.shoppingapplication.Fragments.AddProductFragment;
import com.burakgomec.shoppingapplication.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPzpXuDA1KOYkmTnEaT4lVXD8CSgTptQ_5WA&usqp=CAU
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //getWindow().setStatusBarColor(Color.parseColor("#000000"));


        bottomNavigationItemSelect();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
    }


    private void bottomNavigationItemSelect(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.itemHome:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.itemAddProduct:
                        selectedFragment = new AddProductFragment();
                        break;
                    case R.id.itemUserProduct:
                        selectedFragment = new HomeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
                return true;
            }
        });
    }




}