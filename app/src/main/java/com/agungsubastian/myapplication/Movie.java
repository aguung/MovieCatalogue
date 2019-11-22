package com.agungsubastian.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int image;
    private String title;
    private String description;
    private String date;
    private String score;

    Movie() {}
    private Movie(Parcel in) {
        image = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        score = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    int getImage() {
        return image;
    }

    void setImage(int image) {
        this.image = image;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }

    String getScore() {
        return score;
    }

    void setScore(String score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeString(score);
    }
}
