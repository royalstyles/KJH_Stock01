package com.example.kjh_stock01.ui.notifications.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResult {

    @SerializedName("boxofficeType")
    @Expose
    private String boxofficeType;

    @SerializedName("showRange")
    @Expose
    private String showRange;

    @SerializedName("yearWeekTime")
    @Expose
    private String yearWeekTime;

    @SerializedName("dailyBoxOfficeList")
    @Expose
    private List<Movie> movieslist = null;

    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public String getYearWeekTime() {
        return yearWeekTime;
    }

    public void setYearWeekTime(String yearWeekTime) {
        this.yearWeekTime = yearWeekTime;
    }

    public List<Movie> getMovieslist() {
        return movieslist;
    }

    public void setMovieslist(List<Movie> movieslist) {
        this.movieslist = movieslist;
    }
}
