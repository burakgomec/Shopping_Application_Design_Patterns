package com.burakgomec.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;

import com.burakgomec.shoppingapplication.Fragments.AddProductFragment;
import com.burakgomec.shoppingapplication.Fragments.HomeFragment;
import com.burakgomec.shoppingapplication.OrderProxy.IOrder;
import com.burakgomec.shoppingapplication.OrderProxy.OrderProxy;
import com.burakgomec.shoppingapplication.Fragments.ShoppingCartFragment;
import com.burakgomec.shoppingapplication.PaymentStrategy.CreditCartPayment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    boolean handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        //getWindow().setStatusBarColor(Color.parseColor("#000000"));

        addProducts(); //Ürünlerin run-time da yaratılıp listeye eklendigi method

        bottomNavigationItemSelect();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();

        handler = new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //observerProduct.setPrice(500, getApplicationContext());
            }
        },1000);
    }




    private void addProducts(){ //Uygulama prototip olarak gelistirildiği icin veri tabanı baglanmadı

        Uri xboxUri = Uri.parse("android.resource://"+getApplicationContext().getPackageName() +"/"+R.drawable.xbox);
        Uri ps5Uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.ps5);
        Uri iphone12Uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.iphone12);
        Uri mbaUri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.mba);


        Product xbox = new Product(1,String.valueOf(xboxUri),"Xbox Series S 500 GB",User.getUser(),
                4800,"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        Product ps5 = new Product(2,String.valueOf(ps5Uri),"PS5 Digital Edition",User.getUser(),8300
                ,"2 Adet Gamepad dahil ps5 ");

        Product iPhone12 = new Product(3,String.valueOf(iphone12Uri),"iPhone 12 Pro Max",User.getUser()
        ,16000,"Adınıza Faturalı 24 Ay Apple Garantili iPhone 12 128 GB Gold");

        Product mba = new Product(4,String.valueOf(mbaUri),"Macbook M1 Apple Slicon",User.getUser()
                ,11500,"1 TB SSD 16 GB RAM APPLE SILICON");


        Product.getProductsList().add(xbox);
        Product.getProductsList().add(ps5);
        Product.getProductsList().add(iPhone12);
        Product.getProductsList().add(mba);


        //IObserverUser burak = new ObserverUser();
        //IObserverUser cem = new ObserverUser();
        //ps5.addObserver(User.getUser());

        IOrder order = new OrderProxy(); //Siparişi Tamamla Click On
        order.createOrder(User.getUser(),getApplicationContext());



        ShoppingCart.getInstance().addProductToShoppingCart(xbox);
        ShoppingCart.getInstance().addProductToShoppingCart(ps5);
        ShoppingCart.getInstance().setPaymentMethod(new CreditCartPayment("Burak","12323","233"));
        ShoppingCart.getInstance().pay();



        handler = new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ps5.setPrice(1000,getApplicationContext());
            }
        },2000);

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
                    case R.id.itemBasket:
                        selectedFragment = new ShoppingCartFragment();
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


    @Override
    protected void onResume() {
        super.onResume();
    }
}