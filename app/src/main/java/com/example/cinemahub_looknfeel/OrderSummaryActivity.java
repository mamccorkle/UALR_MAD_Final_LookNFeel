package com.example.cinemahub_looknfeel;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivityOrderSummaryBinding;
import com.example.cinemahub_looknfeel.model.Event;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class OrderSummaryActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityOrderSummaryBinding binding;

    private Double ticketPrice = 5.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Fix the poster being passed
        // TODO: Fix the itemized list being calculated
        // TODO: Fix show time bug
        // TODO: Fix buy now button
        // TODO: Pricing for concessions

        super.onCreate(savedInstanceState);
        binding = ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the actionbar:
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {

        // Get the intents from the previous screen:
        Event eventReceiving = getIntent().getParcelableExtra(MovieSeatingChartActivity.EXTRA_MOVIE);

        // Get the Movie:
        binding.tvMovieTitle.setText(eventReceiving.getMovie().getTitle());

        // Get the movie poster:
        int[] resImages = {
                R.drawable.movie_black_adam,
                R.drawable.movie_halloween_ends,
                R.drawable.movie_one_piece_film_red,
                R.drawable.movie_prey_for_the_devil,
                R.drawable.movie_smile,
                R.drawable.movie_ticket_to_paradise,
                R.drawable.icon_generic_movie_poster
        };
        binding.ivMoviePoster.setImageResource(resImages[eventReceiving.getMovie().getImageIndex()]);

        // Get the theater:
        binding.tvMovieTheater.setText(eventReceiving.getTheater().getName());

        // Get the number of tickets/persons:
        binding.tvMovieNumberOfSeats.setText(String.valueOf(eventReceiving.getPersons()));

        // Get the show time:
        // TODO: This variable gets emptied between the MovieSeatingChartActivity intent instantiation and being received in the getParcelable above:
        //binding.tvMovieShowtime.setText(eventReceiving.getShowTime());

        // Set the on-click listener for the buy now button:
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBuyNowBtnClick();
            }
        });

        calculateCost(eventReceiving);
    }

    private void onBuyNowBtnClick() {
        // TODO: Implement the buy now button by passing the events data to the ticket screen to generate the qr code:
        // Create the Snackbar to be displayed to the user:
        Snackbar snackbar = Snackbar.make(binding.getRoot(), "Not implemented yet...", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void calculateCost(Event e) {
        Double runningTotal = 0.0;

        // Add the price of the tickets:
        runningTotal += e.getPersons() * ticketPrice;

        // Add the concessions:
        // TODO: Need a better way for pricing here:
        for ( String item: e.getConcessions() ) {
            runningTotal += 3.50;
        }

        // Format the dolla-dolla-bills:
        DecimalFormat formattedDollars = new DecimalFormat("$ ###,##0.00");

        // Assign the values to the widgets:
        binding.tvTotal.setText(String.valueOf(formattedDollars.format(runningTotal)));
        binding.tvMovieCost.setText(String.valueOf(formattedDollars.format(runningTotal)));
    }
}