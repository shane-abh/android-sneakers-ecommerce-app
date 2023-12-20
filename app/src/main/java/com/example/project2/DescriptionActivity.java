package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.recycleViewAdapters.CartViewAdapter;
import com.example.project2.recycleViewAdapters.CartViewInterface;

public class DescriptionActivity extends AppCompatActivity {



    int quantity = 1; // Initial quantity
    int shoeSize = 37;

    ImageView descriptionImageIv;
    TextView productTitleTv;
    TextView productTagTv;
    TextView productPriceTv;
    TextView productDescriptionTv;

     CartManager cartManager;

     Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        descriptionImageIv = findViewById(R.id.descriptionImage);
        productTitleTv = findViewById(R.id.productHeading);
        productTagTv = findViewById(R.id.productTag);
        productPriceTv = findViewById(R.id.productPrice);
        productDescriptionTv = findViewById(R.id.productDescription);




        Intent intent = getIntent();

        String productHeading = intent.getStringExtra("productHeading");
        String productTag = intent.getStringExtra("productTag");
        String productImage = intent.getStringExtra("image");
        String productPrice = intent.getStringExtra("productPrice");
        String descriptionImage = intent.getStringExtra("descriptionImage");
        String productDescription = intent.getStringExtra("productDescription");

        System.out.println(descriptionImage + " " + productHeading + " " + productTag + productDescription);
        descriptionImageIv.setImageResource(Integer.valueOf(descriptionImage));
        productTitleTv.setText(productHeading);
        productTagTv.setText(productTag);
        productPriceTv.setText("$" + productPrice);
        productDescriptionTv.setText(productDescription);



        cartManager = CartManager.getInstance();


        Button btnMinus = findViewById(R.id.btnMinus);
        TextView tvQuantity = findViewById(R.id.tvQuantity);

        Button btnPlus = findViewById(R.id.btnPlus);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    // A radio button is checked, do something with the checkedId
                    RadioButton checkedRadioButton = findViewById(checkedId);
                    String selectedValue = checkedRadioButton.getText().toString();
                    // or use other properties like checkedRadioButton.getTag()

                    shoeSize = Integer.valueOf(selectedValue);


                    // Now 'selectedValue' contains the value of the selected radio button
                    // Do whatever you need with the selected value
                } else {
                    // No radio button is checked
                }
            }
        });




        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    tvQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can set a maximum limit for the quantity if needed
                quantity++;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });



        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cart c = new Cart(productHeading,productTag,Double.valueOf(productPrice),shoeSize, quantity,Integer.valueOf(productImage));



                cartManager.addToCart(c);
                cartManager.notifyCartSizeChanged();



                Toast.makeText(getApplicationContext(),"Item added to Cart",Toast.LENGTH_SHORT).show();

            }
        });

    }


}