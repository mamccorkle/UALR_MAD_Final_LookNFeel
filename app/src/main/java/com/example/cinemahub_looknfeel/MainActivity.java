package com.example.cinemahub_looknfeel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.acitvity_register);
//        setContentView(R.layout.activity_login);
//        setContentView(R.layout.activity_theater_selection);
//        setContentView(R.layout.activity_theater_selection_v2);
//        setContentView(R.layout.activity_payment);
//        setContentView(R.layout.activity_seating_chart);
//        setContentView(R.layout.activity_main_page);
//        setContentView(R.layout.activity_main_page_v2);
//        setContentView(R.layout.activity_seating_chart_v2);
        setContentView(R.layout.activity_seating_chart_v3);



        // Actionbar Set Up: ///////////////////////////////////////////////////////////////////////

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        ////////////////////////////////////////////////////////////////////////////////////////////



        // Spinner Set Up: /////////////////////////////////////////////////////////////////////////

        Spinner spinner = (Spinner) findViewById(R.id.seatSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.seats_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ////////////////////////////////////////////////////////////////////////////////////////////

    }
}