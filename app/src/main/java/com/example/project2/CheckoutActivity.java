package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project2.checkOutPage.CreditCardFormattingTextWatcher;
import com.example.project2.checkOutPage.ExpiryDateFormattingTextWatcher;

import java.util.regex.Pattern;


public class CheckoutActivity extends AppCompatActivity {

    TextView subtotal, deliveryFee, total;
    EditText email, address, postalCode, deliveryInstructions;

    EditText cardHolderName, cardNumber, cardExpiry, cardCVV;

    ImageView cardType;

    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        subtotal = findViewById(R.id.checkOutSubtotal);
        deliveryFee = findViewById(R.id.checkOutDeliveryFee);
        total = findViewById(R.id.checkOutTotal);

        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        postalCode = findViewById(R.id.postalCode);
        deliveryInstructions = findViewById(R.id.deliveryInstructions);

        cardHolderName = findViewById(R.id.cardOwner);
        cardNumber = findViewById(R.id.cardNumber);
        cardExpiry = findViewById(R.id.expiryDate);
        cardCVV = findViewById(R.id.cvv);

        cardType = findViewById(R.id.creditCard);

        placeOrder = findViewById(R.id.placeOrder);

        cardNumber.addTextChangedListener(new CreditCardFormattingTextWatcher(cardNumber, cardType));
        cardExpiry.addTextChangedListener(new ExpiryDateFormattingTextWatcher(cardExpiry));
        orderSummary();





        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    Intent intent = new Intent(getApplicationContext(), ConfirmationPage.class);
                    startActivity(intent);
                    CartManager.getInstance().clearCart();
                }

            }
        });





    }

    public void orderSummary(){
        Intent intent = getIntent();

        subtotal.setText(intent.getStringExtra("total"));
        deliveryFee.setText("$10.00");
        double totalCost = Double.valueOf(intent.getStringExtra("total")) + 10;
        total.setText(String.valueOf(totalCost));

    }

    private boolean validateFields() {
        boolean isValid = true;

        isValid &= validateField(email, "Enter a valid email address", Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches());
        isValid &= validateField(address, "Address cannot be empty", !TextUtils.isEmpty(address.getText().toString().trim()));
        isValid &= validateField(postalCode, "Enter a valid postal code", Pattern.matches("^[A-Za-z][0-9][A-Za-z] [0-9][A-Za-z][0-9]$", postalCode.getText().toString().trim()));
        isValid &= validateField(deliveryInstructions, "Delivery instructions cannot be empty", !TextUtils.isEmpty(deliveryInstructions.getText().toString().trim()));

        isValid &= validateField(cardHolderName, "Cardholder name cannot be empty", !TextUtils.isEmpty(cardHolderName.getText().toString().trim()));
        isValid &= validateField(cardNumber, "Enter a valid card number", cardNumber.getText().toString().trim().length() >= 16);
        isValid &= validateField(cardExpiry, "Enter a valid expiry date", !TextUtils.isEmpty(cardExpiry.getText().toString().trim()));
        isValid &= validateField(cardCVV, "Enter a valid CVV", cardCVV.getText().toString().trim().length() == 3);

        // Add more validation for other fields as needed

        return isValid;
    }

    private boolean validateField(EditText editText, String errorMessage, boolean isValid) {
        if (!isValid) {
            editText.setError(errorMessage);
        } else {
            editText.setError(null);
        }
        return isValid;
    }
}