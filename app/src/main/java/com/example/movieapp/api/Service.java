package com.example.movieapp.api;

import com.example.movieapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Service {

    @GET("discover/movie?&sort_by=popularity.desc&with_original_language=en")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("primary_release_year") String year);
}