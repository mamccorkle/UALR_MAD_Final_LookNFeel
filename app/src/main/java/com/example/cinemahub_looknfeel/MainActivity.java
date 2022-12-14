package com.example.cinemahub_looknfeel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cinemahub_looknfeel.Adapter.AdapterList;
import com.example.cinemahub_looknfeel.Utils.DataGenerator;
import com.example.cinemahub_looknfeel.databinding.ActivityMainPageV2Binding;
import com.example.cinemahub_looknfeel.databinding.ActivityMovieSelectionBinding;
import com.example.cinemahub_looknfeel.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMainPageV2Binding binding;

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
//        setContentView(R.layout.activity_seating_chart_v3);
//        setContentView(R.layout.activity_movie_view);
//        setContentView(R.layout.activity_movie_view_v2);
//        setContentView(R.layout.activity_movie_details);
//        setContentView(R.layout.activity_movie_selection);
        setContentView(R.layout.activity_main_page_v2);

        // TODO: link showtimes to the class on the movie details page
        // TODO: link seating to the class on the seating page
        // TODO: create the theater class using the theater selection page
        // TODO: add the seating, movie, and theater to the event class

//        // Actionbar Set Up: ///////////////////////////////////////////////////////////////////////
//        // calling the action bar
//        ActionBar actionBar = getSupportActionBar();
//
//        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        ////////////////////////////////////////////////////////////////////////////////////////////

        View btnMovies = findViewById(R.id.ibMovies);
        btnMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the new intent to go to the movie selection screen:
                Intent startMovieSelection = new Intent(MainActivity.this, MovieSelectionActivity.class);

                // Start the new activity and send the data at the same time:
                startActivity( startMovieSelection );
            }
        });

        View btnTheaters = findViewById(R.id.ibTheaters);
        btnTheaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the new intent to go to the movie selection screen:
                Intent startTheaterSelection = new Intent(MainActivity.this, TheaterSelectionActivity.class);

                // Start the new activity and send the data at the same time:
                startActivity( startTheaterSelection );
            }
        });

        View btnTickets = findViewById(R.id.ibTickets);
        btnTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the new intent to go to the movie selection screen:
                Intent startTicketList = new Intent(MainActivity.this, TicketListActivity.class);

                // Start the new activity and send the data at the same time:
                startActivity( startTicketList );
            }
        });

    }
}