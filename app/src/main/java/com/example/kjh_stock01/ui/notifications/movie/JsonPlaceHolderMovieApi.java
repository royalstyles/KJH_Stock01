package com.example.kjh_stock01.ui.notifications.movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderMovieApi {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?")
    Call<Result> getBoxOffice(@Query("key") String key
                                ,@Query(("targetDt")) String targetdt);
}
