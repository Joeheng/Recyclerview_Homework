package com.example.recyclerview_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview_homework.model.Movie;

public class Detail_Movie_Activity extends AppCompatActivity {
    ImageView movieImage;
    TextView movieTitle, movieDate, movieSummary, movieDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__movie_);
        movieImage = findViewById(R.id.movie_Image);
        movieDate = findViewById(R.id.movie_Date);
        movieTitle = findViewById(R.id.movie_Name);
        movieSummary = findViewById(R.id.movie_Summary);
        movieDuration = findViewById(R.id.movie_Duration);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        movieImage.setImageResource(movie.getImageMovie());
        movieDate.setText("" + movie.getDate());
        movieTitle.setText(""+ movie.getMovieTitle());
        movieSummary.setText(""+movie.getSummary());
        movieDuration.setText(""+movie.getDuration());
    }
}
