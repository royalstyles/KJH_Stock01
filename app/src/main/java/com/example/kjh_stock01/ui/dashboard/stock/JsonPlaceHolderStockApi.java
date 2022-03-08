package com.example.kjh_stock01.ui.dashboard.stock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderStockApi {

    @GET("/api/v3/stock_market/gainers?")
    Call<List<Stock>>getMostGainer(@Query("apikey") String apikey);

}
