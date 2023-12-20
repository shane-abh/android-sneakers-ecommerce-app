package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2.recycleViewAdapters.CartViewAdapter;
import com.example.project2.recycleViewAdapters.CartViewInterface;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartViewInterface {

    ArrayList<Cart> cartList;
    RecyclerView recyclerView;
    CartViewAdapter cAdapter;

    TextView subtotalTv;
    TextView taxTv;
    TextView totalTv;

    private CartManager cartManager;

    Button checkOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartManager = CartManager.getInstance();
        cartList = new ArrayList<Cart>();
        cartList = (ArrayList<Cart>) cartManager.getCartItems();

        subtotalTv = findViewById(R.id.subtotal);
        taxTv = findViewById(R.id.tax);
        totalTv = findViewById(R.id.total);

        checkOutBtn = findViewById(R.id.checkOutBtn);

        recyclerView = findViewById(R.id.cartView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        calculateTotal();


        cAdapter = new CartViewAdapter(this, cartList);

        cAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(cAdapter);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra("subtotal", subtotalTv.getText().toString());
                intent.putExtra("tax",taxTv.getText().toString());
                intent.putExtra("total",totalTv.getText().toString());
                startActivity(intent);

            }
        });


    }

    public void calculateTotal(){
        double subtotal = 0;

        for (Cart i : cartManager.getCartItems()) {
            subtotal += i.getQuantity() * i.getProductPrice();
        }
        double total = Math.round(subtotal * 1.13);

        subtotalTv.setText(String.valueOf(subtotal));
        taxTv.setText("13%");
        totalTv.setText(String.valueOf(total));
    }


    @Override
    public void onDeleteClick(int position) {
        cartManager.removeFromCart(position);
        cAdapter.notifyDataSetChanged();
        cAdapter.notifyItemRemoved(position);

        calculateTotal();
        cartManager.notifyCartSizeChanged();
    }
}