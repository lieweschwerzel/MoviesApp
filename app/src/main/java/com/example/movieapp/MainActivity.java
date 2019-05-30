package com.example.movieapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.adapter.MoviesAdapter;
import com.example.movieapp.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> mMovies;
    private MoviesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mYear;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYear = findViewById(R.id.yearEdit);
        //Initialize the recycleview variables
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String year = mYear.getText().toString();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String year = mYear.getText().toString();
                viewModel.getPopularMovies(year);
            }
        });


        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mMovies = movies;
                updateUI();
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new MoviesAdapter(getApplicationContext(), mMovies);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mMovies);
        }
    }

}




