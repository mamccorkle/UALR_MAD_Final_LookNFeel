package com.example.cinemahub_looknfeel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.cinemahub_looknfeel.databinding.ActivitySeatingChartV3Binding;
import com.example.cinemahub_looknfeel.model.Event;
import com.example.cinemahub_looknfeel.model.Seat;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MovieSeatingChartActivity extends AppCompatActivity {

    // Add the binder:
    private ActivitySeatingChartV3Binding binding;

    // Global show time variable for the lambda below:
    public String showTime = "";

    // Global for the intents below:
    public static final String EXTRA_MOVIE = "EventFromSeating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Fix the showTime variable not passing through correctly
        // TODO: Fix the poster being passed

        super.onCreate(savedInstanceState);

        binding = ActivitySeatingChartV3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Make sure the ActionBar is visible:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init() {

        // Get the intents from the Movie Details Activity:
        Event eventReceiving = getIntent().getParcelableExtra(MovieDetailsActivity.EXTRA_MOVIE);

        // Generate the ShowTimes:
        // Loop through the show times and dynamically generate chips for each show time and assign
        // to the chip group. Whilst, setting them selected and checked at the same time:
        List<String> showTimes = eventReceiving.getAvailableShowTimes();
        for (String showTime : showTimes )
        {
            Chip tempChip = new Chip(this);
            tempChip.setText(showTime);
            tempChip.setCheckable(true);
            tempChip.setCheckedIconVisible(true);
            tempChip.setOnClickListener(v -> {
                tempChip.setSelected(true);
                tempChip.setChecked(true);
            });
            binding.chpGroup.addView(tempChip);
        }

        // For the grid-View, loop through each widget and add on-click listeners for the ToggleButtons:

        // Get the grid-views ViewGroup:
        ViewGroup seatingChartVG = (ViewGroup) binding.glSeatingChart;
        // Loop through each widget in the Grid-View:
        for (int i = 0; i < seatingChartVG.getChildCount(); i++) {

            // Get the current widget at the index:
            View currentWidget = seatingChartVG.getChildAt(i);

            // Check if the widget is a ToggleButton object:
            if(currentWidget instanceof ToggleButton)
            {
                // Add the on-click listener for each ToggleButton widget in the grid-view:
                ((ToggleButton) currentWidget).setOnCheckedChangeListener((buttonView, isChecked) -> {
                    binding.glSeatingChart.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    // If the button is < 7, then they are reclining seats:
                    if(Integer.parseInt(((ToggleButton) currentWidget).getText().toString()) < 7) {
                        // If the button is not checked, set the background color and draw the rectangle:
                        if(!((ToggleButton) currentWidget).isChecked()) {
                            currentWidget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            currentWidget.setBackgroundResource(R.drawable.shape_rectangle);
                        }
                        // Otherwise draw the default setting:
                        else
                            currentWidget.setBackgroundColor(Color.parseColor("#AAFF7AB2"));
                    }
                    // If the button is > 11, then they are handicap seats:
                    else if(Integer.parseInt(((ToggleButton) currentWidget).getText().toString()) > 11) {
                        if(!((ToggleButton) currentWidget).isChecked()) {
                            currentWidget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            currentWidget.setBackgroundResource(R.drawable.shape_rectangle);
                        }
                        else
                            currentWidget.setBackgroundColor(Color.parseColor("#FF4FB1CB"));
                    }
                    // Generic seating:
                    else {
                        if(!((ToggleButton) currentWidget).isChecked()) {
                            currentWidget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            currentWidget.setBackgroundResource(R.drawable.shape_rectangle);
                        }
                        else
                            currentWidget.setBackgroundColor(Color.parseColor("#FFA3A3A3"));
                    }
                });
            }
        }

        // Loop through the parent chip group and dynamically assign on click listeners for each of
        // the chips in the chip group:
        ViewGroup chpGroupVG = (ViewGroup) binding.chpGroup;
        for (int i = 0; i < chpGroupVG.getChildCount(); i++) {
            View currentWidget = chpGroupVG.getChildAt(i);
            if(currentWidget instanceof Chip)
            {
                // Set the chips properties:
                ((Chip) currentWidget).setCheckable(true);
                ((Chip) currentWidget).setCheckedIconVisible(true);

                // Add the listeners:
                currentWidget.setOnClickListener(v -> {
                    // Select and check the given chip when it is clicked/tapped. Also, assign the
                    // time of the selected chip to the global showtime variable. The background
                    // color is defined as white here due to the error procedures defined elsewhere
                    // changing the background to red:
                    currentWidget.setSelected(true);
                    ((Chip) currentWidget).setChecked(true);
                    showTime = ((Chip) currentWidget).getText().toString();
                    binding.chpGroup.setBackgroundColor(Color.parseColor("#FFFFFF"));
                });
            }
        }

        // Set the proceed buttons on-click listener:
        binding.btnProceedToCheckout.setOnClickListener(v -> {
            onProceedBtnClick(eventReceiving);
        });
    }

    public void onProceedBtnClick(Event event) {
        // Instead of setting an on-click listener I will loop through to check which seats have
        // been activated (checkButtons are checked) prior to moving to the next activity:
        ViewGroup seatingChartVG = (ViewGroup) binding.glSeatingChart;

        // Used to store the seat numbers being passed:
        List<Seat> tempSeats = new ArrayList<>();

        for (int i = 0; i < seatingChartVG.getChildCount(); i++) {
            // Get the current widget as a child of the parent element and if it is a toggle button
            // check to see if it is checked:
            View currentWidget = seatingChartVG.getChildAt(i);
            if(currentWidget instanceof ToggleButton)
            {
                if(((ToggleButton) currentWidget).isChecked()) {
                    // Declare a temp seat class object to use:
                    Seat seat = new Seat();

                    // Set the seat number:
                    seat.setId(Integer.parseInt(((ToggleButton) currentWidget).getText().toString()));

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

                    // Add the seat to the Seat List:
                    tempSeats.add(seat);
                }
            }
        }

        // Error for no seat being selected:
        if(tempSeats.isEmpty()) {
            binding.glSeatingChart.requestFocus();
            binding.glSeatingChart.setBackgroundColor(Color.parseColor("#FF7F7F"));
            binding.glSeatingChart.setBackgroundResource(R.drawable.shape_rectangle_error);

            // Create the Snackbar to be displayed to the user:
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Please select at least one seat", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        // Error for no show time being selected:
        if(showTime.isEmpty()) {
            binding.chpGroup.requestFocus();
            binding.chpGroup.setBackgroundColor(Color.parseColor("#FF7F7F"));
            binding.chpGroup.setBackgroundResource(R.drawable.shape_rectangle_error);

            // Create the Snackbar to be displayed to the user:
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Please select at least one showtime", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        // Get the showTime and seats and assign them to the event class object:
        event.setShowTime(showTime);
        event.setSeat(tempSeats);

        // Create the new intent to send the event data to the Order Summary Activity:
        Intent intent = new Intent( this, OrderSummaryActivity.class);
        intent.putExtra(EXTRA_MOVIE, event);

        // Start the new activity and send the data at the same time:
        startActivity( intent );
    }
}