package com.example.kjh_stock01.ui.notifications.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("boxOfficeResult")
    @Expose
    private MovieResult movieResult;

    public MovieResult getMovieResult() {
        return movieResult;
    }

    public void setMovieResult(MovieResult movieResult) {
        this.movieResult = movieResult;
    }
}
