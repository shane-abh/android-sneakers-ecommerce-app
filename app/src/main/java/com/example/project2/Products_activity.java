package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.recycleViewAdapters.CartSizeChangeListener;
import com.example.project2.recycleViewAdapters.CartViewAdapter;
import com.example.project2.recycleViewAdapters.CartViewInterface;
import com.example.project2.recycleViewAdapters.ProductViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Products_activity extends AppCompatActivity implements CartSizeChangeListener {

    private CartManager cartManager;
    TextView cartSize;

    RecyclerView recyclerView;
    ProductViewAdapter pAdapter;


    Toolbar toolbar;


    List<Product> productData = new ArrayList<Product>();

    SearchView searchView;

    ImageView adImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView = findViewById(R.id.rproducts);
        searchView = findViewById(R.id.searchView);
        cartSize = findViewById(R.id.cartSize);
        cartManager = CartManager.getInstance();
        cartManager.registerListener(this);

        adImage = findViewById(R.id.adImage);

        setUpToolbar();

        ProductData();

        searchView.setQueryHint("Search...");

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        pAdapter = new ProductViewAdapter(productData);

        recyclerView.setAdapter(pAdapter);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            TranslateAnimation anim = new TranslateAnimation(100, 1, -100, 0);
            anim.setInterpolator(new OvershootInterpolator());
            anim.setRepeatCount(Animation.ABSOLUTE);
            anim.setDuration(8800);


            adImage.startAnimation(anim);
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


    }

    private void filter(String text) {

        ArrayList<Product> filteredlist = new ArrayList<Product>();

        for (Product item : productData) {

            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            pAdapter.filterList(filteredlist);
        }
    }

    public void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        ImageView cartToolbar = findViewById(R.id.cartToolbar);


        System.out.println("CartSize: " + 0);


        cartToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);
    }

    public void ProductData() {
        productData.add(new Product("Nike Air Jordan 1 Mid", R.drawable.nike, "Men's Shoes", 100.00, "Inspired by the original AJ1, this mid-top edition maintains the iconic look you love while choice colours and crisp leather give it a distinct identity.", R.drawable.nike_big));
        productData.add(new Product("Nike Air Jordan 1 Mid SE", R.drawable.air_jordan_brown, "Men's Shoe", 200.00, "Rock some retro colours and look good doin' it. This pair of kicks has nubuck leather and suede in the upper for a plush look and feel. Nike Air in the sole keeps your every step light as … well, air.", R.drawable.air_jordan_brown_big));
        productData.add(new Product("Nike Infinity RN 4", R.drawable.nike_infinity_rn, "Men's Shoe", 500.00, "With supportive cushioning built for a smooth run, the Nike InfinityRN 4 is a brand-new take on a familiar favourite. It's made from our all-new Nike ReactX foam, which gives you 13% more energy return compared to Nike React foam, to help you stay fresh and bouncy. (What's more? Nike ReactX reduces its carbon footprint in a pair of midsoles by at least 43% compared with Nike React foam.*) This version has GORE-TEX fabric to help you stay dry for your miles. It's the kind of shoe that supports every step in style. *The carbon footprint of ReactX is based on a cradle-to-gate assessment reviewed by PRé Sustainability B.V. and Intertek China. Other midsole components such as airbags, plates or other foam formulations were not considered.", R.drawable.nike_infinity_rn_big));
        productData.add(new Product("Nike Air Jordan 1 Low", R.drawable.air_jordan_low, "Men's Shoe", 400.00, "Inspired by the original that debuted in 1985, the Air Jordan 1 Low offers a clean, classic look that's familiar yet always fresh. With an iconic design that pairs perfectly with any 'fit, these kicks ensure you'll always be on point.", R.drawable.air_jordan_low_big));
        productData.add(new Product("Nike Air Max Pulse", R.drawable.nike_air_max_pluse, "Men's Shoe", 150.00, "The Air Max Pulse pulls inspiration from the London music scene, bringing an underground touch to the iconic Air Max line. Its textile-wrapped midsole and vacuum-sealed accents keep 'em looking fresh and clean, while colours inspired by the London music scene give your look the edge. Point-loaded Air cushioning—revamped from the incredibly plush Air Max 270—delivers better bounce, helping you push past your limits.", R.drawable.nike_air_max_pluse_big));
        productData.add(new Product("Nike Air Force", R.drawable.nike_air_force, "Men's Shoe", 600.00, "The radiance lives on with the b-ball original. Crossing hardwood comfort with off-court flair, it puts a fresh spin on what you know best: '80s-inspired construction, bold details and nothin'-but-net style.", R.drawable.nike_air_force_big));


    }


    @Override
    public void onCartSizeChanged(int newSize) {
        cartSize.setText(String.valueOf(newSize));
    }
}