package com.agungsubastian.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private String[] dataTitle;
    private String[] dataScore;
    private String[] dataDate;
    private String[] dataDescription;
    private TypedArray dataImage;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ListView listView = findViewById(R.id.listMovie);
        movieAdapter = new MovieAdapter(this);
        listView.setAdapter(movieAdapter);

        init();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie dataMovie = movies.get(i);
                Movie mv = new Movie();
                mv.setImage(dataMovie.getImage());
                mv.setTitle(dataMovie.getTitle());
                mv.setScore(dataMovie.getScore());
                mv.setDate(dataMovie.getDate());
                mv.setDescription(dataMovie.getDescription());

                Intent movieIntent = new Intent(MovieActivity.this,DetailMovieActivity.class);
                movieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, mv);
                startActivity(movieIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        assert searchManager != null;
        searchView.setQueryHint("Title Search");
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                movieAdapter.filter(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void init(){
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataScore = getResources().getStringArray(R.array.data_score);
        dataDate = getResources().getStringArray(R.array.data_date);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataImage = getResources().obtainTypedArray(R.array.data_image);
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setImage(dataImage.getResourceId(i,-1));
            movie.setTitle(dataTitle[i]);
            movie.setScore(dataScore[i]);
            movie.setDate(dataDate[i]);
            movie.setDescription(dataDescription[i]);
            movies.add(movie);
        }
        movieAdapter.setMovie(movies);
    }
}
