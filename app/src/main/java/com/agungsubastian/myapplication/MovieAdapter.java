package com.agungsubastian.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movieList = new ArrayList<>();
    private ArrayList<Movie> movie = new ArrayList<>();

    void setMovie(ArrayList<Movie> movie) {
        this.movie = movie;
        this.movieList.addAll(movie);
    }

    MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_list_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private TextView txtScore;
        private TextView txtDate;
        private TextView txtDescription;
        private ImageView imgMovie;

        ViewHolder(View view) {
            txtTitle = view.findViewById(R.id.txt_title);
            txtScore = view.findViewById(R.id.txt_score);
            txtDate = view.findViewById(R.id.txt_date);
            txtDescription = view.findViewById(R.id.txt_description);
            imgMovie = view.findViewById(R.id.img_photo);
        }

        void bind(Movie movie) {
            txtTitle.setText(movie.getTitle());
            txtScore.setText(movie.getScore());
            txtDate.setText(movie.getDate());
            txtDescription.setText(movie.getDescription());
            imgMovie.setImageResource(movie.getImage());
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        movieList.clear();
        if (charText.length() == 0) {
            movieList.addAll(movie);
        } else {
            for (Movie mv : movie) {
                if (mv.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    movieList.add(mv);
                }
            }
        }
        notifyDataSetChanged();
    }
}
