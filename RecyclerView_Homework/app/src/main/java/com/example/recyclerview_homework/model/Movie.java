package com.example.recyclerview_homework.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Movie implements Parcelable {
    private String movieTitle, date, duration, summary;
    private @DrawableRes int imageMovie;
    private int gallery;
    public Movie(){ }

    public Movie(String movieTitle, String date, String duration, String summary, int imageMovie) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.duration = duration;
        this.summary = summary;
        this.imageMovie = imageMovie;
    }

    protected Movie(Parcel in) {
        movieTitle = in.readString();
        date = in.readString();
        duration = in.readString();
        summary = in.readString();
        imageMovie = in.readInt();
        gallery = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieTitle);
        dest.writeString(date);
        dest.writeString(duration);
        dest.writeString(summary);
        dest.writeInt(imageMovie);
        dest.writeInt(gallery);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(int imageMovie) {
        this.imageMovie = imageMovie;
    }

}
