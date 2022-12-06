package com.example.cinemahub_looknfeel;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivitySeatingChartV3Binding;
import com.example.cinemahub_looknfeel.model.Event;
import com.example.cinemahub_looknfeel.model.Movie;
import com.example.cinemahub_looknfeel.model.Seat;

public class MovieSeatingChartActivity extends AppCompatActivity {

    // Add the binder:
    private ActivitySeatingChartV3Binding binding;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        binding = ActivitySeatingChartV3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent( ) {

        // Spinner Set Up: /////////////////////////////////////////////////////////////////////////
            Spinner spinner = (Spinner) findViewById(R.id.seatSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.seats_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        ////////////////////////////////////////////////////////////////////////////////////////////

        Event eventReceiving = getIntent().getParcelableExtra(MovieDetailsActivity.EXTRA_MOVIE);

        // Set the proceed buttons on-click listener:
        binding.btnProceed.setOnClickListener(v -> onProceedBtnClicked());
    }

    private void onProceedBtnClicked() {
        // TODO: Change spinner selected item from string to int?
        // TODO: What if there is more than one person in the party???
        Seat seat = new Seat();

        // Get the seat number: THIS IS A REALLY BAD WAY OF DOING THIS!!!
        int seatNumber = Integer.parseInt(binding.seatSpinner.getSelectedItem().toString().replaceAll("[^0-9]", ""));
        // Set the seat number:
        seat.setId(seatNumber);

        // Set the handicap and reclinable sets options based on the seat number:
        if(seat.getId() < 7) {
            seat.setReclinable(true);
            seat.setHandicap(false);
        }
        else if(seat.getId() > 11) {
            seat.setReclinable(false);
            seat.setHandicap(true);
        }
        else
        {
            seat.setReclinable(false);
            seat.setHandicap(false);
        }


    }

}