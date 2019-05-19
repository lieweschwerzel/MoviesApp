package com.example.movieapp;

import com.example.movieapp.api.MovieApi;
import com.example.movieapp.api.Service;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MoviesResponse;

import retrofit2.Call;

public class MovieRepository {

    private Service movieService = MovieApi.create();

    public Call<MoviesResponse> getPopularMovies(String year) {
        return movieService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, year);
    }

}


