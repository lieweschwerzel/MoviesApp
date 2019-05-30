package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.model.Movie;


public class DetailActivity extends AppCompatActivity {
    TextView mMovieName, mPlot, mRating, mReleaseDate;
    ImageView mMoviePoster, mMovieBackdrop, mStar;
    Movie movie;
    String movieName, plot, rating, releaseDate, moviePoster, movieBackdrop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMovieName = (TextView) findViewById(R.id.name);
        mPlot = (TextView) findViewById(R.id.plot);
        mRating = (TextView) findViewById(R.id.rating);
        mReleaseDate = (TextView) findViewById(R.id.releasedate);
        mMoviePoster = (ImageView) findViewById(R.id.posterView);
        mMovieBackdrop = (ImageView) findViewById(R.id.backdropView);
        mStar = (ImageView) findViewById(R.id.starView);

        Intent intent = getIntent();
        movie = getIntent().getParcelableExtra("movies");
        moviePoster = movie.getPosterPath();
        movieBackdrop = movie.getBackdropPath();
        mStar.setImageResource(R.drawable.ic_star);
        movieName = movie.getOriginalTitle();
        plot = movie.getOverview();
        rating = Double.toString(movie.getVoteAverage());
        releaseDate = "Release: "+ movie.getReleaseDate();

        String poster = "https://image.tmdb.org/t/p/w500" + moviePoster;
        String backdrop = "https://image.tmdb.org/t/p/w500" + movieBackdrop;

        Glide.with(this)
                .load(poster)
                .into(mMoviePoster);

        Glide.with(this)
                .load(backdrop)
                .into(mMovieBackdrop);

        mMovieName.setText(movieName);
        mPlot.setText(plot);
        mRating.setText(rating);
        mReleaseDate.setText(releaseDate);
    }
}
