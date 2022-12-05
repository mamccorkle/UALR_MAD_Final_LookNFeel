package com.example.cinemahub_looknfeel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemahub_looknfeel.R;
import com.example.cinemahub_looknfeel.model.Movie;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter
{
    private List<Movie> mMovies;
    private Context mContext;
    private OnItemClickListener mOnMovieClickListener;

    // Constructor:
    public AdapterList(Context context, List<Movie> movies)
    {
        this.mMovies = movies;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_view_v2, parent, false);
        vh = new MovieViewHolder(itemView);
        return vh;
    }

    // Required overridden method: This method is called when the layout manager is ready to display
    //                             a new particular view in the recycler view area
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;
        Movie i = mMovies.get(position);
        int[] resImages = {
                R.drawable.movie_black_adam,
                R.drawable.movie_halloween_ends,
                R.drawable.movie_one_piece_film_red,
                R.drawable.movie_prey_for_the_devil,
                R.drawable.movie_smile,
                R.drawable.movie_ticket_to_paradise,
                R.drawable.icon_generic_movie_poster
        };
        viewHolder.ivMoviePoster.setImageResource(resImages[i.getImageIndex()]);
        viewHolder.tvMovieTitle.setText(i.getTitle());
        viewHolder.tvMovieRating.setText(i.getRating().name());
        viewHolder.tvMovieReleaseDate.setText(i.getDate());
        viewHolder.tvMovieGenre.setText(i.getGenre().name());
        viewHolder.tvMovieScore.setRating((float)(i.getScore()/2));
    }

    //    public void removeItem( int position )
    //    {
    //        //// Check for out-of-bounds:
    //        //if( position >= mItems.size() )
    //        //    return;
    //
    //        //// Remove the item from the list:
    //        //mItems.remove( position );
    //
    //        //// Notify the adapter that an item has been removed from the list:
    //        //notifyItemRemoved( position );
    //
    //        //// Notify the adapter the range has changed since removing the item:
    //        //notifyItemRangeChanged( position, getItemCount() );
    //    }

    //    //public void addItem( int position, Inbox email )
    //    public void addItem( int position )
    //    {
    //        //// Add the item to the list:
    //        //mItems.add( email );
    //
    //        //// Notify the adapter that an item has been removed from the list:
    //        //notifyItemInserted( position );
    //    }

    // Required overridden method: This method is called when the layout manager needs to determine
    //                             how many elements are available in the list/data collection
    @Override
    public int getItemCount()
    {
        return this.mMovies.size();
    }

    public interface OnItemClickListener
    {
        void onItemClick( View v, Movie movie, int position );
    }

    public void setOnItemClickListener( final OnItemClickListener mItemClickListener )
    {
        this.mOnMovieClickListener = mItemClickListener;
    }

    public Context getContext()
    {
        return this.mContext;
    }

    // ViewHolder Internal Class:
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView     ivMoviePoster;
        public TextView      tvMovieTitle;
        public TextView      tvMovieRating;
        public TextView      tvMovieReleaseDate;
        public TextView      tvMovieGenre;
        public RatingBar     tvMovieScore;
        public View          layoutParent;

        // ViewHolder Constructor:
        public MovieViewHolder(View v) {
            super(v);
            ivMoviePoster         = v.findViewById(R.id.ivMoviePoster);
            tvMovieTitle          = v.findViewById(R.id.tvMovieTitle);
            tvMovieRating         = v.findViewById(R.id.tvMovieRating);
            tvMovieReleaseDate    = v.findViewById(R.id.tvMovieReleaseDate);
            tvMovieGenre          = v.findViewById(R.id.tvMovieGenre);
            tvMovieScore          = v.findViewById(R.id.ratingBar);
            layoutParent          = v.findViewById(R.id.layoutParent);

            // On-Click Listener for the parent/root component of the activity:
            layoutParent.setOnClickListener(v1 -> mOnMovieClickListener.onItemClick(v1, mMovies.get(getLayoutPosition()), getLayoutPosition()));
        }
    }
}