package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.DetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {
    public List<Movie> mMovies;
    private Context mContext;

//    public MoviesAdapter() {
//        super(DIFF_CALLBACK);
//    }

//    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
//        @Override
//        public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
//            return oldItem.getId() == newItem.getId();
//        }
//
//        @Override
//        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
//            return oldItem.getTitle().equals(newItem.getTitle()) &&
//                    oldItem.getOriginalTitle().equals(newItem.getOriginalTitle()) &&
//                    oldItem.isAdult() == newItem.isAdult();
//        }
//    };




    public MoviesAdapter(Context mContext, List<Movie> mMovies){
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int pos){
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.movie_card, viewGroup, false);

        MoviesAdapter.MovieHolder viewHolder = new MoviesAdapter.MovieHolder(view);
        return viewHolder;
//        return new MovieHolder(view);
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(final MovieHolder viewHolder, int pos){
        Movie movies = mMovies.get(pos);

//        Movie currentMovie = get(pos);
        String position = Integer.toString(pos+1);
        viewHolder.textViewPosition.setText("1");

        String poster = "https://image.tmdb.org/t/p/w500" + movies.getPosterPath();

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.loading)
                .into(viewHolder.vMoviePoster);
    }

//    public Movie getMovieAt(int pos){
//        return getItem(pos);
//    }


    public class MovieHolder extends RecyclerView.ViewHolder{
        public TextView textViewPosition;
        public ImageView vMoviePoster;

        public MovieHolder(View view){
            super(view);
            textViewPosition = view.findViewById(R.id.position);
            vMoviePoster = view.findViewById(R.id.moviePosterCard);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                        Movie clickedDataItem = mMovies.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movies", clickedDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + pos, Toast.LENGTH_LONG).show();
                    }
//                }
            });
        }
    }



}
