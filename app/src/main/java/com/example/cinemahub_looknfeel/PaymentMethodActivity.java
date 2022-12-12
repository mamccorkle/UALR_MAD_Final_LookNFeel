package com.example.cinemahub_looknfeel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivityPaymentMethodsBinding;
import com.example.cinemahub_looknfeel.model.Event;
import com.google.android.material.snackbar.Snackbar;

public class PaymentMethodActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityPaymentMethodsBinding binding;

    // Intents variable:
    public static final String EXTRA_MOVIE = "MovieData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityPaymentMethodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the actionbar:
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Payment");

        init();
    }

    public void init() {
        // Get the intents from the Movie Details Activity:
        Event eventReceiving = getIntent().getParcelableExtra(OrderSummaryActivity.EXTRA_MOVIE);

        //Intent toPayment = new Intent(this, TicketViewActivity.class);
        Intent toPayment = new Intent(this, PaymentActivity.class);

        // Set the intent to pass the event object:
        toPayment.putExtra( EXTRA_MOVIE, eventReceiving );

        // Get the parent layout:
        View parentLayout = findViewById(android.R.id.content);

        // GooglePay on-click listener:
        View vGPay = findViewById(R.id.llGooglePay);
        vGPay.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parentLayout, "GooglePay: Not implemented yet...", Snackbar.LENGTH_SHORT);
            snackbar.show();

            // Start the new activity and send the data at the same time:
            startActivity( toPayment );
        });

        // MasterCard on-click listener:
        View vMC = findViewById(R.id.llMasterCard);
        vMC.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parentLayout, "MasterCard: Not implemented yet...", Snackbar.LENGTH_SHORT);
            snackbar.show();

            // Start the new activity and send the data at the same time:
            startActivity( toPayment );
        });

        // PayPal on-click listener:
        View vPP = findViewById(R.id.llPayPal);
        vPP.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parentLayout, "PayPal: Not implemented yet...", Snackbar.LENGTH_SHORT);
            snackbar.show();

            // Start the new activity and send the data at the same time:
            startActivity( toPayment );
        });

        // Visa on-click listener:
        View vV = findViewById(R.id.llVisa);
        vV.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parentLayout, "Visa: Not implemented yet...", Snackbar.LENGTH_SHORT);
            snackbar.show();

            // Start the new activity and send the data at the same time:
            startActivity( toPayment );
        });

        // NewCreditCard on-click listener:
        View vNCC = findViewById(R.id.llNewCreditCard);
        vNCC.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parentLayout, "NewCreditCard: Not implemented yet...", Snackbar.LENGTH_SHORT);
            snackbar.show();

            // Start the new activity and send the data at the same time:
            startActivity( toPayment );
        });
    }

}
