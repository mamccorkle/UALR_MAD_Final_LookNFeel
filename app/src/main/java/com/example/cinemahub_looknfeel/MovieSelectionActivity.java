package com.example.cinemahub_looknfeel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemahub_looknfeel.Adapter.AdapterList;
import com.example.cinemahub_looknfeel.Utils.DataGenerator;
import com.example.cinemahub_looknfeel.databinding.ActivityMovieSelectionBinding;
import com.example.cinemahub_looknfeel.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSelectionActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMovieSelectionBinding binding;

    public static final String EXTRA_MOVIE = "MovieData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent()
    {
        // RecyclerView Set Up: /////////////////////////////////////////////////////////////////////////

        binding = ActivityMovieSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Movie> movies = new ArrayList<>();
        movies.addAll(DataGenerator.generateMovieData(this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        AdapterList mAdapter = new AdapterList(this, movies);

        // Get the reference to the RecyclerView:
        RecyclerView recyclerView = binding.recyclerView;

        // Connect the Layout Manager and Adapter to the RecyclerView:
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((v, movie, position) -> selectionChange(v, movie, mAdapter));

        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void selectionChange(View v, Movie movie, AdapterList mAdapter )
    {
        // Create the intent:
        Intent intent = new Intent( this, MovieDetailsActivity.class );

        Movie movieSending = new Movie(
                movie.getTitle(),
                movie.getDate(),
                movie.getRating(),
                movie.getScore(),
                movie.getRuntime(),
                movie.getSynopsis(),
                movie.getDirector(),
                movie.getCast(),
                movie.getGenre(),
                movie.getShowTimes(),
                movie.getImageIndex(),
                movie.getYoutubeLink()
        );
        intent.putExtra(EXTRA_MOVIE, movieSending);

        // Start the new activity and send the data at the same time:
        startActivity( intent );
    }

}