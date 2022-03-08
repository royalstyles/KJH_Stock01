package com.example.kjh_stock01.ui.notifications.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.R;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    ArrayList<Movie> item = new ArrayList<>();
    Context context;
    private MovieItemCliecked listener;

//    public MovieListAdapter(ArrayList<Movie> item, Context context) {
//        this.item = item;
//        this.context = context;
//    }

    public MovieListAdapter(MovieItemCliecked movieItemClicked) {
        this.listener = movieItemClicked;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie02, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentItem = item.get(position);

        holder.rank.setText(currentItem.getRank());
        holder.movieNm.setText(currentItem.getMovieNm());
        holder.openDt.setText(currentItem.getOpenDt());
        holder.audiCnt.setText(currentItem.getAudiCnt());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void UpdateMovie(ArrayList<Movie> updatemovie) {
        item.clear();
        item.addAll(updatemovie);

        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView rrum, rank, rankInten, rankOldAndNew, movieCd, movieNm, openDt
                , salesAmt, salesShare, salesInten, salesChange, salesAcc
                , audiCnt, audiInten, audiChange, audiAcc
                , scrnCnt, showCnt;

        public MovieViewHolder(@NonNull View view) {
            super(view);
            rank = view.findViewById(R.id.rank);
            movieNm = view.findViewById(R.id.movie_name);
            openDt = view.findViewById(R.id.count1);
            audiCnt = view.findViewById(R.id.count2);
        }
    }
}

