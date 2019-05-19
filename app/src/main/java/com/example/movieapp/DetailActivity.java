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

    //    private final AppCompatActivity activity = DetailActivity.this;
    TextView mMovieName, mPlot, mRating, mReleaseDate;
    ImageView mMoviePoster;
    Movie movie;
    String movieName, plot, rating, releaseDate, moviePoster;
//    int movieId;
//    private RecyclerView recyclerView;

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
        mMoviePoster = (ImageView) findViewById(R.id.posterDetail);

        Intent intent = getIntent();
        movie = getIntent().getParcelableExtra("movies");
        moviePoster = movie.getPosterPath();
        movieName = movie.getOriginalTitle();
        plot = movie.getOverview();
        rating = Double.toString(movie.getVoteAverage());
        releaseDate = movie.getReleaseDate();

        String poster = "https://image.tmdb.org/t/p/w500" + moviePoster;

        Glide.with(this)
                .load(poster)
                .into(mMoviePoster);

        mMovieName.setText(movieName);
        mPlot.setText(plot);
        mRating.setText(rating);
        mReleaseDate.setText(releaseDate);
    }
}
