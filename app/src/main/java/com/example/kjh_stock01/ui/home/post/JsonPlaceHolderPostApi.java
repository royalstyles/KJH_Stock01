package com.example.kjh_stock01.ui.home.post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderPostApi {

    @GET("/posts")
    Call<List<Post>>getPost();
}
