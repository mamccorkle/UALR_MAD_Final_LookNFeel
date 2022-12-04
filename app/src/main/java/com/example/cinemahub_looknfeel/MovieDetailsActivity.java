package com.example.cinemahub_looknfeel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivityMovieDetailsBinding;
import com.example.cinemahub_looknfeel.model.Movie;
import com.google.android.material.chip.Chip;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        // Enable the back button:
//        Toolbar toolbar = findViewById(R.id.bottomAppBar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponent();
    }

    private void initComponent() {
        Movie movieReceiving = getIntent().getParcelableExtra(MovieSelectionActivity.EXTRA_MOVIE);

        binding.tvTitle.setText(movieReceiving.getTitle());
        binding.tvDate.setText(movieReceiving.getDate());
        binding.tvRating.setText(movieReceiving.getRating().name());
        binding.ratingBar.setRating((float)movieReceiving.getScore());
        binding.tvRuntime.setText(String.valueOf(movieReceiving.getRuntime()));
        binding.etSynopsis.setText(movieReceiving.getSynopsis());
        binding.tvDirector.setText(movieReceiving.getDirector());

        // Cast:
        String castMembers = "";
        for (int i = 0; i < movieReceiving.getCast().size() - 1; i++)
        {
            castMembers += movieReceiving.getCast().get(i) + "\n";
        }
        castMembers += movieReceiving.getCast().get(movieReceiving.getCast().size() - 1);
        binding.etCast.setText(castMembers);

        binding.tvGenre.setText(movieReceiving.getGenre().name());

        // Showtimes:
        List<String> showTimes = movieReceiving.getShowTimes();
        for (String showTime : showTimes )
        {
            Chip tempChip = new Chip(this);
            tempChip.setText(showTime);
            binding.chpGroup.addView(tempChip);
        }

        // Poster - Imageindex:
        int[] resImages = {
                R.drawable.black_adam,
                R.drawable.halloween_ends,
                R.drawable.one_piece_film_red,
                R.drawable.prey_for_the_devil,
                R.drawable.smile,
                R.drawable.ticket_to_paradise,
                R.drawable.poster_light
        };
        binding.ivPoster.setImageResource(resImages[movieReceiving.getImageIndex()]);

        // YouTube link:
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = movieReceiving.getYoutubeLink();

                // Load, but dont play video:
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

    }
}