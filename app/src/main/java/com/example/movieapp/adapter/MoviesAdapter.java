package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapp.DetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {
    public List<Movie> mMovies;
    private Context mContext;

    public MoviesAdapter(Context mContext, List<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.movie_card, viewGroup, false);

        MoviesAdapter.MovieHolder viewHolder = new MoviesAdapter.MovieHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public void onBindViewHolder(final MovieHolder viewHolder, int pos) {
        Movie movies = mMovies.get(pos);

        String position = Integer.toString(pos + 1);
        viewHolder.textViewPosition.setText(position);

        String poster = "https://image.tmdb.org/t/p/w500" + movies.getPosterPath();

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.loading)
                .into(viewHolder.vMoviePoster);
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView textViewPosition;
        public ImageView vMoviePoster;

        public MovieHolder(View view) {
            super(view);
            textViewPosition = view.findViewById(R.id.position);
            vMoviePoster = view.findViewById(R.id.moviePosterCard);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Movie clickedDataItem = mMovies.get(pos);
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("movies", clickedDataItem);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    Toast.makeText(v.getContext(), "You clicked " + pos, Toast.LENGTH_LONG).show();
                }
//                }
            });
        }
    }

    public void swapList(List<Movie> newList) {
        mMovies = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
