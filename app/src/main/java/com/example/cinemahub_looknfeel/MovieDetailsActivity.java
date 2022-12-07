package com.example.cinemahub_looknfeel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.Adapter.AdapterList;
import com.example.cinemahub_looknfeel.databinding.ActivityMovieDetailsBinding;
import com.example.cinemahub_looknfeel.model.Event;
import com.example.cinemahub_looknfeel.model.Movie;
import com.google.android.material.chip.Chip;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMovieDetailsBinding binding;

    public static final String EXTRA_MOVIE = "MovieData";
    public static final String EXTRA_MOVIE_SHOWTIMES = "ShowTimesData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // TODO: REMOVE Showtimes here:
        List<String> showTimes = movieReceiving.getShowTimes();
        for (String showTime : showTimes )
        {
            Chip tempChip = new Chip(this);
            tempChip.setText(showTime);
            tempChip.setCheckable(true);
            tempChip.setCheckedIconVisible(true);
            tempChip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempChip.setSelected(true);
                    tempChip.setChecked(true);
                }
            });
            binding.chpGroup.addView(tempChip);
        }

        // Poster - Imageindex:
        int[] resImages = {
                R.drawable.movie_black_adam,
                R.drawable.movie_halloween_ends,
                R.drawable.movie_one_piece_film_red,
                R.drawable.movie_prey_for_the_devil,
                R.drawable.movie_smile,
                R.drawable.movie_ticket_to_paradise,
                R.drawable.icon_generic_movie_poster
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

        binding.btnSelectSeat.setOnClickListener(v -> onSelectSeatBtnClicked(v, movieReceiving ));

    }

    //private void onSelectSeatBtnClicked(View v, Movie movie, AdapterList mAdapter ) {
    private void onSelectSeatBtnClicked(View v, Movie movie ) {
//        if(binding.chpGroup.getCheckedChipId() == -1) {
//            binding.chpGroup.requestFocus();
//            binding.chpGroup.setBackgroundColor(Color.parseColor("#FF7F7F"));
//        }
//        else {
            // Create the intent:
            Intent intent = new Intent(this, MovieSeatingChartActivity.class);

            // Create the event class and add the movie and showtime to it:
            //Event event = new Event(movie, ((Chip) binding.chpGroup.getChildAt((int)binding.chpGroup.getCheckedChipId() - 1)).getText().toString());
            Event event = new Event(movie);

//            Bundle bundle = new Bundle();
//            bundle.putParcelable(EXTRA_MOVIE, event);
//            bundle.putParcelable(EXTRA_MOVIE_SHOWTIMES, movie.getShowTimes());
//            intent.putExtras(bundle);

            // Set the intent to pass the event object:
            intent.putExtra(EXTRA_MOVIE, event);

            // Start the new activity and send the data at the same time:
            startActivity(intent);
//        }
    }

}