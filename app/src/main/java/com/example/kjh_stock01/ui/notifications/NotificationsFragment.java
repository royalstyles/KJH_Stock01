package com.example.kjh_stock01.ui.notifications;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.R;
import com.example.kjh_stock01.databinding.FragmentNotificationsBinding;
import com.example.kjh_stock01.ui.notifications.movie.JsonPlaceHolderMovieApi;
import com.example.kjh_stock01.ui.notifications.movie.Movie;
import com.example.kjh_stock01.ui.notifications.movie.MovieItemCliecked;
import com.example.kjh_stock01.ui.notifications.movie.MovieListAdapter;
import com.example.kjh_stock01.ui.notifications.movie.MovieResult;
import com.example.kjh_stock01.ui.notifications.movie.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment implements MovieItemCliecked {

    final String BASE_URL = "http://kobis.or.kr";
    RecyclerView recyclerView;
    MovieListAdapter mAdapter;

    Retrofit retrofit;
    JsonPlaceHolderMovieApi movieApi;

    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

    private FragmentNotificationsBinding binding;

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat sdf_yyyy = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdf_MM = new SimpleDateFormat("MM");
    SimpleDateFormat sdf_dd = new SimpleDateFormat("dd");

    private DatePickerDialog.OnDateSetListener callbackMethod;
    private String yyyymmdd;

    TextView txtviewDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.nofiti_recyclerview);

        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        yyyymmdd = sdf1.format(cal.getTime());

        txtviewDate = binding.MovieDate;
        txtviewDate.setText("날짜 : " + sdf2.format(cal.getTime()));

        txtviewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getClass().getName(), "onClick(View view)");
                Log.d(getClass().getName(), sdf2.format(cal.getTime()));
                DatePickerDialog dialog = new DatePickerDialog(getContext(), callbackMethod
                        , Integer.parseInt(sdf_yyyy.format(cal.getTime()))
                        , Integer.parseInt(sdf_MM.format(cal.getTime())) - 1
                        , Integer.parseInt(sdf_dd.format(cal.getTime())));

                dialog.show();
            }
        });

        Log.d(getClass().getName(), "onCreateView fetch_date()");
        fetch_date();

        mAdapter = new MovieListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(getClass().getName(), "onDateSet(DatePicker datePicker, int year, int month, int day)");

                txtviewDate.setText("날짜 : " + year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", day));
                yyyymmdd = Integer.toString(year) + String.format("%02d", month + 1) + String.format("%02d", day);
                Toast.makeText(getContext(), yyyymmdd, Toast.LENGTH_SHORT).show();

                fetch_date();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void fetch_date(){

        Log.d(getClass().getName(), "fetch_date()");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.d(getClass().getName(), "retrofit start");

        movieApi = retrofit.create(JsonPlaceHolderMovieApi.class);

        movieApi.getBoxOffice(Movie.getApiKey(), yyyymmdd).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(getClass().getName(), "retrofit onResponse");

                if (!response.isSuccessful()){
                    Log.d(getClass().getName(), "Code : " + response.code());
                    return;
                }

                Result result = response.body();
                MovieResult movieResult = result.getMovieResult();
                Log.d(getClass().getName(), movieResult.getBoxofficeType());
                Log.d(getClass().getName(), movieResult.getShowRange());
                List<Movie> movieList = movieResult.getMovieslist();

                movieArrayList.clear();

                for (Movie movie : movieList){
                    Log.d(getClass().getName(), "retrofit onResponse for");
                    String contents = "";
                    contents += "\n" + "Rank : " + movie.getRank() + "\n";
                    contents += "MovieNm : " + movie.getMovieNm() + "\n";
                    contents += "OpenDt : " + movie.getOpenDt() + "\n";
                    contents += "AudiCnt : " + movie.getAudiCnt() + "\n";

//                    Log.d((getClass().getName()), contents);

                    Movie movieitem = new Movie(
                            movie.getRank(),
                            movie.getMovieNm(),
                            movie.getOpenDt(),
                            movie.getAudiCnt()
                    );
                    movieArrayList.add(movieitem);
                }
                Log.d(getClass().getName(), "retrofit onResponse5");

//                recyclerView.removeAllViewsInLayout();
//                recyclerView.setAdapter(mAdapter);
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                mAdapter.UpdateMovie(movieArrayList);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(getClass().getName(), t.getMessage().toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(Movie movie) {

    }
}