package com.example.movieapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.adapter.MoviesAdapter;
import com.example.movieapp.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {
        private List<Movie> mMovies;
//
//    private MoviesAdapter adapter;
//    private RecyclerView mRecyclerView;
    private EditText mYear;
    private MainActivityViewModel viewModel;

    private GestureDetector mGestureDetector;
    public static final String LOG_TAG = MoviesAdapter.class.getName();


//    private AppCompatActivity activity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String year = mYear.getText().toString();
//                Toast.makeText(activity, year, Toast.LENGTH_SHORT).show();
                viewModel.getPopularMovies(year);
//                mAdapter.notifyDataSetChanged();

            }
        });

        mYear = findViewById(R.id.yearEdit);

        //Initialize the recycleview variables
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MoviesAdapter adapter = new MoviesAdapter(this, mMovies);
        recyclerView.setAdapter(adapter);

        String year = mYear.getText().toString();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
//                adapter.submitList(movies);
                mMovies = movies;
                Toast.makeText(MainActivity.this, movies.get(1).toString(), Toast.LENGTH_SHORT).show();
            }
        });



//        //Initialize the instance view model
//        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
//
//        viewModel.getError().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//
//        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(@Nullable List<Movie> movies) {
//                mMovies = movies;
//                mAdapter.submitList(movies );
////                mAdapter = new MoviesAdapter(getApplicationContext(), movies);
////                mRecyclerView.setAdapter(mAdapter);
//            }
    }


//
//        viewModel.getPopularMovies("2012");
////        mAdapter.notifyDataSetChanged();
//


}
//
//    private void startRecyler() {
//        mMovies = new ArrayList<>();
//        mAdapter = new MoviesAdapter(this, mMovies);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
////        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//    }

//    private void loadJSON(String Year) {
//        MovieApi MovieApi = new MovieApi();
//        Service apiService =
//                MovieApi.create();
//        Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, Year);
//        call.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                List<Movie> movies = response.body().getResults();
//                Toast.makeText(MainActivity.this, movies.get(1).toString(), Toast.LENGTH_SHORT).show();
//
//                mRecyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
//                mRecyclerView.smoothScrollToPosition(0);
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                Log.d("Errror", t.getMessage());
//                Toast.makeText(MainActivity.this, "Errror Fetching Data!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

//    private void updateUI() {
//        if (mAdapter == null) {
//            mAdapter = new MoviesAdapter(getApplicationContext(), mMovies);
//            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.swapList(mMovies);
//        }
//    }




