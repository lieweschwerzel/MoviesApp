package com.example.movieapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository = new MovieRepository();

    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {super(application);}

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return mMovies;
    }


    public void getPopularMovies(String year) {
        movieRepository
                .getPopularMovies(year)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        List<Movie> movies = response.body().getResults();
                        mMovies.setValue(movies);
                    }
                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d("Errror", t.getMessage());
                        Toast.makeText(getApplication(), "Errror Fetching Data!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

