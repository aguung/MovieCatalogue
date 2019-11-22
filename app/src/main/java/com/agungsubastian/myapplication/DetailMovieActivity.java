package com.agungsubastian.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Detail Movie");
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        TextView title = findViewById(R.id.title);
        TextView date = findViewById(R.id.date);
        TextView score = findViewById(R.id.score);
        TextView description = findViewById(R.id.description);
        ImageView image = findViewById(R.id.img_photo);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert movie != null;
        title.setText(movie.getTitle());
        date.setText(movie.getDate());
        score.setText(movie.getScore());
        description.setText(movie.getDescription());
        image.setImageResource(movie.getImage());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
